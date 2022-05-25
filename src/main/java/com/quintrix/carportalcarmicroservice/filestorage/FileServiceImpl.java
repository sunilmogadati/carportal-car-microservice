package com.quintrix.carportalcarmicroservice.filestorage;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.quintrix.carportalcarmicroservice.car.CarEntity;
import com.quintrix.carportalcarmicroservice.car.CarEntityRepository;
import com.quintrix.carportalcarmicroservice.car.CarImagesEntity;
import com.quintrix.carportalcarmicroservice.car.CarImagesRepository;

@Service
public class FileServiceImpl implements FileService {

  @Autowired
  private AmazonS3Client awsS3;

  @Autowired
  CarEntityRepository carRepo;

  @Autowired
  CarImagesRepository photoRepo;

  @Override
  public String uploadFile(MultipartFile file, String id, int order) {

    // gets the car by id
    Optional<CarEntity> carentity = carRepo.findById(id);


    // if the car model dont exist do not upload file
    if (!carentity.isPresent()) {
      System.out.print("Empty Object");
      return null;
    }
    // if model does exist proceed
    else {
      System.out.print("Object is not empty");
      System.out.print(carentity.get().getUuid());


      // System.out.print(carentity.get().getUuid());

      // stores the file extension
      String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

      // create a unique key for the file and stores it
      String key = UUID.randomUUID().toString() + "." + extension;

      // Create an MetaData Object for the file
      ObjectMetadata metaData = new ObjectMetadata();

      // gets the file size
      metaData.setContentLength(file.getSize());

      // gets the content type
      metaData.setContentType(file.getContentType());

      // Puts File into Amazon S3 Bucket named dw-imagestorage
      try {
        awsS3.putObject("dw-imagestorage", key, file.getInputStream(), metaData);
      } catch (IOException e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            "Error ocured while uploading the file");
      }

      // makes the file access control public
      awsS3.setObjectAcl("dw-imagestorage", key, CannedAccessControlList.PublicRead);

      // returns the public url
      String photoUrl = awsS3.getResourceUrl("dw-imagestorage", key);

      // creates a carimage instance
      CarImagesEntity image = new CarImagesEntity();

      // add photo url, car id, and dporder to the carimage object
      image.setAwsS3Url(photoUrl);
      image.setCarInfoId(id);
      image.setDisplayOrder(order);

      // saves the image object into the database
      photoRepo.save(image);

      // returns the public url
      return photoUrl;
    }
  }

}

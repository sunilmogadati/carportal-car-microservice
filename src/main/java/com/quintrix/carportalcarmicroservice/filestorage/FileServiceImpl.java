package com.quintrix.carportalcarmicroservice.filestorage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
      String key = id + Integer.toString(order);

      // Create an MetaData Object for the file
      ObjectMetadata metaData = new ObjectMetadata();

      // gets the file size
      metaData.setContentLength(file.getSize());

      // gets the content type
      metaData.setContentType(file.getContentType());

      // Puts File into Amazon S3 Bucket named cardemo44
      try {
        awsS3.putObject("cardemo44", key, file.getInputStream(), metaData);
      } catch (IOException e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            "Error ocured while uploading the file");
      }

      // makes the file access control public
      awsS3.setObjectAcl("cardemo44", key, CannedAccessControlList.PublicRead);

      // returns the public url
      String photoUrl = awsS3.getResourceUrl("cardemo44", key);

      // creates a carimage instance
      CarImagesEntity image = new CarImagesEntity();

      // add photo url, car id, and dporder to the carimage object
      image.setAwsS3Url(photoUrl);
      image.setCarInfoId(id);
      image.setDisplayOrder(order);

      // saves the image object into the database
      photoRepo.save(image);
      // awsS3.deleteObject(key, key);

      // returns the public url
      return photoUrl;
    }
  }

  @Override
  public void deletePhotos(String carid) {

    List<CarImagesEntity> response = photoRepo.findByCarInfoId(carid);

    // prints to console all display orders FOR TESTING
    response.stream().forEach(x -> {
      System.out.print(x.getDisplayOrder());
    });

    // delete all images linked to the car with the id of carid
    response.stream().forEach(x -> {
      awsS3.deleteObject("cardemo44", carid + Integer.toString(x.getDisplayOrder()));
      photoRepo.delete(x);
    });
  }

}

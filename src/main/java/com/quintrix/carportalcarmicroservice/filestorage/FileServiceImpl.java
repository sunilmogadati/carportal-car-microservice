package com.quintrix.carportalcarmicroservice.filestorage;

import java.io.IOException;
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

@Service
public class FileServiceImpl implements FileService {

  @Autowired
  private AmazonS3Client awsS3;

  @Override
  public String uploadFile(MultipartFile file) {

    // stores the file extension
    String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

    // create a unique key for the file and stores it
    String key = UUID.randomUUID().toString() + "." + extension;

    ObjectMetadata metaData = new ObjectMetadata();

    metaData.setContentLength(file.getSize());
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
    return awsS3.getResourceUrl("dw-imagestorage", key);
  }

}

package com.quintrix.carportalcarmicroservice.filestorage;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  String uploadFile(MultipartFile file, String id);

}

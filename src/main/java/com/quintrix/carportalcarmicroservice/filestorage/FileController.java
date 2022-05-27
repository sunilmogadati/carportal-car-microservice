package com.quintrix.carportalcarmicroservice.filestorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileController {

  @Autowired
  private FileService fileservice;


  @PostMapping("/{carid}/{order}/file")
  public ResponseEntity<String> uploadFile(@PathVariable("carid") String carid,
      @PathVariable("order") int order, @RequestParam("file") MultipartFile file) {
    String publicUrl = fileservice.uploadFile(file, carid, order);
    return new ResponseEntity<String>(publicUrl, HttpStatus.CREATED);
  }
}



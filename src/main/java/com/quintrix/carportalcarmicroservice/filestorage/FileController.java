package com.quintrix.carportalcarmicroservice.filestorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "File API", description = "Upload a car's picture and recieve a URL to access it.")

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FileController {

  @Autowired
  private FileService fileservice;

  @Operation(summary = "Upload a file and recieve a URL.")
  @ApiResponse(responseCode = "201",
      content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
          schema = @Schema(implementation = ResponseEntity.class))

  )

  @PostMapping("/{carid}/{order}/file")
  public ResponseEntity<String> uploadFile(@PathVariable("carid") String carid,
      @PathVariable("order") int order, @RequestParam("file") MultipartFile file) {
    String publicUrl = fileservice.uploadFile(file, carid, order);
    return new ResponseEntity<String>(publicUrl, HttpStatus.CREATED);
  }
}



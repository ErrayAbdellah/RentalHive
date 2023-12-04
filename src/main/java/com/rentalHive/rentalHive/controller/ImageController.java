package com.rentalHive.rentalHive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }

        // Specify the directory where you want to save the uploaded images
        String uploadDirectory = "images/";

        // Get the original file name
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

        // Create the file path
        String filePath = uploadDirectory + originalFilename;

        // Create the destination file
        File destinationFile = new File(filePath);

        try {
            // Save the file to the specified path
            System.out.println("Upload Directory: " + uploadDirectory);
            System.out.println("File Path: " + filePath);

            file.transferTo(destinationFile);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error uploading file");
        }
    }
}

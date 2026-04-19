package com.org.jayanth.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Autowired
	private S3Client s3Client;
	
    private final String bucketName = "ecommerce-jayanth-invoice";
    
    
    public String uploadFile(File file) throws Exception {

        String key = "invoices/" + file.getName();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(request, file.toPath());

        // Return file URL
        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }

    
}

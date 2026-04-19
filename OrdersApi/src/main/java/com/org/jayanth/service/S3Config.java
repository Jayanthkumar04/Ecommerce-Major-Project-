package com.org.jayanth.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

	@Bean
    public S3Client s3Client() {

        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                "YOUR_ACCESS_KEY",
                "YOUR_SECRET_KEY"
        );

        return S3Client.builder()
                .region(Region.US_EAST_1) 
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}

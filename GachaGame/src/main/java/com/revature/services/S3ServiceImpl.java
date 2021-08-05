package com.revature.services;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3ServiceImpl implements S3Service{
	@Autowired
	private S3Client s3;
	@Value("${BUCKET_NAME}")
	public String BUCKET_NAME;
	
	// upload to the bucket
	public void uploadToBucket(String key, byte[] file) {
		s3.putObject(PutObjectRequest.builder().bucket(BUCKET_NAME).key(key).build(), RequestBody.fromBytes(file));
	}

	// download from the bucket
	public InputStream getObject(String key) {
		InputStream object = s3.getObject(GetObjectRequest.builder().bucket(BUCKET_NAME).key(key).build());
		return object;
	}
}

package com.revature.util;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3Util {
	public static final Region region = Region.US_EAST_2; // whatever your region is
	public static final String BUCKET_NAME= "2107-gacha";
	
	private static Logger log = LogManager.getLogger(S3Util.class);
	
	private static S3Util instance = null;
	
	private S3Client s3 = null;
	
	private S3Util() {
		s3 = S3Client.builder().region(region).build();
	}

	public static synchronized S3Util getInstance() {
		if(instance == null) {
			instance = new S3Util();
		}
		return instance;
	}
	
	
	// upload to the bucket
	public void uploadToBucket(String key, byte[] file) {
		log.trace("Uploading file as "+key);
		s3.putObject(PutObjectRequest.builder().bucket(BUCKET_NAME).key(key).build(),
				RequestBody.fromBytes(file));
		log.trace("Upload complete");
	}
	
	// download from the bucket
	public InputStream getObject(String key) {
		log.trace("Retrieving data from s3: "+key);
		InputStream object = s3.getObject(GetObjectRequest.builder().bucket(BUCKET_NAME).key(key).build());
		log.trace("Returning object");
		return object;
	}
}

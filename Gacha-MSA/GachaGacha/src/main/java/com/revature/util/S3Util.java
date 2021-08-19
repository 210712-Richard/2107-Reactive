package com.revature.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration // A stereotype
public class S3Util {
	@Bean // register the return value of this method as a Bean
	public S3Client s3Client() {
		return S3Client.builder().region(Region.US_EAST_2).build();
	}
}

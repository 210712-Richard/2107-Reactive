package com.revature.services;

import java.io.InputStream;

public interface S3Service {
	void uploadToBucket(String key, byte[] file);
	InputStream getObject(String key);
}

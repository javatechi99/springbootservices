package com.org.services.java.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StorageService {
	
	public String uploadImage(MultipartFile file) throws IOException;
	
	public byte[] downloadImage(String fileName);

}

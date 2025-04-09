package com.org.services.java.serviceImpl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.org.services.java.entity.ImageData;
import com.org.services.java.repository.StorageRepository;
import com.org.services.java.service.StorageService;
import com.org.services.java.util.ImageUtils;

@Component
public class StorageServiceImpl implements StorageService{
	
	   @Autowired
	    private StorageRepository repository;

	    public String uploadImage(MultipartFile file) throws IOException {

	        ImageData imageData = repository.save(ImageData.builder()
	                .name(file.getOriginalFilename())
	                .type(file.getContentType())
	                .imageData(ImageUtils.compressImage(file.getBytes())).build());
	        if (imageData != null) {
	            return "file uploaded successfully : " + file.getOriginalFilename();
	        }
	        return null;
	    }

	    public byte[] downloadImage(String fileName){
	        Optional<ImageData> dbImageData = repository.findByName(fileName);
	        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
	        return images;
	    }
	}


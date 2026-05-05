package com.cdsc.eshopdemo.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	private  String IMG_PATH = "src/main/resources/static/images/"; // Directory to save uploaded images
	
	public String saveImage(MultipartFile image) throws IOException {
		if (image != null && !image.isEmpty()) {
			IMG_PATH=IMG_PATH+image.getOriginalFilename(); // Set the path to save the image with its original filename
			 Files.copy(image.getInputStream(), Paths.get(IMG_PATH),StandardCopyOption.REPLACE_EXISTING); // Save the image to the specified path, replacing if it already exists);
		}
		
		System.out.println("Image saved at: " + IMG_PATH);
		
		return IMG_PATH;
	}

	    public InputStream getResource(String path, String name) throws FileNotFoundException {
	        String fullPath = path + File.separator + name;
	        InputStream inputStream = new FileInputStream(fullPath);
	        return inputStream;
	    }
}

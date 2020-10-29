package com.assignment.utilities;

import java.io.File;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UtilFunctions {
	
	@Value("${image-dir}")
	private String mainFolderPath;
	
	@Autowired
	ServletContext context; 
	
	
	public String uploadFileAndGetPath(MultipartFile file)
	{
		
		String path=null;
		
			System.out.println(Paths.get(mainFolderPath));
			
		
				try {
				 String fileName=file.getOriginalFilename();
				 InputStream is = file.getInputStream();
				 long unifier=System.currentTimeMillis();
				 Files.copy(is, Paths.get(mainFolderPath+File.separator+unifier+fileName));
				 File uploadedFile= new File(mainFolderPath+File.separator+unifier+fileName);
				 if(uploadedFile.exists())
				 {
					 path=uploadedFile.getName();
					 System.out.println(path);
				 }
				} catch (Exception e) {
					e.printStackTrace();
				}
		
	
		return path;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	         Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
}

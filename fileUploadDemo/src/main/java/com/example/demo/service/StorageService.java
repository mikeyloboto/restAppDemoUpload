package com.example.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Metadata;

@Service
public class StorageService {

	private final String ROOT_PATH = "C:\\UploadedFiles\\";
	private final String META_PATH = "C:\\UploadedFiles\\Metadata\\";

	@Autowired
	public StorageService() {
		new File(ROOT_PATH).mkdirs();
		new File(META_PATH).mkdirs();
	}

	public List<Metadata> getAllMetaFiles() {
		List<Metadata> output = new ArrayList<>();
		File[] files = new File(META_PATH).listFiles();
		for (int i = 0; i < files.length; i++) {
			try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream(files[i]));
				Metadata meta = (Metadata)is.readObject();
				is.close();
				output.add(meta);
			}
			catch (IOException e) {
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return output;
	}

	public Metadata storeFile(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		File fileOutput = new File(ROOT_PATH + fileName);
		File fileMetaOutput = new File(META_PATH + fileName + ".meta");
		Metadata metadata = new Metadata();
		try {
			file.transferTo(fileOutput);
			BasicFileAttributes attrs = Files.readAttributes(fileOutput.toPath(), BasicFileAttributes.class);
			metadata.setCreationDate(attrs.creationTime().toMillis());
			metadata.setFileName(fileName);
			metadata.setFileSize(attrs.size());
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileMetaOutput));
			os.writeObject(metadata);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return metadata;

	}

}

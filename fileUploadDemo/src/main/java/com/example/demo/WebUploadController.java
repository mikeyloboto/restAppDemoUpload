package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//
//import net.demo.demorest.entity.Metadata;
//import net.demo.demorest.storage.StorageService;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Metadata;
import com.example.demo.service.StorageService;

@RestController
public class WebUploadController {

	private final StorageService storageService;

	@Autowired
	public WebUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping(value = "/files/meta", produces = "application/json")
	public List<Metadata> getAllMetadata() {
		return storageService.getAllMetaFiles();
	}

	@PostMapping(value = "/files")
	public Metadata handleFileUpload(@RequestParam("file") MultipartFile file) {
		return storageService.storeFile(file);
	}

}

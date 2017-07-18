package com.example.demo.entity;

import java.io.Serializable;

public class Metadata implements Serializable{
	private static final long serialVersionUID = -5299402295934813596L;

	private String fileName;
	private Long creationDate;
	private Long fileSize;
	
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
}

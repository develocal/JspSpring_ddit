package com.spring.request;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.PdsVO;

public class RegistPdsRequest {
	
	// 변수명 form태그에서넘어오는 parameter에 맞출것.
	private String title;
	private String content;
	private String writer;
	private MultipartFile[] uploadFile;
	

	
	public RegistPdsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RegistPdsRequest(String title, String content, String writer, MultipartFile[] uploadFile) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.uploadFile = uploadFile;
	}

	@Override
	public String toString() {
		return "RegistPdsRequest [title=" + title + ", content=" + content + ", writer=" + writer + ", uploadFile="
				+ Arrays.toString(uploadFile) + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public MultipartFile[] getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile[] uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public PdsVO toPdsVO() {
		PdsVO pds = new PdsVO();
		pds.setContent(this.content);
		pds.setTitle(this.title);
		pds.setWriter(this.writer);
		
		return pds;
	}
	
}

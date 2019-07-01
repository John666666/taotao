package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.util.PictureResult;

public interface PicService {

	/**
	 * 上传文件
	 * @param uploadFile
	 * @return
	 */
	public PictureResult upload(MultipartFile uploadFile);
}

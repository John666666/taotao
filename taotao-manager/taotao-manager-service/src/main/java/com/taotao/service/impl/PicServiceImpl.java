package com.taotao.service.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.service.PicService;
import com.taotao.util.FtpUtil;
import com.taotao.util.IDUtils;
import com.taotao.util.PictureResult;

@Service
public class PicServiceImpl implements PicService {

	private Logger log = Logger.getLogger(PicServiceImpl.class);
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	@Value("${FILI_UPLOAD_PATH}")
	private String FILI_UPLOAD_PATH;
	@Value("${FTP_SERVER_IP}")
	private String FTP_SERVER_IP;
	@Value("${FTP_SERVER_PORT}")
	private Integer FTP_SERVER_PORT;
	@Value("${FTP_SERVER_USERNAME}")
	private String FTP_SERVER_USERNAME;
	@Value("${FTP_SERVER_PASSWORD}")
	private String FTP_SERVER_PASSWORD;
	
	
	
	@Override
	public PictureResult upload(MultipartFile uploadFile) {
		// 日期路径+文件文件名称
		String path = savePicture(uploadFile);
		PictureResult result = new PictureResult(0, IMAGE_BASE_URL+path);
		log.debug("回显地址----------"+IMAGE_BASE_URL+path);
		return result;
	}
	
	private String savePicture(MultipartFile uploadFile) {
		String result = null;
		try {
			if (uploadFile.isEmpty()) {
				return null;
			}
			// 创建日期路径
			String filePath = dateStr();
			//获取上传名
			String fileName = uploadFile.getOriginalFilename();
			//获取新的文件名
			String newFileName = IDUtils.genImageName()+
					fileName.substring(fileName.lastIndexOf("."));
			FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, 
					FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD, 
					FILI_UPLOAD_PATH, filePath, newFileName, uploadFile.getInputStream());
			result = filePath+"/"+newFileName;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * 2019/1/4
	 * @return
	 */
	private  String dateStr() {
		Date  now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		// 获取年
		int year = cal.get(Calendar.YEAR);
		// 获取月
		int month = cal.get(Calendar.MONTH)+1;
		//获取天
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return year+"/"+month+"/"+day;
	}

}

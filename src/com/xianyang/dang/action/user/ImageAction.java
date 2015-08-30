package com.xianyang.dang.action.user;


import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Map;

import com.xianyang.dang.action.BaseAction;
import com.xianyang.dang.util.ImageUtil;


public class ImageAction extends BaseAction{
	//input  无
	//output
	private InputStream imageStream;
	
	public String execute(){
		//动态生成一个图片,输出
		Map<String,BufferedImage> map=ImageUtil.createImage();
		String key=map.keySet().iterator().next();
		session.put("code",key);
		BufferedImage image=map.get(key);
		try {
			//将图片信息给imageStream
			imageStream=ImageUtil.change(image);
			return "success";//调用stream的Result将imageStream输出
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		} 
	}
	
	public InputStream getImageStream() {
		return imageStream;
	}
	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	public static void main(String[] args) {
		System.out.println(123);
	}
}

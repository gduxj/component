package com.hry.java.tess4j.v4;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 中文识别
 * @author Administrator
 *
 */
public class TesseractChineseSelfTest {
	public static void main(String[] args) {
		// user.dir = D:\eclipse_study\simpletool
	//	File root = new File("C:\\Users\\Administrator\\Desktop\\tmp\\other\\ocr");
		File root = new File("C:\\Users\\hry\\Desktop\\tmp\\other\\ocr");
		System.out.println(root.getAbsolutePath());
		Tesseract tessreact = new Tesseract();
		
		/**
		 * 设置语言库: 
		 * 下载地址：https://github.com/tesseract-ocr/tessdata
		 * 
		 */
        tessreact.setDatapath(System.getProperty("user.dir") + "/tess4j/src/main/resources/com/hry/java/tess4j/v4/tessdata/");
        // 设置识别语言，默认为英文
        tessreact.setLanguage("chi_sim+eng");

    	boolean opt = false;

		try {
			List<String> arrayList = new ArrayList<>();
			File[] files = root.listFiles();
			for (File file : files) {
				System.out.println("=" + file.toString());
				String result = null;
				if(opt){
					BufferedImage bufferedImage = TesseractUtil.optimizeImage(file.getPath());
					result = tessreact.doOCR(bufferedImage);
				}else {
					result = tessreact.doOCR(file);
				}


				String fileName = file.toString().substring(
						file.toString().lastIndexOf("\\") + 1);
				result = result.replaceAll(" ","");
						// .replaceAll("\\r","")
				//	.replaceAll("\\n","");
		//		System.out.println("图片名：" + fileName + " 识别结果：" );
		//		System.out.println();
				arrayList.add(result);
				arrayList.add("\\n");
			}

			File saveFile = new File("C:\\Users\\hry\\Desktop\\tmp\\today\\ocr.txt");
			FileUtils.writeLines(saveFile, arrayList);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

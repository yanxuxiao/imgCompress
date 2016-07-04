package com.kingshine.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CompressPicUtils {
	// private static final Logger logger =
	// LoggerFactory.getLogger(CompressPicUtils.class);
	private static File inputFile; // 文件对象
	private static File outputFile; // 输出图路径
	private static int outputWidth; // 默认输出图片宽
	private static int outputHeight; // 默认输出图片高
	private static boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)

	public CompressPicUtils() {
	}

	public static boolean compressPic() {
		try {
			// 获得源文件
			if (!inputFile.exists()) {
				return false;
			}
			Image img = ImageIO.read(inputFile);
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1) {
				return false;
			} else {
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放
				Double rate = 0.0;
				if (proportion == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null))
							/ (double) outputWidth;
					double rate2 = ((double) img.getHeight(null))
							/ (double) outputHeight;
					// 根据缩放比率大的进行缩放控制
					rate = rate1 < rate2 ? rate1 : rate2;
					newWidth = (int) (img.getWidth(null) / rate);
					newHeight = (int) (img.getHeight(null) / rate);
				} else {
					newWidth = outputWidth; // 输出的图片宽度
					newHeight = outputHeight; // 输出的图片高度
				}
				BufferedImage tag = new BufferedImage(newWidth, newHeight,
						BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(
						img.getScaledInstance(newWidth, newHeight,
								Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out = new FileOutputStream(outputFile);
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);  
	            /* 压缩质量 */  
	            jep.setQuality(Float.parseFloat(rate.toString()), true); 
				encoder.encode(tag);
				out.close();
			}
		} catch (IOException ex) {
			// logger.error("compressPic error", ex);
			return false;
		}
		return true;
	}

	public static boolean compressPic(File inputFile1, File outputFile1, int width,
			int height, boolean gp) {
		// 输入图路径
		inputFile = inputFile1;
		// 输出图路径
		outputFile = outputFile1;
		// 设置图片长宽
		outputWidth = width;
		outputHeight = height;
		// 是否是等比缩放 标记
		proportion = gp;
		return compressPic();
	}
}
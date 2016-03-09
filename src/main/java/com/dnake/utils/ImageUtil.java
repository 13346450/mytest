package com.dnake.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.dnake.domain.common.MobileParms;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
/**
 * 图片处理工具类参考网址：
 * http://www.2cto.com/kf/201312/268554.html
 * http://www.oschina.net/question/76860_25758
*  ImageUtil <br/> 
*  2015年3月24日 上午11:13:19 <br/> 
* @author chen qige 
* @version
 */
public class ImageUtil {

	/**
	 * 根据比例，指定大小； 若图片横比width小，高比height小，不变;
	 * 若图片横比width小，高比height大，高缩小到height，图片比例不变;
	 * 若图片横比width大，高比height小，横缩小到width，图片比例不变 ;
	 * 若图片横比width大，高比height大，图片按比例缩小，横为width或高为height
	 * 
	 * @title compressFixWidthAndHeight
	 * @author chen qige
	 * @date 2014年10月8日
	 * @param imageSrcPath
	 * @param imageDstPath
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public static void compressWithWidthAndHeight(String imageSrcPath,
			String imageDstPath, int width, int height) throws IOException {
		Thumbnails.of(imageSrcPath).size(width, height).toFile(imageDstPath);
	}

	/**
	 * 按指定比例缩放
	 * 
	 * @title compressWithScale
	 * @author chen qige
	 * @date 2014年10月8日
	 * @param imageSrcPath
	 * @param imageDstPath
	 * @param scale
	 * @throws IOException
	 */
	public static void compressWithScale(String imageSrcPath, String imageDstPath,
			double scale) throws IOException {
		Thumbnails.of(imageSrcPath).scale(scale).toFile(imageDstPath);
	}

	/**
	 * 按指定大小，不安比例缩放
	 * 
	 * @title compressWithFixWidthAndHeight
	 * @author chen qige
	 * @date 2014年10月8日
	 * @param imageSrcPath
	 * @param imageDstPath
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public  static void compressWithFixWidthAndHeight(String imageSrcPath,
			String imageDstPath, int width, int height) throws IOException {
		Thumbnails.of(imageSrcPath).size(width, height).keepAspectRatio(false)
				.toFile(imageDstPath);
	}

	/**
	 * 根据坐标截取指定区域，可以再进行缩放大小
	 * 
	 * @title cutByCoordinate
	 * @author chen qige
	 * @date 2014年10月8日
	 * @param imageSrcPath
	 * @param imageDstPath
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public static void cutByCoordinate(String imageSrcPath, String imageDstPath,
			int x1, int y1, int x2, int y2, int width, int height)
			throws IOException {
		Thumbnails.of(imageSrcPath).sourceRegion(x1, y1, x2, y2)
				.size(width, height).keepAspectRatio(false)
				.toFile(imageDstPath);
	}

	/**
	 * 根据相对位置截取图片，可缩放
	 * 
	 * @title cutByRelativePosition
	 * @author chen qige
	 * @date 2014年10月8日
	 * @param imageSrcPath
	 * @param imageDstPath
	 * @param position
	 *            Position为Thumbnails自带的类Positions的常量： 如Positions.BOTTOM_CENTER
	 * @param regionWidth
	 * @param regoinHeight
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public static void cutByRelativePosition(String imageSrcPath, String imageDstPath,
			Position position, int regionWidth, int regoinHeight, int width,
			int height) throws IOException {
		Thumbnails.of(imageSrcPath)
				.sourceRegion(position, regionWidth, regoinHeight)
				.size(width, height).keepAspectRatio(false)
				.toFile(imageDstPath);

	}
	/**
	 * 压缩至指定图片尺寸（例如：横400高300），保持图片不变形，多余部分裁剪掉
	* @title      compressImageToFixSizeNoDeformation 
	* @author  chen qige     
	* @date      2014年10月9日 
	*  @throws IOException
	 */
	public static void compressImageToFixSizeNoDeformation() throws IOException{
		String imagePath = "F:\\image\\IMG_20131229_114806.jpg";
		BufferedImage image = ImageIO.read(new File(imagePath));
		Builder<BufferedImage> builder = null;

		int imageWidth = image.getWidth();
		int imageHeitht = image.getHeight();
		if ((float)300 / 400 != (float)imageWidth / imageHeitht) {
			if (imageWidth > imageHeitht) {
				image = Thumbnails.of(imagePath).height(300).asBufferedImage();
			} else {
				image = Thumbnails.of(imagePath).width(400).asBufferedImage();
			}
			builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, 400, 300).size(400, 300);
		} else {
			builder = Thumbnails.of(image).size(400, 300);
		}
		builder.outputFormat("jpg").toFile("F:\\image\\output\\IMG_20131229_114806");
	}
	/**
	 * 改变图片格式
	* @title      changeImageFormat 
	* @author  chen qige     
	* @date      2014年10月9日 
	*  @param imageSrcPath
	*  @param imageDstPath
	*  @param dstFormat
	*  @throws IOException
	 */
	public static void changeImageFormat(String imageSrcPath, String imageDstPath,String dstFormat) throws IOException{
		Thumbnails.of(imageSrcPath)
		.outputFormat(dstFormat)
		.toFile(imageDstPath);
	}
	/**
	 * 添加水印
	* @title      addWatermark 
	* @author  chen qige     
	* @date      2014年10月9日 
	*  @param imageSrcPath
	*  @param imageDstPath
	*  @param watermarkPath
	*  @param position
	*  @param watermarkScale
	*  @throws IOException
	 */
	public static void addWatermark(String imageSrcPath, String imageDstPath,String watermarkPath,Position position,Long watermarkScale) throws IOException{
		Thumbnails.of(imageSrcPath)
		.watermark(position,ImageIO.read(new File("images/watermark.png")),watermarkScale)
		.toFile(imageDstPath);
	}
	public static List<MultipartFile> getMobileImageFiles(MobileParms parms)
	{
		List<MultipartFile> listFile = new ArrayList<MultipartFile>();
		if(parms.getFileImage1() != null)
		{
			if(!parms.getFileImage1().isEmpty())
			{
				listFile.add(parms.getFileImage1());
			}
		}
		if(parms.getFileImage2() != null)
		{
			if(!parms.getFileImage2().isEmpty())
			{
				listFile.add(parms.getFileImage2());
			}
		}
		if(parms.getFileImage3() != null)
		{
			if(!parms.getFileImage3().isEmpty())
			{
				listFile.add(parms.getFileImage3());
			}
		}
		if(parms.getFileImage4() != null)
		{
			if(!parms.getFileImage4().isEmpty())
			{
				listFile.add(parms.getFileImage4());
			}
		}
		if(parms.getFileImage5() != null)
		{
			if(!parms.getFileImage5().isEmpty())
			{
				listFile.add(parms.getFileImage5());
			}
		}
		return listFile;
	}
}

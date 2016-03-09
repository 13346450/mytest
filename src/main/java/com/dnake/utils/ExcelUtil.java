package com.dnake.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *说明：读写excel操作
 *@创建：作者:ts		创建时间：2009-6-29
 *@修改历史：
 *		[序号](ts	2009-6-29)<修改说明>
 */
public final class ExcelUtil {
	
	private static Logger log = Logger.getLogger(ExcelUtil.class.getName());

	/**
	 *摘要：读已经存在的excel
	 *@说明：
	 *@创建：作者:ts		创建时间：2009-6-29
	 *@param file 
	 *@param sheetIndex sheet序号
	 *@return
	 *@throws IOException 
	 *@修改历史：
	 *		[序号](ts	2009-6-29)<修改说明>
	 */
	public static boolean readExcel(File file,int sheetIndex) throws IOException{
		//读excel
		java.lang.StringBuilder rows = new java.lang.StringBuilder("");
		java.lang.StringBuilder cells = new java.lang.StringBuilder("");
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		for(int rowno=0;rowno<=sheet.getLastRowNum();rowno++){
			HSSFRow rowData = sheet.getRow(rowno);
			if(null!=rowData){			
				for(short cellno=0;cellno<rowData.getLastCellNum();cellno++){
					HSSFCell cell = rowData.getCell(cellno);
					if(null!=cell){
						switch (cell.getCellType()){
						case HSSFCell.CELL_TYPE_FORMULA://公式2
							cells.append( String.valueOf(cell.getNumericCellValue()) );
							break;
						case HSSFCell.CELL_TYPE_STRING://字符1
							cells.append( cell.getStringCellValue());
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN://4
							cells.append( String.valueOf(cell.getBooleanCellValue()) );
							break;
						case HSSFCell.CELL_TYPE_NUMERIC://0
							cells.append( String.valueOf(cell.getNumericCellValue()) );
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							cells.append(" ");
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							cells.append("error data");
							break;
						default:
							cells.append("error source");
						}
						cells.append("&nbsp;&nbsp;");
					}
				}
				
				rows.append(cells+"<br>");
				cells = new java.lang.StringBuilder("");				
			}
		}
		
		//log.info(rows);
		return true;
	}
	
	/**
	 *摘要：从文件输入流读取excel
	 *@说明：Excel文件格式要求为：
	 *			逻辑卡号、用户名、充值金额
	 *			sheet No 为第一页
	 *@param file 
	 *@param sheetIndex sheet序号
	 *@return
	 *@throws IOException 
	 *@修改历史：
	 *		[序号](ts	2009-6-29)<修改说明>
	 */
	public static List<Object[]> readExcel(FileInputStream fis,int sheetIndex) throws IOException{
		//读excel
		List<Object[]> datas = new ArrayList<Object[]>();
		Object[] o;
		DecimalFormat  df = new DecimalFormat(); 
		
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		for(int rowno=1;rowno<=sheet.getLastRowNum();rowno++){
			HSSFRow rowData = sheet.getRow(rowno);
			if(null!=rowData){	
				o = new Object[rowData.getLastCellNum()];
				for(short cellno=0;cellno<rowData.getLastCellNum();cellno++){
					HSSFCell cell = rowData.getCell(cellno);
					if(null!=cell){
						switch (cell.getCellType()){
						case HSSFCell.CELL_TYPE_STRING://字符1
							o[cellno]=cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC://0
							o[cellno]=df.format(cell.getNumericCellValue());
							break;
						default:
							o[cellno]="";
						}

					}
				}					
				if(null!=o[0])
					datas.add(o);
			}
		}
		return datas;
	}
	/**
	 * 
	 *摘要：
	 *@说明：读数据从0行开始的
	 *@创建：作者:詹保山(zbs),softzbs@126.com	 创建时间：Dec 4, 2009
	 *@param fis
	 *@param sheetIndex
	 *@return
	 *@throws IOException 
	 *@修改历史：
	 *		[序号](zbs	Dec 4, 2009)<修改说明>
	 */
	public static List<Object[]> readExcelBy0(InputStream fis,int sheetIndex) throws IOException{
		//读excel
		List<Object[]> datas = new ArrayList<Object[]>();
		Object[] o;
		DecimalFormat df = new DecimalFormat("0"); 
		DecimalFormat datef = new DecimalFormat("yyyy-MM-dd"); 
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		for(int rowno=0;rowno<=sheet.getLastRowNum();rowno++){
			HSSFRow rowData = sheet.getRow(rowno);
			if(null!=rowData){	
				o = new Object[rowData.getLastCellNum()];
				for(short cellno=0;cellno<rowData.getLastCellNum();cellno++){
					HSSFCell cell = rowData.getCell(cellno);
					if(null!=cell){
						/*switch (cell.getCellType()){
						case HSSFCell.CELL_TYPE_STRING://字符1
							o[cellno]=cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC://0
							o[cellno]=df.format(cell.getNumericCellValue());
							break;
						}*/
						if(cell.getCellType()== HSSFCell.CELL_TYPE_STRING)//字符1
						{
							o[cellno]=cell.getStringCellValue();
						}
						else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC)//0
						{
							if(HSSFDateUtil.isCellDateFormatted(cell))
							{
								//o[cellno]=HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
								Date d = cell.getDateCellValue();
								DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
								o[cellno]=formater.format(d);
							}else{
								//2014-1-8xql修改：浮点数被format为0了,且整型数会被补上.0，因此调用方法将0去掉
								//o[cellno]=df.format(cell.getNumericCellValue());//原代码
								o[cellno]=StringUtils.removePointZero(cell.getNumericCellValue());
							}
						}
					}
				}					
				if(null!=o[0])
					datas.add(o);
			}
		}
		return datas;
	}

	/**
	 *摘要：创建新的excel
	 *@说明：
	 *@创建：作者:ts		创建时间：2009-6-29
	 *@param request
	 *@param file
	 *@return
	 *@throws IOException 
	 *@修改历史：
	 *		[序号](ts	2009-6-29)<修改说明>
	 */
	public static boolean writeExcel() throws IOException{
		//建一个新的Excel文件
		java.util.Date date = new java.util.Date();		
		String outputFile = "E:\\tmp\\表格"+DateTimeUtil.formatDateToStr(date, "yyyymmdd")+".xls";
		//if(!objFile.exists()){ 
			HSSFWorkbook wbk = new HSSFWorkbook();
			//格式
			HSSFFont hf = wbk.createFont();
			hf.setColor(HSSFFont.COLOR_RED);
			hf.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			hf.setUnderline(HSSFFont.U_SINGLE_ACCOUNTING);
			HSSFCellStyle hcs = wbk.createCellStyle();
			hcs.setFont(hf);
			
			HSSFSheet sh = wbk.createSheet();
			//xie wbk.setSheetName(0, "效益指标", HSSFCell.ENCODING_UTF_16);
			for(int i=0;i<20;i++){
				HSSFRow r = sh.createRow(i);
				for(short j=0;j<10;j++){
					HSSFCell c = r.createCell(j);
					//c.setCellStyle(hcs);
					c.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					c.setCellValue((i+1)*(j+1));
				}
			}
			
			short cell1 = 0;
			short cell2 = 1;
			HSSFCell hc1 = sh.createRow(20).createCell(cell1);
			HSSFCell hc2 = sh.createRow(20).createCell(cell2);
			// xie hc1.setEncoding(HSSFCell.ENCODING_UTF_16);
			hc1.setCellType(HSSFCell.CELL_TYPE_STRING);
			hc1.setCellValue("合计总计");
			hc1.setCellStyle(hcs);			
			hc2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			hc2.setCellFormula("SUM(B1:B20)");
			//hc2.setCellStyle(hcs);
			
			//合并单元格,开始行/列,结束行/列
			//sh.addMergedRegion(new Region(1,(short)5,5,(short)8));
			FileOutputStream fOut = new FileOutputStream(outputFile);
			wbk.write(fOut);
			fOut.flush();
			fOut.close();
		//}
	
		//log.info("Write Excel completed!");
		return true;
	}
	

	/**
	 *摘要：写已经存在的excel
	 *@说明：
	 *@创建：作者:ts		创建时间：2009-6-29
	 *@param request
	 *@param file
	 *@return
	 *@throws IOException 
	 *@修改历史：
	 *		[序号](ts	2009-6-29)<修改说明>
	 */
	public static boolean writeExcel(File file,int sheetIndex) throws IOException{
	
		//写入已经存在的excel
		//创建单元格
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		HSSFRow row = sheet.createRow(5);
		short cellno = 3;
		HSSFCell cell = row.createCell(cellno);
		//xie cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("修改已存在的sheet");
		workbook.createSheet("修改的sheet表");
		FileOutputStream fs = new FileOutputStream(file);
		//BufferedOutputStream bos = new BufferedOutputStream(fs);
		workbook.write(fs);
		fs.flush();
		fs.close();
		
		//log.info("Write Excel completed!");
		return true;
	}
	
	
	public static void main(String[] s){
		//写excel
//		try{
//			writeExcel();
//		}catch(IOException e){
//			e.printStackTrace();
//		}
		
		//写excel
//		File file1 = new File("E:\\tmp\\表格20090630.xls");
//		try{
//			writeExcel(file1,0);
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//		
//		//读excel
		File file = new File("c:\\a.xls");
		try{
			FileInputStream fis=new FileInputStream(file);
			List<Object[]> list=readExcelBy0(fis,0);
			System.out.println(list.size());
			for(Object[] item:list)
			{
				System.out.println(item[3]+"...."+item[5]);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}


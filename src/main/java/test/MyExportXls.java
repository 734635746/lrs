package test;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class MyExportXls {

	public static HSSFWorkbook exportXls(List<TmpFuneralStatistics> tfs){
		
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("堂数登记表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setFillBackgroundColor(HSSFColor.BLUE.index);
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        String[] lunarDay = {"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三",
			    "十四","十五","十六", "十七", "十八","十九","廿十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","卅十"};
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("法名");  
        cell.setCellStyle(style); 
        
        
        for(int i = 0;i < 30;i ++){
        	 cell = row.createCell((short) (i+1));  
             cell.setCellValue(lunarDay[i]);  
             cell.setCellStyle(style); 
        }
        int k = 1;
        for(int i = 0;i < (tfs.size()/30);i++){
        	k = 1;
        	row = sheet.createRow((int) (i+1));  
        	row.createCell((short) 0).setCellValue(tfs.get(i*30).getName()); 
				for(int j = (i*30); j < ((i*30) + 30);j ++){
					row.createCell((short) k).setCellValue(tfs.get(j).getCount()); 
					k = k + 1;
			}
		}
        return wb;
	}
	
	
	public static HSSFWorkbook exportTotalXls(List<FuneralGatherCensus> fcgs){
		
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("法事堂数登记表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setFillBackgroundColor(HSSFColor.BLUE.index);
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("法名");  
        cell.setCellStyle(style); 
        
        cell = row.createCell((short) (1));  
        cell.setCellValue("总堂数");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) (2));  
        cell.setCellValue("缺堂数");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) (3));  
        cell.setCellValue("参堂数");  
        cell.setCellStyle(style); 
        	 
        for(int i = 0;i < fcgs.size();i++){
        	row = sheet.createRow((int) (i+1));  
        	row.createCell((short) 0).setCellValue(fcgs.get(i).getMemberName()); 
        	row.createCell((short) 1).setCellValue(fcgs.get(i).getAll()); 
        	row.createCell((short) 2).setCellValue(fcgs.get(i).getAbsent()); 
        	row.createCell((short) 3).setCellValue(fcgs.get(i).getJoin()); 
		}
        return wb;
	}
}

package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.opensagres.xdocreport.core.utils.StringUtils;
public class test {  
	   
    protected static final Logger logger = LoggerFactory.getLogger(test.class);  
       
    public static void main(String[] args) throws Exception{  
        String filepath = "G://jeecg myeclipse//jeecg-framework-3.4.3GA//jeecg//WebRoot//resources//wenshu//s1.docx";  
        String outpath = "G://jeecg myeclipse//jeecg-framework-3.4.3GA//jeecg//WebRoot//resources//wenshu//s1.pdf";   
           
        InputStream source = new FileInputStream(filepath);  
        OutputStream target = new FileOutputStream(outpath);  
        Map<String, String> params = new HashMap<String, String>();  
           
           
        PdfOptions options = PdfOptions.create();  
           
        wordConverterToPdf(source, target, options, params);  
    }  
   
    /** 
     * 将word文档， 转换成pdf, 中间替换掉变量 
     * @param source 源为word文档， 必须为docx文档 
     * @param target 目标输出 
     * @param params 需要替换的变量 
     * @throws Exception 
     */  
    public static void wordConverterToPdf(InputStream source,  
            OutputStream target, Map<String, String> params) throws Exception {  
        wordConverterToPdf(source, target, null, params);      
    }  
   
    /** 
     * 将word文档， 转换成pdf, 中间替换掉变量 
     * @param source 源为word文档， 必须为docx文档 
     * @param target 目标输出 
     * @param params 需要替换的变量 
     * @param options PdfOptions.create().fontEncoding( "windows-1250" ) 或者其他 
     * @throws Exception 
     */  
    public static void wordConverterToPdf(InputStream source, OutputStream target,   
            PdfOptions options,  
            Map<String, String> params) throws Exception {  
         XWPFDocument doc = new XWPFDocument(source);  
         paragraphReplace(doc.getParagraphs(), params);  
         for (XWPFTable table : doc.getTables()) {  
           for (XWPFTableRow row : table.getRows()) {  
               for (XWPFTableCell cell : row.getTableCells()) {  
                   paragraphReplace(cell.getParagraphs(), params);  
               }  
           }  
       }  
       PdfConverter.getInstance().convert(doc, target, options);  
    }  
       
    /** 替换段落中内容 */  
    private static void paragraphReplace(List<XWPFParagraph> paragraphs, Map<String, String> params) {  
        if (MapUtils.isNotEmpty(params)) {  
            for (XWPFParagraph p : paragraphs){  
                for (XWPFRun r : p.getRuns()){  
                    String content = r.getText(r.getTextPosition());  
                    logger.info(content);  
                    if(StringUtils.isNotEmpty(content) && params.containsKey(content)) {  
                        r.setText(params.get(content), 0);  
                    }  
                }  
            }  
        }  
    }  
       
}  
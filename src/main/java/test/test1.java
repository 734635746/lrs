package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
  
   
  
public class test1{  
  
   private Configuration configuration = null;  
  
   
  
   public test1() {  
  
      configuration = new Configuration();  
  
      configuration.setDefaultEncoding("utf-8");  
  
   }  
  
   
  
   public void createDoc() throws IOException {  
  
      // 要填入模本的数据文件   
  
      Map dataMap = new HashMap();  
  
      getData(dataMap);  
  
      // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，   
  
      // 这里我们的模板是放在com.havenliu.document.template包下面   
  
      configuration.setDirectoryForTemplateLoading(new File("G://jeecg myeclipse//jeecg-framework-3.4.3GA//jeecg//WebRoot//resources//wenshu"));  
 
  
      Template t = null;  
  
      try {  
  
         // test.ftl为要装载的模板   
  
         t = configuration.getTemplate("wenshuRealeaseModel.ftl");  
  
         t.setEncoding("utf-8");  
  
      } catch (IOException e) {  
  
         e.printStackTrace();  
  
      }  
  
      // 输出文档路径及名称   
  
      
      File outFile = new File("G://jeecg myeclipse//jeecg-framework-3.4.3GA//jeecg//WebRoot//resources//wenshu//realese.doc");  
  
      Writer out = null;  
  
      try {  
  
         out = new BufferedWriter(new OutputStreamWriter(  
  
                new FileOutputStream(outFile), "utf-8"));  
  
   
  
      } catch (Exception e1) {  
  
         e1.printStackTrace();  
  
      }  
  
      try {  
  
         t.process(dataMap, out);  
  
         out.close();  
  
      } catch (TemplateException e) {  
  
         e.printStackTrace();  
  
      } catch (IOException e) {  
  
         e.printStackTrace();  
  
      }  
  
   }  
  
   
  
   /** 
 
    * 注意dataMap里存放的数据Key值要与模板中的参数相对应 
 
    *  
 
    * @param dataMap 
 
    */  
  
   private void getData(Map dataMap) {  
  
      dataMap.put("address", "广东省外环西路100号广东工业大学"); 
      dataMap.put("name1", "张三"); 
      dataMap.put("name2", "张三");
      dataMap.put("name3", "张三");
      dataMap.put("name4", "张三");
      dataMap.put("name5", "张三");
      dataMap.put("name6", "张三");
      dataMap.put("name7", "张三");
      dataMap.put("name8", "张三");
      dataMap.put("lunardate2", "十月初五至十月初七");
      dataMap.put("book", "地藏菩萨额你拖");
      dataMap.put("lunardate1", "十月初五至十月初七");
      dataMap.put("prayingobj1", "李四");
      dataMap.put("prayingobj2", "李五");
      dataMap.put("prayingobj3", "李六");
      dataMap.put("register", "管理员");
      dataMap.put("na1", "");
      dataMap.put("wenzi1", "十方法界冤親債主");
      dataMap.put("wenzi2", "有祀無祀孤魂等眾");
      dataMap.put("wenzi3", "歷劫父母師長眷屬");
      /*
      dataMap.put("lunardate", "十月初五");
      dataMap.put("book", "观音菩萨额你拖佛");*/
  
      
  
   }  
   
   public void printWordFile(String filename) throws IOException{
	   ComThread.InitSTA();
	   ActiveXComponent wd = new ActiveXComponent("Word.Application");
	   //Runtime.getRuntime().exec("cmd.exe /c start /min WINWORD.EXE " +filename+" /q /n /f /mFilePrint/mFileExit");
		try {
			// 获得打印属性
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			pras.add(new Copies(1));
			// 获得打印设备
			PrintService pss[] = PrintServiceLookup.lookupPrintServices(
					DocFlavor.INPUT_STREAM.GIF, pras);
			if (pss.length == 0)
				throw new RuntimeException("No printer services available.");
			PrintService ps = pss[0];
			System.out.println("Printing to " + ps);
			// 获得打印工作
			DocPrintJob job = ps.createPrintJob();
			FileInputStream fin = new FileInputStream(filename);
			Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.GIF, null);
			// 开始打印
			job.print(doc, pras);
			fin.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (PrintException pe) {
			pe.printStackTrace();
		}
   }
   public void testZip() throws Exception {  
	   
	   String sourceDirStr = "G:/tomcat6.0/apache-tomcat-6.0.44-windows-x64/apache-tomcat-6.0.44/webapps/lrs/webpage/c_rea_wenshu";  
	   String zipPath = "G:/tomcat6.0/apache-tomcat-6.0.44-windows-x64/apache-tomcat-6.0.44/webapps/lrs/webpage/c_rea_wenshu/dest.zip";
       ZipOutputStream zipOut = new ZipOutputStream(  
           new BufferedOutputStream(  
               new FileOutputStream(zipPath), 1024));  
 
       File sourceDirectory = new File(sourceDirStr);  
       File[] files = sourceDirectory.listFiles();  
 
       byte[] bs = new byte[1024];    // 缓冲数组  
       int value = -1;   //文件是否结束标记  
         
       for (File file : files) { // 遍历所有的文件  
           zipOut.putNextEntry(new ZipEntry(file.getName()));   // 存入文件名称，便于解压缩  
           BufferedInputStream bfInput = new BufferedInputStream(  
                   new FileInputStream(file), 1024);  
           while ((value = bfInput.read(bs, 0, bs.length)) != -1) {  
               zipOut.write(bs, 0, value);  
           }  
           bfInput.close(); //关闭缓冲输入流  
       }  
       zipOut.flush();  
       zipOut.close();  
   }  
   
   public void deleteFile(){
	   File file = new File("G:/tomcat6.0/apache-tomcat-6.0.44-windows-x64/apache-tomcat-6.0.44/webapps/lrs/webpage/c_gen_wenshu");
       File[] files = file.listFiles();
       System.out.println(files.length);
       for(int i=0; i<files.length; i++){
           File tmp = files[i];
           tmp.delete();
       }
   }
   public static void main(String []args) throws Exception{
	   test1 te = new test1();
	   //te.createDoc();
	   //te.testZip();
	   te.deleteFile();
	  // te.printWordFile("G://jeecg myeclipse//jeecg-framework-3.4.3GA//jeecg//WebRoot//resources//wenshu//s1.doc");
   }
  
}  
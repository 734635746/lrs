package com.sys.util;
import java.text.DecimalFormat;
import java.util.Scanner;
  
public class ChineseCurrency {  
    public static void main(String[] args) {  
        Scanner s = new Scanner(System.in);  
        System.out.println("请输入人民币金额:");  
        double number = 1;  
        while (number > 0.000000001) {  
            number = s.nextDouble();  
            System.out.println(toSmall(new Double(number)));  
        }  
    }  
  
    public static String toChineseCurrency(Number o) {  
        String s = new DecimalFormat("#.00").format(o);  
        s = s.replaceAll("\\.", "");  
        char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };  
        String unit = "仟佰拾兆仟佰拾亿仟佰拾万仟佰拾元角分";  
        int l = unit.length();  
        StringBuffer sb = new StringBuffer(unit);  
        for (int i = s.length() - 1; i >= 0; i--)  
            sb = sb.insert(l - s.length() + i, digit[(s.charAt(i) - 0x30)]);  
        s = sb.substring(l - s.length(), l + s.length());  
        s = s.replaceAll("零[拾佰仟]", "零").replaceAll("零{2,}", "零").replaceAll("零([兆万元])", "$1").replaceAll("零[角分]", "");  
        if (s.endsWith("角"))  
            s += "零分";  
        if (!s.contains("角") && !s.contains("分") && s.contains("元"))  
            s += "整";  
        if (s.contains("分") && !s.contains("整") && !s.contains("角"))  
            s = s.replace("元", "元零");  
        return s;  
    }  
    public static String toSmall(Number o){
    	String s = new DecimalFormat("#.00").format(o); 
    	StringBuilder sb=new StringBuilder(); 
    	int j=1;
    	for(int i = s.length() - 4; i >= 0; i--,j++)  {
    		if(j%3==1&&j>1){
    			sb.append(",");
    		}
    		sb.append(s.charAt(i));
    	}
    	
    	return sb.reverse().toString()+s.substring(s.length()-3);
    }
} 

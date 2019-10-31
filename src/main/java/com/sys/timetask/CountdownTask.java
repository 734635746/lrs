package com.sys.timetask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sys.entity.continuouslight.ContinuouslightEntity;
import com.sys.entity.diamondsutra.DiamondsutraEntity;
import com.sys.service.continuouslight.ContinuouslightServiceI;
import com.sys.service.diamondsutra.DiamondsutraServiceI;

public class CountdownTask {
	
	@Autowired
	private DiamondsutraServiceI diamondsutraService;
	
	@Autowired
	private ContinuouslightServiceI continuouslightService;

    public void execute() {
        System.out.println(" Quartz! - executing its JOB at "+ 
            new Date() + " by ");
        /*DiamondsutraController controller = new DiamondsutraController();
        try {
			controller.updateRestDays();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        System.out.println("updateRestDays");
		/*List<DiamondsutraEntity> diamondsutraList = diamondsutraService.getList(DiamondsutraEntity.class);
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        try {
			cal.setTime(sdf.parse(dateString));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        long current_timestamp = cal.getTimeInMillis();                 
                
		for(DiamondsutraEntity dse : diamondsutraList){
			String starttime = dse.getStarttime();
			
			try {
				cal.setTime(sdf.parse(starttime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	        long start_timestamp = cal.getTimeInMillis();         
	        int between_days=(int) ((current_timestamp-start_timestamp)/(1000*3600*24));  
	        
	        if((dse.getDays() - between_days) >= 0){
	        	dse.setDays(dse.getDays() - between_days);
		        if(dse.getDays() == 0){
		        	dse.setDeadlineflag(1);
		        }
		        diamondsutraService.updateEntitie(dse);
	        }

		}
		System.out.println("更新金刚经日期完成。。。。。。");
		
		
		List<ContinuouslightEntity> continuouslightList = continuouslightService.getList(ContinuouslightEntity.class);
		for(ContinuouslightEntity con : continuouslightList){
			String starttime = con.getStarttime();
			
			try {
				cal.setTime(sdf.parse(starttime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	        long start_timestamp = cal.getTimeInMillis();         
	        int between_days=(int) ((current_timestamp-start_timestamp)/(1000*3600*24));  
	        
	        if(between_days > 0){
	        	if((con.getDays() - between_days) >= 0){
	        		con.setDays(con.getDays() - between_days);
		        	if(con.getDays() == 0){
		        		con.setDeadlineflag(1);
		        	}
		        	continuouslightService.updateEntitie(con);
	        	}
	        	
	        }

		}
		System.out.println("更新长明灯日期完成。。。。。。");*/
		
    }
    
}

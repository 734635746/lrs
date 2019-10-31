package com.sys.timetask;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import com.sys.controller.diamondsutra.DiamondsutraController;

public class QuartzScheduling {
	public static void main(String[] args) throws ParseException, SchedulerException {

		/*SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	    Scheduler scheduler = schedulerFactory.getScheduler();
	
	    JobDetail jobDetail = new JobDetail("QuartzJob", 
	            Scheduler.DEFAULT_GROUP, CountdownTask.class);
	
	    
	    String cronExpression = "0 20 23 * * ?"; // 每天0点计算 
        CronTrigger cronTrigger = new CronTrigger("cronTrigger", 
                Scheduler.DEFAULT_GROUP, cronExpression);

        scheduler.scheduleJob(jobDetail, cronTrigger);
	
	
	    scheduler.start();*/
		 DiamondsutraController controller = new DiamondsutraController();
		controller.updateRestDays();
	}
}

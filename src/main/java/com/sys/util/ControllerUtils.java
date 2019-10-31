package com.sys.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.pray.PrayEntity;
import com.sys.entity.releasesouls.ReleaseSoulsEntity;

/**
* @ClassName: ControllerUtils
* @Description: controller层的utils
* @author kooking
* @date 2018-4-7 下午8:48:53
*/ 
public class ControllerUtils {

	
	/**
	* @Title: getThisYearFuneralheldsByRitualtype
	* @Description: 通过法会的类型获取今年法事的举行日期安排
	* @param service 查询需要调用的service接口
	* @param ritualtype 法会类型
	* @return   查询到的法会举行日期安排
	* @return List<FuneralheldEntity>    返回类型
	* @throws
	*/ 
	public static List<FuneralheldEntity> getThisYearFuneralheldsByRitualtype(
			CommonService service, String ritualtype) {
		
		//获取当前时间的年份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String year = sdf.format(date);
        
        //hql查询语句
        String hql_held = "from FuneralheldEntity where ritualtype='"+ritualtype+"' AND holdYear='"+year+"'";
		List<FuneralheldEntity> funeralhelds = service.findByQueryString(hql_held);
		return funeralhelds;

	}
	
	/**
	* @Title: save2Pray
	* @Description: 将祈福类记录保存到pray表
	* @param object 
	* @param service
	* @throws Exception   
	* @return void    返回类型
	* @throws
	*/ 
	public static void save2Pray(Object object,CommonService service) throws Exception{
		String sql = "{call pray_proc(?,?)}";
		
		PrayEntity prayEntity=new PrayEntity();
		MyBeanUtils.copyBean2Bean(prayEntity, object);
		service.executeSQLProc(sql, prayEntity.getDoritualid(),prayEntity.getSize());
		service.save(prayEntity);
	}
	
	
	/**
	* @Title: save2ReleaseSouls
	* @Description: 将超度类记录保存到release_souls表
	* @param object
	* @param service
	* @throws Exception   
	* @return void    返回类型
	* @throws
	*/ 
	public static void save2ReleaseSouls(Object object,CommonService service) throws Exception{
		String sql = "{call release_souls_proc(?,?)}";
		
		ReleaseSoulsEntity releaseSoulsEntity=new ReleaseSoulsEntity();
		MyBeanUtils.copyBean2Bean(releaseSoulsEntity, object);
		service.executeSQLProc(sql, releaseSoulsEntity.getDoritualid(),releaseSoulsEntity.getSize());
		service.save(releaseSoulsEntity);
	}
}

package com.sys.service.doritualinfo;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.livingmenber.LivingmenberEntity;
import com.sys.entity.ancestor.AncestorEntity;

public interface DoritualinfoServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(DoritualinfoEntity doritualinfo,
	        List<LivingmenberEntity> livingmenberList,List<AncestorEntity> ancestorList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(DoritualinfoEntity doritualinfo,
	        List<LivingmenberEntity> livingmenberList,List<AncestorEntity> ancestorList);
	
	
	/**
	* @Title: delMain
	* @Description: 删除做法事人的信息
	* @param doritualinfo   
	* @return void    返回类型
	* @throws
	*/ 
	public void delMain (DoritualinfoEntity doritualinfo);
	
	
	/**
	* @Title: updateAddress
	* @Description: 只更新做法事人的地址信息，其他不更新
	* @param doritualinfo   
	* @return void    返回类型
	* @throws
	*/ 
//	public void updateAddress (DoritualinfoEntity doritualinfo);
}

package com.sys.service.impl.doritualinfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.livingmenber.LivingmenberEntity;
import com.sys.entity.ancestor.AncestorEntity;
@Service("doritualinfoService")
@Transactional
public class DoritualinfoServiceImpl extends CommonServiceImpl implements DoritualinfoServiceI {

	
	public void addMain(DoritualinfoEntity doritualinfo,
	        List<LivingmenberEntity> livingmenberList,List<AncestorEntity> ancestorList){
			//保存主信息
			this.save(doritualinfo);
		
			/**保存-在世的人*/
			for(LivingmenberEntity livingmenber:livingmenberList){
				//外键设置
				livingmenber.setRitualid(doritualinfo.getId());
				this.save(livingmenber);
			}
			/**保存-先人表*/
			for(AncestorEntity ancestor:ancestorList){
				//外键设置
				ancestor.setRitualid(doritualinfo.getId());
				this.save(ancestor);
			}
	}

	
	public void updateMain(DoritualinfoEntity doritualinfo,
	        List<LivingmenberEntity> livingmenberList,List<AncestorEntity> ancestorList) {
		//保存订单主信息
		this.saveOrUpdate(doritualinfo);
		
		
		//===================================================================================
		//获取参数
		Object id0 = doritualinfo.getId();
		Object id1 = doritualinfo.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-在世的人
	    String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ? ";
	    List<LivingmenberEntity> livingmenberOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-在世的人
		for(LivingmenberEntity oldE:livingmenberOldList){
			boolean isUpdate = false;
				for(LivingmenberEntity sendE:livingmenberList){
					//需要更新的明细数据-在世的人
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-在世的人
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-在世的人
		for(LivingmenberEntity livingmenber:livingmenberList){
			if(livingmenber.getId()==null){
				//外键设置
				livingmenber.setRitualid(doritualinfo.getId());
				this.save(livingmenber);
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-先人表
	    String hql1 = "from AncestorEntity where 1 = 1 AND ritualid = ? ";
	    List<AncestorEntity> ancestorOldList = this.findHql(hql1,id1);
		//2.筛选更新明细数据-先人表
		for(AncestorEntity oldE:ancestorOldList){
			boolean isUpdate = false;
				for(AncestorEntity sendE:ancestorList){
					//需要更新的明细数据-先人表
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-先人表
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-先人表
		for(AncestorEntity ancestor:ancestorList){
			if(ancestor.getId()==null){
				//外键设置
				ancestor.setRitualid(doritualinfo.getId());
				this.save(ancestor);
			}
		}
		
	}

	
	public void delMain(DoritualinfoEntity doritualinfo) {
		//删除主表信息
		this.delete(doritualinfo);
		
		//===================================================================================
		//获取参数
		Object id0 = doritualinfo.getId();
		Object id1 = doritualinfo.getId();
		//===================================================================================
		//删除-在世的人
	    String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ? ";
	    List<LivingmenberEntity> livingmenberOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(livingmenberOldList);
		//===================================================================================
		//删除-先人表
	    String hql1 = "from AncestorEntity where 1 = 1 AND ritualid = ? ";
	    List<AncestorEntity> ancestorOldList = this.findHql(hql1,id1);
		this.deleteAllEntitie(ancestorOldList);
	}


	/* (non-Javadoc)
	* Title: updateAddress
	* Description: 只更新做法事人的地址信息
	* @param doritualinfo 做法事人的信息（id，地址）
	* @see com.sys.service.doritualinfo.DoritualinfoServiceI#updateAddress(com.sys.entity.doritualinfo.DoritualinfoEntity)
	*/ 
	/*@Override
	public void updateAddress(DoritualinfoEntity doritualinfo) {
		//因为只修改地址，而调用update方法时，除了主键所有属性都会被修改
		//若不获取其他属性，除地址外的所有属性将变为空
		//根据id过去做法事人的所有信息
		DoritualinfoEntity info = this.get(DoritualinfoEntity.class, doritualinfo.getId());
		
		//修改info的地址
		info.setAddress(doritualinfo.getAddress());
		
		//修改
		this.updateEntitie(info);
		
	}*/
	
}
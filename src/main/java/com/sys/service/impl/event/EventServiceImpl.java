package com.sys.service.impl.event;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.sys.service.event.EventServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.sys.entity.event.EventEntity;
import com.sys.entity.volunteerevent.VolunteereventEntity;
@Service("eventService")
@Transactional
public class EventServiceImpl extends CommonServiceImpl implements EventServiceI {

	
	public void addMain(EventEntity event,
	        List<VolunteereventEntity> volunteereventList){
			//保存主信息
			this.save(event);
		
			/**保存-义工事务登记*/
			for(VolunteereventEntity volunteerevent:volunteereventList){
				//外键设置
				volunteerevent.setId(event.getId());
				//外键设置
				volunteerevent.setEventid(event.getId());
				this.save(volunteerevent);
			}
	}

	
	public void updateMain(EventEntity event,
	        List<VolunteereventEntity> volunteereventList) {
		//保存订单主信息
		this.saveOrUpdate(event);
		
		
		//===================================================================================
		//获取参数
		Object id0 = event.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-义工事务登记
	    String hql0 = "from VolunteereventEntity where 1 = 1 AND eventid = ? ";
	    List<VolunteereventEntity> volunteereventOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-义工事务登记
		for(VolunteereventEntity oldE:volunteereventOldList){
			boolean isUpdate = false;
				for(VolunteereventEntity sendE:volunteereventList){
					//需要更新的明细数据-义工事务登记
					if(oldE.getId().equals(sendE.getId())){
		    			try {
		    				System.out.println("旧的ID" + oldE.getId() + "新的ID" + sendE.getId());
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-义工事务登记
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-义工事务登记
		for(VolunteereventEntity volunteerevent:volunteereventList){
			if(volunteerevent.getId()==null){
				//外键设置
				volunteerevent.setId(event.getId());
				volunteerevent.setEventid(event.getId());
				this.save(volunteerevent);
			}
		}
		
	}

	
	public void delMain(EventEntity event) {
		//删除主表信息
		this.delete(event);
		
		//===================================================================================
		//获取参数
		Object id0 = event.getId();
		//===================================================================================
		//删除-义工事务登记
	    String hql0 = "from VolunteereventEntity where 1 = 1 AND eventid = ? ";
	    List<VolunteereventEntity> volunteereventOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(volunteereventOldList);
	}
	
}
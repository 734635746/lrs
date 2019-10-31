package com.sys.service.impl.volunteer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.sys.service.volunteer.VolunteerServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.sys.entity.volunteer.VolunteerEntity;
import com.sys.entity.volunteerevent.VolunteereventEntity;
@Service("volunteerService")
@Transactional
public class VolunteerServiceImpl extends CommonServiceImpl implements VolunteerServiceI {

	
	public void addMain(VolunteerEntity volunteer,
	        List<VolunteereventEntity> volunteereventList){
			//保存主信息
			this.save(volunteer);
		
			/**保存-义工事务登记*/
			for(VolunteereventEntity volunteerevent:volunteereventList){
				//外键设置
				volunteerevent.setId(volunteer.getId());
				//外键设置
				volunteerevent.setVolunteerid(volunteer.getId());
				this.save(volunteerevent);
			}
	}

	
	public void updateMain(VolunteerEntity volunteer,
	        List<VolunteereventEntity> volunteereventList) {
		//保存订单主信息
		this.saveOrUpdate(volunteer);
		
		
		//===================================================================================
		//获取参数
		Object id0 = volunteer.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-义工事务登记
	    String hql0 = "from VolunteereventEntity where 1 = 1 AND id = ?  AND volunteerid = ? ";
	    List<VolunteereventEntity> volunteereventOldList = this.findHql(hql0,id0,id0);
		//2.筛选更新明细数据-义工事务登记
		for(VolunteereventEntity oldE:volunteereventOldList){
			boolean isUpdate = false;
				for(VolunteereventEntity sendE:volunteereventList){
					//需要更新的明细数据-义工事务登记
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-义工事务登记
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-义工事务登记
		for(VolunteereventEntity volunteerevent:volunteereventList){
			if(volunteerevent.getId()==null){
				//外键设置
				volunteerevent.setId(volunteer.getId());
				volunteerevent.setVolunteerid(volunteer.getId());
				this.save(volunteerevent);
			}
		}
		
	}

	
	public void delMain(VolunteerEntity volunteer) {
		//删除主表信息
		this.delete(volunteer);
		
		//===================================================================================
		//获取参数
		Object id0 = volunteer.getId();
		//===================================================================================
		//删除-义工事务登记
	    String hql0 = "from VolunteereventEntity where 1 = 1 AND id = ?  AND volunteerid = ? ";
	    List<VolunteereventEntity> volunteereventOldList = this.findHql(hql0,id0,id0);
		this.deleteAllEntitie(volunteereventOldList);
	}
	
}
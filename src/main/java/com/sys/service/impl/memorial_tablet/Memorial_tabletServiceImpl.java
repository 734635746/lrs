package com.sys.service.impl.memorial_tablet;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.sys.service.memorial_tablet.Memorial_tabletServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.sys.entity.memorial_tablet.Memorial_tabletEntity;
import com.sys.entity.namelist.NamelistEntity;
import com.sys.entity.linkmanlist.LinkmanlistEntity;
@Service("memorial_tabletService")
@Transactional
public class Memorial_tabletServiceImpl extends CommonServiceImpl implements Memorial_tabletServiceI {

	
	public void addMain(Memorial_tabletEntity memorial_tablet,
	        List<NamelistEntity> namelistList,List<LinkmanlistEntity> linkmanlistList){
			//保存主信息
			this.save(memorial_tablet);
		
			/**保存-姓名列表*/
			for(NamelistEntity namelist:namelistList){
				//外键设置
				namelist.setTabletId(memorial_tablet.getId());
				this.save(namelist);
			}
			/**保存-联系人列表*/
			for(LinkmanlistEntity linkmanlist:linkmanlistList){
				//外键设置
				linkmanlist.setTabletId(memorial_tablet.getId());
				this.save(linkmanlist);
			}
	}

	
	public void updateMain(Memorial_tabletEntity memorial_tablet,
	        List<NamelistEntity> namelistList,List<LinkmanlistEntity> linkmanlistList) {
		//保存订单主信息
		this.saveOrUpdate(memorial_tablet);
		
		
		//===================================================================================
		//获取参数
		Object id0 = memorial_tablet.getId();
		Object id1 = memorial_tablet.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-姓名列表
	    String hql0 = "from NamelistEntity where 1 = 1 AND tabletId = ? ";
	    List<NamelistEntity> namelistOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-姓名列表
		for(NamelistEntity oldE:namelistOldList){
			boolean isUpdate = false;
				for(NamelistEntity sendE:namelistList){
					//需要更新的明细数据-姓名列表
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-姓名列表
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-姓名列表
		for(NamelistEntity namelist:namelistList){
			if(namelist.getId()==null){
				//外键设置
				namelist.setTabletId(memorial_tablet.getId());
				this.save(namelist);
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-联系人列表
	    String hql1 = "from LinkmanlistEntity where 1 = 1 AND tabletId = ? ";
	    List<LinkmanlistEntity> linkmanlistOldList = this.findHql(hql1,id1);
		//2.筛选更新明细数据-联系人列表
		for(LinkmanlistEntity oldE:linkmanlistOldList){
			boolean isUpdate = false;
				for(LinkmanlistEntity sendE:linkmanlistList){
					//需要更新的明细数据-联系人列表
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-联系人列表
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-联系人列表
		for(LinkmanlistEntity linkmanlist:linkmanlistList){
			if(linkmanlist.getId()==null){
				//外键设置
				linkmanlist.setTabletId(memorial_tablet.getId());
				this.save(linkmanlist);
			}
		}
		
	}

	
	public void delMain(Memorial_tabletEntity memorial_tablet) {
		//删除主表信息
		this.delete(memorial_tablet);
		
		//===================================================================================
		//获取参数
		Object id0 = memorial_tablet.getId();
		Object id1 = memorial_tablet.getId();
		//===================================================================================
		//删除-姓名列表
	    String hql0 = "from NamelistEntity where 1 = 1 AND tabletId = ? ";
	    List<NamelistEntity> namelistOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(namelistOldList);
		//===================================================================================
		//删除-联系人列表
	    String hql1 = "from LinkmanlistEntity where 1 = 1 AND tabletId = ? ";
	    List<LinkmanlistEntity> linkmanlistOldList = this.findHql(hql1,id1);
		this.deleteAllEntitie(linkmanlistOldList);
	}
	
}
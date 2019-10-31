package com.sys.service.impl.staff;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import com.sys.service.staff.StaffServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.MyBeanUtils;
import com.sys.entity.staff.StaffEntity;
import com.sys.entity.workexperience.WorkexperienceEntity;
import com.sys.entity.education.EducationEntity;
import com.sys.entity.familymember.FamilymemberEntity;
import com.sys.entity.department.DepartmentEntity;
@Service("staffService")
@Transactional
public class StaffServiceImpl extends CommonServiceImpl implements StaffServiceI {

	
	public void addMain(StaffEntity staff,
	        List<WorkexperienceEntity> workexperienceList,List<EducationEntity> educationList,List<FamilymemberEntity> familymemberList){
			//保存主信息
			this.save(staff);
		
			/**保存-工作经历*/
			for(WorkexperienceEntity workexperience:workexperienceList){
				//外键设置
				workexperience.setId(staff.getId());
				//外键设置
				workexperience.setStaffid(staff.getId());
				this.save(workexperience);
			}
			/**保存-学历*/
			for(EducationEntity education:educationList){
				//外键设置
				education.setId(staff.getId());
				//外键设置
				education.setStaffid(staff.getId());
				this.save(education);
			}
			/**保存-家庭成员*/
			for(FamilymemberEntity familymember:familymemberList){
				//外键设置
				familymember.setId(staff.getId());
				//外键设置
				familymember.setStaffid(staff.getId());
				this.save(familymember);
			}
	}

	
	public void updateMain(StaffEntity staff,
	        List<WorkexperienceEntity> workexperienceList,List<EducationEntity> educationList,List<FamilymemberEntity> familymemberList) {
		//保存订单主信息
		this.saveOrUpdate(staff);
		
		
		//===================================================================================
		//获取参数
		Object id0 = staff.getId();
		Object id1 = staff.getId();
		Object id2 = staff.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-工作经历
	    String hql0 = "from WorkexperienceEntity where 1 = 1 AND staffid = ? ";
	    List<WorkexperienceEntity> workexperienceOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-工作经历
	    System.out.println("传过来的长度   ：  " + workexperienceList.size());
		for(WorkexperienceEntity oldE:workexperienceOldList){
			boolean isUpdate = false;
				for(WorkexperienceEntity sendE:workexperienceList){
					System.out.println("传过来的sendE   ：  " + sendE.getId());
					//需要更新的明细数据-工作经历
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-工作经历
	    			System.out.println("删除    :  " + oldE.getId());
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-工作经历
		for(WorkexperienceEntity workexperience:workexperienceList){
			if(workexperience.getWorkunit() != null){
				if(workexperience.getId()==null){
					//外键设置
					workexperience.setId(staff.getId());
					workexperience.setStaffid(staff.getId());
					this.save(workexperience);
				}
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-学历
	    String hql1 = "from EducationEntity where 1 = 1 AND staffid = ? ";
	    List<EducationEntity> educationOldList = this.findHql(hql1,id1);
		//2.筛选更新明细数据-学历
		for(EducationEntity oldE:educationOldList){
			boolean isUpdate = false;
				for(EducationEntity sendE:educationList){
					//需要更新的明细数据-学历
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-学历
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-学历
		for(EducationEntity education:educationList){
			if(education.getStarttime() != null){
				if(education.getId()==null){
					//外键设置
					education.setId(staff.getId());
					education.setStaffid(staff.getId());
					this.save(education);
				}
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-家庭成员
	    String hql2 = "from FamilymemberEntity where 1 = 1 AND staffid = ? ";
	    List<FamilymemberEntity> familymemberOldList = this.findHql(hql2,id2);
		//2.筛选更新明细数据-家庭成员
		for(FamilymemberEntity oldE:familymemberOldList){
			boolean isUpdate = false;
				for(FamilymemberEntity sendE:familymemberList){
					//需要更新的明细数据-家庭成员
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-家庭成员
		    		super.delete(oldE);
	    		}
	    		
			}
		//3.持久化新增的数据-家庭成员
		for(FamilymemberEntity familymember:familymemberList){
			if(familymember.getRelationship() != null){
				if(familymember.getId()==null){
					//外键设置
					familymember.setId(staff.getId());
					familymember.setStaffid(staff.getId());
					this.save(familymember);
				}
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-部门信息
		
	}

	
	public void delMain(StaffEntity staff) {
		//删除主表信息
		this.delete(staff);
		
		//===================================================================================
		//获取参数
		Object id0 = staff.getId();
		Object id1 = staff.getId();
		Object id2 = staff.getId();
		Object id3 = staff.getId();
		//===================================================================================
		//删除-工作经历
	    String hql0 = "from WorkexperienceEntity where 1 = 1 AND staffid = ? ";
	    List<WorkexperienceEntity> workexperienceOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(workexperienceOldList);
		//===================================================================================
		//删除-学历
	    String hql1 = "from EducationEntity where 1 = 1 AND staffid = ? ";
	    List<EducationEntity> educationOldList = this.findHql(hql1,id1);
		this.deleteAllEntitie(educationOldList);
		//===================================================================================
		//删除-家庭成员
	    String hql2 = "from FamilymemberEntity where 1 = 1 AND staffid = ? ";
	    List<FamilymemberEntity> familymemberOldList = this.findHql(hql2,id2);
		this.deleteAllEntitie(familymemberOldList);
		//===================================================================================
		//删除-部门信息
	    String hql3 = "from DepartmentEntity where 1 = 1 AND staffid = ? ";
	    List<DepartmentEntity> departmentOldList = this.findHql(hql3,id3);
		this.deleteAllEntitie(departmentOldList);
	}
	
}
package com.sys.service.staff;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.sys.entity.staff.StaffEntity;
import com.sys.entity.workexperience.WorkexperienceEntity;
import com.sys.entity.education.EducationEntity;
import com.sys.entity.familymember.FamilymemberEntity;
import com.sys.entity.department.DepartmentEntity;

public interface StaffServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(StaffEntity staff,
	        List<WorkexperienceEntity> workexperienceList,List<EducationEntity> educationList,List<FamilymemberEntity> familymemberList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(StaffEntity staff,
	        List<WorkexperienceEntity> workexperienceList,List<EducationEntity> educationList,List<FamilymemberEntity> familymemberList);
	public void delMain (StaffEntity staff);
}

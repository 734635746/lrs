package com.sys.service.impl.attendance;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.attendance.AttendanceServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("attendanceService")
@Transactional
public class AttendanceServiceImpl extends CommonServiceImpl implements AttendanceServiceI {
	
}
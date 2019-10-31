package com.sys.service.impl.registrationroom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.registrationroom.RegistrationroomServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("registrationroomService")
@Transactional
public class RegistrationroomServiceImpl extends CommonServiceImpl implements RegistrationroomServiceI {
	
}
package com.sys.service.impl.registrationcar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.registrationcar.RegistrationcarServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("registrationcarService")
@Transactional
public class RegistrationcarServiceImpl extends CommonServiceImpl implements RegistrationcarServiceI {
	
}
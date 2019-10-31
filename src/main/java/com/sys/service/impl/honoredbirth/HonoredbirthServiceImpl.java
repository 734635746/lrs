package com.sys.service.impl.honoredbirth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.honoredbirth.HonoredbirthServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("honoredbirthService")
@Transactional
public class HonoredbirthServiceImpl extends CommonServiceImpl implements HonoredbirthServiceI {
	
}
package com.sys.service.impl.buddhabirth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.buddhabirth.BuddhabirthServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("buddhabirthService")
@Transactional
public class BuddhabirthServiceImpl extends CommonServiceImpl implements BuddhabirthServiceI {
	
}
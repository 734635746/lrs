package com.sys.service.impl.goddessbirth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.goddessbirth.GoddessbirthServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("goddessbirthService")
@Transactional
public class GoddessbirthServiceImpl extends CommonServiceImpl implements GoddessbirthServiceI {
	
}
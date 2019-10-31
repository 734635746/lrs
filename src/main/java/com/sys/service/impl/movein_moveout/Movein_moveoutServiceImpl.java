package com.sys.service.impl.movein_moveout;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.movein_moveout.Movein_moveoutServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("movein_moveoutService")
@Transactional
public class Movein_moveoutServiceImpl extends CommonServiceImpl implements Movein_moveoutServiceI {
	
}
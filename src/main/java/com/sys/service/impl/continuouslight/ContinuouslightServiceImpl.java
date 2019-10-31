package com.sys.service.impl.continuouslight;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.continuouslight.ContinuouslightServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("continuouslightService")
@Transactional
public class ContinuouslightServiceImpl extends CommonServiceImpl implements ContinuouslightServiceI {
	
}
package com.sys.service.impl.funeralheld;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.funeralheld.FuneralheldServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("funeralheldService")
@Transactional
public class FuneralheldServiceImpl extends CommonServiceImpl implements FuneralheldServiceI {
	
}
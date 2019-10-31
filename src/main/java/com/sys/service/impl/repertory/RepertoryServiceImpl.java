package com.sys.service.impl.repertory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.repertory.RepertoryServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("repertoryService")
@Transactional
public class RepertoryServiceImpl extends CommonServiceImpl implements RepertoryServiceI {
	
}
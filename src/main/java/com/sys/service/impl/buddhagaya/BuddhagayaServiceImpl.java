package com.sys.service.impl.buddhagaya;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.buddhagaya.BuddhagayaServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("buddhagayaService")
@Transactional
public class BuddhagayaServiceImpl extends CommonServiceImpl implements BuddhagayaServiceI {
	
}
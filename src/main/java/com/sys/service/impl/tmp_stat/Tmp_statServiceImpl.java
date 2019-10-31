package com.sys.service.impl.tmp_stat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.tmp_stat.Tmp_statServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tmp_statService")
@Transactional
public class Tmp_statServiceImpl extends CommonServiceImpl implements Tmp_statServiceI {
	
}
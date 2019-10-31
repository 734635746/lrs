package com.sys.service.impl.m_info;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.m_info.M_infoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("m_infoService")
@Transactional
public class M_infoServiceImpl extends CommonServiceImpl implements M_infoServiceI {
	
}
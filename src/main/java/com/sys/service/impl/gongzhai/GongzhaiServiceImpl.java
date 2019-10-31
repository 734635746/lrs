package com.sys.service.impl.gongzhai;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.gongzhai.GongzhaiServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("gongzhaiService")
@Transactional
public class GongzhaiServiceImpl extends CommonServiceImpl implements GongzhaiServiceI {
	
}
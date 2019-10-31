package com.sys.service.impl.jizobirth;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.jizobirth.JIzobirthServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("jIzobirthService")
@Transactional
public class JIzobirthServiceImpl extends CommonServiceImpl implements JIzobirthServiceI {
	
}
package com.sys.service.impl.disciplemember;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.disciplemember.DisciplememberServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("disciplememberService")
@Transactional
public class DisciplememberServiceImpl extends CommonServiceImpl implements DisciplememberServiceI {
	
}
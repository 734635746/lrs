package com.sys.service.impl.pravrajanamember;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.pravrajanamember.PravrajanamemberServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("pravrajanamemberService")
@Transactional
public class PravrajanamemberServiceImpl extends CommonServiceImpl implements PravrajanamemberServiceI {
	
}
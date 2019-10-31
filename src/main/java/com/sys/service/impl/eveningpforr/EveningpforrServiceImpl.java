package com.sys.service.impl.eveningpforr;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.eveningpforr.EveningpforrServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("eveningpforrService")
@Transactional
public class EveningpforrServiceImpl extends CommonServiceImpl implements EveningpforrServiceI {
	
}
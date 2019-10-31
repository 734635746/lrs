package com.sys.service.impl.freelives;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.freelives.FreeLivesServiceI;
import com.sys.service.morningpforr.MorningpforrServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("freeLivesService")
@Transactional
public class FreeLivesServiceImpl extends CommonServiceImpl implements FreeLivesServiceI {
	
}
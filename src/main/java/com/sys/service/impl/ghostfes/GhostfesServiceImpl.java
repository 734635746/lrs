package com.sys.service.impl.ghostfes;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.ghostfes.GhostfesServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("ghostfesService")
@Transactional
public class GhostfesServiceImpl extends CommonServiceImpl implements GhostfesServiceI {
	
}
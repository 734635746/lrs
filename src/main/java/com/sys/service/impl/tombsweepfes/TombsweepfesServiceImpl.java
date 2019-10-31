package com.sys.service.impl.tombsweepfes;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.tombsweepfes.TombsweepfesServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tombsweepfesService")
@Transactional
public class TombsweepfesServiceImpl extends CommonServiceImpl implements TombsweepfesServiceI {
	
}
package com.sys.service.impl.diamondsutra;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.diamondsutra.DiamondsutraServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("diamondsutraService")
@Transactional
public class DiamondsutraServiceImpl extends CommonServiceImpl implements DiamondsutraServiceI {
	
}
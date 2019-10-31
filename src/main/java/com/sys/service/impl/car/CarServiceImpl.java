package com.sys.service.impl.car;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.car.CarServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("carService")
@Transactional
public class CarServiceImpl extends CommonServiceImpl implements CarServiceI {
	
}
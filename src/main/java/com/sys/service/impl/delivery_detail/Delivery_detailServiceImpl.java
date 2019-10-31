package com.sys.service.impl.delivery_detail;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.delivery_detail.Delivery_detailServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("delivery_detailService")
@Transactional
public class Delivery_detailServiceImpl extends CommonServiceImpl implements Delivery_detailServiceI {
	
}
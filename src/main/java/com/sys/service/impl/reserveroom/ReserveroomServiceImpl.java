package com.sys.service.impl.reserveroom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.reserveroom.ReserveroomServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("reserveroomService")
@Transactional
public class ReserveroomServiceImpl extends CommonServiceImpl implements ReserveroomServiceI {
	
}
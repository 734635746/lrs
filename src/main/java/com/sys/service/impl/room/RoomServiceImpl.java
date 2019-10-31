package com.sys.service.impl.room;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.room.RoomServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("roomService")
@Transactional
public class RoomServiceImpl extends CommonServiceImpl implements RoomServiceI {
	
}
package com.sys.service.impl.tmp_table;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.tmp_table.Tmp_tableServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tmp_tableService")
@Transactional
public class Tmp_tableServiceImpl extends CommonServiceImpl implements Tmp_tableServiceI {
	
}
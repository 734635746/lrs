package com.sys.service.impl.setfree;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.setfree.SetFreeServiceI;

@Service("setFreeService")
@Transactional
public class SetFreeServiceImpl extends CommonServiceImpl implements SetFreeServiceI{

}

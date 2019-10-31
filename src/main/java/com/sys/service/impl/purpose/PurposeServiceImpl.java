package com.sys.service.impl.purpose;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.purpose.PurposeServiceI;


/**
* @ClassName: PurposeServiceImpl
* @Description: 善款用途service实现类
* @author kooking
* @date 2018-3-29 上午11:30:36
*/ 
@Service("purposeService")
@Transactional
public class PurposeServiceImpl extends CommonServiceImpl implements PurposeServiceI{

}

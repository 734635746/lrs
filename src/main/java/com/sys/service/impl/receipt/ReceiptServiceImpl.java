package com.sys.service.impl.receipt;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.receipt.ReceiptServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("receiptService")
@Transactional
public class ReceiptServiceImpl extends CommonServiceImpl implements ReceiptServiceI {
	
}
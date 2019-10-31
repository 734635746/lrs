package com.sys.service.impl.receiptno;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.service.receiptno.ReceiptnoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("receiptnoService")
@Transactional
public class ReceiptnoServiceImpl extends CommonServiceImpl implements ReceiptnoServiceI {
	
}
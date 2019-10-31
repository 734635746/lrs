package com.sys.entity.purpose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
* @ClassName: PurposeEntity
* @Description: 善款用途
* @author kooking
* @date 2018-3-29 上午11:11:13
*/ 
@Entity
@Table(name = "purpose", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class PurposeEntity implements java.io.Serializable{

	/**
	* @Fields id : 主键
	*/ 
	private java.lang.String id;
	
	/**
	* @Fields purpose : 善款用途
	*/ 
	private java.lang.String purpose;

	
	
	/**
	* @Title: getId
	* @Description: 获取id
	* @return   
	* @return java.lang.String    返回类型
	* @throws
	*/ 
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId() {
		return id;
	}

	/**
	* @Title: setId
	* @Description: 设置id
	* @param id   
	* @return void    返回类型
	* @throws
	*/ 
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	* @Title: getPurpose
	* @Description: 获取善款用途名称
	* @return   
	* @return java.lang.String    返回类型
	* @throws
	*/ 
	@Column(name ="purpose",nullable=true,length=20)
	public java.lang.String getPurpose() {
		return purpose;
	}

	/**
	* @Title: setPurpose
	* @Description: 设置善款用途
	* @param purpose   
	* @return void    返回类型
	* @throws
	*/ 
	public void setPurpose(java.lang.String purpose) {
		this.purpose = purpose;
	}
}

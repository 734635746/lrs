package com.sys.entity.delivery_detail;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 出库明细表
 * @author zhangdaihao
 * @date 2016-10-18 18:50:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "delivery_detail", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class Delivery_detailEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**register*/
	private java.lang.String register;
	/**registertime*/
	private java.lang.String registertime;
	/**物品代码*/
	private java.lang.String itemStdmode;
	/**物品名称*/
	private java.lang.String itemName;
	/**生产厂家*/
	private java.lang.String itemManufacturer;
	/**生产地址*/
	private java.lang.String manufacturerAddress;
	private java.lang.String unit;
	/**memberName*/
	private java.lang.String memberName;
	/**当前库存量*/
	private java.lang.Integer deliveryQuantity;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  register
	 */
	@Column(name ="REGISTER",nullable=true,length=255)
	public java.lang.String getRegister(){
		return this.register;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  register
	 */
	public void setRegister(java.lang.String register){
		this.register = register;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  registertime
	 */
	@Column(name ="REGISTERTIME",nullable=true,length=255)
	public java.lang.String getRegistertime(){
		return this.registertime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  registertime
	 */
	public void setRegistertime(java.lang.String registertime){
		this.registertime = registertime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物品代码
	 */
	@Column(name ="ITEM_STDMODE",nullable=true,length=100)
	public java.lang.String getItemStdmode(){
		return this.itemStdmode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物品代码
	 */
	public void setItemStdmode(java.lang.String itemStdmode){
		this.itemStdmode = itemStdmode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物品名称
	 */
	@Column(name ="ITEM_NAME",nullable=true,length=100)
	public java.lang.String getItemName(){
		return this.itemName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物品名称
	 */
	public void setItemName(java.lang.String itemName){
		this.itemName = itemName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产厂家
	 */
	@Column(name ="ITEM_MANUFACTURER",nullable=true,length=100)
	public java.lang.String getItemManufacturer(){
		return this.itemManufacturer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产厂家
	 */
	public void setItemManufacturer(java.lang.String itemManufacturer){
		this.itemManufacturer = itemManufacturer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产地址
	 */
	@Column(name ="MANUFACTURER_ADDRESS",nullable=true,length=100)
	public java.lang.String getManufacturerAddress(){
		return this.manufacturerAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产地址
	 */
	public void setManufacturerAddress(java.lang.String manufacturerAddress){
		this.manufacturerAddress = manufacturerAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  memberName
	 */
	@Column(name ="MEMBER_NAME",nullable=true,length=255)
	public java.lang.String getMemberName(){
		return this.memberName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  memberName
	 */
	public void setMemberName(java.lang.String memberName){
		this.memberName = memberName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  当前库存量
	 */
	@Column(name ="DELIVERY_QUANTITY",nullable=true,precision=10,scale=0)
	public java.lang.Integer getDeliveryQuantity(){
		return this.deliveryQuantity;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  当前库存量
	 */
	public void setDeliveryQuantity(java.lang.Integer deliveryQuantity){
		this.deliveryQuantity = deliveryQuantity;
	}

	public java.lang.String getUnit() {
		return unit;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}
}

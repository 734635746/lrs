package com.sys.entity.repertory;

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
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 库存表
 * @author zhangdaihao
 * @date 2016-10-18 18:18:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "repertory", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
@ExcelTarget(id="repertoryEntity")
public class RepertoryEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	
	@Excel(exportName="登记人",orderNum="1",needMerge=true)
	private java.lang.String register;
	@Excel(exportName="登记时间",orderNum="2",needMerge=true,importFormat="yyyy-MM-dd")
	private java.lang.String registertime;
	/**物品代码*/
	@Excel(exportName="物品编码",orderNum="3",needMerge=true)
	private java.lang.String itemStdmode;
	/**物品名称*/
	@Excel(exportName="物品名称",orderNum="4",needMerge=true)
	private java.lang.String itemName;
	/**生产厂家*/
	@Excel(exportName="生产厂家",orderNum="5",needMerge=true)
	private java.lang.String itemManufacturer;
	/**生产地址*/
	@Excel(exportName="生产地址",orderNum="6",needMerge=true)
	private java.lang.String manufacturerAddress;
	/**当前库存量*/
	@Excel(exportName="当前库存量",orderNum="7",needMerge=true)
	private java.lang.Integer currentInventory;
	/**最低库存量*/
	@Excel(exportName="最低库存量",orderNum="8",needMerge=true)
	private java.lang.Integer quantityStorage;
	
	private java.lang.String unit;
	private java.lang.Float price;
	
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
	public java.lang.String getRegister() {
		return register;
	}

	public void setRegister(java.lang.String register) {
		this.register = register;
	}

	public java.lang.String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(java.lang.String registertime) {
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  当前库存量
	 */
	@Column(name ="CURRENT_INVENTORY",nullable=true,precision=10,scale=0)
	public java.lang.Integer getCurrentInventory(){
		return this.currentInventory;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  当前库存量
	 */
	public void setCurrentInventory(java.lang.Integer currentInventory){
		this.currentInventory = currentInventory;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  最低库存量
	 */
	@Column(name ="QUANTITY_STORAGE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getQuantityStorage(){
		return this.quantityStorage;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  最低库存量
	 */
	public void setQuantityStorage(java.lang.Integer quantityStorage){
		this.quantityStorage = quantityStorage;
	}

	public java.lang.String getUnit() {
		return unit;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	public java.lang.Float getPrice() {
		return price;
	}

	public void setPrice(java.lang.Float price) {
		this.price = price;
	}
}

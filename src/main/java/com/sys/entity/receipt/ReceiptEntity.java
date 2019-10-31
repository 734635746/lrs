package com.sys.entity.receipt;

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
 * @Description: 收据表
 * @author zhangdaihao
 * @date 2015-12-04 15:18:31
 * @version V1.0   
 *
 */
@Entity
@Table(name = "receipt", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
@ExcelTarget(id="receiptEntity")
public class ReceiptEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**法事类型*/
	@Excel(exportName="法事类型",orderNum="2")
	private java.lang.String ritualtype;
	/**登记时间*/
	@Excel(exportName="登记时间",orderNum="3")
	private java.lang.String registertime;
	/**登记人*/
	@Excel(exportName="登记人",orderNum="4")
	private java.lang.String registrant;
	/**付款人*/
	@Excel(exportName="付款人",orderNum="5")
	private java.lang.String paymen;
	/**金额*/
	@Excel(exportName="金额",orderNum="7",importConvertSign=1)
	private java.lang.Integer money;
	/**付款方式*/
	@Excel(exportName="付款方式",orderNum="8")
	private java.lang.String payway;
	/**做法事的人ID*/
	private java.lang.String doritualid;
	/**法事的ID*/
	private java.lang.String ritualid;
	/**收据编号 */
	@Excel(exportName="收据编号",orderNum="1")
	private java.lang.String no;
	/**摘要*/
	@Excel(exportName="摘要",orderNum="9")
	private java.lang.String summary;
	/**用途*/
	@Excel(exportName="用途",orderNum="12")
	private java.lang.String purpose;
	/**地址*/
	@Excel(exportName="地址",orderNum="10")
	private java.lang.String address;
	/**备注*/
	@Excel(exportName="备注",orderNum="11")
	private java.lang.String remark;
	/**对象*/
	@Excel(exportName="对象",orderNum="6")
	private java.lang.String obj;
	/**超渡类型*/
	private java.lang.Integer type;
	/**是否退订*/
	private java.lang.Integer flag;
	/**是否交接*/
	@Excel(exportName="是否确认交接",orderNum="14",exportConvertSign=1)
	private java.lang.Integer transit;
	/**取消标志*/
	@Excel(exportName="是否已退订",orderNum="13",exportConvertSign=1)
	private java.lang.Integer cancel;
	/**取消原因*/
	private java.lang.String cancelreason;
	/**取消经手人*/
	private java.lang.String cancelhandler;
	/**取消日期*/
	private java.lang.String canceldate;
	private java.lang.String date;
	/**牌位大小*/
	private java.lang.String size;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
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
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  法事类型
	 */
	@Column(name ="RITUALTYPE",nullable=true,length=12)
	public java.lang.String getRitualtype(){
		return this.ritualtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法事类型
	 */
	public void setRitualtype(java.lang.String ritualtype){
		this.ritualtype = ritualtype;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  登记时间
	 */
	@Column(name ="REGISTERTIME",length=40,nullable=true)
	public java.lang.String getRegistertime(){
		return this.registertime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  登记时间
	 */
	public void setRegistertime(java.lang.String registertime){
		this.registertime = registertime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登记人
	 */
	@Column(name ="REGISTRANT",nullable=true,length=13)
	public java.lang.String getRegistrant(){
		return this.registrant;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登记人
	 */
	public void setRegistrant(java.lang.String registrant){
		this.registrant = registrant;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款人
	 */
	@Column(name ="PAYMEN",nullable=true,length=40)
	public java.lang.String getPaymen(){
		return this.paymen;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款人
	 */
	public void setPaymen(java.lang.String paymen){
		this.paymen = paymen;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  金额
	 */
	@Column(name ="MONEY",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  金额
	 */
	public void setMoney(java.lang.Integer money){
		this.money = money;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款方式
	 */
	@Column(name ="PAYWAY",nullable=true,length=3)
	public java.lang.String getPayway(){
		return this.payway;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款方式
	 */
	public void setPayway(java.lang.String payway){
		this.payway = payway;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  做法事的人ID
	 */
	@Column(name ="DORITUALID",nullable=true,length=36)
	public java.lang.String getDoritualid(){
		return this.doritualid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  做法事的人ID
	 */
	public void setDoritualid(java.lang.String doritualid){
		this.doritualid = doritualid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  法事的ID
	 */
	@Column(name ="RITUALID",nullable=true,length=36)
	public java.lang.String getRitualid(){
		return this.ritualid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法事的ID
	 */
	public void setRitualid(java.lang.String ritualid){
		this.ritualid = ritualid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  摘要
	 */
	@Column(name ="SUMMARY",nullable=true,length=1000)
	public java.lang.String getSummary(){
		return this.summary;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  摘要
	 */
	public void setSummary(java.lang.String summary){
		this.summary = summary;
	}

	@Column(name ="NO",nullable=true,length=20)
	public java.lang.String getNo() {
		return no;
	}

	public void setNo(java.lang.String no) {
		this.no = no;
	}

	@Column(name ="PURPOSE",nullable=true,length=30)
	public java.lang.String getPurpose() {
		return purpose;
	}

	public void setPurpose(java.lang.String purpose) {
		this.purpose = purpose;
	}

	@Column(name ="ADDRESS",nullable=true,length=30)
	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	@Column(name ="REMARK",nullable=true,length=30)
	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	@Column(name ="OBJ",nullable=true,length=100)
	public java.lang.String getObj() {
		return obj;
	}

	public void setObj(java.lang.String obj) {
		this.obj = obj;
	}

	@Column(name ="TYPE",nullable=true,length=2)
	public java.lang.Integer getType() {
		return type;
	}

	public void setType(java.lang.Integer type) {
		this.type = type;
	}

	@Column(name ="FLAG",nullable=true,length=2)
	public java.lang.Integer getFlag() {
		return flag;
	}

	public void setFlag(java.lang.Integer flag) {
		this.flag = flag;
	}

	@Column(name ="TRANSIT",nullable=true,length=2)
	public java.lang.Integer getTransit() {
		return transit;
	}

	public void setTransit(java.lang.Integer transit) {
		this.transit = transit;
	}

	@Column(name ="CANCEL",nullable=true,length=2)
	public java.lang.Integer getCancel() {
		return cancel;
	}

	public void setCancel(java.lang.Integer cancel) {
		this.cancel = cancel;
	}

	@Column(name ="CANCELREASON",nullable=true,length=255)
	public java.lang.String getCancelreason() {
		return cancelreason;
	}

	public void setCancelreason(java.lang.String cancelreason) {
		this.cancelreason = cancelreason;
	}

	@Column(name ="CANCELHANDLER",nullable=true,length=60)
	public java.lang.String getCancelhandler() {
		return cancelhandler;
	}

	public void setCancelhandler(java.lang.String cancelhandler) {
		this.cancelhandler = cancelhandler;
	}

	@Column(name ="CANCELDATE",nullable=true,length=40)
	public java.lang.String getCanceldate() {
		return canceldate;
	}

	public void setCanceldate(java.lang.String canceldate) {
		this.canceldate = canceldate;
	}

	public java.lang.String getDate() {
		return date;
	}

	public void setDate(java.lang.String date) {
		this.date = date;
	}

	public java.lang.String getSize() {
		return size;
	}

	public void setSize(java.lang.String size) {
		this.size = size;
	}
	
	
	public String convertGetCancel(){
		if(this.cancel == 0){
			return "正常";
		}else{
			return "已退订";
		}
	}
	
	
	public String convertGetTransit(){
		if(this.cancel == 0){
			return "未已交接";
		}else{
			return "已交接";
		}
	}
	
}

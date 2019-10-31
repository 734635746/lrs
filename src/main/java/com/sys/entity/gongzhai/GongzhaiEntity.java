package com.sys.entity.gongzhai;

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
 * @Description: 供斋管理
 * @author zhangdaihao
 * @date 2016-08-17 10:26:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "gongzhai", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class GongzhaiEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**登记时间*/
	private java.lang.String registertime;
	/**登记人*/
	private java.lang.String registrant;
	/**编号*/
	private java.lang.Integer serial;
	/**付款者*/
	private java.lang.String prayingobj;
	/**去世*/
	private java.lang.String livingmember;
	/**金额*/
	private java.lang.Integer money;
	/**收款方式*/
	private java.lang.String payway;
	/**摘要*/
	private java.lang.String summary;
	/**receiptno*/
	private java.lang.String receiptno;
	/**doritualid*/
	private java.lang.String doritualid;
	/**address*/
	private java.lang.String address;
	/**receiptid*/
	private java.lang.String receiptid;
	/**type*/
	private java.lang.Integer type;
	/**flag*/
	private java.lang.Integer flag;
	/**size*/
	private java.lang.String size;
	/**autoserial*/
	private java.lang.String autoserial;
	/**cancel*/
	private java.lang.Integer cancel;
	/**holddate*/
	private java.lang.String holddate;
	
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
	 *@return: java.lang.String  登记时间
	 */
	@Column(name ="REGISTERTIME",nullable=true,length=40)
	public java.lang.String getRegistertime(){
		return this.registertime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登记时间
	 */
	public void setRegistertime(java.lang.String registertime){
		this.registertime = registertime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登记人
	 */
	@Column(name ="REGISTRANT",nullable=true,length=12)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  编号
	 */
	@Column(name ="SERIAL",nullable=true,precision=10,scale=0)
	public java.lang.Integer getSerial(){
		return this.serial;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  编号
	 */
	public void setSerial(java.lang.Integer serial){
		this.serial = serial;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款者
	 */
	@Column(name ="PRAYINGOBJ",nullable=true,length=40)
	public java.lang.String getPrayingobj(){
		return this.prayingobj;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款者
	 */
	public void setPrayingobj(java.lang.String prayingobj){
		this.prayingobj = prayingobj;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  去世
	 */
	@Column(name ="LIVINGMEMBER",nullable=true,length=200)
	public java.lang.String getLivingmember(){
		return this.livingmember;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  去世
	 */
	public void setLivingmember(java.lang.String livingmember){
		this.livingmember = livingmember;
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
	 *@return: java.lang.String  收款方式
	 */
	@Column(name ="PAYWAY",nullable=true,length=3)
	public java.lang.String getPayway(){
		return this.payway;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款方式
	 */
	public void setPayway(java.lang.String payway){
		this.payway = payway;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  摘要
	 */
	@Column(name ="SUMMARY",nullable=true,length=255)
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  receiptno
	 */
	@Column(name ="RECEIPTNO",nullable=true,length=20)
	public java.lang.String getReceiptno(){
		return this.receiptno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  receiptno
	 */
	public void setReceiptno(java.lang.String receiptno){
		this.receiptno = receiptno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  doritualid
	 */
	@Column(name ="DORITUALID",nullable=true,length=36)
	public java.lang.String getDoritualid(){
		return this.doritualid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  doritualid
	 */
	public void setDoritualid(java.lang.String doritualid){
		this.doritualid = doritualid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  address
	 */
	@Column(name ="ADDRESS",nullable=true,length=30)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  address
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  receiptid
	 */
	@Column(name ="RECEIPTID",nullable=true,length=36)
	public java.lang.String getReceiptid(){
		return this.receiptid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  receiptid
	 */
	public void setReceiptid(java.lang.String receiptid){
		this.receiptid = receiptid;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  type
	 */
	@Column(name ="TYPE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  type
	 */
	public void setType(java.lang.Integer type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  flag
	 */
	@Column(name ="FLAG",nullable=true,precision=10,scale=0)
	public java.lang.Integer getFlag(){
		return this.flag;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  flag
	 */
	public void setFlag(java.lang.Integer flag){
		this.flag = flag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  size
	 */
	@Column(name ="SIZE",nullable=true,length=2)
	public java.lang.String getSize(){
		return this.size;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  size
	 */
	public void setSize(java.lang.String size){
		this.size = size;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  autoserial
	 */
	@Column(name ="AUTOSERIAL",nullable=true,length=5)
	public java.lang.String getAutoserial(){
		return this.autoserial;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  autoserial
	 */
	public void setAutoserial(java.lang.String autoserial){
		this.autoserial = autoserial;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  cancel
	 */
	@Column(name ="CANCEL",nullable=true,precision=10,scale=0)
	public java.lang.Integer getCancel(){
		return this.cancel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  cancel
	 */
	public void setCancel(java.lang.Integer cancel){
		this.cancel = cancel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  holddate
	 */
	@Column(name ="HOLDDATE",nullable=true,length=20)
	public java.lang.String getHolddate(){
		return this.holddate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  holddate
	 */
	public void setHolddate(java.lang.String holddate){
		this.holddate = holddate;
	}
}

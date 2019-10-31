package com.sys.entity.setfree;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "setfree", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
public class SetFreeEntity {
	/**id*/
	private java.lang.String id;
	/**登记时间*/
	private java.lang.String registertime;
	/**登记人*/
	private java.lang.String registrant;
	/**编号*/
	private java.lang.String serial;
	/**祈福者*/
	private java.lang.String prayingobj;
	/**在世*/
	private java.lang.String livingmenber;
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
	/**receiptid*/
	private java.lang.String receiptid;
	/**flag*/
	private java.lang.Integer flag;
	/**autoserial*/
	private java.lang.String autoserial;
	/**cancel*/
	private java.lang.Integer cancel;
	/**paymen*/
	private java.lang.String paymen;
	/**lunardate*/
	private java.lang.String lunardate;
	/**solardate*/
	private java.lang.String solardate;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编号
	 */
	@Column(name ="SERIAL",nullable=true,length=11)
	public java.lang.String getSerial(){
		return this.serial;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setSerial(java.lang.String serial){
		this.serial = serial;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  祈福者
	 */
	@Column(name ="PRAYINGOBJ",nullable=true,length=40)
	public java.lang.String getPrayingobj(){
		return this.prayingobj;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  祈福者
	 */
	public void setPrayingobj(java.lang.String prayingobj){
		this.prayingobj = prayingobj;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  在世
	 */
	@Column(name ="LIVINGMENBER",nullable=true,length=200)
	public java.lang.String getLivingmenber(){
		return this.livingmenber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  在世
	 */
	public void setLivingmenber(java.lang.String livingmenber){
		this.livingmenber = livingmenber;
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
	 *@return: java.lang.String  autoserial
	 */
	@Column(name ="AUTOSERIAL",nullable=true,length=20)
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
	 *@return: java.lang.String  paymen
	 */
	@Column(name ="PAYMEN",nullable=true,length=255)
	public java.lang.String getPaymen(){
		return this.paymen;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  paymen
	 */
	public void setPaymen(java.lang.String paymen){
		this.paymen = paymen;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  lunardate
	 */
	@Column(name ="LUNARDATE",nullable=true,length=255)
	public java.lang.String getLunardate(){
		return this.lunardate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  lunardate
	 */
	public void setLunardate(java.lang.String lunardate){
		this.lunardate = lunardate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  solardate
	 */
	@Column(name ="SOLARDATE",nullable=true,length=255)
	public java.lang.String getSolardate(){
		return this.solardate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  solardate
	 */
	public void setSolardate(java.lang.String solardate){
		this.solardate = solardate;
	}

}

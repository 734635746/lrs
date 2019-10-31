package com.sys.entity.pharmacistbirth;

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
 * @Description: 药师诞信息
 * @author zhangdaihao
 * @date 2015-11-25 16:31:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pharmacistbirth", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class PharmacistbirthEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**登记时间*/
	private java.lang.String registertime;
	/**登记人*/
	private java.lang.String registrant;
	/**编号*/
	private java.lang.String serial;
	/**收据编号*/
	private java.lang.String receiptNo;
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
	/**做法事人的ID*/
	private java.lang.String doritualid;
	/**地址*/
	private java.lang.String address;
	/**收据ID*/
	private java.lang.String receiptId;
	/**打印标志*/
	private java.lang.Integer flag;
	/**牌位大小*/
	private java.lang.String size;
	/**自动编号*/
	private java.lang.String autoserial;
	/**是否取消*/
	private java.lang.Integer cancel;
	private java.lang.String paymen;
	private java.lang.String book;
	
	@Column(name ="BOOK",nullable=true,length=255)
	public java.lang.String getBook() {
		return book;
	}

	public void setBook(java.lang.String book) {
		this.book = book;
	}
	@Column(name ="AUTOSERIAL",nullable=true,length=5)
	public java.lang.String getAutoserial() {
		return autoserial;
	}

	public void setAutoserial(java.lang.String autoserial) {
		this.autoserial = autoserial;
	}

	@Column(name ="CANCEL",nullable=true,length=2)
	public java.lang.Integer getCancel() {
		return cancel;
	}

	public void setCancel(java.lang.Integer cancel) {
		this.cancel = cancel;
	}
	
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  登记时间
	 */
	@Column(name ="REGISTERTIME",nullable=true,length=40)
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
	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
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

	@Column(name ="SUMMARY",nullable=true,length=255)
	public java.lang.String getSummary() {
		return summary;
	}

	public void setSummary(java.lang.String summary) {
		this.summary = summary;
	}

	@Column(name ="RECEIPTNO",nullable=true,length=20)
	public java.lang.String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(java.lang.String receiptNo) {
		this.receiptNo = receiptNo;
	}

	@Column(name ="DORITUALID",nullable=true,length=36)
	public java.lang.String getDoritualid() {
		return doritualid;
	}

	public void setDoritualid(java.lang.String doritualid) {
		this.doritualid = doritualid;
	}

	@Column(name ="ADDRESS",nullable=true,length=30)
	public java.lang.String getAddress() {
		return address;
	}
	
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	
	@Column(name ="RECEIPTID",nullable=true,length=36)
	public java.lang.String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(java.lang.String receiptId) {
		this.receiptId = receiptId;
	}

	@Column(name ="FLAG",nullable=true,length=2)
	public java.lang.Integer getFlag() {
		return flag;
	}

	public void setFlag(java.lang.Integer flag) {
		this.flag = flag;
	}

	@Column(name ="SIZE",nullable=true,length=2)
	public java.lang.String getSize() {
		return size;
	}

	public void setSize(java.lang.String size) {
		this.size = size;
	}
	@Column(name ="PAYMEN",nullable=true,length=2)
	public java.lang.String getPaymen() {
		return paymen;
	}

	public void setPaymen(java.lang.String paymen) {
		this.paymen = paymen;
	}

}

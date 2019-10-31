package com.sys.entity.registrationroom;

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
 * @Description: 登记房间
 * @author zhangdaihao
 * @date 2015-12-29 19:20:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "registrationroom", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class RegistrationroomEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**登记人*/
	private java.lang.String registrant;
	/**入住人名字*/
	private java.lang.String intakename;
	/**身份证号*/
	private java.lang.String identification;
	/**手机号*/
	private java.lang.String mobilephone;
	/**入住房间号*/
	private java.lang.String intakennumber;
	/**入住房间类型*/
	private java.lang.String intakentype;
	/**需要床位*/
	private java.lang.Integer needbeds;
	/**房间号ID*/
	private java.lang.String roomid;
	/**入住时间*/
	private java.util.Date intakentime;
	/**登记时间*/
	private java.util.Date registertime;
	/**离开时间*/
	private java.util.Date leavetime;
	/**确认离开时间*/
	private java.util.Date confirmdeparturetime;
	/**状态 离开 未离开*/
	private java.lang.String leavestate;
	
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
	 *@return: java.lang.String  登记人
	 */
	@Column(name ="REGISTRANT",nullable=true,length=10)
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
	 *@return: java.lang.String  入住人名字
	 */
	@Column(name ="INTAKENAME",nullable=true,length=10)
	public java.lang.String getIntakename(){
		return this.intakename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入住人名字
	 */
	public void setIntakename(java.lang.String intakename){
		this.intakename = intakename;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证号
	 */
	@Column(name ="IDENTIFICATION",nullable=true,length=26)
	public java.lang.String getIdentification(){
		return this.identification;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号
	 */
	public void setIdentification(java.lang.String identification){
		this.identification = identification;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号
	 */
	@Column(name ="MOBILEPHONE",nullable=true,length=11)
	public java.lang.String getMobilephone(){
		return this.mobilephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号
	 */
	public void setMobilephone(java.lang.String mobilephone){
		this.mobilephone = mobilephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入住房间号
	 */
	@Column(name ="INTAKENNUMBER",nullable=true,length=20)
	public java.lang.String getIntakennumber(){
		return this.intakennumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入住房间号
	 */
	public void setIntakennumber(java.lang.String intakennumber){
		this.intakennumber = intakennumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入住房间类型
	 */
	@Column(name ="INTAKENTYPE",nullable=true,length=6)
	public java.lang.String getIntakentype(){
		return this.intakentype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入住房间类型
	 */
	public void setIntakentype(java.lang.String intakentype){
		this.intakentype = intakentype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  房间号ID
	 */
	@Column(name ="ROOMID",nullable=true,length=36)
	public java.lang.String getRoomid(){
		return this.roomid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  房间号ID
	 */
	public void setRoomid(java.lang.String roomid){
		this.roomid = roomid;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入住时间
	 */
	@Column(name ="INTAKENTIME",nullable=true)
	public java.util.Date getIntakentime(){
		return this.intakentime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入住时间
	 */
	public void setIntakentime(java.util.Date intakentime){
		this.intakentime = intakentime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  登记时间
	 */
	@Column(name ="REGISTERTIME",nullable=true)
	public java.util.Date getRegistertime(){
		return this.registertime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  登记时间
	 */
	public void setRegistertime(java.util.Date registertime){
		this.registertime = registertime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  离开时间
	 */
	@Column(name ="LEAVETIME",nullable=true)
	public java.util.Date getLeavetime(){
		return this.leavetime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  离开时间
	 */
	public void setLeavetime(java.util.Date leavetime){
		this.leavetime = leavetime;
	}

	@Column(name ="NEEDBEDS",nullable=true,length=10)
	public java.lang.Integer getNeedbeds() {
		return needbeds;
	}

	public void setNeedbeds(java.lang.Integer needbeds) {
		this.needbeds = needbeds;
	}

	@Column(name ="LEAVESTATE",nullable=true,length=10)
	public java.lang.String getLeavestate() {
		return leavestate;
	}

	public void setLeavestate(java.lang.String leavestate) {
		this.leavestate = leavestate;
	}

	@Column(name ="CONFIRMDEPARTURETIME",nullable=true)
	public java.util.Date getconfirmdeparturetime() {
		return confirmdeparturetime;
	}

	public void setconfirmdeparturetime(java.util.Date confirmdeparturetime) {
		this.confirmdeparturetime = confirmdeparturetime;
	}
}

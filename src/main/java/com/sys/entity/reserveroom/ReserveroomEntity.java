package com.sys.entity.reserveroom;

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
 * @Description: 预定房间
 * @author zhangdaihao
 * @date 2016-01-14 15:09:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "reserveroom", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ReserveroomEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**房号*/
	private java.lang.String roomnumber;
	/**房间类型*/
	private java.lang.String roomkind;
	/**所需床位*/
	private java.lang.Integer needbeds;
	/**登记人*/
	private java.lang.String registrant;
	/**登记时间*/
	private java.util.Date registertime;
	/**入住时间*/
	private java.util.Date intakentime;
	/**离开时间*/
	private java.util.Date leavetime;
	/**手机号*/
	private java.lang.String mobilephone;
	/**预定状态*/
	private java.lang.String predeterminedstate;
	/**房间ID*/
	private java.lang.String roomid;
	
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
	 *@return: java.lang.String  房号
	 */
	@Column(name ="ROOMNUMBER",nullable=true,length=12)
	public java.lang.String getRoomnumber(){
		return this.roomnumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  房号
	 */
	public void setRoomnumber(java.lang.String roomnumber){
		this.roomnumber = roomnumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  房间类型
	 */
	@Column(name ="ROOMKIND",nullable=true,length=8)
	public java.lang.String getRoomkind(){
		return this.roomkind;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  房间类型
	 */
	public void setRoomkind(java.lang.String roomkind){
		this.roomkind = roomkind;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  所需床位
	 */
	@Column(name ="NEEDBEDS",nullable=true,precision=10,scale=0)
	public java.lang.Integer getNeedbeds(){
		return this.needbeds;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  所需床位
	 */
	public void setNeedbeds(java.lang.Integer needbeds){
		this.needbeds = needbeds;
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
	 *@return: java.lang.String  预定状态
	 */
	@Column(name ="PREDETERMINEDSTATE",nullable=true,length=12)
	public java.lang.String getPredeterminedstate(){
		return this.predeterminedstate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预定状态
	 */
	public void setPredeterminedstate(java.lang.String predeterminedstate){
		this.predeterminedstate = predeterminedstate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  房间ID
	 */
	@Column(name ="ROOMID",nullable=true,length=36)
	public java.lang.String getRoomid(){
		return this.roomid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  房间ID
	 */
	public void setRoomid(java.lang.String roomid){
		this.roomid = roomid;
	}
}

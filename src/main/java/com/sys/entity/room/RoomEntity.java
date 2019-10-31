package com.sys.entity.room;

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
 * @Description: 客房基本信息
 * @author zhangdaihao
 * @date 2015-12-29 15:09:47
 * @version V1.0   
 *
 */
@Entity
@Table(name = "room", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class RoomEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**房间号*/
	private java.lang.String roomNumber;
	/**房间类型*/
	private java.lang.String roomKind;
	/**备注*/
	private java.lang.Integer beds;
	/**剩余床位*/
	private java.lang.Integer restbeds;
	/**备注*/
	private java.lang.String remark;
	
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
	 *@return: java.lang.String  房间号
	 */
	@Column(name ="ROOM_NUMBER",nullable=true,length=20)
	public java.lang.String getRoomNumber(){
		return this.roomNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  房间号
	 */
	public void setRoomNumber(java.lang.String roomNumber){
		this.roomNumber = roomNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  房间类型
	 */
	@Column(name ="ROOM_KIND",nullable=true,length=4)
	public java.lang.String getRoomKind(){
		return this.roomKind;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  房间类型
	 */
	public void setRoomKind(java.lang.String roomKind){
		this.roomKind = roomKind;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=100)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}

	@Column(name ="BEDS",nullable=true,length=3)
	public java.lang.Integer getBeds() {
		return beds;
	}

	public void setBeds(java.lang.Integer beds) {
		this.beds = beds;
	}

	@Column(name ="RESTBEDS",nullable=true,length=3)
	public java.lang.Integer getRestbeds() {
		return restbeds;
	}

	public void setRestbeds(java.lang.Integer restbeds) {
		this.restbeds = restbeds;
	}
}

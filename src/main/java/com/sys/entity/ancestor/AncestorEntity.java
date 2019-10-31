package com.sys.entity.ancestor;

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
 * @Description: 先人表
 * @author zhangdaihao
 * @date 2015-11-25 15:49:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ancestor", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class AncestorEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**称呼*/
	private java.lang.String called;
	/**姓名*/
	private java.lang.String name;
	/**做法事的人ID*/
	private java.lang.String ritualid;
	/**药师诞的ID*/
	private java.lang.String pharmacistbirthid;
	
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
	 *@return: java.lang.String  称呼
	 */
	@Column(name ="CALLED",nullable=true,length=10)
	public java.lang.String getCalled(){
		return this.called;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  称呼
	 */
	public void setCalled(java.lang.String called){
		this.called = called;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="NAME",nullable=true,length=20)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  做法事的人ID
	 */
	@Column(name ="RITUALID",nullable=true,length=36)
	public java.lang.String getRitualid(){
		return this.ritualid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  做法事的人ID
	 */
	public void setRitualid(java.lang.String ritualid){
		this.ritualid = ritualid;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  做法事的人ID
	 */
	@Column(name ="PHARMACISTBIRTHID",nullable=true,length=36)
	public java.lang.String getPharmacistbirthid(){
		return this.pharmacistbirthid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  做法事的人ID
	 */
	public void setPharmacistbirthid(java.lang.String pharmacistbirthid){
		this.pharmacistbirthid = pharmacistbirthid;
	}
}

package com.sys.entity.familymember;

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
 * @Description: 家庭成员
 * @author zhangdaihao
 * @date 2015-10-23 10:16:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "familymember", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FamilymemberEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**关系*/
	private java.lang.String relationship;
	/**成员姓名*/
	private java.lang.String membername;
	/**年龄*/
	private java.lang.Integer age;
	/**职业*/
	private java.lang.String occupation;
	/**现住地址*/
	private java.lang.String addressnow;
	/**员工ID*/
	private java.lang.String staffid;
	
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
	 *@return: java.lang.String  关系
	 */
	@Column(name ="RELATIONSHIP",nullable=true,length=10)
	public java.lang.String getRelationship(){
		return this.relationship;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关系
	 */
	public void setRelationship(java.lang.String relationship){
		this.relationship = relationship;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  成员姓名
	 */
	@Column(name ="MEMBERNAME",nullable=true,length=10)
	public java.lang.String getMembername(){
		return this.membername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  成员姓名
	 */
	public void setMembername(java.lang.String membername){
		this.membername = membername;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  年龄
	 */
	@Column(name ="AGE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getAge(){
		return this.age;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  年龄
	 */
	public void setAge(java.lang.Integer age){
		this.age = age;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职业
	 */
	@Column(name ="OCCUPATION",nullable=true,length=11)
	public java.lang.String getOccupation(){
		return this.occupation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职业
	 */
	public void setOccupation(java.lang.String occupation){
		this.occupation = occupation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  现住地址
	 */
	@Column(name ="ADDRESSNOW",nullable=true,length=20)
	public java.lang.String getAddressnow(){
		return this.addressnow;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  现住地址
	 */
	public void setAddressnow(java.lang.String addressnow){
		this.addressnow = addressnow;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  员工ID
	 */
	@Column(name ="STAFFID",nullable=true,length=36)
	public java.lang.String getStaffid(){
		return this.staffid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  员工ID
	 */
	public void setStaffid(java.lang.String staffid){
		this.staffid = staffid;
	}
}

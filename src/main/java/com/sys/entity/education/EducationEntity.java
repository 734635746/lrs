package com.sys.entity.education;

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
 * @Description: 学历
 * @author zhangdaihao
 * @date 2015-10-23 10:16:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "education", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class EducationEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**开始时间*/
	private java.util.Date starttime;
	/**结束时间*/
	private java.util.Date endtime;
	/**专业*/
	private java.lang.String major;
	/**学位*/
	private java.lang.String degree;
	/**是否毕业*/
	private java.lang.String graduation;
	/**证件名称*/
	private java.lang.String certificate;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	@Column(name ="STARTTIME",nullable=true)
	public java.util.Date getStarttime(){
		return this.starttime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStarttime(java.util.Date starttime){
		this.starttime = starttime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	@Column(name ="ENDTIME",nullable=true)
	public java.util.Date getEndtime(){
		return this.endtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndtime(java.util.Date endtime){
		this.endtime = endtime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  专业
	 */
	@Column(name ="MAJOR",nullable=true,length=20)
	public java.lang.String getMajor(){
		return this.major;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  专业
	 */
	public void setMajor(java.lang.String major){
		this.major = major;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学位
	 */
	@Column(name ="DEGREE",nullable=true,length=10)
	public java.lang.String getDegree(){
		return this.degree;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学位
	 */
	public void setDegree(java.lang.String degree){
		this.degree = degree;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否毕业
	 */
	@Column(name ="GRADUATION",nullable=true,length=3)
	public java.lang.String getGraduation(){
		return this.graduation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否毕业
	 */
	public void setGraduation(java.lang.String graduation){
		this.graduation = graduation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  证件名称
	 */
	@Column(name ="CERTIFICATE",nullable=true,length=20)
	public java.lang.String getCertificate(){
		return this.certificate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  证件名称
	 */
	public void setCertificate(java.lang.String certificate){
		this.certificate = certificate;
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

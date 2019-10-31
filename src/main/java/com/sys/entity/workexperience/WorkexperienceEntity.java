package com.sys.entity.workexperience;

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
 * @Description: 工作经历
 * @author zhangdaihao
 * @date 2015-10-23 10:16:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "workexperience", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class WorkexperienceEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**曾工作单位*/
	private java.lang.String workunit;
	/**职位*/
	private java.lang.String position;
	/**到职年月*/
	private java.util.Date arrivaldate;
	/**离职年月*/
	private java.util.Date departuredate;
	/**离职原因*/
	private java.lang.String departurereason;
	/**员工ID*/
	private java.lang.String staffid;
	
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
	 *@return: java.lang.String  曾工作单位
	 */
	@Column(name ="WORKUNIT",nullable=true,length=30)
	public java.lang.String getWorkunit(){
		return this.workunit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  曾工作单位
	 */
	public void setWorkunit(java.lang.String workunit){
		this.workunit = workunit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职位
	 */
	@Column(name ="POSITION",nullable=true,length=30)
	public java.lang.String getPosition(){
		return this.position;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职位
	 */
	public void setPosition(java.lang.String position){
		this.position = position;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到职年月
	 */
	@Column(name ="ARRIVALDATE",nullable=true)
	public java.util.Date getArrivaldate(){
		return this.arrivaldate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到职年月
	 */
	public void setArrivaldate(java.util.Date arrivaldate){
		this.arrivaldate = arrivaldate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  离职年月
	 */
	@Column(name ="DEPARTUREDATE",nullable=true)
	public java.util.Date getDeparturedate(){
		return this.departuredate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  离职年月
	 */
	public void setDeparturedate(java.util.Date departuredate){
		this.departuredate = departuredate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  离职原因
	 */
	@Column(name ="DEPARTUREREASON",nullable=true,length=255)
	public java.lang.String getDeparturereason(){
		return this.departurereason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离职原因
	 */
	public void setDeparturereason(java.lang.String departurereason){
		this.departurereason = departurereason;
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

package com.sys.entity.department;

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
 * @Description: 部门信息
 * @author zhangdaihao
 * @date 2015-10-23 10:16:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "department", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DepartmentEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**部门*/
	private java.lang.String dept;
	/**职位*/
	private java.lang.String job;
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
	 *@return: java.lang.String  部门
	 */
	@Column(name ="DEPT",nullable=true,length=20)
	public java.lang.String getDept(){
		return this.dept;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门
	 */
	public void setDept(java.lang.String dept){
		this.dept = dept;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职位
	 */
	@Column(name ="JOB",nullable=true,length=20)
	public java.lang.String getJob(){
		return this.job;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职位
	 */
	public void setJob(java.lang.String job){
		this.job = job;
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

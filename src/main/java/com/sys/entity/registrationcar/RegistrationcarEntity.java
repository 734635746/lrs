package com.sys.entity.registrationcar;

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
 * @Description: 车辆使用登记表
 * @author zhangdaihao
 * @date 2015-10-27 10:11:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "registrationcar", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class RegistrationcarEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**车型*/
	private java.lang.String cartype;
	/**车牌号码*/
	private java.lang.String platenumber;
	/**状态*/
	private java.lang.String carstatus;
	/**用车人*/
	private java.lang.String usingcarpeople;
	/**用车时间*/
	private java.util.Date usingtime;
	/**前往地点*/
	private java.lang.String toplace;
	/**返回时间*/
	private java.util.Date returntime;
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
	 *@return: java.lang.String  车牌号码
	 */
	@Column(name ="PLATENUMBER",nullable=true,length=10)
	public java.lang.String getPlatenumber(){
		return this.platenumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车牌号码
	 */
	public void setPlatenumber(java.lang.String platenumber){
		this.platenumber = platenumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="CARSTATUS",nullable=true,length=10)
	public java.lang.String getCarstatus(){
		return this.carstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setCarstatus(java.lang.String carstatus){
		this.carstatus = carstatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用车人
	 */
	@Column(name ="USINGCARPEOPLE",nullable=true,length=11)
	public java.lang.String getUsingcarpeople(){
		return this.usingcarpeople;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用车人
	 */
	public void setUsingcarpeople(java.lang.String usingcarpeople){
		this.usingcarpeople = usingcarpeople;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  用车时间
	 */
	@Column(name ="USINGTIME",nullable=true)
	public java.util.Date getUsingtime(){
		return this.usingtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  用车时间
	 */
	public void setUsingtime(java.util.Date usingtime){
		this.usingtime = usingtime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  前往地点
	 */
	@Column(name ="TOPLACE",nullable=true,length=20)
	public java.lang.String getToplace(){
		return this.toplace;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  前往地点
	 */
	public void setToplace(java.lang.String toplace){
		this.toplace = toplace;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  返回时间
	 */
	@Column(name ="RETURNTIME",nullable=true)
	public java.util.Date getReturntime(){
		return this.returntime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  返回时间
	 */
	public void setReturntime(java.util.Date returntime){
		this.returntime = returntime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=255)
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

	@Column(name ="CARTYPE",nullable=true,length=20)
	public java.lang.String getCartype() {
		return cartype;
	}

	public void setCartype(java.lang.String cartype) {
		this.cartype = cartype;
	}
}

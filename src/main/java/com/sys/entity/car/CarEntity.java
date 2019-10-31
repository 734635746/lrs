package com.sys.entity.car;

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
 * @Description: 车辆信息
 * @author zhangdaihao
 * @date 2015-12-28 19:37:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "car", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class CarEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**车辆名字*/
	private java.lang.String carname;
	/**车牌号*/
	private java.lang.String carnumber;
	/**车型*/
	private java.lang.String cartype;
	/**买车的时间*/
	private java.util.Date buytime;
	
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
	 *@return: java.lang.String  车辆名字
	 */
	@Column(name ="CARNAME",nullable=true,length=30)
	public java.lang.String getCarname(){
		return this.carname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车辆名字
	 */
	public void setCarname(java.lang.String carname){
		this.carname = carname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车牌号
	 */
	@Column(name ="CARNUMBER",nullable=true,length=10)
	public java.lang.String getCarnumber(){
		return this.carnumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车牌号
	 */
	public void setCarnumber(java.lang.String carnumber){
		this.carnumber = carnumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车型
	 */
	@Column(name ="CARTYPE",nullable=true,length=10)
	public java.lang.String getCartype(){
		return this.cartype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车型
	 */
	public void setCartype(java.lang.String cartype){
		this.cartype = cartype;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  买车的时间
	 */
	@Column(name ="BUYTIME",nullable=true)
	public java.util.Date getBuytime(){
		return this.buytime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  买车的时间
	 */
	public void setBuytime(java.util.Date buytime){
		this.buytime = buytime;
	}
}

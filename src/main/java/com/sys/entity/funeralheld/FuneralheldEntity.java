package com.sys.entity.funeralheld;

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
 * @Description: 法事举行日期安排
 * @author zhangdaihao
 * @date 2016-11-02 09:05:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "funeralheld", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FuneralheldEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**法事类型*/
	private java.lang.String ritualtype;
	/**举行年份*/
	private java.lang.String holdYear;
	/**农历开始日期*/
	private java.lang.String holdDate;
	/**农历结束日期*/
	private java.lang.String endDate;
	/**持续天数*/
	private java.lang.Integer continuouDays;
	
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
	 *@return: java.lang.String  法事类型
	 */
	@Column(name ="RITUALTYPE",nullable=true,length=50)
	public java.lang.String getRitualtype(){
		return this.ritualtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法事类型
	 */
	public void setRitualtype(java.lang.String ritualtype){
		this.ritualtype = ritualtype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  举行年份
	 */
	@Column(name ="HOLD_YEAR",nullable=true,length=11)
	public java.lang.String getHoldYear(){
		return this.holdYear;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  举行年份
	 */
	public void setHoldYear(java.lang.String holdYear){
		this.holdYear = holdYear;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  农历日期
	 */
	@Column(name ="HOLD_DATE",nullable=true,length=30)
	public java.lang.String getHoldDate(){
		return this.holdDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  农历日期
	 */
	public void setHoldDate(java.lang.String holdDate){
		this.holdDate = holdDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  持续天数
	 */
	@Column(name ="CONTINUOU_DAYS",nullable=true,precision=10,scale=0)
	public java.lang.Integer getContinuouDays(){
		return this.continuouDays;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  持续天数
	 */
	public void setContinuouDays(java.lang.Integer continuouDays){
		this.continuouDays = continuouDays;
	}

	public java.lang.String getEndDate() {
		return endDate;
	}

	public void setEndDate(java.lang.String endDate) {
		this.endDate = endDate;
	}
}

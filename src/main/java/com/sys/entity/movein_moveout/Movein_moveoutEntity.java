package com.sys.entity.movein_moveout;

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
 * @Description: 迁入迁出记录
 * @author zhangdaihao
 * @date 2016-03-21 10:01:59
 * @version V1.0   
 *
 */
@Entity
@Table(name = "movein_moveout", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class Movein_moveoutEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**moveintime*/
	private java.util.Date moveintime;
	/**迁出时间*/
	private java.util.Date moveouttime;
	/**登记时间*/
	private java.lang.String registertime;
	/**经手人*/
	private java.lang.String a_handler;
	/**皈依弟子姓名*/
	private java.lang.String disciplename;
	/**迁入迁出原因*/
	private java.lang.String reason;
	/**皈依弟子的ID*/
	private java.lang.String discipleid;
	
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  moveintime
	 */
	@Column(name ="MOVEINTIME",nullable=true)
	public java.util.Date getMoveintime(){
		return this.moveintime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  moveintime
	 */
	public void setMoveintime(java.util.Date moveintime){
		this.moveintime = moveintime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  迁出时间
	 */
	@Column(name ="MOVEOUTTIME",nullable=true)
	public java.util.Date getMoveouttime(){
		return this.moveouttime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  迁出时间
	 */
	public void setMoveouttime(java.util.Date moveouttime){
		this.moveouttime = moveouttime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登记时间
	 */
	@Column(name ="REGISTERTIME",nullable=true,length=10)
	public java.lang.String getRegistertime(){
		return this.registertime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登记时间
	 */
	public void setRegistertime(java.lang.String registertime){
		this.registertime = registertime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经手人
	 */
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  皈依弟子姓名
	 */
	@Column(name ="DISCIPLENAME",nullable=true,length=12)
	public java.lang.String getDisciplename(){
		return this.disciplename;
	}

	@Column(name ="A_HANDLER",nullable=true,length=20)
	public java.lang.String getA_handler() {
		return a_handler;
	}

	public void setA_handler(java.lang.String a_handler) {
		this.a_handler = a_handler;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  皈依弟子姓名
	 */
	public void setDisciplename(java.lang.String disciplename){
		this.disciplename = disciplename;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  迁入迁出原因
	 */
	@Column(name ="REASON",nullable=true,length=1000)
	public java.lang.String getReason(){
		return this.reason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  迁入迁出原因
	 */
	public void setReason(java.lang.String reason){
		this.reason = reason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  皈依弟子的ID
	 */
	@Column(name ="DISCIPLEID",nullable=true,length=36)
	public java.lang.String getDiscipleid(){
		return this.discipleid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  皈依弟子的ID
	 */
	public void setDiscipleid(java.lang.String discipleid){
		this.discipleid = discipleid;
	}
}

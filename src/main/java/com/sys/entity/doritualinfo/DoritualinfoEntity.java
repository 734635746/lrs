package com.sys.entity.doritualinfo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 做法事人的基本信息
 * @author zhangdaihao
 * @date 2015-11-25 15:49:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "doritualinfo", schema = "")
@SuppressWarnings("serial")
public class DoritualinfoEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**姓名*/
	private java.lang.String rname;
	/**手机号码1*/
	private java.lang.String phonenumber1;
	/**手机号码2*/
	private java.lang.String phonenumber2;
	/**居住地址*/
	private java.lang.String address;
	
	private java.lang.String registertime;
	
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
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="RNAME",nullable=true,length=20)
	public java.lang.String getRname(){
		return this.rname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setRname(java.lang.String rname){
		this.rname = rname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号码1
	 */
	@Column(name ="PHONENUMBER1",nullable=true,length=11)
	public java.lang.String getPhonenumber1(){
		return this.phonenumber1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号码1
	 */
	public void setPhonenumber1(java.lang.String phonenumber1){
		this.phonenumber1 = phonenumber1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号码2
	 */
	@Column(name ="PHONENUMBER2",nullable=true,length=11)
	public java.lang.String getPhonenumber2(){
		return this.phonenumber2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号码2
	 */
	public void setPhonenumber2(java.lang.String phonenumber2){
		this.phonenumber2 = phonenumber2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  居住地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=30)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  居住地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}

	
	@Column(name ="REGISTERTIME",nullable=true,length=20)
	public java.lang.String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(java.lang.String registertime) {
		this.registertime = registertime;
	}
}

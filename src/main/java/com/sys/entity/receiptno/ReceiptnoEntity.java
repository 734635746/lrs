package com.sys.entity.receiptno;

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
 * @Description: 收据编号管理
 * @author zhangdaihao
 * @date 2016-11-07 16:04:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "receiptno", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ReceiptnoEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**year*/
	private java.lang.Integer year;
	/**mincount*/
	private java.lang.Integer mincount;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	
	@Id
	@GeneratedValue
	@Column(name ="ID",nullable=false,precision=10,scale=0)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  year
	 */
	@Column(name ="YEAR",nullable=true,length=255)
	public java.lang.Integer getYear(){
		return this.year;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  year
	 */
	public void setYear(java.lang.Integer year){
		this.year = year;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  mincount
	 */
	@Column(name ="MINCOUNT",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMincount(){
		return this.mincount;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  mincount
	 */
	public void setMincount(java.lang.Integer mincount){
		this.mincount = mincount;
	}
}

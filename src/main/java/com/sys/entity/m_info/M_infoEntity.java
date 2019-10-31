package com.sys.entity.m_info;

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
 * @Description: 牌位信息
 * @author zhangdaihao
 * @date 2017-03-09 09:51:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "m_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class M_infoEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**buildingName*/
	private java.lang.String buildingName;
	/**danName*/
	private java.lang.String danName;
	/**Row*/
	private java.lang.Integer Row;
	/**Column*/
	private java.lang.Integer Column;
	
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
	 *@return: java.lang.String  buildingName
	 */
	@Column(name ="BUILDING_NAME",nullable=true,length=255)
	public java.lang.String getBuildingName(){
		return this.buildingName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  buildingName
	 */
	public void setBuildingName(java.lang.String buildingName){
		this.buildingName = buildingName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  danName
	 */
	@Column(name ="DAN_NAME",nullable=true,length=255)
	public java.lang.String getDanName(){
		return this.danName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  danName
	 */
	public void setDanName(java.lang.String danName){
		this.danName = danName;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  Row
	 */
	@Column(name ="_ROW",nullable=true,precision=10,scale=0)
	public java.lang.Integer getRow(){
		return this.Row;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  Row
	 */
	public void setRow(java.lang.Integer Row){
		this.Row = Row;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  Column
	 */
	@Column(name ="_COLUMN",nullable=true,precision=10,scale=0)
	public java.lang.Integer getColumn(){
		return this.Column;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  Column
	 */
	public void setColumn(java.lang.Integer Column){
		this.Column = Column;
	}
}

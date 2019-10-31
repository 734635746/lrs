package com.sys.page.memorial_tablet;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

import com.sys.entity.namelist.NamelistEntity;
import com.sys.entity.linkmanlist.LinkmanlistEntity;

/**   
 * @Title: Entity
 * @Description: 牌位表
 * @author zhangdaihao
 * @date 2016-01-04 15:08:41
 * @version V1.0   
 *
 */
@Entity
@Table(name = "memorial_tablet", schema = "")
@SuppressWarnings("serial")
public class Memorial_tabletPage implements java.io.Serializable {
	/**保存-姓名列表*/
	private List<NamelistEntity> namelistList = new ArrayList<NamelistEntity>();
	public List<NamelistEntity> getNamelistList() {
		return namelistList;
	}
	public void setNamelistList(List<NamelistEntity> namelistList) {
		this.namelistList = namelistList;
	}
	/**保存-联系人列表*/
	private List<LinkmanlistEntity> linkmanlistList = new ArrayList<LinkmanlistEntity>();
	public List<LinkmanlistEntity> getLinkmanlistList() {
		return linkmanlistList;
	}
	public void setLinkmanlistList(List<LinkmanlistEntity> linkmanlistList) {
		this.linkmanlistList = linkmanlistList;
	}


	/**id*/
	private java.lang.String id;
	/**登记时间*/
	private java.util.Date registertime;
	/**编号*/
	private java.lang.String serial;
	/**段位*/
	private java.lang.String dan;
	/**行号*/
	private java.lang.Integer row;
	/**位号*/
	private java.lang.Integer ordernumber;
	/**大小*/
	private java.lang.String size;
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
	 *@return: java.lang.String  编号
	 */
	@Column(name ="SERIAL",nullable=true,length=32)
	public java.lang.String getSerial(){
		return this.serial;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setSerial(java.lang.String serial){
		this.serial = serial;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  段位
	 */
	@Column(name ="DAN",nullable=true,length=5)
	public java.lang.String getDan(){
		return this.dan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  段位
	 */
	public void setDan(java.lang.String dan){
		this.dan = dan;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  行号
	 */
	@Column(name ="ROW",nullable=true,precision=10,scale=0)
	public java.lang.Integer getRow(){
		return this.row;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  行号
	 */
	public void setRow(java.lang.Integer row){
		this.row = row;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  位号
	 */
	@Column(name ="ORDERNUMBER",nullable=true,precision=10,scale=0)
	public java.lang.Integer getOrdernumber(){
		return this.ordernumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  位号
	 */
	public void setOrdernumber(java.lang.Integer ordernumber){
		this.ordernumber = ordernumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  大小
	 */
	@Column(name ="SIZE",nullable=true,length=5)
	public java.lang.String getSize(){
		return this.size;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  大小
	 */
	public void setSize(java.lang.String size){
		this.size = size;
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
	
	@Column(name ="REGISTERTIME",nullable=true)
	public java.util.Date getRegistertime() {
		return registertime;
	}

	public void setRegistertime(java.util.Date registertime) {
		this.registertime = registertime;
	}
}

package com.sys.entity.linkmanlist;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import com.sys.entity.memorial_tablet.Memorial_tabletEntity;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 联系人列表
 * @author zhangdaihao
 * @date 2016-01-04 15:08:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "linkmanlist", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LinkmanlistEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**QQ*/
	private java.lang.String qq;
	/**电子邮件*/
	private java.lang.String email;
	/**手机号*/
	@Excel(exportName="手机号",orderNum="12")
	private java.lang.String mobilephone;
	/**固话*/
	@Excel(exportName="固话",orderNum="13")
	private java.lang.String homephone;
	/**微信号*/
	private java.lang.String wechat;
	/**牌位ID*/
	private java.lang.String tabletId;
	/**地址*/
	@Excel(exportName="地址",orderNum="11")
	private java.lang.String address;
	@Excel(exportName="联系人",orderNum="10")
	private java.lang.String linkman;
	
	private java.lang.String relationship;
	/**牌位主键*/
	private Memorial_tabletEntity memorial_tabletEntity;
	
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
	 *@return: java.lang.String  QQ
	 */
	@Column(name ="QQ",nullable=true,length=16)
	public java.lang.String getQq(){
		return this.qq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  QQ
	 */
	public void setQq(java.lang.String qq){
		this.qq = qq;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电子邮件
	 */
	@Column(name ="EMAIL",nullable=true,length=36)
	public java.lang.String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电子邮件
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号
	 */
	@Column(name ="MOBILEPHONE",nullable=true,length=11)
	public java.lang.String getMobilephone(){
		return this.mobilephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号
	 */
	public void setMobilephone(java.lang.String mobilephone){
		this.mobilephone = mobilephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  固话
	 */
	@Column(name ="HOMEPHONE",nullable=true,length=14)
	public java.lang.String getHomephone(){
		return this.homephone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  固话
	 */
	public void setHomephone(java.lang.String homephone){
		this.homephone = homephone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信号
	 */
	@Column(name ="WECHAT",nullable=true,length=36)
	public java.lang.String getWechat(){
		return this.wechat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信号
	 */
	public void setWechat(java.lang.String wechat){
		this.wechat = wechat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  牌位ID
	 */
	@Column(name ="TABLET_ID",nullable=true,length=36)
	public java.lang.String getTabletId(){
		return this.tabletId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  牌位ID
	 */
	public void setTabletId(java.lang.String tabletId){
		this.tabletId = tabletId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=36)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TABLETID")
	public Memorial_tabletEntity getMemorial_tabletEntity() {
		return memorial_tabletEntity;
	}

	public void setMemorial_tabletEntity(Memorial_tabletEntity memorial_tabletEntity) {
		this.memorial_tabletEntity = memorial_tabletEntity;
	}

	@Column(name ="LINKMAN",nullable=true,length=20)
	public java.lang.String getLinkman() {
		return linkman;
	}

	public void setLinkman(java.lang.String linkman) {
		this.linkman = linkman;
	}

	public java.lang.String getRelationship() {
		return relationship;
	}

	public void setRelationship(java.lang.String relationship) {
		this.relationship = relationship;
	}
}

package com.sys.entity.namelist;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import com.sys.entity.memorial_tablet.Memorial_tabletEntity;

/**   
 * @Title: Entity
 * @Description: 姓名列表
 * @author zhangdaihao
 * @date 2016-01-04 15:08:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "namelist", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class NamelistEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**姓名*/
	@Excel(exportName="姓名")
	private java.lang.String name;
	/**状态*/
	@Excel(exportName="状态")
	private java.lang.String state;
	
	/*关系*/
	@Excel(exportName="关系")
	private java.lang.String relation;
	
	/**牌位的ID*/
	private java.lang.String tabletId;
	
	/**牌位主键*/
	private Memorial_tabletEntity memorial_tabletEntity;
	
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
	@Column(name ="NAME",nullable=true,length=20)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATE",nullable=true,length=2)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关系
	 */
	@Column(name ="RELATION",nullable=true,length=5)
	public java.lang.String getRelation() {
		return relation;
	}

	public void setRelation(java.lang.String relation) {
		this.relation = relation;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  牌位的ID
	 */
	@Column(name ="TABLET_ID",nullable=true)
	public java.lang.String getTabletId(){
		return this.tabletId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  牌位的ID
	 */
	public void setTabletId(java.lang.String tabletId){
		this.tabletId = tabletId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TABLETID")
	public Memorial_tabletEntity getMemorial_tabletEntity() {
		return memorial_tabletEntity;
	}

	public void setMemorial_tabletEntity(Memorial_tabletEntity memorial_tabletEntity) {
		this.memorial_tabletEntity = memorial_tabletEntity;
	}
}

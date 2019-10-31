package com.sys.entity.memorial_tablet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import com.sys.entity.linkmanlist.LinkmanlistEntity;
import com.sys.entity.namelist.NamelistEntity;

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
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
@ExcelTarget(id="memorial_tabletEntity")
public class Memorial_tabletEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**登记时间*/
	@Excel(exportName="登记日期",orderNum="1",needMerge=true,importFormat="yyyy-MM-dd HH:mm:ss")
	private java.lang.String registertime;
	/**编号*/
	@Excel(exportName="编号",orderNum="2",needMerge=true)
	private java.lang.String serial;
	/**楼名*/
	@Excel(exportName="楼名",orderNum="3",needMerge=true)
	private java.lang.String buildingName;
	/**段位*/
	@Excel(exportName="段号",orderNum="4",needMerge=true)
	private java.lang.String dan;
	/**行号*/
	@Excel(exportName="行号",orderNum="5",needMerge=true,importConvertSign=1)
	private java.lang.Integer row;
	/**位号*/
	@Excel(exportName="位号",orderNum="6",needMerge=true,importConvertSign=1)
	private java.lang.Integer ordernumber;
	
	private java.lang.String begintime;
	
	private java.lang.String endtime;
	
	private java.lang.String comforttime;
	/**大小*/
	/*@Excel(exportName="大小",orderNum="5",needMerge=true)*/
	private java.lang.String size;
	/**备注*/
	@Excel(exportName="备注",orderNum="14",needMerge=true)
	private java.lang.String remark;
	
	@ExcelCollection(exportName="姓名列表",orderNum="7")
	private List<NamelistEntity> namelistEntityList;
	
	@ExcelCollection(exportName="联系人列表",orderNum="10")
	private List<LinkmanlistEntity> linkmanlistEntityList;
	
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

	@OneToMany(mappedBy="tabletId",cascade=CascadeType.REMOVE)
	public List<NamelistEntity> getNamelistEntityList() {
		return namelistEntityList;
	}

	public void setNamelistEntityList(List<NamelistEntity> namelistEntityList) {
		this.namelistEntityList = namelistEntityList;
	}

	@OneToMany(mappedBy="tabletId",cascade=CascadeType.REMOVE)
	public List<LinkmanlistEntity> getLinkmanlistEntityList() {
		return linkmanlistEntityList;
	}

	public void setLinkmanlistEntityList(
			List<LinkmanlistEntity> linkmanlistEntityList) {
		this.linkmanlistEntityList = linkmanlistEntityList;
	}

	@Column(name ="REGISTERTIME",nullable=true)
	public java.lang.String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(java.lang.String registertime) {
		this.registertime = registertime;
	}
	
	/*public void convertSetSerial(Integer serial){
		this.serial = Integer.valueOf(serial);
	}*/
	
	public void convertSetRow(Integer row){
		this.row = Integer.valueOf(row);
	}
	
	public void convertSetOrdernumber(Integer ordernumber){
		this.ordernumber = Integer.valueOf(ordernumber);
	}

	@Column(name ="BUILDING_NAME",nullable=true,length=255)
	public java.lang.String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(java.lang.String buildingName) {
		this.buildingName = buildingName;
	}

	public java.lang.String getBegintime() {
		return begintime;
	}

	public void setBegintime(java.lang.String begintime) {
		this.begintime = begintime;
	}

	public java.lang.String getEndtime() {
		return endtime;
	}

	public void setEndtime(java.lang.String endtime) {
		this.endtime = endtime;
	}

	public java.lang.String getComforttime() {
		return comforttime;
	}

	public void setComforttime(java.lang.String comforttime) {
		this.comforttime = comforttime;
	}

	

	
	
	
}

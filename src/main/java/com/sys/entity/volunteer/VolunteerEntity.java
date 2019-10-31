package com.sys.entity.volunteer;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 义工登记信息
 * @author zhangdaihao
 * @date 2015-10-30 15:09:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "volunteer", schema = "")
@SuppressWarnings("serial")
@ExcelTarget(id="volunteerEntity")
public class VolunteerEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**义工姓名*/
	@Excel(exportName="姓名",orderNum="1",needMerge=true)
	private java.lang.String name;
	/**性别*/
	@Excel(exportName="性别",orderNum="4",needMerge=true)
	private java.lang.String sex;
	/**出生日期*/
	@Excel(exportName="出生日期",orderNum="3",needMerge=true,importFormat="yyyy-MM-dd")
	private java.util.Date birthday;
	/**身份证*/
	@Excel(exportName="身份证",orderNum="2",needMerge=true)
	private java.lang.String certification;
	/**籍贯*/
	@Excel(exportName="籍贯",orderNum="6",needMerge=true)
	private java.lang.String nativeplace;
	/**手机电话*/
	@Excel(exportName="手机号码",orderNum="5",needMerge=true)
	private java.lang.String phonenumber;
	/**固话*/
	@Excel(exportName="固话",orderNum="7",needMerge=true)
	private java.lang.String homenumber;
	/**专业技能*/
	@Excel(exportName="专业技能",orderNum="8",needMerge=true)
	private java.lang.String professionalskills;
	/**最高学历*/
	@Excel(exportName="学历",orderNum="9",needMerge=true)
	private java.lang.String higheducation;
	/**政治面貌*/
	@Excel(exportName="政治面貌",orderNum="10",needMerge=true)
	private java.lang.String politicalstatus;
	/**平均分*/
	private java.lang.Float average;
	/**参加次数*/
	private java.lang.Integer joincount;
	
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
	 *@return: java.lang.String  义工姓名
	 */
	@Column(name ="NAME",nullable=true,length=11)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  义工姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="SEX",nullable=true,length=3)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  出生日期
	 */
	@Column(name ="BIRTHDAY",nullable=true)
	public java.util.Date getBirthday(){
		return this.birthday;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  出生日期
	 */
	public void setBirthday(java.util.Date birthday){
		this.birthday = birthday;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证
	 */
	@Column(name ="CERTIFICATION",nullable=true,length=20)
	public java.lang.String getCertification(){
		return this.certification;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证
	 */
	public void setCertification(java.lang.String certification){
		this.certification = certification;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  籍贯
	 */
	@Column(name ="NATIVEPLACE",nullable=true,length=11)
	public java.lang.String getNativeplace(){
		return this.nativeplace;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  籍贯
	 */
	public void setNativeplace(java.lang.String nativeplace){
		this.nativeplace = nativeplace;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机电话
	 */
	@Column(name ="PHONENUMBER",nullable=true,length=11)
	public java.lang.String getPhonenumber(){
		return this.phonenumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机电话
	 */
	public void setPhonenumber(java.lang.String phonenumber){
		this.phonenumber = phonenumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  固话
	 */
	@Column(name ="HOMENUMBER",nullable=true,length=14)
	public java.lang.String getHomenumber(){
		return this.homenumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  固话
	 */
	public void setHomenumber(java.lang.String homenumber){
		this.homenumber = homenumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  专业技能
	 */
	@Column(name ="PROFESSIONALSKILLS",nullable=true,length=20)
	public java.lang.String getProfessionalskills(){
		return this.professionalskills;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  专业技能
	 */
	public void setProfessionalskills(java.lang.String professionalskills){
		this.professionalskills = professionalskills;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最高学历
	 */
	@Column(name ="HIGHEDUCATION",nullable=true,length=10)
	public java.lang.String getHigheducation(){
		return this.higheducation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最高学历
	 */
	public void setHigheducation(java.lang.String higheducation){
		this.higheducation = higheducation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  政治面貌
	 */
	@Column(name ="POLITICALSTATUS",nullable=true,length=10)
	public java.lang.String getPoliticalstatus(){
		return this.politicalstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  政治面貌
	 */
	public void setPoliticalstatus(java.lang.String politicalstatus){
		this.politicalstatus = politicalstatus;
	}

	@Column(name ="JOINTCOUNT",nullable=true)
	public java.lang.Integer getJoincount() {
		return joincount;
	}

	public void setJoincount(java.lang.Integer joincount) {
		this.joincount = joincount;
	}

	@Column(name ="AVERAGE",nullable=true)
	public java.lang.Float getAverage() {
		return average;
	}

	public void setAverage(java.lang.Float average) {
		this.average = average;
	}
	
}

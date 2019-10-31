package com.sys.page.volunteer;

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

import com.sys.entity.volunteerevent.VolunteereventEntity;

/**   
 * @Title: Entity
 * @Description: 义工登记信息
 * @author zhangdaihao
 * @date 2015-10-30 15:09:15
 * @version V1.0   
 *
 */
@Entity
@Table(name = "volunteer", schema = "")
@SuppressWarnings("serial")
public class VolunteerPage implements java.io.Serializable {
	/**保存-义工事务登记*/
	private List<VolunteereventEntity> volunteereventList = new ArrayList<VolunteereventEntity>();
	public List<VolunteereventEntity> getVolunteereventList() {
		return volunteereventList;
	}
	public void setVolunteereventList(List<VolunteereventEntity> volunteereventList) {
		this.volunteereventList = volunteereventList;
	}


	/**主键*/
	private java.lang.String id;
	/**义工姓名*/
	private java.lang.String name;
	/**性别*/
	private java.lang.String sex;
	/**出生日期*/
	private java.util.Date birthday;
	/**身份证*/
	private java.lang.String certification;
	/**籍贯*/
	private java.lang.String nativeplace;
	/**手机电话*/
	private java.lang.String phonenumber;
	/**固话*/
	private java.lang.String homenumber;
	/**专业技能*/
	private java.lang.String professionalskills;
	/**最高学历*/
	private java.lang.String higheducation;
	/**政治面貌*/
	private java.lang.String politicalstatus;
	/**平均分*/
	private java.lang.Integer average;
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
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  平均分
	 */
	@Column(name ="AVERAGE",nullable=true,precision=10,scale=0)
	public java.lang.Integer getAverage(){
		return this.average;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  平均分
	 */
	public void setAverage(java.lang.Integer average){
		this.average = average;
	}
	
	@Column(name ="JOINTCOUNT",nullable=true)
	public java.lang.Integer getJoincount() {
		return joincount;
	}

	public void setJoincount(java.lang.Integer joincount) {
		this.joincount = joincount;
	}
}

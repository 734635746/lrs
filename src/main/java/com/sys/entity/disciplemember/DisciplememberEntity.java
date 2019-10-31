package com.sys.entity.disciplemember;

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
 * @Description: 皈依弟子档案表
 * @author zhangdaihao
 * @date 2015-10-24 09:37:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "disciplemember", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DisciplememberEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**姓名*/
	private java.lang.String disciplename;
	/**法名*/
	private java.lang.String dharmaname;
	/**籍贯*/
	private java.lang.String origin;
	/**性别*/
	private java.lang.String sex;
	/**学历*/
	private java.lang.String education;
	/**学科*/
	private java.lang.String subject;
	/**职业*/
	private java.lang.String profession;
	/**职称*/
	private java.lang.String professiontitle;
	/**爱好*/
	private java.lang.String hobby;
	/**特长*/
	private java.lang.String speciality;
	/**手机电话*/
	private java.lang.String phonenumber;
	/**固话*/
	private java.lang.String homephone;
	/**联系地址*/
	private java.lang.String address;
	/**皈依原因*/
	private java.lang.String disciplereason;
	/**皈依原因*/
	private java.lang.Integer status;
	
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
	@Column(name ="DISCIPLENAME",nullable=true,length=12)
	public java.lang.String getDisciplename(){
		return this.disciplename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setDisciplename(java.lang.String disciplename){
		this.disciplename = disciplename;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  法名
	 */
	@Column(name ="DHARMANAME",nullable=true,length=12)
	public java.lang.String getDharmaname(){
		return this.dharmaname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法名
	 */
	public void setDharmaname(java.lang.String dharmaname){
		this.dharmaname = dharmaname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  籍贯
	 */
	@Column(name ="ORIGIN",nullable=true,length=10)
	public java.lang.String getOrigin(){
		return this.origin;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  籍贯
	 */
	public void setOrigin(java.lang.String origin){
		this.origin = origin;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学历
	 */
	@Column(name ="EDUCATION",nullable=true,length=11)
	public java.lang.String getEducation(){
		return this.education;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学历
	 */
	public void setEducation(java.lang.String education){
		this.education = education;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学科
	 */
	@Column(name ="SUBJECT",nullable=true,length=11)
	public java.lang.String getSubject(){
		return this.subject;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学科
	 */
	public void setSubject(java.lang.String subject){
		this.subject = subject;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职业
	 */
	@Column(name ="PROFESSION",nullable=true,length=11)
	public java.lang.String getProfession(){
		return this.profession;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职业
	 */
	public void setProfession(java.lang.String profession){
		this.profession = profession;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职称
	 */
	@Column(name ="PROFESSIONTITLE",nullable=true,length=11)
	public java.lang.String getProfessiontitle(){
		return this.professiontitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职称
	 */
	public void setProfessiontitle(java.lang.String professiontitle){
		this.professiontitle = professiontitle;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  爱好
	 */
	@Column(name ="HOBBY",nullable=true,length=20)
	public java.lang.String getHobby(){
		return this.hobby;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  爱好
	 */
	public void setHobby(java.lang.String hobby){
		this.hobby = hobby;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  特长
	 */
	@Column(name ="SPECIALITY",nullable=true,length=20)
	public java.lang.String getSpeciality(){
		return this.speciality;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  特长
	 */
	public void setSpeciality(java.lang.String speciality){
		this.speciality = speciality;
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
	@Column(name ="HOMEPHONE",nullable=true,length=13)
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
	 *@return: java.lang.String  联系地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=20)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  皈依原因
	 */
	@Column(name ="DISCIPLEREASON",nullable=true,length=255)
	public java.lang.String getDisciplereason(){
		return this.disciplereason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  皈依原因
	 */
	public void setDisciplereason(java.lang.String disciplereason){
		this.disciplereason = disciplereason;
	}

	@Column(name ="STATUS",nullable=true,length=2)
	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
}

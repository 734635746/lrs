package com.sys.entity.pravrajanamember;

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
 * @Description: 出家众人档案表
 * @author zhangdaihao
 * @date 2015-10-24 10:33:23
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pravrajanamember", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class PravrajanamemberEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**俗名*/
	private java.lang.String pravrajananame;
	/**性别*/
	private java.lang.String pravrajanasex;
	/**出生日期*/
	private java.util.Date birthday;
	/**法名*/
	private java.lang.String dharmaname;
	/**籍贯*/
	private java.lang.String origin;
	/**民族*/
	private java.lang.String nation;
	/**法号*/
	private java.lang.String religiousname;
	/**健康情况*/
	private java.lang.String healthstatus;
	/**来寺时间*/
	private java.util.Date arrivetime;
	/**文化程度*/
	private java.lang.String education;
	/**剃度寺庙*/
	private java.lang.String tonsuretemple;
	/**剃度师父*/
	private java.lang.String tonsuremaster;
	/**联系电话*/
	private java.lang.String phonenumber;
	/**介绍人*/
	private java.lang.String introducer;
	/**身份证*/
	private java.lang.String identification;
	/**户口所在地*/
	private java.lang.String registeredresidence;
	/**户口所在地*/
	private java.lang.String departid;
	
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
	 *@return: java.lang.String  俗名
	 */
	@Column(name ="PRAVRAJANANAME",nullable=true,length=11)
	public java.lang.String getPravrajananame(){
		return this.pravrajananame;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  俗名
	 */
	public void setPravrajananame(java.lang.String pravrajananame){
		this.pravrajananame = pravrajananame;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="PRAVRAJANASEX",nullable=true,length=3)
	public java.lang.String getPravrajanasex(){
		return this.pravrajanasex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setPravrajanasex(java.lang.String pravrajanasex){
		this.pravrajanasex = pravrajanasex;
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
	 *@return: java.lang.String  法名
	 */
	@Column(name ="DHARMANAME",nullable=true,length=11)
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
	@Column(name ="ORIGIN",nullable=true,length=11)
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
	 *@return: java.lang.String  民族
	 */
	@Column(name ="NATION",nullable=true,length=11)
	public java.lang.String getNation(){
		return this.nation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  民族
	 */
	public void setNation(java.lang.String nation){
		this.nation = nation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  法号
	 */
	@Column(name ="RELIGIOUSNAME",nullable=true,length=11)
	public java.lang.String getReligiousname(){
		return this.religiousname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法号
	 */
	public void setReligiousname(java.lang.String religiousname){
		this.religiousname = religiousname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  健康情况
	 */
	@Column(name ="HEALTHSTATUS",nullable=true,length=3)
	public java.lang.String getHealthstatus(){
		return this.healthstatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  健康情况
	 */
	public void setHealthstatus(java.lang.String healthstatus){
		this.healthstatus = healthstatus;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  来寺时间
	 */
	@Column(name ="ARRIVETIME",nullable=true)
	public java.util.Date getArrivetime(){
		return this.arrivetime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  来寺时间
	 */
	public void setArrivetime(java.util.Date arrivetime){
		this.arrivetime = arrivetime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文化程度
	 */
	@Column(name ="EDUCATION",nullable=true,length=6)
	public java.lang.String getEducation(){
		return this.education;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文化程度
	 */
	public void setEducation(java.lang.String education){
		this.education = education;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  剃度寺庙
	 */
	@Column(name ="TONSURETEMPLE",nullable=true,length=16)
	public java.lang.String getTonsuretemple(){
		return this.tonsuretemple;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  剃度寺庙
	 */
	public void setTonsuretemple(java.lang.String tonsuretemple){
		this.tonsuretemple = tonsuretemple;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  剃度师父
	 */
	@Column(name ="TONSUREMASTER",nullable=true,length=11)
	public java.lang.String getTonsuremaster(){
		return this.tonsuremaster;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  剃度师父
	 */
	public void setTonsuremaster(java.lang.String tonsuremaster){
		this.tonsuremaster = tonsuremaster;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系电话
	 */
	@Column(name ="PHONENUMBER",nullable=true,length=11)
	public java.lang.String getPhonenumber(){
		return this.phonenumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系电话
	 */
	public void setPhonenumber(java.lang.String phonenumber){
		this.phonenumber = phonenumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  介绍人
	 */
	@Column(name ="INTRODUCER",nullable=true,length=11)
	public java.lang.String getIntroducer(){
		return this.introducer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  介绍人
	 */
	public void setIntroducer(java.lang.String introducer){
		this.introducer = introducer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证
	 */
	@Column(name ="IDENTIFICATION",nullable=true,length=20)
	public java.lang.String getIdentification(){
		return this.identification;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证
	 */
	public void setIdentification(java.lang.String identification){
		this.identification = identification;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  户口所在地
	 */
	@Column(name ="REGISTEREDRESIDENCE",nullable=true,length=20)
	public java.lang.String getRegisteredresidence(){
		return this.registeredresidence;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  户口所在地
	 */
	public void setRegisteredresidence(java.lang.String registeredresidence){
		this.registeredresidence = registeredresidence;
	}

	public java.lang.String getDepartid() {
		return departid;
	}

	public void setDepartid(java.lang.String departid) {
		this.departid = departid;
	}
}

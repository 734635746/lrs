package com.sys.entity.staff;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 员工信息
 * @author zhangdaihao
 * @date 2015-10-23 10:16:46
 * @version V1.0   
 *
 */
@Entity
@Table(name = "staff", schema = "")
@SuppressWarnings("serial")
public class StaffEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**员工姓名*/
	private java.lang.String staffname;
	/**员工性别*/
	private java.lang.String staffsex;
	/**员工出生年月*/
	private java.util.Date birthday;
	/**籍贯*/
	private java.lang.String nativeplace;
	/**身份证号*/
	private java.lang.String identification;
	/**政治面貌*/
	private java.lang.String status;
	/**婚否*/
	private java.lang.String marrystatus;
	/**电话号码*/
	private java.lang.String phonenumber;
	/**户口地址*/
	private java.lang.String address;
	
	/**受过何培训*/
	private java.lang.String training;
	/**语言能力*/
	private java.lang.String languageability;
	/**电脑操作*/
	private java.lang.String computeropt;
	/**入职日期*/
	private java.util.Date entrydate;
	/**试用期薪金*/
	private java.lang.String probationsalary;
	/**入职部门*/
	private java.lang.String entrydepartment;
	/**试用期满规定时间*/
	private java.lang.String probationtime;
	/**部门ID*/
	private java.lang.String departid;
	/**职位*/
	private java.lang.String position;
	
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
	 *@return: java.lang.String  员工姓名
	 */
	@Column(name ="STAFFNAME",nullable=true,length=11)
	public java.lang.String getStaffname(){
		return this.staffname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  员工姓名
	 */
	public void setStaffname(java.lang.String staffname){
		this.staffname = staffname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  员工性别
	 */
	@Column(name ="STAFFSEX",nullable=true,length=11)
	public java.lang.String getStaffsex(){
		return this.staffsex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  员工性别
	 */
	public void setStaffsex(java.lang.String staffsex){
		this.staffsex = staffsex;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  员工出生年月
	 */
	@Column(name ="BIRTHDAY",nullable=true)
	public java.util.Date getBirthday(){
		return this.birthday;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  员工出生年月
	 */
	public void setBirthday(java.util.Date birthday){
		this.birthday = birthday;
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
	 *@return: java.lang.String  身份证号
	 */
	@Column(name ="IDENTIFICATION",nullable=true,length=20)
	public java.lang.String getIdentification(){
		return this.identification;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号
	 */
	public void setIdentification(java.lang.String identification){
		this.identification = identification;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  政治面貌
	 */
	@Column(name ="STATUS",nullable=true,length=10)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  政治面貌
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  婚否
	 */
	@Column(name ="MARRYSTATUS",nullable=true,length=10)
	public java.lang.String getMarrystatus(){
		return this.marrystatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  婚否
	 */
	public void setMarrystatus(java.lang.String marrystatus){
		this.marrystatus = marrystatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话号码
	 */
	@Column(name ="PHONENUMBER",nullable=true,length=11)
	public java.lang.String getPhonenumber(){
		return this.phonenumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话号码
	 */
	public void setPhonenumber(java.lang.String phonenumber){
		this.phonenumber = phonenumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  户口地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=30)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  户口地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	
	@Column(name ="TRAINING",nullable=true,length=20)
	public java.lang.String getTraining() {
		return training;
	}
	
	public void setTraining(java.lang.String training) {
		this.training = training;
	}
	
	@Column(name ="LANGUAGEABILITY",nullable=true,length=10)
	public java.lang.String getLanguageability() {
		return languageability;
	}
	public void setLanguageability(java.lang.String languageability) {
		this.languageability = languageability;
	}
	
	@Column(name ="COMPUTEROPT",nullable=true,length=10)
	public java.lang.String getComputeropt() {
		return computeropt;
	}
	public void setComputeropt(java.lang.String computeropt) {
		this.computeropt = computeropt;
	}
	
	@Column(name ="ENTRYDATE",nullable=true)
	public java.util.Date getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(java.util.Date entrydate) {
		this.entrydate = entrydate;
	}
	
	@Column(name ="PROBATIONSALARY",nullable=true,length=10)
	public java.lang.String getProbationsalary() {
		return probationsalary;
	}

	public void setProbationsalary(java.lang.String probationsalary) {
		this.probationsalary = probationsalary;
	}
	
	@Column(name ="ENTRYDEPARTMENT",nullable=true,length=20)
	public java.lang.String getEntrydepartment() {
		return entrydepartment;
	}
	public void setEntrydepartment(java.lang.String entrydepartment) {
		this.entrydepartment = entrydepartment;
	}
	
	@Column(name ="PROBATIONTIME",nullable=true,length=10)
	public java.lang.String getProbationtime() {
		return probationtime;
	}
	public void setProbationtime(java.lang.String probationtime) {
		this.probationtime = probationtime;
	}
	
	@Column(name ="DEPARTID",nullable=true,length=36)
	public java.lang.String getDepartid() {
		return departid;
	}

	public void setDepartid(java.lang.String departid) {
		this.departid = departid;
	}
	
	@Column(name ="POSOTION",nullable=true,length=16)
	public java.lang.String getPosition() {
		return position;
	}
	public void setPosition(java.lang.String position) {
		this.position = position;
	}
}

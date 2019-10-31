package com.sys.entity.attendance;

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
 * @Description: 考勤管理
 * @author zhangdaihao
 * @date 2016-10-03 14:54:24
 * @version V1.0   
 *
 */
@Entity
@Table(name = "attendance", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class AttendanceEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**农历日期*/
	private java.lang.String lunardate;
	/**公历日期*/
	private java.lang.String solardate;
	/**姓名*/
	private java.lang.String memberName;
	/**工号*/
	private java.lang.String memberId;
	/**请假原因*/
	private java.lang.String reason;
	/**考勤类型*/
	private java.lang.String attendanceType;
	
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
	 *@return: java.lang.String  农历日期
	 */
	@Column(name ="LUNARDATE",nullable=true,length=20)
	public java.lang.String getLunardate(){
		return this.lunardate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  农历日期
	 */
	public void setLunardate(java.lang.String lunardate){
		this.lunardate = lunardate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公历日期
	 */
	@Column(name ="SOLARDATE",nullable=true,length=20)
	public java.lang.String getSolardate(){
		return this.solardate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公历日期
	 */
	public void setSolardate(java.lang.String solardate){
		this.solardate = solardate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="MEMBER_NAME",nullable=true,length=30)
	public java.lang.String getMemberName(){
		return this.memberName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setMemberName(java.lang.String memberName){
		this.memberName = memberName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工号
	 */
	@Column(name ="MEMBER_ID",nullable=true,length=30)
	public java.lang.String getMemberId(){
		return this.memberId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工号
	 */
	public void setMemberId(java.lang.String memberId){
		this.memberId = memberId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请假原因
	 */
	@Column(name ="REASON",nullable=true,length=20)
	public java.lang.String getReason(){
		return this.reason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请假原因
	 */
	public void setReason(java.lang.String reason){
		this.reason = reason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  考勤类型
	 */
	@Column(name ="ATTENDANCE_TYPE",nullable=true,length=20)
	public java.lang.String getAttendanceType(){
		return this.attendanceType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  考勤类型
	 */
	public void setAttendanceType(java.lang.String attendanceType){
		this.attendanceType = attendanceType;
	}
}

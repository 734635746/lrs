package com.sys.entity.volunteerevent;

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
 * @Description: 义工事务登记
 * @author zhangdaihao
 * @date 2015-10-30 15:09:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "volunteerevent", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class VolunteereventEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**义工ID*/
	private java.lang.String volunteerid;
	/**义工名字*/
	private java.lang.String volunteername;
	/**工作情况*/
	private java.lang.Float workcondition;
	/**工作态度*/
	private java.lang.Float atitude;
	/**事件ID*/
	private java.lang.String eventid;
	/**事件名字*/
	private java.lang.String eventname;
	
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
	 *@return: java.lang.String  义工ID
	 */
	@Column(name ="VOLUNTEERID",nullable=true,length=36)
	public java.lang.String getVolunteerid(){
		return this.volunteerid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  义工ID
	 */
	public void setVolunteerid(java.lang.String volunteerid){
		this.volunteerid = volunteerid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  事件ID
	 */
	@Column(name ="EVENTID",nullable=true,length=36)
	public java.lang.String getEventid(){
		return this.eventid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  事件ID
	 */
	public void setEventid(java.lang.String eventid){
		this.eventid = eventid;
	}
	
	
	@Column(name ="VOLUNTEERNAME",nullable=true)
	public java.lang.String getVolunteername() {
		return volunteername;
	}


	public void setVolunteername(java.lang.String volunteername) {
		this.volunteername = volunteername;
	}

	@Column(name ="EVENTNAME",nullable=true)
	public java.lang.String getEventname() {
		return eventname;
	}

	public void setEventname(java.lang.String eventname) {
		this.eventname = eventname;
	}

	@Column(name ="WORKCONDITION",nullable=true)
	public java.lang.Float getWorkcondition() {
		return workcondition;
	}

	public void setWorkcondition(java.lang.Float workcondition) {
		this.workcondition = workcondition;
	}

	@Column(name ="ATITUDE",nullable=true)
	public java.lang.Float getAtitude() {
		return atitude;
	}

	public void setAtitude(java.lang.Float atitude) {
		this.atitude = atitude;
	}
}

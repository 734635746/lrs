package com.sys.page.event;

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
 * @Description: 事件信息登记
 * @author zhangdaihao
 * @date 2015-10-30 15:09:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "event", schema = "")
@SuppressWarnings("serial")
public class EventPage implements java.io.Serializable {
	/**保存-义工事务登记*/
	private List<VolunteereventEntity> volunteereventList = new ArrayList<VolunteereventEntity>();
	public List<VolunteereventEntity> getVolunteereventList() {
		return volunteereventList;
	}
	public void setVolunteereventList(List<VolunteereventEntity> volunteereventList) {
		this.volunteereventList = volunteereventList;
	}


	/**id*/
	private java.lang.String id;
	/**事件名字*/
	private java.lang.String eventname;
	/**创建人*/
	private java.lang.String creator;
	/**创建时间*/
	private java.util.Date createtime;
	/**是否已经确认**/
	private java.lang.String isconfirm;
	/**是否已经评价**/
	private java.lang.String isestimate;
	
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
	 *@return: java.lang.String  事件名字
	 */
	@Column(name ="EVENTNAME",nullable=true,length=36)
	public java.lang.String getEventname(){
		return this.eventname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  事件名字
	 */
	public void setEventname(java.lang.String eventname){
		this.eventname = eventname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATOR",nullable=true,length=12)
	public java.lang.String getCreator(){
		return this.creator;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreator(java.lang.String creator){
		this.creator = creator;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATETIME",nullable=true)
	public java.util.Date getCreatetime(){
		return this.createtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreatetime(java.util.Date createtime){
		this.createtime = createtime;
	}
	
	@Column(name ="ISCONFIRM",nullable=true)
	public java.lang.String getIsconfirm() {
		return isconfirm;
	}

	public void setIsconfirm(java.lang.String isconfirm) {
		this.isconfirm = isconfirm;
	}

	@Column(name ="ISESTIMATE",nullable=true)
	public java.lang.String getIsestimate() {
		return isestimate;
	}

	public void setIsestimate(java.lang.String isestimate) {
		this.isestimate = isestimate;
	}
}

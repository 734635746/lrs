package com.sys.entity.tmp_stat;

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
 * @Description: 统计收据
 * @author zhangdaihao
 * @date 2016-03-14 08:48:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "tmp_stat", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class Tmp_statEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**法事类型*/
	private java.lang.String ritualtype;
	/**登记时间*/
	private java.lang.String registertime;
	/**登记人*/
	private java.lang.String registrant;
	/**现金*/
	private java.lang.Integer cash;
	/**刷卡*/
	private java.lang.Integer card;
	/**支付宝*/
	private java.lang.Integer alipay;
	/**微信*/
	private java.lang.Integer weixin;
	/**其他*/
	private java.lang.Integer other;
	/**合计*/
	private java.lang.Integer total;
	
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
	 *@return: java.lang.String  法事类型
	 */
	@Column(name ="RITUALTYPE",nullable=true,length=12)
	public java.lang.String getRitualtype(){
		return this.ritualtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法事类型
	 */
	public void setRitualtype(java.lang.String ritualtype){
		this.ritualtype = ritualtype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登记时间
	 */
	@Column(name ="REGISTERTIME",nullable=true,length=40)
	public java.lang.String getRegistertime(){
		return this.registertime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登记时间
	 */
	public void setRegistertime(java.lang.String registertime){
		this.registertime = registertime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登记人
	 */
	@Column(name ="REGISTRANT",nullable=true,length=13)
	public java.lang.String getRegistrant(){
		return this.registrant;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登记人
	 */
	public void setRegistrant(java.lang.String registrant){
		this.registrant = registrant;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  现金
	 */
	@Column(name ="CASH",nullable=true,precision=10,scale=0)
	public java.lang.Integer getCash(){
		return this.cash;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  现金
	 */
	public void setCash(java.lang.Integer cash){
		this.cash = cash;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  刷卡
	 */
	@Column(name ="CARD",nullable=true,precision=10,scale=0)
	public java.lang.Integer getCard(){
		return this.card;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  刷卡
	 */
	public void setCard(java.lang.Integer card){
		this.card = card;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  合计
	 */
	@Column(name ="TOTAL",nullable=true,precision=10,scale=0)
	public java.lang.Integer getTotal(){
		return this.total;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  合计
	 */
	public void setTotal(java.lang.Integer total){
		this.total = total;
	}

	public java.lang.Integer getAlipay() {
		return alipay;
	}

	public void setAlipay(java.lang.Integer alipay) {
		this.alipay = alipay;
	}

	public java.lang.Integer getWeixin() {
		return weixin;
	}

	public void setWeixin(java.lang.Integer weixin) {
		this.weixin = weixin;
	}

	public java.lang.Integer getOther() {
		return other;
	}

	public void setOther(java.lang.Integer other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return "Tmp_statEntity [id=" + id + ", ritualtype=" + ritualtype
				+ ", registertime=" + registertime + ", registrant="
				+ registrant + ", cash=" + cash + ", card=" + card
				+ ", alipay=" + alipay + ", weixin=" + weixin + ", other="
				+ other + ", total=" + total + "]";
	}
	
}

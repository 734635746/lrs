package com.sys.entity.memorialinfo_view;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="memorialinfo_view", schema="")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Memorialinfo_viewEntity
  implements Serializable
{
  private memorialUnionID unionID;
  private String serial;
  private String registertime;
  private String dan;
  private String buildingName;
  private Integer row;
  private Integer ordernumber;
  private String size;
  private String remark;
  private String name;
  private String status;//不能为state，否则封装为json对象是会有问题
  private String relation;
  private String qq;
  private String address;
  private String email;
  private String homephone;
  private String linkman;
  private String relationship;
  private String mobilephone;
  private String wechat;
  private String begintime;
  private String endtime;
  private String comforttime;
  
  @Id
  public memorialUnionID getUnionID()
  {
    return this.unionID;
  }
  
  public void setUnionID(memorialUnionID unionID)
  {
    this.unionID = unionID;
  }
  
  @Column(name="SERIAL", nullable=true, length=32)
  public String getSerial()
  {
    return this.serial;
  }
  
  public void setSerial(String serial)
  {
    this.serial = serial;
  }
  
  @Column(name="REGISTERTIME", nullable=true)
  public String getRegistertime()
  {
    return this.registertime;
  }
  
  public void setRegistertime(String registertime)
  {
    this.registertime = registertime;
  }
  
  @Column(name="DAN", nullable=true, length=5)
  public String getDan()
  {
    return this.dan;
  }
  
  public void setDan(String dan)
  {
    this.dan = dan;
  }
  
  @Column(name="ROW", nullable=true, precision=10, scale=0)
  public Integer getRow()
  {
    return this.row;
  }
  
  public void setRow(Integer row)
  {
    this.row = row;
  }
  
  @Column(name="ORDERNUMBER", nullable=true, precision=10, scale=0)
  public Integer getOrdernumber()
  {
    return this.ordernumber;
  }
  
  public void setOrdernumber(Integer ordernumber)
  {
    this.ordernumber = ordernumber;
  }
  
  @Column(name="SIZE", nullable=true, length=5)
  public String getSize()
  {
    return this.size;
  }
  
  public void setSize(String size)
  {
    this.size = size;
  }
  
  @Column(name="REMARK", nullable=true, length=255)
  public String getRemark()
  {
    return this.remark;
  }
  
  public void setRemark(String remark)
  {
    this.remark = remark;
  }
  
  @Column(name="NAME", nullable=true, length=20)
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  @Column(name="STATE", nullable=true, length=2)
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  @Column(name="RELATION", nullable=true, length=5)
  public String getRelation() {
	return relation;
}

public void setRelation(String relation) {
	this.relation = relation;
}

@Column(name="QQ", nullable=true, length=16)
  public String getQq()
  {
    return this.qq;
  }
  
  public void setQq(String qq)
  {
    this.qq = qq;
  }
  
  @Column(name="ADDRESS", nullable=true, length=36)
  public String getAddress()
  {
    return this.address;
  }
  
  public void setAddress(String address)
  {
    this.address = address;
  }
  
  @Column(name="EMAIL", nullable=true, length=36)
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  @Column(name="HOMEPHONE", nullable=true, length=14)
  public String getHomephone()
  {
    return this.homephone;
  }
  
  public void setHomephone(String homephone)
  {
    this.homephone = homephone;
  }
  
  @Column(name="LINKMAN", nullable=true, length=20)
  public String getLinkman()
  {
    return this.linkman;
  }
  
  public void setLinkman(String linkman)
  {
    this.linkman = linkman;
  }
  
  @Column(name="MOBILEPHONE", nullable=true, length=11)
  public String getMobilephone()
  {
    return this.mobilephone;
  }
  
  public void setMobilephone(String mobilephone)
  {
    this.mobilephone = mobilephone;
  }
  
  @Column(name="WECHAT", nullable=true, length=36)
  public String getWechat()
  {
    return this.wechat;
  }
  
  public void setWechat(String wechat)
  {
    this.wechat = wechat;
  }



  @Column(name="BUILDING_NAME", nullable=true)
  public String getBuildingName() {
		return buildingName;
	}

  public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRelationship() {
		return relationship;
	}
	
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public String getBegintime() {
		return begintime;
	}
	
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	
	public String getEndtime() {
		return endtime;
	}
	
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getComforttime() {
		return comforttime;
	}

	public void setComforttime(String comforttime) {
		this.comforttime = comforttime;
	}


}

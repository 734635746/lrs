package com.sys.entity.memorialinfo_view;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class memorialUnionID
  implements Serializable
{
  private String id;
  private String linkmanid;
  private String nameid;
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getLinkmanid()
  {
    return this.linkmanid;
  }
  
  public void setLinkmanid(String linkmanid)
  {
    this.linkmanid = linkmanid;
  }
  
  public String getNameid()
  {
    return this.nameid;
  }
  
  public void setNameid(String nameid)
  {
    this.nameid = nameid;
  }
  
  public boolean equals(Object obj)
  {
    if ((obj instanceof memorialUnionID))
    {
      memorialUnionID muID = (memorialUnionID)obj;
      if ((this.id.equals(muID.id)) && (this.nameid.equals(muID.nameid)) && (this.linkmanid.equals(muID.linkmanid))) {
        return true;
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    return super.hashCode();
  }
}

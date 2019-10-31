package test;

import java.io.Serializable;

public class TabletStat implements Serializable {

	private String ritualtype;
	private String size;
	private long notprint;
	private long alreadyprint;
	public String getRitualtype() {
		return ritualtype;
	}
	public void setRitualtype(String ritualtype) {
		this.ritualtype = ritualtype;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public long getNotprint() {
		return notprint;
	}
	public void setNotprint(long notprint) {
		this.notprint = notprint;
	}
	public long getAlreadyprint() {
		return alreadyprint;
	}
	public void setAlreadyprint(long alreadyprint) {
		this.alreadyprint = alreadyprint;
	}
	
	
}

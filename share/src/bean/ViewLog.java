package bean;

import java.util.Date;

public class ViewLog {

	
	String clientWx;
	int infoId;
	Date viewdate  ;
	String kouling;
	int sdate ;
	long stime ;
	String tgurl;
	
	

	public String getTgurl() {
		return tgurl;
	}
	public void setTgurl(String tgurl) {
		this.tgurl = tgurl;
	}
	public long getStime() {
		return stime;
	}
	public void setStime(long stime) {
		this.stime = stime;
	}
	public String getClientWx() {
		return clientWx;
	}
	public void setClientWx(String clientWx) {
		this.clientWx = clientWx;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	public Date getViewdate() {
		return viewdate;
	}
	public void setViewdate(Date viewdate) {
		this.viewdate = viewdate;
	}
	public String getKouling() {
		return kouling;
	}
	public void setKouling(String kouling) {
		this.kouling = kouling;
	}
	public int getSdate() {
		return sdate;
	}
	public void setSdate(int sdate) {
		this.sdate = sdate;
	}
 

	
	
}

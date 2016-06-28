package bean;

import java.util.List;

public class Client {


	private int clientid ;
	private String clientWxid ;
	private String clientName ;
	private String clientImg ;
	//历史累计积分(换算成称号)
	private int score ;
	//门票数
	private int ticket ;
	//推广链接
	private String tgurl ;
	//性别
	 String  sex ;
	 //省份
	 String  province;
	 //城市
	 String  city;
	 //国家
	 String  country;
	 //用户特权列表	 
	 List<String>  privilege;
	 String  unionid;
	
	public String getTgurl() {
		return tgurl;
	}
	public void setTgurl(String tgurl) {
		this.tgurl = tgurl;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	public String getClientWxid() {
		return clientWxid;
	}
	public void setClientWxid(String clientWxid) {
		this.clientWxid = clientWxid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientImg() {
		return clientImg;
	}
	public void setClientImg(String clientImg) {
		this.clientImg = clientImg;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<String> getPrivilege() {
		return privilege;
	}
	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

}

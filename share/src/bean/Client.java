package bean;

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

}

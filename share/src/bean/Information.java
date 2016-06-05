package bean;

public class Information {
	private int infoId;
	private String clientWx  ;
	private String clientName   ;
	private String clientImg;
	//类型，0位普通用户分享，1为商家
	private int type  ;
	private int  introduct_acount   ;
	private int  introduct_num   ;
	private String kouling ;
	private int sdate   ;
	//这个值不用存储
	private String outputsdate   ;
	private long stime   ; 
	private int visitor   ;
	private int allowVisit;
	private int authflow;
	private String tgurl;
	private String authreason;
	
	
	
	
	public String getClientWx() {
		return clientWx;
	}
	public void setClientWx(String clientWx) {
		this.clientWx = clientWx;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAuthreason() {
		return authreason;
	}
	public void setAuthreason(String authreason) {
		this.authreason = authreason;
	}
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
	public String getOutputsdate() {
		return outputsdate;
	}
	public void setOutputsdate(String outputsdate) {
		this.outputsdate = outputsdate;
	}
	public String getClientImg() {
		return clientImg;
	}
	public void setClientImg(String clientImg) {
		this.clientImg = clientImg;
	}
	public int getAllowVisit() {
		return allowVisit;
	}
	public void setAllowVisit(int allowVisit) {
		this.allowVisit = allowVisit;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
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

 
	public int getVisitor() {
		return visitor;
	}
	public void setVisitor(int visitor) {
		this.visitor = visitor;
	}
	public int getAuthflow() {
		return authflow;
	}
	public void setAuthflow(int authflow) {
		this.authflow = authflow;
	}
	public int getIntroduct_acount() {
		return introduct_acount;
	}
	public void setIntroduct_acount(int introduct_acount) {
		this.introduct_acount = introduct_acount;
	}
	public int getIntroduct_num() {
		return introduct_num;
	}
	public void setIntroduct_num(int introduct_num) {
		this.introduct_num = introduct_num;
	}
	
	
}

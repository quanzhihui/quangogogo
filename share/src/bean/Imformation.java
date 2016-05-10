package bean;

public class Imformation {
	private int imfoId;
	private int clientId  ;
	private String clientName   ;
	private String clientImg;
	private int  introduct_acount   ;
	private int  introduct_num   ;
	private String kouling ;
	private int sdate   ;
	//这个值不用存储
	private String outputsdate   ;
	private int shour   ;
	private int sminute   ;
	private int zan   ;
	private int keng   ;
	private int visitor   ;
	private int allowVisit;
	private int authflow;

	
	
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
	public int getImfoId() {
		return imfoId;
	}
	public void setImfoId(int imfoId) {
		this.imfoId = imfoId;
	}
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int id) {
		this.clientId = id;
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
	public int getShour() {
		return shour;
	}
	public void setShour(int shour) {
		this.shour = shour;
	}
	public int getSminute() {
		return sminute;
	}
	public void setSminute(int sminute) {
		this.sminute = sminute;
	}
	public int getZan() {
		return zan;
	}
	public void setZan(int zan) {
		this.zan = zan;
	}
	public int getKeng() {
		return keng;
	}
	public void setKeng(int keng) {
		this.keng = keng;
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

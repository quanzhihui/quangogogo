package bean;

import java.util.List;

public class Wxbean_ClientBean {
 String openid;
 String  nickname;
 String  sex ;
 String  province;
 String  city;
 String  country;
 String  headimgurl;
 List<String>  privilege;
 String  unionid;
 String errcode;
 
public String getOpenid() {
	return openid;
}
public void setOpenid(String openid) {
	this.openid = openid;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
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
public String getHeadimgurl() {
	return headimgurl;
}
public void setHeadimgurl(String headimgurl) {
	this.headimgurl = headimgurl;
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
public String getErrcode() {
	return errcode;
}
public void setErrcode(String errcode) {
	this.errcode = errcode;
}
 
 
}

package util;

import service.TokenServer;


public class ShareConst {

	//域名
	public static String domain="127.0.0.1:8080";
	//工程名
	public static String projectname="";
	//全局路径
	public static String path="";
	//图片存储路径	
	public static String imgPath=path+"/img";
	//签到获奖励积分
	public static int qianDaoScore=50;
	//积分兑换门票
	public static int ScoreDuiHuanMenPiao=100;
	
	//推广码长度
	public static int tgdlength=8;
	
	//邀请码码长度
	public static int yqmlength=10;
	
	//消息码码长度
	public static int xxmlength=6;
	
	//普通用户发口令的type
	public static int fkltype_user=0;
	
	//商家用户发口令的type
	public static int fkltype_shop=1;
	
	
	// 用户初始化分数
	public static int init_user_score=50;
	
	// 用户初始化门票
	public static int init_user_ticket=1;
	
	// 用户口令  信息初始化能让多少人能看
	public static int init_user_dsrk=100;
	
	// 商家口令 信息初始化能让多少人能看
	public static int init_shop_dsrk=3000;
	
	
	// 审批状态
	public static int audit_ty=1;
	public static int audit_bh=-1;
	public static int audit_dsp=0;
	
	//系统参数
	
	public static String wxurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+TokenServer.getappId()+"&redirect_uri=https%3a%2f%2fwww.o2ohappy.com%2fshare%2findex%2furl%2fmain&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		
	//获得系统参数
//	public static final Properties PROPERTIES = new Properties(System.getProperties());
	
}

package com.jxxc.jingxijoin;

public class Api {
   public static final String BASEURL = "http://47.101.185.138:8110/";//

    //1-获取验证码
    public static final String SMS_CODE = BASEURL + "common/auth/get_code";
   //2-验证码登录
   public static final String LOGIN_CODE = BASEURL + "system/company/login_by_code";
    //3-客户登录
    public static final String LOGIN = BASEURL + "system/company/login";
    //4-查询app最新版本
    public static final String LATEST_VERSION = BASEURL + "system/upgrade_pack/latest_version";
    //5-订单列表
    public static final String MY_ORDER = BASEURL + "biz/carWashOrder/myOrder";

    
    public static final String GET_ACCOUNT_INFO = BASEURL + "/user/login.json";
    //1-客户登录
    public static final String MESSAGE_LIST = BASEURL + "/user/login.json";
    //1-客户登录
    public static final String BINDING_TIXIAN = BASEURL + "/user/login.json";
}

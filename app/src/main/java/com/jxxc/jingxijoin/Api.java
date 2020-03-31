package com.jxxc.jingxijoin;

public class Api {
//       public static final String BASEURL = "https://repair-api-sit.zhizukj.com/zhizu/repair";//测试环境
//    public static final String BASEURL = "https://zhizuxia.zhizukj.com/ydb/app/";//生产
   public static final String BASEURL = "http://192.168.0.46:8091/zhizu/repair";//于立华

    //1-客户登录
    public static final String LOGIN = BASEURL + "/user/login.json";
    //1-客户登录
    public static final String BINDING_TIXIAN = BASEURL + "/user/login.json";
    //1-客户登录
    public static final String SMS_CODE = BASEURL + "/user/login.json";
    //1-客户登录
    public static final String GET_ACCOUNT_INFO = BASEURL + "/user/login.json";
}

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
    //6-订单详情
    public static final String GET_ORDER = BASEURL + "biz/carWashOrder/getOrder";
    //7-调度
    public static final String DISPATCH = BASEURL + "biz/carWashOrder/dispatch";
    //8-基础收益(首页)
    public static final String BAS_EARNINGS = BASEURL + "system/company/basEarnings";
    //9-技师列表
    public static final String QUERY_LIST = BASEURL + "system/technician/queryList";
    //10-技师详情
    public static final String TECHNICIAN_INFO = BASEURL + "system/technician/info";
    //11-修改技师信息
    public static final String TECHNICIAN_EDIT = BASEURL + "system/technician/edit";
    //12-新增技师
    public static final String TECHNICIAN_ADD = BASEURL + "system/technician/add";
    //13-预约列表
    public static final String APPOINTMENT_LIST = BASEURL + "system/company/appointmentList";
    //14-预约单管理
    public static final String APPOINTMENT_MANAGEMENT = BASEURL + "system/company/appointmentManagement";
   //15-预约详情
   public static final String APPOINTMENT_INFO = BASEURL + "system/company/appointmentInfo";


    public static final String GET_ACCOUNT_INFO = BASEURL + "/user/login.json";
    //1-客户登录
    public static final String MESSAGE_LIST = BASEURL + "/user/login.json";
    //1-客户登录
    public static final String BINDING_TIXIAN = BASEURL + "/user/login.json";
}

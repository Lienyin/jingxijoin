package com.jxxc.jingxijoin;

import com.jxxc.jingxijoin.utils.SPUtils;

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
   //16-获取二维码
   public static final String GET_QRCOED = BASEURL + "system/company/getQrcoed";
   //17-删除技师
   public static final String TECHNICIAN_REMOVE = BASEURL + "system/technician/remove";
    //18-个人信息
    public static final String USER_INFO = BASEURL + "system/company/info";
    //19-提现账户
    public static final String GET_ACCOUNT_INFO = BASEURL + "biz/drawMoney/getAccountInfo";
    //20-上传文件
    public static final String UPLOAD_FILE = SPUtils.get(SPUtils.K_FILE_URL,"") + "common/upload";
    //21-财务报表
    public static final String STATS = BASEURL + "biz/form/stats";
    //22-提现申请
    public static final String DRAW_MONEY_APPLY = BASEURL + "biz/drawMoney/drawMoneyApply";
    //23-提现记录
    public static final String DRAW_MONEY_RECORD = BASEURL + "biz/drawMoney/drawMoneyRecord";
    //24-佣金明细
    public static final String COMMISSION_DETAIL = BASEURL + "biz/drawMoney/commissionDetail";
    //25-绑定银行卡
    public static final String BINDING_ACCOUNT = BASEURL + "biz/drawMoney/bindingAccount";
    //26-省市区
    public static final String SELECT_ALL_AREA = BASEURL + "system/area/selectAllArea";
    //27-更新商户信息
    public static final String UPDATE_INFO = BASEURL + "system/company/updateInfo";

    //0-客户登录
    public static final String MESSAGE_LIST = BASEURL + "/";
    //0-客户登录
    public static final String BINDING_TIXIAN = BASEURL + "/";
}

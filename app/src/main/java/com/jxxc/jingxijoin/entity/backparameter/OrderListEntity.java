package com.jxxc.jingxijoin.entity.backparameter;

import java.io.Serializable;
import java.util.List;

public class OrderListEntity implements Serializable {
    public String address;
    public String appointmentTime;
    public String price;
    public String orderTopic;
    public String remark;
    public String carNum;
    public String brandType;
    public String phonenumber;
    public String createTime;
    public String endTime;
    public String statusName;
    public String canCompleteTime;
    public String orderId;
    public String startTime;
    public int status;
    public int serviceType;
    public int color;
    public double lat;
    public double lng;

    public String getCanCompleteTime() {
        return canCompleteTime;
    }

    public void setCanCompleteTime(String canCompleteTime) {
        this.canCompleteTime = canCompleteTime;
    }
}

package com.jxxc.jingxijoin.entity.backparameter;

import java.io.Serializable;
import java.util.List;

public class GetOrderEntity implements Serializable {
    public int serviceType;
    public int isComment;
    public int color;
    public String orderId;
    public String technicianAvatar;
    public String phonenumber;
    public String remark;
    public String technicianRealName;
    public String payType;
    public String appointmentTime;
    public String price;
    public String statusName;
    public String startTime;
    public float technicianGrade;
    public String surplusCompleteTime;
    public String technicianPhonenumber;
    public String carNum;
    public String commentContent;
    public String customerCommentTime;
    public String brandType;
    public String createTime;
    public String endTime;
    public float starLevel;
    public int status;
    public List<Products> products;
    public class Products{
        public String imgUrl;
        public String productId;
        public String productName;
    }
}

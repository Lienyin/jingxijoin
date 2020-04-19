package com.jxxc.jingxijoin.entity.backparameter;

import java.io.Serializable;
import java.util.List;

public class AppointmentListEntity implements Serializable {

    public List<HourDispatch> hourDispatch;
    public List<DayNum> dayNum;
    public class HourDispatch {
        public String realName;
        public String technicianCode;
        public String isDispatch;
        public String orderId;
        public int num;
        public String h;
        public String title;
        public int status;
    }

    public class DayNum{
        public String date;
        public String num;
    }
}

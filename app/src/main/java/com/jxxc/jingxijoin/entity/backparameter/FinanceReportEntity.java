package com.jxxc.jingxijoin.entity.backparameter;

import java.io.Serializable;
import java.util.List;

public class FinanceReportEntity implements Serializable {
//    public String totalRent;
//    public List<FinanceReport> report;
//
//    public class FinanceReport{
//        public double rentMoney;
//        public double commissionMoney;
//        public String statsDate;
//    }

    public String threeMonthsStats;
    public List<FinanceReport> twelveMonthStats;

    public class FinanceReport{
        public double orderPrice;
        public double commission;
        public String date;
    }
}

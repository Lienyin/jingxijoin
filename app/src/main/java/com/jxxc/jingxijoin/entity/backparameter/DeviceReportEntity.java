package com.jxxc.jingxijoin.entity.backparameter;

import java.io.Serializable;
import java.util.List;

public class DeviceReportEntity implements Serializable {
    public String totalNum;
    public List<DeviceReport> report;

    public class DeviceReport{
        public int carNum;
        public int orderNum;
        public int cabinetNum;
        public String statsDate;
    }
}

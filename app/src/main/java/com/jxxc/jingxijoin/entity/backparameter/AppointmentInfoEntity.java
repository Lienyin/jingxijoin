package com.jxxc.jingxijoin.entity.backparameter;

import java.io.Serializable;
import java.util.List;

public class AppointmentInfoEntity implements Serializable {
    public List<Carport> carport;
    public List<Order> order;
    public List<Tech> tech;
    public class Carport{
        public String carportId;

        public String getCarportId() {
            return carportId;
        }

        public void setCarportId(String carportId) {
            this.carportId = carportId;
        }
    }
    public class Order{
        public String appointmentTime;
        public String orderId;
    }
    public class Tech{
        public String realName;
        public String technicianId;
    }
}

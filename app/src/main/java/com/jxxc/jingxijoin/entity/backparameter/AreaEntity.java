package com.jxxc.jingxijoin.entity.backparameter;

import java.io.Serializable;
import java.util.List;

public class AreaEntity implements Serializable {

    public int name;
    public String value;
    public List<Citys> citys;

    public class Citys{
        public int name;
        public String value;
        public List<Districts> districts;

        public class Districts{
            public int name;
            public String value;
        }
    }
}

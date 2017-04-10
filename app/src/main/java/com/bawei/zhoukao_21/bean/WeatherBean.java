package com.bawei.zhoukao_21.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/9 19:59
 */

public class WeatherBean {
    private String status;
    private String msg;
    private List<ResultBean> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {

        private String cityid;
        private String parentid;
        private String citycode;
        private String city;
        private ArrayList<ResultBean> child_list;

        public ArrayList<ResultBean> getChild_list() {
            return child_list;
        }

        public void setChild_list(ArrayList<ResultBean> child_list) {
            this.child_list = child_list;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}

package com.weisj.fx.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BBMJ on 2015/12/21.
 */
public class AdressBean implements Serializable {


    /**
     * code : 1
     * msg : 获取该用户下所有收获人信息成功!
     * data : [{"recipient_id":8,"recipients":"韦李龙","phone":"18718481873","country":null,"province":"广西","city":"北海","area":"银海区","address":"合浦","recipient_state":0,"zipcode":null},{"recipient_id":11,"recipients":"韦李龙1","phone":"18718481873","country":"中国","province":"福建","city":"龙岩","area":"漳平市","address":"aaaaaaa","recipient_state":0,"zipcode":null},{"recipient_id":13,"recipients":"韦李龙","phone":"18718781877799","country":"中国","province":"河南","city":"安阳","area":"汤阴县","address":"heafa","recipient_state":0,"zipcode":null}]
     */

    private String code;
    private String msg;
    /**
     * recipient_id : 8
     * recipients : 韦李龙
     * phone : 18718481873
     * country : null
     * province : 广西
     * city : 北海
     * area : 银海区
     * address : 合浦
     * recipient_state : 0
     * zipcode : null
     */

    private List<DataEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity  implements Serializable {
        private int recipient_id;
        private String recipients;
        private String phone;
        private Object country;
        private String province;
        private String city;
        private String area;
        private String address;
        private int recipient_state;
        private Object zipcode;

        public void setRecipient_id(int recipient_id) {
            this.recipient_id = recipient_id;
        }

        public void setRecipients(String recipients) {
            this.recipients = recipients;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setCountry(Object country) {
            this.country = country;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setRecipient_state(int recipient_state) {
            this.recipient_state = recipient_state;
        }

        public void setZipcode(Object zipcode) {
            this.zipcode = zipcode;
        }

        public int getRecipient_id() {
            return recipient_id;
        }

        public String getRecipients() {
            return recipients;
        }

        public String getPhone() {
            return phone;
        }

        public Object getCountry() {
            return country;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getArea() {
            return area;
        }

        public String getAddress() {
            return address;
        }

        public int getRecipient_state() {
            return recipient_state;
        }

        public Object getZipcode() {
            return zipcode;
        }
    }
}

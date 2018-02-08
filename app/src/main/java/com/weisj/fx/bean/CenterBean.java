package com.weisj.fx.bean;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class CenterBean {


    /**
     * code : 1
     * msg : 获取会员中心信息成功!
     * data : {"point":0,"sex":0,"cart_num":0,"shop_id":0,"member":2,"member_name":"访客_934393","current_money":0,"collec_num":0,"cupon_num":0,"has_signin":0,"has_consignee":0,"member_pic":"","has_bind_qq":0,"has_bind_webchat":0,"has_bind_sina":0,"shop_type":0,"brand_id":0,"true_name":""}
     */

    private String code;
    private String msg;
    /**
     * point : 0
     * sex : 0
     * cart_num : 0
     * shop_id : 0
     * member : 2
     * member_name : 访客_934393
     * current_money : 0
     * collec_num : 0
     * cupon_num : 0
     * has_signin : 0
     * has_consignee : 0
     * member_pic :
     * has_bind_qq : 0
     * has_bind_webchat : 0
     * has_bind_sina : 0
     * shop_type : 0
     * brand_id : 0
     * true_name :
     */

    private DataEntity data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private int point;
        private int sex;
        private int cart_num;
        private int shop_id;
        private int member;
        private String member_name;
        private String current_money;
        private int collec_num;
        private int cupon_num;
        private int has_signin;
        private int has_consignee;
        private String member_pic;
        private int has_bind_qq;
        private int has_bind_webchat;
        private int has_bind_sina;
        private int shop_type;
        private int brand_id;
        private String true_name;
        private int district_id;
        private String staff_id;

        public String getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(String staff_id) {
            this.staff_id = staff_id;
        }

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public void setCart_num(int cart_num) {
            this.cart_num = cart_num;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public void setMember(int member) {
            this.member = member;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public void setCurrent_money(String current_money) {
            this.current_money = current_money;
        }

        public void setCollec_num(int collec_num) {
            this.collec_num = collec_num;
        }

        public void setCupon_num(int cupon_num) {
            this.cupon_num = cupon_num;
        }

        public void setHas_signin(int has_signin) {
            this.has_signin = has_signin;
        }

        public void setHas_consignee(int has_consignee) {
            this.has_consignee = has_consignee;
        }

        public void setMember_pic(String member_pic) {
            this.member_pic = member_pic;
        }

        public void setHas_bind_qq(int has_bind_qq) {
            this.has_bind_qq = has_bind_qq;
        }

        public void setHas_bind_webchat(int has_bind_webchat) {
            this.has_bind_webchat = has_bind_webchat;
        }

        public void setHas_bind_sina(int has_bind_sina) {
            this.has_bind_sina = has_bind_sina;
        }

        public void setShop_type(int shop_type) {
            this.shop_type = shop_type;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public int getPoint() {
            return point;
        }

        public int getSex() {
            return sex;
        }

        public int getCart_num() {
            return cart_num;
        }

        public int getShop_id() {
            return shop_id;
        }

        public int getMember() {
            return member;
        }

        public String getMember_name() {
            return member_name;
        }

        public String getCurrent_money() {
            return current_money;
        }

        public int getCollec_num() {
            return collec_num;
        }

        public int getCupon_num() {
            return cupon_num;
        }

        public int getHas_signin() {
            return has_signin;
        }

        public int getHas_consignee() {
            return has_consignee;
        }

        public String getMember_pic() {
            return member_pic;
        }

        public int getHas_bind_qq() {
            return has_bind_qq;
        }

        public int getHas_bind_webchat() {
            return has_bind_webchat;
        }

        public int getHas_bind_sina() {
            return has_bind_sina;
        }

        public int getShop_type() {
            return shop_type;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public String getTrue_name() {
            return true_name;
        }
    }
}

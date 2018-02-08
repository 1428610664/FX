package com.weisj.fx.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by BBMJ on 2016/1/19.
 */
public class AccountBillBean {


    /**
     * code : 1
     * msg : 该会员对帐单获取成功！
     * data : [{"type":"充值","status":"已到账","money":1,"order_info_sn":"","goods_name":"","spec_name":"","goods_pic":"","price":0,"withdraw_time":"2016-1-19","trade_no":"2016011913305853092"},{"type":"充值","status":"充值中","money":2,"order_info_sn":"","goods_name":"","spec_name":"","goods_pic":"","price":0,"withdraw_time":"2016-1-19","trade_no":"2016011912382793914"},{"type":"充值","status":"充值中","money":2,"order_info_sn":"","goods_name":"","spec_name":"","goods_pic":"","price":0,"withdraw_time":"2016-1-19","trade_no":"2016011912381459993"},{"type":"充值","status":"充值中","money":2,"order_info_sn":"","goods_name":"","spec_name":"","goods_pic":"","price":0,"withdraw_time":"2016-1-19","trade_no":"2016011912380599557"},{"type":"充值","status":"充值中","money":1,"order_info_sn":"","goods_name":"","spec_name":"","goods_pic":"","price":0,"withdraw_time":"2016-1-19","trade_no":"2016011912191339385"},{"type":"充值","status":"充值中","money":2,"order_info_sn":"","goods_name":"","spec_name":"","goods_pic":"","price":0,"withdraw_time":"2016-1-19","trade_no":"2016011912174650346"},{"type":"充值","status":"充值中","money":2,"order_info_sn":"","goods_name":"","spec_name":"","goods_pic":"","price":0,"withdraw_time":"2016-1-19","trade_no":"2016011912093460125"},{"type":"充值","status":"充值中","money":2,"order_info_sn":"","goods_name":"","spec_name":"","goods_pic":"","price":0,"withdraw_time":"2016-1-19","trade_no":"2016011911565307222"},{"type":"充值","status":"充值中","money":2,"order_info_sn":"","goods_name":"","spec_name":"","goods_pic":"","price":0,"withdraw_time":"2016-1-19","trade_no":"2016011911525073685"},{"type":"充值","status":"充值中","money":2,"order_info_sn":"","goods_name":"","spec_name":"","goods_pic":"","price":0,"withdraw_time":"2016-1-19","trade_no":"2016011911523844304"}]
     */

    private String code;
    private String msg;
    /**
     * type : 充值
     * status : 已到账
     * money : 1
     * order_info_sn :
     * goods_name :
     * spec_name :
     * goods_pic :
     * price : 0
     * withdraw_time : 2016-1-19
     * trade_no : 2016011913305853092
     */

    private List<DataEntity> data;

    public static AccountBillBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), AccountBillBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

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

    public static class DataEntity {
        private String type;
        private String status;
        private String money;
        private String order_info_sn;
        private String goods_name;
        private String spec_name;
        private String goods_pic;
        private String price;
        private String withdraw_time;
        private String trade_no;


        private String periods_no;
        private String yyg_spec_name;
        private String yyg_spec_pic;


        public String getPeriods_no() {
            return periods_no;
        }

        public void setPeriods_no(String periods_no) {
            this.periods_no = periods_no;
        }

        public String getYyg_spec_name() {
            return yyg_spec_name;
        }

        public void setYyg_spec_name(String yyg_spec_name) {
            this.yyg_spec_name = yyg_spec_name;
        }

        public String getYyg_spec_pic() {
            return yyg_spec_pic;
        }

        public void setYyg_spec_pic(String yyg_spec_pic) {
            this.yyg_spec_pic = yyg_spec_pic;
        }

        public static DataEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public void setOrder_info_sn(String order_info_sn) {
            this.order_info_sn = order_info_sn;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public void setSpec_name(String spec_name) {
            this.spec_name = spec_name;
        }

        public void setGoods_pic(String goods_pic) {
            this.goods_pic = goods_pic;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setWithdraw_time(String withdraw_time) {
            this.withdraw_time = withdraw_time;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getType() {
            return type;
        }

        public String getStatus() {
            return status;
        }

        public String getMoney() {
            return money;
        }

        public String getOrder_info_sn() {
            return order_info_sn;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public String getSpec_name() {
            return spec_name;
        }

        public String getGoods_pic() {
            return goods_pic;
        }

        public String getPrice() {
            return price;
        }

        public String getWithdraw_time() {
            return withdraw_time;
        }

        public String getTrade_no() {
            return trade_no;
        }
    }
}

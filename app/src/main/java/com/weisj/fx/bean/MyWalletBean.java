package com.weisj.fx.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BBMJ on 2016/1/19.
 */
public class MyWalletBean {


    /**
     * code : 1
     * msg : 获取该会员各项统计金额成功！
     * data : {"withdraw_money":0,"withdrawing_money":0,"not_confirm_money":0,"user_money":0,"all_income":89992}
     */

    private String code;
    private String msg;
    /**
     * withdraw_money : 0
     * withdrawing_money : 0
     * not_confirm_money : 0
     * user_money : 0
     * all_income : 89992
     */

    private DataEntity data;

    public static MyWalletBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), MyWalletBean.class);
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
        private String withdraw_money;
        private String withdrawing_money;
        private String not_confirm_money;
        private String user_money;
        private String all_income;

        public static DataEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public void setWithdraw_money(String withdraw_money) {
            this.withdraw_money = withdraw_money;
        }

        public void setWithdrawing_money(String withdrawing_money) {
            this.withdrawing_money = withdrawing_money;
        }

        public void setNot_confirm_money(String not_confirm_money) {
            this.not_confirm_money = not_confirm_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public void setAll_income(String all_income) {
            this.all_income = all_income;
        }

        public String getWithdraw_money() {
            return withdraw_money;
        }

        public String getWithdrawing_money() {
            return withdrawing_money;
        }

        public String getNot_confirm_money() {
            return not_confirm_money;
        }

        public String getUser_money() {
            return user_money;
        }

        public String getAll_income() {
            return all_income;
        }
    }
}

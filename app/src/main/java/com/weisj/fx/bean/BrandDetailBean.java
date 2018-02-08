package com.weisj.fx.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BBMJ on 2016/1/19.
 */
public class BrandDetailBean {


    /**
     * code : 1
     * msg : 该会员获取绑定银行卡信息成功！
     * data : {"bank_id":3,"bank_name":"空空咯空咯","bank_account_name":"空间里","bank_account_no":"2255 5655 5555 5555 525"}
     */

    private String code;
    private String msg;
    /**
     * bank_id : 3
     * bank_name : 空空咯空咯
     * bank_account_name : 空间里
     * bank_account_no : 2255 5655 5555 5555 525
     */

    private DataEntity data;

    public BrandDetailBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), BrandDetailBean.class);
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

    public class DataEntity {
        private int bank_id;
        private String bank_name;
        private String bank_account_name;
        private String bank_account_no;
        private String branch_bank_name;
        private String city_name;

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getBranch_bank_name() {
            return branch_bank_name;
        }

        public void setBranch_bank_name(String branch_bank_name) {
            this.branch_bank_name = branch_bank_name;
        }

        public DataEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public void setBank_id(int bank_id) {
            this.bank_id = bank_id;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public void setBank_account_name(String bank_account_name) {
            this.bank_account_name = bank_account_name;
        }

        public void setBank_account_no(String bank_account_no) {
            this.bank_account_no = bank_account_no;
        }

        public int getBank_id() {
            return bank_id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public String getBank_account_name() {
            return bank_account_name;
        }

        public String getBank_account_no() {
            return bank_account_no;
        }
    }
}


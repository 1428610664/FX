package com.weisj.fx.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by BBMJ on 2016/8/9.
 */
public class ConversionRateBean {

    /**
     * code : 1
     * msg : 获取员工分享的统计信息成功！
     * data : {"share_statistic_domain_list":[{"time_point":0,"access_count":1,"share_date":""},{"time_point":3,"access_count":0,"share_date":""},{"time_point":6,"access_count":0,"share_date":""},{"time_point":9,"access_count":2,"share_date":""},{"time_point":12,"access_count":4,"share_date":""},{"time_point":15,"access_count":3,"share_date":""},{"time_point":18,"access_count":1,"share_date":""},{"time_point":21,"access_count":0,"share_date":""},{"time_point":0,"access_count":0,"share_date":""},{"time_point":3,"access_count":1,"share_date":""},{"time_point":6,"access_count":1,"share_date":""},{"time_point":9,"access_count":6,"share_date":""},{"time_point":12,"access_count":4,"share_date":""},{"time_point":15,"access_count":5,"share_date":""}],"share_goods_info_domain_list":[{"goods_id":3,"goods_name":"测试商品购买无效荣华双黄连月饼","goods_describe":"荣华","pay_money":0.05,"goods_number":1,"share_record_count":0},{"goods_id":4,"goods_name":"测试商品购买无效哈根达斯冰淇淋月饼","goods_describe":"哈根达斯","pay_money":0.05,"goods_number":1,"share_record_count":6},{"goods_id":6,"goods_name":"测试商品购买无效富锦高端送礼月饼","goods_describe":"富锦","pay_money":0.05,"goods_number":1,"share_record_count":1}]}
     */

    private String code;
    private String msg;
    private DataEntity data;

    public static ConversionRateBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ConversionRateBean.class);
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
        /**
         * time_point : 0
         * access_count : 1
         * share_date :
         */

        private List<ShareStatisticDomainListEntity> share_statistic_domain_list;
        /**
         * goods_id : 3
         * goods_name : 测试商品购买无效荣华双黄连月饼
         * goods_describe : 荣华
         * pay_money : 0.05
         * goods_number : 1
         * share_record_count : 0
         */

        private List<ShareGoodsInfoDomainListEntity> share_goods_info_domain_list;

        public static DataEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public void setShare_statistic_domain_list(List<ShareStatisticDomainListEntity> share_statistic_domain_list) {
            this.share_statistic_domain_list = share_statistic_domain_list;
        }

        public void setShare_goods_info_domain_list(List<ShareGoodsInfoDomainListEntity> share_goods_info_domain_list) {
            this.share_goods_info_domain_list = share_goods_info_domain_list;
        }

        public List<ShareStatisticDomainListEntity> getShare_statistic_domain_list() {
            return share_statistic_domain_list;
        }

        public List<ShareGoodsInfoDomainListEntity> getShare_goods_info_domain_list() {
            return share_goods_info_domain_list;
        }

        public static class ShareStatisticDomainListEntity {
            private String time_point;
            private int access_count;
            private String share_date;

            public static ShareStatisticDomainListEntity objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), ShareStatisticDomainListEntity.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public void setTime_point(String time_point) {
                this.time_point = time_point;
            }

            public void setAccess_count(int access_count) {
                this.access_count = access_count;
            }

            public void setShare_date(String share_date) {
                this.share_date = share_date;
            }

            public String getTime_point() {
                return time_point;
            }

            public int getAccess_count() {
                return access_count;
            }

            public String getShare_date() {
                return share_date;
            }
        }

        public static class ShareGoodsInfoDomainListEntity {
            private int goods_id;
            private String goods_name;
            private String goods_describe;
            private double pay_money;
            private int goods_number;
            private int share_record_count;
            private String goods_pic;

            public static ShareGoodsInfoDomainListEntity objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), ShareGoodsInfoDomainListEntity.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }


            public String getGoods_pic() {
                return goods_pic;
            }

            public void setGoods_pic(String goods_pic) {
                this.goods_pic = goods_pic;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setGoods_describe(String goods_describe) {
                this.goods_describe = goods_describe;
            }

            public void setPay_money(double pay_money) {
                this.pay_money = pay_money;
            }

            public void setGoods_number(int goods_number) {
                this.goods_number = goods_number;
            }

            public void setShare_record_count(int share_record_count) {
                this.share_record_count = share_record_count;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getGoods_describe() {
                return goods_describe;
            }

            public double getPay_money() {
                return pay_money;
            }

            public int getGoods_number() {
                return goods_number;
            }

            public int getShare_record_count() {
                return share_record_count;
            }
        }
    }
}

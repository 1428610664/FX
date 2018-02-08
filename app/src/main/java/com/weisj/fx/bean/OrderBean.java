package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class OrderBean {


    /**
     * code : 1
     * msg : 我的订单查询成功！
     * data : [{"nickname":"像少年啦飞驰","order_brand_id":16,"order_brand_sn":"B2016080420041527720","brand_name":"顺丰优选自营","order_brand_state":1,"goods_num":1,"brand_total_money":0.01,"order_info_domain_list":[{"number":1,"price":0.02,"order_info_id":16,"goods_name":"九峰黄金李(40个装)","spec_name":null,"category_name":"桃/李/杏","pay_money":0.01,"spec_pic":"http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082513_84929.png","order_info_state":1,"is_comment":0,"commission_money":0,"goods_sn":null}],"create_time":"2016-08-04 20:04:15","pay_time":"2016-08-04 20:04:25","deliver_time":"","receive_time":"","recipient_object":null,"shipping_sn":"","shipping_money":0.01,"shipping_company_name":"","shipping_company_code":"","commission_money":0,"pay_type":0,"img_small":null,"ali_account_no":null,"consignee_name":null,"order_address":null,"shop_id":0},{"nickname":"像少年啦飞驰","order_brand_id":15,"order_brand_sn":"B2016080419393868761","brand_name":"顺丰优选自营","order_brand_state":0,"goods_num":1,"brand_total_money":0.01,"order_info_domain_list":[{"number":1,"price":0.02,"order_info_id":15,"goods_name":"九峰黄金李(40个装)","spec_name":null,"category_name":"桃/李/杏","pay_money":0.01,"spec_pic":"http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082513_84929.png","order_info_state":0,"is_comment":0,"commission_money":0,"goods_sn":null}],"create_time":"2016-08-04 19:39:38","pay_time":"","deliver_time":"","receive_time":"","recipient_object":null,"shipping_sn":"","shipping_money":0.01,"shipping_company_name":"","shipping_company_code":"","commission_money":0,"pay_type":0,"img_small":null,"ali_account_no":null,"consignee_name":null,"order_address":null,"shop_id":0},{"nickname":"像少年啦飞驰","order_brand_id":14,"order_brand_sn":"B2016080419362363521","brand_name":"顺丰优选自营","order_brand_state":0,"goods_num":1,"brand_total_money":0.01,"order_info_domain_list":[{"number":1,"price":0.02,"order_info_id":14,"goods_name":"九峰黄金李(40个装)","spec_name":null,"category_name":"桃/李/杏","pay_money":0.01,"spec_pic":"http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082513_84929.png","order_info_state":0,"is_comment":0,"commission_money":0,"goods_sn":null}],"create_time":"2016-08-04 19:36:23","pay_time":"","deliver_time":"","receive_time":"","recipient_object":null,"shipping_sn":"","shipping_money":0.01,"shipping_company_name":"","shipping_company_code":"","commission_money":0,"pay_type":0,"img_small":null,"ali_account_no":null,"consignee_name":null,"order_address":null,"shop_id":0},{"nickname":"像少年啦飞驰","order_brand_id":13,"order_brand_sn":"B2016080419343965091","brand_name":"顺丰优选自营","order_brand_state":0,"goods_num":1,"brand_total_money":0.01,"order_info_domain_list":[{"number":1,"price":0.02,"order_info_id":13,"goods_name":"九峰黄金李(40个装)","spec_name":null,"category_name":"桃/李/杏","pay_money":0.01,"spec_pic":"http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082513_84929.png","order_info_state":0,"is_comment":0,"commission_money":0,"goods_sn":null}],"create_time":"2016-08-04 19:34:39","pay_time":"","deliver_time":"","receive_time":"","recipient_object":null,"shipping_sn":"","shipping_money":0.01,"shipping_company_name":"","shipping_company_code":"","commission_money":0,"pay_type":0,"img_small":null,"ali_account_no":null,"consignee_name":null,"order_address":null,"shop_id":0}]
     */

    private String code;
    private String msg;
    /**
     * nickname : 像少年啦飞驰
     * order_brand_id : 16
     * order_brand_sn : B2016080420041527720
     * brand_name : 顺丰优选自营
     * order_brand_state : 1
     * goods_num : 1
     * brand_total_money : 0.01
     * order_info_domain_list : [{"number":1,"price":0.02,"order_info_id":16,"goods_name":"九峰黄金李(40个装)","spec_name":null,"category_name":"桃/李/杏","pay_money":0.01,"spec_pic":"http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082513_84929.png","order_info_state":1,"is_comment":0,"commission_money":0,"goods_sn":null}]
     * create_time : 2016-08-04 20:04:15
     * pay_time : 2016-08-04 20:04:25
     * deliver_time :
     * receive_time :
     * recipient_object : null
     * shipping_sn :
     * shipping_money : 0.01
     * shipping_company_name :
     * shipping_company_code :
     * commission_money : 0
     * pay_type : 0
     * img_small : null
     * ali_account_no : null
     * consignee_name : null
     * order_address : null
     * shop_id : 0
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

    public static class DataEntity {
        private String nickname;
        private int order_brand_id;
        private String brand_name;
        private int order_brand_state;
        private int goods_num;
        private String shipping_sn;
        private String shipping_company_name;
        private String shipping_company_code;
        private String commission_money;
        private int pay_type;
        /**
         * number : 1
         * price : 0.02
         * order_info_id : 16
         * goods_name : 九峰黄金李(40个装)
         * spec_name : null
         * category_name : 桃/李/杏
         * pay_money : 0.01
         * spec_pic : http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082513_84929.png
         * order_info_state : 1
         * is_comment : 0
         * commission_money : 0
         * goods_sn : null
         */

        private List<OrderInfoDomainListEntity> order_info_domain_list;

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setOrder_brand_id(int order_brand_id) {
            this.order_brand_id = order_brand_id;
        }


        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public void setOrder_brand_state(int order_brand_state) {
            this.order_brand_state = order_brand_state;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }


        public void setShipping_sn(String shipping_sn) {
            this.shipping_sn = shipping_sn;
        }


        public void setShipping_company_name(String shipping_company_name) {
            this.shipping_company_name = shipping_company_name;
        }

        public void setShipping_company_code(String shipping_company_code) {
            this.shipping_company_code = shipping_company_code;
        }

        public void setCommission_money(String commission_money) {
            this.commission_money = commission_money;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }


        public void setOrder_info_domain_list(List<OrderInfoDomainListEntity> order_info_domain_list) {
            this.order_info_domain_list = order_info_domain_list;
        }

        public String getNickname() {
            return nickname;
        }

        public int getOrder_brand_id() {
            return order_brand_id;
        }


        public String getBrand_name() {
            return brand_name;
        }

        public int getOrder_brand_state() {
            return order_brand_state;
        }

        public int getGoods_num() {
            return goods_num;
        }


        public String getShipping_sn() {
            return shipping_sn;
        }


        public String getShipping_company_name() {
            return shipping_company_name;
        }

        public String getShipping_company_code() {
            return shipping_company_code;
        }

        public String getCommission_money() {
            return commission_money;
        }

        public int getPay_type() {
            return pay_type;
        }


        public List<OrderInfoDomainListEntity> getOrder_info_domain_list() {
            return order_info_domain_list;
        }

        public static class OrderInfoDomainListEntity {
            private int number;
            private String price;
            private int order_info_id;
            private String goods_name;
            private String spec_pic;
            private String commission_money;

            public void setNumber(int number) {
                this.number = number;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setOrder_info_id(int order_info_id) {
                this.order_info_id = order_info_id;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }


            public void setSpec_pic(String spec_pic) {
                this.spec_pic = spec_pic;
            }


            public void setCommission_money(String commission_money) {
                this.commission_money = commission_money;
            }


            public int getNumber() {
                return number;
            }

            public String getPrice() {
                return price;
            }

            public int getOrder_info_id() {
                return order_info_id;
            }

            public String getGoods_name() {
                return goods_name;
            }


            public String getSpec_pic() {
                return spec_pic;
            }

            public String getCommission_money() {
                return commission_money;
            }

        }
    }
}

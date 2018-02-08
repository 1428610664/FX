package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class OrderLogisticsBean {


    private String code;
    private String msg;


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
        private String nickname;
        private int order_brand_id;
        private String order_brand_sn;
        private String brand_name;
        private int order_brand_state;
        private int goods_num;
        private String brand_total_money;
        private String create_time;
        private String pay_time;
        private String deliver_time;
        private String receive_time;
        /**
         * country : 中国
         * province : 广东省
         * city : 深圳市
         * area : 龙岗区
         * address : 南湾街道
         * zipcode :
         * recipient_id : 41
         * recipients : 明
         * phone : 13430701886
         * recipient_state : 0
         */

        private RecipientObjectEntity recipient_object;
        private String shipping_sn;
        private String shipping_money;
        private String shipping_company_name;
        private String shipping_company_code;
        private String commission_money;
        private int pay_type;
        private String img_small;
        private String ali_account_no;
        private int order_id;
        private int shop_id;
        /**
         * number : 1
         * price : 1168
         * order_info_id : 97
         * goods_name : 【长江壹号】有机大闸蟹提货券-马到成功
         * spec_name : null
         * category_name : 大闸蟹
         * pay_money : 976
         * spec_pic : /Public/backend/upload/images/2016/08/20160827164303_20819.jpg
         * order_info_state : 4
         * is_comment : 0
         * commission_money : 0
         * goods_sn : null
         * goods_id : 0
         * goods_describe : null
         */

        private List<OrderInfoDomainListEntity> order_info_domain_list;

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setOrder_brand_id(int order_brand_id) {
            this.order_brand_id = order_brand_id;
        }

        public void setOrder_brand_sn(String order_brand_sn) {
            this.order_brand_sn = order_brand_sn;
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

        public void setBrand_total_money(String brand_total_money) {
            this.brand_total_money = brand_total_money;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public void setDeliver_time(String deliver_time) {
            this.deliver_time = deliver_time;
        }

        public void setReceive_time(String receive_time) {
            this.receive_time = receive_time;
        }

        public void setRecipient_object(RecipientObjectEntity recipient_object) {
            this.recipient_object = recipient_object;
        }

        public void setShipping_sn(String shipping_sn) {
            this.shipping_sn = shipping_sn;
        }

        public void setShipping_money(String shipping_money) {
            this.shipping_money = shipping_money;
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

        public void setImg_small(String img_small) {
            this.img_small = img_small;
        }

        public void setAli_account_no(String ali_account_no) {
            this.ali_account_no = ali_account_no;
        }


        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
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

        public String getOrder_brand_sn() {
            return order_brand_sn;
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

        public String getBrand_total_money() {
            return brand_total_money;
        }

        public String getCreate_time() {
            return create_time;
        }

        public String getPay_time() {
            return pay_time;
        }

        public String getDeliver_time() {
            return deliver_time;
        }

        public String getReceive_time() {
            return receive_time;
        }

        public RecipientObjectEntity getRecipient_object() {
            return recipient_object;
        }

        public String getShipping_sn() {
            return shipping_sn;
        }

        public String getShipping_money() {
            return shipping_money;
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

        public String getImg_small() {
            return img_small;
        }

        public String getAli_account_no() {
            return ali_account_no;
        }

        public int getOrder_id() {
            return order_id;
        }

        public int getShop_id() {
            return shop_id;
        }

        public List<OrderInfoDomainListEntity> getOrder_info_domain_list() {
            return order_info_domain_list;
        }

        public static class RecipientObjectEntity {
            private String country;
            private String province;
            private String city;
            private String area;
            private String address;
            private String zipcode;
            private int recipient_id;
            private String recipients;
            private String phone;
            private int recipient_state;

            public void setCountry(String country) {
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

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public void setRecipient_id(int recipient_id) {
                this.recipient_id = recipient_id;
            }

            public void setRecipients(String recipients) {
                this.recipients = recipients;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public void setRecipient_state(int recipient_state) {
                this.recipient_state = recipient_state;
            }

            public String getCountry() {
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

            public String getZipcode() {
                return zipcode;
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

            public int getRecipient_state() {
                return recipient_state;
            }
        }

        public static class OrderInfoDomainListEntity {
            private int number;
            private String price;
            private int order_info_id;
            private String goods_name;
            private String spec_name;
            private String category_name;
            private String pay_money;
            private String spec_pic;
            private int order_info_state;
            private int is_comment;
            private String commission_money;
            private String goods_sn;
            private int goods_id;
            private String goods_describe;

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

            public void setSpec_name(String spec_name) {
                this.spec_name = spec_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public void setPay_money(String pay_money) {
                this.pay_money = pay_money;
            }

            public void setSpec_pic(String spec_pic) {
                this.spec_pic = spec_pic;
            }

            public void setOrder_info_state(int order_info_state) {
                this.order_info_state = order_info_state;
            }

            public void setIs_comment(int is_comment) {
                this.is_comment = is_comment;
            }

            public void setCommission_money(String commission_money) {
                this.commission_money = commission_money;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public void setGoods_describe(String goods_describe) {
                this.goods_describe = goods_describe;
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

            public String getSpec_name() {
                return spec_name;
            }

            public String getCategory_name() {
                return category_name;
            }

            public String getPay_money() {
                return pay_money;
            }

            public String getSpec_pic() {
                return spec_pic;
            }

            public int getOrder_info_state() {
                return order_info_state;
            }

            public int getIs_comment() {
                return is_comment;
            }

            public String getCommission_money() {
                return commission_money;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public String getGoods_describe() {
                return goods_describe;
            }
        }
    }
}

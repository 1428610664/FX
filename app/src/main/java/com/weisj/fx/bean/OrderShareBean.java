package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class OrderShareBean {


    /**
     * code : 1
     * msg : 查询该会员分享商品和活动成功！
     * data : {"all_total_count":0,"account_money":0,"brand_share_record_domain_list":[{"brand_id":1,"brand_name":"顺丰优选自营","visit_reward":50,"brand_available_count":0,"share_record_domain_list":[{"share_record_id":3,"member_id":3,"goods_id":1,"coupon_id":0,"total_count":0,"available_count":0,"create_time":1470391448000,"goods_name":"中秋季-花好月圆30g（两个）","html_address":"","goods_pic":"http://bg.wsjzb.cn/Public/upload/image/20160805/20160805070806_72091.jpg","coupon_title":""}]}]}
     */

    private String code;
    private String msg;
    /**
     * all_total_count : 0
     * account_money : 0
     * brand_share_record_domain_list : [{"brand_id":1,"brand_name":"顺丰优选自营","visit_reward":50,"brand_available_count":0,"share_record_domain_list":[{"share_record_id":3,"member_id":3,"goods_id":1,"coupon_id":0,"total_count":0,"available_count":0,"create_time":1470391448000,"goods_name":"中秋季-花好月圆30g（两个）","html_address":"","goods_pic":"http://bg.wsjzb.cn/Public/upload/image/20160805/20160805070806_72091.jpg","coupon_title":""}]}]
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
        private int all_total_count;
        private String account_money;
        /**
         * brand_id : 1
         * brand_name : 顺丰优选自营
         * visit_reward : 50
         * brand_available_count : 0
         * share_record_domain_list : [{"share_record_id":3,"member_id":3,"goods_id":1,"coupon_id":0,"total_count":0,"available_count":0,"create_time":1470391448000,"goods_name":"中秋季-花好月圆30g（两个）","html_address":"","goods_pic":"http://bg.wsjzb.cn/Public/upload/image/20160805/20160805070806_72091.jpg","coupon_title":""}]
         */

        private List<BrandShareRecordDomainListEntity> brand_share_record_domain_list;

        public void setAll_total_count(int all_total_count) {
            this.all_total_count = all_total_count;
        }

        public void setAccount_money(String account_money) {
            this.account_money = account_money;
        }

        public void setBrand_share_record_domain_list(List<BrandShareRecordDomainListEntity> brand_share_record_domain_list) {
            this.brand_share_record_domain_list = brand_share_record_domain_list;
        }

        public int getAll_total_count() {
            return all_total_count;
        }

        public String getAccount_money() {
            return account_money;
        }

        public List<BrandShareRecordDomainListEntity> getBrand_share_record_domain_list() {
            return brand_share_record_domain_list;
        }

        public static class BrandShareRecordDomainListEntity {
            private int brand_id;
            private String brand_name;
            private int visit_reward;
            private int brand_available_count;
            /**
             * share_record_id : 3
             * member_id : 3
             * goods_id : 1
             * coupon_id : 0
             * total_count : 0
             * available_count : 0
             * create_time : 1470391448000
             * goods_name : 中秋季-花好月圆30g（两个）
             * html_address :
             * goods_pic : http://bg.wsjzb.cn/Public/upload/image/20160805/20160805070806_72091.jpg
             * coupon_title :
             */

            private List<ShareRecordDomainListEntity> share_record_domain_list;

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public void setVisit_reward(int visit_reward) {
                this.visit_reward = visit_reward;
            }

            public void setBrand_available_count(int brand_available_count) {
                this.brand_available_count = brand_available_count;
            }

            public void setShare_record_domain_list(List<ShareRecordDomainListEntity> share_record_domain_list) {
                this.share_record_domain_list = share_record_domain_list;
            }

            public int getBrand_id() {
                return brand_id;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public int getVisit_reward() {
                return visit_reward;
            }

            public int getBrand_available_count() {
                return brand_available_count;
            }

            public List<ShareRecordDomainListEntity> getShare_record_domain_list() {
                return share_record_domain_list;
            }

            public static class ShareRecordDomainListEntity {
                private int share_record_id;
                private int member_id;
                private int goods_id;
                private int coupon_id;
                private int total_count;
                private int available_count;
                private long create_time;
                private String goods_name;
                private String html_address;
                private String goods_pic;
                private String coupon_title;
                private String coupon_pic;

                public String getCoupon_pic() {
                    return coupon_pic;
                }

                public void setCoupon_pic(String coupon_pic) {
                    this.coupon_pic = coupon_pic;
                }

                public void setShare_record_id(int share_record_id) {
                    this.share_record_id = share_record_id;
                }

                public void setMember_id(int member_id) {
                    this.member_id = member_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public void setCoupon_id(int coupon_id) {
                    this.coupon_id = coupon_id;
                }

                public void setTotal_count(int total_count) {
                    this.total_count = total_count;
                }

                public void setAvailable_count(int available_count) {
                    this.available_count = available_count;
                }

                public void setCreate_time(long create_time) {
                    this.create_time = create_time;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public void setHtml_address(String html_address) {
                    this.html_address = html_address;
                }

                public void setGoods_pic(String goods_pic) {
                    this.goods_pic = goods_pic;
                }

                public void setCoupon_title(String coupon_title) {
                    this.coupon_title = coupon_title;
                }

                public int getShare_record_id() {
                    return share_record_id;
                }

                public int getMember_id() {
                    return member_id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public int getCoupon_id() {
                    return coupon_id;
                }

                public int getTotal_count() {
                    return total_count;
                }

                public int getAvailable_count() {
                    return available_count;
                }

                public long getCreate_time() {
                    return create_time;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public String getHtml_address() {
                    return html_address;
                }

                public String getGoods_pic() {
                    return goods_pic;
                }

                public String getCoupon_title() {
                    return coupon_title;
                }
            }
        }
    }
}

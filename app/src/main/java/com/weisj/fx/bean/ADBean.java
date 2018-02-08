package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class ADBean {


    /**
     * code : 1
     * msg : 操作成功!
     * data : [{"enabled":0,"ad_id":46,"ad_type":0,"ad_name":"APP闪屏广告（勿删）","ad_pic":"http://bg.wsjzb.cn/Public/upload/image/20160805/20160805114702_72674.jpg","ad_link":"baidu.com","diy_ad_link":"","is_add":null}]
     */

    private String code;
    private String msg;
    /**
     * enabled : 0
     * ad_id : 46
     * ad_type : 0
     * ad_name : APP闪屏广告（勿删）
     * ad_pic : http://bg.wsjzb.cn/Public/upload/image/20160805/20160805114702_72674.jpg
     * ad_link : baidu.com
     * diy_ad_link :
     * is_add : null
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
        private int enabled;
        private int ad_id;
        private int ad_type;
        private String ad_name;
        private String ad_pic;
        private String ad_link;

        public void setEnabled(int enabled) {
            this.enabled = enabled;
        }

        public void setAd_id(int ad_id) {
            this.ad_id = ad_id;
        }

        public void setAd_type(int ad_type) {
            this.ad_type = ad_type;
        }

        public void setAd_name(String ad_name) {
            this.ad_name = ad_name;
        }

        public void setAd_pic(String ad_pic) {
            this.ad_pic = ad_pic;
        }

        public void setAd_link(String ad_link) {
            this.ad_link = ad_link;
        }


        public int getEnabled() {
            return enabled;
        }

        public int getAd_id() {
            return ad_id;
        }

        public int getAd_type() {
            return ad_type;
        }

        public String getAd_name() {
            return ad_name;
        }

        public String getAd_pic() {
            return ad_pic;
        }

        public String getAd_link() {
            return ad_link;
        }


    }
}

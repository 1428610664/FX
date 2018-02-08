package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class FriendRecordBean {


    /**
     * code : 1
     * msg : 查询访问该会员分享的商品或活动的微信会员信息成功！
     * data : [{"nickname":"周章成","wx_user_id":6,"img_small":null}]
     */

    private String code;
    private String msg;
    /**
     * nickname : 周章成
     * wx_user_id : 6
     * img_small : null
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
        private int wx_user_id;
        private String img_small;

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setWx_user_id(int wx_user_id) {
            this.wx_user_id = wx_user_id;
        }

        public void setImg_small(String img_small) {
            this.img_small = img_small;
        }

        public String getNickname() {
            return nickname;
        }

        public int getWx_user_id() {
            return wx_user_id;
        }

        public String getImg_small() {
            return img_small;
        }
    }
}

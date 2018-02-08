package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class OneShareBean {

    /**
     * code : 1
     * msg : 获取参与该活动的人员列表成功！
     * data : [{"guessRecordId":1,"guessActivityId":0,"memberId":0,"parentMemberId":0,"sellMemberId":0,"currentGuessPrice":20.88,"lastGuessPrice":null,"guessNum":2,"recordStatus":0,"createTime":null,"udpateTime":1481006881000,"nickname":"Doctor通","headerPic":"/Public/upload/header_images/2016/08/20160824114513_56861.png","guessAwardId":1}]
     */

    private String code;
    private String msg;
    /**
     * guessRecordId : 1
     * guessActivityId : 0
     * memberId : 0
     * parentMemberId : 0
     * sellMemberId : 0
     * currentGuessPrice : 20.88
     * lastGuessPrice : null
     * guessNum : 2
     * recordStatus : 0
     * createTime : null
     * udpateTime : 1481006881000
     * nickname : Doctor通
     * headerPic : /Public/upload/header_images/2016/08/20160824114513_56861.png
     * guessAwardId : 1
     */

    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String currentGuessPrice;
        private int guessNum;
        private int recordStatus;
        private long udpateTime;
        private String nickname;
        private String headerPic;
        private int guessAwardId;



        public String getCurrentGuessPrice() {
            return currentGuessPrice;
        }

        public void setCurrentGuessPrice(String currentGuessPrice) {
            this.currentGuessPrice = currentGuessPrice;
        }


        public int getGuessNum() {
            return guessNum;
        }

        public void setGuessNum(int guessNum) {
            this.guessNum = guessNum;
        }

        public int getRecordStatus() {
            return recordStatus;
        }

        public void setRecordStatus(int recordStatus) {
            this.recordStatus = recordStatus;
        }


        public long getUdpateTime() {
            return udpateTime;
        }

        public void setUdpateTime(long udpateTime) {
            this.udpateTime = udpateTime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeaderPic() {
            return headerPic;
        }

        public void setHeaderPic(String headerPic) {
            this.headerPic = headerPic;
        }

        public int getGuessAwardId() {
            return guessAwardId;
        }

        public void setGuessAwardId(int guessAwardId) {
            this.guessAwardId = guessAwardId;
        }
    }
}

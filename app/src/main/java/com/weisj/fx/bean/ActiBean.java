package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class ActiBean {

    /**
     * code : 1
     * msg : 获取所有竞猜活动成功！
     * data : [{"guessActivityId":2,"issueNo":"2016120502","activityName":"竞猜腊味的价格 最高可获得1000元等值商品","activityRule":null,"activityType":0,"goodsId":61,"bannerPic":"http://shop.fx-sf.com/Public/backend/upload/images/2016/12/20161205150725_42950.jpg","sharePic":null,"htmlAddress":null,"startTime":1480932552000,"endTime":1481105354000,"awardPrice":1000,"activityStatus":1,"createTime":1480932432000,"updateTime":1480932432000,"goodsName":"罗浮山酥醪天然晾晒永记广式腊味礼盒800g"},{"guessActivityId":1,"issueNo":"2016120501","activityName":"有奖竞猜小龙虾的价格 最高可获得1000元等值商品","activityRule":null,"activityType":0,"goodsId":64,"bannerPic":"http://shop.fx-sf.com/Public/backend/upload/images/2016/12/20161205150725_42950.jpg","sharePic":null,"htmlAddress":null,"startTime":1480921801000,"endTime":1481353803000,"awardPrice":1000,"activityStatus":1,"createTime":1480921828000,"updateTime":1480921832000,"goodsName":"莱克6-8钱口味虾麻辣小龙虾香辣熟食口味虾3斤装净虾1.5斤"}]
     */

    private String code;
    private String msg;
    /**
     * guessActivityId : 2
     * issueNo : 2016120502
     * activityName : 竞猜腊味的价格 最高可获得1000元等值商品
     * activityRule : null
     * activityType : 0
     * goodsId : 61
     * bannerPic : http://shop.fx-sf.com/Public/backend/upload/images/2016/12/20161205150725_42950.jpg
     * sharePic : null
     * htmlAddress : null
     * startTime : 1480932552000
     * endTime : 1481105354000
     * awardPrice : 1000
     * activityStatus : 1
     * createTime : 1480932432000
     * updateTime : 1480932432000
     * goodsName : 罗浮山酥醪天然晾晒永记广式腊味礼盒800g
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
        private int guessActivityId;
        private String issueNo;
        private String activityName;
        private int activityType;
        private int goodsId;
        private String bannerPic;
        private String sharePic;
        private String htmlAddress;
        private long startTime;
        private long endTime;
        private String awardPrice;
        private int activityStatus;
        private long createTime;
        private long updateTime;
        private String goodsName;


        public int getGuessActivityId() {
            return guessActivityId;
        }

        public void setGuessActivityId(int guessActivityId) {
            this.guessActivityId = guessActivityId;
        }

        public String getIssueNo() {
            return issueNo;
        }

        public void setIssueNo(String issueNo) {
            this.issueNo = issueNo;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }


        public int getActivityType() {
            return activityType;
        }

        public void setActivityType(int activityType) {
            this.activityType = activityType;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getBannerPic() {
            return bannerPic;
        }

        public void setBannerPic(String bannerPic) {
            this.bannerPic = bannerPic;
        }

        public String getSharePic() {
            return sharePic;
        }

        public void setSharePic(String sharePic) {
            this.sharePic = sharePic;
        }

        public String getHtmlAddress() {
            return htmlAddress;
        }

        public void setHtmlAddress(String htmlAddress) {
            this.htmlAddress = htmlAddress;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getAwardPrice() {
            return awardPrice;
        }

        public void setAwardPrice(String awardPrice) {
            this.awardPrice = awardPrice;
        }

        public int getActivityStatus() {
            return activityStatus;
        }

        public void setActivityStatus(int activityStatus) {
            this.activityStatus = activityStatus;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }
    }
}

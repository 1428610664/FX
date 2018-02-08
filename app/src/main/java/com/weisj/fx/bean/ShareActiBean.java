package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class ShareActiBean {

    /**
     * code : 1
     * msg : 获取所有分享的竞猜活动成功！
     * data : [{"guessActivityId":1,"issueNo":"2016120501","activityName":"有奖竞猜小龙虾的价格 最高可获得1000元等值商品","activityRule":null,"activityType":0,"goodsId":0,"bannerPic":null,"sharePic":"http://shop.fx-sf.com/Public/backend/upload/images/2016/12/20161205150800_41883.jpg","htmlAddress":null,"startTime":null,"endTime":null,"awardPrice":null,"activityStatus":1,"createTime":null,"updateTime":null,"goodsName":"莱克6-8钱口味虾麻辣小龙虾香辣熟食口味虾3斤装净虾1.5斤","guessPersons":1,"isHaveAward":1,"awardCount":1}]
     */

    private String code;
    private String msg;
    /**
     * guessActivityId : 1
     * issueNo : 2016120501
     * activityName : 有奖竞猜小龙虾的价格 最高可获得1000元等值商品
     * activityRule : null
     * activityType : 0
     * goodsId : 0
     * bannerPic : null
     * sharePic : http://shop.fx-sf.com/Public/backend/upload/images/2016/12/20161205150800_41883.jpg
     * htmlAddress : null
     * startTime : null
     * endTime : null
     * awardPrice : null
     * activityStatus : 1
     * createTime : null
     * updateTime : null
     * goodsName : 莱克6-8钱口味虾麻辣小龙虾香辣熟食口味虾3斤装净虾1.5斤
     * guessPersons : 1
     * isHaveAward : 1
     * awardCount : 1
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
        private String activityName;
        private int activityType;
        private int goodsId;
        private String sharePic;
        private int activityStatus;
        private String goodsName;
        private int guessPersons;
        private int isHaveAward;
        private int awardCount;

        public int getGuessActivityId() {
            return guessActivityId;
        }

        public void setGuessActivityId(int guessActivityId) {
            this.guessActivityId = guessActivityId;
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

        public String getSharePic() {
            return sharePic;
        }

        public void setSharePic(String sharePic) {
            this.sharePic = sharePic;
        }

        public int getActivityStatus() {
            return activityStatus;
        }

        public void setActivityStatus(int activityStatus) {
            this.activityStatus = activityStatus;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getGuessPersons() {
            return guessPersons;
        }

        public void setGuessPersons(int guessPersons) {
            this.guessPersons = guessPersons;
        }

        public int getIsHaveAward() {
            return isHaveAward;
        }

        public void setIsHaveAward(int isHaveAward) {
            this.isHaveAward = isHaveAward;
        }

        public int getAwardCount() {
            return awardCount;
        }

        public void setAwardCount(int awardCount) {
            this.awardCount = awardCount;
        }
    }
}

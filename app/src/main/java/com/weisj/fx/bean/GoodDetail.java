package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class GoodDetail {


    /**
     * code : 1
     * msg : 操作成功!
     * data : {"images":["http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082513_84929.png","http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082446_38876.png","http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082451_19646.png","http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082456_58138.png","http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082504_31460.png"],"goodsId":1,"goodsName":"九峰黄金李(40个装)","goodsSn":"ASAFDGA","brandId":1,"isCertified":1,"directSupply":1,"sendHour":8,"sendCity":"深圳","freight":"0","delMoney":"5.00","commission":"0","goodsList":[{"goodsId":3,"goodsName":null,"img1":"1"},{"goodsId":10,"goodsName":null,"img1":"1"},{"goodsId":1,"goodsName":null,"img1":"http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082513_84929.png"}]}
     */

    private String code;
    private String msg;
    /**
     * images : ["http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082513_84929.png","http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082446_38876.png","http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082451_19646.png","http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082456_58138.png","http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082504_31460.png"]
     * goodsId : 1
     * goodsName : 九峰黄金李(40个装)
     * goodsSn : ASAFDGA
     * brandId : 1
     * isCertified : 1
     * directSupply : 1
     * sendHour : 8
     * sendCity : 深圳
     * freight : 0
     * delMoney : 5.00
     * commission : 0
     * goodsList : [{"goodsId":3,"goodsName":null,"img1":"1"},{"goodsId":10,"goodsName":null,"img1":"1"},{"goodsId":1,"goodsName":null,"img1":"http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082513_84929.png"}]
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
        private int goodsId;
        private String goodsName;
        private String goodsSn;
        private int brandId;
        private int isCertified;
        private int directSupply;
        private String sendHour;
        private String sendCity;
        private String freight;
        private String delMoney;
        private String commission;
		private String price;
        private String describe;
        private String website;
        private String shareTitle;
        private String shareDes;
        private String goodsPoint;
        private int commentNum;
        private List<String> images;
        private String brandCellphone;
        private String activityRule;
        private String htmlAddress;
        private String activityName;
        private  String sharePic;

        public String getSharePic() {
            return sharePic;
        }

        public void setSharePic(String sharePic) {
            this.sharePic = sharePic;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getHtmlAddress() {
            return htmlAddress;
        }

        public void setHtmlAddress(String htmlAddress) {
            this.htmlAddress = htmlAddress;
        }

        public String getActivityRule() {
            return activityRule;
        }

        public void setActivityRule(String activityRule) {
            this.activityRule = activityRule;
        }

        public String getBrandCellphone() {
            return brandCellphone;
        }

        public void setBrandCellphone(String brandCellphone) {
            this.brandCellphone = brandCellphone;
        }

        public String getGoodsPoint() {
            return goodsPoint;
        }

        public void setGoodsPoint(String goodsPoint) {
            this.goodsPoint = goodsPoint;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getShareDes() {
            return shareDes;
        }

        public void setShareDes(String shareDes) {
            this.shareDes = shareDes;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }
		 public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        /**
         * goodsId : 3
         * goodsName : null
         * img1 : 1
         */

        private List<GoodsListEntity> goodsList;

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public void setGoodsSn(String goodsSn) {
            this.goodsSn = goodsSn;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public void setIsCertified(int isCertified) {
            this.isCertified = isCertified;
        }

        public void setDirectSupply(int directSupply) {
            this.directSupply = directSupply;
        }

        public void setSendHour(String sendHour) {
            this.sendHour = sendHour;
        }

        public void setSendCity(String sendCity) {
            this.sendCity = sendCity;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public void setDelMoney(String delMoney) {
            this.delMoney = delMoney;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public void setGoodsList(List<GoodsListEntity> goodsList) {
            this.goodsList = goodsList;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public String getGoodsSn() {
            return goodsSn;
        }

        public int getBrandId() {
            return brandId;
        }

        public int getIsCertified() {
            return isCertified;
        }

        public int getDirectSupply() {
            return directSupply;
        }

        public String getSendHour() {
            return sendHour;
        }

        public String getSendCity() {
            return sendCity;
        }

        public String getFreight() {
            return freight;
        }

        public String getDelMoney() {
            return delMoney;
        }

        public String getCommission() {
            return commission;
        }

        public List<String> getImages() {
            return images;
        }

        public List<GoodsListEntity> getGoodsList() {
            return goodsList;
        }

        public static class GoodsListEntity {
            private int goodsId;
            private String goodsName;
            private String img1;
            private String price;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public String getImg1() {
                return img1;
            }
        }
    }
}

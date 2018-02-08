package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */

public class HomeBanner {


    /**
     * errorCode : null
     * message : success
     * result : {"hotGoods":[{"goodsImagePath":"goods/20170123/148513718372621783.jpg","name":"微信支付成功1.01元测试","title":"","productId":2064,"productName":"1","salePrice":1.01,"goodsSn":"YW11837A43EA73"},{"goodsImagePath":"goods/20161121/147972475829634169.jpg","name":"Claire的测试商品","title":"","productId":1998,"productName":"5斤装","salePrice":1.32,"goodsSn":"YW713254952BA5"},{"goodsImagePath":"goods/20161215/148178700271656584.jpg,goods/20161215/148178700340568061.jpg,goods/20161215/148178700426387567.jpg,goods/20161215/148178700466381999.jpg,goods/20161215/148178700545317708.jpg,goods/20161215/148178700624386651.jpg","name":"【法国拉菲】奥希耶红A干红葡萄酒 极富紫罗兰和樱桃的香气，酒体如春日初上","title":"法国红酒","productId":1964,"productName":"单瓶750ML","salePrice":79,"goodsSn":"YW6D1CD928DD7D"},{"goodsImagePath":"goods/20160806/147045730148606177.jpg,goods/20160806/147045730470871924.jpg,goods/20160806/147045731123336609.jpg,goods/20160806/147045731432285041.jpg","name":"烟台栖霞红富士苹果","title":"2016年新鲜果，现摘现发","productId":387,"productName":"中大果5斤装（80mm）","salePrice":0.01,"goodsSn":"YWA2183733B968"},{"goodsImagePath":"goods/20161223/148248919367858483.jpg,goods/20161223/148248919406858067.jpg,goods/20161223/148248919435826761.jpg,goods/20161223/148248919463843454.jpg","name":"平和琯溪三红蜜柚","title":"颜值！颜值！","productId":1838,"productName":"四粒装","salePrice":56.9,"goodsSn":"YW12B299CAC540"},{"goodsImagePath":"goods/20160908/147331677928494995.jpg,goods/20160908/147331816361408916.jpg,goods/20160908/147330227223806821.jpg","name":"膏满堂-兴化大闸蟹","title":"全民半价品好蟹，手慢无！","productId":1375,"productName":"钜惠款：5对 (公2.5-3.0两5只，母1.8-2.3两5只）","salePrice":89.9,"goodsSn":"YWED38CAA0FCBB"},{"goodsImagePath":"goods/20161214/148169882325495668.jpg,goods/20161214/148169882420375873.jpg,goods/20161214/148169882515365678.jpg,goods/20161214/148169882623478350.jpg","name":"进口雪梨","title":"海南雪梨","productId":2010,"productName":"20斤/1箱","salePrice":0.01,"goodsSn":"YWFF2A2D138BC1"}],"material":[{"id":85,"createDate":1481799445000,"modifyDate":1487673018000,"title":"温补羊肉","content":null,"url":"http://img.sfddj.com/action/all/2016shendian/index.html?orgin=ddjapp","orderList":0,"isLink":true,"imageUrl":"material/20170213/148695621701517653.png","groupId":12},{"id":86,"createDate":1481799650000,"modifyDate":1487233548000,"title":"寻味","content":null,"url":"https://www.shhyxypsx.com/app/v1/365webcall/toApp","orderList":0,"isLink":true,"imageUrl":"material/20170213/148695625675671701.png","groupId":12},{"id":100,"createDate":1482837046000,"modifyDate":1487666139000,"title":"茶暖人心","content":null,"url":"https://www.shhyxypsx.com/app/v1","orderList":0,"isLink":true,"imageUrl":"material/20170213/148695633493174369.png","groupId":12},{"id":156,"createDate":1490062879000,"modifyDate":1490062927000,"title":"周四必抢","content":null,"url":"https://www.shhyxypsx.com/robThursday/toRob","orderList":0,"isLink":true,"imageUrl":"material/20170321/149006286252391385.jpg","groupId":12}]}
     */

//    private Object errorCode;
    private String message;
    private ResultBean result;

//    public Object getErrorCode() {
//        return errorCode;
//    }
//
//    public void setErrorCode(Object errorCode) {
//        this.errorCode = errorCode;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<HotGoodsBean> hotGoods;
        private List<MaterialBean> material;

        public List<HotGoodsBean> getHotGoods() {
            return hotGoods;
        }

        public void setHotGoods(List<HotGoodsBean> hotGoods) {
            this.hotGoods = hotGoods;
        }

        public List<MaterialBean> getMaterial() {
            return material;
        }

        public void setMaterial(List<MaterialBean> material) {
            this.material = material;
        }

        public static class HotGoodsBean {
            /**
             * goodsImagePath : goods/20170123/148513718372621783.jpg
             * name : 微信支付成功1.01元测试
             * title :
             * productId : 2064
             * productName : 1
             * salePrice : 1.01
             * goodsSn : YW11837A43EA73
             */

            private String goodsImagePath;
            private String name;
            private String title;
            private int productId;
            private String productName;
            private String salePrice;
            private String goodsSn;

            public String getGoodsImagePath() {
                return goodsImagePath;
            }

            public void setGoodsImagePath(String goodsImagePath) {
                this.goodsImagePath = goodsImagePath;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getSalePrice() {
                return salePrice;
            }

            public void setSalePrice(String salePrice) {
                this.salePrice = salePrice;
            }

            public String getGoodsSn() {
                return goodsSn;
            }

            public void setGoodsSn(String goodsSn) {
                this.goodsSn = goodsSn;
            }
        }

        public static class MaterialBean {
            /**
             * id : 85
             * createDate : 1481799445000
             * modifyDate : 1487673018000
             * title : 温补羊肉
             * content : null
             * url : http://img.sfddj.com/action/all/2016shendian/index.html?orgin=ddjapp
             * orderList : 0
             * isLink : true
             * imageUrl : material/20170213/148695621701517653.png
             * groupId : 12
             */

            private int id;
            private long createDate;
            private long modifyDate;
            private String title;
            private String url;
            private int orderList;
            private boolean isLink;
            private String imageUrl;
            private String content;
            private HomeCouponbean.DataEntity.SingleCouponListEntity entity;

            public HomeCouponbean.DataEntity.SingleCouponListEntity getEntity() {
                return entity;
            }

            public void setEntity(HomeCouponbean.DataEntity.SingleCouponListEntity entity) {
                this.entity = entity;
            }

            public String getContent() {
                return content != null?content:"";
            }

            public void setContent(String content) {
                this.content = content;
            }

            private int groupId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public long getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(long modifyDate) {
                this.modifyDate = modifyDate;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }


            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getOrderList() {
                return orderList;
            }

            public void setOrderList(int orderList) {
                this.orderList = orderList;
            }

            public boolean isIsLink() {
                return isLink;
            }

            public void setIsLink(boolean isLink) {
                this.isLink = isLink;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public int getGroupId() {
                return groupId;
            }

            public void setGroupId(int groupId) {
                this.groupId = groupId;
            }
        }
    }
}

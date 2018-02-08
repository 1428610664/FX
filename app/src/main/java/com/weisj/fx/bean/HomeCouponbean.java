package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class HomeCouponbean {

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
        private int categoryId;

        private List<SingleCouponListEntity> districtCouponList;

        private List<CategoryBean.DataEntity> categoryList;

        private List<SingleCouponListEntity> singleCouponList;

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public void setDistrictCouponList(List<SingleCouponListEntity> districtCouponList) {
            this.districtCouponList = districtCouponList;
        }

        public void setCategoryList(List<CategoryBean.DataEntity> categoryList) {
            this.categoryList = categoryList;
        }

        public void setSingleCouponList(List<SingleCouponListEntity> singleCouponList) {
            this.singleCouponList = singleCouponList;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public List<SingleCouponListEntity> getDistrictCouponList() {
            return districtCouponList;
        }

        public List<CategoryBean.DataEntity> getCategoryList() {
            return categoryList;
        }

        public List<SingleCouponListEntity> getSingleCouponList() {
            return singleCouponList;
        }


        public static class SingleCouponListEntity {
            private int couponId;
            private String delMoney;
            private int templateId;
            private String htmlAddress;
            private String couponPic;
            private String goodsName;
            private String brandName;
            private String brandLogo;
            private int categoryId;
            private String couponTitle;
            private String sharePic;
            private int mutiFlag;
            private int goodsId;

            public String getSharePic() {
                return sharePic;
            }

            public void setSharePic(String sharePic) {
                this.sharePic = sharePic;
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getBrandLogo() {
                return brandLogo;
            }

            public void setBrandLogo(String brandLogo) {
                this.brandLogo = brandLogo;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getCouponTitle() {
                return couponTitle;
            }

            public void setCouponTitle(String couponTitle) {
                this.couponTitle = couponTitle;
            }

            public int getMutiFlag() {
                return mutiFlag;
            }

            public void setMutiFlag(int mutiFlag) {
                this.mutiFlag = mutiFlag;
            }

            public void setCouponId(int couponId) {
                this.couponId = couponId;
            }

            public void setDelMoney(String delMoney) {
                this.delMoney = delMoney;
            }

            public void setTemplateId(int templateId) {
                this.templateId = templateId;
            }

            public void setHtmlAddress(String htmlAddress) {
                this.htmlAddress = htmlAddress;
            }

            public void setCouponPic(String couponPic) {
                this.couponPic = couponPic;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public int getCouponId() {
                return couponId;
            }

            public String getDelMoney() {
                return delMoney;
            }

            public int getTemplateId() {
                return templateId;
            }

            public String getHtmlAddress() {
                return htmlAddress;
            }

            public String getCouponPic() {
                return couponPic;
            }

            public String getGoodsName() {
                return goodsName;
            }

        }
    }
}

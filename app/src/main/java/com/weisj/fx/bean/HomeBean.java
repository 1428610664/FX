package com.weisj.fx.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class HomeBean {


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

        private List<DistrictGoodsListEntity> districtGoodsList;

        private List<HomeCouponbean.DataEntity.SingleCouponListEntity> districtCouponList;

        private List<DistrictGoodsListEntity> countryGoodsList;
        private String memberName;
        private String guanName;
        private String highCommissionPicUrl;

        public String getHighCommissionPicUrl() {
            return highCommissionPicUrl;
        }

        public void setHighCommissionPicUrl(String highCommissionPicUrl) {
            this.highCommissionPicUrl = highCommissionPicUrl;
        }

        public String getGuanName() {
            return guanName;
        }

        public void setGuanName(String guanName) {
            this.guanName = guanName;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public void setDistrictGoodsList(List<DistrictGoodsListEntity> districtGoodsList) {
            this.districtGoodsList = districtGoodsList;
        }

        public void setDistrictCouponList(List<HomeCouponbean.DataEntity.SingleCouponListEntity> districtCouponList) {
            this.districtCouponList = districtCouponList;
        }

        public void setCountryGoodsList(List<DistrictGoodsListEntity> countryGoodsList) {
            this.countryGoodsList = countryGoodsList;
        }

        public List<DistrictGoodsListEntity> getDistrictGoodsList() {
            return districtGoodsList;
        }

        public List<HomeCouponbean.DataEntity.SingleCouponListEntity> getDistrictCouponList() {
            return districtCouponList;
        }

        public List<DistrictGoodsListEntity> getCountryGoodsList() {
            return countryGoodsList;
        }

        public static class DistrictGoodsListEntity {
            private int goodsId;
            private String goodsName;
            private String img1;
            private String img2;
            private String img3;
            private String img4;
            private String img5;
            private String img6;
            private String img7;
            private String delMoney;
            private String price;
            private String describe;
            private String website;
            private String unit;
            private String delMoneyFinish;

            public String getDelMoneyFinish() {
                if (delMoney != null && price != null) {
                    BigDecimal bDelMoney = BigDecimal.valueOf(Double.valueOf(delMoney));
                    BigDecimal bMoney = new BigDecimal(price);
                    BigDecimal newMoney = bMoney.subtract(bDelMoney);
                    return newMoney.setScale(2).toString();
                } else {
                    return "0";
                }
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getImg4() {
                return img4;
            }

            public void setImg4(String img4) {
                this.img4 = img4;
            }

            public String getImg5() {
                return img5;
            }

            public void setImg5(String img5) {
                this.img5 = img5;
            }

            public String getImg6() {
                return img6;
            }

            public void setImg6(String img6) {
                this.img6 = img6;
            }

            public String getImg7() {
                return img7;
            }

            public void setImg7(String img7) {
                this.img7 = img7;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
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


            public void setPrice(String price) {
                this.price = price;
            }


            public void setDelMoney(String delMoney) {
                this.delMoney = delMoney;
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


            public String getPrice() {
                return price;
            }

            public String getDelMoney() {
                return delMoney;
            }

            public String getDescribe() {
                return describe;
            }

        }

    }
}

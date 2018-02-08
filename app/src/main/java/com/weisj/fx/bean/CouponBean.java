package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class CouponBean {


    /**
     * code : 1
     * msg : 操作成功!
     * data : [{"couponId":1,"delMoney":5,"beginDate":1469671290000,"endDate":1469671293000,"templateId":0,"htmlAddress":"1","createTime":1469671318000,"couponPic":"1","goodsName":"九峰黄金李(40个装)","img1":"wrtgwet"}]
     */

    private String code;
    private String msg;
    /**
     * couponId : 1
     * delMoney : 5
     * beginDate : 1469671290000
     * endDate : 1469671293000
     * templateId : 0
     * htmlAddress : 1
     * createTime : 1469671318000
     * couponPic : 1
     * goodsName : 九峰黄金李(40个装)
     * img1 : wrtgwet
     */

    private List<HomeCouponbean.DataEntity.SingleCouponListEntity> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<HomeCouponbean.DataEntity.SingleCouponListEntity> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<HomeCouponbean.DataEntity.SingleCouponListEntity> getData() {
        return data;
    }


}

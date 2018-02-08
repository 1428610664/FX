package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class GoodBean {
    /**
     * code : 1
     * msg : 获取验证码成功!
     */

    private String code;
    private String msg;
    private List<HomeBean.DataEntity.DistrictGoodsListEntity> data;

    public List<HomeBean.DataEntity.DistrictGoodsListEntity> getData() {
        return data;
    }

    public void setData(List<HomeBean.DataEntity.DistrictGoodsListEntity> data) {
        this.data = data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

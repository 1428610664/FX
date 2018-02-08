package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class SearchBrand {


    /**
     * code : 1
     * msg : 操作成功!
     * data : [{"brandId":0,"brandName":"所有品牌","districtId":"2"}]
     */

    private String code;
    private String msg;
    /**
     * brandId : 0
     * brandName : 所有品牌
     * districtId : 2
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
        private int brandId;
        private String brandName;
        private String districtId;

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public int getBrandId() {
            return brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public String getDistrictId() {
            return districtId;
        }
    }
}

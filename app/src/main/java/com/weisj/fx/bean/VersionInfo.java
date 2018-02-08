package com.weisj.fx.bean;

/**
 * Created by Administrator on 2016/2/24 0024.
 */
public class VersionInfo {


    /**
     * code : 1
     * msg : 操作成功!
     * data : {"code":0,"version":"V1.0","website":"www.baidu.com","des":"no"}
     */

    private String code;
    private String msg;
    /**
     * code : 0
     * version : V1.0
     * website : www.baidu.com
     * des : no
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
        private int code;
        private String version;
        private String website;
        private String des;

        public void setCode(int code) {
            this.code = code;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getCode() {
            return code;
        }

        public String getVersion() {
            return version;
        }

        public String getWebsite() {
            return website;
        }

        public String getDes() {
            return des;
        }
    }
}

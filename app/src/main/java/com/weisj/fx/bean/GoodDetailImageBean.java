package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class GoodDetailImageBean {


    /**
     * code : 1
     * msg : 操作成功!
     * data : ["http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082446_38876.png","http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082451_19646.png","http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082456_58138.png","http://bg.wsjzb.cn/Public/upload/image/20160727/20160727082504_31460.png"]
     */

    private String code;
    private String msg;
    private List<String> data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<String> getData() {
        return data;
    }
}

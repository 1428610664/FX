package com.weisj.fx.bean;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class ExpressInfo {
    private String com;
    private String num;

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public ExpressInfo(String com, String num) {
        this.com = com;
        this.num = num;
    }
}

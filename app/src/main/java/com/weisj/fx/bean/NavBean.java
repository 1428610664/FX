package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class NavBean {


    /**
     * code : 1
     * msg : 操作成功!
     * data : [{"menuId":1,"content":"首页","image":"1.jpg","orderNum":1,"menuCode":"navigation","destination":"homepage","imageClick":"a1.jpg"},{"menuId":2,"content":"分类","image":"2.jpg","orderNum":2,"menuCode":"navigation","destination":"category","imageClick":"a2.jpg"},{"menuId":3,"content":"优惠券","image":"3.jpg","orderNum":3,"menuCode":"navigation","destination":"coupon","imageClick":"a3.jpg"},{"menuId":4,"content":"订单","image":"4.jpg","orderNum":4,"menuCode":"navigation","destination":"order","imageClick":"a4.jpg"},{"menuId":5,"content":"我的","image":"5.jpg","orderNum":5,"menuCode":"navigation","destination":"mine","imageClick":"a5.jpg"}]
     */

    private String code;
    private String msg;
    /**
     * menuId : 1
     * content : 首页
     * image : 1.jpg
     * orderNum : 1
     * menuCode : navigation
     * destination : homepage
     * imageClick : a1.jpg
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
        private int menuId;
        private String content;
        private String image;
        private int orderNum;
        private String menuCode;
        private String destination;
        private String imageClick;

        public void setMenuId(int menuId) {
            this.menuId = menuId;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public void setMenuCode(String menuCode) {
            this.menuCode = menuCode;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public void setImageClick(String imageClick) {
            this.imageClick = imageClick;
        }

        public int getMenuId() {
            return menuId;
        }

        public String getContent() {
            return content;
        }

        public String getImage() {
            return image;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public String getMenuCode() {
            return menuCode;
        }

        public String getDestination() {
            return destination;
        }

        public String getImageClick() {
            return imageClick;
        }
    }
}

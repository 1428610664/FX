package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/2 0002.
 */

public class GoodPoint {


    /**
     * code : 1
     * msg : 操作成功!
     * data : [{"content":"味道还不错。","pics":[],"comment_id":22,"user_name":"来林夕，秀美梦","add_time":"11-01 13:28","comment_rank":4},{"content":"","pics":[],"comment_id":21,"user_name":"零下一度","add_time":"11-01 12:42","comment_rank":5},{"content":"商品不错，物流非常快！","pics":[],"comment_id":20,"user_name":"刘海峰","add_time":"11-01 10:27","comment_rank":5},{"content":"好吃","pics":[],"comment_id":19,"user_name":"小柒酱ななじゃん ","add_time":"11-01 09:45","comment_rank":5},{"content":"推荐给同事买了，好吃","pics":[],"comment_id":18,"user_name":"小柒酱ななじゃん ","add_time":"11-01 09:44","comment_rank":5},{"content":"","pics":[],"comment_id":17,"user_name":"仙美","add_time":"11-01 09:21","comment_rank":5},{"content":"两大包，很好吃，快递给力","pics":[],"comment_id":16,"user_name":"狮子","add_time":"10-31 17:01","comment_rank":5},{"content":"售后那位帅哥的声音好听。","pics":[],"comment_id":15,"user_name":"乐新七公主","add_time":"10-31 15:29","comment_rank":5},{"content":"顺丰快递，保温包装，味道一流，好评","pics":[],"comment_id":14,"user_name":"杨峰","add_time":"10-31 14:52","comment_rank":5},{"content":"送货速度很快，还不错！","pics":[],"comment_id":13,"user_name":"理悟","add_time":"10-31 13:44","comment_rank":5}]
     */

    private String code;
    private String msg;
    /**
     * content : 味道还不错。
     * pics : []
     * comment_id : 22
     * user_name : 来林夕，秀美梦
     * add_time : 11-01 13:28
     * comment_rank : 4
     */

    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String content;
        private int comment_id;
        private String user_name;
        private String add_time;
        private int comment_rank;
        private List<String> pics;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getComment_rank() {
            return comment_rank;
        }

        public void setComment_rank(int comment_rank) {
            this.comment_rank = comment_rank;
        }

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }
    }
}

package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class NoticeBean {



    private String code;
    private String msg;


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
        private String title;
        private String keywords;
        private String websibe;
        private int article_id;
        private String file_url;
        private String create_time;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public void setWebsibe(String websibe) {
            this.websibe = websibe;
        }

        public void setArticle_id(int article_id) {
            this.article_id = article_id;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTitle() {
            return title;
        }

        public String getKeywords() {
            return keywords;
        }

        public String getWebsibe() {
            return websibe;
        }

        public int getArticle_id() {
            return article_id;
        }

        public String getFile_url() {
            return file_url;
        }

        public String getCreate_time() {
            return create_time;
        }
    }
}

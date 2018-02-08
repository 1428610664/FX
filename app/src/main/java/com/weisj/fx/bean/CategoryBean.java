package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class CategoryBean {

    /**
     * code : 1
     * msg : 成功
     * data : [{"categoryId":1,"parentId":0,"categoryName":"水果","categoryPic":""},{"categoryId":2,"parentId":0,"categoryName":"蔬菜","categoryPic":""},{"categoryId":3,"parentId":0,"categoryName":"精品肉类","categoryPic":""},{"categoryId":4,"parentId":0,"categoryName":"海鲜水产","categoryPic":""},{"categoryId":5,"parentId":0,"categoryName":"速冻食品","categoryPic":""},{"categoryId":6,"parentId":0,"categoryName":"速食冻品","categoryPic":null},{"categoryId":7,"parentId":0,"categoryName":"节日食品","categoryPic":null},{"categoryId":8,"parentId":0,"categoryName":"饼干糕点","categoryPic":null},{"categoryId":9,"parentId":0,"categoryName":"糖果/巧克力","categoryPic":null},{"categoryId":10,"parentId":0,"categoryName":"坚果炒货","categoryPic":null},{"categoryId":11,"parentId":0,"categoryName":"休闲零食","categoryPic":null},{"categoryId":12,"parentId":0,"categoryName":"白酒","categoryPic":null},{"categoryId":13,"parentId":0,"categoryName":"啤酒","categoryPic":null},{"categoryId":14,"parentId":0,"categoryName":"南北干货","categoryPic":null},{"categoryId":15,"parentId":0,"categoryName":"葡萄酒","categoryPic":null}]
     */

    private String code;
    private String msg;
    /**
     * categoryId : 1
     * parentId : 0
     * categoryName : 水果
     * categoryPic :
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
        private int categoryId;
        private int parentId;
        private String categoryName;
        private String categoryPic;

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public void setCategoryPic(String categoryPic) {
            this.categoryPic = categoryPic;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public int getParentId() {
            return parentId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public String getCategoryPic() {
            return categoryPic;
        }
    }
}

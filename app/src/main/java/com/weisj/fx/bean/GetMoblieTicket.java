package com.weisj.fx.bean;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
public class GetMoblieTicket {

    /**
     * code : 1
     * msg : 输入的验证码正确！
     * data : {"ticket_code":"285794532923"}
     */

    private String code;
    private String msg;
    /**
     * ticket_code : 285794532923
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
        private String ticket_code;

        public void setTicket_code(String ticket_code) {
            this.ticket_code = ticket_code;
        }

        public String getTicket_code() {
            return ticket_code;
        }
    }

}


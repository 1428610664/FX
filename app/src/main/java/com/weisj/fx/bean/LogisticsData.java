package com.weisj.fx.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/1/9 0009.
 */
public class LogisticsData {

    private String errorCode;
    private String message;
    private DataResult result;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public DataResult getResult() {
        return result;
    }

    public void setResult(DataResult result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataResult{
        private String bno;
        private int state;
        private String stateMsg;
        private List<DataEntity> details;

        public String getBno() {
            return bno;
        }

        public void setBno(String bno) {
            this.bno = bno;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getStateMsg() {
            return stateMsg;
        }

        public void setStateMsg(String stateMsg) {
            this.stateMsg = stateMsg;
        }

        public List<DataEntity> getDetails() {
            return details;
        }

        public void setDetails(List<DataEntity> details) {
            this.details = details;
        }
    }

    public static class DataEntity {
        private String time;
        private String context;

        public void setTime(String time) {
            this.time = time;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getTime() {
            return time;
        }

        public String getContext() {
            return context;
        }
    }
}

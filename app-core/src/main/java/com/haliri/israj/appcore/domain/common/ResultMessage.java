package com.haliri.israj.appcore.domain.common;

/**
 * Created by israjhaliri on 11/21/17.
 */
public class ResultMessage {

    private int status;
    private String error;
    private String message;
    private Object contentData;

    private static ResultMessage instance = null;

    public ResultMessage() {
    }

    public static ResultMessage getInstance() {
        if (instance == null) {
            instance = new ResultMessage();
        }
        return instance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContentData() {
        return contentData;
    }

    public void setContentData(Object contentData) {
        this.contentData = contentData;
    }

    public static void setInstance(ResultMessage instance) {
        ResultMessage.instance = instance;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", contentData=" + contentData +
                '}';
    }
}

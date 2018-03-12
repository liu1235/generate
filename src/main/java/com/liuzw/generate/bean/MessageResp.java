package com.liuzw.generate.bean;

import java.io.Serializable;

/**
 * @author 刘泽伟
 */

public class MessageResp implements Serializable {

    private static final long serialVersionUID = 3760433194625689330L;

    private String result;

    private String resultDesc;

    private String statusCode;

    public MessageResp() {
        this.result = Boolean.TRUE.toString();
    }

    public MessageResp(String result, String resultDesc) {
        this.result = result;
        this.resultDesc = resultDesc;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public void setError(String resultDesc) {
        this.result = Boolean.FALSE.toString();
        this.resultDesc = resultDesc;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}

package com.slj.response;

public enum ResultEnum implements CommonEnum {
    SUCCESS(200, "success"),

    FAILED(500, "failed");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

}

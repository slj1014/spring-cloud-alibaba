package com.slj.response;


import com.alibaba.fastjson.JSON;

public class ResultUtils {
    public static Result success(Object obj) {
        return getResult(ResultEnum.SUCCESS, obj);
    }

    public static Result success() {
        return getResult(ResultEnum.SUCCESS, null);
    }

    public static Result failed() {
        return getResult(ResultEnum.FAILED, null);
    }

    public static Result getResult(ResultEnum re) {
        return getResult(re, null);
    }

    public static Result getResult(ResultEnum re, Object obj) {
        return getResult(re.getCode(), re.getMessage(), obj);
    }

    public static Result getResult(Integer code, String msg) {
        return getResult(code, msg, null);
    }


    public static Result getResult(Integer code, String msg, Object obj) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(obj);
        return result;
    }

    public static <T> T getObjectData(Object obj, Class<T> tClass) {
        return JSON.parseObject(JSON.toJSONString(obj), tClass);
    }
}

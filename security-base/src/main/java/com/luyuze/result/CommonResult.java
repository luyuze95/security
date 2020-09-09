package com.luyuze.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 自定义响应结构
 */
@Data
public class CommonResult {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public CommonResult() {
    }
    public CommonResult(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }
    public CommonResult(String message, Object data) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public CommonResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResult ok() {
        return new CommonResult(null);
    }
    public static CommonResult ok(String message) {
        return new CommonResult(message, null);
    }
    public static CommonResult ok(Object data) {
        return new CommonResult(data);
    }
    public static CommonResult ok(String message, Object data) {
        return new CommonResult(message, data);
    }

    public static CommonResult build(Integer code, String message) {
        return new CommonResult(code, message, null);
    }

    public static CommonResult build(Integer code, String message, Object data) {
        return new CommonResult(code, message, data);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }


    /**
     * JSON字符串转成 CommonResult 对象
     * @param json
     * @return
     */
    public static CommonResult format(String json) {
        try {
            return JSON.parseObject(json, CommonResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

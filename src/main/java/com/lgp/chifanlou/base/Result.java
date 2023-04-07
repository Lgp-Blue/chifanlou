package com.lgp.chifanlou.base;


//封装所有请求返回的响应对象
public class Result<T> {
    private Integer status=10000;
    private String msg="success";
    private T data;

    public Integer getStatus() {
        return status;
    }

    public static Result ok(){
        return new Result();
    }

    public static Result fail(){
        Result fail = new Result();
        fail.status = 0;
        fail.msg = "fail";
        return fail;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
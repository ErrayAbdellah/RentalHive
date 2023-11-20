package com.rentalHive.rentalHive.model.dto;

public class CustomResponse<T> {
    private String msg;
    private T data;

    public CustomResponse(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}

package com.example.library.data;

import java.util.List;

public class CommentData {
    private int code;
    private String message;
    private Data data;//自定义的Data类

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private int total;
        private List<CommentDetail> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<CommentDetail> getList() {
            return list;
        }

        public void setList(List<CommentDetail> list) {
            this.list = list;
        }
    }
}

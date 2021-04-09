package com.example.library.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDigest {
    /**
     * data : [{"id":2,"title":"离散数学","date":"2021-03-22","public":true},{"id":5,"title":"","date":"2021-03-23","public":false},{"id":6,"title":"","date":"2021-03-28","public":false},{"id":7,"title":"","date":"2021-03-28","public":false},{"id":8,"title":"","date":"2021-03-28","public":false},{"id":9,"title":"离散数学","date":"2021-03-28","public":true},{"id":11,"title":"幸福关系的7段旅程","date":"2021-03-28","public":true},{"id":13,"title":"幸福关系的7段旅程","date":"2021-04-03","public":true},{"id":14,"title":"书摘标题1","date":"2021-04-04","public":true},{"id":15,"title":"人鼠之间","date":"2021-04-04","public":true},{"id":16,"title":"向着少女与光","date":"2021-04-04","public":true},{"id":17,"title":"向着少女与光","date":"2021-04-04","public":true},{"id":18,"title":"向着少女与光","date":"2021-04-04","public":true},{"id":19,"title":"向着少女与光","date":"2021-04-04","public":true},{"id":20,"title":"向着少女与光","date":"2021-04-08","public":true},{"id":21,"title":"波斯之剑","date":"2021-04-08","public":true},{"id":22,"title":"波斯之剑","date":"2021-04-08","public":true},{"id":23,"title":"偏见","date":"2021-04-08","public":true},{"id":24,"title":"偏见","date":"2021-04-08","public":true},{"id":25,"title":"","date":"2021-04-09","public":false},{"id":26,"title":"撒旦解放和","date":"2021-04-09","public":true},{"id":27,"title":"撒旦解放和","date":"2021-04-09","public":true}]
     * message : 获取成功
     */

    private List<DataDTO> data;
    private String message;

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataDTO {
        /**
         * id : 2
         * title : 离散数学
         * date : 2021-03-22
         * public : true
         */

        private Integer id;
        private String title;
        private String date;
        @SerializedName("public")
        private Boolean publicX;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Boolean getPublicX() {
            return publicX;
        }

        public void setPublicX(Boolean publicX) {
            this.publicX = publicX;
        }
    }
}

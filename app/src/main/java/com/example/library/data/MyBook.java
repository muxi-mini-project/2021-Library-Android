package com.example.library.data;

import java.util.UUID;

public class MyBook extends BookData.DataBean {
    public boolean mIsMine;

    private UUID nId;

    public MyBook(){
        nId = UUID.randomUUID();
    }


    public boolean isMine() {
        return mIsMine;
    }

    public void setMine(boolean mine) {
        mIsMine = mine;
    }
}

package org.techtown.policy_briefing_app;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {

    private ArrayList<result_data> like = new ArrayList<result_data>();

    @Override
    public void onCreate() {
        //전역 변수 초기화
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setState(result_data rd){
        like.add(rd);
    }

    public ArrayList <result_data> getState(){
        return like;
    }
}

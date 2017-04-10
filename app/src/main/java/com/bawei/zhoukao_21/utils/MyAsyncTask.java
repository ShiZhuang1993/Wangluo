package com.bawei.zhoukao_21.utils;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/9 20:08
 */

public class MyAsyncTask extends AsyncTask<String,Integer,String> {
    @Override
    protected String doInBackground(String... params) {
        try {
            return HttpUtils.getHttp(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

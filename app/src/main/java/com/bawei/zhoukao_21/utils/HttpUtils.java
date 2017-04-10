package com.bawei.zhoukao_21.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * 类用途：
 * 作者：史壮壮
 * 时间：2017/4/9 20:00
 */

public class HttpUtils {
    public static String getHttp(String urls) throws IOException {
        HttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(urls);
        HttpResponse execute = defaultHttpClient.execute(httpPost);
        if (execute.getStatusLine().getStatusCode() == 200) {
            InputStream inputStream = execute.getEntity().getContent();
            byte[] b = new byte[1024];
            int len = 0;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            while ((len = inputStream.read(b)) != -1) {
                stream.write(b, 0, len);
            }
            return stream.toString("utf-8");
        }
        return null;
    }
}

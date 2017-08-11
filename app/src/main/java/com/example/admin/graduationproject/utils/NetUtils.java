package com.example.admin.graduationproject.utils;

import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/7/10.
 */

public class NetUtils {


    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        }

        return mOkHttpClient;
    }

    public static void download(String uri, String savepath, final FileDownloadListener listener) {

        final File file = new File(savepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
        }

        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case -2:
                        listener.onFailed();
                        break;
                    case -1:
                        listener.onSuccess();
                        break;
                    default:
                        listener.onProgress(msg.what);
                        break;

                }

                return false;
            }
        });
        Request request = new Request.Builder().url(uri).build();
        getHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(-2);
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {


                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        handler.sendEmptyMessage(progress);
                    }

                    fos.flush();
                    handler.sendEmptyMessage(-1);

                    if (is != null) {
                        is.close();
                    }

                    if (fos != null) {
                        fos.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public interface FileDownloadListener {
        void onSuccess();

        void onFailed();

        void onProgress(int precent);
    }

}

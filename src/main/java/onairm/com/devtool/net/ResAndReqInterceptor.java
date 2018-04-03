package onairm.com.devtool.net;


import android.app.Application;
import android.util.Log;



import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by apple on 17/11/2.
 */

public class ResAndReqInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        String radom = "";
        String ty = "";
        String v = "";
        String dk = "";
        String userId = "";
        String tn = "";

        Request oldRequest = chain.request();
        HttpUrl build = oldRequest .url().newBuilder()
                .addEncodedQueryParameter("ty", ty)
                .addEncodedQueryParameter("v", v)
                .addEncodedQueryParameter("dk", dk)
                .addEncodedQueryParameter("tn",  tn)
                .addEncodedQueryParameter("userId", userId)
                .build();
        Request newRequest =oldRequest .newBuilder().url(build).build();
        String string = String.format("%s - %s", newRequest.method(), newRequest.url());
        Log.i("HTTP", string);
        return chain.proceed(newRequest);
    }
}

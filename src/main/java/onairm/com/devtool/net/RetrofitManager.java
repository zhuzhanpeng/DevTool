package onairm.com.devtool.net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 17/11/2.
 */

public class RetrofitManager {
    private static RetrofitManager mRetrofitManager;
    private Retrofit mRetrofit;
    private RetrofitManager() {
        initRetrofit();
    }

    public static synchronized RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }

    private void initRetrofit() {
        HttpLoggingInterceptor LoginInterceptor = new HttpLoggingInterceptor();
        LoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // builder.addInterceptor(new RspCheckInterceptor()); //添加检查拦截器，根据返回值可以处理返回的错误信息
        builder.addInterceptor(new ResAndReqInterceptor());  //也可添加拦截器，拦截请求信息，添加公共的基础参数
            builder.addInterceptor(LoginInterceptor); //添加retrofit日志打印
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);

        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("")
                // .addConverterFactory(GsonConverterFactory.create()) // 默认
                // .addConverterFactory(GsonConverterFactory.create(buildGson())) //添加服务器返回的数据不正确的方式
                .addConverterFactory(CustomGsonConverterFactory.create(buildGson()))  // 在数据解析的时候处理resopnseCode
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    public <T> T createReq(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }

    Gson gson;

    public Gson buildGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())  // String为NULL转空字符串处理
                    .registerTypeAdapter(Integer.class, new IntegerDefaultGson())
                    .registerTypeAdapter(int.class, new IntegerDefaultGson())
                    .registerTypeAdapter(Double.class, new DoubleDefaultGosn())
                    .registerTypeAdapter(double.class, new DoubleDefaultGosn())
                    .registerTypeAdapter(Long.class, new LongDefaultGson())
                    .registerTypeAdapter(long.class, new LongDefaultGson())
                    .create();
        }
        return gson;
    }

    public static void resertRetrofitManager() {
        mRetrofitManager = null;
        getInstance();
    }

}

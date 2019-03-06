package onairm.com.devtool.net;


import android.text.TextUtils;
import android.util.Log;



import rx.Subscriber;

/**
 * Created by edison on 2018/4/2.
 */

public abstract class M3u8StringSubscriber extends Subscriber<String> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onError(Throwable e) {
        onHttpError(e);
    }

    @Override
    public void onNext(String ts) {
        Log.e("m3u8", "onSuccess: "+ts );

        if (!TextUtils.isEmpty(ts)){
            onSuccess(ts);
        }else {
            onHttpError(new Throwable(ts));
        }
    }



    public abstract void onSuccess(String t);


    public abstract void onHttpError(Throwable throwable);
}

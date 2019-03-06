package onairm.com.devtool.net;





import rx.Subscriber;

/**
 * Created by apple on 17/11/2.
 */

public abstract class HttpBaseResultSubscriber extends Subscriber {
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
    public void onNext(Object tBaseData) {
                onSuccess(tBaseData);
               onHttpError(new Throwable());
    }


    public abstract void onSuccess(Object t);


    public abstract void onHttpError(Throwable throwable);

}

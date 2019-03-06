package onairm.com.devtool;

import android.app.Application;

/**
 * Created by Edison on 2017/9/21.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this,"测试");
        TipToast.init(this);
    }
}

package tony.adaptivelayout;

import android.app.Application;

import com.tony.autolayout.config.AutoLayoutConfig;

/**
 * Created by tony on 7/21/16.
 */
public class AppContext extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConfig.getInstance().init(this);
    }
}

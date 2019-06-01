package vehiclentpartner.com.vehiclent.baseClass;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import vehiclentpartner.com.vehiclent.R;

public class MyApp extends Application {

    private static MyApp sAppContext;

    public static synchronized MyApp getInstance() {
        return sAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
}

package vehiclentpartner.com.vehiclent.util;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;

public class Utility {

    public static Typeface typeFaceForBoldText(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/semibold.otf");
    }

    public static Typeface typeFaceForRegulerText(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/regular.otf");
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}

package vn.doithe66.doithe66.Utils;

import android.content.res.Resources;

/**
 * Created by Windows 10 Now on 1/8/2018.
 */

public class Utils {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}

package vn.doithe66.doithe66.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Windows 10 Now on 1/8/2018.
 */

public class Utils {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static void closeKeyboard(Context c, IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }

    public static int isNumeric(String str)
    {
        char[] characters = str.toCharArray();
        for (int i = 0;i<characters.length;i++)
        {
            char c = characters[i];
            if (!Character.isDigit(c)) return i;
        }
        return -1;
    }

    public static int posSpecial(String str)
    {
//        char[] characters = str.toCharArray();
        Pattern p = Pattern.compile("[^A-Za-z0-9]", Pattern.CASE_INSENSITIVE);
        for (int i = 0;i<str.length();i++)
        {
//            char c = characters[i];
//            Matcher m = p.matcher(Character.toString(c));
            // boolean b = m.matches();
            boolean b = containsSpecialCharacter(str.substring(i,i+1));
            if (b){
                return i;
            }
        }
        return -1;
    }

    public static boolean containsSpecialCharacter(String s) {
        return (s == null) ? false : s.matches("[^A-Za-z0-9 ]");
    }
}

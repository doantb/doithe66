package vn.doithe66.doithe66.networking;

import android.os.AsyncTask;
import java.io.IOException;
import org.jsoup.Jsoup;

/**
 * Created by Windows 10 Now on 1/18/2018.
 */

public class TaskGetVersion extends AsyncTask<Void, String, String> {
    private String packageName;

    public TaskGetVersion(String getPackageName) {
        this.packageName = getPackageName;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String newVersion = null;
        try {
            newVersion = Jsoup.connect(
                    "https://play.google.com/store/apps/details?id=" + packageName + "&hl=it")
                    .timeout(1000)
                    .userAgent(
                            "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select("div[itemprop=softwareVersion]")
                    .first()
                    .ownText();
            return newVersion;
        } catch (IOException e) {
            return newVersion;
        }
    }

    @Override
    protected void onPostExecute(String onlineVersion) {
        super.onPostExecute(onlineVersion);
    }
}

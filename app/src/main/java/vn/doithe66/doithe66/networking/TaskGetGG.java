package vn.doithe66.doithe66.networking;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import java.io.IOException;

/**
 * Created by Windows 10 Now on 11/18/2017.
 */

public class TaskGetGG extends AsyncTask<String, Void, String> {
    private static final int REQUEST_AUTHORIZATION = 2018;
    private Context context;

    public TaskGetGG(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String accountName = params[0];
        String scopes = "oauth2:profile email";
        String token = null;
//        Log.d("tokengg","co vao day");
        try {
            token = GoogleAuthUtil.getToken(context, accountName, scopes);
//            Log.d("tokengg", token + " a");
        } catch (IOException e) {
//            Log.e("exception", e.getMessage());
//            Log.d("exception1","co vao day");
        } catch (UserRecoverableAuthException e) {
            ((Activity)context).startActivityForResult(e.getIntent(), REQUEST_AUTHORIZATION);
            // abc//startActivityForResult(e.getIntent(), REQ_SIGN_IN_REQUIRED);
//            Log.d("exception2","co vao day");
        } catch (GoogleAuthException e) {
//            Log.e("exception", e.getMessage());
//            Log.d("exception3","co vao day");
        }
        return token;
    }

    @Override
    protected void onPostExecute(String token) {
        super.onPostExecute(token);
    }
}

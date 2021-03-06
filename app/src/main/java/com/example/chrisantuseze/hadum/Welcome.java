package com.example.chrisantuseze.hadum;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.format.DateUtils;
import android.widget.TextView;

public class Welcome extends Activity {
    private SessionManager sessionManager;
    private UserInfo userInfo;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView txtView = (TextView)findViewById(R.id.textClock);

        // session manager
        sessionManager = new SessionManager(getApplicationContext());
        userInfo = new UserInfo(this);

//        if (!sessionManager.isLoggedIn()) {
//            sessionManager.setLogin(true);
//        }else{
//            sessionManager.setLogin(false);
//            userInfo.clearUserInfo();
//            startActivity(new Intent(this, Splash.class));
//            finish();
//        }

        Calendar cal = new GregorianCalendar();
        String date = DateUtils.formatDateTime(this, cal.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME);

        Typeface custom_font_1 = Typeface.createFromAsset(getAssets(),  "fonts/Aller_Rg.ttf");
        txtView.setTypeface(custom_font_1);

        txtView.setText(date);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Welcome.this, MainActivity.class));
                finish();
            }
        },2000);
    }

}

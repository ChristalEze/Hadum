package com.example.chrisantuseze.hadum.Settings;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chrisantuseze.hadum.MainActivity;
import com.example.chrisantuseze.hadum.R;
import com.example.chrisantuseze.hadum.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class EditName extends AppCompatActivity {
    private EditText mEditName;
    private Button mBtnSave;
    private DatabaseReference mUserDatabase;
    private Toolbar mToolbar;
    String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        mToolbar = (Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Edit Name");

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uId).child("name");

        mEditName = (EditText)findViewById(R.id.edit_name);
        mBtnSave = (Button)findViewById(R.id.btn_edit_name);

        Typeface custom_font_1 = Typeface.createFromAsset(getAssets(),  "fonts/Aller_Rg.ttf");
        mEditName.setTypeface(custom_font_1);
        mBtnSave.setTypeface(custom_font_1);
        final UserInfo userInfo = new UserInfo(this);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name = mEditName.getText().toString();

                if (!TextUtils.isEmpty(name)){
                    StyleableToast.makeText(getApplicationContext(), "Changes saved", R.style.success).show();
                    //changeName(name);
                    userInfo.setKeyName(name);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else{
                    StyleableToast.makeText(getApplicationContext(), "Field cannot be empty!", R.style.error).show();
                }

            }
        });
    }

//    private void changeName(final String name){
//        mUserDatabase.setValue(name).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    StyleableToast.makeText(getApplicationContext(), "Changes saved", R.style.success).show();
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    finish();
//                }else{
//                    StyleableToast.makeText(getApplicationContext(), "Saving failed!", R.style.error).show();
//                }
//            }
//        });
//    }
    public boolean checkInternetConnection() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connectMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        //Check for network connections
        if (connectMgr.getNetworkInfo(0).getState() ==
                NetworkInfo.State.CONNECTED ||
                connectMgr.getNetworkInfo(0).getState() ==
                        NetworkInfo.State.CONNECTING ||
                connectMgr.getNetworkInfo(1).getState() ==
                        NetworkInfo.State.CONNECTING ||
                connectMgr.getNetworkInfo(0).getState() ==
                        NetworkInfo.State.CONNECTED) {
            return true;
        } else if (
                connectMgr.getNetworkInfo(0).getState() ==
                        NetworkInfo.State.DISCONNECTED ||
                        connectMgr.getNetworkInfo(1).getState() ==
                                NetworkInfo.State.DISCONNECTED) {

            return false;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

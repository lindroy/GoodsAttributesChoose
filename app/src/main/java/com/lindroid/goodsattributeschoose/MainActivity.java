package com.lindroid.goodsattributeschoose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import static android.R.attr.activityCloseEnterAnimation;

/**
 * @author Lin
 */
public class MainActivity extends AppCompatActivity {

    public final static int ATTRIBUTES_CHOOSE_CODE = 0x100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void chooseTicket(View view) {
        startActivityForResult(new Intent(MainActivity.this, AttributesDialogActivity.class), ATTRIBUTES_CHOOSE_CODE);
        overridePendingTransition(R.anim.dialog_in, 0);
        Log.e("Tag", "activityCloseEnterAnimation=" + activityCloseEnterAnimation);
        Log.e("Tag", "R.anim.dialog_in=" + R.anim.dialog_in);

    }
}

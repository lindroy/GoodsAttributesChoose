package com.lindroid.goodsattributeschoose;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lindroid.goodsattributeschoose.bean.TicketBean;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * @author Lin
 */
public class MainActivity extends AppCompatActivity {

    public final static int ATTRIBUTES_CHOOSE_CODE = 0x100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(getResources().openRawResource(R.raw.data));
        Type type = new TypeToken<TicketBean>() {
        }.getType();
        TicketBean ticketBean = gson.fromJson(reader, type);
    }

    public void chooseTicket(View view) {
        startActivityForResult(new Intent(MainActivity.this, AttributesDialogActivity.class), ATTRIBUTES_CHOOSE_CODE);
        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowAnimationStyle});
        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
        activityStyle.recycle();
        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId,
                new int[]{android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
        int activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);

//        activityCloseExitAnimation = activityStyle.getResourceId(1, 0);

        activityStyle.recycle();
        overridePendingTransition(R.anim.dialog_in, 0);
        Log.e("Tag", "activityCloseEnterAnimation=" + activityCloseEnterAnimation);
        Log.e("Tag", "R.anim.dialog_in=" + R.anim.dialog_in);

    }
}

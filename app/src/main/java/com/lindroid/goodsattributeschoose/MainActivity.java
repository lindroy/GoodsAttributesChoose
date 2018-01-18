package com.lindroid.goodsattributeschoose;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * @author Lin
 */
public class MainActivity extends AppCompatActivity {

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
}

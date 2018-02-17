package com.lindroid.goodsattributeschoose;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lindroid.goodsattributeschoose.bean.OptionBean;
import com.lindroid.goodsattributeschoose.bean.TicketDataBean;
import com.lindroid.goodsattributeschoose.view.SpaceItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author lin
 */
public class AttributesDialogActivity extends AppCompatActivity {
    //    protected int activityCloseEnterAnimation;
//    protected int activityCloseExitAnimation;

    private List<TicketDataBean.TicketBean> ticketBeanList = new ArrayList<>();

    /**
     * 选项中日期的集合
     */
    private HashSet<OptionBean> optionDates = new HashSet<>();
    /**
     * 选项中时间的集合
     */
    private HashSet<OptionBean> optionTimes = new HashSet<>();
    /**
     * 选项中等级的集合
     */
    private HashSet<OptionBean> optionLevels = new HashSet<>();
    private RecyclerView rvDate;
    private RecyclerView rvTime;
    private RecyclerView rvLevel;

    //不可选
    private final int INVALID = 0;
    //可选但未选中
    private final int SELECTABLE = 1;
    //可选且选中
    private final int SELECTED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_attributes_dialog);
        setWindow();
        initView();
//        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[] {android.R.attr.windowAnimationStyle});
//        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
//        activityStyle.recycle();
//        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId,
//                new int[] {android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
//        activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);
//        activityCloseExitAnimation = activityStyle.getResourceId(1, 0);
//        activityStyle.recycle();

        getOptionData();
    }

    private void initView() {
        rvDate = (RecyclerView) findViewById(R.id.rv_date);
        rvTime = (RecyclerView) findViewById(R.id.rv_time);
        rvLevel = (RecyclerView) findViewById(R.id.rv_level);
        initRecyclerView(rvDate);

    }

    private void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        SpaceItemDecoration sd = new SpaceItemDecoration(this);
        sd.setSpaceColor(ContextCompat.getColor(this, android.R.color.transparent));
        sd.setEdgeSpace(15);
        recyclerView.addItemDecoration(sd);
    }

    private void getOptionData() {
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(getResources().openRawResource(R.raw.data));
        Type type = new TypeToken<TicketDataBean>() {
        }.getType();
        TicketDataBean ticketDataBean = gson.fromJson(reader, type);
        ticketBeanList = ticketDataBean.getTicketData();
        initDateOptions();
    }

    private void initDateOptions() {
        HashSet<String> stringDate = new HashSet<>();
        for (TicketDataBean.TicketBean ticketBean : ticketBeanList) {
            stringDate.add(ticketBean.getDate());
            optionDates.add(new OptionBean(ticketBean.getDate(), SELECTABLE));
        }

        List<OptionBean> list = new ArrayList<>(optionDates);
        Log.e("Tag", "list.size()=" + list.size());
        Log.e("Tag", "optionDates.size()=" + optionDates.size());
        CommonAdapter<OptionBean> dateAdapter = new CommonAdapter<OptionBean>(this, R.layout.item_option, list) {
            @Override
            protected void convert(ViewHolder holder, OptionBean option, int position) {
                holder.setText(R.id.tv_option_name, option.getOptionName());
            }
        };
        rvDate.setAdapter(dateAdapter);
        Log.e("Tag", "stringDate.size()=" + stringDate.size());
    }

    private void setWindow() {
        //窗口对齐屏幕宽度
        Window win = this.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        win.setAttributes(lp);
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
        overridePendingTransition(0, R.anim.dialog_out);
    }
}

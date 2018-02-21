package com.lindroid.goodsattributeschoose;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lindroid.goodsattributeschoose.bean.OptionBean;
import com.lindroid.goodsattributeschoose.bean.TicketDataBean;
import com.lindroid.goodsattributeschoose.view.RecyclerViewSpacesItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author lin
 */
public class AttributesDialogActivity extends AppCompatActivity {
    //    protected int activityCloseEnterAnimation;
//    protected int activityCloseExitAnimation;

    private List<TicketDataBean.TicketBean> ticketBeanList = new ArrayList<>();
    private Context mContext;

    /**
     * 选项中日期的集合
     */
    private List<OptionBean> optionDates = new ArrayList<>();
    /**
     * 选项中时间的集合
     */
    private List<OptionBean> optionTimes = new ArrayList<>();
    /**
     * 选项中等级的集合
     */
    private List<OptionBean> optionLevels = new ArrayList<>();
    private RecyclerView rvDate;
    private RecyclerView rvTime;
    private RecyclerView rvLevel;

    /**
     * 不可选
     */
    private final int INVALID = 0;
    /**
     * 可选但未选中
     */
    private final int SELECTABLE = 1;
    /**
     * 可选且选中
     */
    private final int SELECTED = 2;
    private CommonAdapter<OptionBean> dateAdapter;
    private CommonAdapter<OptionBean> timeAdapter;
    private CommonAdapter<OptionBean> levelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_attributes_dialog);
        mContext = this;
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

        initDateAdapter();
        initTimeAdapter();
        initLevelAdapter();
        getOptionData();
    }

    private void initLevelAdapter() {
        levelAdapter = new CommonAdapter<OptionBean>(mContext, R.layout.item_option, optionLevels) {
            @Override
            protected void convert(ViewHolder holder, OptionBean optionBean, int position) {
                TextView tvName = holder.getView(R.id.tv_option_name);
                tvName.setText(optionBean.getOptionName());
                setOptionStyle(tvName, optionBean.getStatus());
            }
        };
        rvLevel.setAdapter(levelAdapter);
    }

    private void initTimeAdapter() {
        timeAdapter = new CommonAdapter<OptionBean>(mContext, R.layout.item_option, optionTimes) {
            @Override
            protected void convert(ViewHolder holder, OptionBean optionBean, int position) {
                TextView tvName = holder.getView(R.id.tv_option_name);
                tvName.setText(optionBean.getOptionName());
                setOptionStyle(tvName, optionBean.getStatus());
            }
        };
        rvTime.setAdapter(timeAdapter);
    }

    private void initDateAdapter() {
        dateAdapter = new CommonAdapter<OptionBean>(this, R.layout.item_option, optionDates) {
            @Override
            protected void convert(ViewHolder holder, OptionBean optionBean, int position) {
                TextView tvName = holder.getView(R.id.tv_option_name);
                tvName.setText(optionBean.getOptionName());
                setOptionStyle(tvName, optionBean.getStatus());
            }
        };
        rvDate.setAdapter(dateAdapter);
    }

    private void initView() {
        rvDate = (RecyclerView) findViewById(R.id.rv_date);
        rvTime = (RecyclerView) findViewById(R.id.rv_time);
        rvLevel = (RecyclerView) findViewById(R.id.rv_level);
        initRecyclerView(rvDate);
        initRecyclerView(rvTime);
        initRecyclerView(rvLevel);

    }

    private void initRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerView.setHasFixedSize(true);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>(3);
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.TOP_DECORATION, 0);
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, 0);
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.LEFT_DECORATION, 15);
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.RIGHT_DECORATION, 15);
        recyclerView.addItemDecoration(new RecyclerViewSpacesItemDecoration(mContext, stringIntegerHashMap));

    }

    private void getOptionData() {
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(getResources().openRawResource(R.raw.data));
        Type type = new TypeToken<TicketDataBean>() {
        }.getType();
        TicketDataBean ticketDataBean = gson.fromJson(reader, type);
        ticketBeanList = ticketDataBean.getTicketData();
        setOptionData();
    }

    private void setOptionData() {
        for (TicketDataBean.TicketBean ticketBean : ticketBeanList) {
            if (filterRepetition(optionDates, ticketBean.getDate())) {
                optionDates.add(new OptionBean(ticketBean.getDate(), SELECTABLE));
            }
            if (filterRepetition(optionTimes, ticketBean.getTime())) {
                optionTimes.add(new OptionBean(ticketBean.getTime(), SELECTABLE));
            }
            if (filterRepetition(optionLevels, ticketBean.getLevel())) {
                optionLevels.add(new OptionBean(ticketBean.getLevel(), SELECTABLE));
            }
        }
        adapterNotify();
    }

    /**
     * 刷新数据
     */
    private void adapterNotify() {
        dateAdapter.notifyDataSetChanged();
        timeAdapter.notifyDataSetChanged();
        levelAdapter.notifyDataSetChanged();
    }


    /**
     * 设置购票规格的背景和字体颜色
     *
     * @param status:0:不可选，1：可选但未选中，2：可选且选中
     */
    private void setOptionStyle(TextView textView, int status) {
        switch (status) {
            case INVALID:
                textView.setTextColor(ContextCompat.getColor(mContext, android.R.color.darker_gray));
                textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_invalid));
                break;
            case SELECTABLE:
                textView.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
                textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_selectable));
                break;
            case SELECTED:
                textView.setTextColor(ContextCompat.getColor(mContext, android.R.color.white));
                textView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_selected));
                break;
            default:
                break;
        }
    }

    /**
     * 去除重复元素
     *
     * @param list
     * @param element
     * @return
     */
    private boolean filterRepetition(List<OptionBean> list, String element) {
        for (OptionBean optionBean : list) {
            if (optionBean.getOptionName().equals(element)) {
                //元素重複了
                return false;
            }
        }
        return true;
    }

    private boolean filterRepetString(List<String> list, String element) {
        for (String s : list) {
            if (s.equals(element)) {
                //元素重複了
                return false;
            }
        }
        return true;
    }

    /**
     * 判断元素是否在集合中
     *
     * @param list
     * @param element
     * @return
     */
    private boolean isElementInsideStr(List<String> list, String element) {
        for (String s : list) {
            if (element.equals(s)) {
                return true;
            }
        }
        return false;

    }

    /**
     * 获取元素在集合中的下标
     *
     * @param list
     * @param element
     * @return
     */
    private int getIndex(List<OptionBean> list, String element) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getOptionName().equals(element)) {
                return i;
            }
        }
        return 0;
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

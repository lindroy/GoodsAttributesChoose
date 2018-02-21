package com.lindroid.goodsattributeschoose.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import java.util.HashMap;

/**
 * @author Lin
 * @date 2018/2/21
 * @function https://www.jianshu.com/p/340f4338ec64
 */

public class RecyclerViewSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private HashMap<String, Integer> mSpaceValueMap;
    private DisplayMetrics mDisplayMetrics;
    public static final String TOP_DECORATION = "top_decoration";
    public static final String BOTTOM_DECORATION = "bottom_decoration";
    public static final String LEFT_DECORATION = "left_decoration";
    public static final String RIGHT_DECORATION = "right_decoration";

    public RecyclerViewSpacesItemDecoration(Context context, HashMap<String, Integer> mSpaceValueMap) {
        mDisplayMetrics = context.getResources().getDisplayMetrics();
        this.mSpaceValueMap = mSpaceValueMap;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mSpaceValueMap.get(TOP_DECORATION) != null) {
            outRect.top = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    mSpaceValueMap.get(TOP_DECORATION), mDisplayMetrics) + 0.5f);
//            outRect.top = mSpaceValueMap.get(TOP_DECORATION);
        }
        if (mSpaceValueMap.get(LEFT_DECORATION) != null) {
            outRect.left = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    mSpaceValueMap.get(LEFT_DECORATION), mDisplayMetrics) + 0.5f);
//            outRect.left = mSpaceValueMap.get(LEFT_DECORATION);
        }
        if (mSpaceValueMap.get(RIGHT_DECORATION) != null) {
            outRect.right = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    mSpaceValueMap.get(RIGHT_DECORATION), mDisplayMetrics) + 0.5f);
//            outRect.right = mSpaceValueMap.get(RIGHT_DECORATION);
        }
        if (mSpaceValueMap.get(BOTTOM_DECORATION) != null) {
            outRect.bottom = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    mSpaceValueMap.get(BOTTOM_DECORATION), mDisplayMetrics) + 0.5f);

//            outRect.bottom = mSpaceValueMap.get(BOTTOM_DECORATION);
        }
    }


}

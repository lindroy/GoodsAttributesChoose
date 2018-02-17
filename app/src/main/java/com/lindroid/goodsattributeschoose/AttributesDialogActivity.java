package com.lindroid.goodsattributeschoose;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author lin
 */
public class AttributesDialogActivity extends AppCompatActivity {
//    protected int activityCloseEnterAnimation;
//    protected int activityCloseExitAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_attributes_dialog);
        setWindow();
//        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[] {android.R.attr.windowAnimationStyle});
//        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
//        activityStyle.recycle();
//        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId,
//                new int[] {android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
//        activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);
//        activityCloseExitAnimation = activityStyle.getResourceId(1, 0);
//        activityStyle.recycle();
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

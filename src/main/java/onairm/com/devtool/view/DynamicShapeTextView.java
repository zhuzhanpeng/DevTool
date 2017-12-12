package onairm.com.devtool.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;

import onairm.com.devtool.R;


/**
 * Created by Edison on 2017/12/12.
 */

public class DynamicShapeTextView extends android.support.v7.widget.AppCompatTextView {
    private int bgNormalColor=-1;
    private int bgFocusColor=-1;

    private int txtNormalColor=-1;
    private int txtFocusColor=-1;

    private int strokeColor=-1;
    private int normalStrokeWidth=-1;
    private int focusStrokeWidth=-1;

    private int cornerRadius=-1;
    public DynamicShapeTextView(Context context) {
        this(context,null,0);
    }

    public DynamicShapeTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DynamicShapeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DynamicShapeTextView);
        bgNormalColor=typedArray.getColor(R.styleable.DynamicShapeTextView_bg_normal_color,-1);
        bgFocusColor =typedArray.getColor(R.styleable.DynamicShapeTextView_bg_focus_color,-1);

        txtNormalColor = typedArray.getColor(R.styleable.DynamicShapeTextView_normal_txt_color,-1);
        txtFocusColor = typedArray.getColor(R.styleable.DynamicShapeTextView_focus_txt_color,-1);

        cornerRadius=typedArray.getDimensionPixelSize(R.styleable.DynamicShapeTextView_corner_radius,-1);
        normalStrokeWidth=typedArray.getDimensionPixelSize(R.styleable.DynamicShapeTextView_normal_stroke_width,-1);
        focusStrokeWidth=typedArray.getDimensionPixelSize(R.styleable.DynamicShapeTextView_focus_stroke_width,-1);
        strokeColor=typedArray.getColor(R.styleable.DynamicShapeTextView_stroke_color,-1);
        typedArray.recycle();
        setFocusable(true);
        setGravity(Gravity.CENTER);
        if(-1!=txtFocusColor){
            setTextColor(createColorStateList(txtNormalColor,txtFocusColor));
        }
        if(-1!=bgFocusColor||-1!=bgNormalColor||-1!=cornerRadius||-1!=strokeColor||-1!=normalStrokeWidth||-1!=focusStrokeWidth){
            setBackground(newSelector(bgNormalColor,bgFocusColor,cornerRadius,strokeColor,normalStrokeWidth,focusStrokeWidth));
        }
    }
    /** 对TextView设置不同状态时其文字颜色。 */
    private ColorStateList createColorStateList(int normal, int focused) {
        int[] colors=new int[]{focused,normal};
        int[][] states = new int[2][];
        states[0] = new int[] { android.R.attr.state_focused };
        states[1] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    /** 设置Selector。 */
    public static StateListDrawable newSelector(int bgNormalColor,int bgFocusColor,int cornerRadius,
                                                int strokeColor,int normalStrokeWidth,int focusStrokeWidth) {


        GradientDrawable normal = new GradientDrawable();//创建drawable
        if(-1!=bgNormalColor){
            normal.setColor(bgNormalColor);
        }
        if(-1!=cornerRadius){
            normal.setCornerRadius(cornerRadius);
        }
        if(-1!=normalStrokeWidth){
            normal.setStroke(normalStrokeWidth, strokeColor);
        }


        GradientDrawable focused = new GradientDrawable();//创建drawable
        if(-1!=bgFocusColor){
            focused.setColor(bgFocusColor);
        }
        if(-1!=cornerRadius){
            focused.setCornerRadius(cornerRadius);
        }
        if(-1!=focusStrokeWidth){
            focused.setStroke(focusStrokeWidth, strokeColor);
        }

        StateListDrawable bg=new StateListDrawable();
        // View.FOCUSED_STATE_SET
        bg.addState(new int[] { android.R.attr.state_focused }, focused);
        // View.WINDOW_FOCUSED_STATE_SET
        // View.EMPTY_STATE_SET
        bg.addState(new int[] {}, normal);
        return bg;
    }


}

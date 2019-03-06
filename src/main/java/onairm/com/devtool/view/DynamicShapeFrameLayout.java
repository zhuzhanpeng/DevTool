package onairm.com.devtool.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import onairm.com.devtool.R;

/**
 * Created by Edison on 2017/12/12.
 */

public class DynamicShapeFrameLayout extends FrameLayout {
    private int bgNormalColor=-1;
    private int bgFocusColor=-1;


    private int strokeColor=-1;
    private int normalStrokeWidth=-1;
    private int focusStrokeWidth=-1;

    private int cornerRadius=-1;
    public DynamicShapeFrameLayout(@NonNull Context context) {
        this(context,null,0);
    }

    public DynamicShapeFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DynamicShapeFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DynamicShapeFLayout);
        bgNormalColor=typedArray.getColor(R.styleable.DynamicShapeFLayout_f_bg_normal_color,-1);
        bgFocusColor =typedArray.getColor(R.styleable.DynamicShapeFLayout_f_bg_focus_color,-1);


        cornerRadius=typedArray.getDimensionPixelSize(R.styleable.DynamicShapeFLayout_f_corner_radius,-1);
        normalStrokeWidth=typedArray.getDimensionPixelSize(R.styleable.DynamicShapeFLayout_f_normal_stroke_width,-1);
        focusStrokeWidth=typedArray.getDimensionPixelSize(R.styleable.DynamicShapeFLayout_f_focus_stroke_width,-1);
        strokeColor=typedArray.getColor(R.styleable.DynamicShapeFLayout_f_stroke_color,-1);
        typedArray.recycle();


        if(-1!=bgFocusColor||-1!=bgNormalColor||-1!=cornerRadius||-1!=strokeColor||-1!=normalStrokeWidth||-1!=focusStrokeWidth){
            setBackground(newSelector(bgNormalColor,bgFocusColor,cornerRadius,strokeColor,normalStrokeWidth,focusStrokeWidth));
        }
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

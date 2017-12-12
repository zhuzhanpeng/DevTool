package onairm.com.devtool.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import onairm.com.devtool.R;


public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context);
    }
    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View.OnClickListener positiveButtonClickListener;
        private View.OnClickListener negativeButtonClickListener;

        private View layout;
        private CustomDialog dialog;
        public Builder(Context context) {
            //这里传入自定义的style，直接影响此Dialog的显示效果。style具体实现见style.xml
            dialog = new CustomDialog(context, R.style.Theme_AppCompat_Dialog);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.dialog_layout, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, View.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, View.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * 创建双按钮对话框
         * @return
         */
        public CustomDialog createTwoButtonDialog() {
            layout.findViewById(R.id.positiveButton).setOnClickListener(positiveButtonClickListener);
            layout.findViewById(R.id.negativeButton).setOnClickListener(negativeButtonClickListener);
            //如果传入的按钮文字为空，则使用默认的“是”和“否”
            if(message != null){
                ((TextView)layout.findViewById(R.id.tvTitle)).setText(message);
            }
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
            } else {
                ((TextView) layout.findViewById(R.id.positiveButton)).setText("是");
            }
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.negativeButton)).setText(negativeButtonText);
            } else {
                ((TextView) layout.findViewById(R.id.negativeButton)).setText("否");
            }
            create();
            return dialog;
        }

        private void create() {
            dialog.setContentView(layout);
            dialog.setCancelable(true);     //用户可以点击手机Back键取消对话框显示
            dialog.setCanceledOnTouchOutside(false);        //用户不能通过点击对话框之外的地方取消对话框显示
        }
    }
}
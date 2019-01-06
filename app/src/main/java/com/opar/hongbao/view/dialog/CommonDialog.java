package com.opar.hongbao.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.opar.hongbao.R;
import com.opar.hongbao.utils.KeyBordUtil;

/**
 * Created by tangge on 18/3/13.
 */
public class CommonDialog extends Dialog {

    private Context mContext;

    View ly_title;
    TextView btn_cancel;
    TextView tv_title;
    TextView btn_confirm;
    LinearLayout ly_content;
    View ly_content_container;
    View ly_message;
    TextView tv_message;
    View line;

    private CharSequence title;

    private String cancelLabel;

    private String confirmLabel;

    private OnButtonClickListener cancelClickListener, confirmClickListener;

    private View contentView;

    private EditText focusView;

    private CharSequence text;

    private View.OnClickListener defaultClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
            if (focusView != null) {
                KeyBordUtil.hideInputMethod(focusView);
            }
        }
    };

    public CommonDialog(Context context) {
        super(context, R.style.dialog_untran);
        mContext = context;
    }

    public void setConfrimButtonEnable(boolean enable) {
        btn_confirm.setEnabled(enable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        Window window = getWindow();
        WindowManager winManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display d = winManager.getDefaultDisplay();
        WindowManager.LayoutParams params = window.getAttributes();
        if(params != null){
            params.width = (int) (d.getWidth() * 0.74f);
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
         
    }

    public static class Builder {
        private Context mContext;
        private String confirmLabel, cancelLabel;
        private OnButtonClickListener confirmListener, cancelListener;
        private View contentView;
        private EditText focusView;
        private CharSequence text, title;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setTitle(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder setConfirmButton(String confirmLabel, OnButtonClickListener listener) {
            if (TextUtils.isEmpty(confirmLabel)) {
                confirmLabel = "确定";
            }
            this.confirmLabel = confirmLabel;
            confirmListener = listener;
            return this;
        }

        public Builder setCancelButton(String cancelLabel, OnButtonClickListener listener) {
            if (TextUtils.isEmpty(cancelLabel)) {
                cancelLabel = "取消";
            }
            this.cancelLabel = cancelLabel;
            cancelListener = listener;
            return this;
        }

        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder setFocusText(EditText focusView) {
            this.focusView = focusView;
            return this;
        }

        public Builder setMessage(CharSequence text) {
            this.text = text;
            return this;
        }

        public CommonDialog create() {
            CommonDialog dialog = new CommonDialog(mContext);
            if(!TextUtils.isEmpty(title)){
                dialog.setTitle(title);
            }
            dialog.setCancelButton(cancelLabel, cancelListener);
            dialog.setConfirmButton(confirmLabel, confirmListener);
            dialog.setCustomContentView(contentView);
            if(!TextUtils.isEmpty(text)){
                dialog.setMessage(text);
            }
            dialog.setFocusText(focusView);
            return dialog;
        }
    }

    @Override
    public void show() {
        super.show();
        if (focusView != null) {
            KeyBordUtil.showInputMethod(focusView);
        }
    }

    private void setFocusText(EditText focusView) {
        this.focusView = focusView;
    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
    }

    private void setConfirmButton(String confirmLabel, OnButtonClickListener listener) {
        this.confirmLabel = confirmLabel;
        confirmClickListener = listener;
    }

    private void setCancelButton(String cancelLable, OnButtonClickListener listener) {
        this.cancelLabel = cancelLable;
        cancelClickListener = listener;
    }

    private void setMessage(CharSequence text) {
        this.text = text;
    }

    public void setCustomContentView(View contentView) {
        this.contentView = contentView;
    }

    private void initView() {
        View content = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_common, new LinearLayout(mContext),false);
        ly_title = content.findViewById(R.id.ly_title);
        btn_cancel = (TextView) content.findViewById(R.id.btn_cancel);
        tv_title = (TextView) content.findViewById(R.id.tv_title);
        btn_confirm = (TextView) content.findViewById(R.id.btn_confirm);
        ly_content = (LinearLayout) content.findViewById(R.id.ly_content);
        ly_content_container = content.findViewById(R.id.ly_content_container);
        ly_message = content.findViewById(R.id.ly_message);
        tv_message = (TextView) content.findViewById(R.id.tv_message);
        line = content.findViewById(R.id.line);

        setContentView(content);
        setCancelable(false);
        if (!TextUtils.isEmpty(title)) {
            setViewVisible(ly_title, true);
            tv_title.setText(title);
        } else {
            setViewVisible(ly_title, false);
        }
        if (!TextUtils.isEmpty(cancelLabel)) {
            btn_cancel.setText(cancelLabel);
            setViewVisible(btn_cancel, true);
            if (cancelClickListener != null) {
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelClickListener.onButtonClick(CommonDialog.this);
                        if (focusView != null) {
                            KeyBordUtil.hideInputMethod(focusView);
                        }
                    }
                });
            } else {
                btn_cancel.setOnClickListener(defaultClickListener);
            }
        } else {
            line.setVisibility(View.GONE);
            setViewVisible(btn_cancel, false);
        }

        if (!TextUtils.isEmpty(confirmLabel)) {
            setViewVisible(btn_confirm, true);
            btn_confirm.setText(confirmLabel);
            if (confirmClickListener != null) {
                btn_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmClickListener.onButtonClick(CommonDialog.this);
                    }
                });
            } else {
                btn_confirm.setOnClickListener(defaultClickListener);
            }
        } else {
            line.setVisibility(View.GONE);
            setViewVisible(btn_confirm, false);
        }
        if (contentView != null && contentView.getParent() == null) {
            ly_content_container.setVisibility(View.VISIBLE);
            ly_message.setVisibility(View.GONE);
            ly_content.addView(contentView);
        } else {
            ly_content_container.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(text)) {
                ly_message.setVisibility(View.VISIBLE);
                tv_message.setText(text);
            } else {
                ly_message.setVisibility(View.GONE);
            }
        }
    }

    private void setViewVisible(View targetView, boolean visible) {
        targetView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public interface OnButtonClickListener {
        void onButtonClick(Dialog dialog);
    }

}

package com.ahn.cysi.canyousolveit;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.edit_sign_acc) EditText mEditEmail;
    @BindView(R.id.edit_sign_nick) EditText mEditNick;
    @BindView(R.id.edit_sign_pwd1) EditText mEditPwd1;
    @BindView(R.id.edit_sign_pwd2) EditText mEditPwd2;
    @BindView(R.id.bt_sign_ok) Button mBtnOk;
    @BindView(R.id.bt_sign_cancel) Button mBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mEditEmail.setFilters(new InputFilter[] { filter });
    }

    // todo - 임시 방편으로 하드 코딩. db 접근 방법으로 수정해야 함.
    protected boolean checkEmail(String input) {
        boolean tf = false;

        if(!input.equals("root@admin.com")) {
            tf = true;
        }

        return tf;
    }

    protected boolean checkPassword(String input1, String input2) {
        boolean tf = false;

        if(input1.equals(input2))
            tf = true;

        return tf;
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.bt_sign_ok)
    protected void signOk(View view) {
        if(checkEmail(mEditEmail.getText().toString())) {
            if(checkPassword(mEditPwd1.getText().toString(), mEditPwd2.getText().toString())) {
                finish();
                Snackbar.make(view, "SignUp Success !", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.bt_sign_cancel)
    protected void signCancel(View view) {
        finish();
    }

    /**
     * able to write email pattern at EditText
     */
    private InputFilter filter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dStart, int dEnd) {
            for (int i = start; i < end; i++) {
                Pattern ps = Pattern.compile("^[a-zA-Z0-9@._]+$");
                if (!ps.matcher(source).matches()) {

                    return "";

                }
            }
            return null;
        }
    };
}

package com.ahn.cysi.canyousolveit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email) EditText mEditLoginEmail;
    @BindView(R.id.login_password) EditText mEditPassword;
    @BindView(R.id.bt_login) Button mEditBtLogin;
    @BindView(R.id.bt_signup) Button mEditBtSignUp;

    private Retrofit mRetrofit;
    private RetrofitBase mRetrofitBase;

    public static SharedPreferences preferences;
    public static SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        preferences = getSharedPreferences("AUTO", Activity.MODE_PRIVATE);
        String email = preferences.getString("EMAIL",null);
        String pwd = preferences.getString("PASSWORD",null);

        if(email != null && pwd != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        mEditLoginEmail.setFilters(new InputFilter[] { filter });
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.bt_login)
    protected void login(final View view) {
        mRetrofit = new Retrofit.Builder().baseUrl(RetrofitBase.url).addConverterFactory(GsonConverterFactory.create()).build();
        mRetrofitBase = mRetrofit.create(RetrofitBase.class);
        Call<RetrofitMember> call = mRetrofitBase.login(mEditLoginEmail.getText().toString(), mEditPassword.getText().toString());
        call.enqueue(new Callback<RetrofitMember>() {
            @Override
            public void onResponse(Call<RetrofitMember> call, Response<RetrofitMember> response) {
                Log.i("--- email", response.body().getEmail());
                Log.i("--- pwd", response.body().getPwd());
//                RetrofitMember member = response.body();
//                if(member != null) {
//                    Log.i("--- email", member.getEmail());
//                    Log.i("--- pwd", member.getPwd());
//                }

                Snackbar.make(view, "welcome", Snackbar.LENGTH_SHORT).show();

                preferences = getSharedPreferences("AUTO", Activity.MODE_PRIVATE);
                prefEditor = preferences.edit();
                prefEditor.putString("EMAIL", mEditLoginEmail.getText().toString());
                prefEditor.putString("PASSWORD", mEditPassword.getText().toString());
                prefEditor.commit();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<RetrofitMember> call, Throwable t) {
                Snackbar.make(view, "login failed", Snackbar.LENGTH_SHORT).show();
            }
        });

//        if(checkEmail(mEditLoginEmail.getText().toString())) {
//            Snackbar.make(view, "login", Snackbar.LENGTH_SHORT).show();
//
//            preferences = getSharedPreferences("AUTO", Activity.MODE_PRIVATE);
//            prefEditor = preferences.edit();
//            prefEditor.putString("EMAIL", mEditLoginEmail.getText().toString());
//            prefEditor.putString("PASSWORD", mEditPassword.getText().toString());
//            prefEditor.commit();
//
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
//        }
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.bt_signup)
    protected void signUp(View view) {
        Snackbar.make(view, "sign up", Snackbar.LENGTH_SHORT).show();

        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    /**
     * check the input is email pattern
     * @param email
     * @return
     */
    protected boolean checkEmail(String email) {
        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
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

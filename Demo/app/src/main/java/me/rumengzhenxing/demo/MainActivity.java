package me.rumengzhenxing.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProgressView progressView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressView=new ProgressView(this);
        progressView.setWindowOutsideTouchable(false);
    }


    public void onShow(View v) {
        progressView.showPopupWindow();
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                if (progressView != null && progressView.isShowing()) {
                    progressView.dismiss();
                }
            }
        }, 2000);

    }
}

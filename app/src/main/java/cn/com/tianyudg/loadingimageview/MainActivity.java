package cn.com.tianyudg.loadingimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.com.tianyudg.loadingimageview.widget.LoadingImageView;

public class MainActivity extends AppCompatActivity {

    private LoadingImageView loadingImageView;
    private float progress = 0.01f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingImageView = (LoadingImageView) findViewById(R.id.loadingImageView);
        loadingImageView.setImageResource(R.mipmap.ic_launcher_round);
        test();
    }

    private void test() {
        loadingImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                progress = progress + 0.01f;
                loadingImageView.setProgress(progress);
                if (progress < 1) {
                    test();
                } else {
//                    loadingImageView.setImageResource(R.mipmap.ic_launcher_round);
                }
            }
        }, 600);
    }
}

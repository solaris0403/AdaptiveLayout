package tony.adaptivelayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import tony.adaptivelayout.config.AutoLayoutConfig;
import tony.adaptivelayout.utils.LogUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView0, mTextView1;
    private TextView mTextView2, mTextView3,mTextView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView0 = (TextView) findViewById(R.id.text0);
        mTextView0.setText("ScreenWidth:"+AutoLayoutConfig.getInstance().getScreenWidth()+"\n"+"ScreenHeight:"+AutoLayoutConfig.getInstance().getScreenHeight());
        mTextView1 = (TextView) findViewById(R.id.text1);
        mTextView1.setText("DesignWidth:"+AutoLayoutConfig.getInstance().getDesignWidth()+"\n"+"DesignHeight:"+AutoLayoutConfig.getInstance().getDesignHeight());

        mTextView2 = (TextView) findViewById(R.id.text2);
        mTextView2.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.i("2 width:"+mTextView2.getWidth()+",height:"+mTextView2.getHeight());
            }
        });

        mTextView3 = (TextView) findViewById(R.id.text3);
        mTextView3.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.i("3 width:"+mTextView3.getWidth()+",height:"+mTextView3.getHeight());
            }
        });

        mTextView4 = (TextView) findViewById(R.id.text4);
        mTextView4.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.i("4 width:"+mTextView4.getWidth()+",height:"+mTextView4.getHeight());
            }
        });
    }
}

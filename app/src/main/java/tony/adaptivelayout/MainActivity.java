package tony.adaptivelayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tony.autolayout.widget.auto.AutoLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView0, mTextView1;
    private TextView mTextView2, mTextView3, mTextView4;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview);
//        mTextView0 = (TextView) findViewById(R.id.text0);
//        mTextView0.setText("ScreenWidth:"+ AutoLayoutConfig.getInstance().getScreenWidth()+"\n"+"ScreenHeight:"+AutoLayoutConfig.getInstance().getScreenHeight());
//        mTextView1 = (TextView) findViewById(R.id.text1);
//        mTextView1.setText("DesignWidth:"+AutoLayoutConfig.getInstance().getDesignWidth()+"\n"+"DesignHeight:"+AutoLayoutConfig.getInstance().getDesignHeight());
//
//        mTextView2 = (TextView) findViewById(R.id.text2);
//        AutoLayout.autoSize(mTextView2);
////        AutoLayout.auto(mTextView2);
//        mTextView2.post(new Runnable() {
//            @Override
//            public void run() {
//                LogUtils.i("2 width:"+mTextView2.getWidth()+",height:"+mTextView2.getHeight());
//            }
//        });

//        mTextView3 = (TextView) findViewById(R.id.text3);
//        mTextView3.post(new Runnable() {
//            @Override
//            public void run() {
//                LogUtils.i("3 width:"+mTextView3.getWidth()+",height:"+mTextView3.getHeight());
//            }
//        });
//
//        mTextView4 = (TextView) findViewById(R.id.text4);
//        mTextView4.post(new Runnable() {
//            @Override
//            public void run() {
//                LogUtils.i("4 width:"+mTextView4.getWidth()+",height:"+mTextView4.getHeight());
//            }
//        });
        List<String> mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add(String.valueOf(i));
        }
        MyAdapter adapter = new MyAdapter(mData);
        mListView.setAdapter(adapter);
    }

    private class MyAdapter extends BaseAdapter {
        private List<String> mData;

        public MyAdapter(List<String> mData) {
            this.mData = mData;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mData.get(position).hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent, false);
            AutoLayout.auto(convertView);
            TextView textView = (TextView) convertView.findViewById(R.id.text_view);
            final TextView a = textView;
            a.post(new Runnable() {
                @Override
                public void run() {
                    Log.e("123", a.getWidth() + "-" + a.getHeight());
                }
            });
            textView.setText(mData.get(position));
            return convertView;
        }
    }
}

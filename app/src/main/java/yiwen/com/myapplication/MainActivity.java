package yiwen.com.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yiwen.com.myapplication.View.CalendarView;
import yiwen.com.myapplication.View.CalendarViewDialog;
import yiwen.com.myapplication.View.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //头像
    LinearLayout ll_icon;
    //添加
    ImageView iv_add;
    LinearLayout ll_add;
    //左边
    private LinearLayout ll_left;
    private TextView tv_left;
    //右边
    private LinearLayout ll_right;
    private TextView tv_right;
    //填充数据
    private ViewPager vp_client_data;
    /*碎片集合*/
    private List<Fragment> fragments;

    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化组件
        initView();
        //加载碎片
        InitTextView();
        //点击事件监听
        initListener();
        //设置滑动监听
        vp_client_data.setOnPageChangeListener(new OnMainPageChangeListener());
    }


    /**
     * 初始化
     */
    private void initView() {
        ll_icon= (LinearLayout) findViewById(R.id.ll_icon);
        ll_add= (LinearLayout) findViewById(R.id.ll_add);
       // iv_add= (ImageView) findViewById(R.id.iv_add);
        ll_left= (LinearLayout) findViewById(R.id.ll_left);
        ll_right= (LinearLayout) findViewById(R.id.ll_right);
        tv_left= (TextView) findViewById(R.id.tv_left);
        tv_right= (TextView) findViewById(R.id.tv_right);
        vp_client_data = (ViewPager) findViewById(R.id.vp_client_data);
        tv_left.setTextColor(getResources().getColor(R.color.blue1));
    }

    /**
     * 点击事件
     */
    private void initListener() {
        ll_add.setOnClickListener(this);
        ll_left.setOnClickListener(this);
        ll_right.setOnClickListener(this);
        vp_client_data.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_icon:

                break;
            case R.id.ll_add:
                //Toast.makeText(MainActivity.this,"123",Toast.LENGTH_LONG).show();
                break;
            case R.id.ll_left:
                vp_client_data.setCurrentItem(0);
                break;
            case R.id.ll_right:
                vp_client_data.setCurrentItem(1);
                break;
        }
    }
    /**
     * 数据
     */
    protected void InitTextView() {
           /*详情界面的两个功能  填充效果*/
        fragments = new ArrayList<Fragment>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        MyPageAdapter myPageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        vp_client_data.setAdapter(myPageAdapter);
        vp_client_data.setOffscreenPageLimit(2);
    }

    public class MyPageAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    private class OnMainPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        /** 当touch事件发生时回调此方法 */
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        /** 当见面选中状态发生变更时会回调此方法 */
        public void onPageSelected(int position) {
            int blue = getResources().getColor(R.color.blue1);
            int White = getResources().getColor(R.color.white);
            int font1 = getResources().getColor(R.color.font1);
            if(position==0){
                // tab对应的页面被选中
                ll_left.setBackgroundResource(R.drawable.left_circle_nomal);
                tv_left.setTextColor(blue);
                ll_right.setBackgroundResource(R.drawable.right_circle);
                tv_right.setTextColor(White);
            } else {
                // tab对应的页面没有被选中
                ll_right.setBackgroundResource(R.drawable.right_circle_nomal);
                tv_right.setTextColor(blue);
                ll_left.setBackgroundResource(R.drawable.left_circle);
                tv_left.setTextColor(White);
            }
        }

        @Override
        /** 当页面的滑动状态发生变更会回调此方法 */
        public void onPageScrollStateChanged(int state) {

        }
    }
}

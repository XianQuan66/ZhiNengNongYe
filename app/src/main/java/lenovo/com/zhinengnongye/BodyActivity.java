package lenovo.com.zhinengnongye;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fragement.Fragement01;
import fragement.Fragement02;
import fragement.Fragement03;

public class BodyActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {
    private long mExitTime = 0;
    ViewPager body_vp;
    ArrayList<Fragment> arr;
    TextView tv; //最上方的字
    ImageView iv_sy,iv_sz,iv_bz;  //最下方的图片
    TextView tv_sy,tv_sz,tv_bz; //最下方文字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        body_vp = findViewById(R.id.body_vp);
        body_vp.setOnPageChangeListener(this);
        tv = findViewById(R.id.tv);
        iv_sy = findViewById(R.id.iv_sy);
        iv_sz = findViewById(R.id.iv_sz);
        iv_bz = findViewById(R.id.iv_bz);
        tv_sy = findViewById(R.id.tv_sy);
        tv_sz = findViewById(R.id.tv_sz);
        tv_bz = findViewById(R.id.tv_bz);
        init();
    }

    /**
     * viewpager 和 fragement方法
     */
    private void init() {
        findViewById(R.id.lin_sy).setOnClickListener(this);
        findViewById(R.id.lin_sz).setOnClickListener(this);
        findViewById(R.id.lin_help).setOnClickListener(this);
        arr = new ArrayList<>();
        arr.add(new Fragement01());
        arr.add(new Fragement02());
        arr.add(new Fragement03());
        body_vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    /**
     * 监听软件最下面三个点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        int item = 0;
        switch (view.getId()) {
            case R.id.lin_sy:
                item = 0;
                break;
            case R.id.lin_sz:
                item = 1;
                break;
            case R.id.lin_help:
                item = 2;
                break;
        }
        //改变页面
        body_vp.setCurrentItem(item);
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {}
    /**
     * 最下方图标点击时修改图标颜色
     */
    @Override
    public void onPageSelected(int i) {
        iv_sy.setImageResource(R.mipmap.b1);
        tv_sy.setTextColor(getResources().getColor(R.color.bantou));
        iv_sz.setImageResource(R.mipmap.b2);
        tv_sz.setTextColor(getResources().getColor(R.color.bantou));
        iv_bz.setImageResource(R.mipmap.b3);
        tv_bz.setTextColor(getResources().getColor(R.color.bantou));

        if (i==0){
            iv_sy.setImageResource(R.mipmap.shouye_lu);
            tv_sy.setTextColor(getResources().getColor(R.color.main0));
            tv.setText("智能农业");  //修改最上方标题
        }
        if (i==1){
            iv_sz.setImageResource(R.mipmap.shezhi_lu);
            tv_sz.setTextColor(getResources().getColor(R.color.main0));
            tv.setText("设置");  //修改最上方标题
        }
        if (i==2){
            iv_bz.setImageResource(R.mipmap.bangzhu_lu);
            tv_bz.setTextColor(getResources().getColor(R.color.main0));
            tv.setText("帮助");  //修改最上方标题
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {}

    /**
     * 写一个内部类来设置adapter监听
     */
    private class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return arr.get(i);
        }

        @Override
        public int getCount() {
            return arr.size();
        }
    }

    /**
     * 两次点击退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            /**
             * //第一次点击
             * 获取系统时间
             */
            if (System.currentTimeMillis() - mExitTime > 2000) {
                Toast.makeText(this, "再次点击返回键退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            //return true 是为了停止返回功能
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}


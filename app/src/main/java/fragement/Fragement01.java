package fragement;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import lenovo.com.zhinengnongye.F1Air;
import lenovo.com.zhinengnongye.F1Co2;
import lenovo.com.zhinengnongye.F1GuangZhao;
import lenovo.com.zhinengnongye.F1TuRang;
import lenovo.com.zhinengnongye.R;
import utils.MyApp;
import utils.MyOkHttp;


public class Fragement01 extends Fragment implements View.OnClickListener {

    /**
     * 图片资源的id（背景图）
     *
     * @param savedInstanceState
     */
    private int[] imgIdArray;

    /**
     * 存放小圆点数组
     *
     * @param savedInstanceState
     */
    private ImageView[] tips;

    /**
     * imageview 数组
     *
     * @param savedInstanceState
     */
    private ImageView[] ivTemp;
    ViewPager viewPager;
    ViewGroup group;
    MyApp myApp;
    RelativeLayout rel1, rel2, rel3, rel4;
    TextView co2Set, lightSet, SoilTemperatureset, SoilHumiditySet, AirTemperatureSet, AirHumiditySet;
    TextView co2_time, guangzhao_time, turangwd_time, turangsd_time, airwd_time, airsd_time;
    public static final int MSG_CHANGE_AUTO = 999; //标识符
    public static final int MSG_MAX_SERVER = 111; //最大最小值标识符
    public static final int MSG_CURRENT_SERVER = 222; //实时值标识符

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_CHANGE_AUTO:   //修改小圆点
                    int current = viewPager.getCurrentItem();
                    viewPager.setCurrentItem(current + 1);
                    handler.sendEmptyMessageDelayed(MSG_CHANGE_AUTO, 2000);
                    break;
                case MSG_MAX_SERVER:   //修改设定的最大最小值
                    co2Set.setText(myApp.getMinCo2() + "~~" + myApp.getMaxCo2());
                    lightSet.setText(myApp.getMinLight()+ "~~" + myApp.getMaxLight());
                    SoilTemperatureset.setText(myApp.getMinSoilTemperature()+ "~~" + myApp.getMaxSoilTemperature());
                    SoilHumiditySet.setText(myApp.getMinSoilHumidity()+ "~~" + myApp.getMaxSoilHumidity());
                    AirTemperatureSet.setText(myApp.getMinAirTemperature()+ "~~" + myApp.getMaxAirTemperature());
                    AirHumiditySet.setText(myApp.getMinAirHumidity()+ "~~" + myApp.getMaxAirHumidity());
                    break;
                case MSG_CURRENT_SERVER:   //修改实时值
                    String data[]= (String[]) msg.obj;
                    co2_time.setText(data[0]);
                    guangzhao_time.setText(data[1]);
                    turangwd_time.setText(data[2]);
                    turangsd_time.setText(data[3]);
                    airwd_time.setText(data[4]);
                    airsd_time.setText(data[5]);
                    break;

            }

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement01, null);
        viewPager = view.findViewById(R.id.vp2);
        rel1 = view.findViewById(R.id.rel1);
        rel2 = view.findViewById(R.id.rel2);
        rel3 = view.findViewById(R.id.rel3);
        rel4 = view.findViewById(R.id.rel4);
        rel1.setOnClickListener(this);
        rel2.setOnClickListener(this);
        rel3.setOnClickListener(this);
        rel4.setOnClickListener(this);
        co2Set = view.findViewById(R.id.co2Set);
        lightSet = view.findViewById(R.id.lightSet);
        SoilTemperatureset = view.findViewById(R.id.SoilTemperature);
        SoilHumiditySet = view.findViewById(R.id.SoilHumiditySet);
        AirTemperatureSet = view.findViewById(R.id.AirTemperatureSet);
        AirHumiditySet = view.findViewById(R.id.AirHumiditySet);

        co2_time = view.findViewById(R.id.co2_time);
        guangzhao_time = view.findViewById(R.id.guangzhao_time);
        turangwd_time = view.findViewById(R.id.turangwd_time);
        turangsd_time = view.findViewById(R.id.turangsd_time);
        airwd_time = view.findViewById(R.id.airwd_time);
        airsd_time = view.findViewById(R.id.airsd_time);

        group = view.findViewById(R.id.f1_viewgroup);//向上转型  lineralayout  --->ViewGroup
        initView();

        /**
         * 设定页面数值
         */
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                getDataFromServer();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 8000);

        return view;
    }

    /**
     * 更改页面图片
     */
    private void initView() {
        myApp = (MyApp) getActivity().getApplication();
        imgIdArray = new int[]{R.mipmap.bana1, R.mipmap.bana2, R.mipmap.bana3};//添加三张图片
        ivTemp = new ImageView[imgIdArray.length];
        //创建三个imageview 把三张图片分别放到imageview中
        ImageView iv1 = new ImageView(getContext());
        iv1.setBackgroundResource(R.mipmap.bana1);
        ImageView iv2 = new ImageView(getContext());
        iv2.setBackgroundResource(R.mipmap.bana2);
        ImageView iv3 = new ImageView(getContext());
        iv3.setBackgroundResource(R.mipmap.bana3);
        //把imageview添加到数组中
        ivTemp[0] = iv1;
        ivTemp[1] = iv2;
        ivTemp[2] = iv3;
        //初始化tips（小圆点数组）
        tips = new ImageView[ivTemp.length];


        /**
         * 加载小圆点
         */
        for (int i = 0; i < tips.length; i++) {
            ImageView image = new ImageView(getContext());
            image.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
            tips[i] = image;
            if (i == 0) {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layout.setMargins(10, 10, 10, 10);
            image.setLayoutParams(layout);
            group.addView(image);
        }
        viewPager.setAdapter(new MyAdapter());

        /**
         * // handle 写自动播放
         */
        viewPager.setCurrentItem(1);  //页面初始为第一张
        if (tips.length > 0) {
            handler.sendEmptyMessageDelayed(MSG_CHANGE_AUTO, 2000);
        }

        /**
         * //小圆点随图片滑动变化
         */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //i ：在viewPager中的位置 从0开始
                /**
                 *  i=0 第 0 张图片
                 *  i=1 第 1 张图片
                 *  i=2 第 2张图片
                 *  i=3 第 0 张图片
                 *  i=4 第 1 张图片
                 *  i=5 第 2张图片
                 *  i=6 第 3张图片
                 */
                setTipsBack(i % tips.length);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 获取沙盘数据来设置第一个页面的设定值
     */
    private void getDataFromServer() {
        MyOkHttp okHttp = new MyOkHttp();
        try {
            //设置第一个页面的最大最小设定值
            okHttp.getConfig(myApp);
            Message msg = new Message();
            msg.what = MSG_MAX_SERVER;
            handler.sendMessage(msg);
            //设置第一个页面的实时设定值
            String[] sensor = okHttp.getSensor(myApp);
            Message msg2 = new Message();
            msg2.what = MSG_CURRENT_SERVER;
            msg2.obj = sensor;
            handler.sendMessage(msg2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 小圆点随图片滑动变化
     * index 数组索引 0 1 2
     */
    public void setTipsBack(int index) {
        for (int i = 0; i < tips.length; i++) {  //i: 0 1 2
            if (i == index) {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
        }
    }

    /**
     * 四个点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel1:
                startActivity(new Intent(getContext(), F1Co2.class));
                break;
            case R.id.rel2:
                startActivity(new Intent(getContext(), F1GuangZhao.class));
                break;
            case R.id.rel3:
                startActivity(new Intent(getContext(), F1TuRang.class));
                break;
            case R.id.rel4:
                startActivity(new Intent(getContext(), F1Air.class));
                break;
        }
    }


    /**
     * 写一个Adapter适配器
     * 加载图片
     */
    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

        @Override
        //提前预加载
        public Object instantiateItem(ViewGroup container, int position) {
            /**
             * 取余进行循环播放
             */
            int i = position % ivTemp.length;
            ImageView imageView = ivTemp[i];  //i=0 1 2
            ViewParent vp = imageView.getParent(); //父容器
            if (vp != null) {
                //如果父容器不是空的 把父容器里的东西remove掉
                //只要有imageview 父容器就不是空的
                ViewGroup group = (ViewGroup) vp;
                group.removeView(imageView);
            }
            container.addView(ivTemp[i]);//循环加载 要加载不一样的图片
            return ivTemp[i];
        }
    }


}

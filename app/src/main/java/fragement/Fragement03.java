package fragement;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


import java.util.Timer;
import java.util.TimerTask;

import lenovo.com.zhinengnongye.R;


public class Fragement03 extends Fragment implements View.OnClickListener{
    private RelativeLayout r1, r2,r3,r4;
    private Button btn1, btn2, btn3,btn4;
    private Timer timer;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RelativeLayout rl = null;
            switch (msg.what) {
                case 1:
                    rl = r1;
                    break;
                case 2:
                    rl = r2;
                    break;
                case 3:
                    rl = r3;
                    break;
                case 4:
                    rl = r4;
                    break;
            }
            float dipValue = (float) msg.obj;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rl.getLayoutParams();
            params.height = dippx(getActivity(), dipValue);
            rl.setLayoutParams(params);
        }
    };

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement03,null);

        //下拉tip1
        r1 = view.findViewById(R.id.f3_r_tip1);
        btn1 = view.findViewById(R.id.f3_btn_1);
        btn1.setOnClickListener(this);
        view.findViewById(R.id.f3_iv1).setOnClickListener(this);
        //下拉tip2
        r2 = view.findViewById(R.id.f3_r_tip2);
        btn2 = view.findViewById(R.id.f3_btn_2);
        btn2.setOnClickListener(this);
        view.findViewById(R.id.f3_iv2).setOnClickListener(this);
        //下拉tip3
        r3 = view.findViewById(R.id.f3_r_tip3);
        btn3 = view.findViewById(R.id.f3_btn_3);
        btn3.setOnClickListener(this);
        view.findViewById(R.id.f3_iv3).setOnClickListener(this);
        //下拉tip4
        r4 = view.findViewById(R.id.f3_r_tip4);
        btn4 = view.findViewById(R.id.f3_btn_4);
        btn4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.f3_iv1:
                btn1.callOnClick();
                break;
            case R.id.f3_btn_1://tip1点击事件
                if (r1.getHeight() == 0) {
                    open(1, setAnimation(true, r1));
                } else if (r1.getHeight() > 150) {
                    close(1, setAnimation(false, r1));
                }
                System.out.println("f3_btn_1");
                break;
            case R.id.f3_iv2:
                btn2.callOnClick();
                break;
            case R.id.f3_btn_2://tip2点击
                if (r2.getHeight() == 0) {
                    open(2, setAnimation(true, r2));
                } else if (r2.getHeight() > 150) {
                    close(2, setAnimation(false, r2));
                }
                break;
            case R.id.f3_iv3:
                btn3.callOnClick();
                break;
            case R.id.f3_btn_3://tip3点击
                if (r3.getHeight() == 0) {
                    open(3, setAnimation(true, r3));
                } else if (r3.getHeight() > 150) {
                    close(3, setAnimation(false, r3));
                }
                break;
            case R.id.f3_btn_4://tip4点击
                if (r4.getHeight() == 0) {
                    open(4, setAnimation(true, r4));
                } else if (r4.getHeight() > 150) {
                    close(4, setAnimation(false, r4));
                }
                break;
        }
    }

    /**
     * 配置动画
     *
     * @param b    true or fales 判断是关闭还是打开
     * @param view 传入需要操作的控件
     * @return
     */
    private ObjectAnimator setAnimation(boolean b, View view) {
        ObjectAnimator scaleY;
        if (b) {
            scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.01f, 1f);
            scaleY.setDuration(250);
        } else {
            scaleY = ObjectAnimator.ofFloat(view, "ScaleY", 1f, 0.01f);
            scaleY.setDuration(300);
        }
        return scaleY;
    }

    //展开页面
    private void open(final int i, ObjectAnimator scaleY) {
        if (timer == null) {
            timer = new Timer();
        } else {
            timer.cancel();
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            private float dipValue = 0;
            @Override
            public void run() {
                dipValue += 1;
                if (dipValue > 150) {
                    timer.cancel();
                } else {
                    Message msg = new Message();
                    msg.what = i;
                    msg.obj = dipValue;
                    handler.sendMessage(msg);
                }
            }
        }, 1, 1);
        scaleY.start();
    }

    //关闭页面
    private void close(final int i, ObjectAnimator scaleY) {
        if (timer == null) {
            timer = new Timer();
        } else {
            timer.cancel();
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            private float dipValue = 150;
            @Override
            public void run() {
                dipValue -= 1;
                if (dipValue < 0) {
                    timer.cancel();
                } else {
                    Message msg = new Message();
                    msg.what = i;
                    msg.obj = dipValue;
                    handler.sendMessage(msg);
                }
            }
        }, 1, 1);
        scaleY.start();
    }

    private int dippx(Context context, float dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }

}

package lenovo.com.zhinengnongye;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import utils.MyApp;
import utils.MyOkHttp;

public class F1Co2 extends AppCompatActivity implements View.OnClickListener {
    ImageView include_dkgz, include_dksb, include_exit, include_dkfs, include_dkbj;
    TextView include_tv, f1_co2time, f1_co2set;
    MyApp myApp;
    MyOkHttp myOkHttp = new MyOkHttp();

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 1001:
                   include_dkfs.setImageResource(R.mipmap.dakaifengshan2);
                   break;
               case 1002:
                   include_dkfs.setImageResource(R.mipmap.dakaifengshan);
                   break;

               case 2001:
                   include_dkbj.setImageResource(R.mipmap.dakaibaojing2);
                   break;
               case 2002:
                   include_dkbj.setImageResource(R.mipmap.dakaibaojing);
                   break;
               case 3000:
                   String dataArr[] = (String[]) msg.obj;
                   f1_co2time.setText(dataArr[0]);
                   break;
           }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f1_co2);
        /**
         * 隐藏不要的图片
         */
        include_dkgz = findViewById(R.id.include_dkgz);
        include_dksb = findViewById(R.id.include_dksb);
        include_dkfs = findViewById(R.id.include_dkfs);
        include_dkbj = findViewById(R.id.include_dkbj);
        include_dkfs.setOnClickListener(this);
        include_dkbj.setOnClickListener(this);
        include_tv = findViewById(R.id.include_tv);
        include_tv.setText("CO2详情"); //更改title
        include_dkgz.setVisibility(View.GONE);
        include_dksb.setVisibility(View.GONE);
        f1_co2time = findViewById(R.id.f1_co2time);
        f1_co2set = findViewById(R.id.f1_co2set);

        include_exit = findViewById(R.id.include_exit);
        include_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /**
         * 设定页面数值
         */
        setConfig();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.include_dkfs:
                getContorllerStatus(0);
                break;
            case R.id.include_dkbj:
                getContorllerStatus(1);
                break;
        }

    }

    /**
     * 获取控件当前状态
     */
    private void getContorllerStatus(int i){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int[] dataArr = myOkHttp.getContorllerStatus(myApp);
                    switch (i){
                        case 0:
                            Message message = new Message();
                            if (dataArr[1]==0){
                                myOkHttp.control(myApp,"Blower",1);
                                message.what=1001;
                                handler.sendMessage(message);
                            }else {
                                myOkHttp.control(myApp,"Blower",0);
                                message.what=1002;
                                handler.sendMessage(message);
                            }
                            break;
                        case 1:
                            Message message2 = new Message();
                            if (dataArr[3]==0){
                                myOkHttp.control(myApp,"Buzzer",1);
                                message2.what=2001;
                                handler.sendMessage(message2);
                            }else {
                                myOkHttp.control(myApp,"Buzzer",0);
                                message2.what=2002;
                                handler.sendMessage(message2);
                            }
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 改数值
     */
    private void setConfig() {
        myApp = (MyApp) getApplication();
        f1_co2set.setText(myApp.getMinCo2() + "~~" + myApp.getMaxCo2());

        Message msg = new Message();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myOkHttp.getSensor(myApp);
                    String[] dataArr = myOkHttp.getSensor(myApp);
                    msg.what = 3000;
                    msg.obj = dataArr;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

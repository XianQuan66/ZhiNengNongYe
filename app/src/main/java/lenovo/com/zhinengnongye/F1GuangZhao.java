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

import utils.MyApp;
import utils.MyOkHttp;

public class F1GuangZhao extends AppCompatActivity implements View.OnClickListener{
    ImageView include_dkfs, include_dksb, include_exit,include_dkgz,include_dkbj;
    TextView include_tv,f1_guangzhaotime,f1_guangzhaoset;
    MyApp myApp;
    MyOkHttp myOkHttp = new MyOkHttp();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1001:
                    include_dkgz.setImageResource(R.mipmap.dakaiguangzhao2);
                    break;
                case 1002:
                    include_dkgz.setImageResource(R.mipmap.dakaiguangzhao);
                    break;
                case 2001:
                    include_dkbj.setImageResource(R.mipmap.dakaibaojing2);
                    break;
                case 2002:
                    include_dkbj.setImageResource(R.mipmap.dakaibaojing);
                    break;
                case 3000:
                    String dataArr[] = (String[]) msg.obj;
                    f1_guangzhaotime.setText(dataArr[1]);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f1_guang_zhao);

        include_dkfs = findViewById(R.id.include_dkfs);
        include_dksb = findViewById(R.id.include_dksb);
        include_tv = findViewById(R.id.include_tv);
        include_tv.setText("光照详情"); //更改title
        include_dkfs.setVisibility(View.GONE);
        include_dksb.setVisibility(View.GONE);
        f1_guangzhaotime = findViewById(R.id.f1_guangzhaotime);
        f1_guangzhaoset = findViewById(R.id.f1_guangzhaoset);
        include_dkgz = findViewById(R.id.include_dkgz);
        include_dkbj = findViewById(R.id.include_dkbj);
        include_dkgz.setOnClickListener(this);
        include_dkbj.setOnClickListener(this);

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
        switch (view.getId()){
            case R.id.include_dkgz:
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
                            Message message1 = new Message();
                            if (dataArr[2]==0){
                                myOkHttp.control(myApp,"Roadlamp",1);
                                message1.what=1001;
                                handler.sendMessage(message1);
                            }else {
                                myOkHttp.control(myApp,"Roadlamp",0);
                                message1.what=1002;
                                handler.sendMessage(message1);
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
    private void setConfig(){
        myApp = (MyApp) getApplication();
        f1_guangzhaoset.setText(myApp.getMinLight()+ "~~" + myApp.getMaxLight());

        Message msg = new Message();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myOkHttp.getSensor(myApp);
                    String[] dataArr = myOkHttp.getSensor(myApp);
                    msg.what=3000;
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

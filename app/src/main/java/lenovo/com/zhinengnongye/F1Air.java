package lenovo.com.zhinengnongye;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import utils.MyApp;
import utils.MyOkHttp;

public class F1Air extends AppCompatActivity implements View.OnClickListener{
    ImageView include_exit,include_dkfs,include_dkgz,include_dksb,include_dkbj;
    TextView f1_airwdtime, f1_airsdtime, f1_airwdset, f1_airsdset;
    MyOkHttp myOkHttp = new MyOkHttp();
    MyApp myApp;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1001:
                    include_dkfs.setImageResource(R.mipmap.dakaifengshan2);
                    break;
                case 1002:
                    include_dkfs.setImageResource(R.mipmap.dakaifengshan);
                    break;
                case 3000:
                    String dataArr[] = (String[]) msg.obj;
                    f1_airwdtime.setText(dataArr[4]);
                    f1_airsdtime.setText(dataArr[5]);
                    break;
                case 2001:
                    include_dkgz.setImageResource(R.mipmap.dakaiguangzhao2);
                    break;
                case 2002:
                    include_dkgz.setImageResource(R.mipmap.dakaiguangzhao);
                    break;
                case 3001:
                    include_dkbj.setImageResource(R.mipmap.dakaibaojing2);
                    break;
                case 3002:
                    include_dkbj.setImageResource(R.mipmap.dakaibaojing);
                    break;
                case 4001:
                    include_dksb.setImageResource(R.mipmap.dakaishui2);
                    break;
                case 4002:
                    include_dksb.setImageResource(R.mipmap.dakaishui);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f1_air);

        f1_airwdtime = findViewById(R.id.f1_airwdtime);
        f1_airsdtime = findViewById(R.id.f1_airsdtime);
        f1_airwdset = findViewById(R.id.f1_airwdset);
        f1_airsdset = findViewById(R.id.f1_airsdset);

        include_exit = findViewById(R.id.include_exit);
        include_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        include_dkfs = findViewById(R.id.include_dkfs);
        include_dkgz = findViewById(R.id.include_dkgz);
        include_dksb = findViewById(R.id.include_dksb);
        include_dkbj = findViewById(R.id.include_dkbj);
        include_dkfs.setOnClickListener(this);
        include_dkgz.setOnClickListener(this);
        include_dksb.setOnClickListener(this);
        include_dkbj.setOnClickListener(this);

        /**
         * 设定页面数值
         */
        setConfig();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.include_dkfs:
                getContorllerStatus(0);
                break;
            case R.id.include_dkgz:
                getContorllerStatus(1);
                break;
            case R.id.include_dksb:
                getContorllerStatus(3);
                break;
            case R.id.include_dkbj:
                getContorllerStatus(2);
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
                            Message message1 = new Message();
                            if (dataArr[2]==0){
                                myOkHttp.control(myApp,"Roadlamp",1);
                                message1.what=2001;
                                handler.sendMessage(message1);
                            }else {
                                myOkHttp.control(myApp,"Roadlamp",0);
                                message1.what=2002;
                                handler.sendMessage(message1);
                            }
                            break;
                        case 2:
                            Message message2 = new Message();
                            if (dataArr[3]==0){
                                myOkHttp.control(myApp,"Buzzer",1);
                                message2.what=3001;
                                handler.sendMessage(message2);
                            }else {
                                myOkHttp.control(myApp,"Buzzer",0);
                                message2.what=3002;
                                handler.sendMessage(message2);
                            }
                            break;
                        case 3:
                            Message message3 = new Message();
                            if (dataArr[0]==0){
                                myOkHttp.control(myApp,"WaterPump",1);
                                message3.what=4001;
                                handler.sendMessage(message3);
                            }else {
                                myOkHttp.control(myApp,"WaterPump",0);
                                message3.what=4002;
                                handler.sendMessage(message3);
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
        f1_airwdset.setText(myApp.getMinAirTemperature()+ "~~" + myApp.getMaxAirTemperature());
        f1_airsdset.setText(myApp.getMinAirHumidity()+ "~~" + myApp.getMaxAirHumidity());

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

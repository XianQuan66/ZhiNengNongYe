package lenovo.com.zhinengnongye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import utils.MyApp;

public class IPActivity extends AppCompatActivity {
    private EditText etIP;
    private Button btnLogin;
    View ip_linear;
    String ip;

    /**
     * 登陆界面
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ip);

        //getSupportActionBar().hide();//隐藏标题栏，写在setContentView下面

        etIP = findViewById(R.id.ip_edt);
        btnLogin = findViewById(R.id.btn_login);
        ip_linear = findViewById(R.id.ip_linear);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ip = etIP.getText().toString().trim();
                if (isIP(ip)) {
                    MyApp myApp = (MyApp) getApplication();
                    myApp.setIp(ip);
                    Toast.makeText(IPActivity.this, "欢迎您", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(IPActivity.this, BodyActivity.class));
                    finish();//用完直接finish这个页面
                } else {
                    Toast.makeText(IPActivity.this, "请输入正确的ip地址", Toast.LENGTH_SHORT).show();
                }
            }
        });

        myAnimation(); //调用动画
    }


    /**
     * 判断输入ip的方法
     * 用正则表达式来判断输入的是否是一个正确的ip地址
     *
     * @param ip
     * @return
     */
    private boolean isIP(String ip) {
        boolean b = false;
        /**
         * 可以直接百度正则表达式 Java ip
         */
        boolean matches = ip.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
        if (matches) {
            String[] split = ip.split("\\.");

            if (Integer.parseInt(split[0]) <= 255) {
                if (Integer.parseInt(split[1]) <= 255) {
                    if (Integer.parseInt(split[2]) <= 255) {
                        if (Integer.parseInt(split[3]) <= 255) {
                            b = true;
                        }
                    }
                }
            }
        }
        return b;
    }

    /**
     * IP窗口透明动画效果
     */
    private void myAnimation() {
        //
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 2.0f);
        //设置动画时长
        alphaAnimation.setDuration(5000);
        ip_linear.startAnimation(alphaAnimation);
    }

}

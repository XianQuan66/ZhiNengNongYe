package lenovo.com.zhinengnongye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import shoudong.Air;
import shoudong.Co2;
import shoudong.GuangZhao;
import shoudong.TuRang;

public class ShouDong extends AppCompatActivity implements View.OnClickListener {
    ImageView sdiv_exit;
    RelativeLayout sdrl_co2, sdrl_guangzhao, sdrl_air, sdrl_turang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_dong);
        init();
    }

    private void init() {
        sdiv_exit = findViewById(R.id.sdiv_exit);
        sdiv_exit.setOnClickListener(this);
        sdrl_co2 = findViewById(R.id.sdrl_co2);
        sdrl_co2.setOnClickListener(this);
        sdrl_guangzhao = findViewById(R.id.sdrl_guangzhao);
        sdrl_guangzhao.setOnClickListener(this);
        sdrl_air = findViewById(R.id.sdrl_air);
        sdrl_air.setOnClickListener(this);
        sdrl_turang = findViewById(R.id.sdrl_turang);
        sdrl_turang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sdiv_exit:
                startActivity(new Intent(getApplicationContext(), BodyActivity.class));
                break;
            case R.id.sdrl_co2:
                startActivity(new Intent(getApplicationContext(), Co2.class));
                break;
            case R.id.sdrl_guangzhao:
                startActivity(new Intent(getApplicationContext(), GuangZhao.class));
                break;
            case R.id.sdrl_air:
                startActivity(new Intent(getApplicationContext(), Air.class));
                break;
            case R.id.sdrl_turang:
                startActivity(new Intent(getApplicationContext(), TuRang.class));
                break;
        }
    }
}

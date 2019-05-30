package shoudong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import lenovo.com.zhinengnongye.R;
import lenovo.com.zhinengnongye.ShouDong;

public class GuangZhao extends AppCompatActivity {
    Button guangzhao_btn;
    ImageView guangzhao_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guang_zhao);
        guangzhao_btn = findViewById(R.id.guangzhao_btn);
        guangzhao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GuangZhao.this, "设置成功", Toast.LENGTH_SHORT).show();
            }
        });
        guangzhao_exit = findViewById(R.id.guangzhao_exit);
        guangzhao_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ShouDong.class));
            }
        });
    }
}

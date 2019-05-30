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

public class Co2 extends AppCompatActivity {
    Button co2_btn;
    ImageView co2_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co2);
        co2_btn=findViewById(R.id.co2_btn);
        co2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Co2.this, "设置成功", Toast.LENGTH_SHORT).show();
            }
        });
        co2_exit = findViewById(R.id.co2_exit);
        co2_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ShouDong.class));
            }
        });
    }
}

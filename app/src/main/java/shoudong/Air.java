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

public class Air extends AppCompatActivity {
    Button air_btn;
    ImageView air_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air);
        air_btn=findViewById(R.id.air_btn);
        air_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Air.this, "设置成功", Toast.LENGTH_SHORT).show();
            }
        });
        air_exit = findViewById(R.id.air_exit);
        air_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ShouDong.class));
            }
        });
    }
}

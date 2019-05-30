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

public class TuRang extends AppCompatActivity {
    Button turang_btn;
    ImageView turang_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_rang);
        turang_btn=findViewById(R.id.turang_btn);
        turang_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TuRang.this, "设置成功", Toast.LENGTH_SHORT).show();
            }
        });
        turang_exit = findViewById(R.id.turang_exit);
        turang_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ShouDong.class));
            }
        });    }
}

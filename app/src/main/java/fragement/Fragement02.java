package fragement;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import lenovo.com.zhinengnongye.R;
import lenovo.com.zhinengnongye.ShouDong;

public class Fragement02 extends Fragment implements View.OnClickListener {
    RelativeLayout rel_sd, rel_clean, rel_update, rel_guanyu, rel_exit,rl_xiahua;  //点击
    private boolean open = false;
    TextView version;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement02, null);
        rel_sd = view.findViewById(R.id.rel_sd);
        rel_sd.setOnClickListener(this);
        rel_clean = view.findViewById(R.id.rel_clean);
        rel_clean.setOnClickListener(this);
        rel_update = view.findViewById(R.id.rel_update);
        version = view.findViewById(R.id.version);
        //PackageManager主要管理应用程序包（apk），通过它可以获取应用程序信息
        try {
            PackageManager manager = getActivity().getPackageManager();
            //获取应用程序信息
            PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), 0);
            String versionName = info.versionName;
            version.setText("当前版本号为"+versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        rel_update.setOnClickListener(this);
        rel_guanyu = view.findViewById(R.id.rel_guanyu);
        rel_guanyu.setOnClickListener(this);
        rel_exit = view.findViewById(R.id.rel_exit);
        rel_exit.setOnClickListener(this);
        rl_xiahua = view.findViewById(R.id.f2_rl);
        return view;
    }

    /**
     * 第二个页面点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rel_sd:
                startActivity(new Intent(getContext(),ShouDong.class));
                break;
            case R.id.rel_clean:
                System.gc(); //java 垃圾回收机制
                Toast.makeText(getContext(), "缓存清理完毕", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rel_update:
                Toast.makeText(getContext(), "已经是最新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rel_guanyu:
                change();
                break;
            case R.id.rel_exit:
                Toast.makeText(getContext(), "再次点击退出", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 关于我们下拉事件
     */
    private void change() {
        if (open){
            ObjectAnimator translationY = ObjectAnimator.ofFloat(rl_xiahua, "translationY", 250, 0);
            translationY.setDuration(800);
            translationY.start();
            open = false;
        }else {
            ObjectAnimator translationY = ObjectAnimator.ofFloat(rl_xiahua, "translationY", 0, 250);
            translationY.setDuration(800);
            translationY.start();
            open = true;
        }


    }
}

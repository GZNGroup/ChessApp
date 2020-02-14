package com.example.myapplication3;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.widget.Toast;






public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) //这是初始化页面的方法
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    int size=30;
    public void bigger(View v)
    {
        // 放大按钮对应的 onclick 响应的方法
        TextView txv;
        txv= findViewById(R.id.txv);  // 根据ID找到对应的text对象
        txv.setTextSize(++size);       // 修改对象的字符大小-size
    }
    public void smaller(View v)
    {
        // 缩小按钮对应的 onclick 响应的方法
        TextView txv;
        txv= findViewById(R.id.txv);  // 根据ID找到对应的text对象
        txv.setTextSize(--size);       // 修改对象的字符大小-size
    }
    public void display(View v)
    {     // 显示按钮对应的 onclick 响应的方法
        EditText name= findViewById(R.id.name);  //还是根据ID找到对象，并进行接下来的操作
        TextView text2=  findViewById(R.id.txv);
        text2.setText(name.getText().toString());// 设置字符
    }

    public void click(View v)
    {//点击设置中的窗口对应的方法
        switch (v.getId())
        {
            case R.id.btn_4:
                showSingleDialog();
                break;
            case R.id.btn_6:
                showWaitDialog();
                break;
        }
    }
    int ide = 0;  //全局变量
    private void showSingleDialog()
    {
        final String[] size = {"小", "中", "大"};
        final AlertDialog.Builder dialog4 = new AlertDialog.Builder(this)
                .setTitle("选择字体大小:")//参数1：选项
                .setSingleChoiceItems(size, 0, new DialogInterface.OnClickListener()//参数2：选择
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        ide = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() //参数3：选中时的事件
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MainActivity.this, "字体大小更改为：" + size[ide], Toast.LENGTH_LONG).show();
                    }
                });   //点击确定后对话框消失，并且打印所选内容

        dialog4.show();
    }

    private void showWaitDialog()
    {
        //进度条对话框，默认是转圈
        final ProgressDialog dialog = ProgressDialog.show(this, "正在清理",
                "请稍等 …", true, true);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);//让他显示3秒后再消失
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
                Looper.prepare();
                Toast.makeText(MainActivity.this, "缓存清理完毕", Toast.LENGTH_LONG).show();
                Looper.loop();//类似上下文切换
            }
        });
        t1.start();
    }
}




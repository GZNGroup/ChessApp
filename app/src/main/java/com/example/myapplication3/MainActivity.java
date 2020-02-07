package com.example.myapplication3;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        // 按钮对应的 onclick 响应
        TextView txv;
        txv= findViewById(R.id.txv);  // 根据ID找到对应的text对象
        txv.setTextSize(++size);       // 修改对象的字符大小-size
    }
    public void smaller(View v)
    {
        // 按钮对应的 onclick 响应
        TextView txv;
        txv= findViewById(R.id.txv);  // 根据ID找到对应的text对象
        txv.setTextSize(--size);       // 修改对象的字符大小-size
    }
    public void display(View v)
    {     // 另外一个按钮对应的 onclick 响应
        EditText name= findViewById(R.id.name);  //还是根据ID找到对象，并进行接下来的操作
        TextView text2=  findViewById(R.id.txv);
        text2.setText(name.getText().toString());// 设置字符
    }
}

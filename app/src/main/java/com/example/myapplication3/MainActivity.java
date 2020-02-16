package com.example.myapplication3;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.widget.Toast;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;





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


    int ide = 0;  //全局变量
    public void showSingleDialog(View v)//虽然下面没有用到View v，但是不加就报错
    {

        final String[] size = {"小", "中", "大"};
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this)
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

        dialog.show();
    }

    public void showWaitDialog(View v)//虽然下面没有用到View v，但是不加就报错
    {
        //进度条对话框，默认是转圈
        final ProgressDialog dialog = ProgressDialog.show(this, "正在清理",
                "请稍等 …", true, true);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(3000);//让他显示3秒后再消失
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                dialog.dismiss();
                Looper.prepare();//类似上下文切换，不加就报错
                Toast.makeText(MainActivity.this, "缓存清理完毕", Toast.LENGTH_LONG).show();
                Looper.loop();//类似上下文切换，不加就报错
            }
        });
        thread.start();
    }
    //注意 左上角是（0，0），横轴是X轴，纵轴是Y轴
    int goal_x=0,goal_y=0;//棋子移动的目标位置
    int white_king_x=0,white_king_y=0;//白棋国王的位置
    int white_queen_x=0,white_queen_y=0;//白棋皇后的位置
    int white_rook_x=0,white_rook_y=0;//白棋战车的位置
    int white_bishop_x=0,white_bishop_y=0;//白棋主教的位置
    int white_knight_x=0,white_knight_y=0;//白棋骑士的位置
    int white_pawn_x=0,white_pawn_y=0;//白棋禁卫军的位置
    int black_king_x=0,black_king_y=0;//黑棋国王的位置
    int black_queen_x=0,black_queen_y=0;//黑棋皇后的位置
    int black_rook_x=0,black_rook_y=0;//黑棋战车的位置
    int black_bishop_x=0,black_bishop_y=0;//黑棋主教的位置
    int black_knight_x=0,black_knight_y=0;//黑棋骑士的位置
    int black_pawn_x=0,black_pawn_y=0;//黑棋禁卫军的位置

    public void translate(View v)//虽然下面没有用到View v，但是不加就报错
    {
        String input = ((EditText)findViewById(R.id.editText)).getText().toString();
        char[] move = input.toCharArray(); //将字符串变量转换为字符数组
        switch(move[0])
        {
            case 'W'://表示选择的是白方的棋子
            {
                switch(move[1])
                {
                    case 'K'://表示选择的是国王
                    {
                        ImageView white_king = findViewById(R.id.white_king);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>white_king_x&&goal_y>white_king_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_king, "translationX",white_king_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_king, "translationY",white_king_y,goal_y);
                            white_king_x=goal_x;
                            white_king_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_king_x&&goal_y>white_king_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_king, "translationX",white_king_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_king, "translationY",white_king_y,goal_y);
                            white_king_x=goal_x;
                            white_king_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_king_x&&goal_y<white_king_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_king, "translationX",white_king_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_king, "translationY",white_king_y,goal_y);
                            white_king_x=goal_x;
                            white_king_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>white_king_x&&goal_y<white_king_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_king, "translationX",white_king_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_king, "translationY",white_king_y,goal_y);
                            white_king_x=goal_x;
                            white_king_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==white_king_x&&goal_y<white_king_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_king, "translationY",white_king_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==white_king_x&&goal_y>white_king_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_king, "translationY",white_king_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<white_king_x&&goal_y==white_king_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_king, "translationX",white_king_x,goal_x);white_king_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>white_king_x&&goal_y==white_king_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_king, "translationX",white_king_x,goal_x);white_king_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;

                    }
                    case 'Q'://表示选择的是皇后
                    {
                        ImageView white_queen = findViewById(R.id.white_queen);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>white_queen_x&&goal_y>white_queen_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_queen, "translationX",white_queen_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_queen, "translationY",white_queen_y,goal_y);
                            white_queen_x=goal_x;
                            white_queen_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_queen_x&&goal_y>white_queen_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_queen, "translationX",white_queen_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_queen, "translationY",white_queen_y,goal_y);
                            white_queen_x=goal_x;
                            white_queen_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_queen_x&&goal_y<white_queen_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_queen, "translationX",white_queen_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_queen, "translationY",white_queen_y,goal_y);
                            white_queen_x=goal_x;
                            white_queen_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>white_queen_x&&goal_y<white_queen_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_queen, "translationX",white_queen_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_queen, "translationY",white_queen_y,goal_y);
                            white_queen_x=goal_x;
                            white_queen_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==white_queen_x&&goal_y<white_queen_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_queen, "translationY",white_queen_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==white_queen_x&&goal_y>white_queen_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_queen, "translationY",white_queen_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<white_queen_x&&goal_y==white_queen_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_queen, "translationX",white_queen_x,goal_x);white_queen_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>white_queen_x&&goal_y==white_queen_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_queen, "translationX",white_queen_x,goal_x);white_queen_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;
                    }
                    case 'R'://表示选择的是战车
                    {
                        ImageView white_rook = findViewById(R.id.white_rook);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>white_rook_x&&goal_y>white_rook_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_rook, "translationX",white_rook_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_rook, "translationY",white_rook_y,goal_y);
                            white_rook_x=goal_x;
                            white_rook_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_rook_x&&goal_y>white_rook_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_rook, "translationX",white_rook_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_rook, "translationY",white_rook_y,goal_y);
                            white_rook_x=goal_x;
                            white_rook_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_rook_x&&goal_y<white_rook_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_rook, "translationX",white_rook_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_rook, "translationY",white_rook_y,goal_y);
                            white_rook_x=goal_x;
                            white_rook_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>white_rook_x&&goal_y<white_rook_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_rook, "translationX",white_rook_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_rook, "translationY",white_rook_y,goal_y);
                            white_rook_x=goal_x;
                            white_rook_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==white_rook_x&&goal_y<white_rook_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_rook, "translationY",white_rook_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==white_rook_x&&goal_y>white_rook_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_rook, "translationY",white_rook_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<white_rook_x&&goal_y==white_rook_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_rook, "translationX",white_rook_x,goal_x);white_rook_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>white_rook_x&&goal_y==white_rook_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_rook, "translationX",white_rook_x,goal_x);white_rook_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;
                    }
                    case 'B'://表示选择的是主教
                    {
                        ImageView white_bishop = findViewById(R.id.white_bishop);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>white_bishop_x&&goal_y>white_bishop_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_bishop, "translationX",white_bishop_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_bishop, "translationY",white_bishop_y,goal_y);
                            white_bishop_x=goal_x;
                            white_bishop_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_bishop_x&&goal_y>white_bishop_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_bishop, "translationX",white_bishop_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_bishop, "translationY",white_bishop_y,goal_y);
                            white_bishop_x=goal_x;
                            white_bishop_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_bishop_x&&goal_y<white_bishop_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_bishop, "translationX",white_bishop_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_bishop, "translationY",white_bishop_y,goal_y);
                            white_bishop_x=goal_x;
                            white_bishop_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>white_bishop_x&&goal_y<white_bishop_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_bishop, "translationX",white_bishop_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_bishop, "translationY",white_bishop_y,goal_y);
                            white_bishop_x=goal_x;
                            white_bishop_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==white_bishop_x&&goal_y<white_bishop_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_bishop, "translationY",white_bishop_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==white_bishop_x&&goal_y>white_bishop_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_bishop, "translationY",white_bishop_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<white_bishop_x&&goal_y==white_bishop_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_bishop, "translationX",white_bishop_x,goal_x);white_bishop_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>white_bishop_x&&goal_y==white_bishop_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_bishop, "translationX",white_bishop_x,goal_x);white_bishop_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;
                    }
                    case 'N'://表示选择的是骑士
                    {
                        ImageView white_knight = findViewById(R.id.white_knight);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>white_knight_x&&goal_y>white_knight_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_knight, "translationX",white_knight_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_knight, "translationY",white_knight_y,goal_y);
                            white_knight_x=goal_x;
                            white_knight_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_knight_x&&goal_y>white_knight_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_knight, "translationX",white_knight_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_knight, "translationY",white_knight_y,goal_y);
                            white_knight_x=goal_x;
                            white_knight_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_knight_x&&goal_y<white_knight_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_knight, "translationX",white_knight_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_knight, "translationY",white_knight_y,goal_y);
                            white_knight_x=goal_x;
                            white_knight_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>white_knight_x&&goal_y<white_knight_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_knight, "translationX",white_knight_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_knight, "translationY",white_knight_y,goal_y);
                            white_knight_x=goal_x;
                            white_knight_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==white_knight_x&&goal_y<white_knight_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_knight, "translationY",white_knight_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==white_knight_x&&goal_y>white_knight_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_knight, "translationY",white_knight_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<white_knight_x&&goal_y==white_knight_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_knight, "translationX",white_knight_x,goal_x);white_knight_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>white_knight_x&&goal_y==white_knight_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_knight, "translationX",white_knight_x,goal_x);white_knight_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;
                    }
                    case 'P'://表示选择的是禁卫军
                    {
                        ImageView white_pawn = findViewById(R.id.white_pawn);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>white_pawn_x&&goal_y>white_pawn_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn, "translationX",white_pawn_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn, "translationY",white_pawn_y,goal_y);
                            white_pawn_x=goal_x;
                            white_pawn_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_pawn_x&&goal_y>white_pawn_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn, "translationX",white_pawn_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn, "translationY",white_pawn_y,goal_y);
                            white_pawn_x=goal_x;
                            white_pawn_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<white_pawn_x&&goal_y<white_pawn_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn, "translationX",white_pawn_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn, "translationY",white_pawn_y,goal_y);
                            white_pawn_x=goal_x;
                            white_pawn_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>white_pawn_x&&goal_y<white_pawn_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn, "translationX",white_pawn_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn, "translationY",white_pawn_y,goal_y);
                            white_pawn_x=goal_x;
                            white_pawn_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==white_pawn_x&&goal_y<white_pawn_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn, "translationY",white_pawn_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==white_pawn_x&&goal_y>white_pawn_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn, "translationY",white_pawn_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<white_pawn_x&&goal_y==white_pawn_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn, "translationX",white_pawn_x,goal_x);white_pawn_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>white_pawn_x&&goal_y==white_pawn_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn, "translationX",white_pawn_x,goal_x);white_pawn_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;
                    }
                    default:break;
                }

            }
            case 'B'://表示选择的是黑方的棋子
            {
                switch(move[1])
                {
                    case 'K'://表示选择的是国王
                    {
                        ImageView black_king = findViewById(R.id.black_king);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>black_king_x&&goal_y>black_king_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_king, "translationX",black_king_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_king, "translationY",black_king_y,goal_y);
                            black_king_x=goal_x;
                            black_king_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_king_x&&goal_y>black_king_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_king, "translationX",black_king_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_king, "translationY",black_king_y,goal_y);
                            black_king_x=goal_x;
                            black_king_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_king_x&&goal_y<black_king_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_king, "translationX",black_king_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_king, "translationY",black_king_y,goal_y);
                            black_king_x=goal_x;
                            black_king_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>black_king_x&&goal_y<black_king_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_king, "translationX",black_king_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_king, "translationY",black_king_y,goal_y);
                            black_king_x=goal_x;
                            black_king_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==black_king_x&&goal_y<black_king_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_king, "translationY",black_king_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==black_king_x&&goal_y>black_king_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_king, "translationY",black_king_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<black_king_x&&goal_y==black_king_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_king, "translationX",black_king_x,goal_x);black_king_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>black_king_x&&goal_y==black_king_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_king, "translationX",black_king_x,goal_x);black_king_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;

                    }
                    case 'Q'://表示选择的是皇后
                    {
                        ImageView black_queen = findViewById(R.id.black_queen);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>black_queen_x&&goal_y>black_queen_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_queen, "translationX",black_queen_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_queen, "translationY",black_queen_y,goal_y);
                            black_queen_x=goal_x;
                            black_queen_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_queen_x&&goal_y>black_queen_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_queen, "translationX",black_queen_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_queen, "translationY",black_queen_y,goal_y);
                            black_queen_x=goal_x;
                            black_queen_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_queen_x&&goal_y<black_queen_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_queen, "translationX",black_queen_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_queen, "translationY",black_queen_y,goal_y);
                            black_queen_x=goal_x;
                            black_queen_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>black_queen_x&&goal_y<black_queen_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_queen, "translationX",black_queen_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_queen, "translationY",black_queen_y,goal_y);
                            black_queen_x=goal_x;
                            black_queen_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==black_queen_x&&goal_y<black_queen_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_queen, "translationY",black_queen_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==black_queen_x&&goal_y>black_queen_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_queen, "translationY",black_queen_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<black_queen_x&&goal_y==black_queen_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_queen, "translationX",black_queen_x,goal_x);black_queen_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>black_queen_x&&goal_y==black_queen_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_queen, "translationX",black_queen_x,goal_x);black_queen_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;
                    }
                    case 'R'://表示选择的是战车
                    {
                        ImageView black_rook = findViewById(R.id.black_rook);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>black_rook_x&&goal_y>black_rook_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_rook, "translationX",black_rook_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_rook, "translationY",black_rook_y,goal_y);
                            black_rook_x=goal_x;
                            black_rook_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_rook_x&&goal_y>black_rook_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_rook, "translationX",black_rook_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_rook, "translationY",black_rook_y,goal_y);
                            black_rook_x=goal_x;
                            black_rook_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_rook_x&&goal_y<black_rook_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_rook, "translationX",black_rook_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_rook, "translationY",black_rook_y,goal_y);
                            black_rook_x=goal_x;
                            black_rook_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>black_rook_x&&goal_y<black_rook_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_rook, "translationX",black_rook_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_rook, "translationY",black_rook_y,goal_y);
                            black_rook_x=goal_x;
                            black_rook_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==black_rook_x&&goal_y<black_rook_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_rook, "translationY",black_rook_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==black_rook_x&&goal_y>black_rook_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_rook, "translationY",black_rook_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<black_rook_x&&goal_y==black_rook_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_rook, "translationX",black_rook_x,goal_x);black_rook_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>black_rook_x&&goal_y==black_rook_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_rook, "translationX",black_rook_x,goal_x);black_rook_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;
                    }
                    case 'B'://表示选择的是主教
                    {
                        ImageView black_bishop = findViewById(R.id.black_bishop);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>black_bishop_x&&goal_y>black_bishop_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_bishop, "translationX",black_bishop_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_bishop, "translationY",black_bishop_y,goal_y);
                            black_bishop_x=goal_x;
                            black_bishop_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_bishop_x&&goal_y>black_bishop_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_bishop, "translationX",black_bishop_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_bishop, "translationY",black_bishop_y,goal_y);
                            black_bishop_x=goal_x;
                            black_bishop_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_bishop_x&&goal_y<black_bishop_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_bishop, "translationX",black_bishop_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_bishop, "translationY",black_bishop_y,goal_y);
                            black_bishop_x=goal_x;
                            black_bishop_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>black_bishop_x&&goal_y<black_bishop_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_bishop, "translationX",black_bishop_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_bishop, "translationY",black_bishop_y,goal_y);
                            black_bishop_x=goal_x;
                            black_bishop_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==black_bishop_x&&goal_y<black_bishop_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_bishop, "translationY",black_bishop_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==black_bishop_x&&goal_y>black_bishop_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_bishop, "translationY",black_bishop_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<black_bishop_x&&goal_y==black_bishop_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_bishop, "translationX",black_bishop_x,goal_x);black_bishop_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>black_bishop_x&&goal_y==black_bishop_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_bishop, "translationX",black_bishop_x,goal_x);black_bishop_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;
                    }
                    case 'N'://表示选择的是骑士
                    {
                        ImageView black_knight = findViewById(R.id.black_knight);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>black_knight_x&&goal_y>black_knight_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_knight, "translationX",black_knight_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_knight, "translationY",black_knight_y,goal_y);
                            black_knight_x=goal_x;
                            black_knight_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_knight_x&&goal_y>black_knight_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_knight, "translationX",black_knight_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_knight, "translationY",black_knight_y,goal_y);
                            black_knight_x=goal_x;
                            black_knight_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_knight_x&&goal_y<black_knight_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_knight, "translationX",black_knight_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_knight, "translationY",black_knight_y,goal_y);
                            black_knight_x=goal_x;
                            black_knight_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>black_knight_x&&goal_y<black_knight_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_knight, "translationX",black_knight_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_knight, "translationY",black_knight_y,goal_y);
                            black_knight_x=goal_x;
                            black_knight_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==black_knight_x&&goal_y<black_knight_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_knight, "translationY",black_knight_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==black_knight_x&&goal_y>black_knight_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_knight, "translationY",black_knight_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<black_knight_x&&goal_y==black_knight_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_knight, "translationX",black_knight_x,goal_x);black_knight_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>black_knight_x&&goal_y==black_knight_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_knight, "translationX",black_knight_x,goal_x);black_knight_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;
                    }
                    case 'P'://表示选择的是禁卫军
                    {
                        ImageView black_pawn = findViewById(R.id.black_pawn);//图形
                        goal_x=120*(move[2]-'a');
                        goal_y=+120*('8'-move[3]);
                        if(goal_x>black_pawn_x&&goal_y>black_pawn_y)//向右下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn, "translationX",black_pawn_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn, "translationY",black_pawn_y,goal_y);
                            black_pawn_x=goal_x;
                            black_pawn_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_pawn_x&&goal_y>black_pawn_y)//向左下方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn, "translationX",black_pawn_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn, "translationY",black_pawn_y,goal_y);
                            black_pawn_x=goal_x;
                            black_pawn_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x<black_pawn_x&&goal_y<black_pawn_y)//向左上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn, "translationX",black_pawn_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn, "translationY",black_pawn_y,goal_y);
                            black_pawn_x=goal_x;
                            black_pawn_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x>black_pawn_x&&goal_y<black_pawn_y)//向右上方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn, "translationX",black_pawn_x,goal_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn, "translationY",black_pawn_y,goal_y);
                            black_pawn_x=goal_x;
                            black_pawn_y=goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                        }
                        else if(goal_x==black_pawn_x&&goal_y<black_pawn_y)//向上方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn, "translationY",black_pawn_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x==black_pawn_x&&goal_y>black_pawn_y)//向下方移动
                        {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn, "translationY",black_pawn_y,goal_y);
                            translationY.setDuration(1000);//运行时间，ms为单位
                            translationY.start();
                        }
                        else if(goal_x<black_pawn_x&&goal_y==black_pawn_y)//向左方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn, "translationX",black_pawn_x,goal_x);black_pawn_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        else if(goal_x>black_pawn_x&&goal_y==black_pawn_y)//向右方移动
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn, "translationX",black_pawn_x,goal_x);black_pawn_x=goal_x;
                            translationX.setDuration(1000);//运行时间，ms为单位
                            translationX.start();
                        }
                        break;
                    }
                    default:break;
                }
            }
        }

        //wk.x=wk.x+120;
        //wk.y=wk.y+120;
        //wk.set_x(wk.x+120);
        //wk.set_y(wk.y+120);
        //ObjectAnimator miss = ObjectAnimator.ofFloat(wk.white_king, "alpha", 1, 0);
        //重载函数ofFloat(Object target目标名字, String xPropertyName或者String yPropertyName, Path path)



    }
}




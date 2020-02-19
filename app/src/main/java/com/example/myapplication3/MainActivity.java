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
import android.content.Context;


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
    int size = 30;

    public void bigger(View v)//虽然下面没有用到View v，但是不加就报错
    {
        // 放大按钮对应的 onclick 响应的方法
        TextView txv;
        txv = findViewById(R.id.txv);  // 根据ID找到对应的text对象
        txv.setTextSize(++size);       // 修改对象的字符大小-size
    }

    public void smaller(View v)//虽然下面没有用到View v，但是不加就报错
    {
        // 缩小按钮对应的 onclick 响应的方法
        TextView txv;
        txv = findViewById(R.id.txv);  // 根据ID找到对应的text对象
        txv.setTextSize(--size);       // 修改对象的字符大小-size
    }

    public void display(View v)//虽然下面没有用到View v，但是不加就报错
    {     // 显示按钮对应的 onclick 响应的方法
        EditText name = findViewById(R.id.name);  //还是根据ID找到对象，并进行接下来的操作
        TextView text2 = findViewById(R.id.txv);
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
                    public void onClick(DialogInterface dialog, int which) {
                        ide = which;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() //参数3：选中时的事件
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
            public void run() {
                try {
                    Thread.sleep(3000);//让他显示3秒后再消失
                } catch (InterruptedException e) {
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

    public  int dip2px(Context context, float dpValue)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    int square;
    //注意 左上角是（0，0），横轴是X轴，纵轴是Y轴,一个格子长度是square单位，但是布局中一个格子长度是40dp
    int goal_x, goal_y;//棋子移动的目标位置
    int black_king1_x = 4 * square, black_king1_y = 0;//黑棋国王1的当前位置
    int black_queen1_x = 3 * square, black_queen1_y = 0;//黑棋皇后1的当前位置
    int black_rook1_x = 0, black_rook1_y = 0;//黑棋战车1的当前位置
    int black_rook2_x = 7 * square, black_rook2_y = 0;//黑棋战车2的当前位置
    int black_bishop1_x = 2 * square, black_bishop1_y = 0;//黑棋主教1的当前位置
    int black_bishop2_x = 5 * square, black_bishop2_y = 0;//黑棋主教2的当前位置
    int black_knight1_x = 3 * square, black_knight1_y = 0;//黑棋骑士1的当前位置
    int black_knight2_x = 4 * square, black_knight2_y = 0;//黑棋骑士2的当前位置
    int black_pawn1_x = 0, black_pawn1_y = square;//黑棋禁卫军1的当前位置
    int black_pawn2_x = square, black_pawn2_y = square;//黑棋禁卫军2的当前位置
    int black_pawn3_x = 2 * square, black_pawn3_y = square;//黑棋禁卫军3的当前位置
    int black_pawn4_x = 3 * square, black_pawn4_y = square;//黑棋禁卫军4的当前位置
    int black_pawn5_x = 4 * square, black_pawn5_y = square;//黑棋禁卫军5的当前位置
    int black_pawn6_x = 5 * square, black_pawn6_y = square;//黑棋禁卫军6的当前位置
    int black_pawn7_x = 6 * square, black_pawn7_y = square;//黑棋禁卫军7的当前位置
    int black_pawn8_x = 7 * square, black_pawn8_y = square;//黑棋禁卫军8的当前位置

    int white_king1_x = 4 * square, white_king1_y = 7 * square;//白棋国王1的当前位置
    int white_queen1_x = 3 * square, white_queen1_y = 7 * square;//白棋皇后1的当前位置
    int white_rook1_x = 0, white_rook1_y = 7 * square;//白棋战车1的当前位置
    int white_rook2_x = 7 * square, white_rook2_y = 7 * square;//白棋战车2的当前位置
    int white_bishop1_x = square, white_bishop1_y = 7 * square;//白棋主教1的当前位置
    int white_bishop2_x = 6 * square, white_bishop2_y = 7 * square;//白棋主教2的当前位置
    int white_knight1_x = 2 * square, white_knight1_y = 7 * square;//白棋骑士1的当前位置
    int white_knight2_x = 5 * square, white_knight2_y = 7 * square;//白棋骑士2的当前位置
    int white_pawn1_x = 0, white_pawn1_y = 6 * square;//白棋禁卫军1的当前位置
    int white_pawn2_x = square, white_pawn2_y = 6 * square;//白棋禁卫军2的当前位置
    int white_pawn3_x = 2 * square, white_pawn3_y = 6 * square;//白棋禁卫军3的当前位置
    int white_pawn4_x = 3 * square, white_pawn4_y = 6 * square;//白棋禁卫军4的当前位置
    int white_pawn5_x = 4 * square, white_pawn5_y = 6 * square;//白棋禁卫军5的当前位置
    int white_pawn6_x = 5 * square, white_pawn6_y = 6 * square;//白棋禁卫军6的当前位置
    int white_pawn7_x = 6 * square, white_pawn7_y = 6 * square;//白棋禁卫军7的当前位置
    int white_pawn8_x = 7 * square, white_pawn8_y = 6 * square;//白棋禁卫军8的当前位置



    //注意动画的移动是以最开始的位置为原点，为了使得原点改为棋盘的最左上角，需要减去初始位置
    int initial_black_king1_x = 4 * square, initial_black_king1_y = 0;//黑棋国王1的初始位置
    int initial_black_queen1_x = 3 * square, initial_black_queen1_y = 0;//黑棋皇后1的初始位置
    int initial_black_rook1_x = 0, initial_black_rook1_y = 0;//黑棋战车1的初始位置
    int initial_black_rook2_x = 7 * square, initial_black_rook2_y = 0;//黑棋战车2的初始位置
    int initial_black_bishop1_x = 2 * square, initial_black_bishop1_y = 0;//黑棋主教1的初始位置
    int initial_black_bishop2_x = 5 * square, initial_black_bishop2_y = 0;//黑棋主教2的初始位置
    int initial_black_knight1_x = 3 * square, initial_black_knight1_y = 0;//黑棋骑士1的初始位置
    int initial_black_knight2_x = 4 * square, initial_black_knight2_y = 0;//黑棋骑士2的初始位置
    int initial_black_pawn1_x = 0, initial_black_pawn1_y = square;//黑棋禁卫军1的初始位置
    int initial_black_pawn2_x = square, initial_black_pawn2_y = square;//黑棋禁卫军2的初始位置
    int initial_black_pawn3_x = 2 * square, initial_black_pawn3_y = square;//黑棋禁卫军3的初始位置
    int initial_black_pawn4_x = 3 * square, initial_black_pawn4_y = square;//黑棋禁卫军4的初始位置
    int initial_black_pawn5_x = 4 * square, initial_black_pawn5_y = square;//黑棋禁卫军5的初始位置
    int initial_black_pawn6_x = 5 * square, initial_black_pawn6_y = square;//黑棋禁卫军6的初始位置
    int initial_black_pawn7_x = 6 * square, initial_black_pawn7_y = square;//黑棋禁卫军7的初始位置
    int initial_black_pawn8_x = 7 * square, initial_black_pawn8_y = square;//黑棋禁卫军8的初始位置

    int initial_white_king1_x = 4 * square, initial_white_king1_y = 7 * square;//白棋国王1的初始位置
    int initial_white_queen1_x = 3 * square, initial_white_queen1_y = 7 * square;//白棋皇后1的初始位置
    int initial_white_rook1_x = 0, initial_white_rook1_y = 7 * square;//白棋战车1的初始位置
    int initial_white_rook2_x = 7 * square, initial_white_rook2_y = 7 * square;//白棋战车2的初始位置
    int initial_white_bishop1_x = square, initial_white_bishop1_y = 7 * square;//白棋主教1的初始位置
    int initial_white_bishop2_x = 6 * square, initial_white_bishop2_y = 7 * square;//白棋主教2的初始位置
    int initial_white_knight1_x = 2 * square, initial_white_knight1_y = 7 * square;//白棋骑士1的初始位置
    int initial_white_knight2_x = 5 * square, initial_white_knight2_y = 7 * square;//白棋骑士2的初始位置
    int initial_white_pawn1_x = 0, initial_white_pawn1_y = 6 * square;//白棋禁卫军1的初始位置
    int initial_white_pawn2_x = square, initial_white_pawn2_y = 6 * square;//白棋禁卫军2的初始位置
    int initial_white_pawn3_x = 2 * square, initial_white_pawn3_y = 6 * square;//白棋禁卫军3的初始位置
    int initial_white_pawn4_x = 3 * square, initial_white_pawn4_y = 6 * square;//白棋禁卫军4的初始位置
    int initial_white_pawn5_x = 4 * square, initial_white_pawn5_y = 6 * square;//白棋禁卫军5的初始位置
    int initial_white_pawn6_x = 5 * square, initial_white_pawn6_y = 6 * square;//白棋禁卫军6的初始位置
    int initial_white_pawn7_x = 6 * square, initial_white_pawn7_y = 6 * square;//白棋禁卫军7的初始位置
    int initial_white_pawn8_x = 7 * square, initial_white_pawn8_y = 6 * square;//白棋禁卫军8的初始位置

    //为了区分同种棋子中的不同，标号从左到右为1，2...,小写为白方棋子，大写为黑方棋子
    //其中k1、q1、K1、Q1中的1没有实际意义，只是为了和其他棋子的储存方式对齐
    String[][] chessboard = {
            {"R1", "N1", "B1", "Q1", "K1", "B2", "N2", "R2"},
            {"P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8"},
            {"00", "00", "00", "00", "00", "00", "00", "00"},
            {"00", "00", "00", "00", "00", "00", "00", "00"},
            {"00", "00", "00", "00", "00", "00", "00", "00"},
            {"00", "00", "00", "00", "00", "00", "00", "00"},
            {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"},
            {"r1", "n1", "b1", "q1", "k1", "b2", "n2", "r2"}
    };

    public ImageView disappear(int x,int y)//通过字符串数组棋盘中的位置，找到对应的图片
    {
        switch(chessboard[x][y])
        {
            case "r1":return findViewById(R.id.white_rook1);
            case "n1":return findViewById(R.id.white_knight1);
            case "b1":return findViewById(R.id.white_bishop1);
            case "q1":return findViewById(R.id.white_queen1);
            case "k1":return findViewById(R.id.white_king1);
            case "b2":return findViewById(R.id.white_bishop2);
            case "n2":return findViewById(R.id.white_knight2);
            case "r2":return findViewById(R.id.white_rook2);
            case "p1":return findViewById(R.id.white_pawn1);
            case "p2":return findViewById(R.id.white_pawn2);
            case "p3":return findViewById(R.id.white_pawn3);
            case "p4":return findViewById(R.id.white_pawn4);
            case "p5":return findViewById(R.id.white_pawn5);
            case "p6":return findViewById(R.id.white_pawn6);
            case "p7":return findViewById(R.id.white_pawn7);
            case "p8":return findViewById(R.id.white_pawn8);
            //上面是白方，下面是黑方
            case "R1":return findViewById(R.id.black_rook1);
            case "N1":return findViewById(R.id.black_knight1);
            case "B1":return findViewById(R.id.black_bishop1);
            case "Q1":return findViewById(R.id.black_queen1);
            case "K1":return findViewById(R.id.black_king1);
            case "B2":return findViewById(R.id.black_bishop2);
            case "N2":return findViewById(R.id.black_knight2);
            case "R2":return findViewById(R.id.black_rook2);
            case "P1":return findViewById(R.id.black_pawn1);
            case "P2":return findViewById(R.id.black_pawn2);
            case "P3":return findViewById(R.id.black_pawn3);
            case "P4":return findViewById(R.id.black_pawn4);
            case "P5":return findViewById(R.id.black_pawn5);
            case "P6":return findViewById(R.id.black_pawn6);
            case "P7":return findViewById(R.id.black_pawn7);
            case "P8":return findViewById(R.id.black_pawn8);

        }
        return null;//不会出现这种情况，因为该函数的前提是有棋子被吃
    }
    public void translate(View v)//虽然下面没有用到View v，但是不加就报错
    {
        int square =  dip2px(this,40f);
        String input = ((EditText) findViewById(R.id.editText)).getText().toString();
        char[] move = input.toCharArray(); //将字符串变量转换为字符数组
        //最先判断王车易位
        if(input.equals("0w00"))//白方短易位，不必考虑吃，且王和车都在初始位置
        {
            ImageView white_king1 = findViewById(R.id.white_king1);//图形
            ImageView white_rook2 = findViewById(R.id.white_rook2);//图形
            ObjectAnimator translation_king = ObjectAnimator.ofFloat(white_king1, "translationX",0,2 * square);
            ObjectAnimator translation_rook = ObjectAnimator.ofFloat(white_rook2, "translationX",0,-2 * square);
            white_king1_x = 6 * square;
            white_rook2_x = 5 * square;
            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
            animatorset.play(translation_king).before(translation_rook);//方法还有with和after
            animatorset.setDuration(1000);//运行时间，ms为单位
            animatorset.start();
        }
        else if(input.equals("0b00"))//黑方短易位，不必考虑吃，且王和车都在初始位置
        {
            ImageView black_king1 = findViewById(R.id.black_king1);//图形
            ImageView black_rook2 = findViewById(R.id.black_rook2);//图形
            ObjectAnimator translation_king = ObjectAnimator.ofFloat(black_king1, "translationX",0,2 * square);
            ObjectAnimator translation_rook = ObjectAnimator.ofFloat(black_rook2, "translationX",0,-2 * square);
            black_king1_x = 6 * square;
            black_rook2_x = 5 * square;
            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
            animatorset.play(translation_king).before(translation_rook);//方法还有with和after
            animatorset.setDuration(1000);//运行时间，ms为单位
            animatorset.start();
        }
        else if(input.equals("w000"))//白方长易位，不必考虑吃，且王和车都在初始位置
        {
            ImageView white_king1 = findViewById(R.id.white_king1);//图形
            ImageView white_rook1 = findViewById(R.id.white_rook1);//图形
            ObjectAnimator translation_king = ObjectAnimator.ofFloat(white_king1, "translationX",0,-2 * square);
            ObjectAnimator translation_rook = ObjectAnimator.ofFloat(white_rook1, "translationX",0,3 * square);
            white_king1_x = 2 * square;
            white_rook1_x = 3 * square;
            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
            animatorset.play(translation_king).before(translation_rook);//方法还有with和after
            animatorset.setDuration(1000);//运行时间，ms为单位
            animatorset.start();
        }
        else if(input.equals("b000"))//黑方长易位，不必考虑吃，且王和车都在初始位置
        {
            ImageView black_king1 = findViewById(R.id.black_king1);//图形
            ImageView black_rook1 = findViewById(R.id.black_rook1);//图形
            ObjectAnimator translation_king = ObjectAnimator.ofFloat(black_king1, "translationX",0,-2 * square);
            ObjectAnimator translation_rook = ObjectAnimator.ofFloat(black_rook1, "translationX",0,3 * square);
            black_king1_x = 2 * square;
            black_rook1_x = 3 * square;
            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
            animatorset.play(translation_king).before(translation_rook);//方法还有with和after
            animatorset.setDuration(1000);//运行时间，ms为单位
            animatorset.start();
        }
        else if(move[2]=='>')//这种输入下，会认为是晋升
        {
            ImageView promoted_pawn = disappear('8' - move[1],move[0] - 'a');
            switch (move[3])
            {//禁卫军不能变成国王和禁卫军，下面case没有k、K、p、P
                case 'r':
                {
                    promoted_pawn.setImageDrawable(getResources().getDrawable(R.drawable.white_rook));
                    break;
                }
                case 'n':
                {
                    promoted_pawn.setImageDrawable(getResources().getDrawable(R.drawable.white_knight));
                    break;
                }
                case 'b':
                {
                    promoted_pawn.setImageDrawable(getResources().getDrawable(R.drawable.white_bishop));
                    break;
                }
                case 'q':
                {
                    promoted_pawn.setImageDrawable(getResources().getDrawable(R.drawable.white_queen));
                    break;
                }
                //上面是白方，下面是黑方
                case 'R':
                {
                    promoted_pawn.setImageDrawable(getResources().getDrawable(R.drawable.black_rook));
                    break;
                }
                case 'N':
                {
                    promoted_pawn.setImageDrawable(getResources().getDrawable(R.drawable.black_knight));
                    break;
                }
                case 'B':
                {
                    promoted_pawn.setImageDrawable(getResources().getDrawable(R.drawable.black_bishop));
                    break;
                }
                case 'Q':
                {
                    promoted_pawn.setImageDrawable(getResources().getDrawable(R.drawable.black_queen));
                    break;
                }
                default:break;
            }
        }
        else
        {
            int x, y;
            x = '8' - move[1];
            y = move[0] - 'a';//先通过前两个字母确定要走的棋子在字符串数组中的位置
            char[] chessman = (chessboard[x][y]).toCharArray();//找到棋子
            if (chessman[0] >= 'a' && chessman[0] <= 'z')//第一个字母是小写，说明是白方的棋子
            {
                switch (chessman[0])
                {
                    case 'k'://表示选择的是国王
                    {
                        ImageView white_king1 = findViewById(R.id.white_king1);//图形
                        goal_x =  square * (move[2] - 'a');
                        goal_y =  square * ('8' - move[3]);
                        chessboard['8' - move[1]][move[0] - 'a'] = "00";

                        if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_king1, "translationX",white_king1_x-initial_white_king1_x, goal_x-initial_white_king1_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_king1, "translationY",white_king1_y-initial_white_king1_y, goal_y-initial_white_king1_y);
                            ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                            white_king1_x = goal_x;
                            white_king1_y = goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                            chessboard['8' - move[3]][move[2] - 'a'] = "k1";
                        }
                        else//目标位置没有棋子，正常进入
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_king1, "translationX",white_king1_x-initial_white_king1_x, goal_x-initial_white_king1_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_king1, "translationY",white_king1_y-initial_white_king1_y, goal_y-initial_white_king1_y);
                            white_king1_x = goal_x;
                            white_king1_y = goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                            chessboard['8' - move[3]][move[2] - 'a'] = "k1";
                        }
                        break;
                    }

                    case 'q'://表示选择的是皇后
                    {
                        ImageView white_queen1 = findViewById(R.id.white_queen1);//图形
                        goal_x = square * (move[2] - 'a');
                        goal_y = square * ('8' - move[3]);
                        chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！

                        if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_queen1, "translationX",white_queen1_x-initial_white_queen1_x, goal_x-initial_white_queen1_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_queen1, "translationY",white_queen1_y-initial_white_queen1_y, goal_y-initial_white_queen1_y);
                            ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                            white_queen1_x = goal_x;
                            white_queen1_y = goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                            chessboard['8' - move[3]][move[2] - 'a'] = "q1";
                        }
                        else//目标位置没有棋子，正常进入
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(white_queen1, "translationX",white_queen1_x-initial_white_queen1_x, goal_x-initial_white_queen1_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(white_queen1, "translationY",white_queen1_y-initial_white_queen1_y, goal_y-initial_white_queen1_y);
                            white_queen1_x = goal_x;
                            white_queen1_y = goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                            chessboard['8' - move[3]][move[2] - 'a'] = "q1";
                        }
                        break;
                    }
                    
                    case 'r'://表示选择的是战车
                    {
                        if (chessman[1]=='1')
                        {
                            ImageView white_rook1 = findViewById(R.id.white_rook1);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！

                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_rook1, "translationX",white_rook1_x-initial_white_rook1_x, goal_x-initial_white_rook1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_rook1, "translationY",white_rook1_y-initial_white_rook1_y, goal_y-initial_white_rook1_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_rook1_x = goal_x;
                                white_rook1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "r1";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_rook1, "translationX",white_rook1_x-initial_white_rook1_x, goal_x-initial_white_rook1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_rook1, "translationY",white_rook1_y-initial_white_rook1_y, goal_y-initial_white_rook1_y);
                                white_rook1_x = goal_x;
                                white_rook1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "r1";
                            }
                        }
                        else if (chessman[1]=='2')
                        {
                            ImageView white_rook2 = findViewById(R.id.white_rook2);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_rook2, "translationX",white_rook2_x-initial_white_rook2_x, goal_x-initial_white_rook2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_rook2, "translationY",white_rook2_y-initial_white_rook2_y, goal_y-initial_white_rook2_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_rook2_x = goal_x;
                                white_rook2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "r2";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_rook2, "translationX",white_rook2_x-initial_white_rook2_x, goal_x-initial_white_rook2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_rook2, "translationY",white_rook2_y-initial_white_rook2_y, goal_y-initial_white_rook2_y);
                                white_rook2_x = goal_x;
                                white_rook2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "r2";
                            }
                        }
                        break;
                    }

                    case 'n'://表示选择的是骑士
                    {
                        if (chessman[1]=='1')
                        {
                            ImageView white_knight1 = findViewById(R.id.white_knight1);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！

                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_knight1, "translationX",white_knight1_x-initial_white_knight1_x, goal_x-initial_white_knight1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_knight1, "translationY",white_knight1_y-initial_white_knight1_y, goal_y-initial_white_knight1_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_knight1_x = goal_x;
                                white_knight1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "n1";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_knight1, "translationX",white_knight1_x-initial_white_knight1_x, goal_x-initial_white_knight1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_knight1, "translationY",white_knight1_y-initial_white_knight1_y, goal_y-initial_white_knight1_y);
                                white_knight1_x = goal_x;
                                white_knight1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "n1";
                            }
                        }
                        else if (chessman[1]=='2')
                        {
                            ImageView white_knight2 = findViewById(R.id.white_knight2);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_knight2, "translationX",white_knight2_x-initial_white_knight2_x, goal_x-initial_white_knight2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_knight2, "translationY",white_knight2_y-initial_white_knight2_y, goal_y-initial_white_knight2_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_knight2_x = goal_x;
                                white_knight2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "n2";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_knight2, "translationX",white_knight2_x-initial_white_knight2_x, goal_x-initial_white_knight2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_knight2, "translationY",white_knight2_y-initial_white_knight2_y, goal_y-initial_white_knight2_y);
                                white_knight2_x = goal_x;
                                white_knight2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "n2";
                            }
                        }
                        break;
                    }

                    case 'b'://表示选择的是主教
                    {
                        if (chessman[1]=='1')
                        {
                            ImageView white_bishop1 = findViewById(R.id.white_bishop1);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！

                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_bishop1, "translationX",white_bishop1_x-initial_white_bishop1_x, goal_x-initial_white_bishop1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_bishop1, "translationY",white_bishop1_y-initial_white_bishop1_y, goal_y-initial_white_bishop1_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_bishop1_x = goal_x;
                                white_bishop1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "b1";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_bishop1, "translationX",white_bishop1_x-initial_white_bishop1_x, goal_x-initial_white_bishop1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_bishop1, "translationY",white_bishop1_y-initial_white_bishop1_y, goal_y-initial_white_bishop1_y);
                                white_bishop1_x = goal_x;
                                white_bishop1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "b1";
                            }
                        }
                        else if (chessman[1]=='2')
                        {
                            ImageView white_bishop2 = findViewById(R.id.white_bishop2);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_bishop2, "translationX",white_bishop2_x-initial_white_bishop2_x, goal_x-initial_white_bishop2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_bishop2, "translationY",white_bishop2_y-initial_white_bishop2_y, goal_y-initial_white_bishop2_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_bishop2_x = goal_x;
                                white_bishop2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "b2";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_bishop2, "translationX",white_bishop2_x-initial_white_bishop2_x, goal_x-initial_white_bishop2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_bishop2, "translationY",white_bishop2_y-initial_white_bishop2_y, goal_y-initial_white_bishop2_y);
                                white_bishop2_x = goal_x;
                                white_bishop2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "b2";
                            }
                        }
                        break;
                    }

                    case 'p'://表示选择的是禁卫军
                    {
                        if (chessman[1]=='1')
                        {
                            ImageView white_pawn1 = findViewById(R.id.white_pawn1);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！

                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn1, "translationX",white_pawn1_x-initial_white_pawn1_x, goal_x-initial_white_pawn1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn1, "translationY",white_pawn1_y-initial_white_pawn1_y, goal_y-initial_white_pawn1_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_pawn1_x = goal_x;
                                white_pawn1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p1";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn1, "translationX",white_pawn1_x-initial_white_pawn1_x, goal_x-initial_white_pawn1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn1, "translationY",white_pawn1_y-initial_white_pawn1_y, goal_y-initial_white_pawn1_y);
                                white_pawn1_x = goal_x;
                                white_pawn1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p1";
                            }
                        }
                        else if (chessman[1]=='2')
                        {
                            ImageView white_pawn2 = findViewById(R.id.white_pawn2);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn2, "translationX",white_pawn2_x-initial_white_pawn2_x, goal_x-initial_white_pawn2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn2, "translationY",white_pawn2_y-initial_white_pawn2_y, goal_y-initial_white_pawn2_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_pawn2_x = goal_x;
                                white_pawn2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p2";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn2, "translationX",white_pawn2_x-initial_white_pawn2_x, goal_x-initial_white_pawn2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn2, "translationY",white_pawn2_y-initial_white_pawn2_y, goal_y-initial_white_pawn2_y);
                                white_pawn2_x = goal_x;
                                white_pawn2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p2";
                            }
                        }
                        else if (chessman[1]=='3')
                        {
                            ImageView white_pawn3 = findViewById(R.id.white_pawn3);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn3, "translationX",white_pawn3_x-initial_white_pawn3_x, goal_x-initial_white_pawn3_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn3, "translationY",white_pawn3_y-initial_white_pawn3_y, goal_y-initial_white_pawn3_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_pawn3_x = goal_x;
                                white_pawn3_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p3";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn3, "translationX",white_pawn3_x-initial_white_pawn3_x, goal_x-initial_white_pawn3_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn3, "translationY",white_pawn3_y-initial_white_pawn3_y, goal_y-initial_white_pawn3_y);
                                white_pawn3_x = goal_x;
                                white_pawn3_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p3";
                            }
                        }
                        else if (chessman[1]=='4')
                        {
                            ImageView white_pawn4 = findViewById(R.id.white_pawn4);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn4, "translationX",white_pawn4_x-initial_white_pawn4_x, goal_x-initial_white_pawn4_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn4, "translationY",white_pawn4_y-initial_white_pawn4_y, goal_y-initial_white_pawn4_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_pawn4_x = goal_x;
                                white_pawn4_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p4";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn4, "translationX",white_pawn4_x-initial_white_pawn4_x, goal_x-initial_white_pawn4_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn4, "translationY",white_pawn4_y-initial_white_pawn4_y, goal_y-initial_white_pawn4_y);
                                white_pawn4_x = goal_x;
                                white_pawn4_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p4";
                            }
                        }
                        else if (chessman[1]=='5')
                        {
                            ImageView white_pawn5 = findViewById(R.id.white_pawn5);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn5, "translationX",white_pawn5_x-initial_white_pawn5_x, goal_x-initial_white_pawn5_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn5, "translationY",white_pawn5_y-initial_white_pawn5_y, goal_y-initial_white_pawn5_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_pawn5_x = goal_x;
                                white_pawn5_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p5";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn5, "translationX",white_pawn5_x-initial_white_pawn5_x, goal_x-initial_white_pawn5_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn5, "translationY",white_pawn5_y-initial_white_pawn5_y, goal_y-initial_white_pawn5_y);
                                white_pawn5_x = goal_x;
                                white_pawn5_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p5";
                            }
                        }
                        else if (chessman[1]=='6')
                        {
                            ImageView white_pawn6 = findViewById(R.id.white_pawn6);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn6, "translationX",white_pawn6_x-initial_white_pawn6_x, goal_x-initial_white_pawn6_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn6, "translationY",white_pawn6_y-initial_white_pawn6_y, goal_y-initial_white_pawn6_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_pawn6_x = goal_x;
                                white_pawn6_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p6";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn6, "translationX",white_pawn6_x-initial_white_pawn6_x, goal_x-initial_white_pawn6_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn6, "translationY",white_pawn6_y-initial_white_pawn6_y, goal_y-initial_white_pawn6_y);
                                white_pawn6_x = goal_x;
                                white_pawn6_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p6";
                            }
                        }
                        else if (chessman[1]=='7')
                        {
                            ImageView white_pawn7 = findViewById(R.id.white_pawn7);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn7, "translationX",white_pawn7_x-initial_white_pawn7_x, goal_x-initial_white_pawn7_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn7, "translationY",white_pawn7_y-initial_white_pawn7_y, goal_y-initial_white_pawn7_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_pawn7_x = goal_x;
                                white_pawn7_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p7";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn7, "translationX",white_pawn7_x-initial_white_pawn7_x, goal_x-initial_white_pawn7_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn7, "translationY",white_pawn7_y-initial_white_pawn7_y, goal_y-initial_white_pawn7_y);
                                white_pawn7_x = goal_x;
                                white_pawn7_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p7";
                            }
                        }
                        else if (chessman[1]=='8')
                        {
                            ImageView white_pawn8 = findViewById(R.id.white_pawn8);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn8, "translationX",white_pawn8_x-initial_white_pawn8_x, goal_x-initial_white_pawn8_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn8, "translationY",white_pawn8_y-initial_white_pawn8_y, goal_y-initial_white_pawn8_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                white_pawn8_x = goal_x;
                                white_pawn8_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p8";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(white_pawn8, "translationX",white_pawn8_x-initial_white_pawn8_x, goal_x-initial_white_pawn8_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(white_pawn8, "translationY",white_pawn8_y-initial_white_pawn8_y, goal_y-initial_white_pawn8_y);
                                white_pawn8_x = goal_x;
                                white_pawn8_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "p8";
                            }
                        }
                        break;
                    }
                    default: break;
                }
            }
            else if (chessman[0] >= 'A' && chessman[0] <= 'Z')//第一个字母是大写，说明是黑方的棋子
            {
                switch (chessman[0])
                {
                    case 'K'://表示选择的是国王
                    {
                        ImageView black_king1 = findViewById(R.id.black_king1);//图形
                        goal_x = square * (move[2] - 'a');
                        goal_y = square * ('8' - move[3]);
                        chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！

                        if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_king1, "translationX",black_king1_x-initial_black_king1_x, goal_x-initial_black_king1_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_king1, "translationY",black_king1_y-initial_black_king1_y, goal_y-initial_black_king1_y);
                            ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                            black_king1_x = goal_x;
                            black_king1_y = goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                            chessboard['8' - move[3]][move[2] - 'a'] = "K1";
                        }
                        else//目标位置没有棋子，正常进入
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_king1, "translationX",black_king1_x-initial_black_king1_x, goal_x-initial_black_king1_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_king1, "translationY",black_king1_y-initial_black_king1_y, goal_y-initial_black_king1_y);
                            black_king1_x = goal_x;
                            black_king1_y = goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                            chessboard['8' - move[3]][move[2] - 'a'] = "K1";
                        }
                        break;
                    }

                    case 'Q'://表示选择的是皇后
                    {
                        ImageView black_queen1 = findViewById(R.id.black_queen1);//图形
                        goal_x = square * (move[2] - 'a');
                        goal_y = square * ('8' - move[3]);
                        chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！

                        if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_queen1, "translationX",black_queen1_x-initial_black_queen1_x, goal_x-initial_black_queen1_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_queen1, "translationY",black_queen1_y-initial_black_queen1_y, goal_y-initial_black_queen1_y);
                            ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                            black_queen1_x = goal_x;
                            black_queen1_y = goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                            chessboard['8' - move[3]][move[2] - 'a'] = "Q1";
                        }
                        else//目标位置没有棋子，正常进入
                        {
                            ObjectAnimator translationX = ObjectAnimator.ofFloat(black_queen1, "translationX",black_queen1_x-initial_black_queen1_x, goal_x-initial_black_queen1_x);
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(black_queen1, "translationY",black_queen1_y-initial_black_queen1_y, goal_y-initial_black_queen1_y);
                            black_queen1_x = goal_x;
                            black_queen1_y = goal_y;
                            AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                            animatorset.play(translationX).with(translationY);//方法还有before和after
                            animatorset.setDuration(1000);//运行时间，ms为单位
                            animatorset.start();
                            chessboard['8' - move[3]][move[2] - 'a'] = "Q1";
                        }
                        break;
                    }

                    case 'R'://表示选择的是战车
                    {
                        if (chessman[1]=='1')
                        {
                            ImageView black_rook1 = findViewById(R.id.black_rook1);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！

                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_rook1, "translationX",black_rook1_x-initial_black_rook1_x, goal_x-initial_black_rook1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_rook1, "translationY",black_rook1_y-initial_black_rook1_y, goal_y-initial_black_rook1_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_rook1_x = goal_x;
                                black_rook1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "R1";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_rook1, "translationX",black_rook1_x-initial_black_rook1_x, goal_x-initial_black_rook1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_rook1, "translationY",black_rook1_y-initial_black_rook1_y, goal_y-initial_black_rook1_y);
                                black_rook1_x = goal_x;
                                black_rook1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "R1";
                            }
                        }
                        else if (chessman[1]=='2')
                        {
                            ImageView black_rook2 = findViewById(R.id.black_rook2);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_rook2, "translationX",black_rook2_x-initial_black_rook2_x, goal_x-initial_black_rook2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_rook2, "translationY",black_rook2_y-initial_black_rook2_y, goal_y-initial_black_rook2_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_rook2_x = goal_x;
                                black_rook2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "R2";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_rook2, "translationX",black_rook2_x-initial_black_rook2_x, goal_x-initial_black_rook2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_rook2, "translationY",black_rook2_y-initial_black_rook2_y, goal_y-initial_black_rook2_y);
                                black_rook2_x = goal_x;
                                black_rook2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "R2";
                            }
                        }
                        break;
                    }

                    case 'N'://表示选择的是骑士
                    {
                        if (chessman[1]=='1')
                        {
                            ImageView black_knight1 = findViewById(R.id.black_knight1);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！

                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_knight1, "translationX",black_knight1_x-initial_black_knight1_x, goal_x-initial_black_knight1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_knight1, "translationY",black_knight1_y-initial_black_knight1_y, goal_y-initial_black_knight1_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_knight1_x = goal_x;
                                black_knight1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "N1";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_knight1, "translationX",black_knight1_x-initial_black_knight1_x, goal_x-initial_black_knight1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_knight1, "translationY",black_knight1_y-initial_black_knight1_y, goal_y-initial_black_knight1_y);
                                black_knight1_x = goal_x;
                                black_knight1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "N1";
                            }
                        }
                        else if (chessman[1]=='2')
                        {
                            ImageView black_knight2 = findViewById(R.id.black_knight2);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_knight2, "translationX",black_knight2_x-initial_black_knight2_x, goal_x-initial_black_knight2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_knight2, "translationY",black_knight2_y-initial_black_knight2_y, goal_y-initial_black_knight2_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_knight2_x = goal_x;
                                black_knight2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "N2";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_knight2, "translationX",black_knight2_x-initial_black_knight2_x, goal_x-initial_black_knight2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_knight2, "translationY",black_knight2_y-initial_black_knight2_y, goal_y-initial_black_knight2_y);
                                black_knight2_x = goal_x;
                                black_knight2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "N2";
                            }
                        }
                        break;
                    }

                    case 'B'://表示选择的是主教
                    {
                        if (chessman[1]=='1')
                        {
                            ImageView black_bishop1 = findViewById(R.id.black_bishop1);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！

                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_bishop1, "translationX",black_bishop1_x-initial_black_bishop1_x, goal_x-initial_black_bishop1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_bishop1, "translationY",black_bishop1_y-initial_black_bishop1_y, goal_y-initial_black_bishop1_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_bishop1_x = goal_x;
                                black_bishop1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "B1";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_bishop1, "translationX",black_bishop1_x-initial_black_bishop1_x, goal_x-initial_black_bishop1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_bishop1, "translationY",black_bishop1_y-initial_black_bishop1_y, goal_y-initial_black_bishop1_y);
                                black_bishop1_x = goal_x;
                                black_bishop1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "B1";
                            }
                        }
                        else if (chessman[1]=='2')
                        {
                            ImageView black_bishop2 = findViewById(R.id.black_bishop2);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_bishop2, "translationX",black_bishop2_x-initial_black_bishop2_x, goal_x-initial_black_bishop2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_bishop2, "translationY",black_bishop2_y-initial_black_bishop2_y, goal_y-initial_black_bishop2_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_bishop2_x = goal_x;
                                black_bishop2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "B2";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_bishop2, "translationX",black_bishop2_x-initial_black_bishop2_x, goal_x-initial_black_bishop2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_bishop2, "translationY",black_bishop2_y-initial_black_bishop2_y, goal_y-initial_black_bishop2_y);
                                black_bishop2_x = goal_x;
                                black_bishop2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "B2";
                            }
                        }
                        break;
                    }

                    case 'P'://表示选择的是禁卫军
                    {
                        if (chessman[1]=='1')
                        {
                            ImageView black_pawn1 = findViewById(R.id.black_pawn1);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！

                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn1, "translationX",black_pawn1_x-initial_black_pawn1_x, goal_x-initial_black_pawn1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn1, "translationY",black_pawn1_y-initial_black_pawn1_y, goal_y-initial_black_pawn1_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_pawn1_x = goal_x;
                                black_pawn1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P1";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn1, "translationX",black_pawn1_x-initial_black_pawn1_x, goal_x-initial_black_pawn1_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn1, "translationY",black_pawn1_y-initial_black_pawn1_y, goal_y-initial_black_pawn1_y);
                                black_pawn1_x = goal_x;
                                black_pawn1_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P1";
                            }
                        }
                        else if (chessman[1]=='2')
                        {
                            ImageView black_pawn2 = findViewById(R.id.black_pawn2);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn2, "translationX",black_pawn2_x-initial_black_pawn2_x, goal_x-initial_black_pawn2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn2, "translationY",black_pawn2_y-initial_black_pawn2_y, goal_y-initial_black_pawn2_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_pawn2_x = goal_x;
                                black_pawn2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P2";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn2, "translationX",black_pawn2_x-initial_black_pawn2_x, goal_x-initial_black_pawn2_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn2, "translationY",black_pawn2_y-initial_black_pawn2_y, goal_y-initial_black_pawn2_y);
                                black_pawn2_x = goal_x;
                                black_pawn2_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P2";
                            }
                        }
                        else if (chessman[1]=='3')
                        {
                            ImageView black_pawn3 = findViewById(R.id.black_pawn3);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn3, "translationX",black_pawn3_x-initial_black_pawn3_x, goal_x-initial_black_pawn3_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn3, "translationY",black_pawn3_y-initial_black_pawn3_y, goal_y-initial_black_pawn3_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_pawn3_x = goal_x;
                                black_pawn3_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P3";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn3, "translationX",black_pawn3_x-initial_black_pawn3_x, goal_x-initial_black_pawn3_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn3, "translationY",black_pawn3_y-initial_black_pawn3_y, goal_y-initial_black_pawn3_y);
                                black_pawn3_x = goal_x;
                                black_pawn3_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P3";
                            }
                        }
                        else if (chessman[1]=='4')
                        {
                            ImageView black_pawn4 = findViewById(R.id.black_pawn4);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn4, "translationX",black_pawn4_x-initial_black_pawn4_x, goal_x-initial_black_pawn4_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn4, "translationY",black_pawn4_y-initial_black_pawn4_y, goal_y-initial_black_pawn4_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_pawn4_x = goal_x;
                                black_pawn4_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P4";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn4, "translationX",black_pawn4_x-initial_black_pawn4_x, goal_x-initial_black_pawn4_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn4, "translationY",black_pawn4_y-initial_black_pawn4_y, goal_y-initial_black_pawn4_y);
                                black_pawn4_x = goal_x;
                                black_pawn4_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P4";
                            }
                        }
                        else if (chessman[1]=='5')
                        {
                            ImageView black_pawn5 = findViewById(R.id.black_pawn5);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn5, "translationX",black_pawn5_x-initial_black_pawn5_x, goal_x-initial_black_pawn5_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn5, "translationY",black_pawn5_y-initial_black_pawn5_y, goal_y-initial_black_pawn5_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_pawn5_x = goal_x;
                                black_pawn5_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P5";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn5, "translationX",black_pawn5_x-initial_black_pawn5_x, goal_x-initial_black_pawn5_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn5, "translationY",black_pawn5_y-initial_black_pawn5_y, goal_y-initial_black_pawn5_y);
                                black_pawn5_x = goal_x;
                                black_pawn5_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P5";
                            }
                        }
                        else if (chessman[1]=='6')
                        {
                            ImageView black_pawn6 = findViewById(R.id.black_pawn6);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn6, "translationX",black_pawn6_x-initial_black_pawn6_x, goal_x-initial_black_pawn6_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn6, "translationY",black_pawn6_y-initial_black_pawn6_y, goal_y-initial_black_pawn6_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_pawn6_x = goal_x;
                                black_pawn6_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P6";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn6, "translationX",black_pawn6_x-initial_black_pawn6_x, goal_x-initial_black_pawn6_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn6, "translationY",black_pawn6_y-initial_black_pawn6_y, goal_y-initial_black_pawn6_y);
                                black_pawn6_x = goal_x;
                                black_pawn6_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P6";
                            }
                        }
                        else if (chessman[1]=='7')
                        {
                            ImageView black_pawn7 = findViewById(R.id.black_pawn7);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn7, "translationX",black_pawn7_x-initial_black_pawn7_x, goal_x-initial_black_pawn7_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn7, "translationY",black_pawn7_y-initial_black_pawn7_y, goal_y-initial_black_pawn7_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_pawn7_x = goal_x;
                                black_pawn7_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P7";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn7, "translationX",black_pawn7_x-initial_black_pawn7_x, goal_x-initial_black_pawn7_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn7, "translationY",black_pawn7_y-initial_black_pawn7_y, goal_y-initial_black_pawn7_y);
                                black_pawn7_x = goal_x;
                                black_pawn7_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P7";
                            }
                        }
                        else if (chessman[1]=='8')
                        {
                            ImageView black_pawn8 = findViewById(R.id.black_pawn8);//图形
                            goal_x = square * (move[2] - 'a');
                            goal_y = square * ('8' - move[3]);
                            chessboard['8' - move[1]][move[0] - 'a'] = "00";//注意！！！
                            if(!(chessboard['8' - move[3]][move[2] - 'a'].equals("00")))//此时目标位置有棋子，该棋子被吃
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn8, "translationX",black_pawn8_x-initial_black_pawn8_x, goal_x-initial_black_pawn8_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn8, "translationY",black_pawn8_y-initial_black_pawn8_y, goal_y-initial_black_pawn8_y);
                                ObjectAnimator miss = ObjectAnimator.ofFloat(disappear('8' - move[3],move[2] - 'a'), "alpha", 1,0);
                                black_pawn8_x = goal_x;
                                black_pawn8_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY).before(miss);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P8";
                            }
                            else//目标位置没有棋子，正常进入
                            {
                                ObjectAnimator translationX = ObjectAnimator.ofFloat(black_pawn8, "translationX",black_pawn8_x-initial_black_pawn8_x, goal_x-initial_black_pawn8_x);
                                ObjectAnimator translationY = ObjectAnimator.ofFloat(black_pawn8, "translationY",black_pawn8_y-initial_black_pawn8_y, goal_y-initial_black_pawn8_y);
                                black_pawn8_x = goal_x;
                                black_pawn8_y = goal_y;
                                AnimatorSet animatorset = new AnimatorSet();//新建一个组合动画
                                animatorset.play(translationX).with(translationY);//方法还有before和after
                                animatorset.setDuration(1000);//运行时间，ms为单位
                                animatorset.start();
                                chessboard['8' - move[3]][move[2] - 'a'] = "P8";
                            }
                        }
                        break;
                    }
                    default: break;
                }
            }
        }
    }
}
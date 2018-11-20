package com.example.hp.menudemo;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button button;
    private  Button butSingle;
    private  Button butCons;
    private  Button butShow;
    private EditText editName,editpass;
    private  Button butHorzonial;
    private  Button butdata;
    private  Button butTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "onCreate: " + "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.button);
        butSingle=findViewById(R.id.but_single);
        butCons=findViewById(R.id.but_cons);
        butShow=findViewById(R.id.but_show);
        butHorzonial=findViewById(R.id.but_horzonial);
        butdata=findViewById(R.id.but_data);
        butTime=findViewById(R.id.but_time);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //一搬得alertDialog
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("删除数据")
                        .setMessage("确定删除数据吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "删除", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .show();

            }
        });
        butSingle.setOnClickListener(new View.OnClickListener() {
            String [] items={"红","绿","黄","蓝"};
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("指定背景颜色")
                        .setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,items[which],Toast.LENGTH_SHORT).show();
                                dialog.dismiss();

                            }
                        })
                        .create()
                        .show();
            }
        });
        //自定义AlertDialog

        butCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view=View.inflate(MainActivity.this,R.layout.alert_dialog,null);
               editName = view.findViewById(R.id.username);
                editpass = view.findViewById(R.id.edit_pass);

                new AlertDialog.Builder(MainActivity.this)
                        .setView(view)
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String name = editName.getText().toString();
                                String pass = editpass.getText().toString();
                                Toast.makeText(MainActivity.this,name+":"+pass,Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create()
                        .show();
            }
        });
        butShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "数据加载", "数据加载中.....");
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i <20 ; i++) {
                                    try {
                                        Thread.sleep(200);

                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                progressDialog.dismiss();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"加载完成",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                ).start();

            }
        });
        //显示水平的进度条 alertDialog
        butHorzonial.setOnClickListener(new View.OnClickListener() {
            int count=100;
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                new Thread(
                        new Runnable() {

                            @Override
                            public void run() {
                                for (int i = 0; i <count ; i++) {
                                    try {
                                        Thread.sleep(100);

                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    progressDialog.setProgress(progressDialog.getProgress()+1);
                                }
                                progressDialog.dismiss();
                            }
                        }
                ).start();
            }
        });
        /**
         * 显示日历
         *
         */
       butdata.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Calendar calendar = Calendar.getInstance();
               final int now_y = calendar.get(Calendar.YEAR);//得到年份
               final int now_m = calendar.get(Calendar.MONTH);//得到月份
               final int now_d = calendar.get(Calendar.DATE);//得到月份中今天的号数
               Log.e(TAG, "onClick: "+now_y+now_m+now_d );
               new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       Log.e(TAG, "onDateSet: "+now_y+"--"+(now_m+1)+"--"+now_d);

                   }
               },now_y,now_m,now_d).show();


           }
       });
       butTime.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               Calendar calendar = Calendar.getInstance();
               int now_h = calendar.get(Calendar.HOUR_OF_DAY);//得到一天中现在的时间，24小时制
               int now_mm = calendar.get(Calendar.MINUTE);//得到分钟数
               new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                   }
               },now_h,now_mm,true).show();
           }
       });



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " + "onDestroy");
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart: " + "onRestart");
        super.onRestart();
    }
}

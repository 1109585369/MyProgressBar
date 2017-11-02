package activity.yy.com.myprogressbar;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

    //该程序模拟填充为100的数组
    private int[] data=new int[100];
    int hasData=0;
    //记录ProgressBar的完成进度
    int status=0;
    ProgressBar bar,bar2;
    //创建一个负责更新的进度的Handler
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //表明消息是由该程序发送的
            if(msg.what==0x111){
                bar.setProgress(status);
                bar2.setProgress(status);
            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar=findViewById(R.id.bar);
        bar2=findViewById(R.id.bar2);
        //启动线程来执行任务
        new  Thread(){
            @Override
            public void run() {
                while (status<100){
                    //获取耗时操作的百分比
                    status=doWork();
                    handler.sendEmptyMessage(0x111);
                }
            }
        }.start();
    }


    public int doWork(){
        //为数组元素赋值
        data[hasData++]= (int) (Math.random()*100);
        try{
            Thread.sleep(100);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        return hasData;
    }
}

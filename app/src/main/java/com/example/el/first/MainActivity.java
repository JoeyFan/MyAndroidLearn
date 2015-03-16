package com.example.el.first;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.el.R;


public class MainActivity extends ActionBarActivity {
    private Button button;
    private EditText showNum;
    private TextView textView;

    private EditText oneEdit;
    private EditText twoEdit;
    private TextView resultTv;
    private Button  okBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.mobile_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNum=(EditText)findViewById(R.id.mobile_text);
                textView=(TextView)findViewById(R.id.mobile_show);
                //textView.setText(R.string.show_text_main);
                textView.setText(showNum.getText());

                Intent intent=new Intent();
                intent.setAction("android.intent.action.CALL");
              //  intent.addCategory("android.intent.category.DEFAULT");//方法内部会自动为Intent添加 android.intent.category.DEFAUL的category
                intent.setData(Uri.parse("tel:" + showNum.getText().toString()));
                startActivity(intent);
            }
        });


        okBtn=(Button)findViewById(R.id.button1);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String one =((EditText)findViewById(R.id.editText1)).getText().toString();
                String two =((EditText)findViewById(R.id.editText2)).getText().toString();
                Intent intent=new Intent();

                //intent.putExtra("result",one+"+"+two+"=，是否提交？");
                intent.setClass(MainActivity.this,ActivityTest.class);
                Bundle bundle=new Bundle();
                bundle.putString("result",one+"+"+two+"=，是否提交？");
                startActivityForResult(intent, 10, bundle);
            }
        });


    }


    /**
     * startActivityForResult 返回结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10){
            String key=data.getStringExtra("result");

            Toast.makeText(MainActivity.this,key,Toast.LENGTH_LONG).show();
            resultTv=(TextView)findViewById(R.id.textView3);
            resultTv.setText(key);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

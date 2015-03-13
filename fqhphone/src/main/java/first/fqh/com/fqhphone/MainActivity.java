package first.fqh.com.fqhphone;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import first.fqh.com.R;


public class MainActivity extends ActionBarActivity {
    private EditText phoneText;
    private EditText smsText;
    private Button smsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smsBtn=(Button)findViewById(R.id.smsBtn);
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneText =(EditText)findViewById(R.id.mobile_num);
                smsText=(EditText)findViewById(R.id.smsEdit);
                String number=phoneText.getText().toString();
                String sms=smsText.getText().toString();
                SmsManager smsManager=SmsManager.getDefault();
                for (String text:smsManager.divideMessage(sms)){
                    smsManager.sendTextMessage(number.toString(),null,text,null,null);
                }
            }
        });


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

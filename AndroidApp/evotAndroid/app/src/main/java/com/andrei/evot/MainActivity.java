package com.andrei.evot;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    private Button mainBtn;
    private EditText cnpET;
    private EditText passET;
    private Context context = this;
    String tempResultStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mainBtn = (Button) findViewById(R.id.mainBtn);
        cnpET = (EditText) findViewById(R.id.cnpET);
        passET = (EditText) findViewById(R.id.passET);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cmd = "loginBtn";
                try {
                    String cnp = cnpET.getText().toString();
                    cnp = normalizeString(cnp);
                    String password = passET.getText().toString();
                    User.mCnp = cnp;
                    User.mPassword = password;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                LoginBW bg = new LoginBW(context);
                bg.execute();
            }
        });

    }

    private String normalizeString(String str) {
        //remove white spaces
        str = str.replaceAll("\\s", "");
        //remove non ASCII characters
        str = str.replaceAll("[^\\x00-\\x7F]", "");
        //remove non printable caracters
        str = str.replaceAll("[\\p{C}]", "");
        str = str.replaceAll("[\\r\\n\\t]", "");
        return str;
    }
}

package com.andrei.evot;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginScreen extends AppCompatActivity {

    private Button mainBtn;
    private EditText cnpET;
    private EditText seriesPersET;
    private EditText numberPersET;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mainBtn = (Button) findViewById(R.id.mainBtn);
        cnpET = (EditText) findViewById(R.id.cnpET);
        seriesPersET = (EditText) findViewById(R.id.seriesPersET);
        numberPersET = (EditText) findViewById(R.id.numberPersET);

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cmd = "loginBtn";
                try {
                    String cnp = cnpET.getText().toString();
                    cnp = normalizeString(cnp);
                    String seriesPers = seriesPersET.getText().toString();
                    seriesPers = normalizeString(seriesPers);
                    String numberPers = numberPersET.getText().toString();
                    numberPers = normalizeString(numberPers);
                    User.mCnp = cnp;
                    User.mPassword = seriesPers;
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

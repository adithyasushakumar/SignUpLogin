package ae.bluecast.signuplogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
   private   Button login,register;
   private EditText etEmail,etPass;
    private DpHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new Session(this);
        db = new DpHelper(this);
        login = (Button)findViewById(R.id.bt1);
        register = (Button)findViewById(R.id.bt2);
        etEmail=(EditText)findViewById(R.id.et1email);
        etPass=(EditText)findViewById(R.id.et2pass);

        login.setOnClickListener(this);
        register.setOnClickListener(this);



        if(session.loggedIn()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                login();
                break;
            case R.id.bt2:
                startActivity(new Intent(LoginActivity.this, RegiserActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                break;
            default:

        }

    }
    private void login(){
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();
        if(db.getUser(email,password)) {
            session.setLoggedIn(true);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
            else
            {
                Toast.makeText(getApplicationContext(),"username/password is wrong",Toast.LENGTH_LONG).show();
            }
        }

}


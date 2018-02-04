package ae.bluecast.signuplogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegiserActivity extends AppCompatActivity implements View.OnClickListener {
    private Button reg;
    private TextView loginbackl;
    private EditText etEmail,etPassword;
    private DpHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiser);
        db =new DpHelper(this);
        reg = (Button)findViewById(R.id.btreg);
        loginbackl = (TextView)findViewById(R.id.textback);
        etEmail= (EditText)findViewById(R.id.et1);
        etPassword =(EditText)findViewById(R.id.et2);
        reg.setOnClickListener(this);
        loginbackl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btreg:
              register();
                break;
            case R.id.textback:
                startActivity(new Intent(RegiserActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.side_out_right);
                finish();
                break;
                default:
        }

    }
    private void register(){

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (!validate()) {
            onSignupFailed();
            return;
        }
        if(email.isEmpty() && password.isEmpty()){
            Toast.makeText(getApplicationContext(),"username/password is empty",Toast.LENGTH_LONG).show();


        }
        else {
            db.addUser(email,password);
            Toast.makeText(getApplicationContext(),"user registered",Toast.LENGTH_LONG).show();
            finish();
        }
    }
    private boolean validate() {
        boolean valid = true;


        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("enter a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            etPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }
    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();


    }
}

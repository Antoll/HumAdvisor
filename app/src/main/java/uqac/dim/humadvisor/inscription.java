package uqac.dim.humadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;


public class inscription extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextView signUp;
    private ImageButton returnButton;
    private EditText editTextPseudo, editTextMail, editTextPass, editTextConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        mAuth = FirebaseAuth.getInstance();
        returnButton = (ImageButton) findViewById(R.id.ib2);
        returnButton.setOnClickListener(this);
        signUp = (Button) findViewById(R.id.registerbutton);
        signUp.setOnClickListener(this);
        editTextPseudo = (EditText) findViewById(R.id.username);
        editTextMail = (EditText) findViewById(R.id.mail);
        editTextPass = (EditText) findViewById(R.id.password);
        editTextConfirmPass = (EditText) findViewById(R.id.passwordconfirm);


    }

//    public void inscriptionPage(View view)
//    {
//        Intent intent = new Intent(this,LoginActivity.class);
//        startActivity(intent);
//        finish();
//    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib2:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.registerbutton:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        String pseudo = editTextPseudo.getText().toString().trim();
        String mail = editTextMail.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();
        String passwordConfirm = editTextConfirmPass.getText().toString().trim();

        if (pseudo.isEmpty()){
            editTextPseudo.setError("Pseudo is required");
            editTextPseudo.requestFocus();
            return;
        }

        if (mail.isEmpty()){
            editTextMail.setError("Mail is required");
            editTextMail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            editTextMail.setError("Please provide valid email");
            editTextMail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPass.setError("Password is required");
            editTextPass.requestFocus();
            return;
        }

        if (password.length() < 6){
            editTextPass.setError("Password not long enough");
            editTextPass.requestFocus();
            return;
        }

        if (passwordConfirm.isEmpty()){
            editTextConfirmPass.setError("Confirm the password");
            editTextConfirmPass.requestFocus();
            return;
        }else{
            if (!password.equals(passwordConfirm)){
                editTextConfirmPass.setError("Password not the same");
                editTextConfirmPass.requestFocus();
                return;
            }
        }

    }
}
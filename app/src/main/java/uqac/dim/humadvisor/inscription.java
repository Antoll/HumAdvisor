package uqac.dim.humadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;


public class inscription extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextView signUp;
    private ImageButton returnButton;
    private EditText editTextPseudo, editTextMail, editTextPass, editTextConfirmPass;
    private ProgressBar progressBar;

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
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

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
                loginPage();
                break;
            case R.id.registerbutton:
                registerUser();
                break;
        }
    }

    public void loginPage()
    {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerUser(){
        String pseudo = editTextPseudo.getText().toString().trim();
        String email = editTextMail.getText().toString().trim();
        String password = editTextPass.getText().toString();
        String passwordConfirm = editTextConfirmPass.getText().toString();

        if (pseudo.isEmpty()){
            editTextPseudo.setError("Pseudo is required");
            editTextPseudo.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editTextMail.setError("Mail is required");
            editTextMail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
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
            editTextPass.setError("Password needs 6 characters at least");
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

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(pseudo, email, FirebaseAuth.getInstance().getUid());
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(inscription.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        loginPage();
                                    }else {
                                        Toast.makeText(inscription.this, "Failed to register", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(inscription.this, "Failed to register", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
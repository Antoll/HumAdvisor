package uqac.dim.humadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        button=(Button) findViewById(R.id.temporaire);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTempActivity2();
            }
        });

    }


    public void openTempActivity2() {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);}

    public void inscriptionPage(View view)
    {
        setContentView(R.layout.inscription);
    }

    public void connexionPage(View view)
    {
        setContentView(R.layout.activity_main);
    }
}
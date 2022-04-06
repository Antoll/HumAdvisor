package uqac.dim.humadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void inscriptionPage(View view)
    {
        setContentView(R.layout.inscription);
    }

    public void connexionPage(View view)
    {
        setContentView(R.layout.activity_main);
    }
}
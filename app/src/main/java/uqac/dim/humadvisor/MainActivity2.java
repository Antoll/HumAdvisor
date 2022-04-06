package uqac.dim.humadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity2 extends AppCompatActivity {


    BottomNavigationView bottomNavigationView ;

    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    ChatFragment chatFragment = new ChatFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.nav_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,searchFragment).commit();
                        return true;
                    case R.id.nav_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                        return true;
                    case R.id.nav_chat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,chatFragment).commit();
                        return true;

                }


                return false;
            }
        });
    }
}
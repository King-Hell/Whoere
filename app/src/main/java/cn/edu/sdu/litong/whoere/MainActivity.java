package cn.edu.sdu.litong.whoere;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.net.Socket;

public class MainActivity extends AppCompatActivity implements SettingFragment.OnFragmentInteractionListener, ChatFragment.OnFragmentInteractionListener {
    protected static Account account;
    protected static boolean isLogin=false;
    protected static Socket socket=null;
    private ChatFragment chatFragment;
    private SettingFragment settingFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_chat:
                    if(isLogin=false){
                        Toast.makeText(getApplicationContext(), "请先登录", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    if (chatFragment == null) {
                        chatFragment = new ChatFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, chatFragment).commit();
                    return true;
                case R.id.navigation_people:

                    return true;
                case R.id.navigation_launch:
                    if (settingFragment == null) {
                        settingFragment = new SettingFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, settingFragment).commit();
                    return true;
            }
            return false;
        }

    };


    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatFragment = new ChatFragment();
        settingFragment = new SettingFragment();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, 1);
        }
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setDefaultFragment();

    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        settingFragment = new SettingFragment();
        transaction.replace(R.id.content, settingFragment);
        transaction.commit();

    }
}

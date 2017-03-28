package cn.edu.sdu.litong.whoere;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.tencent.TIMManager;

public class MainActivity extends AppCompatActivity implements SettingFragment.OnFragmentInteractionListener, ChatFragment.OnFragmentInteractionListener {

    private ChatFragment chatFragment;
    private SettingFragment settingFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_chat:
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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setDefaultFragment();
        TIMManager.getInstance();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        chatFragment = new ChatFragment();
        transaction.replace(R.id.content, chatFragment);
        transaction.commit();
    }
}

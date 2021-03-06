package org.nearbyshops.enduserappnew.EditProfile.ChangePhone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;


import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import org.nearbyshops.enduserappnew.R;


public class ChangePhone extends AppCompatActivity implements ShowFragmentChangePhone{


    public static final String TAG_STEP_ONE = "tag_step_one";
    public static final String TAG_STEP_TWO = "tag_step_two";

    SmsVerifyCatcher smsVerifyCatcher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        overridePendingTransition(R.anim.enter_from_right,R.anim.exit_to_left);
        setContentView(R.layout.activity_change_phone);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
//        toolbar.setTitle("Forgot Password");
        setSupportActionBar(toolbar);



        if(savedInstanceState==null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,new FragmentChangePhone(),TAG_STEP_ONE)
                    .commitNow();
        }



        smsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {
            @Override
            public void onSmsCatch(String message) {


            }
        });



    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_right);
    }





    @Override
    public void showVerifyPhone() {

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                .replace(R.id.fragment_container,new FragmentVerifyPhone(),TAG_STEP_TWO)
                .addToBackStack("step_two")
                .commit();

    }

    @Override
    public void showResultSuccess() {


        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                .replace(R.id.fragment_container,new FragmentResultChangePhone())
                .commit();
    }



    @Override
    protected void onStart() {
        super.onStart();
        smsVerifyCatcher.onStart();
    }


    @Override
    protected void onStop() {
        super.onStop();
        smsVerifyCatcher.onStop();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }







}

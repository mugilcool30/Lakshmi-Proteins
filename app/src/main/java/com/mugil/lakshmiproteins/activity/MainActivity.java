package com.mugil.lakshmiproteins.activity;

import android.content.Intent;
import android.os.*;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.animation.ValueAnimator;
import android.view.animation.*;
import android.view.View;
import android.widget.RelativeLayout;
import android.support.v4.view.animation.*;
import android.animation.*;
import android.util.*;
import com.mugil.lakshmiproteins.R;
import com.daasuu.ei.*;
import com.mugil.lakshmiproteins.fragments.DashboardFragment;
import com.mugil.lakshmiproteins.custom_classes.StatusBarUtil;
import com.mugil.lakshmiproteins.fragments.LoginFragment;

import android.widget.ProgressBar;
import android.graphics.Color;
import android.graphics.PorterDuff;

public class MainActivity extends AppCompatActivity {
    Fragment frag_login;
    ProgressBar pbar;
    View button_login, button_label, button_icon, ic_menu1, ic_menu2;
    private DisplayMetrics dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbar = (ProgressBar) findViewById(R.id.mainProgressBar1);
        button_icon = findViewById(R.id.button_icon);
        button_label = findViewById(R.id.button_label);


        dm = getResources().getDisplayMetrics();
        button_login = findViewById(R.id.button_login);
        button_login.setTag(0);
        pbar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        StatusBarUtil.immersive(this);

        frag_login = new LoginFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, frag_login).commit();
        final ValueAnimator va = new ValueAnimator();
        va.setDuration(1000);
        va.setInterpolator(new DecelerateInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator p1) {
                RelativeLayout.LayoutParams button_login_lp = (RelativeLayout.LayoutParams) button_login.getLayoutParams();
                button_login_lp.width = Math.round((float) p1.getAnimatedValue());
                button_login.setLayoutParams(button_login_lp);
            }
        });
        button_login.animate().translationX(dm.widthPixels + button_login.getMeasuredWidth()).setDuration(0).setStartDelay(0).start();
        button_login.animate().translationX(0).setStartDelay(2000).setDuration(1500).setInterpolator(new OvershootInterpolator()).start();
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View p1) {

                va.setFloatValues(button_login.getMeasuredWidth(), button_login.getMeasuredHeight());
                va.start();
                pbar.animate().setStartDelay(300).setDuration(1000).alpha(1).start();
                button_label.animate().setStartDelay(100).setDuration(500).alpha(0).start();

                NextActivity(p1);


            }
        });
    }

    public void NextActivity(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(HomeActivity.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(HomeActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        ActivityCompat.startActivity(this, intent, options.toBundle());
        finish();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

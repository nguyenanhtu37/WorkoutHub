package com.workouthub;

import static com.workouthub.modules.common.guard.Guard.exitUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.workouthub.R;
import com.workouthub.databinding.ActivityMainBinding;
import com.workouthub.modules.home.controller.HomeMenu;
import com.workouthub.modules.setup.controller.SetUpActivity;

public class MainActivity extends AppCompatActivity {
    TextView title, subtitle;
    Button start;
    ImageView img1, img2;
    View load_bar, load_bar2;
    Animation anim_img1, anim_title, btn_anim, load_progress, load_stop;
    private ActivityMainBinding binding;

    private void OnBindingView() {
        anim_img1 = AnimationUtils.loadAnimation(this, R.anim.animimage1);
        anim_title = AnimationUtils.loadAnimation(this, R.anim.title);
        btn_anim = AnimationUtils.loadAnimation(this, R.anim.btnanim);
        load_progress = AnimationUtils.loadAnimation(this, R.anim.progress);
        load_stop = AnimationUtils.loadAnimation(this, R.anim.progress_stop);
        title = (TextView) findViewById(R.id.title_txt);
        subtitle = (TextView) findViewById(R.id.subtitle_txt);
        start = (Button) findViewById(R.id.btn1);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        load_bar = (View) findViewById(R.id.load_bar);
        load_bar2 = (View) findViewById(R.id.load_bar2);
        img1.startAnimation(anim_img1);
        img2.startAnimation(anim_img1);
        title.startAnimation(anim_title);
        subtitle.startAnimation(anim_title);
        start.startAnimation(btn_anim);
        load_bar.startAnimation(load_progress);
        load_bar2.startAnimation(load_stop);
    }

    private void OnBindingAction() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetUpPage();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (exitUser(this)) {
            openHomePage();
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        OnBindingView();
        OnBindingAction();
    }


    public void openSetUpPage() {
        Intent intent = new Intent(this, SetUpActivity.class);
        startActivity(intent);
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomeMenu.class);
        startActivity(intent);
    }
}
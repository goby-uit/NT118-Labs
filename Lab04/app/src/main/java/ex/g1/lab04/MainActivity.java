package ex.g1.lab04;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button fade_in_xml, fade_in_code,
    fade_out_xml, fade_out_code,
    blink_xml, blink_code,
    zoom_in_xml, zoom_in_code,
    zoom_out_xml, zoom_out_code,
    rotate_xml, rotate_code,
    move_xml, move_code,
    slide_up_xml, slide_up_code,
    bounce_xml, bounce_code,
    combine_xml, combine_code;
    ImageView uitLogo;
    Animation.AnimationListener animationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Todo: Add your code here
        findViewById();
        initVariables();
        //todo: khoi tao animation
        Animation fade_in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_fade_in);
        Animation fade_out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_fade_out);
        Animation blink = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_blink);
        Animation zoom_in = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_zoom_in);
        Animation zoom_out = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_zoom_out);
        Animation rotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_rotate);
        Animation move = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_move);
        Animation slide_up = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_slide_up);
        Animation bounce = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_bounce);
        Animation combine = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_combine);

        //todo: set animation listener
        fade_in.setAnimationListener(animationListener);
        fade_out.setAnimationListener(animationListener);
        blink.setAnimationListener(animationListener);
        zoom_in.setAnimationListener(animationListener);
        zoom_out.setAnimationListener(animationListener);
        rotate.setAnimationListener(animationListener);
        move.setAnimationListener(animationListener);
        slide_up.setAnimationListener(animationListener);
        bounce.setAnimationListener(animationListener);
        combine.setAnimationListener(animationListener);

        //todo: handle buttons XML
        fade_in_xml.setOnClickListener(v -> {uitLogo.startAnimation(fade_in);});
        fade_out_xml.setOnClickListener(v -> {uitLogo.startAnimation(fade_out);});
        blink_xml.setOnClickListener(v -> {uitLogo.startAnimation(blink);});
        zoom_in_xml.setOnClickListener(v -> {uitLogo.startAnimation(zoom_in);});
        zoom_out_xml.setOnClickListener(v -> {uitLogo.startAnimation(zoom_out);});
        rotate_xml.setOnClickListener(v -> {uitLogo.startAnimation(rotate);});
        move_xml.setOnClickListener(v -> {uitLogo.startAnimation(move);});
        slide_up_xml.setOnClickListener(v -> {uitLogo.startAnimation(slide_up);});
        bounce_xml.setOnClickListener(v -> {uitLogo.startAnimation(bounce);});
        combine_xml.setOnClickListener(v -> {uitLogo.startAnimation(combine);});


        //todo: handle buttons CODE
        fade_in_code.setOnClickListener(v->{
            Animation ani = FadeInAnimation();
            uitLogo.startAnimation(ani);
        });
        fade_out_code.setOnClickListener(v->{
            Animation ani = FadeOutAnimation();
            uitLogo.startAnimation(ani);
        });
        blink_code.setOnClickListener(v->{
            Animation ani = BlinkAnimation();
            uitLogo.startAnimation(ani);
        });
        zoom_in_code.setOnClickListener(v->{
            Animation ani = ZoomInAnimation();
            uitLogo.startAnimation(ani);
        });
        zoom_out_code.setOnClickListener(v->{
            Animation ani = ZoomOutAnimation();
            uitLogo.startAnimation(ani);
        });
        rotate_code.setOnClickListener(v->{
            Animation ani = RotateAnimation();
            uitLogo.startAnimation(ani);
        });
        move_code.setOnClickListener(v->{
            Animation ani = MoveAnimation();
            uitLogo.startAnimation(ani);
        });
        slide_up_code.setOnClickListener(v->{
            Animation ani = SlideUpAnimation();
            uitLogo.startAnimation(ani);
        });
        bounce_code.setOnClickListener(v->{
            Animation ani = BounceAnimation();
            uitLogo.startAnimation(ani);
        });
        combine_code.setOnClickListener(v->{
            Animation ani = CombineAnimation();
            uitLogo.startAnimation(ani);
        });

        //todo: click logo UIT
        uitLogo.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);
        });

        //todo: End code here

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void findViewById() {
        uitLogo = findViewById(R.id.uit_logo);
        fade_in_xml = findViewById(R.id.fade_in_xml);
        fade_in_code = findViewById(R.id.fade_in_code);
        fade_out_xml = findViewById(R.id.fade_out_xml);
        fade_out_code = findViewById(R.id.fade_out_code);
        blink_xml = findViewById(R.id.blink_xml);
        blink_code = findViewById(R.id.blink_code);
        zoom_in_xml = findViewById(R.id.zoom_in_xml);
        zoom_in_code = findViewById(R.id.zoom_in_code);
        zoom_out_xml = findViewById(R.id.zoom_out_xml);
        zoom_out_code = findViewById(R.id.zoom_out_code);
        rotate_xml = findViewById(R.id.rotate_xml);
        rotate_code = findViewById(R.id.rotate_code);
        move_xml = findViewById(R.id.move_xml);
        move_code = findViewById(R.id.move_code);
        slide_up_xml = findViewById(R.id.slide_up_xml);
        slide_up_code = findViewById(R.id.slide_up_code);
        bounce_xml = findViewById(R.id.bounce_xml);
        bounce_code = findViewById(R.id.bounce_code);
        combine_xml = findViewById(R.id.combine_xml);
        combine_code = findViewById(R.id.combine_code);
    }
    private void initVariables() {

        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation has done!",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }
    //todo: function anim From code
    private Animation FadeInAnimation() {
        Animation ani = new AlphaAnimation(0.0f, 1.0f);
        ani.setDuration(3000);
        ani.setFillAfter(true);
        ani.setAnimationListener(animationListener);
        return ani;

    }
    private Animation FadeOutAnimation() {
        Animation ani = new AlphaAnimation(1.0f, 0.0f);
        ani.setDuration(3000);
        ani.setFillAfter(true);
        ani.setAnimationListener(animationListener);
        return ani;
    }
    private Animation BlinkAnimation() {
        Animation ani = new AlphaAnimation(0.0f, 1.0f);
        ani.setDuration(500);
        ani.setRepeatCount(3);
        ani.setRepeatMode(Animation.REVERSE);
        ani.setAnimationListener(animationListener);
        return ani;
    }
    private Animation ZoomInAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 2.0f,
                1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }
    private Animation ZoomOutAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 0.5f,
                1.0f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }
    private Animation RotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setInterpolator(new CycleInterpolator(1));
        rotateAnimation.setAnimationListener(animationListener);
        return rotateAnimation;
    }
    private Animation MoveAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f
        );
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setAnimationListener(animationListener);
        return translateAnimation;
    }
    private Animation SlideUpAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.0f,
                1.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f
        );
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }
    private Animation BounceAnimation (){
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.0f,
                0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f
        );
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }
    private Animation CombineAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.setInterpolator(new LinearInterpolator());

        Animation roteAnimation = RotateAnimation();
        Animation moveAnimation = MoveAnimation();

        // Thêm các animation vào AnimationSet
        animationSet.addAnimation(roteAnimation);
        animationSet.addAnimation(moveAnimation);
        animationSet.setAnimationListener(animationListener);
        return animationSet;
    }
}
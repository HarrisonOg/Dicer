package com.harrisonog.dicer;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final Random RANDOM = new Random();
    private Button rollDices;
    private ImageView imageView1, imageView2;
    private TextView resultView;
    private int dOne, dTwo;

    Animation anim1;
    Animation anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dOne = 1;
        dTwo = 1;

        rollDices = (Button) findViewById(R.id.rollDiceButton);
        imageView1 = (ImageView) findViewById(R.id.diceView1);
        imageView2 = (ImageView) findViewById(R.id.diceView2);
        resultView = (TextView) findViewById(R.id.resultTextView);

        rollDices.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
        anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
        this.setUpAnimation();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.diceView1:
                imageView1.startAnimation(anim1);
                break;

            case R.id.diceView2:
                imageView2.startAnimation(anim2);
                break;

            case R.id.rollDiceButton:
                imageView1.startAnimation(anim1);
                imageView2.startAnimation(anim2);
                break;

            default:
                break;
        }
    }

    public void setUpAnimation() {
        final Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int value = randomDiceValue();
                int res = getResources().getIdentifier("dice_" + value, "drawable", "com.harrisonog.dicer");

                if(animation == anim1){
                    dOne = value;
                    imageView1.setImageResource(res);
                } else if (animation == anim2) {
                    dTwo = value;
                    imageView2.setImageResource(res);
                }

                resultView.setText(new String("Your roll is: " + (dOne + dTwo)));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        anim1.setAnimationListener(animationListener);
        anim2.setAnimationListener(animationListener);
    }

    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }
}

package com.ajayvamsee.slider;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

public class MainActivity extends AppCompatActivity {

    Slider slider;
    NumberPicker picker;
    TextView tvData,tvTime;
    ImageView ivInc,ivDec;
    int val=10;
    Animation animation;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slider=findViewById(R.id.slider);
        picker=findViewById(R.id.numPick);
        tvData=findViewById(R.id.tvData);
        ivInc=findViewById(R.id.ivInc);
        ivDec=findViewById(R.id.ivDec);
        tvTime=findViewById(R.id.tvTime);
        aSwitch=findViewById(R.id.swSettingClutch);

        animation=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);


        picker.setMaxValue(100);
        picker.setMinValue(10);
        picker.setWrapSelectorWheel(false);

        aSwitch.setClickable(true);



        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
               // Log.d("aa","onStartTrackingTouch  "+ slider.getValue());
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                Log.d("aa", "onStopTrackingTouch "+ ((int)slider.getValue()));
                tvData.setText("Slider Picker "+((int)slider.getValue())+" seconds");
            }
        });

        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
               // tvData.setText("Slider Picker addOnChangeListener"+((int)slider.getValue())+" seconds");
                tvData.setText((int) value+"valuesss");

                adjustBrightness(slider.getValue());
            }
        });

        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                tvData.setText("Number Picker "+newVal+" seconds");
            }
        });

        ivInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val++;
                tvTime.setText(""+val);
            }
        });

        ivDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(val>10 && val<100){
                    val--;
                }
                tvTime.setText(""+val);
            }
        });
    }

    private void adjustBrightness(float brightness) {
        runOnUiThread(() -> {
            WindowManager.LayoutParams layout = getWindow().getAttributes();
            layout.screenBrightness = brightness;
            getWindow().setAttributes(layout);
        });
    }
}
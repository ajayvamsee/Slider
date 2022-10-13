package com.ajayvamsee.slider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ajayvamsee.slider.databinding.ActivityMain2Binding;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setContentView(R.layout.activity_main2);
        // textView= findViewById(R.id.textView1);

         //textView.setText(getMacAddr());

        loading();
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            //handle exception
            ex.printStackTrace();
        }
        return "";
    }

    private void loading() {
        binding.swOpenClose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvDoorStatus.setText(R.string.opened);
                }else{
                    binding.tvDoorStatus.setText(R.string.closed);
                }

            }
        });

        binding.swSettingClutch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.swOpenClose.setClickable(true);
                    binding.tvClutchStatus.setText(R.string.activated);
                    binding.cvOprDisable.setVisibility(View.GONE);
                    if(binding.clutchSwitch.isChecked()){
                        binding.cvOprDisableInstall.setVisibility(View.GONE);
                    }else{
                        binding.cvOprDisableInstall.setVisibility(View.VISIBLE);
                    }
                }else {
                    binding.swOpenClose.setClickable(false);
                    binding.tvClutchStatus.setText(R.string.deactivated);
                    binding.cvOprDisable.setVisibility(View.VISIBLE);
                    if(binding.cvOprDisableInstall.getVisibility() == View.VISIBLE){
                        binding.cvOprDisableInstall.setVisibility(View.GONE);
                        binding.cvOprDisable.setVisibility(View.VISIBLE);
                    }

                }
            }
        });

        // installation

        binding.clutchSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.swOpenClose.setClickable(true);
                    binding.cvOprDisableInstall.setVisibility(View.GONE);
                    if(binding.clutchSwitch.isChecked()){
                        binding.cvOprDisable.setVisibility(View.GONE);
                        if(binding.swSettingClutch.isChecked()){
                            binding.cvOprDisable.setVisibility(View.GONE);
                        }else{
                            binding.cvOprDisable.setVisibility(View.VISIBLE);
                        }
                    }else{
                        binding.cvOprDisable.setVisibility(View.GONE);
                        binding.cvOprDisableInstall.setVisibility(View.VISIBLE);

                    }
                }else {
                    binding.swOpenClose.setClickable(false);
                    binding.cvOprDisableInstall.setVisibility(View.VISIBLE);
                    if(binding.cvOprDisable.getVisibility() == View.VISIBLE){
                        binding.cvOprDisableInstall.setVisibility(View.VISIBLE);
                        binding.cvOprDisable.setVisibility(View.GONE);
                    }

                }
            }
        });
    }
}
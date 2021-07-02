package com.practicaltask;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.practicaltask.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public ActivityMainBinding binding;
    private String output = "";
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.tvSubmit.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSubmit:
                if (!Utils.validateString(binding.etInputString.getText().toString())) {
                    Utils.showMessageOK(MainActivity.this, "Alert", "Enter Input String");
                }else if (!Utils.validateString(binding.etSkipNumber.getText().toString())) {
                    Utils.showMessageOK(MainActivity.this, "Alert", "Enter Number");
                }else{
                    InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                    if (imm != null){
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);}
                    String input = binding.etInputString.getText().toString().trim();
                    number = Integer.parseInt(binding.etSkipNumber.getText().toString());
                    String s[] = input.split("\\.");
                    System.out.println(s[0]);
                    int i = 0;

                    for(int j = s.length; i < j; ++i) {
                        s[i] = s[i].trim();
                        if (number>=0){
                            getPositive(s[i]);
                        }else {
                            getNegative(s[i]);
                        }
                    }
                    System.out.println(output);
                    binding.ivOutput.setVisibility(View.VISIBLE);
                    binding.tvOutputString.setText(output);
                    output = "";
                }
                break;
        }
    }
    private void getPositive(String sentence) {
        List words = Arrays.asList(sentence.split(" "));
        String result = "";
        int i;
        boolean var5;

        if (words.size() > number) {
            i = words.size() - (number + 1);

            for(var5 = false; i >= 0; --i){
                result = result + (String)words.get(i) + " ";
                System.out.println(result);
            }
        }
        if (words.size() > (number-1)){
            i = words.size() - number;

            for(int j = words.size(); i < j; ++i){
                result = result + (String)words.get(i) + " ";
                System.out.println(result);
            }
        }else if (words.size() < number) {
            result = result + sentence;
        }
        output += result.trim()+'.';
    }

    private void getNegative(String sentence) {
        List words = Arrays.asList(sentence.split(" "));
        String result = "";

        if (words.size() > number + 1 && words.size() >= number * -1) {
            for(int j = 0; j < number * -1; ++j) {
                System.out.println(j);
                result = result + (String)words.get(j) + " ";
            }
        }else if (words.size() < number * -1) {
            result = result + sentence;
        }

        if (words.size() > number) {
            for(int i = words.size() - 1; i >= number * -1; --i){
                System.out.println(i);
                result = result + (String)words.get(i) + " ";
            }
        }
        output += result.trim()+'.';
    }
}
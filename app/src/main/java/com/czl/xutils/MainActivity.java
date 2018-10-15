package com.czl.xutils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.czl.xutils.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public MutableLiveData<String> name = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.init(getApplicationContext());
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(this);
        binding.setLifecycleOwner(this);
    }

    public void onClick() {
        String n = name.getValue();
        ToastUtils.showLong(n);
        name.setValue("OK");
    }
}

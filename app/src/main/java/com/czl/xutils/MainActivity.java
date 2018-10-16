package com.czl.xutils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.czl.xutils.databinding.ActivityMainBinding;
import com.czl.xutils.model.NetRsp;

public class MainActivity extends AppCompatActivity {
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> name1 = new MutableLiveData<>();

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
        ApiManager.getToday().observe(this, today -> name.setValue(today.getCategory().get(0)));
        ApiManager.getAndroids(10, 1).observe(this, netRsp -> name1.setValue(netRsp.getResults().get(0).getDesc()));
    }
}

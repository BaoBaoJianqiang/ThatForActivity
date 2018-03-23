package jianqiang.com.plugin1;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends BasePluginActivity {

    private static final String TAG = "Client-SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        that.setContentView(R.layout.second);
    }
}
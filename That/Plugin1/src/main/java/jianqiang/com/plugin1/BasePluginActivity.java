package jianqiang.com.plugin1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.example.jianqiang.mypluginlibrary.AppConstants;
import com.example.jianqiang.mypluginlibrary.RefInvoke;

import java.lang.reflect.Method;

public class BasePluginActivity extends Activity {

    private static final String TAG = "Client-BaseActivity";

    /**
     * 等同于mProxyActivity，可以当作Context来使用，会根据需要来决定是否指向this<br/>
     * 可以当作this来使用
     */
    protected Activity that;
    protected String dexPath;

    public void setProxy(Activity proxyActivity, String dexPath) {
        that = proxyActivity;
        this.dexPath = dexPath;
    }
}

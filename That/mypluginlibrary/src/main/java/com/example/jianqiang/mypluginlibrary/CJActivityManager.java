package com.example.jianqiang.mypluginlibrary;

import java.util.ArrayList;
import java.util.List;

public class CJActivityManager {
    private static List<IRemoteActivity> atyStack;

    private CJActivityManager() {}

    private static class ManagerHolder {
        private static final CJActivityManager instance = new CJActivityManager();
    }

    public static CJActivityManager create() {
        return ManagerHolder.instance;
    }

    public IRemoteActivity launch(IRemoteActivity pluginAty) {
        if (atyStack == null) {
            atyStack = new ArrayList<IRemoteActivity>();
            add(pluginAty);
            return null;
        } else {
            return query(pluginAty);
        }
    }

    /**
     * 添加一个插件Activity到返回栈
     *
     * @param aty
     */
    private void add(IRemoteActivity aty) {
        atyStack.add(aty);
    }

    /**
     * 核心方法
     *
     * @param aty
     * @return
     */
    private IRemoteActivity query(IRemoteActivity aty) {
        IRemoteActivity res = null;
        int count = atyStack.size() - 1;
        for (int i = count; i >= 0; i--) {
            IRemoteActivity temp = atyStack.get(i);
            if (temp.getClass().equals(aty.getClass())) {
                switch (temp.getLaunchMode()) {
                    case LaunchMode.STANDARD:
                        add(aty);
                        break;
                    case LaunchMode.SINGLETOP:
                        if (i == count) {
                            res = temp;
                        } else {
                            add(aty);
                        }
                        break;
                    case LaunchMode.SINGLETASK:
                        for (int j = i + 1; j < count; j++) {
                            finish(j, atyStack.get(j));
                        }
                        res = temp;
                        break;
                    case LaunchMode.SINGLEINSTANCE:
                        for (int j = i + 1; j < count; j++) {
                            atyStack.set(j - 1, atyStack.get(j));
                        }
                        atyStack.set(count - 1, temp);
                        res = temp;
                        break;
                }
                break; // break loop
            } else {
                add(aty);
            }
        }
        return res;
    }

    /**
     * 结束Stack中指定index的Activity
     *
     * @param index
     *            Stack中指定Activity的下标
     * @param plugin
     *            指定的插件Activity
     */
    private void finish(int index, IRemoteActivity plugin) {
        if (plugin instanceof BasePluginActivity) {
            ((BasePluginActivity) plugin).that.finish();
        }
        atyStack.remove(index);
    }

    /**
     * 结束掉指定的插件Activity
     *
     * @param activity
     */
    public void finish(IRemoteActivity activity) {
        if (activity != null) {
            atyStack.remove(activity);
            if (activity instanceof BasePluginActivity) {
                ((BasePluginActivity) activity).that.finish();
            }
            activity = null;
        }
    }

    /**
     * 结束指定的插件Activity
     */
    public void finishActivity(Class<?> cls) {
        for (IRemoteActivity activity : atyStack) {
            if (activity.getClass().equals(cls)) {
                finish(activity);
            }
        }
    }
}
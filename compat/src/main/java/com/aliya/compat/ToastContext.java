package com.aliya.compat;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * 解决 8.0 以下 Toast WindowManager$BadTokenException 异常
 *
 * @author a_liYa
 * @date 2019-06-06 17:07.
 */
public class ToastContext extends ContextWrapper {

    public static Context compatContext(Context context) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.O ? new ToastContext(context) : context;
    }

    private LayoutInflater mInflater;

    public ToastContext(Context base) {
        super(base);
    }

    @Override
    public Context getApplicationContext() {
        return new ApplicationContextWrapper(super.getApplicationContext());
    }

    @Override
    public Object getSystemService(String name) {
        Object service = super.getSystemService(name);
        if (Context.LAYOUT_INFLATER_SERVICE.equals(name)) {
            if (service instanceof LayoutInflater) {
                if (mInflater == null) {
                    mInflater = ((LayoutInflater) service).cloneInContext(this);
                }
            }
            return mInflater;
        }
        return service;
    }

    static class ApplicationContextWrapper extends ContextWrapper {

        public ApplicationContextWrapper(Context base) {
            super(base);
        }

        @Override
        public Object getSystemService(String name) {
            if (Context.WINDOW_SERVICE.equals(name)) {
                return new WindowManagerWrapper((WindowManager) super.getSystemService(name));
            }
            return super.getSystemService(name);
        }
    }

    static class WindowManagerWrapper implements WindowManager {

        private final WindowManager mBase;

        private WindowManagerWrapper(WindowManager base) {
            this.mBase = base;
        }

        @Override
        public Display getDefaultDisplay() {
            return mBase.getDefaultDisplay();
        }

        @Override
        public void removeViewImmediate(View view) {
            mBase.removeViewImmediate(view);
        }

        @Override
        public void addView(View view, ViewGroup.LayoutParams params) {
            try {
                mBase.addView(view, params);
            } catch (BadTokenException e) {
                /* ignore */
            }
        }

        @Override
        public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
            mBase.updateViewLayout(view, params);
        }

        @Override
        public void removeView(View view) {
            mBase.removeView(view);
        }
    }
}

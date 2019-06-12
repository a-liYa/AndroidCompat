# AndroidCompat
兼容处理Android系统相关的问题

## 依赖

[![License](https://img.shields.io/badge/License-Apache%202.0-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://api.bintray.com/packages/a-liya/maven/android-compat/images/download.svg) ](https://bintray.com/a-liya/maven/android-compat/_latestVersion)
[![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2014%20-f0ad4e.svg)](https://android-arsenal.com/api?level=14)

```
implementation 'com.aliya:android-compat:0.0.1'
```

## 1. 修复 Toast WindowManager$BadTokenException 异常

```
异常log：

    android.view.WindowManager$BadTokenException: Unable to add window -- token android.os.BinderProxy@420183e is not valid; is your activity running?
        at android.view.ViewRootImpl.setView(ViewRootImpl.java:679)
        at android.view.WindowManagerGlobal.addView(WindowManagerGlobal.java:342)
        at android.view.WindowManagerImpl.addView(WindowManagerImpl.java:93)
        at android.widget.Toast$TN.handleShow(Toast.java:459)
        at android.widget.Toast$TN$2.handleMessage(Toast.java:342)
        at android.os.Handler.dispatchMessage(Handler.java:102)
        at android.os.Looper.loop(Looper.java:154)
        at android.app.ActivityThread.main(ActivityThread.java:6119)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:886)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:776)
```
修复方法
```
Toast.makeText(ToastContext.compatContext(context), "", Toast.LENGTH_SHORT).show();
```

## 2. 修复 Object#finalize() - java.util.concurrent.TimeoutException 异常


```
异常log：

    java.util.concurrent.TimeoutException: com.android.org.conscrypt.OpenSSLDigestContext.finalize() timed out after 120 seconds
        at com.android.org.conscrypt.NativeCrypto.EVP_MD_CTX_destroy(Native Method)
        at com.android.org.conscrypt.OpenSSLDigestContext.finalize(OpenSSLDigestContext.java:27)
        at java.lang.Daemons$FinalizerDaemon.run(Daemons.java:200)
        at java.lang.Daemons$FinalizerDaemon.run(Daemons.java:200)
        at java.lang.Thread.run(Thread.java:818)
```

修复方法：在 Application#onCreate() 调用以下方法

```
@Override
    public void onCreate() {
        super.onCreate();

        CrashCompat.fixBug(); // 在Bug上报工具之后，eg：Bugly init.
    }
```
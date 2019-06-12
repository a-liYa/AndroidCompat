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
Toast.makeText(ToastContext.compatContext(context), "", Toast.LENGTH_SHORT).show();
```

## 2. 修复 Object#finalize() - java.util.concurrent.TimeoutException 异常

在 Application#onCreate() 调用以下方法

```
@Override
    public void onCreate() {
        super.onCreate();

        CrashCompat.fixBug(); // 在Bug上报工具之后，eg：Bugly init.
    }
```
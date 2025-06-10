# HeGuiChecker

合规检测器-基于工信部[最新标准](http://www.gov.cn/zhengce/zhengceku/2020-08/02/content_5531975.htm)

下面是我基于`工信部文件`整理出来已经`hook`了的方法，如有其他的可以提个`issue`。

| 包名                               | 方法名                                                       |
| ---------------------------------- | ------------------------------------------------------------ |
| android.telephony.TelephonyManager | getDeviceId,getImei,getSubscriberId                          |
| android.net.wifi.WifiInfo          | getMacAddress                                                |
| java.net.NetworkInterface          | getHardwareAddress                                           |
| android.provider.Settings.Secure   | getString(AndroidId)                                         |
| LocationManager                    | getLastKnownLocatio,requestLocationUpdates                   |
| PackageManager                     | getInstalledPackages,getInstalledApplications,getInstallerPackageName,getPackageInfo |
| ClipboardManager                   | hasPrimaryClip,getPrimaryClip                                |
| 待新增                             |                                                              |

## 导入依赖

[![](https://jitpack.io/v/simplepeng/HeGuiChecker.svg)](https://jitpack.io/#simplepeng/HeGuiChecker)

```groovy
maven { url 'https://jitpack.io' }
```

```groovy
def checkerV = "1.1.1"
```

### pine版本

基于[pine](https://github.com/canyie/pine)，适配`Android 4.4(ART only) ~ 15 Beta 4 with thumb-2/arm64`版本，感谢大佬的无私奉献。

```groovy
debugImplementation "com.github.simplepeng.HeGuiChecker:checker-pine:$checkerV"
releaseImplementation "com.github.simplepeng.HeGuiChecker:checker-no-op:$checkerV"
```

## 使用

基于`ContentProvider`自动初始化，无需引入初始化代码

输出：

![](imgs/img_log.png)

显示：

![](imgs/img_toast.png)

当然你也可以关闭`输出`或`显示`

```kotlin
HeGuiChecker.SHOW_LOG = false
HeGuiChecker.SHOW_TOAST = false
```

或者在`同意授权后`不再显示任何信息

```kotlin
HeGuiChecker.allow(true)
```

## 感谢各位大佬打赏🙇🙇🙇！

您的支持是作者努力更新的动力。万水千山总是情，10.24我看行！

| ![](https://raw.githubusercontent.com/simplepeng/merge_pay_code/refs/heads/master/qrcode_alipay.jpg) | ![](https://raw.githubusercontent.com/simplepeng/merge_pay_code/refs/heads/master/qrcode_wxpay.png) | ![](https://raw.githubusercontent.com/simplepeng/merge_pay_code/refs/heads/master/qrcode_qqpay.png) |
| ------------------------------------------------------------ | ----- | ----- |

[打赏链接](https://simplepeng.com/merge_pay_code/) | [赞助列表](https://simplepeng.com/Sponsor/)

## 版本

* 1.1.1：un include `checker` module
* 1.1.0：升级pine版本到0.3.0
* 1.0.4：新增`WifiInfoHooker`的hook方法
  * getConnectionInfo
  * hookGetSSID
* v1.0.3: 新增`ClipboardManager`，hook方法
  * hasPrimaryClip
  * getPrimaryClip
* v1.0.2：新增`PackageManagerHooker`，hook方法
  * getInstalledPackages
  * getInstalledApplications
  * getInstallerPackageName
  * getPackageInfo
* v1.0.1：基于`pine`重写
* v1.0.0：首次上传
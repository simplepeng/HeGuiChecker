# HeGuiChecker

合规检测器-基于工信部[最新标准](http://www.gov.cn/zhengce/zhengceku/2020-08/02/content_5531975.htm)

下面是我基于`工信部文件`整理出来已经`hook`了的方法，如有其他的可以提个`issue`。

| 包名                               | 方法名                                       |
| ---------------------------------- | -------------------------------------------- |
| android.telephony.TelephonyManager | getDeviceId，getImei，getSubscriberId        |
| android.net.wifi.WifiInfo          | getMacAddress                                |
| java.net.NetworkInterface          | getHardwareAddress                           |
| android.provider.Settings.Secure   | getString(AndroidId)                         |
| LocationManager                    | getLastKnownLocation，requestLocationUpdates |
| 待新增                             |                                              |

## 导入依赖

[![](https://jitpack.io/v/simplepeng/HeGuiChecker.svg)](https://jitpack.io/#simplepeng/HeGuiChecker)

```groovy
maven { url 'https://jitpack.io' }
```

```groovy
def checkerV = "v1.0.2"
```

### pine版本-推荐

基于[pine](https://github.com/canyie/pine)，适配`4.4-12.1`版本，感谢大佬的无私奉献。

```groovy
debugImplementation "com.github.simplepeng.HeGuiChecker:checker-pine:$checkerV"
releaseImplementation "com.github.simplepeng.HeGuiChecker:checker-no-op:$checkerV"
```

### SankHook版本-不推荐

基于[SandHook](https://github.com/asLody/SandHook)，已适配`4.4-11`版本，感谢大佬的无私奉献。

```groovy
debugImplementation "com.github.simplepeng.HeGuiChecker:checker:$checkerV"
releaseImplementation "com.github.simplepeng.HeGuiChecker:checker-no-op:$checkerV"
```

#### ! 使用警告 !

因为`SandHook`的限制的，`targetSdk>28`初始化会闪退，所以在测试的时候：

**请设置`targetSdk = 28`**

**请设置`targetSdk = 28`**

**请设置`targetSdk = 28`**

`pine`则不需要，所以更推荐`pine版本`。

或者：配置一个`productFlavors`或许会更方便。

后记：但是我看`SandHook`的`PR`里有人提交了修复，等大佬合并吧。

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

## 版本

* v1.0.2：新增`PackageManagerHooker`，hook方法
	* getInstalledPackages
	* getInstalledApplications
	* getInstallerPackageName
	* getPackageInfo
* v1.0.1：基于`pine`重写
* v1.0.0：首次上传
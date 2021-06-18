关注公众号学习更多知识



![](https://files.mdnice.com/user/15648/404c2ab2-9a89-40cf-ba1c-02df017a4ae8.jpg)




# 1、ViewModel

大部分人更关心用法，所以我 1.1 讲用法， 1.2 讲对 viewmodel 的理解

另外为了大家看的更清晰，本文不会扩展 viewmodel 和 livedata 结合使用的案例

## 1.1、ViewModel 初始化方式

时间来到 androidx，ViewModel 的创建方式与老版本有了很大的不同，所以这里还是要将 Viewmodel 的初始化讲一下

**本例中前四种创建 viewmodel 的方法都不能复用 viewmodel，不建议使用，第五种是建议的初始化方式**

### 1.1.1、安卓工厂初始化

使用 AndroidViewModelFactory 进行工厂创建

1. viewmodel 类定义

```
class AndroidFactoryViewModel(app:Application): AndroidViewModel(app) {
    fun print(){
        logEE("使用安卓默认工厂创建viewmodel")
    }
}
```

2. 创建 viewmodel 实例代码

```
val model = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                .create(AndroidFactoryViewModel::class.java)
            model.print()
```

### 1.1.2、简单工厂初始化工厂初始化工厂

NewInstanceFactory

1. viewmodel 类定义代码

```
class SimpleFactoryViewModel: ViewModel() {
    fun print(){
        logEE("使用简单工厂创建viewmodel")
    }
}
```

2. 创建 viewmodel 代码

```
 val model =
                ViewModelProvider.NewInstanceFactory().create(SimpleFactoryViewModel::class.java)
            model.print()
```

### 1.1.3、自定义安卓工厂初始化

默认的工厂只能创建带 Application 的 ViewModel 实例的，我们通过自定义工厂实现自定义构造参数的目的

1.  定义安卓工厂

```
class CustomAndroidViewModelFactory(val app: Application, private val data: String) :
    ViewModelProvider.AndroidViewModelFactory(app) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
            return try {
                modelClass.getConstructor(Application::class.java, String::class.java)
                    .newInstance(app, data)
            } ...
        }
        return super.create(modelClass)
    }
}
```

省略了 catch 中的代码，您可以到源码中查看

在我们的自定义工厂的构造中传入一个自定义的 data 参数，代表我们的自定义构造参数

2. viewmodel 类定义

```
class CustomAndroidViewModel(private val app: Application, private val data: String) :
    AndroidViewModel(app) {
    fun print() {
        logEE(data)
    }
}
```

我们的 CustomAndroidViewModel 类中也有一个 data：String 参数，这个参数就是对应上一步中自定义工厂中的 data 参数

3. 实例化 viewmodel

```
val model = ViewModelProvider(
                viewModelStore,
                CustomAndroidViewModelFactory(application, "自定义安卓工厂创建viewmodel")
            ).get(CustomAndroidViewModel::class.java)
            model.print()
```

### 1.1.4、自定义简单工厂初始化

自定义简单工厂也是为了实现 viewmodel 构造传参的目的，废话不多说，直接上代码吧

1.  定义安卓工厂

```
class CustomSimpleViewModelFactory(app:Application,private val data:String) : ViewModelProvider.AndroidViewModelFactory(app) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return try {
            modelClass.getConstructor(String::class.java).newInstance(data)
        } ......
    }
}
```

2. viewmodel 类定义

```
class CustomSimpleViewModel(private val data: String) : ViewModel() {
    fun print() {
        logEE(data)
    }
}
```

3. 实例化 viewmodel

```
val model = ViewModelProvider(
                viewModelStore,
                CustomSimpleViewModelFactory(application, "自定义简单工厂创建viewmodel")
            ).get(CustomSimpleViewModel::class.java)
            model.print()
```

### 1.1.5 使用委托机制创建 viewmodel（推荐）

google 官方给我们提供了 activity-ktx 库，通过它我们可以使用委托机制创建 viewmodel

实现方式如下：

1. 加入依赖

```
implementation 'androidx.activity:activity-ktx:1.2.2'
```

2. 创建 viewmodel 类

```
class EnTrustModel : ViewModel() {
    fun print(){
        logEE("关注公众号 \"安安安安卓\" 免费学知识")
    }
}
```

3. 实例化 viewmodel

```
private val wtModel: EnTrustModel by viewModels<EnTrustModel> {
        ViewModelProvider.NewInstanceFactory()
    }
```

4. 调用 viewmodel 的方法

```
 wtModel.print()
```

## 1.2 viewmodel 概览

ViewModel 类旨在以注重生命周期的方式存储和管理界面相关的数据。ViewModel 类让数据可在发生屏幕旋转等配置更改后继续留存。

我们知道 android 中的 Activity 和 Fragment 都是可以对生命周期进行管理的，如果 Activity/Fragment 被系统销毁，例如屏幕旋转就必须重新创建 Activity/Fragment，这个时候就涉及到一个暑假恢复的问题。通常我们会使用 onSaveInstanceState 和 onRestoreSaveInstance 两个方法对数据进行保存和恢复，但是这两个方法只能处理数据量较小的情况。

如果是大数据量的情况那么用 viewmodel 就是不错的选择了

同时 viewmodel 还可以帮助我们将业务逻辑从 Activity/Fragment 中分离出来

## 1.3 viewmodel 实现 Fragment 之间数据共享

同一个 activity 中出现多个 Fragment，并且这些 Fragment 之间需要进行通信，这样的需求是很常见的

以往我们可能会采用下列方式实现数据共享：

1. EventBus
2. 接口回调
3. 全局共享数据池

但是当我们学了 viewmodel 后就会发现，viewmodel 可以轻松实现 Fragment 之间的数据共享

我们可以让多个 Fragment 共享同一个 ViewModel 实现数据的共享。

本来中我们要实现下面的功能：

activity 中有两个 fragment，FragmentA 和 FragmentB，我们在 ViewModel 的构造方法中开始倒计时 2000s，在 FragmentA 中观察倒计时的数据并展示在页面上。然后再切换到 FragmentB，如果 FragmentB 中的倒计时的秒数没有重置为 2000，说明我们的数据共享成功了。

上代码：

1. 创建 ViewModel

```
class ShareDataModel: ViewModel() {
    val liveData = MutableLiveData<String>()
    var total = 2000L
    init {
        /**
         * 实现倒计时，一秒钟倒计时一次
         */
        val countDownTimer = object :CountDownTimer(1000 * total, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                liveData.postValue("剩余倒计时时间 ${--total}")
            }
            override fun onFinish() {
                logEE("倒计时完成")
            }
        }
        countDownTimer.start()
    }
}
```

2. 创建 FragmentA

```
class FragmentA : Fragment() {
    private val model: ShareDataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false).apply {
            model.liveData.observe(viewLifecycleOwner,
                { value -> findViewById<TextView>(R.id.tv_aresult).text = value })
        }
    }
}
```

3. 创建 FragmentB

```
class FragmentB : Fragment() {
    private val model: ShareDataModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false).apply {
            model.liveData.observe(viewLifecycleOwner,
                { value -> findViewById<TextView>(R.id.tv_bresult).text = value })
        }
    }
}
```

4. 展示结果

![](https://files.mdnice.com/user/15648/d3b75b42-2c0a-4040-8493-72bcc62e0f41.gif)

通过 gif 我们发现，即使 replace 后倒计时的数据仍然没有改变，说明我们成功实现了数据共享

## 1.3 关于 viewmodel 的几个知识点

1. viewmodel 绝对不可以引用视图
2. 在 activity 的存在时间内可能会多次走 onCreate 方法，但是我们的 viewmodel 只有 actvity 结束并销毁的情况下才会被回收
3. viewmodel 通过 lifecycle 来观察 activity/fragment 的生命周期

# Javafx音乐播放器

#### 介绍
 这是款由纯java语言开发的在线音乐播放器，当然也支持播放本地的音乐，在本地音乐模块主要采用目前java最主流的音频标记库Jaudiotagger，可解析MP3文件头信息，比如提取内嵌到音频文件内的音乐封面，也可以将音乐封面写入到音频文件内，支持的音频文件主要为MP3，当然也支持wav，包括嵌入和链接图像支持。可读取音频文件头信息里的歌曲名，专辑、歌手名等元信息。在线模块由爬虫技术完成，主要依赖的是jsoup库，通过对网易云api接口发送请求后解析json数据拿到歌曲元信息再进行解析播放，解析json数据依赖阿里巴巴开发的fastjson库。javafx 第三方ui库jfoenix，toasterfx。其余功能：碟片旋转，歌词滚动特效，歌词同步，最小化精简模式，桌面歌词，切换歌单，下载音乐，最小化系统托盘，少量Material Design风格（依赖jfoenix完成）等。

#### 软件架构
（Jdk15.0.1）or Jdk 1.8_201（需要更换成jfoenix8.0.8版本），由于使用了动画，会出现大量的内存碎片，推荐使用jdk14或者jdk15,不是很推荐用jdk8。

#### 注意事项
  毕竟是java编写的图形界面，而且加了不少动画，内存多多少少不会像c那样低，不过在代码编写方式上，已经很注意对象的创建，大量的对象都是复用的。监听器对象也是复用的。经过测试，jdk15为例：最小内存129mb，最大内存达到了380mb，jdk8内存消耗更多，不以这个作为参照。

#### 使用说明

1.  发现音乐的歌单是随机获取的，可以点击发现音乐栏右边的按钮更新发现音乐栏的内容，这个操作会发送一条https请求。

![主界面](https://images.gitee.com/uploads/images/2020/1228/171946_f2969c9a_4822721.png "3.png")

2.  歌单列表可以是发现音乐栏里的一个，也可以是本地音乐，还可以是搜索结果后的，如果要加载本地音乐，点击本地音乐栏右边的文件夹按钮，打开本地音乐文件夹，一般这个文件夹都是相对于程序所在路径，在LocalMusic/Muisc文件夹内放音乐文件重新点击本地音乐栏就能加载本地音乐，如果需要歌词，请在LocalMusic/Lrc文件夹下放.lrc文件，注意，下载当前音乐会把音乐文件下载到LocalMusic文件夹内，lrc和音乐文件会放到对应的文件夹。表格右键会弹出菜单，比如你喜欢当前播放的专辑封面，可以点击菜单栏复制到剪切板。

![歌单列表](https://images.gitee.com/uploads/images/2020/1230/105954_aacdb2a9_4822721.png "4.png")

3.  歌词是同步的，支持桌面歌词，让你在写代码听音乐的时候也能看到歌词。精简模式下可方便切换歌曲，播放暂停歌曲和调出or隐藏桌面歌词

![歌词同步](https://images.gitee.com/uploads/images/2020/1230/110229_49ddf642_4822721.png "6.png")

4. 新功能：
   拖拽：比如你喜欢歌单封面，你可以选择右键弹出菜单复制链接或者直接拖拽图片出来，鼠标松开即可将图片保存到本地。

![拖拽保存图片](https://images.gitee.com/uploads/images/2020/1230/110652_4a12dc57_4822721.png "1.png")
   
   拖拽音乐或者lrc文件将其批量复制到本地音乐文件夹，鼠标必须移动到在红框内才会生效。

![拖拽导入](https://images.gitee.com/uploads/images/2020/1230/110935_02500b2c_4822721.png "2.png")

![批量导入](https://images.gitee.com/uploads/images/2020/1230/110952_de32caa7_4822721.png "3.png")

![右键复制链接](https://images.gitee.com/uploads/images/2020/1230/110813_45a59df7_4822721.png "5.png")

5. 其他

![精简模式](https://images.gitee.com/uploads/images/2020/1230/110334_30100b69_4822721.png "7.png")

![关于](https://images.gitee.com/uploads/images/2020/1230/110302_d234e952_4822721.png "8.png")



#### 说明：
本项目以学习为目的，不做任何商用。

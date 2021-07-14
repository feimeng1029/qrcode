# qrcode

#### 介绍
二维码项目，通过二维码来进行数据传输，解决内外网数据无法互通的问题，仅用于技术研究，不对实际项目组产生版本侵害

#### 软件架构

程序主要分为2部分
1. 内网部分
> 1. 把内网的文件进行文件拆分，拆分大小是2048字节（前4个字节是文件序号，后2044字节是文件体）
> 2. 通过qrcode二维码把2048字节的文件生成二维码
> 3. 通过awt的程序把二维码的图片展示出来，轮询刷新

2.外网部分
> 1. 通过awt中screen截屏功能，制定屏幕的坐标（内网进行窗口化，然后把二维码的awt程序位置固定），进行截图，文件名为template1.jpg
> 2. 通过qrcode解析二维码程序解析template1.jpg图片
> 3. 上面的1,2步骤是重复执行的，直到2步骤解析的图片的数目和内网中文件拆分的数目一直为止
> 4. 用文件合并功能进行文件合并，最终生成目标文件

补丁备注，后续实现：
```
二维码容错解决办法：可以把内网的文件进行base64str转换，然后外网也进行base64转换，如果发现有不一致的，就说明传输有问题，一般是个别的有问题，然后个别的在单独跑就行了。
```

#### 安装教程

1.  本地运行，暂无安装步骤

#### 使用说明

1.  本项目入口共4个main函数
2.  com.arthas.qrcode.main.InsideMain 内网主函数
3.  com.arthas.qrcode.screen.PhotoRotationFrame 二维码动态滚动函数
4.  com.arthas.qrcode.main.ScreenMain 截屏主函数（截图坐标需要自己调整）
5.  com.arthas.qrcode.main.OutsideMain 外网主函数

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)

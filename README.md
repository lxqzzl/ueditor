# ueditor
ueditor前后端分离的demo，方便以后的项目不再花时间去研究ueditor

参考：https://www.cnblogs.com/ocean-sky/p/7132319.html

技术：
后端springboot 2.3.3.RELEASE
前端vue 2.0

上传图片使用的是阿里云的oss服务

使用：
后端：
1.直接讲项目整合到自己的项目中；
2.配置文件application.properties添加配置：

#阿里云keyid
ali.oss.accessKeyId=
#阿里云秘钥
ali.oss.serect=
#阿里云endpoint
ali.oss.endpoint=
#阿里云bucketname
ali.oss.bucketName=
#阿里云文件相对路径
ali.oss.filePath=
#文请求基础地址
ali.oss.fileUrl=https://${ali.oss.bucketName}.${ali.oss.endpoint}/${ali.oss.filePath}

3.将config.json文件放在resources文件夹下，修改其中的文件相关路径问自己的
（注意：不要用txt打开编辑，不然在转换json字符串时会报错）

前端：
1.将static文件夹下的ueditor(这个是ueritor原本的文件)整个移到自己项目的static(vue2.X)或public(vue3.X)下
2.将components文件夹下的ueditor文件夹整个移到自己项目的components文件夹下
3.下载对应的包：npm install
4.修改components/ueditor/中的ueditor.vue中import报的路径，改为自己的
5.修改static/ueditor/ueditor.config.js中的serverUrl，改为自己接口的地址

END

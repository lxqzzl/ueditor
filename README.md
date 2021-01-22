# ueditor
ueditor前后端分离的demo，方便以后的项目不再花时间去研究ueditor

参考：https://www.cnblogs.com/ocean-sky/p/7132319.html

技术：
后端springboot 2.3.3.RELEASE
前端vue 2.0

上传图片使用的是阿里云的oss服务

使用：
**一 后端：**
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

~~3.将config.json文件放在resources文件夹下，修改其中的文件相关路径问自己的
（注意：不要用txt打开编辑，不然在转换json字符串时会报错）~~
现在已经将config.json文件转为实体类

**二 前端：**
1.将static文件夹下的ueditor(这个是ueritor原本的文件)整个移到自己项目的static(vue2.X)或public(vue3.X)下
2.将components文件夹下的ueditor文件夹整个移到自己项目的components文件夹下
3.下载对应的包：npm install
4.修改components/ueditor/中的ueditor.vue中import报的路径，改为自己的
5.修改static/ueditor/ueditor.config.js中的serverUrl，改为自己接口的地址
6.若出现： *$ is not defined* 问题，解决方法如下：
6.1 安装jquery包：*npm install jquery --save*
6.2 配置webpack: 添加 *const webpack = require('webpack')*，
在module.exports中添加：
  plugins: [
    new webpack.ProvidePlugin({
      jQuery: "jquery",
      $: "jquery"
    })
  ],
6.3 在mian.js中导入：*import 'jquery'*
6.4 如果有.eslintrc.js文件，还需要在文件的module.exports中，为env添加一个键值对 jquery: true
7.在需要应用组件的页面中  
7.1 导入组件：*import UEditor from "@/components/ueditor/ueditor.vue"*
components: {
    UEditor
  },
7.2 添加配置数据，在data中添加：
      config: {
            // 可以在此处定义工具栏的内容
            // toolbars: [
            //  ['fullscreen', 'undo', 'redo','|','bold', 'italic', 'underline',
            //  '|','superscript','subscript','|', 'insertorderedlist', 'insertunorderedlist',
            //  '|','fontfamily','fontsize','justifyleft','justifyright','justifycenter','justifyjustify']
            // ],
        autoHeightEnabled: false,
        autoFloatEnabled: true,
        initialContent: '',   // 初始化编辑器的内容,也可以通过textarea/script给值，看官网例子
        autoClearinitialContent: true, // 是否自动清除编辑器初始内容，注意：如果focus属性设置为true,这个也为真，那么编辑器一上来就会触发导致初始化的内容看不到了
        initialFrameWidth: null,
        initialFrameHeight: 450,
        BaseUrl: '',
        UEDITOR_HOME_URL: 'static/ueditor/'
      },
7.3 若需预填入富文本内容，添加：
      mounted () {
        var ue = UE.getEditor('editor');
        // editor准备好之后才可以使用
    	  ue.addListener("ready", function () {
          //在这个地方填入预先写入的值
          ue.setContent("预先写入的值");
        });
      },  
7.4 添加获取内容的方法：
      methods: {
        //获取文档内容
        getContent: function(){
          let content = this.$refs.ueditor.getUEContent();
          console.log(content);
          alert(content);
        }
      }
7.5 只用ueditor标签：
<UEditor :config=config ref="ueditor"></UEditor>

END

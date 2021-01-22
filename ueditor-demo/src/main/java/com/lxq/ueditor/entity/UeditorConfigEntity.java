package com.lxq.ueditor.entity;

import lombok.Data;

/**
 * ueditor 配置信息
 * @author l1
 *
 */

@Data
public class UeditorConfigEntity {
    /* 上传图片配置项 */
	/* 执行上传图片的action名称 */
    private String imageActionName;
    /* 提交的图片表单名称 */	
    private String imageFieldName;
    /* 上传大小限制，单位B */
    private Integer imageMaxSize; 
    /* 上传图片格式显示 */
    private String[] imageAllowFiles; 
    /* 是否压缩图片,默认是true */
    private Boolean imageCompressEnable; 
    /* 图片压缩最长边限制 */
    private Integer imageCompressBorder; 
    /* 插入的图片浮动方式 */
    private String imageInsertAlign; 
    /* 图片访问路径前缀 */
    private String imageUrlPrefix; 
    private String localSavePathPrefix;
    /**
     *  上传保存路径,可以自定义保存路径和文件名格式
     * {filename} 会替换成原文件名,配置这项需要注意中文乱码问
     * {rand:6} 会替换成随机数,后面的数字是随机数的位数 
     * {time} 会替换成时间戳 
     * {yyyy} 会替换成四位年份 
     * {yy} 会替换成两位年份 
     * {mm} 会替换成两位月份 
     * {dd} 会替换成两位日期 
     * {hh} 会替换成两位小时 
     * {ii} 会替换成两位分钟 
     * {ss} 会替换成两位秒 
     * 非法字符 \ : * ? " < > | 
     * 具请体看线上文档: fex.baidu.com/ueditor/#use-format_upload_filename 
     */
    private String imagePathFormat; 

    /* 涂鸦图片上传配置项 */
    /* 执行上传涂鸦的action名称 */
    private String scrawlActionName; 
    /* 提交的图片表单名称 */
    private String scrawlFieldName; 
    /* 上传保存路径,可以自定义保存路径和文件名格式 */
    private String scrawlPathFormat;
    /* 上传大小限制，单位B */
    private Integer scrawlMaxSize; 
    private String scrawlUrlPrefix; 
    /* 图片访问路径前缀 */
    private String scrawlInsertAlign;

    /* 截图工具上传 */
    /* 执行上传截图的action名称 */
    private String snapscreenActionName; 
    /* 上传保存路径,可以自定义保存路径和文件名格式 */
    private String snapscreenPathFormat; 
    /* 图片访问路径前缀 */
    private String snapscreenUrlPrefix; 
    /* 插入的图片浮动方式 */
    private String napscreenInsertAlign; 

    /* 抓取远程图片配置 */
    private String[] catcherLocalDomain;
    /* 执行抓取远程图片的action名称 */
    private String catcherActionName; 
    private String catcherFieldName; /* 提交的图片列表表单名称 */
    private String catcherPathFormat; /* 上传保存路径,可以自定义保存路径和文件名格式 */
    private String catcherUrlPrefix; /* 图片访问路径前缀 */
    private Integer catcherMaxSize; /* 上传大小限制，单位B */
    private String[] catcherAllowFiles; /* 抓取图片格式显示 */
    /*抓取远程图片是否开启,默认true*/
	private Boolean catchRemoteImageEnable;

    /* 上传视频配置 */
	private String videoActionName; /* 执行上传视频的action名称 */
	private String videoFieldName; /* 提交的视频表单名称 */
	private String videoPathFormat; /* 上传保存路径,可以自定义保存路径和文件名格式 */
	private String videoUrlPrefix; /* 视频访问路径前缀 */
    private Integer videoMaxSize; /* 上传大小限制，单位B，默认100MB */
    private String[] videoAllowFiles; /* 上传视频格式显示 */

    /* 上传文件配置 */
    private String fileActionName; /* controller里,执行上传视频的action名称 */
    private String fileFieldName; /* 提交的文件表单名称 */
    private String filePathFormat; /* 上传保存路径,可以自定义保存路径和文件名格式 */
    private String fileUrlPrefix; /* 文件访问路径前缀 */
    private Integer fileMaxSize; /* 上传大小限制，单位B，默认50MB */
    private String[] fileAllowFiles; /* 上传文件格式显示 */

    /* 列出指定目录下的图片 */
    private String imageManagerActionName; /* 执行图片管理的action名称 */
    private String imageManagerListPath; /* 指定要列出图片的目录 */
    private Integer imageManagerListSize; /* 每次列出文件数量 */
    private String imageManagerUrlPrefix; /* 图片访问路径前缀 */
    private String imageManagerInsertAlign; /* 插入的图片浮动方式 */
    private String[]imageManagerAllowFiles; /* 列出的文件类型 */

    /* 列出指定目录下的文件 */
    private String fileManagerActionName; /* 执行文件管理的action名称 */
    private String fileManagerListPath; /* 指定要列出文件的目录 */
    private String fileManagerUrlPrefix; /* 文件访问路径前缀 */
    private Integer fileManagerListSize; /* 每次列出文件数量 */
    private String[] fileManagerAllowFiles;/* 列出的文件类型 */ 
    
    
    public UeditorConfigEntity(String fileTempPath) {
        this.imageActionName = "uploadimage";	
        this.imageFieldName = "upfile";
        this.imageMaxSize = 2048000; 
        this.imageAllowFiles = new String[] {".png", ".jpg", ".jpeg", ".gif", ".bmp"}; 
        this.imageCompressEnable = true; 
        this.imageCompressBorder = 1600; 
        this.imageInsertAlign = "none"; 
        this.imageUrlPrefix = ""; 
        this.localSavePathPrefix = "";
        this.imagePathFormat = fileTempPath+"/{yyyy}{mm}{dd}/{time}{rand:6}"; 

        /* 涂鸦图片上传配置项 */
        /* 执行上传涂鸦的action名称 */
        this.scrawlActionName = "uploadscrawl"; 
        /* 提交的图片表单名称 */
        this.scrawlFieldName = "upfile"; 
        /* 上传保存路径,可以自定义保存路径和文件名格式 */
        this.scrawlPathFormat = fileTempPath+"/{yyyy}{mm}{dd}/{time}{rand:6}";
        /* 上传大小限制，单位B */
        this.scrawlMaxSize = 2048000; 
        this.scrawlUrlPrefix = ""; 
        /* 图片访问路径前缀 */
        this.scrawlInsertAlign = "none";

        /* 截图工具上传 */
        /* 执行上传截图的action名称 */
        this.snapscreenActionName = "uploadimage"; 
        /* 上传保存路径,可以自定义保存路径和文件名格式 */
        this.snapscreenPathFormat = fileTempPath+"/{yyyy}{mm}{dd}/{time}{rand:6}"; 
        /* 图片访问路径前缀 */
        this.snapscreenUrlPrefix = ""; 
        /* 插入的图片浮动方式 */
        this.napscreenInsertAlign = "none"; 

        /* 抓取远程图片配置 */
        this.catcherLocalDomain = new String[] {"127.0.0.1", "localhost", "img.baidu.com"};
        /* 执行抓取远程图片的action名称 */
        this.catcherActionName = "catchimage"; 
        this.catcherFieldName = "source"; /* 提交的图片列表表单名称 */
        this.catcherPathFormat = fileTempPath+"/{yyyy}{mm}{dd}/{time}{rand:6}"; /* 上传保存路径,可以自定义保存路径和文件名格式 */
        this.catcherUrlPrefix = ""; /* 图片访问路径前缀 */
        this.catcherMaxSize = 2048000; /* 上传大小限制，单位B */
        this.catcherAllowFiles = new String[] {".png", ".jpg", ".jpeg", ".gif", ".bmp"}; /* 抓取图片格式显示 */
        /*抓取远程图片是否开启,默认true*/
        this.catchRemoteImageEnable = false;

        /* 上传视频配置 */
        this.videoActionName = "uploadvideo"; /* 执行上传视频的action名称 */
        this.videoFieldName = "upfile"; /* 提交的视频表单名称 */
        this.videoPathFormat = fileTempPath+"/{yyyy}{mm}{dd}/{time}{rand:6}"; /* 上传保存路径,可以自定义保存路径和文件名格式 */
        this.videoUrlPrefix = ""; /* 视频访问路径前缀 */
        this.videoMaxSize = 102400000; /* 上传大小限制，单位B，默认100MB */
        this.videoAllowFiles = new String[] {
            ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
            ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"}; /* 上传视频格式显示 */

        /* 上传文件配置 */
        this.fileActionName = "uploadfile"; /* controller里,执行上传视频的action名称 */
        this.fileFieldName = "upfile"; /* 提交的文件表单名称 */
        this.filePathFormat = fileTempPath+"/{yyyy}{mm}{dd}/{time}{rand:6}"; /* 上传保存路径,可以自定义保存路径和文件名格式 */
        this.fileUrlPrefix = ""; /* 文件访问路径前缀 */
        this.fileMaxSize = 51200000; /* 上传大小限制，单位B，默认50MB */
        this.fileAllowFiles = new String[] {
            ".png", ".jpg", ".jpeg", ".gif", ".bmp",
            ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
            ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
            ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
            ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"
        }; /* 上传文件格式显示 */

        /* 列出指定目录下的图片 */
        this.imageManagerActionName = "listimage"; /* 执行图片管理的action名称 */
        this.imageManagerListPath = fileTempPath+"/"; /* 指定要列出图片的目录 */
        this.imageManagerListSize = 20; /* 每次列出文件数量 */
        this.imageManagerUrlPrefix = ""; /* 图片访问路径前缀 */
        this.imageManagerInsertAlign = "none"; /* 插入的图片浮动方式 */
        this.imageManagerAllowFiles = new String[] {".png", ".jpg", ".jpeg", ".gif", ".bmp"}; /* 列出的文件类型 */

        /* 列出指定目录下的文件 */
        this.fileManagerActionName = "listfile"; /* 执行文件管理的action名称 */
        this.fileManagerListPath = fileTempPath+"/"; /* 指定要列出文件的目录 */
        this.fileManagerUrlPrefix = ""; /* 文件访问路径前缀 */
        this.fileManagerListSize = 20; /* 每次列出文件数量 */
        this.fileManagerAllowFiles = new String[] {
            ".png", ".jpg", ".jpeg", ".gif", ".bmp",
            ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
            ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
            ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
            ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"
        }; /* 列出的文件类型 */
       		
    }
}

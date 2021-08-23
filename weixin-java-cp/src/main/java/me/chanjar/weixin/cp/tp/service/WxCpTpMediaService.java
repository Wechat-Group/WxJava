package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.bean.result.WxMediaDownloadBytesResult;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 *  媒体管理接口.
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxCpTpMediaService {

  /**
   * <pre>
   * 上传多媒体文件.
   * 上传的多媒体文件有格式和大小限制，如下：
   *   图片（image）: 1M，支持JPG格式
   *   语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
   *   视频（video）：10MB，支持MP4格式
   *   缩略图（thumb）：64KB，支持JPG格式
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=上传下载多媒体文件
   * </pre>
   *
   * @param corpId      the corp id
   * @param mediaType   媒体类型, 请看{@link me.chanjar.weixin.common.api.WxConsts}
   * @param fileType    文件类型，请看{@link me.chanjar.weixin.common.api.WxConsts}
   * @param inputStream 输入流，需要调用方控制关闭该输入流
   * @return the wx media upload result
   * @throws WxErrorException the wx error exception
   * @throws IOException      the io exception
   */
  WxMediaUploadResult upload(String corpId, String mediaType, String fileType, InputStream inputStream)
    throws WxErrorException, IOException;

  /**
   * 上传多媒体文件.
   *
   * @param corpId    授权企业的corpid
   * @param mediaType 媒体类型
   * @param file      文件对象
   * @return the wx media upload result
   * @throws WxErrorException 异常信息
   */
  WxMediaUploadResult upload(String corpId, String mediaType, File file) throws WxErrorException;

  /**
   * 上传多媒体文件.
   *
   * @param corpId    the corp id
   * @param mediaType 媒体类型
   * @param bytes     the bytes
   * @param ext       文件名后缀(传一下吧,下载的时候你看到一个没有后缀的文件,你知道是啥东西吗?)
   * @return the wx media upload result
   * @throws WxErrorException the wx error exception
   */
  WxMediaUploadResult upload(String corpId, String mediaType, byte[] bytes, String ext) throws WxErrorException;

  /**
   * <pre>
   * 上传图片.
   * 上传图片得到图片URL，该URL永久有效
   * 返回的图片URL，仅能用于图文消息（mpnews）正文中的图片展示；若用于非企业微信域名下的页面，图片将被屏蔽。
   * 每个企业每天最多可上传100张图片
   * 接口url格式：https://qyapi.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param corpId 授权企业的corpid
   * @param file   上传的文件对象
   * @return 返回图片url string
   * @throws WxErrorException 异常信息
   */
  String uploadImg(String corpId, File file) throws WxErrorException;

  /**
   * <pre>
   * 上传图片.
   * 上传图片得到图片URL，该URL永久有效
   * 返回的图片URL，仅能用于图文消息（mpnews）正文中的图片展示；若用于非企业微信域名下的页面，图片将被屏蔽。
   * 每个企业每天最多可上传100张图片
   * 接口url格式：https://qyapi.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param corpId 授权企业的corpid
   * @param bytes  the bytes
   * @param ext    文件名后缀(传一下吧,下载的时候你看到一个没有后缀的文件,你知道是啥东西吗?)
   * @return 返回图片url string
   * @throws WxErrorException 异常信息
   */
  String uploadImg(String corpId, byte[] bytes, String ext) throws WxErrorException;

  /**
   * <pre>
   * 下载多媒体文件.
   * 根据微信文档，视频文件下载不了，会返回null
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=上传下载多媒体文件
   * </pre>
   *
   * @param corpId  the corp id
   * @param mediaId 媒体id
   * @return 保存到本地的临时文件 file
   * @throws WxErrorException the wx error exception
   */
  WxMediaDownloadBytesResult download(String corpId, String mediaId) throws WxErrorException;

  /**
   * <pre>
   * 获取高清语音素材.
   * 可以使用本接口获取从JSSDK的uploadVoice接口上传的临时语音素材，格式为speex，16K采样率。该音频比上文的临时素材获取接口（格式为amr，8K采样率）更加清晰，适合用作语音识别等对音质要求较高的业务。
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/media/get/jssdk?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
   * 仅企业微信2.4及以上版本支持。
   * 文档地址：https://work.weixin.qq.com/api/doc#90000/90135/90255
   * </pre>
   *
   * @param corpId  the corp id
   * @param mediaId 媒体id
   * @return 保存到本地的临时文件 jssdk file
   * @throws WxErrorException the wx error exception
   */
  WxMediaDownloadBytesResult getJssdkFile(String corpId, String mediaId) throws WxErrorException;
}

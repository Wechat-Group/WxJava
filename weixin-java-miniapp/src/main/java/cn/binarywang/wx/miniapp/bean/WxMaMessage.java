package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.xml.XmlBeanUtil;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@XStreamAlias("xml")
@Data
public class WxMaMessage implements Serializable {
  private static final long serialVersionUID = -3586245291677274914L;

  @SerializedName("Encrypt")
  @XStreamAlias("Encrypt")
  private String encrypt;

  @SerializedName("ToUserName")
  @XStreamAlias("ToUserName")
  private String toUser;

  @SerializedName("FromUserName")
  @XStreamAlias("FromUserName")
  private String fromUser;

  @SerializedName("CreateTime")
  @XStreamAlias("CreateTime")
  private Integer createTime;

  @SerializedName("MsgType")
  @XStreamAlias("MsgType")
  private String msgType;

  @SerializedName("MsgDataFormat")
  @XStreamAlias("MsgDataFormat")
  private String msgDataFormat;

  @SerializedName("Content")
  @XStreamAlias("Content")
  private String content;

  @SerializedName("MsgId")
  @XStreamAlias("MsgId")
  private Long msgId;

  @SerializedName("PicUrl")
  @XStreamAlias("PicUrl")
  private String picUrl;

  @SerializedName("MediaId")
  @XStreamAlias("MediaId")
  private String mediaId;

  @SerializedName("Event")
  @XStreamAlias("Event")
  private String event;

  @SerializedName("Title")
  @XStreamAlias("Title")
  private String title;

  @SerializedName("AppId")
  @XStreamAlias("AppId")
  private String appId;

  @SerializedName("PagePath")
  @XStreamAlias("PagePath")
  private String pagePath;

  @SerializedName("ThumbUrl")
  @XStreamAlias("ThumbUrl")
  private String thumbUrl;

  @SerializedName("ThumbMediaId")
  @XStreamAlias("ThumbMediaId")
  private String thumbMediaId;

  @SerializedName("SessionFrom")
  @XStreamAlias("SessionFrom")
  private String sessionFrom;

  /**
   * 以下是异步校验图片/音频是否含有违法违规内容的异步检测结果推送报文中的参数
   */
  @SerializedName("isrisky")
  @XStreamAlias("isrisky")
  private String isRisky;

  @SerializedName("extra_info_json")
  @XStreamAlias("extra_info_json")
  private String extraInfoJson;

  @SerializedName("appid")
  @XStreamAlias("appid")
  private String appid;

  @SerializedName("trace_id")
  @XStreamAlias("trace_id")
  private String traceId;

  @SerializedName("status_code")
  @XStreamAlias("status_code")
  private String statusCode;

  @SerializedName("Scene")
  @XStreamAlias("Scene")
  private Integer scene;

  @SerializedName("Query")
  @XStreamAlias("Query")
  private String query;

  public static WxMaMessage fromXml(String xml) {
    return XmlBeanUtil.toBean(xml,WxMaMessage.class);
  }

  public static WxMaMessage fromXml(InputStream is) {
    return XmlBeanUtil.toBean(is,WxMaMessage.class);
  }

  /**
   * 从加密字符串转换.
   *
   * @param encryptedXml 密文
   * @param wxMaConfig   配置存储器对象
   * @param timestamp    时间戳
   * @param nonce        随机串
   * @param msgSignature 签名串
   */
  public static WxMaMessage fromEncryptedXml(String encryptedXml,
                                             WxMaConfig wxMaConfig, String timestamp, String nonce,
                                             String msgSignature) {
    String plainText = new WxMaCryptUtils(wxMaConfig).decrypt(msgSignature, timestamp, nonce, encryptedXml);
    return fromXml(plainText);
  }

  public static WxMaMessage fromEncryptedXml(InputStream is, WxMaConfig wxMaConfig, String timestamp,
                                             String nonce, String msgSignature) {
    try {
      return fromEncryptedXml(IOUtils.toString(is, StandardCharsets.UTF_8), wxMaConfig,
        timestamp, nonce, msgSignature);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    }
  }

  public static WxMaMessage fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaMessage.class);
  }

  public static WxMaMessage fromEncryptedJson(String encryptedJson, WxMaConfig config) {
    try {
      WxMaMessage encryptedMessage = fromJson(encryptedJson);
      String plainText = new WxMaCryptUtils(config).decrypt(encryptedMessage.getEncrypt());
      return fromJson(plainText);
    } catch (Exception e) {
      throw new WxRuntimeException(e);
    }
  }

  public static WxMaMessage fromEncryptedJson(InputStream inputStream, WxMaConfig config) {
    try {
      return fromEncryptedJson(IOUtils.toString(inputStream, StandardCharsets.UTF_8), config);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return this.toJson();
  }

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

}

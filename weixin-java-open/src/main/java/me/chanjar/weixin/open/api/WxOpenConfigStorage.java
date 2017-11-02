package me.chanjar.weixin.open.api;


import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.open.bean.resp.ComponentAccessTokenResp;
import me.chanjar.weixin.open.bean.resp.PreAuthCodeResp;
import me.chanjar.weixin.open.bean.resp.AuthorizerTokenResp;

import java.io.File;
import java.util.concurrent.locks.Lock;

/**
 * Created by JasonY on 17/10/24.
 */
public interface WxOpenConfigStorage {
  String getComponentVerifyTicket();
  void updateComponentVerifyTicket(String componentVerifyTicket);

  String getComponentAccessToken();
  Lock getComponentAccessTokenLock();
  boolean isComponentAccessTokenExpired();
  void expireComponentAccessToken();
  void updateComponentAccessToken(ComponentAccessTokenResp componentAccessTokenResp);
  void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds);

  Lock getAuthorizerAccessTokenLock();
  String getAuthorizerAccessToken(String appid);
  boolean isAuthorizerAccessTokenExpired(String appid);
  void expireAuthorizerAccessToken(String appid);
  void updateAuthorizerAccessToken(String appid, AuthorizerTokenResp authorizerTokenResp);
  void updateAuthorizerAccessToken(String appid, String authorizerAccessToken, int expiresInSeconds);

  String getJsapiTicket(String appid);
  Lock getJsapiTicketLock();
  boolean isJsapiTicketExpired(String appid);
  void updateJsapiTicket(String appid, String jsapiTicket, int expiresInSeconds);
  void expireJsapiTicket(String appid);

  String getCardApiTicket(String appid);
  Lock getCardApiTicketLock();
  boolean isCardApiTicketExpired(String appid);
  void expireCardApiTicket(String appid);
  void updateCardApiTicket(String appid, String cardApiTicket, int expiresInSeconds);

  String getAppId();

  String getSecret();

  String getToken();

  String getAesKey();

  long getExpiresTime();

  String getHttpProxyHost();

  int getHttpProxyPort();

  String getHttpProxyUsername();

  String getHttpProxyPassword();

  File getTmpDirFile();

  /**
   * http client builder
   *
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

  /**
   * 是否自动刷新token
   */
  boolean autoRefreshToken();

}

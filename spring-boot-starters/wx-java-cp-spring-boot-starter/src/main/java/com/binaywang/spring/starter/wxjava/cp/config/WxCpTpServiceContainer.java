package com.binaywang.spring.starter.wxjava.cp.config;

import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.util.crypto.WxCpTpCryptUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caiqy
 */
public class WxCpTpServiceContainer {
  private Map<String, WxCpTpService> wxCpTpServiceMap = new HashMap<>();

  private Map<String, WxCpTpCryptUtil> wxCpTpCryptUtilMap = new HashMap<>();

  public WxCpTpService getTpService(String suiteId) {
    return wxCpTpServiceMap.get(suiteId);
  }

  public void setWxCpTpServiceMap(Map<String, WxCpTpService> wxCpTpServiceMap) {
    this.wxCpTpServiceMap = wxCpTpServiceMap;
  }

  public WxCpTpCryptUtil getCryptUtil(String suiteId) {
    return wxCpTpCryptUtilMap.computeIfAbsent(suiteId, si -> new WxCpTpCryptUtil(getTpService(si).getWxCpTpConfigStorage()));
  }
}

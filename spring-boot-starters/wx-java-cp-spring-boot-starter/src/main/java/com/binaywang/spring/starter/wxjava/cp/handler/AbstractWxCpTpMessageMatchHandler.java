package com.binaywang.spring.starter.wxjava.cp.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caiqy
 */
@Slf4j
public abstract class AbstractWxCpTpMessageMatchHandler implements WxCpTpMessageMatchHandler {

  @Override
  public String getInfoType() {
    return null;
  }

  @Override
  public String getChangeType() {
    return null;
  }

  @Override
  public String getEventType() {
    return null;
  }

  @Override
  public String getMsgType() {
    return null;
  }

  @Override
  public boolean ignoreMatch() {
    return false;
  }
}

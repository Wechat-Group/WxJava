package com.binarywang.spring.demo.wxjava.cp.handler;

import com.binarywang.spring.starter.wxjava.base.annotation.WxHandler;
import com.binaywang.spring.starter.wxjava.cp.handler.AbstractWxCpTpMessageMatchHandler;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.bean.message.WxCpTpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.constant.WxCpTpConsts;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;

import java.util.Map;

/**
 * @author caiqy
 */
@WxHandler
public class SuiteTicketHandler extends AbstractWxCpTpMessageMatchHandler {

  @Override
  public String getInfoType() {
    return WxCpTpConsts.InfoType.SUITE_TICKET;
  }

  @Override
  public WxCpXmlOutMessage handle(WxCpTpXmlMessage wxMessage, Map<String, Object> context, WxCpTpService wxCpTpService, WxSessionManager sessionManager) throws WxErrorException {
    wxCpTpService.setSuiteTicket(wxMessage.getSuiteTicket());
    return null;
  }
}

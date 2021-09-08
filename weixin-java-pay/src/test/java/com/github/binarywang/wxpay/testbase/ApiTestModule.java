package com.github.binarywang.wxpay.testbase;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.xml.XmlBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type Api test module.
 */
public class ApiTestModule implements Module {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private static final String TEST_CONFIG_XML = "test-config.xml";

  @Override
  public void configure(Binder binder) {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
      if (inputStream == null) {
        throw new WxRuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
      }

      XmlWxPayConfig config = XmlBeanUtil.toBean(inputStream, XmlWxPayConfig.class);
      config.setIfSaveApiData(true);
      WxPayService wxService = new WxPayServiceImpl();
      wxService.setConfig(config);

      binder.bind(WxPayService.class).toInstance(wxService);
      binder.bind(WxPayConfig.class).toInstance(config);
    } catch (IOException e) {
      this.log.error(e.getMessage(), e);
    }

  }

}

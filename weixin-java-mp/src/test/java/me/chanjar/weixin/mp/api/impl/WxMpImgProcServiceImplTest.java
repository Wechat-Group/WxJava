package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.mp.api.WxMpImgProcService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConstants;
import me.chanjar.weixin.mp.bean.imgproc.WxMpImgProcQrCodeResult;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMpImgProcServiceImplTest {
  @Inject
  private WxMpService mpService;

  @Test
  public void testQrCode() throws WxErrorException {
    final WxMpImgProcQrCodeResult result = this.mpService.getImgProcService().qrCode("https://gitee.com/binary/weixin-java-tools/raw/master/images/qrcodes/mp.png");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  @Test
  public void testQrCode2() throws Exception {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream("mm.jpeg");
    File tempFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), TestConstants.FILE_JPG);
    final WxMpImgProcQrCodeResult result = this.mpService.getImgProcService().qrCode(tempFile);
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  public static class mockTest {
    private WxMpService wxService = mock(WxMpService.class);

    @Test
    public void testQrCode() throws Exception {
      String returnJson = "{\n" +
        "    \"errcode\": 0, \n" +
        "    \"errmsg\": \"ok\", \n" +
        "    \"code_results\": [\n" +
        "        {\n" +
        "            \"type_name\": \"QR_CODE\", \n" +
        "            \"data\": \"https://www.qq.com\", \n" +
        "            \"pos\": {\n" +
        "                \"left_top\": {\n" +
        "                    \"x\": 585, \n" +
        "                    \"y\": 378\n" +
        "                }, \n" +
        "                \"right_top\": {\n" +
        "                    \"x\": 828, \n" +
        "                    \"y\": 378\n" +
        "                }, \n" +
        "                \"right_bottom\": {\n" +
        "                    \"x\": 828, \n" +
        "                    \"y\": 618\n" +
        "                }, \n" +
        "                \"left_bottom\": {\n" +
        "                    \"x\": 585, \n" +
        "                    \"y\": 618\n" +
        "                }\n" +
        "            }\n" +
        "        }, \n" +
        "        {\n" +
        "            \"type_name\": \"QR_CODE\", \n" +
        "            \"data\": \"https://mp.weixin.qq.com\", \n" +
        "            \"pos\": {\n" +
        "                \"left_top\": {\n" +
        "                    \"x\": 185, \n" +
        "                    \"y\": 142\n" +
        "                }, \n" +
        "                \"right_top\": {\n" +
        "                    \"x\": 396, \n" +
        "                    \"y\": 142\n" +
        "                }, \n" +
        "                \"right_bottom\": {\n" +
        "                    \"x\": 396, \n" +
        "                    \"y\": 353\n" +
        "                }, \n" +
        "                \"left_bottom\": {\n" +
        "                    \"x\": 185, \n" +
        "                    \"y\": 353\n" +
        "                }\n" +
        "            }\n" +
        "        }, \n" +
        "        {\n" +
        "            \"type_name\": \"EAN_13\", \n" +
        "            \"data\": \"5906789678957\"\n" +
        "        }, \n" +
        "        {\n" +
        "            \"type_name\": \"CODE_128\", \n" +
        "            \"data\": \"50090500019191\"\n" +
        "        }\n" +
        "    ], \n" +
        "    \"img_size\": {\n" +
        "        \"w\": 1000, \n" +
        "        \"h\": 900\n" +
        "    }\n" +
        "}";
      when(wxService.get(anyString(), anyString())).thenReturn(returnJson);
      final WxMpImgProcService wxMpImgProcService = new WxMpImgProcServiceImpl(wxService);
      final WxMpImgProcQrCodeResult result = wxMpImgProcService.qrCode("abc");
      assertThat(result).isNotNull();
      System.out.println(result);
    }
  }
}

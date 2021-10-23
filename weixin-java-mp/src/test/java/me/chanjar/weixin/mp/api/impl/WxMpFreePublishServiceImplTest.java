package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.freepublish.WxMpFreePublishInfo;
import me.chanjar.weixin.mp.bean.freepublish.WxMpFreePublishList;
import me.chanjar.weixin.mp.bean.freepublish.WxMpFreePublishStatus;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 发布能力-单元测试.
 *
 * @author dragon
 * @date 2021-10-23
 */
@Guice(modules = ApiTestModule.class)
public class WxMpFreePublishServiceImplTest {

  /**
   * 新增草稿后返回的id，发布需要使用
   */
  final String mediaId = "HKVdzjkDfooMqBqJtvSs2EEeRAJaM33gJgkii_JDOHg";

  /**
   * 发布后的id，后续查询等需要使用
   */
  final String publishId = "2650177669";

  /**
   * 图文的 article_id，后续查询图文详情、删除发布内容 需要使用
   * 要根据 publishId 来获取 article_id
   * @see this.testGetPushStatus
   */
  final String articleId = "zjMKVd1g66BkEkpetwml4ElbDdniE8JeI2Ec324Sjqg";

  @Inject
  protected WxMpService wxService;

  @Test
  public void testSubmit() throws WxErrorException {
    String submit = this.wxService.getFreePublishService().submit(mediaId);
    assertThat(submit).isNotBlank();
    // 【响应数据】：{"errcode":0,"errmsg":"ok","publish_id":2650177668}
  }

  @Test
  public void testGetPushStatus() throws WxErrorException {
    WxMpFreePublishStatus pushStatus = this.wxService.getFreePublishService().getPushStatus(publishId);
    assertThat(pushStatus).isNotNull();
    // 【响应数据】：{"publish_id":2650177668,"publish_status":0,"article_id":"zjMKVd1g66BkEkpetwml4J-4gNf4I1nsh-B-r_inemw",
    // "article_detail":{"count":1,"item":
    // [{"idx":1,"article_url":
    // "https://mp.weixin.qq.com/s?__biz=MzAwMTE2MzA1xxxxxxxxxx"
    // }]},"fail_idx":[]}
    // article_url -> 已发布内容可被自定义菜单、自动回复、话题引用，也可用于公开传播
  }

  @Test
  public void testGetArticleFromId() throws WxErrorException {
    WxMpFreePublishInfo articleFromId = this.wxService.getFreePublishService().getArticleFromId(articleId);
    assertThat(articleFromId).isNotNull();
    /* 【响应数据】：{"news_item":[{"title":"欢迎你加入啊~ 这是我的第一条文字消息草稿","author":"","digest":"","content":"欢迎你加入啊~ 这是我的第一条文字消息草稿",
    "content_source_url":"","thumb_media_id":"","show_cover_pic":0,"url":"http:\/\/mp.weixin.qq
    .com\/s?__biz=MzAwMTE2MzA1Mg==&mid=2650177668","thumb_url":"","need_open_comment":1,"only_fans_can_comment":1,"is_deleted":false}],
    "create_time":1634961670,"update_time":1634961672}
     */
  }

  @Test
  public void testDelPush() throws WxErrorException {
    Boolean deletePush = this.wxService.getFreePublishService().deletePush(articleId,0);
    // 【响应数据】：{"errcode":0,"errmsg":"ok"}
    assertThat(deletePush).isTrue();
  }

  @Test
  public void testDeletePushAllArticle() throws WxErrorException {
    Boolean deletePush = this.wxService.getFreePublishService().deletePushAllArticle(articleId);
    // 【响应数据】：{"errcode":0,"errmsg":"ok"}
    assertThat(deletePush).isTrue();
  }

  @Test
  public void testListDraft() throws WxErrorException {
    WxMpFreePublishList publicationRecords = this.wxService.getFreePublishService().getPublicationRecords(0, 10);
    /*
    【响应数据】：{"item":[{"media_id":"HKVdzjkDfooMqBqJtvSs2EEeRAJaM33gJgkii_JDxxxx",
    "content":{"news_item":[
    {"title":"test","author":"dragon","digest":"test01234567","content":"<p>test01234567<\/p><p style=\"text-align: center;\">
    <img class=\"rich_pages wxw-img\" data-src=\"https:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/0Q",
    "content_source_url":"","thumb_media_id":"HKVdzjkDfooMqBqJtvSs2PBtCY3WYpWAiBbqwTHQ","show_cover_pic":0,
    "url":"http:\/\/mp.weixin.qq.com\/s?__biz=Mxxx",
    "thumb_url":"http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/0QSAUfxxxxx",
    "need_open_comment":1,"only_fans_can_comment":1}
    ],"create_time":1634866791,"update_time":1634866825}
    ,"update_time":1634866825}],
    "total_count":1,"item_count":1}
    */
    assertThat(publicationRecords).isNotNull();
  }

}


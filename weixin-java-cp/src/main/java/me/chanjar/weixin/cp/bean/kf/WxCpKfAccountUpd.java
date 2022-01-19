package me.chanjar.weixin.cp.bean.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 修改客服帐号-请求参数
 *
 * @author Fu
 * @date 2022/1/19 19:10
 */
@NoArgsConstructor
@Data
public class WxCpKfAccountUpd implements Serializable {

  private static final long serialVersionUID = -900712046553752529L;

  /**
   * 要修改的客服帐号ID。
   * 不多于64字节
   */
  @JsonProperty("open_kfid")
  private String openKfid;

  /**
   * 新的客服名称，如不需要修改可不填。
   * 不多于16个字符
   */
  @JsonProperty("name")
  private String name;

  /**
   * 新的客服头像临时素材，如不需要修改可不填。可以调用上传临时素材接口获取。
   * 不多于128个字节
   */
  @JsonProperty("media_id")
  private String mediaId;
}

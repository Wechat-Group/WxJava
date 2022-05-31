package me.chanjar.weixin.cp.bean.school.health;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取健康上报使用统计.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpGetReportJobids extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("ending")
  private Integer ending;

  @SerializedName("jobids")
  private List<String> jobIds;

  public static WxCpGetReportJobids fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetReportJobids.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

package tlk.nexus_core.utils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"status", "data"})
public class SuccessResponse {
  private String status = "success";
  private Object data;

  public SuccessResponse(Object data) {
    this.data = data;
  }
}

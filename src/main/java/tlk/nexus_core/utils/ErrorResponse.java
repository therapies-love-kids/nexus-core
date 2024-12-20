package tlk.nexus_core.utils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"status", "message"})
public class ErrorResponse {
  private String status = "error";
  private String message;

  public ErrorResponse(String message) {
    this.message = message;
  }
}

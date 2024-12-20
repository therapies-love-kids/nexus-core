package tlk.nexus_core.models.embeddables;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ContatoEmbeddable {
  
  @Schema(description = "Telefone", example = "1234-5678")
  @Column(length = 16)
  private String telefone;

  @Schema(description = "Celular", example = "1234-5678")
  @Column(length = 16)
  private String celular;

  @Schema(description = "Email", example = "qo9ZD@example.com")
  @Column(length = 64)
  private String email;

}

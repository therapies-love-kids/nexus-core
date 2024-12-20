package tlk.nexus_core.models.embeddables;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EnderecoEmbeddable {
  
  @Schema(description = "CEP", example = "50000000")
  @Column(length = 8)
  private String cep;
  
  @Schema(description = "UF", example = "GO")
  @Column(length = 2)
  private String uf;

  @Schema(description = "Cidade", example = "Goianéia")
  @Column(length = 32)
  private String cidade;

  @Schema(description = "Bairro", example = "Centro")
  @Column(length = 32)
  private String bairro;
  
  @Schema(description = "Logradouro", example = "Avenida Brasil")
  @Column(length = 64)
  private String logradouro;
  
  @Schema(description = "Numero", example = "123")
  @Column(length = 8)
  private String numero;

  @Schema(description = "Complemento", example = "Casa")
  @Column(length = 64)
  private String complemento;
  
}

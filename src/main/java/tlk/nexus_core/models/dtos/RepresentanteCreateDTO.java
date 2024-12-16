package tlk.nexus_core.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RepresentanteCreateDTO {
  
  @Schema(description = "Nome completo do representante", example = "Carlos Oliveira", required = true)
  @NotBlank(message = "O nome do representante deve ser informado")
  @JsonProperty("nome")
  private String nome;

  @Schema(description = "Estado civil do representante", example = "Solteiro", required = true)
  @NotBlank(message = "O estado civil do representante deve ser informado")
  @JsonProperty("estado_civil")
  private String estadoCivil;

  @Schema(description = "CPF do representante", example = "12345678909", required = true)
  @NotBlank(message = "O CPF do representante deve ser informado")
  @JsonProperty("cpf")
  private String cpf;

  @Schema(description = "RG do representante", example = "MG1234567")
  @JsonProperty("rg")
  private String rg;

  @Schema(description = "Contatos do representante", example = "carlos@example.com, (31) 98765-4321")
  @JsonProperty("contatos")
  private String contatos;

}

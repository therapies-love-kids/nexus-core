package tlk.nexus_core.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RepresentanteUpdateDTO {

  @Schema(description = "Indica se o paciente está ativo", example = "true")
  @JsonProperty("ativo")
  private Boolean ativo;
  
  @Schema(description = "Nome completo do representante", example = "Carlos Oliveira")
  @JsonProperty("nome")
  private String nome;

  @Schema(description = "Estado civil do representante", example = "Solteiro")
  @JsonProperty("estado_civil")
  private String estadoCivil;

  @Schema(description = "Profissão do representante", example = "Engenheiro")
  @JsonProperty("profissao")
  private String profissao;

  @Schema(description = "CPF do representante", example = "12345678909")
  @JsonProperty("cpf")
  private String cpf;

  @Schema(description = "RG do representante", example = "MG1234567")
  @JsonProperty("rg")
  private String rg;

  @Schema(description = "Contatos do representante", example = "carlos@example.com, (31) 98765-4321")
  @JsonProperty("contatos")
  private String contatos;

}

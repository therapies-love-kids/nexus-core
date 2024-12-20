package tlk.nexus_core.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import tlk.nexus_core.models.embeddables.ContatosEmbeddable;
import tlk.nexus_core.models.embeddables.EnderecoEmbeddable;

@Data
@JsonPropertyOrder({"nome", "estado_civil", "profissao", "cpf", "rg", "endereco", "contatos"})
public class RepresentanteCreateDTO {

  @Schema(description = "Nome completo do representante", example = "Carlos Oliveira", required = true)
  @NotBlank(message = "O nome do representante deve ser informado")
  @JsonProperty("nome")
  private String nome;

  @Schema(description = "Estado civil do representante", example = "Solteiro", required = true)
  @NotBlank(message = "O estado civil do representante deve ser informado")
  @JsonProperty("estado_civil")
  private String estadoCivil;

  @Schema(description = "Profissão do representante", example = "Engenheiro")
  @NotBlank(message = "A profissão do representante deve ser informada")
  @JsonProperty("profissao")
  private String profissao;

  @Schema(description = "CPF do representante", example = "12345678909", required = true)
  @NotBlank(message = "O CPF do representante deve ser informado")
  @JsonProperty("cpf")
  private String cpf;

  @Schema(description = "RG do representante", example = "MG1234567")
  @JsonProperty("rg")
  private String rg;

  @Embedded
  private EnderecoEmbeddable endereco;

  @Embedded
  private ContatosEmbeddable contatos;

}

package tlk.nexus_core.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import lombok.Data;
import tlk.nexus_core.models.embeddables.ContatosEmbeddable;
import tlk.nexus_core.models.embeddables.EnderecoEmbeddable;

@Data
@JsonPropertyOrder({"ativo", "nome", "estado_civil", "profissao", "cpf", "rg", "endereco", "contatos"})
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

  @Embedded
  private EnderecoEmbeddable endereco;

  @Embedded
  private ContatosEmbeddable contatos;

}

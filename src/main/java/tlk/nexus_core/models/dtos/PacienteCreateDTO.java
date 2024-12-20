package tlk.nexus_core.models.dtos;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import tlk.nexus_core.models.embeddables.EnderecoEmbeddable;

@Data
@JsonPropertyOrder({"unidade", "nome", "nome_curto", "sexo", "data_nascimento", "certidao_nascimento", "cpf", "convenio", "numero_convenio", "endereco"})
public class PacienteCreateDTO {

  @Schema(description = "Unidade do paciente", example = "ANA", required = true)
  @NotBlank(message = "A unidade do paciente deve ser informada")
  @JsonProperty("unidade")
  private String unidade = "";

  @Schema(description = "Nome completo do paciente", example = "João da Silva", required = true)
  @NotBlank(message = "O nome do paciente deve ser informado")
  @JsonProperty("nome")
  private String nome;

  @Schema(description = "Nome curto do paciente (utilizado para identificação)", example = "João")
  @JsonProperty("nome_curto")
  private String nomeCurto;

  @Schema(description = "Sexo do paciente (pode ser 'Masculino' ou 'Feminino')", example = "Masculino", required = true)
  @NotBlank(message = "O sexo do paciente deve ser informado")
  @JsonProperty("sexo")
  private String sexo;

  @Schema(description = "Data de nascimento do paciente", example = "2020-01-01", required = true)
  @NotBlank(message = "A data de nascimento do paciente deve ser informada")
  @JsonProperty("data_nascimento")
  private LocalDate dataNascimento;

  @Schema(description = "Número da certidão de nascimento", example = "1234567890")
  @JsonProperty("certidao_nascimento")
  private String certidaoNascimento;

  @Schema(description = "CPF do paciente", example = "12345678909")
  @JsonProperty("cpf")
  private String cpf;

  @Schema(description = "Convênio do paciente", example = "UNIMED")
  @JsonProperty("convenio")
  private String convenio;

  @Schema(description = "Número do convênio do paciente", example = "1234567890")
  @JsonProperty("numero_convenio")
  private String numeroConvenio;

  @Embedded
  private EnderecoEmbeddable endereco;

  @Schema(description = "Informações extras relevantes sobre o paciente vindas de fora", example = "Doenças crônicas")
  @JsonProperty("anotacoes")
  private String anotacoes;

  @Schema(description = "Informações extras relevantes sobre o paciente vindas de dentro", example = "Observações sobre o paciente")
  @JsonProperty("observacoes")
  private String observacoes;

}

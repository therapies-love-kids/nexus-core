package tlk.nexus_core.models.dtos;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PacienteCreateDTO {

  @Schema(description = "Nome completo do paciente", example = "João da Silva", required = true)
  @NotBlank(message = "O nome do paciente deve ser informado")
  private String nome;

  @Schema(description = "Nome curto do paciente (utilizado para identificação)", example = "João")
  private String nomeCurto;

  @Schema(description = "Sexo do paciente (pode ser 'Masculino' ou 'Feminino')", example = "Masculino", required = true)
  @NotBlank(message = "O sexo do paciente deve ser informado")
  private String sexo;

  @Schema(description = "Data de nascimento do paciente", example = "2020-01-01", required = true)
  @NotBlank(message = "A data de nascimento do paciente deve ser informada")
  private LocalDate dataNascimento;

  @Schema(description = "Local de nascimento do paciente", example = "Anápolis - GO")
  private String localNascimento;

  @Schema(description = "Número da certidão de nascimento", example = "1234567890")
  private String certidaoNascimento;

  @Schema(description = "CPF do paciente", example = "12345678909")
  private String cpf;

  @Schema(description = "Convênio do paciente", example = "UNIMED")
  private String convenio;

  @Schema(description = "Número do convênio do paciente", example = "1234567890")
  private String numeroConvenio;

  @Schema(description = "CEP do paciente", example = "50000000")
  private String cep;

  @Schema(description = "Endereço do paciente", example = "Rua A, 123 - Bairro X")
  private String endereco;

  @Schema(description = "Informações extras relevantes sobre o paciente vindas de fora", example = "Doenças crônicas")
  private String anotacoes;

  @Schema(description = "Informações extras relevantes sobre o paciente vindas de dentro", example = "Observações sobre o paciente")
  private String observacoes;

}

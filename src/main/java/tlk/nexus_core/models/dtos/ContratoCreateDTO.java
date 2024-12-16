package tlk.nexus_core.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContratoCreateDTO {
  
  @Schema(description = "Código do paciente", required = true, example = "1")
  @NotBlank(message = "O ID do paciente deve ser informado")
  @JsonProperty("paciente_id")
  private Long pacienteId;
  
  @Schema(description = "Código do representante", required = true, example = "1")
  @NotBlank(message = "O ID do representante deve ser informado")
  @JsonProperty("representante_id")
  private Long representanteId;
  
  @Schema(description = "Indica se o contrato tem convênio", required = true, example = "true")
  @JsonProperty("convenio")
  private Boolean convenio;
}


package tlk.nexus_core.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ContratoUpdateDTO {
  
  @Schema(description = "Código do paciente", example = "1")
  @JsonProperty("paciente_id")
  private Long pacienteId;
  
  @Schema(description = "Código do representante", example = "1")
  @JsonProperty("representante_id")
  private Long representanteId;
  
  @Schema(description = "Indica se o contrato tem convênio", example = "true")
  @JsonProperty("convenio")
  private Boolean convenio;
  
}

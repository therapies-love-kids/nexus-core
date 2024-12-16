package tlk.nexus_core.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VinculoCreateDTO {
  
  @Schema(description = "Código do paciente", required = true, example = "1")
  @NotBlank(message = "O ID do paciente deve ser informado")
  @JsonProperty("paciente_id")
  private Long pacienteId;
  
  @Schema(description = "Código do representante", required = true, example = "1")
  @NotBlank(message = "O ID do representante deve ser informado")
  @JsonProperty("representante_id")
  private Long representanteId;
  
  @Schema(description = "Indica o tipo de vínculo (Mãe, Pai ou Outro)", required = true, example = "Mãe")
  @NotBlank(message = "O tipo de vínculo deve ser informado")
  @JsonProperty("tipo")
  private String tipo;

}


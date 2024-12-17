package tlk.nexus_core.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VinculoUpdateDTO {
  
  @Schema(description = "Indica se o vinculo está ativo", example = "true")
  @JsonProperty("ativo")
  private Boolean ativo;

  @Schema(description = "Código do paciente", example = "1")
  @JsonProperty("paciente_id")
  private Long pacienteId;
  
  @Schema(description = "Código do representante", example = "1")
  @JsonProperty("representante_id")
  private Long representanteId;
  
  @Schema(description = "Indica o tipo de vínculo (Mãe, Pai ou Outro)", example = "Mãe")
  @JsonProperty("tipo")
  private String tipo;
}


package tlk.nexus_core.models;

import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vinculos")
public class VinculoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("id")
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @JsonProperty("ativo")
  @Column(name = "ativo", nullable = false)
  private Boolean ativo = true;

  @CreationTimestamp
  @JsonProperty("data_hora_criacao")
  @Column(name = "data_hora_criacao", updatable = false, nullable = false)
  private ZonedDateTime dataHoraCriacao;

  @ManyToOne
  @JoinColumn(name = "paciente_id", nullable = false)
  private PacienteModel paciente;

  @ManyToOne
  @JoinColumn(name = "representante_id", nullable = false)
  private RepresentanteModel representante;

  @JsonProperty("tipo")
  @Column(name = "tipo", nullable = false, length = 8)
  private String tipo;

}

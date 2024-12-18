package tlk.nexus_core.models;

import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "representantes")
public class RepresentanteModel {

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

  @JsonProperty("nome")
  @Column(name = "nome", nullable = false, length = 64)
  private String nome;

  @JsonProperty("estado_civil")
  @Column(name = "estado_civil", nullable = false, length = 16)
  private String estadoCivil;

  @JsonProperty("cpf")
  @Column(name = "cpf", unique = true, nullable = false, length = 16)
  private String cpf;

  @JsonProperty("rg")
  @Column(name = "rg", length = 32)
  private String rg;

  @JsonProperty("contatos")
  @Column(name = "contatos", length = 128)
  private String contatos;

  @ManyToMany
  @JsonIgnore
  @JoinTable(
    name = "vinculos",
    joinColumns = @JoinColumn(name = "paciente_id"),
    inverseJoinColumns = @JoinColumn(name = "representante_id")
  )
  private List<PacienteModel> pacientesVinculos;

  @ManyToMany
  @JsonIgnore
  @JoinTable(
    name = "contratos",
    joinColumns = @JoinColumn(name = "paciente_id"),
    inverseJoinColumns = @JoinColumn(name = "representante_id")
  )
  private List<PacienteModel> pacientesContratos;

}

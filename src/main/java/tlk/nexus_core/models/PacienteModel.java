package tlk.nexus_core.models;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import tlk.nexus_core.models.embeddables.EnderecoEmbeddable;

@Data
@Entity
@Table(name = "pacientes")
public class PacienteModel {

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

  @JsonProperty("codigo")
  @Column(name = "codigo", unique = true, nullable = false, updatable = false, length = 16)
  private String codigo = "";

  @NotBlank
  @JsonProperty("nome")
  @Column(name = "nome", nullable = false, length = 64)
  private String nome;

  @JsonProperty("nome_curto")
  @Column(name = "nome_curto", unique = true, length = 32)
  private String nomeCurto;

  @NotBlank
  @JsonProperty("sexo")
  @Column(name = "sexo", nullable = false, length = 16)
  private String sexo;

  @NotBlank
  @JsonProperty("data_nascimento")
  @Column(name = "data_nascimento", nullable = false)
  private LocalDate dataNascimento;

  @JsonProperty("certidao_nascimento")
  @Column(name = "certidao_nascimento", length = 32)
  private String certidaoNascimento;

  @JsonProperty("cpf")
  @Column(name = "cpf", unique = true, length = 16)
  private String cpf;

  @JsonProperty("convenio")
  @Column(name = "convenio", length = 32)
  private String convenio;

  @JsonProperty("numero_convenio")
  @Column(name = "numero_convenio", length = 32)
  private String numeroConvenio;

  @Embedded
  private EnderecoEmbeddable endereco;

  @JsonProperty("anotacoes")
  @Column(name = "anotacoes", columnDefinition = "TEXT")
  private String anotacoes;

  @JsonProperty("observacoes")
  @Column(name = "observacoes", columnDefinition = "TEXT")
  private String observacoes;

  @ManyToMany
  @JsonIgnore
  @JoinTable(
    name = "vinculos",
    joinColumns = @JoinColumn(name = "paciente_id"),
    inverseJoinColumns = @JoinColumn(name = "representante_id")
  )
  private List<RepresentanteModel> representantesVinculos;

  @ManyToMany
  @JsonIgnore
  @JoinTable(
    name = "contratos",
    joinColumns = @JoinColumn(name = "paciente_id"),
    inverseJoinColumns = @JoinColumn(name = "representante_id")
  )
  private List<RepresentanteModel> representantesContratos;

}

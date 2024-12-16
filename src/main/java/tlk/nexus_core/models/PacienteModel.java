package tlk.nexus_core.models;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "pacientes")
public class PacienteModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("id")
  @Column(name = "id", updatable = false, nullable = false)
  private Integer id;

  @JsonProperty("ativo")
  @Column(name = "ativo", nullable = false)
  private Boolean ativo = true;

  @CreationTimestamp
  @JsonProperty("data_hora_criacao")
  @Column(name = "data_hora_criacao", updatable = false, nullable = false)
  private ZonedDateTime dataHoraCriacao;

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

  @JsonProperty("local_nascimento")
  @Column(name = "local_nascimento", length = 64)
  private String localNascimento;

  @JsonProperty("certidao_nascimento")
  @Column(name = "certidao_nascimento", unique = true, length = 32)
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

  @JsonProperty("cep")
  @Column(name = "cep", length = 8)
  private String cep;

  @JsonProperty("endereco")
  @Column(name = "endereco", length = 128)
  private String endereco;

  @JsonProperty("anotacoes")
  @Column(name = "anotacoes", columnDefinition = "TEXT")
  private String anotacoes;

  @JsonProperty("observacoes")
  @Column(name = "observacoes", columnDefinition = "TEXT")
  private String observacoes;

}
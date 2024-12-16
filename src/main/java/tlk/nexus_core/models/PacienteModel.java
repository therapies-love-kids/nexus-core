package tlk.nexus_core.models;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
  @Column(name = "id", updatable = false, nullable = false)
  private Integer id;

  @Column(name = "ativo", nullable = false)
  private Boolean ativo = true;

  @CreationTimestamp
  @Column(name = "data_hora_criacao", updatable = false, nullable = false)
  private ZonedDateTime dataHoraCriacao;

  @NotBlank
  @Column(name = "nome", nullable = false, length = 64)
  private String nome;

  @Column(name = "nome_curto", unique = true, length = 32)
  private String nomeCurto;

  @NotBlank
  @Column(name = "sexo", nullable = false, length = 16)
  private String sexo;

  @NotBlank
  @Column(name = "data_nascimento", nullable = false)
  private LocalDate dataNascimento;

  @Column(name = "local_nascimento", length = 64)
  private String localNascimento;

  @Column(name = "certidao_nascimento", unique = true, length = 32)
  private String certidaoNascimento;

  @Column(name = "cpf", unique = true, length = 16)
  private String cpf;

  @Column(name = "convenio", length = 32)
  private String convenio;

  @Column(name = "numero_convenio", length = 32)
  private String numeroConvenio;

  @Column(name = "cep", length = 8)
  private String cep;

  @Column(name = "endereco", length = 128)
  private String endereco;

  @Column(name = "anotacoes", columnDefinition = "TEXT")
  private String anotacoes;

  @Column(name = "observacoes", columnDefinition = "TEXT")
  private String observacoes;

}

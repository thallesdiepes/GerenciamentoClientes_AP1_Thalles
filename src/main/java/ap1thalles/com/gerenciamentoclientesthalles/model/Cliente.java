package ap1thalles.com.gerenciamentoclientesthalles.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Data
public class Cliente {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Nome: obrigatório, mínimo 3 e máximo 100 caracteres
    @NotNull(message = "O campo nome é obrigatório.")
    @Size(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres.")
    @Column(nullable = false)
    private String nome;

    // Email: obrigatório, formato de email e único
    @NotNull(message = "O campo email é obrigatório.")
    @Email(message = "O campo email deve ser válido.")
    @Size(min = 3, max = 100, message = "O campo email deve ter entre 3 e 100 caracteres.")
    @Column(unique = true, nullable = false)
    private String email;

    // CPF: obrigatório, formato CPF brasileiro e único
    @NotNull(message = "O campo CPF é obrigatório.")
    @CPF(message = "O CPF deve ser válido.")
    @Column(unique = true, nullable = false)
    private String cpf;

    // Data de nascimento: obrigatório, passado e garante que o cliente tenha 18 anos ou mais
    @NotNull(message = "O campo data de nascimento é obrigatório.")
    @Past(message = "A data de nascimento deve ser uma data no passado.")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    // Telefone: opcional, mas segue um padrão específico
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone deve estar no formato (XX) XXXXX-XXXX.")
    @Column
    private String telefone;

    // Adicionando @JsonManagedReference para evitar ciclos infinitos na serialização
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Endereco> enderecos;

    
}


package ap1thalles.com.gerenciamentoclientesthalles.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "O campo rua não pode ser vazio")
    @Size(min = 3, max = 255 , message = "O campo rua tem no mínimo 3 e no máximo 255 caracteres")
    @Column(nullable = false)
    private String rua;

    @NotNull(message = "O campo número não pode ser vazio")
    @Column(nullable = false)
    private String numero;

    @NotNull(message = "O campo bairro não pode ser vazio")
    @Size(min = 3, max = 100, message = "O campo bairro tem no mínimo 3 e no máximo 100 caracteres")
    @Column(nullable = false)
    private String bairro;

    @NotNull(message = "O campo cidade não pode ser vazio")
    @Size(min = 2, max = 100, message = "O campo cidade tem no mínimo 2 e no máximo 100 caracteres")
    @Column(nullable = false)
    private String cidade;

    @NotNull(message = "O campo estado não pode ser vazio")
    @Column(nullable = false)
    private String estado;

    @NotNull(message = "O campo cep não pode ser vazio")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 99999-999")
    @Column(nullable = false)
    private String cep;

    // Adicionando @JsonBackReference para quebrar o ciclo de serialização
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference
    private Cliente cliente;
}

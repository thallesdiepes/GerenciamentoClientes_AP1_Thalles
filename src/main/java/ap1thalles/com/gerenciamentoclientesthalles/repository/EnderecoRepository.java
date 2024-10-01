package ap1thalles.com.gerenciamentoclientesthalles.repository;

import ap1thalles.com.gerenciamentoclientesthalles.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
    List<Endereco> findByClienteId(int clienteId);
}


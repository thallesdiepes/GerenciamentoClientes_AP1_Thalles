package ap1thalles.com.gerenciamentoclientesthalles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ap1thalles.com.gerenciamentoclientesthalles.model.Cliente;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
    // Método para buscar cliente por CPF
    Optional<Cliente> findByCpf(String cpf);
    
    // Método para buscar cliente por email
    Optional<Cliente> findByEmail(String email);
}
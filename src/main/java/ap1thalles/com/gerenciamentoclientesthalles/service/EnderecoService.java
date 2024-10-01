package ap1thalles.com.gerenciamentoclientesthalles.service;

import ap1thalles.com.gerenciamentoclientesthalles.model.Endereco;
import ap1thalles.com.gerenciamentoclientesthalles.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> buscarEnderecosPorClienteId(Integer clienteId) {
        return enderecoRepository.findByClienteId(clienteId);
    }

    public void deletarEndereco(Integer id) {
        enderecoRepository.deleteById(id);
    }
}

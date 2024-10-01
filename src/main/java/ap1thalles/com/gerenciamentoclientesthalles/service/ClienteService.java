package ap1thalles.com.gerenciamentoclientesthalles.service;

import ap1thalles.com.gerenciamentoclientesthalles.model.Cliente;
import ap1thalles.com.gerenciamentoclientesthalles.repository.ClienteRepository;
import ap1thalles.com.gerenciamentoclientesthalles.exception.UniqueConstraintViolationException;
import ap1thalles.com.gerenciamentoclientesthalles.exception.ClienteNaoEncontradoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Método para verificar se o cliente tem 18 anos ou mais
    public boolean isMaiorDeIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears() >= 18;
    }

    public Cliente salvarCliente(Cliente cliente) throws Exception {
        // Verificar se CPF ou email já estão cadastrados
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new UniqueConstraintViolationException("CPF já cadastrado!");
        }
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new UniqueConstraintViolationException("Email já cadastrado!");
        }

        // Verificar se o cliente é maior de idade
        if (!isMaiorDeIdade(cliente.getDataNascimento())) {
            throw new Exception("O cliente deve ter 18 anos ou mais.");
        }

        // Salvar o cliente se todas as validações passarem
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Integer id, Cliente clienteAtualizado) throws Exception {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();

            // Verificar se CPF ou email estão sendo atualizados e se já estão cadastrados
            // para outro cliente
            if (!cliente.getCpf().equals(clienteAtualizado.getCpf())
                    && clienteRepository.findByCpf(clienteAtualizado.getCpf()).isPresent()) {
                throw new UniqueConstraintViolationException("CPF já cadastrado para outro cliente!");
            }
            if (!cliente.getEmail().equals(clienteAtualizado.getEmail())
                    && clienteRepository.findByEmail(clienteAtualizado.getEmail()).isPresent()) {
                throw new UniqueConstraintViolationException("Email já cadastrado para outro cliente!");
            }

            // Verificar se o cliente é maior de idade
            if (!isMaiorDeIdade(cliente.getDataNascimento())) {
                throw new Exception("O cliente deve ter 18 anos ou mais.");
            }

            // Atualizar os campos do cliente
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setCpf(clienteAtualizado.getCpf());
            cliente.setDataNascimento(clienteAtualizado.getDataNascimento());
            cliente.setTelefone(clienteAtualizado.getTelefone());

            return clienteRepository.save(cliente);
        } else {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
    }

    public Optional<Cliente> buscarClientePorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public void deletarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    public List<Cliente> getAllClients() {
        return clienteRepository.findAll();
    }
}


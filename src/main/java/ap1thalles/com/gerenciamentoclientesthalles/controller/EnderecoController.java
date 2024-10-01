package ap1thalles.com.gerenciamentoclientesthalles.controller;

import ap1thalles.com.gerenciamentoclientesthalles.model.Endereco;
import ap1thalles.com.gerenciamentoclientesthalles.repository.ClienteRepository;
import ap1thalles.com.gerenciamentoclientesthalles.repository.EnderecoRepository;
import ap1thalles.com.gerenciamentoclientesthalles.service.EnderecoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Endereco> adicionarEndereco(@Valid @RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.salvarEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Endereco>> buscarEnderecosPorClienteId(@PathVariable Integer clienteId) {
        List<Endereco> enderecos = enderecoService.buscarEnderecosPorClienteId(clienteId);
        return ResponseEntity.ok(enderecos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }
}

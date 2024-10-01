package ap1thalles.com.gerenciamentoclientesthalles.controller;

import ap1thalles.com.gerenciamentoclientesthalles.model.Cliente;
import ap1thalles.com.gerenciamentoclientesthalles.repository.ClienteRepository;
import ap1thalles.com.gerenciamentoclientesthalles.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> adicionarCliente(@Valid @RequestBody Cliente cliente) throws Exception {
        Cliente response = clienteService.salvarCliente(cliente);
        return new ResponseEntity<>(response , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id,
            @Valid @RequestBody Cliente clienteAtualizado) throws Exception {
        Cliente cliente = clienteService.atualizarCliente(id, clienteAtualizado);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable("id") Integer id) {
        return this.clienteRepository.findById(id)
            .map(cliente -> new ResponseEntity<>(cliente , HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
       
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{id}")
    public ResponseEntity deletarCliente(@PathVariable Integer id) {
        Optional<Cliente> optCliente = this.clienteRepository.findById(id);
        if (optCliente.isPresent()==false)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        this.clienteRepository.delete(optCliente.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);    
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAp1(){

        return new ResponseEntity<>(clienteService.getAllClients(), HttpStatus.OK);
    }

}

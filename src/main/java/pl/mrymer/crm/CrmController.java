package pl.mrymer.crm;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CrmController {
    ClientRepository clientRepository;

    public CrmController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @PostMapping("/api/clients")
    void addClient(@RequestBody Client client) {
        client.id = UUID.randomUUID().toString();
        clientRepository.save(client);
    }
    @GetMapping("/api/clients/{id}")
    Client getClient(@PathVariable String id) {
        return clientRepository.findById(id).get();
    }

    @GetMapping("/api/clients")
    List<Client> allClient() {
        return clientRepository.findAll();
    }

    @DeleteMapping("/api/clients/{id}")
    void deleteClient(@PathVariable String id) {
        clientRepository.deleteById(id);
    }
}

package barber_shop_ui.service.impl;

import barber_shop_ui.entity.ClientEntity;
import barber_shop_ui.entity.ModelCadastro;
import barber_shop_ui.repository.IClientRepository;
import barber_shop_ui.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements IClientService {  // Implementando a interface

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private ModelCadastro mensagem;

    @Override
    public ResponseEntity<?> registro(ClientEntity clienteCadastro, String barberError) {
        if (clienteCadastro.getName() == null || clienteCadastro.getName().isEmpty()) {
            mensagem.setMessage("O nome precisa ser preenchido");
            return ResponseEntity.badRequest().body(mensagem);
        }

        if (clienteCadastro.getEmail() == null || clienteCadastro.getEmail().isEmpty()) {
            mensagem.setMessage("O email precisa ser preenchido");
            return ResponseEntity.badRequest().body(mensagem);
        }

        if (clienteCadastro.getPhone() == null || clienteCadastro.getPhone().isEmpty()) {
            mensagem.setMessage("O telefone precisa ser preenchido");
            return ResponseEntity.badRequest().body(mensagem);
        }

        HttpStatus status = barberError.equals("/cadastro") ? HttpStatus.CREATED : HttpStatus.OK;
        return new ResponseEntity<>(clientRepository.save(clienteCadastro), status);
    }

    @Override
    public ClientEntity save(ClientEntity entity) {
        return null;
    }

    @Override
    public ClientEntity update(ClientEntity entity) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
    public Iterable<ClientEntity> listar(){
        return clientRepository.findAll();
    }
}

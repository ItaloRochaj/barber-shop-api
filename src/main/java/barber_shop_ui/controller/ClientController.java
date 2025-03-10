package barber_shop_ui.controller;

import barber_shop_ui.controller.request.SaveClientRequest;
import barber_shop_ui.controller.request.UpdateClientRequest;
import barber_shop_ui.controller.response.ClientDetailResponse;
import barber_shop_ui.controller.response.ListClientResponse;
import barber_shop_ui.controller.response.SaveClientResponse;
import barber_shop_ui.controller.response.UpdateClientResponse;
import barber_shop_ui.entity.ClientEntity;
import barber_shop_ui.mapper.IClientMapper;
import barber_shop_ui.service.IClientService;
import barber_shop_ui.service.impl.ClientService;
import barber_shop_ui.service.query.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private final IClientService service;
    private final IClientQueryService queryService;
    private final IClientMapper mapper;

    @Autowired
    private ClientService serviceShop;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarbarber(@RequestBody ClientEntity uiShop){
        return serviceShop.registro(uiShop,"cadastro");
    }

    @PutMapping("{id}")
    UpdateClientResponse updade(@PathVariable final Long id, @RequestBody @Valid final UpdateClientRequest request) {
        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final Long id) {
        service.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final Long id) {
        var entity = queryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping("/listar")
    public Iterable<ClientEntity> listar(){
        return serviceShop.listar();
    }

}

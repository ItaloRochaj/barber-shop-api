package barber_shop_ui.controller;

import barber_shop_ui.controller.request.SaveClientRequest;
import barber_shop_ui.controller.request.UpdateClientRequest;
import barber_shop_ui.controller.response.ClientDetailResponse;
import barber_shop_ui.controller.response.ListClientResponse;
import barber_shop_ui.controller.response.SaveClientResponse;
import barber_shop_ui.controller.response.UpdateClientResponse;
import barber_shop_ui.mapper.IClientMapper;
import barber_shop_ui.service.IClientService;
import barber_shop_ui.service.query.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private final IClientService service;
    private final IClientQueryService queryService;
    private final IClientMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request) {
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
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

    @GetMapping
    List<ListClientResponse> list() {
        var entities = queryService.list();
        return mapper.toListResponse(entities);
    }
}

package it.service.myservice.mapper;

import it.service.myservice.object.dto.UtenteCreateDTO;
import it.service.myservice.object.dto.UtenteDTO;
import it.service.myservice.object.entity.Utente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrdineMapper.class})
public interface UtenteMapper {

    @Mapping(target = "ordini", ignore = true)
    Utente toEntity(UtenteCreateDTO dto);

    @Mapping(source = "ordini", target = "ordini")
    UtenteDTO toDto(Utente entity);

    List<UtenteDTO> toDtoList(List<Utente> entities);

    void updateEntityFromDto(UtenteCreateDTO dto, @MappingTarget Utente entity);
}
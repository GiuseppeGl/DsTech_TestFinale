package it.service.myservice.mapper;

import it.service.myservice.object.dto.OrdineCreateDTO;
import it.service.myservice.object.dto.OrdineDTO;
import it.service.myservice.object.dto.OrdineUpdateStatoDTO;
import it.service.myservice.object.entity.Ordine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DettaglioOrdineMapper.class})
public interface OrdineMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "data", ignore = true)
    @Mapping(target = "stato", ignore = true)
    @Mapping(target = "totale", ignore = true)
    @Mapping(target = "utente", ignore = true)
    @Mapping(target = "dettagli", ignore = true)
    Ordine toEntity(OrdineCreateDTO dto);

    @Mapping(source = "utente.id", target = "utenteId")
    @Mapping(source = "dettagli", target = "dettagli")
    OrdineDTO toDto(Ordine entity);

    List<OrdineDTO> toDtoList(List<Ordine> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "data", ignore = true)
    @Mapping(target = "utente", ignore = true)
    @Mapping(target = "totale", ignore = true)
    @Mapping(target = "dettagli", ignore = true)
    void updateStato(OrdineUpdateStatoDTO dto, @MappingTarget Ordine entity);
}
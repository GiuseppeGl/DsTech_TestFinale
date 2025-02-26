package it.service.myservice.mapper;

import it.service.myservice.object.dto.DettaglioOrdineCreateDTO;
import it.service.myservice.object.dto.DettaglioOrdineDTO;
import it.service.myservice.object.entity.DettaglioOrdine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DettaglioOrdineMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "prezzoTotale", ignore = true)
    @Mapping(target = "ordine", ignore = true)
    @Mapping(target = "prodotto", ignore = true)
    DettaglioOrdine toEntity(DettaglioOrdineCreateDTO dto);

    @Mapping(source = "prodotto.id", target = "prodottoId")
    @Mapping(source = "prodotto.nome", target = "nomeProdotto")
    DettaglioOrdineDTO toDto(DettaglioOrdine entity);

    List<DettaglioOrdineDTO> toDtoList(List<DettaglioOrdine> entities);
}
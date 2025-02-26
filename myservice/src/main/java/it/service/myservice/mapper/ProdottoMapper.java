package it.service.myservice.mapper;

import it.service.myservice.object.dto.ProdottoDTO;
import it.service.myservice.object.entity.Prodotto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdottoMapper {

    Prodotto toEntity(ProdottoDTO dto);

    ProdottoDTO toDto(Prodotto entity);

    List<ProdottoDTO> toDtoList(List<Prodotto> entities);

    void updateEntityFromDto(ProdottoDTO dto, @MappingTarget Prodotto entity);
}
package com.cinema.tickets.api.mapper;

import com.cinema.tickets.api.dto.FilmeDTO;
import com.cinema.tickets.domain.collection.Filme;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public record FilmeMapper(ModelMapper mapper) {

    public FilmeDTO toDTO(Filme filme) {
        return mapper.map(filme, FilmeDTO.class);
    }

    public Filme toEntity(FilmeDTO filmeDTO) {
        return mapper.map(filmeDTO, Filme.class);
    }

}

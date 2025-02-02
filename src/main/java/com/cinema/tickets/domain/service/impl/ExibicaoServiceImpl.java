package com.cinema.tickets.domain.service.impl;

import com.cinema.tickets.domain.collection.Exibicao;
import com.cinema.tickets.domain.collection.Filme;
import com.cinema.tickets.domain.collection.Horarios;
import com.cinema.tickets.domain.collection.Sala;
import com.cinema.tickets.domain.exception.BusinessException;
import com.cinema.tickets.domain.exception.NotFoundException;
import com.cinema.tickets.domain.repository.ExibicaoRepository;
import com.cinema.tickets.domain.repository.FilmeRepository;
import com.cinema.tickets.domain.repository.SalaRepository;
import com.cinema.tickets.domain.service.ExibicaoService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class ExibicaoServiceImpl implements ExibicaoService {

    final Logger log = Logger.getLogger(ExibicaoServiceImpl.class.getName());

    private final ExibicaoRepository exibicaoRepository;

    private final FilmeRepository filmeRepository;

    private final SalaRepository salaRepository;

    public ExibicaoServiceImpl(ExibicaoRepository exibicaoRepository, FilmeRepository filmeRepository, SalaRepository salaRepository) {
        this.exibicaoRepository = exibicaoRepository;
        this.filmeRepository = filmeRepository;
        this.salaRepository = salaRepository;
    }

    @Override
    public List<Exibicao> getAll() {
        log.info("Listando todas as Exibicao");
        return exibicaoRepository.findAll();
    }

    @Override
    public Exibicao save(Exibicao exibicao) {

        log.info("Salvando Exibicao: " + exibicao.getDataExibicao());


        Optional<Filme> filme = filmeRepository.findByTitulo(exibicao.getFilme().getTitulo());

        if (filme.isEmpty()) {
            log.info("Filme não encontrado");
            throw new NotFoundException("Filme não encontrado");
        } else {
            exibicao.setFilme(filme.get());
        }

        Boolean existeDataExibicao = exibicaoRepository.existsByDataExibicao(exibicao.getDataExibicao());

        if (existeDataExibicao) {
            log.info("Exibicao já cadastrada");
            throw new BusinessException("Data de exibição já cadastrada");
        }

        Optional<Sala> sala = salaRepository.findByNumSala(exibicao.getSala().getNumSala());

        if (sala.isEmpty()) {
            log.info("Sala não encontrada");
            throw new NotFoundException("Sala não encontrada");
        } else {
            exibicao.setSala(sala.get());
        }


        return exibicaoRepository.save(exibicao);
    }


    @Override
    public Optional<Exibicao> getById(String id) {

        Optional<Exibicao> exibicao = exibicaoRepository.findById(id);

        if (exibicao.isEmpty()) {
            log.info("Exibicao não encontrada");
            throw new NotFoundException("Exibicao não encontrada");
        }

        log.info("Exibicao de id " + exibicao.get().getId() + " encontrada");
        return exibicaoRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        Optional<Exibicao> exibicao = exibicaoRepository.findById(id);

        if (exibicao.isEmpty()) {
            log.info("Exibicao não encontrada");
            throw new NotFoundException("Exibicao não encontrada");
        }

        exibicaoRepository.deleteById(id);
        log.info("Exibicao de id " + exibicao.get().getId() + " deletada");
    }

    @Override
    public Exibicao update(Exibicao exibicao) {
        Optional<Exibicao> exibicaoExistente = exibicaoRepository.findById(exibicao.getId());

        log.info("Atualizando Exibicao: " + exibicao.getId());
        if (exibicaoExistente.isEmpty()) {
            log.info("Exibicao não encontrada");
            throw new NotFoundException("Exibicao não encontrada");

        } else {

            log.info("Atualizaçao salva");
            return exibicaoRepository.save(exibicao);
        }
    }

}

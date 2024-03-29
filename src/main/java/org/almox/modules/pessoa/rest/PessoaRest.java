package org.almox.modules.pessoa.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.pessoa.dto.FiltroPessoa;
import org.almox.modules.pessoa.dto.PessoaDTO;
import org.almox.modules.pessoa.dto.PessoaFisicaDTO;
import org.almox.modules.pessoa.dto.PessoaFisicaMapper;
import org.almox.modules.pessoa.dto.PessoaJuridicaDTO;
import org.almox.modules.pessoa.dto.PessoaJuridicaMapper;
import org.almox.modules.pessoa.dto.PessoaMapper;
import org.almox.modules.pessoa.model.Pessoa;
import org.almox.modules.pessoa.model.PessoaFisica;
import org.almox.modules.pessoa.model.PessoaJuridica;
import org.almox.modules.pessoa.model.TipoPessoa;
import org.almox.modules.pessoa.service.PessoaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaRest implements PessoaRestFacade {

    private final PessoaServiceImpl pessoaService;
    private final PessoaFisicaMapper pessoaFisicaMapper;
    private final PessoaJuridicaMapper pessoaJuridicaMapper;
    private final PessoaMapper pessoaMapper;

    public ResponseEntity<RestCollection<PessoaDTO>> buscar(
            TipoPessoa tipo, String nome, String email,
            Optional<Integer> page, Optional<Integer> size, String[] sort
    ) {
        Sort ordenacao = Sort.by(sort);
        Pageable paginacao = criarPaginacao(page, size, ordenacao);
        FiltroPessoa filtro = FiltroPessoa.builder().tipo(tipo).nome(nome).email(email).build();

        Page<PessoaDTO> pessoasPage = pessoaService.buscar(filtro, paginacao).map(pessoaMapper::toDTO);
        return ResponseEntity.ok(RestCollection.fromPage(pessoasPage));
    }

    public ResponseEntity<PessoaDTO> buscarPorId(UUID id) {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        PessoaDTO dto = pessoaMapper.toDTO(pessoa);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<Void> criarPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO) {
        PessoaFisica pessoaCriada = pessoaService.criar(pessoaFisicaMapper.fromDTO(pessoaFisicaDTO));
        URI uriCriado = getUriCriado(pessoaCriada.getId());
        return ResponseEntity.created(uriCriado).build();
    }


    public ResponseEntity<Void> criarPessoaJuridica(PessoaJuridicaDTO dto) {
        PessoaJuridica pessoaCriada = pessoaService.criar(pessoaJuridicaMapper.fromDTO(dto));
        URI uriCriado = getUriCriado(pessoaCriada.getId());
        return ResponseEntity.created(uriCriado).build();
    }

    @Override
    public ResponseEntity<PessoaFisicaDTO> atualizarPessoaFisica(UUID id, PessoaFisicaDTO dto) {
        PessoaFisica pessoaAtualizada = pessoaService.atualizar(id, pessoaFisicaMapper.fromDTO(dto));
        PessoaFisicaDTO pessoaAtualizadaDTO = pessoaFisicaMapper.toDTO(pessoaAtualizada);
        return ResponseEntity.ok(pessoaAtualizadaDTO);
    }

    @Override
    public ResponseEntity<PessoaJuridicaDTO> atualizarPessoaJuridica(UUID id, PessoaJuridicaDTO dto) {
        PessoaJuridica pessoaAtualizada = pessoaService.atualizar(id, pessoaJuridicaMapper.fromDTO(dto));
        PessoaJuridicaDTO pessoaAtualizadaDTO = pessoaJuridicaMapper.toDTO(pessoaAtualizada);
        return ResponseEntity.ok(pessoaAtualizadaDTO);
    }

    @Override
    public ResponseEntity<Void> excluir(UUID id) {
        pessoaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

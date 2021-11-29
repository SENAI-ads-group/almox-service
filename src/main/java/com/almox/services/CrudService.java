package com.almox.services;

import com.almox.exceptions.ApplicationRuntimeException;
import com.almox.model.entidades.Auditavel;
import com.almox.model.entidades.EntidadePadrao;
import com.almox.repositories.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public abstract class CrudService<T extends EntidadePadrao, F> implements ICrudService<T, F> {

    @Autowired
    private UsuarioService usuarioService;

    public T buscarPorId(Long id) {
        return _buscarPorId(id);
    }

    public List<T> buscarTodos(F filtro) {
        return _buscarTodos(filtro);
    }

    public List<T> buscarTodos() {
        return _buscarTodos();
    }

    public Page<T> buscarPaginado() {
        return _buscarPaginado();
    }

    @Transactional
    public T criar(T entidade) {
        if (entidade instanceof Auditavel) { //força os dados de criação do usuário
            var auditavel = (Auditavel) entidade;
            auditavel.setCriadoPor(usuarioService.getCurrentAuditor().orElse(null)); //Não necessário quando o AuditorWare estiver funcionando
            LocalDateTime dataAtual = LocalDateTime.now();
            auditavel.setDataCriacao(dataAtual);
            auditavel.setDataAlteracao(dataAtual);
        }
        return _criar(entidade);
    }

    @Transactional
    public T atualizar(Long id, T entidade) {
        if (entidade instanceof Auditavel) { //força os dados de atualização do usuário
            var auditavel = (Auditavel) entidade;
            auditavel.setDataAlteracao(LocalDateTime.now());
            auditavel.setAlteradoPor(usuarioService.getCurrentAuditor().orElse(null)); //Não necessário quando o AuditorWare estiver funcionando
        }
        return _atualizar(id, entidade);
    }

    @Transactional
    public void excluir(long id) {
        T entidadeEncontrada = buscarPorId(id);
        if (entidadeEncontrada instanceof Auditavel) { //força os dados de atualização do usuário{
            Auditavel auditavel = (Auditavel) entidadeEncontrada;
            auditavel.setDataExclusao(LocalDateTime.now());
            auditavel.setExcluidoPor(usuarioService.getUsuarioLogado());
            getRepository().save(entidadeEncontrada);
        }
        _excluir(entidadeEncontrada);
    }

    protected abstract CrudRepository<T> getRepository();

    protected T _buscarPorId(Long id){throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);}

    protected List<T> _buscarTodos(F filtro){throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);}

    protected List<T> _buscarTodos(){throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);}

    protected Page<T> _buscarPaginado(){throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);}

    protected T _criar(T entidade){throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);}

    protected T _atualizar(Long id, T entidade){
        throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);
    }

    protected void _excluir(T entidade) {
        throw new ApplicationRuntimeException(HttpStatus.NOT_IMPLEMENTED);
    }
}

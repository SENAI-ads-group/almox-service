package com.almox.services;

import com.almox.model.dto.FiltroFornecedorDTO;
import com.almox.model.entidades.Fornecedor;
import org.springframework.stereotype.Service;

@Service
public interface IFornecedorService extends ICrudService<Fornecedor, FiltroFornecedorDTO> {
}
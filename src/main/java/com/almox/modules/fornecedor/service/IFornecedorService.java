package com.almox.modules.fornecedor.service;

import com.almox.modules.common.ICrudService;
import com.almox.modules.fornecedor.model.FiltroFornecedorDTO;
import com.almox.modules.fornecedor.model.Fornecedor;
import org.springframework.stereotype.Service;

@Service
public interface IFornecedorService extends ICrudService<Fornecedor, FiltroFornecedorDTO> {
}
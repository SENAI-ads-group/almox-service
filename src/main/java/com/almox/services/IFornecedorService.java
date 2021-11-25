package com.almox.services;
<<<<<<< HEAD
import com.almox.model.dto.FiltroFornecedorDTO;
import com.almox.model.entidades.Fornecedor;

public interface IFornecedorService extends ICrudService<Fornecedor, FiltroFornecedorDTO> {
}


=======

import com.almox.model.dto.FiltroFornecedorDTO;
import com.almox.model.entidades.Fornecedor;
import org.springframework.stereotype.Service;

@Service
public interface IFornecedorService extends ICrudService<Fornecedor, FiltroFornecedorDTO> {
}
>>>>>>> d06b990... Interligação de funcionalidades

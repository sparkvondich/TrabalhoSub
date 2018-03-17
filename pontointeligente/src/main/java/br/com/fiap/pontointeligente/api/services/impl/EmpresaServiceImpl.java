/**
 * 
 */
package br.com.fiap.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.pontointeligente.api.modelo.Empresa;
import br.com.fiap.pontointeligente.api.repositories.EmpresaRepository;
import br.com.fiap.pontointeligente.api.services.EmpresaService;


@Service
public class EmpresaServiceImpl implements EmpresaService {
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);
	@Autowired
	private EmpresaRepository empresaRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sparkbox.pontointeligente.api.services.EmpresaService#buscarPorCnpj(java.
	 * lang.String)
	 */
	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sparkbox.pontointeligente.api.services.EmpresaService#persistir(net.
	 * sparkbox.pontointeligente.api.modelo.Empresa)
	 */
	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Persistindo empresa: {}", empresa);
		return this.empresaRepository.save(empresa);
	}

}

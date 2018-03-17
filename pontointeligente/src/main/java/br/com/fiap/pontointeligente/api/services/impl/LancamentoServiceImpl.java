/**
 * 
 */
package br.com.fiap.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.pontointeligente.api.modelo.Lancamento;
import br.com.fiap.pontointeligente.api.repositories.LancamentoRepository;
import br.com.fiap.pontointeligente.api.services.LancamentoService;


@Service
public class LancamentoServiceImpl implements LancamentoService {
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);

	@Autowired
	private LancamentoRepository lancamentoRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sparkbox.pontointeligente.api.services.LancamentoService#
	 * buscarPorFuncionarioId(java.lang.Long,
	 * org.springframework.data.domain.PageRequest)
	 */
	@Override
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		log.info("Buscando lançamentos para o funcionário ID {}", funcionarioId);
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sparkbox.pontointeligente.api.services.LancamentoService#buscarPorId(java
	 * .lang.Long)
	 */
	@Override
	@Cacheable("lancamentoPorId")
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando um lançamento pelo ID {}", id);
		return Optional.ofNullable(this.lancamentoRepository.findOne(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sparkbox.pontointeligente.api.services.LancamentoService#persistir(net.
	 * sparkbox.pontointeligente.api.modelo.Lancamento)
	 */
	@Override
	@CachePut("lancamentoPorId")
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo o lançamento: {}", lancamento);
		return this.lancamentoRepository.save(lancamento);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sparkbox.pontointeligente.api.services.LancamentoService#remover(java.
	 * lang.Long)
	 */
	@Override
	public void remover(Long id) {
		log.info("Removendo o lançamento ID {}", id);
		this.lancamentoRepository.delete(id);

	}

}

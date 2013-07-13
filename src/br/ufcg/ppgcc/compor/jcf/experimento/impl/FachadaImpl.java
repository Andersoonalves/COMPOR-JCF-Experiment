package br.ufcg.ppgcc.compor.jcf.experimento.impl;

import java.util.LinkedList;
import java.util.List;

import br.ufcg.ppgcc.compor.jcf.experimento.fachada.Dependente;
import br.ufcg.ppgcc.compor.jcf.experimento.fachada.FachadaExperimento;
import br.ufcg.ppgcc.compor.jcf.experimento.fachada.FontePagadora;
import br.ufcg.ppgcc.compor.jcf.experimento.fachada.GastoDedutivel;
import br.ufcg.ppgcc.compor.jcf.experimento.fachada.GerenteTitular;
import br.ufcg.ppgcc.compor.jcf.experimento.fachada.Pessoa;
import br.ufcg.ppgcc.compor.jcf.experimento.fachada.Resultado;
import br.ufcg.ppgcc.compor.jcf.experimento.fachada.Titular;

public class FachadaImpl implements FachadaExperimento{

	
	private GerenteTitular titular; 
	
	public FachadaImpl (){
		titular = new GerenteTitular(); 
	}
	
	@Override
	public void criarNovoTitular(Titular titular) {
			this.titular.CriarNovoTitular(titular);
	}

	@Override
	public List<Titular> listarTitulares() {
			return this.titular.listarTitulares();
	}

	@Override
	public void criarFontePagadora(Titular titular, FontePagadora fonte) {
			this.titular.criarFontePagadora(titular, fonte);
	}

	@Override
	public Resultado declaracaoCompleta(Titular titular) {
		return this.titular.declaracaoCompleta(titular);	
	}

	@Override
	public void criarDependente(Titular titular, Dependente dependente) {
		this.titular.criarDependente(titular, dependente);
	}

	@Override
	public List<FontePagadora> listarFontes(Titular titular) {
		return this.titular.listarFontes(titular);
	}

	@Override
	public List<Dependente> listarDependentes(Titular titular) {
		return this.titular.listarDependentes (titular);
	}

	@Override
	public void criarGastoDedutivel(Titular titular, Pessoa realizador,
			GastoDedutivel gastoDedutivel) {
		this.titular.criarGastoDedutivel(titular, realizador, gastoDedutivel);
	}

	@Override
	public List<GastoDedutivel> listarGastosDedutiveis(Titular titular,
			Pessoa realizador) {

		return null;
	}

	@Override
	public Resultado relatorioSimplificado(Titular titular) {
	
		return null;
	}

	

	
}

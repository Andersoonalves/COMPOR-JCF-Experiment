package br.ufcg.ppgcc.compor.jcf.experimento.fachada;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.jcf.experimento.util.CalculoImpostoRenda;
import br.ufcg.ppgcc.compor.jcf.experimento.util.Validacao;


public class GerenteTitular {

	private List <Titular> titular; 
	private Map <Titular,List<FontePagadora>> mapFontes;
	private Map <Titular,List<Dependente>> mapDependentes;
	private Map <Pessoa,List<GastoDedutivel>> mapGastos;
	private CalculoImpostoRenda calculoImposto;
	private Validacao validar; 

	public GerenteTitular (){
		titular = new ArrayList<Titular>(); 
		this.mapFontes = new HashMap<Titular, List<FontePagadora>>();
		this.mapDependentes = new HashMap<Titular, List<Dependente>>();
		this.mapGastos = new HashMap<Pessoa, List<GastoDedutivel>>();
		this.calculoImposto = new CalculoImpostoRenda();
		this.validar = new Validacao();
	
	}
	
	 private boolean contensTitular(String tituloEleitoral) {
	        for (Titular t : titular) {
	            if (t.getTituloEleitoral().equalsIgnoreCase(tituloEleitoral)) {
	                return true;
	            }
	        }
	        return false;
	    }
	
	public boolean CriarNovoTitular(Titular t){
		
		if(!validar.obrigatorio(t.getNome())|| !validar.obrigatorio(t.getCpf()) ||
				!validar.cpf(t.getCpf())){
			throw new ExcecaoImpostoDeRenda("Titular sem Nome ou CPF!");
		}
		if (!contensTitular(t.getTituloEleitoral())) {
	       	return titular.add(t);
	   }
			return false;
	}
	public List<Titular> listarTitulares (){
		 List<Titular> titular = new ArrayList<Titular>();
	        for (Titular t : this.titular) {
	                titular.add(t);
	        }
	        return titular;
	}
	
	public void criarFontePagadora (Titular titular, FontePagadora fonte) {
		
		if(mapFontes.get(titular) == null){
			mapFontes.put(titular, new ArrayList<FontePagadora>());
		}
		if( !validar.obrigatorio(fonte.getNome()) || !validar.obrigatorio(fonte.getCpfCnpj()) || !validar.cpfOuCnpj(fonte.getCpfCnpj())){
			throw new ExcecaoImpostoDeRenda("Dependente sem Nome ou CPF!");
		}
		
		mapFontes.get(titular).add(fonte);
	}
	
	public List<FontePagadora> listarFontes(Titular titular) {	
		return mapFontes.get(titular);
		
	}
	
	public void criarDependente(Titular titular, Dependente dependente) {
		
		if(mapDependentes.get(titular) == null){
			mapDependentes.put(titular, new ArrayList<Dependente>());
		}
		
		if( !validar.obrigatorio(dependente.getNome()) || 
				!validar.obrigatorio(dependente.getCpf())||
					!validar.cpf(dependente.getCpf())|| !(dependente.getTipo() > 0)){
			throw new ExcecaoImpostoDeRenda("Dependente sem Nome ou CPF!");
		}
		
		mapDependentes.get(titular).add(dependente);
	}
	public List<Dependente> listarDependentes(Titular titular) {
		if(mapDependentes.get(titular) == null){
			mapDependentes.put(titular, new ArrayList<Dependente>());
		}
		return mapDependentes.get(titular);
	}
	
	public Resultado declaracaoCompleta(Titular titular) {
		
		Resultado result = new Resultado();
		
		double recebido = calculoImposto.totalRecebido((mapFontes.get(titular)));	
		
		recebido = calculoImposto.descontoDependentes(recebido, listarDependentes(titular));
				
		result.setImpostoPago(calculoImposto.totalPago(mapFontes.get(titular)));
		
		result.setImpostoDevido(this.calculoImposto.impostoDevido(recebido));
		
		result.setImpostoAPagar(calculoImposto.impostoAPagar(result.getImpostoDevido(), result.getImpostoPago()));
		
		return result;
	}
	
	public void criarGastoDedutivel(Titular titular, Pessoa realizador,
			GastoDedutivel gastoDedutivel) {
		
		
	}
	

}

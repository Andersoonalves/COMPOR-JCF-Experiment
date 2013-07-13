package br.ufcg.ppgcc.compor.jcf.experimento.fachada;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufcg.ppgcc.compor.jcf.experimento.util.CalculoImpostoRenda;


public class GerenteTitular {

	private List <Titular> titular; 
	private Map <Titular,List<FontePagadora>> mapFontes;
	private Map <Titular,List<Dependente>> mapDependentes;
	private CalculoImpostoRenda calculoImposto;
	
	public GerenteTitular (){
		titular = new ArrayList<Titular>(); 
		this.mapFontes = new HashMap<Titular, List<FontePagadora>>();
		this.mapDependentes = new HashMap<Titular, List<Dependente>>();
		this.calculoImposto = new CalculoImpostoRenda();
		
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
			 if (!contensTitular(t.getTituloEleitoral())) {
		        	titular.add(t);
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
		mapFontes.get(titular).add(fonte);
	}
	
	public List<FontePagadora> listarFontes(Titular titular) {	
		return mapFontes.get(titular);
		
	}
	
	public void criarDependente(Titular titular, Dependente dependente) {
		if(mapDependentes.get(titular) == null){
			mapDependentes.put(titular, new ArrayList<Dependente>());
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
				
		result.setImpostoDevido(this.calculoImposto.impostoDevido(recebido));
		return result;
	}
	
	

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pianta;

/**
 *
 * @author Manan
 */
public class PiantaFactory  {

	public Pianta creaPianta(String specie, int età, CategoriaPianta categoria, StatoSalute salute,int[][] coordinate,int dim,float altezza) {
		
            if(categoria == CategoriaPianta.TROPICALE){
                return new Tropicale(specie,età,categoria,salute,coordinate,dim,altezza);
            }else if(categoria == CategoriaPianta.TEMPERATO){
                return new Temperato(specie,età,categoria,salute,coordinate,dim,altezza);
            }else if(categoria == CategoriaPianta.INVERNALE){
                return new Invernale(specie,età,categoria,salute,coordinate,dim,altezza);
            }
            else{
                return null;
            }
            
            
	}


}


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
public interface PiantaInterface {

	public String getSpecie();

	public float getEtà();

	public CategoriaPianta getCategoria();
        
        public StatoSalute getSalute();
        
	public void setSalute(StatoSalute salute);
        
        public int[][] getPos();
        
        public int getDim();
        
        public float getAltezza();
        
        public int getMinTempOttimale();
        
        public int getMaxTempOttimale();
        
        public int getGgOttimale();
        
        public int getGgNoOttimale();
        
        public void setGgOttimale(int ggOt);
        
        public void setGgNoOttimale(int ggNo);
        
        public int getDayCount();
        
        public void setDayCount(int dayCount); 

}

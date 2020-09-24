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
public abstract class Pianta implements PiantaInterface {

	protected String specie;

	protected int età;

	protected CategoriaPianta categoria;

	protected StatoSalute salute;
        
        protected int[][] pos;
        
        protected int dim;
        
        protected float altezza;
        
        protected int Min_temp_ottimale;
        
        protected int Max_temp_ottimale;
        
        protected int gg_Ottimale;
        
        protected int gg_NO_Ottimale;
        
        protected int days_count;

        
	
        @Override
	public String getSpecie() {
            return null;
	}

        @Override
	public float getEtà() {
            return 0;
	}

        @Override
	public CategoriaPianta getCategoria() {
            return null;
	}
        
        @Override
	public StatoSalute getSalute() {
            return null;
	}

        @Override
	public void setSalute(StatoSalute salute) {

	}
        
        @Override
        public int[][] getPos(){
            return null;
        }
        
        @Override
        public int getDim(){
            return 0;
        }
        
        @Override
        public float getAltezza(){
            return 0;
        }
        
        @Override
        public int getMinTempOttimale(){
            return 0;
        }
        
        @Override
        public int getMaxTempOttimale(){
            return 0;
        }
        
        @Override
        public int getGgOttimale(){
            return 0;
        }
        
        @Override
        public int getGgNoOttimale(){
            return 0;
        }
        
        @Override
        public void setGgOttimale(int ggOt){
        }
        
        @Override
        public void setGgNoOttimale(int ggNo){
        }
        
        @Override
        public int getDayCount(){
            return 0;
        }
        
        @Override
        public void setDayCount(int dayCount){
        }
}

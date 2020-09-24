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
public class Invernale extends Pianta{

    public Invernale(String specie, int età, CategoriaPianta categoria, StatoSalute salute,int[][] coordinate,int dim,float altezza) {
        this.specie = specie;
        this.età = età;
        this.categoria = categoria;
        this.salute = salute;
        this.dim = dim;
        this.altezza = altezza;
        this.Min_temp_ottimale = 0;
        this.Max_temp_ottimale = 16;
        this.gg_NO_Ottimale = 0;
        this.gg_Ottimale = 0;
        this.days_count = 0;
        pos = new int[dim][2];
        
        for(int i=0; i<dim; i++){
            System.arraycopy(coordinate[i], 0, pos[i], 0, 2);
        }
    }

    @Override
    public String getSpecie() {
        return specie;
    }

    @Override
    public float getEtà() {
        return età;
    }

    @Override
    public CategoriaPianta getCategoria() {
        return categoria;
    }
    
    @Override
    public StatoSalute getSalute() {
        return salute;
    }
    
    @Override
    public void setSalute(StatoSalute salute) {
        this.salute = salute;
    }
    
    @Override
    public int[][] getPos(){
        return pos;
    }
    
    @Override
    public int getDim(){
        return dim;
    }
    
    @Override
    public float getAltezza(){
        return altezza;
    }
    
    @Override
    public int getMinTempOttimale() {
        return Min_temp_ottimale;
    }

    @Override
    public int getMaxTempOttimale() {
        return Max_temp_ottimale;
    }
    
    @Override
    public int getGgOttimale() {
        return gg_Ottimale;
    }

    @Override
    public int getGgNoOttimale() {
        return gg_NO_Ottimale;
    }
    
    @Override
    public void setGgOttimale(int ggOt) {
        this.gg_Ottimale = ggOt;
    }

    @Override
    public void setGgNoOttimale(int ggNo) {
        this.gg_NO_Ottimale = ggNo;
    }
    
    @Override
    public int getDayCount() {
        return days_count;
    }

    @Override
    public void setDayCount(int dayCount) {
        this.days_count = dayCount;
    }
    
}

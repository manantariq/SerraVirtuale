/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Pianta.CategoriaPianta;
import Pianta.Pianta;
import Pianta.StatoSalute;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import serra.Serra;

/**
 *
 * @author Manan
 */
public class SerraTest {
    private Serra serra;
    
    public SerraTest() {
        serra = null;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        serra = Serra.getInstance();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    //Test per l'aumento della temperatura
    @Test
    public void Temptest(){
        System.out.println("");
        float temp;
        temp = (float) 20.0 + (float) 0.5;
        serra.setTemp(temp);
        float t = serra.getTemp();
        assertEquals("temp must be 20.5",temp, t,1);
    }
    
    //Test se è disponibile lo spazio per la nuova pianta
    @Ignore
    public void isFreetest() throws IOException{
        System.out.println("");
        int[][] coordinate = new int[4][2];
        serra.isFree(1, 1, 4, coordinate);
        assertTrue(serra.isFree(1, 1, 4, coordinate));
        
    }
   // Test per l'aggiunta di nuova pianta invernale con lo stato di salute Sufficiente 
    //Temperatura di default è 20°C
    @Test
    public void statoPiantatest() throws IOException{
        System.out.println("");
        int[][] coordinate = new int[4][2];
        serra.isFree(1, 1, 4, coordinate);
        serra.aggiungiPianta("TTT", 10, CategoriaPianta.INVERNALE, StatoSalute.SUFFICIENTE, coordinate, 4, 10);
        //simulo i dieci giorni 
        for(int i = 0 ;i<=10;i++){
        serra.verificaTempPiante();
        }
        //dopo dieci giorni controllo se lo stato della pianta è passato da sufficiente a scarso
        List<Pianta> pianta = serra.getListPiante();
        Pianta p = pianta.get(0);
        assertEquals(p.getSalute(), StatoSalute.SCARSO);
    }
}

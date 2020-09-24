/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Pianta.CategoriaPianta;
import Pianta.StatoSalute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import serra.Serra;

/**
 *
 * @author Manan
 */
public class Utente {
   
    public static void NuovaPianta() throws IOException{
        
        Serra serra = Serra.getInstance();
        int[][] coordinate;
        
        boolean isCorrect = false;
        CategoriaPianta categoria = null;
        BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        System.out.println("\t");
        System.out.println("_________Nuova Pianta________");
        System.out.println("\t");
        serra.printMatrix();
        System.out.println("\t");

        System.out.println("Quante posizione occupa pianta: 1, 2, 4");
        int dim = sc.nextInt();
        while (dim != 1 && dim != 2 && dim != 4) {
            System.out.println("Quante posizione occupa pianta: 1, 2, 4");
            dim = sc.nextInt();
        }
        coordinate = new int[dim][2];
        System.out.println("\t");
        System.out.println("Inserisci le coordinate del punto di inizio: ");
        System.out.println("riga (0,1,2):");
        int x = sc.nextInt();
        System.out.println("colonna (0,1,2,3,4,5,6,7,8,9,10,11):");
        int y = sc.nextByte();
        System.out.println("\t");
        boolean ris = serra.isFree(x, y, dim, coordinate);
        if (ris == false) {
            return;
        }

        System.out.println("Specie: ");
        String specie = rdr.readLine();
        System.out.println("Età: ");
        int età = sc.nextInt();
        System.out.println("Altezza: ");
        float altezza = sc.nextFloat();

        while (!isCorrect) {
            System.out.println("Categoria: ");
            System.out.println("_____Tropicale: inserisci 1");
            System.out.println("_____Temperato: inserisci 2");
            System.out.println("_____Invernale: inserisci 3");
            String c = rdr.readLine();
            switch (c) {
                case "1":
                    categoria = CategoriaPianta.TROPICALE;
                    isCorrect = true;
                    break;
                case "2":
                    categoria = CategoriaPianta.TEMPERATO;
                    isCorrect = true;
                    break;
                case "3":
                    categoria = CategoriaPianta.INVERNALE;
                    isCorrect = true;
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }
        serra.aggiungiPianta(specie, età, categoria, StatoSalute.OTTIMO, coordinate, dim,altezza); 
    }
    
    public static void AlzaTemperatura(){
        
        Serra serra = Serra.getInstance();
        float temp = serra.getTemp();
        temp += 0.5;
        serra.setTemp(temp);
    }
    public static void AbbassaTemperatura(){
        
        Serra serra = Serra.getInstance();
        float temp = serra.getTemp();
        temp -= 0.5;
        serra.setTemp(temp);
    }
    public static void AlzaUmidità(){
        Serra serra = Serra.getInstance();
        int umidità = serra.getUmidità();
        umidità += 5;
        serra.setUmidità(umidità);
    }
    public static void AbbassaUmidità(){
        Serra serra = Serra.getInstance();
        int umidità = serra.getUmidità();
        umidità -= 5;
        serra.setUmidità(umidità);
    }
    public static void Temperatura(){
        Serra serra = Serra.getInstance();
        System.out.println("");
        System.out.print("____Temperatura: "+serra.getTemp()+"°C");
        System.out.println("");
    }
    public static void Umidità(){
        Serra serra = Serra.getInstance();
        System.out.println("");
        System.out.print("___Umidità: "+serra.getUmidità()+"%");
        System.out.println("");
    }
    public static void main (String[] arg) throws IOException{
        
        Serra serra = Serra.getInstance();
        
        int scelta= 100;
        do {
            System.out.println("");
            System.out.println("Nuova Pianta: Premere 1");
            System.out.println("Alza Temperatura: Premere 2");
            System.out.println("Abbassa Temperatura: Premere 3");
            System.out.println("Alza Umidità: Premere 4");
            System.out.println("Abbassa Umidità: Premere 5");
            System.out.println("Mostra Temperatura: Premere 6");
            System.out.println("Mostra Umidità: Premere 7");
            System.out.println("La lista delle Piante: Premere 8");
            System.out.println("Inserisci Le piante di esempio: Premere 9");
            System.out.println("Stampa la Serra: Premere 10");
            System.out.println("Chiudere: Premere 0");
            System.out.println("Scegli: ");
            
            try{
                Scanner sc = new Scanner(System.in);
                scelta = sc.nextInt();
                
            }catch(InputMismatchException e){
                System.out.println("This is not an integer");
            }
            
            switch (scelta) {
                case 1:
                    NuovaPianta();
                    break;
                case 2:
                    AlzaTemperatura();
                    break;
                case 3:
                    AbbassaTemperatura();
                    break;
                case 4:
                    AlzaUmidità();
                    break;
                case 5:
                    AbbassaUmidità();
                    break;
                case 6:
                    Temperatura();
                    break;
                case 7:
                    Umidità();
                    break;
                case 8:
                    serra.listaPiante();
                    break;
                case 9:
                    int[][] coordinate = new int[4][2];
                    boolean ris = serra.isFree(1, 5, 4, coordinate);
                    if(ris != false){  
                        serra.aggiungiPianta("Pianta1", 10, CategoriaPianta.TROPICALE, StatoSalute.OTTIMO, coordinate, 4, 13);
                    }
                    coordinate = new int[2][2];
                    ris = serra.isFree(2, 11, 2, coordinate);
                    if(ris != false){  
                        serra.aggiungiPianta("Pianta2", 10, CategoriaPianta.TEMPERATO, StatoSalute.OTTIMO, coordinate, 2, 10);
                    }
                    coordinate = new int[1][2];
                    ris = serra.isFree(0, 0, 1, coordinate);
                    if(ris != false){   
                        serra.aggiungiPianta("Pianta3", 10, CategoriaPianta.INVERNALE, StatoSalute.SCARSO, coordinate, 1, 7);
                    }
                    break;
                case 10:
                    serra.printMatrix();
                default:
                    break;
            }
        } while (scelta != 0);
        System.exit(0);
    }
}
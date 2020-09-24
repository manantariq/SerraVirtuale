/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serra;

import Pianta.CategoriaPianta;
import Pianta.Pianta;
import Pianta.PiantaFactory;
import Pianta.StatoSalute;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;  

/**
 *
 * @author Manan
 */
public class Serra {

    private static Serra Instance = null;

    private float temperatura;

    private int umidità;

    private boolean temp_isChangeAble;

    private boolean umi_isChangeAble;

    private List<Pianta> Piante;

    private List<Pianta> CancellaPiante;

    private final int row;

    private final int col;

    private int[][] mappa_serra;

    Timer timer = new Timer();

    private Serra() {

        Piante = new ArrayList<Pianta>();
        CancellaPiante = new ArrayList<Pianta>();

        this.temp_isChangeAble = true;
        this.umi_isChangeAble = true;
        this.temperatura = 20;
        this.umidità = 60;
        this.row = 3;
        this.col = 12;

        mappa_serra = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mappa_serra[i][j] = 0;
            }
        }
        printMatrix();
        day_schedule();

    }
    
    //Singleton
    public static Serra getInstance() {

        if (Instance == null) {
            Instance = new Serra();
        }
        return Instance;
    }
    
    //questo Metodo serve per simulare il tempo 
    //si è supposto che la giornata duri 10secondi per facilitare il test del codice
    public void day_schedule() {
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                verificaTempPiante();
            }
        }, 10000, 10000); //10 secondi

    }
    
    //ogni giorno viene controllato per ogni pianta se è nella temperatura ottimale o no
    public void verificaTempPiante() {
        for (Pianta p : Piante) {
            switch (p.getCategoria()) {
                case INVERNALE:
                    aggiornaStato(p);
                    break;
                case TROPICALE:
                    aggiornaStato(p);
                    break;
                case TEMPERATO:
                    aggiornaStato(p);
                    break;
            }
        }
        cancellaPianta(); // se ci sono piante morte da cancellare
    }
    
    //per ogni pianta aggiorna lo stato di salute
    public void aggiornaStato(Pianta p) {
        if (p.getDayCount() == 10) { //se per la pianta in considerazione sono passati 10 giorni
            if (p.getGgOttimale() == 10) { //se la pianta è rimasto in temperatura ottimale per 10 giorni
                if (p.getSalute().equals(StatoSalute.SCARSO)) {
                    p.setSalute(StatoSalute.SUFFICIENTE);
                } else if (p.getSalute().equals(StatoSalute.SUFFICIENTE)) {
                    p.setSalute(StatoSalute.BUONO);
                } else if (p.getSalute().equals(StatoSalute.BUONO)) {
                    p.setSalute(StatoSalute.OTTIMO);
                }
                p.setGgNoOttimale(0);
                p.setGgOttimale(0);
                p.setDayCount(0);
            }
            if (p.getGgNoOttimale() == 10) { //se la pianta è rimasta in temperatura non ottimale per 10giorni
                if (p.getSalute().equals(StatoSalute.OTTIMO)) {
                    p.setSalute(StatoSalute.BUONO);
                    p.setGgNoOttimale(0);
                    p.setGgOttimale(0);
                    p.setDayCount(0);
                } else if (p.getSalute().equals(StatoSalute.BUONO)) {
                    p.setSalute(StatoSalute.SUFFICIENTE);
                    p.setGgNoOttimale(0);
                    p.setGgOttimale(0);
                    p.setDayCount(0);
                } else if (p.getSalute().equals(StatoSalute.SUFFICIENTE)) {
                    p.setSalute(StatoSalute.SCARSO);
                    p.setGgNoOttimale(0);
                    p.setGgOttimale(0);
                    p.setDayCount(0);
                } else if (p.getSalute().equals(StatoSalute.SCARSO)) {
                    p.setSalute(StatoSalute.MORTA);
                    System.out.println("");
                    System.out.println("");
                    System.out.println("Pianta Morta: Verrà Rimossa");
                    System.out.print("Specie: " + p.getSpecie() + "  ");
                    System.out.print("Età: " + p.getEtà() + " ");
                    System.out.print("Categoria: " + p.getCategoria() + "  ");
                    System.out.print("Stato Salute: " + p.getSalute() + "  ");

                    int[][] pos = p.getPos();
                    int dim1 = p.getDim();
                    System.out.print("Coordinate: { ");
                    for (int i = 0; i < dim1; i++) {
                        mappa_serra[pos[i][0]][pos[i][1]] = 1;
                        for (int j = 0; j < 2; j++) {
                            System.out.print(pos[i][j] + "  ");
                        }
                        System.out.print(", ");
                    }
                    System.out.print("} ");
                    System.out.println("");
                    CancellaPiante.add(p);
                }
            }
            System.out.println("Passati 10 Gironi: Stato della Pianta è stato aggiornato");
        } else { //se non sono pasasti 10 giorni allora aggiorni i contatori ggottimatle(giorni ottimali) e ggNoOttimale(giorni non ottimali)
            float temp = getTemp();
            p.setDayCount(p.getDayCount() + 1);
            if (p.getCategoria().equals(CategoriaPianta.INVERNALE)) {
                if (temp <= p.getMaxTempOttimale()) { //se temperatura è minore della temperatura ottimale 16°C
                    p.setGgOttimale(p.getGgOttimale() + 1);
                } else {
                    p.setGgNoOttimale(p.getGgNoOttimale() + 1);
                }
            }

            if (p.getCategoria().equals(CategoriaPianta.TEMPERATO)) {
                if (temp >= p.getMinTempOttimale() && temp <= p.getMaxTempOttimale()) { //se temperatura è compreso tra 14 e 28
                    p.setGgOttimale(p.getGgOttimale() + 1);
                } else {
                    p.setGgNoOttimale(p.getGgNoOttimale() + 1);
                }
            }

            if (p.getCategoria().equals(CategoriaPianta.TROPICALE)) {
                if (temp >= p.getMinTempOttimale()) { //se temperatura è minore della temperatura ottimale 16°C
                    p.setGgOttimale(p.getGgOttimale() + 1);
                } else {
                    p.setGgNoOttimale(p.getGgNoOttimale() + 1);
                }
            }

        }
    }

    public void cancellaPianta() {
        for (Pianta p : CancellaPiante) {

            int[][] pos = p.getPos();
            int dim1 = p.getDim();
            for (int i = 0; i < dim1; i++) {
                mappa_serra[pos[i][0]][pos[i][1]] = 0;
            }
            Piante.remove(p);
        }
        CancellaPiante.clear();
    }

    public void aggiungiPianta(String specie, int età, CategoriaPianta categoria, StatoSalute salute, int[][] coordinate, int dim, float altezza) throws IOException, NullPointerException {

        PiantaFactory pf = new PiantaFactory();
        Pianta p = pf.creaPianta(specie, età, categoria, salute, coordinate, dim, altezza);
        Piante.add(p);

        System.out.println("Nuova Pianta Aggiunta\t");
        System.out.print("Specie: " + p.getSpecie() + "  ");
        System.out.print("Età: " + p.getEtà() + " ");
        System.out.print("Categoria: " + p.getCategoria() + "  ");
        System.out.print("Stato Salute: " + p.getSalute() + "  ");

        int[][] pos = p.getPos();
        int dim1 = p.getDim();
        System.out.print("Coordinate: { ");
        for (int i = 0; i < dim1; i++) {
            mappa_serra[pos[i][0]][pos[i][1]] = 1;
            for (int j = 0; j < 2; j++) {
                System.out.print(pos[i][j] + "  ");
            }
            System.out.print(", ");
        }
        System.out.print("} ");
        System.out.println("");
        printMatrix();
    }

    public void printMatrix() {
        String str;
        for (int i = 0; i < row; i++) {
            str = "";
            if (i == 0) {
                System.out.print(" ");
                System.out.println("  0  1  2  3  4  5  6  7  8  9  10  11");

            }
            for (int j = 0; j < col; j++) {
                if (j == 0) {
                    System.out.print(i + "| " + mappa_serra[i][j] + "  ");
                } else {
                    System.out.print(mappa_serra[i][j] + "  ");
                }
            }
            System.out.println("");
        }
    }

    public float getTemp() {
        return this.temperatura;
    }

    public void setTemp(float temp) {
        if (temp_isChangeAble) {
            this.temperatura = temp;
            temp_isChangeAble = false;
            Temp_task();
            System.out.print("___Temperatura Modificata: " + getTemp());
            System.out.println("\t");
        } else {
            System.out.println("___Error: Non puoi modificare la temperatura, Riprovaci più tardi");
        }
    }
    
    //simula il tempo per la modifica della temperatura, l'utente può modificare la temperatura ogni 30min
    //per facilitare il test l'utente può modificarela temperatura dopo 10secondi
    public void Temp_task() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                temp_isChangeAble = true;
            }
        }, 10000);
    }

    public int getUmidità() {
        return this.umidità;
    }

    public void setUmidità(int umidità) {
        if (umi_isChangeAble) {

            umi_isChangeAble = false;
            umi_task();
            int umi = umidità;
            float temp = getTemp();
            Random random = new Random();
            if (random.nextDouble() <= 0.20) {
                if (getUmidità() < umi) {
                    temp += 0.5;
                } else {
                    temp -= 0.5;
                }
                setTemp(temp);
            }
            this.umidità = umidità;
            System.out.println("");
            System.out.print("___Umidità Modificata: " + getUmidità());
            System.out.println("\t");
        } else {
            System.out.println("___Error: Non puoi modificare l'umidità, Riprovaci più tardi");
        }
    }
    
    //simula il tempo per la modifica dell'umidita, l'utente può modificare l'umidità ogni 30min
    //per facilitare il test l'utente può modificare l'umidità dopp 10secondi
    public void umi_task() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                umi_isChangeAble = true;
            }
        }, 10000);
    }

    public boolean isFree(int x, int y, int dim, int[][] coordinate) {

        if (x < 0 || y < 0 || x >= row || y >= col) {
            System.out.println("__ERROR___Coordinate sbagliate___\t");
            return false;
        }

        if (x < row && y < col && mappa_serra[x][y] == 0) {
            coordinate[0][0] = x;
            coordinate[0][1] = y;
            if (dim == 1) {
                return true;
            }
            if (dim == 2) {
                if (x + 1 < row && y < col && mappa_serra[x + 1][y] == 0) {
                    coordinate[1][0] = x + 1;
                    coordinate[1][1] = y;
                    return true;
                }
                if (x < row && y + 1 < col && mappa_serra[x][y + 1] == 0) {
                    coordinate[1][0] = x;
                    coordinate[1][1] = y + 1;
                    return true;
                }
                if (x - 1 >= 0 && y < col && mappa_serra[x - 1][y] == 0) {
                    coordinate[1][0] = x - 1;
                    coordinate[1][1] = y;
                    return true;
                }
                if (x < row && y - 1 >= 0 && mappa_serra[x][y - 1] == 0) {
                    coordinate[1][0] = x;
                    coordinate[1][1] = y - 1;
                    return true;
                }
            }
            if (dim == 4) {
                //matrice 4 alto sinistra
                if (x - 1 >= 0 && y + 1 < col && mappa_serra[x - 1][y] == 0 && mappa_serra[x - 1][y + 1] == 0 && mappa_serra[x][y + 1] == 0) {
                    coordinate[1][0] = x - 1;
                    coordinate[1][1] = y;

                    coordinate[2][0] = x - 1;
                    coordinate[2][1] = y + 1;

                    coordinate[3][0] = x;
                    coordinate[3][1] = y + 1;

                    return true;
                }
                //matrice 4 alto destra
                if (x - 1 >= 0 && y - 1 >= 0 && mappa_serra[x - 1][y] == 0 && mappa_serra[x][y - 1] == 0 && mappa_serra[x - 1][y - 1] == 0) {
                    coordinate[1][0] = x - 1;
                    coordinate[1][1] = y;

                    coordinate[2][0] = x;
                    coordinate[2][1] = y - 1;

                    coordinate[3][0] = x - 1;
                    coordinate[3][1] = y - 1;

                    return true;
                }
                //matrice basso sinistra
                if (x + 1 < row && y + 1 < col && mappa_serra[x + 1][y] == 0 && mappa_serra[x + 1][y + 1] == 0 && mappa_serra[x][y + 1] == 0) {
                    coordinate[1][0] = x + 1;
                    coordinate[1][1] = y;

                    coordinate[2][0] = x + 1;
                    coordinate[2][1] = y + 1;

                    coordinate[3][0] = x;
                    coordinate[3][1] = y + 1;

                    return true;
                }
                //matrice basso destra
                if (x + 1 < row && y - 1 >= 0 && mappa_serra[x + 1][y] == 0 && mappa_serra[x][y - 1] == 0 && mappa_serra[x + 1][y - 1] == 0) {
                    coordinate[1][0] = x + 1;
                    coordinate[1][1] = y;

                    coordinate[2][0] = x;
                    coordinate[2][1] = y - 1;

                    coordinate[3][0] = x + 1;
                    coordinate[3][1] = y - 1;

                    return true;
                }
            }
        }
        System.out.println("Errore: Nella posizione scelta non c'è abbastanza spazio per la pianta");
        printMatrix();
        return false;
    }

    public void listaPiante() {
        int k = 0;
        for (Pianta p : Piante) {
            System.out.println("");
            System.out.print(k + "  ");
            System.out.print("Specie: " + p.getSpecie() + "  ");
            System.out.print("Età: " + p.getEtà() + " ");
            System.out.print("Categoria: " + p.getCategoria() + "  ");
            System.out.print("Stato Salute: " + p.getSalute() + "  ");
            int[][] pos = p.getPos();
            int dim1 = p.getDim();
            System.out.print("Coordinate: { ");
            for (int i = 0; i < dim1; i++) {
                mappa_serra[pos[i][0]][pos[i][1]] = 1;
                for (int j = 0; j < 2; j++) {
                    System.out.print(pos[i][j] + "  ");
                }
                System.out.print(", ");
            }
            System.out.print("} ");
            System.out.print("Giorni Ottimale: " + p.getGgOttimale() + "  ");
            System.out.print("Giorni Non Ottimale: " + p.getGgNoOttimale() + "  ");
            k++;
            System.out.println("");
        }
    }
    
    public List<Pianta> getListPiante(){
        return Piante;
    }
}

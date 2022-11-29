
package palabras_desordenadas;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author alfonso
 */
public class Palabras_desordenadas {

    
    private int ganadas1, ganadas2;
    private ArrayList<String> palabras;
    private String palabraActual, actualDesordenada;
    private Scanner sc;
    
    
    public Palabras_desordenadas() {
        this.ganadas1 = 0;
        this.ganadas2 = 0;
        this.sc = new Scanner(System.in);
        
        this.palabras = new ArrayList<>();
        this.palabras.add("girasol");
        this.palabras.add("ferrocarril");
        this.palabras.add("automovil");
        this.palabras.add("cenicero");
        this.palabras.add("libreta");
    } 
    
    
    public void jugar(){
    
        this.elegirPalabra();
        this.desordenar();
        int jugador = 1;
        
        while(true) {
            System.out.println("------------------------------------------------\n");
            System.out.println("La palabra es: " + this.actualDesordenada + "\n\n");
            
            System.out.println("Jugador " + jugador + " su turno.");
            
            //Si acierta, sumale 1 y pasa a la nueva palabra
            if (this.intentar()) {
                
                System.out.println("******CORRECTO********");
                
                //Si jugador 1
                if(jugador == 1) {
                    this.ganadas1 += 1;
                }
                //Si jugador 2
                else {
                    this.ganadas2 += 1;
                }
                this.elegirPalabra();
                this.desordenar();
            }
            else System.out.println("********FALLO*********");
            
            
            this.hayGanador();
            
            
            if (jugador == 1) jugador = 2;
            else jugador = 1;
        }
    
    }
 
    
    /**
     * Elige una palabra al azar de la lista
     */
    public void elegirPalabra() {
    
        Random rand = new Random();
        this.palabraActual = this.palabras.get(rand.nextInt(this.palabras.size()));
        
    }
 
    
    /**
     * Desordena la palabra actual
     * @return 
     */
    private void desordenar() {
    
        Random rand = new Random();
        
        //Pasamos a array de caracteres
        char[] chars = new char[this.palabraActual.length()];
        for (int i = 0; i < this.palabraActual.length(); i++) {
            chars[i] = this.palabraActual.charAt(i);
        }
    
        
        //Desordenamos
        int j,k;
        char aux;
        for (int i = 0; i < 20; i++) {
            
            j = rand.nextInt(this.palabraActual.length());
            k = rand.nextInt(this.palabraActual.length());
            
            aux = chars[j];
            chars[j] = chars[k];
            chars[k] = aux;
        }
        
        
        String resultado = "";
        for(var c: chars) {
            resultado += c;
        }
        
        this.actualDesordenada = resultado;
    }
 
    
    /**
     * Intenta adivinar la palabra. Devuelve si ha sido un acierto o no.
     * @return 
     */
    public boolean intentar() {
    
        this.sc = new Scanner(System.in);
        String intento = sc.nextLine();
        
        System.out.println(check(intento));
        return check(intento);
    
    }
    
    /**
     * Comprueba si la palabra introducida por parametro es la solucion correcta.
     * @param s
     * @return 
     */
    public boolean check(String s) {
        return s.equals(palabraActual);
    }
    
    /**
     * Comprueba si alguien ha ganado, en cuyo caso 
     */
    public boolean hayGanador() {
        
        
        //Si ya se han ganado 2
        if(this.ganadas1 == 2) {
            System.out.println("***********************");
            System.out.println("HA GANADO EL JUGADOR 1*");
            System.out.println("***********************");
            
            System.exit(0);
        }
        if(this.ganadas1 == 2) {
            System.out.println("***********************");
            System.out.println("HA GANADO EL JUGADOR 2*");
            System.out.println("***********************");
            System.exit(0);
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        
        Palabras_desordenadas p = new Palabras_desordenadas();
        p.jugar();
    }
}
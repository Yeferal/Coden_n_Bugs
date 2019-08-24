
package bodega;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilo extends Thread{
    Bodega bodega = new Bodega();
    Inicio inicio;
    public Hilo(Inicio inicio){
        this.inicio = inicio;
    }
    /**Entra en un bluque que verifica la ruta y actualiza la tabla
     * 
     */
    @Override
    public void run(){
        try {
            while (true) {
            bodega.correrTuples();
            inicio.vBodega.rellenarTabla();
            sleep(2000);  
        }
                      
        } catch (InterruptedException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }
}

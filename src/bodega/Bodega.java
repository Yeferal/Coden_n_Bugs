
package bodega;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.ParseConversionEvent;


public class Bodega {

    Conector conect = new Conector();
    private int pcInicio;
    
    
       public Bodega(){
           
       }
       /**se encarga unicamente de correr los tuples de la bodega y envia los datos
        * para luego ser enviados a su punto de control
        * came mensionar que los paquetes priorizados son los que apareceran de primero
        */
       public void correrTuples(){
        try {
            conect.stmt = conect.conexion.createStatement();
            conect.res = conect.stmt.executeQuery("SELECT * FROM bodega ORDER BY priorizado_pq DESC;");
            
            while (conect.res.next()) {
                String detinoS = conect.res.getString(2);
                int id_pc = Integer.parseInt(conect.res.getString(4));
                
                if(verificarRuta(detinoS, id_pc)){
                    break;
                }
            } 
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
            
       }
       /**Verifica la cantidad de paquetes que hay en el punto de control de la ruta 
        * y me retorna esa catidad para luego ser comparada
        * el parametro que recibe es id del punto de control
        * @param idpc
        * @return 
        */
       private int verificarEspacio(int idpc){
           int numeroPaquetes=0;
        try {
            conect.stmt = conect.conexion.createStatement();
            conect.res = conect.stmt.executeQuery("SELECT * FROM paquete WHERE id_pc="+idpc+";");
            
            while (conect.res.next()) {
                numeroPaquetes++;
 
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
           return numeroPaquetes;
       }
       /**busca el la especificacion de cantidad que adminte el punto de control
        * el paramatro es el id del punto de control
        * retorna la capacidad que tiene el punto de control de almacenamiento de paquetes
        * @param idpc
        * @return 
        */
       private int capacidadPC(int idpc){
           int capacidad = 0;
           
           try {
            conect.stmt = conect.conexion.createStatement();
            conect.res = conect.stmt.executeQuery("SELECT * FROM punto_de_control WHERE id_pc="+idpc+";");
            
            while (conect.res.next()) {
                capacidad = Integer.parseInt(conect.res.getString(5));
            }
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return capacidad;
       }
       
       /**verifica primero las rutas que estan disponible y que lleven al destino necesitado
        * luego de filtrar la ruta extrae el apuntador que indica el primer punto de control que tiene la ruta
        * puego envia el apuntador a las dos verificaciones y si es verdadero procede a borrar de la bodega la posicion del paquete
        * y le asigna al paquete su primer punto de control.
        * los dos parametro son el destino y el d del paquete
        * @param destino
        * @param idPaquete 
        */
       private boolean verificarRuta(String destino,int idPaquete){

           try {
            conect.stmt = conect.conexion.createStatement();
            conect.res = conect.stmt.executeQuery("SELECT * FROM ruta WHERE (destino=\""+destino+"\") AND (estado_ruta=1) AND (pc_inicio IS NOT NULL);");
            
            while (conect.res.next()) {
                
                    pcInicio = Integer.parseInt(conect.res.getString(11));
                    String idRuta = conect.res.getString(1);
                    if(verificarEspacio(pcInicio)<capacidadPC(pcInicio)){
                        
                        conect.insercion = conect.conexion.prepareStatement("DELETE FROM bodega WHERE id_pqt="+idPaquete+";");
                        conect.insercion.executeUpdate();
                        
                        conect.insercion = conect.conexion.prepareStatement("UPDATE paquete SET id_pc="+pcInicio+" WHERE id_paquete="+idPaquete+";");
                        conect.insercion.executeUpdate();
                        
                        conect.setDataTime(idPaquete);
                        conect.setDestinarPaquete(idRuta);
                        return true;
                    }
            }
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
           return false;
       }
       
}

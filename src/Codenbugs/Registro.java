
package Codenbugs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Registro {
    
    Conector conex;
    boolean existe;
    String punto = "SELECT * FROM punto_de_control";
    
    
    public Registro(Conector conex){
        this.conex = conex;
    }
    /**retorna el tiempo actual que tiene la base de datos**/
    public String obtenertime() throws SQLException{
        conex.stmt = conex.conexion.createStatement();
        conex.res = conex.stmt.executeQuery("select now();");
        while(conex.res.next()){
            return conex.res.getString(1);
        }
        return conex.res.getString(1);
    }
    /**crea un paquete nuevo con sus parametros correspondientes
     * 
     * @param peso
     * @param nitR
     * @param priori
     * @param tarifa
     * @param destino
     * @param cuota 
     */
    public void crearPaquete(double peso, int nitR, int priori, double tarifa, String destino, double cuota){
        try {
            conex.insercion = conex.conexion.prepareStatement("INSERT INTO paquete (peso,nit_remitente,priorizacion,tarifa_pq,destino_pq,cuota) VALUES ("+peso+","+nitR+","+priori+","+tarifa+",'"+destino+"',"+cuota+");");
            conex.insercion.executeUpdate();
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM paquete");
            int idPa = 0;
            while (conex.res.next()) {
                
                idPa=  conex.res.getShort(1);
            }
            conex.insercion = conex.conexion.prepareStatement("INSERT INTO bodega (destino_pqte,priorizado_pq,id_pqt) VALUES ('"+destino+"',"+priori+","+idPa+");");
            conex.insercion.executeUpdate();
            //Crear espacion en bodega
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace(); 
        }
    }
    /**elimina paquetes**/
    public void eliminarPaquete(String id){
        try {
            conex.insercion = conex.conexion.prepareStatement("DELETE FROM paquete WHERE id_paquete="+id+";");
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace(); 
        }
    }
    /**actualiza la fecha de un paquete**/
    public void setDataTime(String id) throws SQLException{
        conex.insercion = conex.conexion.prepareStatement("UPDATE paquete SET tiempo_pc='"+obtenertime()+"' WHERE id_paquete="+id+";");
        conex.insercion.executeUpdate();
    }
}

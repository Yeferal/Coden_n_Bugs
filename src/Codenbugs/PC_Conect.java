
package Codenbugs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

            
public class PC_Conect {
    Conector conex;
    double tarifaglobal;
    double precioLibra;
    double cuotaDestino;
    double priorizacionP;
    
    public PC_Conect(Conector conex){
        this.conex = conex;
    }
    /**retorona true si no hay paquetes en el punto de control
     * y false si no existen paquetes
     * @param id_pCc
     * @return 
     */
    public boolean validarPaquetesPC(String id_pCc){
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM paquete WHERE id_paquete="+id_pCc+";");
            while(conex.res.next()){
                System.out.println("id: "+conex.res.getString(1));
                return true;
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        JOptionPane.showMessageDialog(null, "Existen Paquetes en este Punto de Control");
        return false;
    }
    /**actualiza los valotes de las varibles de tariafa, precio libra y priorizacion**/
    public void actualizarPrecio(){
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM registro;");
            conex.res.next();
            tarifaglobal = Double.parseDouble(conex.res.getString(1));
            precioLibra = Double.parseDouble(conex.res.getString(3));
            priorizacionP = Double.parseDouble(conex.res.getString(5));
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    /**actualiza la variable de la cuota de destino**/
    public double actualizarCuota(String destino){
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM destinos WHERE nombre='"+destino+"';");
            conex.res.next();
            cuotaDestino = Double.parseDouble(conex.res.getString(2));
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return cuotaDestino;
    }
    /**retorna la fecha en que se entrego el paquete**/
    public String fechar(String idPa){
        String fechaEntrega = null;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM paquete WHERE id_paquete="+idPa+";");
            conex.res.next();
            fechaEntrega = conex.res.getString(7);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return fechaEntrega;
    }
    /**ctualiza los costos que se an genereado en una ruta
     * actualiza el costo que lleva un paquete
     * @param fecha
     * @param idRP
     * @param fechaActual
     * @param idPc
     * @param idPq 
     */
    public void setCostos(String fecha, String idRP, String fechaActual, String idPc , String idPq){
        double costo = getCosto(idRP)+(numeroHoras(fecha, fechaActual))*(getTarifaPc(idPc));
        double costoPaquete = getCostoPaquete(idPq)+(numeroHoras(fecha, fechaActual))*(getTarifaPc(idPc));
        try {
            conex.insercion = conex.conexion.prepareStatement("UPDATE ruta SET costo_ruta="+costo+"WHERE id_ruta="+idRP+";");
            conex.insercion.executeUpdate();
            conex.insercion = conex.conexion.prepareStatement("UPDATE paquete SET costo_pc="+costoPaquete+"WHERE id_ruta="+idPq+";");
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    /**retorna el numero de horas, de la diferencia de la fecha y la fecha actual
     * 
     * @param fecha
     * @param fechaActual
     * @return 
     */
    private int numeroHoras(String fecha, String fechaActual){
        int horas = 0;
        //String datTime=
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT TIMESTAMPDIFF(HOUR, '"+fecha+"', '"+fechaActual+"');");
            conex.res.next();
            horas = Integer.parseInt(conex.res.getString(1));
            System.out.println("horas:"+horas);
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return horas;
    }
    /**retorna el costo actual que tiene acumulado una ruta**/
    public double getCosto(String idRP){
        double costoActual = 0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM ruta WHERE id_ruta="+idRP+";");
            conex.res.next();
            costoActual = Double.parseDouble(conex.res.getString(7));
            System.out.println("Dias:"+costoActual);
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return costoActual;
    }
    /**retorna la tarifa que tiene un punto de control**/
    public double getTarifaPc(String idPc){
        double tarifa=0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM punto_de_control WHERE id_pc="+idPc+";");
            conex.res.next();
            if(conex.res.getString(4)==null){
                tarifa=getTarifaGlobal();
            }else{
                tarifa=Double.parseDouble(conex.res.getString(4));
            }
            
        } catch (SQLException ex) {
        }
           return tarifa;
    }
    /**retorna la tarifa globall**/
    public double getTarifaGlobal(){
        double tarifaG=0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM destinos;");
            conex.res.next();
            tarifaG=Double.parseDouble(conex.res.getString(2));
        } catch (SQLException ex) {
            
        }
           return tarifaG;
    }
    /**retorna los costos actuales de un paquete**/
    public double getCostoPaquete(String idPq){
        double costoP = 0;
        
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM paquete WHERE id_paquete="+idPq+";");
            conex.res.next();
            if(conex.res.getString(8)==null){
                costoP=0;
            }else{
                costoP=Double.parseDouble(conex.res.getString(8));
            }
        } catch (SQLException ex) {
            
        }
        return costoP;
    }
    /**retorna la ruta a la que pertenece un punto de contol**/
    public String getRutaPaquete(String puntoControl){
        String ruta=null;
        Statement stamt;
        ResultSet resu;
        try {
            stamt = conex.conexion.createStatement();
            resu = stamt.executeQuery("SELECT * FROM punto_de_control WHERE id_pc="+puntoControl+";");
            resu.next();
            ruta= resu.getString(7);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return ruta;
    }
    /**actualiza las ganacias que se tiene en entregas**/
    public void actulizarGananias(){
        try {
            conex.insercion = conex.conexion.prepareStatement("UPDATE entregas SET ganancia=cuota-costo;");
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}

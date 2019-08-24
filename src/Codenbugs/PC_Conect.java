
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
    public void actulizarGananias(){
        try {
            conex.insercion = conex.conexion.prepareStatement("UPDATE entregas SET ganancia=cuota-costo;");
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}

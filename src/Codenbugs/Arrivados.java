
package Codenbugs;

import java.sql.SQLException;


public class Arrivados {
    Conector conex;
    double tarifaglobal;
    double precioLibra;
    double cuotaDestino;
    double priorizacionP;
    
    public Arrivados(Conector conex){
        this.conex = conex;
        
    }
    
    private int numeroDias(String fecha){
        int dias = 0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT TIMESTAMPDIFF(DAY,'"+fecha+"',NOW(),);");
            conex.res.next();
            dias = Integer.parseInt(conex.res.getString(1));
            System.out.println("Dias:"+dias);
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return dias;
    }
    
    public void setIngreso(String idPaquete, String idRuta){
        double ingresoNuevo = getIngreso(idRuta)+getTarifaPaquete(idPaquete);
        try {
            conex.insercion = conex.conexion.prepareStatement("UPDATE ruta SET ingresos_ruta="+ingresoNuevo+"WHERE id_ruta="+idRuta+";");
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    
    public double getIngreso(String idRuta){
        double ingresoActual = 0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM ruta WHERE id_ruta="+idRuta+";");
            conex.res.next();
            ingresoActual = Double.parseDouble(conex.res.getString(6));
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return ingresoActual;
    }
    private double getTarifaPaquete(String idPaquete){
        double tarifapaq = 0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM paquete WHERE id_paquete="+idPaquete+";");
            conex.res.next();
            tarifapaq = Double.parseDouble(conex.res.getString(6));
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        
        return tarifapaq;
    }
    public void setEntreegarPaquete(String idRuta){
        int entreganueva = getEntreegarPaq(idRuta)+1;
        int endestinoNuevo = getEnDestinoPaq(idRuta)-1;
        try {
            conex.insercion = conex.conexion.prepareStatement("UPDATE ruta SET paquetes_entregados="+entreganueva+", paquetes_endestino="+endestinoNuevo+" WHERE id_ruta="+idRuta+";");
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    
    private int getEntreegarPaq(String idRuta){
        int ingresP= 0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM ruta WHERE id_ruta="+idRuta+";");
            conex.res.next();
            ingresP = Integer.parseInt(conex.res.getString(9));
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return ingresP;
    }
    private int getEnDestinoPaq(String idRuta){
        int destinoP= 0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM ruta WHERE id_ruta="+idRuta+";");
            conex.res.next();
            destinoP = Integer.parseInt(conex.res.getString(10));
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return destinoP;
    }
    
}

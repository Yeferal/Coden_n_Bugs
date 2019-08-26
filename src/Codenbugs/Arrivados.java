
package Codenbugs;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Arrivados {
    Conector conex;
    double tarifaglobal;
    double precioLibra;
    double cuotaDestino;
    double priorizacionP;
    
    public Arrivados(Conector conex){
        this.conex = conex;
    }
    /**realiza un ajuste de los ingresos en la ruta especifica**/
    public void setIngreso(String idPaquete, String idRuta){
        double ingresoNuevo = getIngreso(idRuta)+getTarifaPaquete(idPaquete);
        try {
            conex.insercion = conex.conexion.prepareStatement("UPDATE ruta SET ingresos_ruta="+ingresoNuevo+"WHERE id_ruta="+idRuta+";");
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    /**muestra los igresos actuales de una ruta**/
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
    /**retorna la tarifi actual de un paquete**/
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
    /**modifica en numero de entrega de los paquete en una ruta**/
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
    /**retorna el numero de entregas actuales de una ruta**/
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
    /**retorna el destino que tiene una ruta**/
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
    /**realiza una moidificacion del numero de paquetes que a tenido un cliente**/
    public void entregarPaque(String nit){
        try {
            conex.insercion = conex.conexion.prepareStatement("UPDATE cliente SET numero_paquetes=numero_paquetes+1 WHERE nit="+nit+";");
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    /** modifica el tiempo que lleva un paquete en ruta**/
    public void setTiempo(String nit, String fecha){
        try {
            conex.insercion = conex.conexion.prepareStatement("UPDATE paquete SET tiempo_ruta="+numeroDias(fecha)+" WHERE nit_Remitente="+nit+";");
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Arrivados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**retona la dierencia de dia entre eldia actual y una fecha**/    
    private int numeroDias(String fecha){
        int dias = 0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT TIMESTAMPDIFF(DAY,'"+fecha+"',NOW());");
            conex.res.next();
            dias = Integer.parseInt(conex.res.getString(1));
            System.out.println("Dias:"+dias);
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return dias;
    }
    /**crea un registro de ntregas de paquetes con los datos de la ruta, cliente y paquete**/
    public void entregarPaqueteNuevo(String idp,String idR,String destino){
        int nit = getNIT(idp);
        double cuota = getCuota(idp);
        double costo = conex.pcConeC.getCostoPaquete(idp);
        try {
            conex.insercion = conex.conexion.prepareStatement("INSERT INTO entregas (id_paquete,nit,id_ruta,destino_ruta,cuota,costo) VALUES ("+idp+","+nit+","+idR+",'"+destino+"',"+cuota+","+costo+");");
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /**retorna la cuota que tiene un paquete**/
    private double getCuota(String idp){
        double cuota = 0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM paquete WHERE id_paquete="+idp+";");
            conex.res.next();
            cuota = Double.parseDouble(conex.res.getString(6));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cuota;
    }
    /**retorna el nit de remitende de un paquete**/
    private int getNIT(String idp){
        int nit = 0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery("SELECT * FROM paquete WHERE id_paquete="+idp+";");
            conex.res.next();
            nit = Integer.parseInt(conex.res.getString(3));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nit;
    }
    /**midifica una entrega, modifica la fecha que fue retirada por el cliente**/
    public void setEntregasFinal(String idp){
        try {
            conex.insercion = conex.conexion.prepareStatement("UPDATE entregas SET fecha_entrega='"+conex.obtenertime()+"' WHERE id_paquete="+idp+";");
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

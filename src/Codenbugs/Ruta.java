package Codenbugs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ruta {
    Conector conex;
    
    public Ruta(Conector conex){
        this.conex = conex;
    }
    
    public void desactivar(String idR){
        try {
            conex.insercion = conex.conexion.prepareStatement("UPDATE ruta SET estado_ruta="+0+" WHERE id_ruta="+idR); 
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    public void activar(String idR){
        try { 
            conex.insercion = conex.conexion.prepareStatement("UPDATE ruta SET estado_ruta="+1+" WHERE id_ruta="+idR);
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    //Crea una nueva ruta pero inicialmente esta desactivada
    public void insertarRuta(String inicio, String destino, double  cuota) throws SQLException{
        conex.insercion = conex.conexion.prepareStatement("INSERT INTO ruta (inicio,destino,estado_ruta,cuota_destino) VALUES ('"+inicio+"','"+destino+"',"+0+","+cuota+");");
        conex.insercion.executeUpdate();
    }
    //Elimina una ruta
    public void eliminarRuta(int id){
        try {
            conex.insercion = conex.conexion.prepareStatement("DELETE FROM ruta WHERE id_ruta="+id);
            conex.insercion.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    public boolean buscarPcenRuta(int rutaID){
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery(conex.punto);
            conex.existe=false;
            while (conex.res.next()) {
                int nu=0;
                if(conex.res.getString(7)!=null){
                    nu=Integer.parseInt(conex.res.getString(7));
                }
                if(nu==rutaID){
                    conex.existe=true;
                    break;
                }
            }  
        } catch (SQLException e) {
            System.out.println("Fallo en el retorno de existente");
            e.getMessage();
            e.printStackTrace();
        }
        return conex.existe;
    }
    
    public int idRutaAnte(int idR){
        int idP=0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery(conex.punto);
            while (conex.res.next()) {                
                    int idPP=0;
                if(conex.res.getString(7)!=null){
                    idPP=Integer.parseInt(conex.res.getString(7));
                }
                    String idSig=conex.res.getString(8);
                if(idPP==idR && idSig==null){
                    idP=Integer.parseInt(conex.res.getString(1));
                    break;
                }
            }  
        } catch (SQLException e) { 
            System.out.println("Fallo en retorno de id");
            e.getMessage();
            e.printStackTrace();
        }
        return idP;
    }
    
    public void agregarPC(int idR, int idPc){
        if(buscarPcenRuta(idR)){
            try {
                int sig = idRutaAnte(idR);
                conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+idPc+" WHERE id_pc="+sig);
                conex.insercion.executeUpdate();
                conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET ruta="+idR+" WHERE id_pc="+idPc);
                conex.insercion.executeUpdate();
            } catch (Exception ex) {
                System.out.println("Fallo en existente");
                ex.getMessage();
                ex.printStackTrace();
            }
        }else{
            try {
                System.out.println("primeor");
                conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET ruta="+idR+" WHERE id_pc="+idPc);
                conex.insercion.executeUpdate();
                System.out.println("segudo");
                conex.insercion = conex.conexion.prepareStatement("UPDATE ruta SET pc_inicio="+idPc+" WHERE id_ruta="+idR);
                conex.insercion.executeUpdate();
                System.out.println("logrado");
            } catch (Exception ex) {
                System.out.println("Fallo en no existente");
            ex.getMessage();
            ex.printStackTrace();
            }
        }
    }
    private int apuntadorPC(int idPCsele){
        int idP=0;
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery(conex.punto);
            while (conex.res.next()) {                
                    int idPP=0;
                if(conex.res.getString(8)!=null){
                    idPP=Integer.parseInt(conex.res.getString(8));
                }
                if(idPP==idPCsele ){
                    idP=Integer.parseInt(conex.res.getString(1));
                    break;
                }
            }  
        } catch (SQLException e) { 
            System.out.println("Fallo en retorno de id");
            e.getMessage();
            e.printStackTrace();
        }
        return idP;
    }
    
    public boolean buscarPcsinAputador(int pcID){
        try {
            conex.stmt = conex.conexion.createStatement();
            conex.res = conex.stmt.executeQuery(conex.punto);
            conex.existe=false;
            while (conex.res.next()) {
                int nu=Integer.parseInt(conex.res.getString(1));
                if(nu==pcID){                    
                    if(conex.res.getString(8)==null){
                        conex.existe=false;
                        break;
                    }else{
                        conex.existe=true;
                        break;
                    }
                }    
            }  
        } catch (SQLException e) {
            System.out.println("Fallo en el retorno de existente");
            e.getMessage();
            e.printStackTrace();
        }
        return conex.existe;
    }
    
    public void eliminarPCdeRuta(int idPCdelet, int idsig){
        if(buscarPcsinAputador(idPCdelet) && !conex.pcConeC.validarPaquetesPC(Integer.toString(idPCdelet))){
            try {
                conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+idsig+" WHERE id_pc="+apuntadorPC(idPCdelet));
                conex.insercion.executeUpdate();
                conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente= NULL WHERE id_pc="+idPCdelet);
                conex.insercion.executeUpdate();
                conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET ruta= NULL WHERE id_pc="+idPCdelet);
                conex.insercion.executeUpdate();
            } catch (SQLException ex) {
                ex.getMessage();
            ex.printStackTrace();
            }
        }else{
            try {
                conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET ruta= NULL WHERE id_pc="+idPCdelet);
                conex.insercion.executeUpdate();
            } catch (SQLException ex) {
                ex.getMessage();
            ex.printStackTrace();
            }    
        }
    }
    
    public void cambiarPCs(int idsele1, int idsele2, String sig1 , String sig2) throws SQLException{
        if(sig1==null){
                int numero = Integer.parseInt(sig2);
                conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+numero+" WHERE id_pc="+idsele1);
                conex.insercion.executeUpdate();
                conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente=NULL WHERE id_pc="+idsele2);
                conex.insercion.executeUpdate();
        }else if(sig2==null){
                int numero = Integer.parseInt(sig1);
                conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente=Null WHERE id_pc="+idsele1);
                conex.insercion.executeUpdate();
                conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+numero+" WHERE id_pc="+idsele2);
                conex.insercion.executeUpdate();
        }else{
            int numero2 = Integer.parseInt(sig2);
            int numero1 = Integer.parseInt(sig1);
            conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+numero2+" WHERE id_pc="+idsele1);
            conex.insercion.executeUpdate();
            conex.insercion = conex.conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+numero1+" WHERE id_pc="+idsele2);
            conex.insercion.executeUpdate();
        }
    }

}

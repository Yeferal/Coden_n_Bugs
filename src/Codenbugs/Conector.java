package Codenbugs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Conector {
    
    Connection conexion =  null;
    PreparedStatement insercion;
    Statement stmt;
    ResultSet res;
    String user = "root";
    String password = "123-abc";
    String servidor = "jdbc:mysql://localhost:3306/codenbugs";
    boolean existe;
    String punto = "SELECT * FROM punto_de_control";
    
    public Conector(){
        try {
            conexion = DriverManager.getConnection(servidor, user, password);
            System.out.println("Se conecto XD: "+conexion.getCatalog());
            
            
            
        } catch (SQLException e) {
            System.out.println("NO SE CONECTO");
            e.getMessage();
            e.printStackTrace();
        } 
    }
    
    public void insertarUsuario(Usuario use) throws SQLException{
        
        insercion = conexion.prepareStatement("INSERT INTO usuario (nombre,codigo_usuario,tipo,pass) VALUES ('"+use.getNombre()+"','"+use.getCodigo()+"',"+use.gettipo()+",'"+use.getPassword()+"');");
//        insercion = conexion.prepareStatement("INSERT INTO Usuario (Nombre,Codigo_Usuario,Tipo,Pass)"+" VALUES(?,?,?,?)");
//        insercion.setString(2, use.getNombre());
//        insercion.setString(3, use.getCodigo());
//        insercion.setInt(4, use.gettipo());
//        insercion.setString(5, use.getPassword());
        insercion.executeUpdate();
    }
/**    
    public boolean buscar (String codigo, String pasword ){
        
            while (marco.vLogin.conect.res.next()) {                
                if(CajaCodigo.getText().equals(marco.vLogin.conect.res.getString(3))){
                    if(cajaPasswird.getText().equals(marco.vLogin.conect.res.getString(5))){
                        sesionUsusario(marco.vLogin.conect.res.getString(1), marco.vLogin.conect.res.getString(2), marco.vLogin.conect.res.getString(3), marco.vLogin.conect.res.getString(4), marco.vLogin.conect.res.getString(5));
                        System.out.println("funciono");
                        this.dispose();
                        break;
                    }else{
                        System.out.println("falla 1");
                    }
                }
System.out.println("falla 2");
                
            }
    }
    **/
    
    public boolean buscar(String codigo, String passw){
                try {
            stmt = conexion.createStatement();
            res = stmt.executeQuery("SELECT * FROM usuario");
            
            while (res.next()) {                
                if(codigo.equals(res.getString(3)) || passw.equals(res.getString(5))){
                    existe=true;
                    break;
                } else {
                    existe=false;
                }    
            }     
        } catch (Exception e) {      
        } 
        return existe;
    }
    
    public void editarUsuario(int id, String Nombre, String codigo, int tipo, String contrasena) throws SQLException{
        
        insercion = conexion.prepareStatement("UPDATE usuario SET nombre='"+Nombre+"', codigo_usuario='"+codigo+"', tipo="+tipo+",pass='"+contrasena+"' WHERE id_usuario="+id);
        insercion.executeUpdate();
        
    }
    public void eliminarUsuario(int id){
        try {
            insercion = conexion.prepareStatement("DELETE FROM usuario WHERE id_usuario="+id);
            insercion.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarPC(Punto_de_Control pc) throws SQLException{
        
        insercion = conexion.prepareStatement("INSERT INTO punto_de_control (nombre_pc,tarifa,capacidad,operador_asignado) VALUES ('"+pc.getNombre()+"','"+pc.getTarifa()+"',"+pc.getCapacidad()+",'"+pc.getOperadorAsig()+"');");

        insercion.executeUpdate();
    }
    
    public void editarPC(int id, String Nombre, double tarifa, int capacidad, int operador_Asig) throws SQLException{
        
        insercion = conexion.prepareStatement("UPDATE punto_de_control SET nombre_pc='"+Nombre+"', tarifa='"+tarifa+"', capacidad="+capacidad+",operador_asignado='"+operador_Asig+"' WHERE id_pc="+id);
        insercion.executeUpdate();
        
    }
    
    public void eliminarPC(int id){
        try {
            insercion = conexion.prepareStatement("DELETE FROM punto_de_control WHERE id_pc="+id);
            insercion.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarRuta(String inicio, String destino, double  cuota) throws SQLException{
        
        insercion = conexion.prepareStatement("INSERT INTO ruta (inicio,destino,estado_ruta,cuota_destino) VALUES ('"+inicio+"','"+destino+"',"+0+","+cuota+");");

        insercion.executeUpdate();
    }
    
    public void eliminarRuta(int id){
        try {
            insercion = conexion.prepareStatement("DELETE FROM ruta WHERE id_ruta="+id);
            insercion.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean buscarPcenRuta(int rutaID){
        try {
            stmt = conexion.createStatement();
            res = stmt.executeQuery(punto);
            existe=false;
            while (res.next()) {
                int nu=0;
                if(res.getString(7)!=null){
                    nu=Integer.parseInt(res.getString(7));
                }
                
                if(nu==rutaID){
                    existe=true;
                    break;
                }
                
            }  
        } catch (SQLException e) {
            System.out.println("Fallo en el retorno de existente");
            e.getMessage();
            e.printStackTrace();
        }
        return existe;
    }
    
    public int idRutaAnte(int idR){
        int idP=0;
        try {
            stmt = conexion.createStatement();
            res = stmt.executeQuery(punto);
            
            while (res.next()) {                
                    int idPP=0;
                if(res.getString(7)!=null){
                    idPP=Integer.parseInt(res.getString(7));
                }
                    String idSig=res.getString(8);
                if(idPP==idR && idSig==null){
                    idP=Integer.parseInt(res.getString(1));
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
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+idPc+" WHERE id_pc="+sig);
                insercion.executeUpdate();
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET ruta="+idR+" WHERE id_pc="+idPc);
                insercion.executeUpdate();
            } catch (Exception ex) {
                System.out.println("Fallo en existente");
                ex.getMessage();
                ex.printStackTrace();
            }
            
        }else{
            try {
                System.out.println("primeor");
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET ruta="+idR+" WHERE id_pc="+idPc);
                insercion.executeUpdate();
                System.out.println("segudo");
                insercion = conexion.prepareStatement("UPDATE ruta SET pc_inicio="+idPc+" WHERE id_ruta="+idR);
                insercion.executeUpdate();
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
            stmt = conexion.createStatement();
            res = stmt.executeQuery(punto);
            
            while (res.next()) {                
                    int idPP=0;
                if(res.getString(8)!=null){
                    idPP=Integer.parseInt(res.getString(8));
                }
                    //String idSig=res.getString(8);
                if(idPP==idPCsele ){
                    idP=Integer.parseInt(res.getString(1));
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
            stmt = conexion.createStatement();
            res = stmt.executeQuery(punto);
            existe=false;
            while (res.next()) {
                int nu=Integer.parseInt(res.getString(1));
                if(nu==pcID){                    
                    if(res.getString(8)==null){
                        existe=false;
                        break;
                    }else{
                        existe=true;
                        break;
                    }
                }    
            }  
        } catch (SQLException e) {
            System.out.println("Fallo en el retorno de existente");
            e.getMessage();
            e.printStackTrace();
        }
        return existe;
    }
    
    public void eliminarPCdeRuta(int idPCdelet, int idsig){
        
        if(buscarPcsinAputador(idPCdelet)){
            try {
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+idsig+" WHERE id_pc="+apuntadorPC(idPCdelet));
                insercion.executeUpdate();
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente= NULL WHERE id_pc="+idPCdelet);
                insercion.executeUpdate();
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET ruta= NULL WHERE id_pc="+idPCdelet);
                insercion.executeUpdate();
            } catch (SQLException ex) {
                ex.getMessage();
            ex.printStackTrace();
            }
            
        }else{
            try {
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET ruta= NULL WHERE id_pc="+idPCdelet);
                insercion.executeUpdate();
            } catch (SQLException ex) {
                ex.getMessage();
            ex.printStackTrace();
            }
                
        }
    }
    
    public void cambiarPCs(int idsele1, int idsele2, String sig1 , String sig2) throws SQLException{
        
        if(sig1==null){
            if(sig2==null){
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente=NULL WHERE id_pc="+idsele1);
                insercion.executeUpdate();
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente=NULL WHERE id_pc="+idsele2);
                insercion.executeUpdate();
            }else{
                int numero = Integer.parseInt(sig2);
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+numero+" WHERE id_pc="+idsele1);
                insercion.executeUpdate();
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente=NULL WHERE id_pc="+idsele2);
                insercion.executeUpdate();
            }
            
        }else if(sig2==null){
            if(sig1==null){
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente=NULL WHERE id_pc="+idsele1);
                insercion.executeUpdate();
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente=NULL WHERE id_pc="+idsele2);
                insercion.executeUpdate();
            }else{
                int numero = Integer.parseInt(sig1);
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente=Null WHERE id_pc="+idsele1);
                insercion.executeUpdate();
                insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+numero+" WHERE id_pc="+idsele2);
                insercion.executeUpdate();
            }
        }else{
            int numero2 = Integer.parseInt(sig2);
            int numero1 = Integer.parseInt(sig1);
            insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+numero2+" WHERE id_pc="+idsele1);
            insercion.executeUpdate();
            
            insercion = conexion.prepareStatement("UPDATE punto_de_control SET id_siguiente="+numero1+" WHERE id_pc="+idsele2);
            insercion.executeUpdate();
        }
    }
    
    public void crearNuevoCliente(int nit,String nombre, String direccion){
        try {
            insercion = conexion.prepareStatement("INSERT INTO cliente (nit,nombre_cliente,direccion) VALUES ("+nit+",'"+nombre+"','"+direccion+"');");
            insercion.executeUpdate();
            System.out.println("Listo creo");
        } catch (SQLException ex) {
           ex.getMessage();
            ex.printStackTrace();
        }
    }
    
    public void actualzarCliente (int nit, String nombre , String direccion){
        try {
            insercion = conexion.prepareStatement("UPDATE cliente SET nombre_cliente='"+nombre+"', direccion='"+direccion+"' WHERE nit="+nit+";");
            insercion.executeUpdate();
            System.out.println("Listo actualizo");
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();        }
    }
    public void crearPaquete(double peso, int nitR, int priori, double tarifa, String destino, double cuota){
        
        
        try {
            insercion = conexion.prepareStatement("INSERT INTO paquete (peso,nit_remitente,priorizacion,tarifa_pq,destino_pq,cuota) VALUES ("+peso+","+nitR+","+priori+","+tarifa+",'"+destino+"',"+cuota+");");
            insercion.executeUpdate();
            stmt = conexion.createStatement();
            res = stmt.executeQuery("SELECT * FROM paquete");
            int idPa = 0;
            while (res.next()) {
                
                idPa=  res.getShort(1);
            }
            insercion = conexion.prepareStatement("INSERT INTO bodega (destino_pqte,priorizado_pq,id_pqt) VALUES ('"+destino+"',"+priori+","+idPa+");");
            insercion.executeUpdate();
            
            //Crear espacion en bodega
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace(); 
        }
    }
}

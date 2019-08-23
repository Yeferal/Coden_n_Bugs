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
    Ruta rutaC = new Ruta(this);
    Registro registroC = new Registro(this);
    PC_Conect pcConeC = new PC_Conect(this);
    Arrivados arrivadosC = new Arrivados(this);
    
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
    //Creaa un nuevo usuario
    public void insertarUsuario(Usuario use) throws SQLException{
        insercion = conexion.prepareStatement("INSERT INTO usuario (nombre,codigo_usuario,tipo,pass) VALUES ('"+use.getNombre()+"','"+use.getCodigo()+"',"+use.gettipo()+",'"+use.getPassword()+"');");
        insercion.executeUpdate();
    }
    //Busca si existe un usuario con estos astributos dentro de la base de datos
    //y me retorna su existencia
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
        } catch (Exception e) {} 
        return existe;
    }
    //retorna el tiempo actual de la base de datos
    public String obtenertime() throws SQLException{
        stmt = conexion.createStatement();
        res = stmt.executeQuery("select now();");
        while(res.next()){
            return res.getString(1);
        }
        return res.getString(1);
    }
    //Modifica los atributos de un usuario
    public void editarUsuario(int id, String Nombre, String codigo, int tipo, String contrasena) throws SQLException{
        insercion = conexion.prepareStatement("UPDATE usuario SET nombre='"+Nombre+"', codigo_usuario='"+codigo+"', tipo="+tipo+",pass='"+contrasena+"' WHERE id_usuario="+id);
        insercion.executeUpdate(); 
    }
    //Eliminda un usuario
    public void eliminarUsuario(int id){
        try {
            insercion = conexion.prepareStatement("DELETE FROM usuario WHERE id_usuario="+id);
            insercion.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    //crea un nuevo punto de control
    public void insertarPC(Punto_de_Control pc) throws SQLException{
        insercion = conexion.prepareStatement("INSERT INTO punto_de_control (nombre_pc,tarifa,capacidad,operador_asignado) VALUES ('"+pc.getNombre()+"','"+pc.getTarifa()+"',"+pc.getCapacidad()+",'"+pc.getOperadorAsig()+"');");
        insercion.executeUpdate();
    }
    //setea los atributos de un ponto de control en especifico
    public void editarPC(int id, String Nombre, double tarifa, int capacidad, int operador_Asig) throws SQLException{
        insercion = conexion.prepareStatement("UPDATE punto_de_control SET nombre_pc='"+Nombre+"', tarifa='"+tarifa+"', capacidad="+capacidad+",operador_asignado='"+operador_Asig+"' WHERE id_pc="+id);
        insercion.executeUpdate();    
    }
    //Elimina un punto de control de la tabla
    public void eliminarPC(int id){
        try {
            if(!pcConeC.validarPaquetesPC(Integer.toString(id))){
                insercion = conexion.prepareStatement("DELETE FROM punto_de_control WHERE id_pc="+id);
            insercion.executeUpdate();
            }
            
        } catch (SQLException ex) {
        }
    }
    
    public void nuevoDestino(String destino, String tarifa){
        try {
            insercion = conexion.prepareStatement("INSERT INTO destinos (nombre,cuota_destino) VALUES ('"+destino+"',"+tarifa+");");
            insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }
    
    public void modificarDestnio(String destinoActual, String destino, String tarifa) throws SQLException{
        
        insercion = conexion.prepareStatement("UPDATE destinos SET nombre='"+destino+"',cuota_destino="+tarifa+" WHERE nombre='"+destinoActual+"';");
        insercion.executeUpdate();
    }
    public void eliminarDestino(String destino) throws SQLException{
        insercion = conexion.prepareStatement("DELETE FROM destinos WHERE nombre='"+destino+"';");
            insercion.executeUpdate();
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
    public void actualizarTarifa(int tarifa){
        try {
            insercion = conexion.prepareStatement("UPDATE registro SET tarifa_global="+tarifa+";");
            insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }
    public void actualizarLibras(int tarifa){
        try {
            insercion = conexion.prepareStatement("UPDATE registro SET precio_libra="+tarifa+";");
            insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }
    public void actualizarPriosizacion(int tarifa){
        try {
            insercion = conexion.prepareStatement("UPDATE registro SET precio_priorizacion="+tarifa+";");
            insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }
}

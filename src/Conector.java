
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
}

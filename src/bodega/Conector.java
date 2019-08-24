
package bodega;


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
    String bodega = "SELECT * FROM bodega;";
    
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
    
    public String obtenertime() throws SQLException{
        stmt = conexion.createStatement();
        res = stmt.executeQuery("select now();");
        while(res.next()){
            return res.getString(1);
        }
        return res.getString(1);
    }
        
    public void setDataTime(int id) throws SQLException{
        insercion = conexion.prepareStatement("UPDATE paquete SET tiempo_pc='"+obtenertime()+"', tiempo_ingreso='"+obtenertime()+"' WHERE id_paquete="+id+";");
        insercion.executeUpdate();
    }
    
    
    
    public void setDestinarPaquete(String idRuta){
        int endestinoNuevo = getEnDestinoPaq(idRuta)+1;
        try {
            insercion = conexion.prepareStatement("UPDATE ruta SET paquetes_endestino="+endestinoNuevo+" WHERE id_ruta="+idRuta+";");
            insercion.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
    private int getEnDestinoPaq(String idRuta){
        int destinoP= 0;
        try {
            stmt = conexion.createStatement();
            res = stmt.executeQuery("SELECT * FROM ruta WHERE id_ruta="+idRuta+";");
            res.next();
            destinoP = Integer.parseInt(res.getString(10));
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        return destinoP;
    }
   
}

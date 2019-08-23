
package Codenbugs;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class PC_Conect {
    Conector conex;
    
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
}

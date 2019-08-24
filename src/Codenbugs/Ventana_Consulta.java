
package Codenbugs;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Ventana_Consulta extends javax.swing.JInternalFrame {

    Ventana_Recepcionista vRecepcionista;
    public Ventana_Consulta(Ventana_Recepcionista vRecepcionista) {
        initComponents();
        this.vRecepcionista = vRecepcionista;
    }
    
    public void llenart(String nit, String id){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("ID");
        modelo1.addColumn("Peso");
        modelo1.addColumn("NIT");
        modelo1.addColumn("Localizacion");
        modelo1.addColumn("Dias en Ruta");
       
        tablaDeConsulta.setModel(modelo1);
        String datos[]= new String[5];
        try {
            setNits(nit);
            vRecepcionista.marco.vLogin.conect.stmt = vRecepcionista.marco.vLogin.conect.conexion.createStatement();
            vRecepcionista.marco.vLogin.conect.res = vRecepcionista.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM paquete WHERE nit_Remitente="+nit+";");
            
            while(vRecepcionista.marco.vLogin.conect.res.next()){
                datos[0]=vRecepcionista.marco.vLogin.conect.res.getString(1);
                datos[1]=vRecepcionista.marco.vLogin.conect.res.getString(2);
                datos[2]=vRecepcionista.marco.vLogin.conect.res.getString(3);
                System.out.println(vRecepcionista.marco.vLogin.conect.res.getString(4));
                if(vRecepcionista.marco.vLogin.conect.res.getString(4)==null){
                    datos[3]="En Bodega";
                }else if(vRecepcionista.marco.vLogin.conect.res.getString(4).equals("0")){
                    datos[3]="Arrivado";
                }else{
                    datos[3]=vRecepcionista.marco.vLogin.conect.res.getString(4);
                }
                datos[4]=vRecepcionista.marco.vLogin.conect.res.getString(13);
                modelo1.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }

    }
    private void setNits(String nit){
        
        try {
            vRecepcionista.marco.vLogin.conect.stmt = vRecepcionista.marco.vLogin.conect.conexion.createStatement();
            vRecepcionista.marco.vLogin.conect.res = vRecepcionista.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM paquete WHERE nit_Remitente="+nit+";");
            while(vRecepcionista.marco.vLogin.conect.res.next()){
                setDias(vRecepcionista.marco.vLogin.conect.res.getString(3),vRecepcionista.marco.vLogin.conect.res.getString(12));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void setDias(String nit, String fecha){
        if(fecha!=null){
            vRecepcionista.marco.vLogin.conect.arrivadosC.setTiempo(nit,fecha);
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDeConsulta = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        tablaDeConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaDeConsulta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDeConsulta;
    // End of variables declaration//GEN-END:variables
}

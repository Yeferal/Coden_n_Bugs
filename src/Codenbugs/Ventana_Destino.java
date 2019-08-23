
package Codenbugs;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ventana_Destino extends javax.swing.JInternalFrame {
    
    Ventana_Admin vRecep;
    String destino;
    boolean seleccion;
    
    public Ventana_Destino(Ventana_Admin vRecep) {
        initComponents();
        this.vRecep = vRecep;
        
    }

    
    public void llenarTabla(){
        DefaultTableModel modelo1 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }  
            };
        
            modelo1.addColumn("Destino");
            modelo1.addColumn("Cuota Destino");
        
            tablaDestinos.setModel(modelo1);
            String datos[]= new String[2];
            
        try {
            vRecep.marco.vLogin.conect.stmt = vRecep.marco.vLogin.conect.conexion.createStatement();
            vRecep.marco.vLogin.conect.res = vRecep.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM destinos;");
            while(vRecep.marco.vLogin.conect.res.next()){
                datos[0] = vRecep.marco.vLogin.conect.res.getString(1);
                datos[1] = vRecep.marco.vLogin.conect.res.getString(2);
                modelo1.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }
    public void resetearCajas(){
        cajaDestino.setText("");
        cajatarifa.setText("");
        seleccion=false;
        destino=null;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDestinos = new javax.swing.JTable();
        botonModificar = new javax.swing.JButton();
        cajaDestino = new javax.swing.JTextField();
        cajatarifa = new javax.swing.JTextField();
        labelTarifa = new javax.swing.JLabel();
        labelDestino = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        botonEliminar = new javax.swing.JButton();
        botonActualziar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaDestinos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaDestinos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDestinosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaDestinos);

        botonModificar.setText("Modificar");
        botonModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonModificarMouseClicked(evt);
            }
        });

        labelTarifa.setText("Tarifa Global:");

        labelDestino.setText("Destino:");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("hagla clic al destino que desea\nmodificar o Eliminar'.\n");
        jScrollPane2.setViewportView(jTextArea1);

        botonEliminar.setText("Eliminar");
        botonEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEliminarMouseClicked(evt);
            }
        });

        botonActualziar.setText("Actualizar");
        botonActualziar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonActualziarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelTarifa)
                                    .addComponent(labelDestino))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cajatarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cajaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(botonActualziar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonEliminar)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cajaDestino, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDestino, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(cajatarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelTarifa, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(botonModificar)
                .addGap(35, 35, 35)
                .addComponent(botonEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonActualziar)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaDestinosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDestinosMouseClicked
        int fila=tablaDestinos.getSelectedRow();
        destino = tablaDestinos.getValueAt(fila, 0).toString();
        cajaDestino.setText(tablaDestinos.getValueAt(fila, 0).toString());
        cajatarifa.setText(tablaDestinos.getValueAt(fila, 1).toString());
        seleccion = true;
    }//GEN-LAST:event_tablaDestinosMouseClicked

    private void botonModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonModificarMouseClicked
        if(seleccion && !cajaDestino.getText().equals("") && !cajatarifa.getText().equals("")){
            try {
                vRecep.marco.vLogin.conect.modificarDestnio(destino, cajaDestino.getText(), cajatarifa.getText());
                JOptionPane.showMessageDialog(null, "Listo se Modifico el Destino");
                resetearCajas();
            } catch (SQLException ex) {
                ex.getMessage();
                ex.printStackTrace();
            }
        }
        llenarTabla();
    }//GEN-LAST:event_botonModificarMouseClicked

    private void botonEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarMouseClicked
      if(seleccion){
          try {
              vRecep.marco.vLogin.conect.eliminarDestino(destino);
              JOptionPane.showMessageDialog(null, "Listo se Elimino el Destino");
              resetearCajas();
              llenarTabla();
          } catch (SQLException ex) {
              ex.getMessage();
              ex.printStackTrace();
          }
      }
    }//GEN-LAST:event_botonEliminarMouseClicked

    private void botonActualziarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActualziarMouseClicked
        llenarTabla();
    }//GEN-LAST:event_botonActualziarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualziar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JTextField cajaDestino;
    private javax.swing.JTextField cajatarifa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelDestino;
    private javax.swing.JLabel labelTarifa;
    private javax.swing.JTable tablaDestinos;
    // End of variables declaration//GEN-END:variables
}

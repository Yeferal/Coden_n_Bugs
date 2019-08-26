
package Codenbugs;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;


public class Ventana_EstadoRuta extends javax.swing.JInternalFrame {

    Ventana_Admin vAdmin;
    private String id;
    private boolean seleccion;
    
    public Ventana_EstadoRuta(Ventana_Admin vAdmin) {
        initComponents();
        this.vAdmin = vAdmin;
    }
    /**agrega el modelo y los registros a la tabla**/
    public void llenarTabla(){
        DefaultTableModel modelo1 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }  
            };
            modelo1.addColumn("ID");
            modelo1.addColumn("Destino");
            modelo1.addColumn("Estado");
        
            tablaRutas.setModel(modelo1);
            String datos[]= new String[3];
            
        try {
            vAdmin.marco.vLogin.conect.stmt = vAdmin.marco.vLogin.conect.conexion.createStatement();
            vAdmin.marco.vLogin.conect.res = vAdmin.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM ruta;");
            while(vAdmin.marco.vLogin.conect.res.next()){
                datos[0] = vAdmin.marco.vLogin.conect.res.getString(1);
                datos[1] = vAdmin.marco.vLogin.conect.res.getString(3);
                if(vAdmin.marco.vLogin.conect.res.getString(4).equals("1")){
                    datos[2]="Activo";
                }else{
                    datos[2]="Desactivado";
                }
                modelo1.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }
    /**limpi los textos y actualiza la tabla**/
    private void resetearDatos(){
        txtNombre.setText("");
            seleccion=false;
            id = null;
            llenarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEstado = new javax.swing.JPanel();
        botonAct = new javax.swing.JButton();
        botonDes = new javax.swing.JButton();
        labelNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        botonActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRutas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonAct.setText("Activar");
        botonAct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonActMouseClicked(evt);
            }
        });

        botonDes.setText("Descativar");
        botonDes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonDesMouseClicked(evt);
            }
        });

        labelNombre.setText("Nombre");

        botonActualizar.setText("Actualizar");
        botonActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonActualizarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelEstadoLayout = new javax.swing.GroupLayout(panelEstado);
        panelEstado.setLayout(panelEstadoLayout);
        panelEstadoLayout.setHorizontalGroup(
            panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelEstadoLayout.createSequentialGroup()
                .addComponent(botonDes, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelEstadoLayout.createSequentialGroup()
                .addGroup(panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAct, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEstadoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(labelNombre))
                    .addGroup(panelEstadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonActualizar)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        panelEstadoLayout.setVerticalGroup(
            panelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonAct)
                .addGap(18, 18, 18)
                .addComponent(botonDes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(labelNombre)
                .addGap(18, 18, 18)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(botonActualizar)
                .addContainerGap())
        );

        getContentPane().add(panelEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 110, 300));

        tablaRutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaRutas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRutasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaRutas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActualizarMouseClicked
        llenarTabla();
    }//GEN-LAST:event_botonActualizarMouseClicked

    private void tablaRutasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRutasMouseClicked
        int fila=tablaRutas.getSelectedRow();
        id = tablaRutas.getValueAt(fila, 0).toString();
        txtNombre.setText(tablaRutas.getValueAt(fila, 1).toString());
        seleccion = true;
    }//GEN-LAST:event_tablaRutasMouseClicked

    private void botonActMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActMouseClicked
        if(seleccion){
            vAdmin.marco.vLogin.conect.rutaC.activar(id);
            resetearDatos();
        }
        
    }//GEN-LAST:event_botonActMouseClicked

    private void botonDesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonDesMouseClicked
        if(seleccion){
            vAdmin.marco.vLogin.conect.rutaC.desactivar(id);
            resetearDatos();
        }
    }//GEN-LAST:event_botonDesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAct;
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonDes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JPanel panelEstado;
    private javax.swing.JTable tablaRutas;
    private javax.swing.JLabel txtNombre;
    // End of variables declaration//GEN-END:variables
}

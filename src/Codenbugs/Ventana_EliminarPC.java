package Codenbugs;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Ventana_EliminarPC extends javax.swing.JInternalFrame {

    Ventana_Admin vadmin;
    int ids;
    boolean seleccionado;
    
    public Ventana_EliminarPC(Ventana_Admin vadmin) {
        initComponents();
        this.vadmin=vadmin;
    }

        
    public void tablaPc(){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("ID");
        modelo1.addColumn("Nombre");
        modelo1.addColumn("Tarifa");
        modelo1.addColumn("Capacidad");
        modelo1.addColumn("Operador Asignado");

        tablaPC.setModel(modelo1);
        String datos[]= new String[5];
        try {
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery(vadmin.punto);
            
            while (vadmin.marco.vLogin.conect.res.next()) {                
                //System.out.println(marco.vLogin.conect.res.getString(1));
                datos[0]=vadmin.marco.vLogin.conect.res.getString(1);
                datos[1]=vadmin.marco.vLogin.conect.res.getString(2);
                datos[2]=vadmin.marco.vLogin.conect.res.getString(4);
                datos[3]=vadmin.marco.vLogin.conect.res.getString(5);
                datos[4]=vadmin.marco.vLogin.conect.res.getString(6);

                modelo1.addRow(datos);      
            }  
        } catch (Exception e) {    
        }
    }
    
    private void limpiar(){
        labeId.setText("");
        labelname.setText("");
        labelTarifa.setText("");
        labelCapacidad.setText("");
    }
    public void enviarPc(int id){
        
        vadmin.marco.vLogin.conect.eliminarPC(id);
        seleccionado=false;
        limpiar();
        tablaPc();
        vadmin.tablaPC();
        
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEliminarUser = new javax.swing.JPanel();
        scrollEliminar = new javax.swing.JScrollPane();
        tablaPC = new javax.swing.JTable();
        bonteliminat = new javax.swing.JButton();
        textoID = new javax.swing.JLabel();
        textoNombre = new javax.swing.JLabel();
        textoTarifa = new javax.swing.JLabel();
        textoCapacidad = new javax.swing.JLabel();
        labeId = new javax.swing.JLabel();
        labelname = new javax.swing.JLabel();
        labelTarifa = new javax.swing.JLabel();
        labelCapacidad = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaPC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPCMouseClicked(evt);
            }
        });
        scrollEliminar.setViewportView(tablaPC);

        bonteliminat.setText("Eliminar PC");
        bonteliminat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bonteliminatMouseClicked(evt);
            }
        });

        textoID.setText("ID:");

        textoNombre.setText("Nombre:");

        textoTarifa.setText("Tarifa:");

        textoCapacidad.setText("Capacidad:");

        javax.swing.GroupLayout panelEliminarUserLayout = new javax.swing.GroupLayout(panelEliminarUser);
        panelEliminarUser.setLayout(panelEliminarUserLayout);
        panelEliminarUserLayout.setHorizontalGroup(
            panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEliminarUserLayout.createSequentialGroup()
                        .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textoCapacidad)
                            .addComponent(textoNombre)
                            .addComponent(textoTarifa)
                            .addComponent(textoID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labeId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelTarifa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelCapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)))
                    .addComponent(bonteliminat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        panelEliminarUserLayout.setVerticalGroup(
            panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarUserLayout.createSequentialGroup()
                .addComponent(scrollEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEliminarUserLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoID)
                    .addComponent(labeId, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNombre)
                    .addComponent(labelname, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoTarifa)
                    .addComponent(labelTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoCapacidad)
                    .addComponent(labelCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bonteliminat)
                .addGap(18, 18, 18))
        );

        getContentPane().add(panelEliminarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPCMouseClicked

        int fila=tablaPC.getSelectedRow();
        ids=  Integer.parseInt((String) tablaPC.getValueAt(fila, 0));

        labeId.setText(tablaPC.getValueAt(fila, 0).toString());
        labelname.setText(tablaPC.getValueAt(fila, 1).toString());
        labelTarifa.setText(tablaPC.getValueAt(fila,2).toString());
        labelCapacidad.setText(tablaPC.getValueAt(fila, 3).toString());
        seleccionado=true;
    }//GEN-LAST:event_tablaPCMouseClicked

    private void bonteliminatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bonteliminatMouseClicked
        // TODO add your handling code here:
        if(seleccionado){
            enviarPc(ids);
            JOptionPane.showMessageDialog(null, "Se elimino correctamente el Punto de control");
        }
    }//GEN-LAST:event_bonteliminatMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bonteliminat;
    private javax.swing.JLabel labeId;
    private javax.swing.JLabel labelCapacidad;
    private javax.swing.JLabel labelTarifa;
    private javax.swing.JLabel labelname;
    private javax.swing.JPanel panelEliminarUser;
    private javax.swing.JScrollPane scrollEliminar;
    private javax.swing.JTable tablaPC;
    private javax.swing.JLabel textoCapacidad;
    private javax.swing.JLabel textoID;
    private javax.swing.JLabel textoNombre;
    private javax.swing.JLabel textoTarifa;
    // End of variables declaration//GEN-END:variables
}

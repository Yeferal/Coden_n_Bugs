package Codenbugs;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Ventana_Nuevo_Destino extends javax.swing.JInternalFrame {

    Ventana_Admin vAdmin;
    
    public Ventana_Nuevo_Destino(Ventana_Admin vAdmin) {
        initComponents();
        this.vAdmin = vAdmin;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botonCrear = new javax.swing.JButton();
        cajaDestino = new javax.swing.JTextField();
        cajatarifa = new javax.swing.JTextField();
        labelTarifa = new javax.swing.JLabel();
        labelDestino = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonCrear.setText("Crear");
        botonCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonCrearMouseClicked(evt);
            }
        });

        labelTarifa.setText("Tarifa Global:");

        labelDestino.setText("Destino:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDestino)
                    .addComponent(labelTarifa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajatarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelDestino)
                            .addComponent(cajaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(labelTarifa))
                    .addComponent(cajatarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonCrear)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCrearMouseClicked
        if(!cajaDestino.getText().equals("") && !cajatarifa.getText().equals("")){
            vAdmin.marco.vLogin.conect.nuevoDestino(cajaDestino.getText(),cajatarifa.getText());
            cajaDestino.setText("");
            cajatarifa.setText("");
            JOptionPane.showMessageDialog(null, "Listo se Creo el Destino");
        }
    }//GEN-LAST:event_botonCrearMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCrear;
    private javax.swing.JTextField cajaDestino;
    private javax.swing.JTextField cajatarifa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelDestino;
    private javax.swing.JLabel labelTarifa;
    // End of variables declaration//GEN-END:variables
}

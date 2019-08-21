package Codenbugs;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ventana_Nueva_Ruta extends javax.swing.JInternalFrame {

    Ventana_Admin vadmin;
    
    public Ventana_Nueva_Ruta(Ventana_Admin vadmin) {
        initComponents();
        this.vadmin=vadmin;
    }

    private void enviarRuta(String inicio, String destino, double cuota){
        
        if(!cajaCuota.getText().equals("")){
            
            try {
                vadmin.marco.vLogin.conect.insertarRuta(inicio, destino, cuota);
                System.out.println("Creo");
                cajaCuota.setText("");
                vadmin.tablaRuta();
            } catch (SQLException ex) {
                System.out.println("Fallo");
                ex.getMessage();
            ex.printStackTrace();
            }
        }
        
        
    }
    
    public void llenarcombos() throws SQLException{
        comboDestino.removeAllItems();
        comboInicio.removeAllItems();
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery(vadmin.destinos);
            
            while (vadmin.marco.vLogin.conect.res.next()) {                
                comboDestino.addItem(vadmin.marco.vLogin.conect.res.getString(1));
                comboInicio.addItem(vadmin.marco.vLogin.conect.res.getString(1));      
            }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelInicio = new javax.swing.JLabel();
        labelDestino = new javax.swing.JLabel();
        labelCuota = new javax.swing.JLabel();
        comboInicio = new javax.swing.JComboBox<>();
        comboDestino = new javax.swing.JComboBox<>();
        cajaCuota = new javax.swing.JFormattedTextField();
        botonCrear = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelInicio.setText("Inicio:");

        labelDestino.setText("Destino:");

        labelCuota.setText("Cuota de Destino:");

        cajaCuota.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        cajaCuota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cajaCuotaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDestino)
                    .addComponent(labelCuota)
                    .addComponent(labelInicio))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCuota)
                    .addComponent(cajaCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, -1, -1));

        botonCrear.setText("Crear");
        botonCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonCrearMouseClicked(evt);
            }
        });
        getContentPane().add(botonCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cajaCuotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cajaCuotaKeyTyped

        char c = evt.getKeyChar();
        
        if(c<'0' || c>'9') evt.consume();
        
    }//GEN-LAST:event_cajaCuotaKeyTyped

    private void botonCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCrearMouseClicked
        
        
        enviarRuta(comboInicio.getSelectedItem().toString(), comboDestino.getSelectedItem().toString(), Double.parseDouble(cajaCuota.getText()));
    }//GEN-LAST:event_botonCrearMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCrear;
    private javax.swing.JFormattedTextField cajaCuota;
    private javax.swing.JComboBox<String> comboDestino;
    private javax.swing.JComboBox<String> comboInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelCuota;
    private javax.swing.JLabel labelDestino;
    private javax.swing.JLabel labelInicio;
    // End of variables declaration//GEN-END:variables
}

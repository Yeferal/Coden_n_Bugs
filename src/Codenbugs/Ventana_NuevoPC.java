package Codenbugs;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Ventana_NuevoPC extends javax.swing.JInternalFrame {

    Ventana_Admin vadmin;
    boolean seleccionado;
    
    
    public Ventana_NuevoPC(Ventana_Admin vadmin) {
        initComponents();
        this.vadmin=vadmin;
    }
    
    public boolean verifiacardatos(String cad1, String cad2, String cad3){
        int num1, num2, num3;
        
        try {
            num1=Integer.parseInt(cad1);
            num2=Integer.parseInt(cad2);
            num3=Integer.parseInt(cad3);
            
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los datos no son lo correctos");
            return false;
        }
        
    }
    public void limpiar(){
        cajaNombre.setText("");
        cajaCapacidad.setText("");
        cajaTrifa.setText("");
        cajauserasig.setText("");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNuevoUsuario = new javax.swing.JPanel();
        textoNombre = new javax.swing.JLabel();
        textoTipo = new javax.swing.JLabel();
        textoTarifa = new javax.swing.JLabel();
        textoUsuerAsign = new javax.swing.JLabel();
        cajaNombre = new javax.swing.JTextField();
        cajaTrifa = new javax.swing.JTextField();
        cajauserasig = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        botonUCrear = new javax.swing.JButton();
        cajaCapacidad = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textoNombre.setText("Nombre:");

        textoTipo.setText("Capacidad de Paquetes:");

        textoTarifa.setText("Tarifa:");

        textoUsuerAsign.setText("Operador Asignado (ID):");

        jLabel1.setText("El operador asignado puede omitirlo no es Obligatorio");

        botonUCrear.setText("Crear");
        botonUCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonUCrearMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelNuevoUsuarioLayout = new javax.swing.GroupLayout(panelNuevoUsuario);
        panelNuevoUsuario.setLayout(panelNuevoUsuarioLayout);
        panelNuevoUsuarioLayout.setHorizontalGroup(
            panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoUsuarioLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTipo)
                    .addComponent(textoUsuerAsign)
                    .addComponent(textoTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonUCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(cajaNombre)
                        .addComponent(cajaTrifa, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                        .addComponent(cajauserasig)
                        .addComponent(cajaCapacidad)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelNuevoUsuarioLayout.setVerticalGroup(
            panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoUsuarioLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(cajaCapacidad))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoTarifa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cajaTrifa, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoUsuerAsign, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajauserasig, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(botonUCrear)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(panelNuevoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonUCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonUCrearMouseClicked
        
        if((verifiacardatos(cajaCapacidad.getText(), cajaTrifa.getText(), cajauserasig.getText())) && !cajaNombre.getText().equals("")){
            Punto_de_Control tmpPc = new Punto_de_Control();
            tmpPc.setDatos(cajaNombre.getText(), Double.parseDouble(cajaTrifa.getText()), Integer.parseInt(cajaCapacidad.getText()), Integer.parseInt(cajauserasig.getText()));
            try {
                vadmin.marco.vLogin.conect.insertarPC(tmpPc);
            } catch (SQLException ex) {
                Logger.getLogger(Ventana_NuevoPC.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Se creo el Punto de Control correctamente");
            limpiar();
            vadmin.tablaPC();
        }else{
            
        }
        
    }//GEN-LAST:event_botonUCrearMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonUCrear;
    private javax.swing.JTextField cajaCapacidad;
    private javax.swing.JTextField cajaNombre;
    private javax.swing.JTextField cajaTrifa;
    private javax.swing.JTextField cajauserasig;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelNuevoUsuario;
    private javax.swing.JLabel textoNombre;
    private javax.swing.JLabel textoTarifa;
    private javax.swing.JLabel textoTipo;
    private javax.swing.JLabel textoUsuerAsign;
    // End of variables declaration//GEN-END:variables
}

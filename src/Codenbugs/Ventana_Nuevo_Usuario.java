package Codenbugs;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Ventana_Nuevo_Usuario extends javax.swing.JInternalFrame {

    Ventana_Admin vadmin;
    Usuario usuario;
    
    public Ventana_Nuevo_Usuario(Ventana_Admin vadmin) {
        initComponents();
        this.vadmin=vadmin;
        
    }
    
    private int tipoUserr(){
        if(comboUTipo.getSelectedItem().equals("Administrador")){
            return 1;
        }else if (comboUTipo.getSelectedItem().equals("Operador")){
            return 2;
        }
        return 3;
    }
    
    
    private void agregar(){
            usuario= new Usuario();
            if(!cajaUNombre.getText().equals("") && !cajaUContrasenia.getText().equals("") && !cajaUCodigo.getText().equals("")){
                    usuario.setDatos(cajaUNombre.getText(), cajaUCodigo.getText(), tipoUserr(), cajaUContrasenia.getText());
                try {
                    vadmin.marco.vLogin.conect.insertarUsuario(usuario);
                } catch (SQLException ex) {
                   
               }
                JOptionPane.showMessageDialog(null, "Se agrego Correctamente");
                cajaUNombre.setText("");
                cajaUCodigo.setText("");
                cajaUContrasenia.setText("");
                vadmin.tablaUser();
                    
           }else{
                JOptionPane.showMessageDialog(null, "Todos los campos deben de esta llenos");
            }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNuevoUsuario = new javax.swing.JPanel();
        textoUNombre = new javax.swing.JLabel();
        textoUTipo = new javax.swing.JLabel();
        textoUCodigo = new javax.swing.JLabel();
        textoUContrasenia = new javax.swing.JLabel();
        cajaUNombre = new javax.swing.JTextField();
        comboUTipo = new javax.swing.JComboBox<>();
        cajaUCodigo = new javax.swing.JTextField();
        cajaUContrasenia = new javax.swing.JTextField();
        botonUCrear = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        textoUNombre.setText("Nombre:");

        textoUTipo.setText("Tipo de Usuario:");

        textoUCodigo.setText("Codigo: ");

        textoUContrasenia.setText("Contraseña: ");

        comboUTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Operador", "Recepcionista" }));

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
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNuevoUsuarioLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoUNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoUTipo)
                            .addComponent(textoUCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoUContrasenia))
                        .addGap(36, 36, 36)
                        .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cajaUNombre)
                            .addComponent(comboUTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cajaUCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addComponent(cajaUContrasenia)))
                    .addGroup(panelNuevoUsuarioLayout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(botonUCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        panelNuevoUsuarioLayout.setVerticalGroup(
            panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoUsuarioLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoUNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaUNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoUTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboUTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoUCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cajaUCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoUContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaUContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonUCrear)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonUCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonUCrearMouseClicked

        if(vadmin.marco.vLogin.conect.buscar(cajaUCodigo.getText(), cajaUContrasenia.getText())){
            JOptionPane.showMessageDialog(null, "Existe un usuario con los mismos datos");
        }else{
            agregar();
        }
        
        
    }//GEN-LAST:event_botonUCrearMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonUCrear;
    private javax.swing.JTextField cajaUCodigo;
    private javax.swing.JTextField cajaUContrasenia;
    private javax.swing.JTextField cajaUNombre;
    private javax.swing.JComboBox<String> comboUTipo;
    private javax.swing.JPanel panelNuevoUsuario;
    private javax.swing.JLabel textoUCodigo;
    private javax.swing.JLabel textoUContrasenia;
    private javax.swing.JLabel textoUNombre;
    private javax.swing.JLabel textoUTipo;
    // End of variables declaration//GEN-END:variables
}

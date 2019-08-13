
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.ParseConversionEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO-PC
 */
public class Editar_Usuario extends javax.swing.JInternalFrame {

    Ventana_Admin vadmin;
    int tip;
    boolean seleccionado;
    
    public Editar_Usuario(Ventana_Admin vadmin) {
        initComponents();
        this.vadmin=vadmin;
        tablaUser();
    }
    
        public void tablaUser(){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("ID");
        modelo1.addColumn("Nombre");
        modelo1.addColumn("Codigo");
        modelo1.addColumn("Tipo");
        modelo1.addColumn("Password");
       
        tablaUsuarios.setModel(modelo1);
        String datos[]= new String[5];
        try {
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery(vadmin.usuarios);
            
            while (vadmin.marco.vLogin.conect.res.next()) {                
                //System.out.println(marco.vLogin.conect.res.getString(1));
                datos[0]=vadmin.marco.vLogin.conect.res.getString(1);
                datos[1]=vadmin.marco.vLogin.conect.res.getString(2);
                datos[2]=vadmin.marco.vLogin.conect.res.getString(3);
                datos[3]=vadmin.marco.vLogin.conect.res.getString(4);
                datos[4]=vadmin.marco.vLogin.conect.res.getString(5);
                modelo1.addRow(datos);
                
            }
            
        } catch (Exception e) {
            
        }
        
        
    }
        
    public void limpiar(){
            cajaUNombre.setText("");
            cajaUCodigo.setText("");
            cajaUContrasenia.setText("");
            texto_Id.setText("");
        }
        
    public void enviar_Modificacion() throws SQLException{
        int id;
        String Nombre;
        String codigo;
        int tipo = 0;
        String contrasena;
        String tipoS;
        if(!cajaUNombre.getText().equals("") && !cajaUCodigo.getText().equals("") && !cajaUContrasenia.getText().equals("")){
            id=tip;
            Nombre=cajaUNombre.getText();
            codigo=cajaUCodigo.getText();
            contrasena=cajaUContrasenia.getText();
            tipoS=(String) comboUTipo.getSelectedItem();
            //comboUTipo.getSelectedItem().toString()
            switch(tipoS){
                case "Administrador":
                    tipo=1;
                    break;
                case "Operador":
                    tipo=2;
                    break;
                case "Recepcionista":
                    tipo=3;
                    break;
                case "Desactivar":
                    tipo=0;
                    break;   
                default:
                        break;                       
            }
            
            vadmin.marco.vLogin.conect.editarUsuario(id, Nombre, codigo, tipo, contrasena);
            tablaUser();
            limpiar();
            seleccionado=false;
            vadmin.tablaUser();
        }else{
            
            JOptionPane.showMessageDialog(null, "No se encuentran todos los campos llenos");
        }
        
    }
        


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEditarUsuario = new javax.swing.JPanel();
        textoUNombre = new javax.swing.JLabel();
        textoUTipo = new javax.swing.JLabel();
        textoUCodigo = new javax.swing.JLabel();
        textoUContrasenia = new javax.swing.JLabel();
        cajaUNombre = new javax.swing.JTextField();
        comboUTipo = new javax.swing.JComboBox<>();
        cajaUCodigo = new javax.swing.JTextField();
        cajaUContrasenia = new javax.swing.JTextField();
        botonModificar = new javax.swing.JButton();
        textoDescripcion = new javax.swing.JLabel();
        textoID = new javax.swing.JLabel();
        texto_Id = new javax.swing.JLabel();
        scrollTabla = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textoUNombre.setText("Nombre:");

        textoUTipo.setText("Tipo de Usuario:");

        textoUCodigo.setText("Codigo:");

        textoUContrasenia.setText("Contraseña: ");

        comboUTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Operador", "Recepcionista", "Desactivar" }));

        botonModificar.setText("Modificar");
        botonModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonModificarMouseClicked(evt);
            }
        });

        textoDescripcion.setText("Haz clik a la fila que quieres editar. Tambien puedes deshabilitar un usuario cambiando el tipo de usuario.");

        textoID.setText("ID Usuario");

        javax.swing.GroupLayout panelEditarUsuarioLayout = new javax.swing.GroupLayout(panelEditarUsuario);
        panelEditarUsuario.setLayout(panelEditarUsuarioLayout);
        panelEditarUsuarioLayout.setHorizontalGroup(
            panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                    .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                        .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoUNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoUTipo)
                            .addComponent(textoUCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoUContrasenia))
                        .addGap(36, 36, 36)
                        .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cajaUNombre)
                            .addComponent(comboUTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cajaUCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                            .addComponent(cajaUContrasenia))
                        .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEditarUsuarioLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textoID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(texto_Id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(24, 24, 24)))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        panelEditarUsuarioLayout.setVerticalGroup(
            panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoUNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaUNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoID))
                .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoUTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboUTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
                    .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(texto_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoUCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cajaUCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
                    .addGroup(panelEditarUsuarioLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(botonModificar)))
                .addGap(33, 33, 33)
                .addGroup(panelEditarUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoUContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaUContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(panelEditarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 810, 260));

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaUsuarios.setToolTipText("");
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        scrollTabla.setViewportView(tablaUsuarios);

        getContentPane().add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 910, 290));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonModificarMouseClicked

        if(seleccionado){
            try {
                enviar_Modificacion();
            } catch (SQLException ex) {
                //Logger.getLogger(Editar_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   

    }//GEN-LAST:event_botonModificarMouseClicked

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        seleccionado=true;
        int fila=tablaUsuarios.getSelectedRow();
        
        cajaUNombre.setText( tablaUsuarios.getValueAt(fila, 1).toString());
        cajaUCodigo.setText( tablaUsuarios.getValueAt(fila, 2).toString());
        //cajaUNombre.setText((String) tablaUsuarios.getValueAt(fila, 3).toString());
        cajaUContrasenia.setText(tablaUsuarios.getValueAt(fila, 4).toString());
        texto_Id.setText(tablaUsuarios.getValueAt(fila, 0).toString());
        switch (tablaUsuarios.getValueAt(fila, 3).toString()) {
            case "1":
                comboUTipo.setSelectedIndex(0);
                break;
            case "2":
                comboUTipo.setSelectedIndex(1);
                break;
            case "3":
                comboUTipo.setSelectedIndex(2);
                break; 
            case "0":
                comboUTipo.setSelectedIndex(3);
                break;
            default:
                
                break;
        }
        tip=  Integer.parseInt((String) tablaUsuarios.getValueAt(fila, 0));
    }//GEN-LAST:event_tablaUsuariosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonModificar;
    private javax.swing.JTextField cajaUCodigo;
    private javax.swing.JTextField cajaUContrasenia;
    private javax.swing.JTextField cajaUNombre;
    private javax.swing.JComboBox<String> comboUTipo;
    private javax.swing.JPanel panelEditarUsuario;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JLabel textoDescripcion;
    private javax.swing.JLabel textoID;
    private javax.swing.JLabel textoUCodigo;
    private javax.swing.JLabel textoUContrasenia;
    private javax.swing.JLabel textoUNombre;
    private javax.swing.JLabel textoUTipo;
    private javax.swing.JLabel texto_Id;
    // End of variables declaration//GEN-END:variables
}

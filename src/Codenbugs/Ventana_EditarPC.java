package Codenbugs;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Ventana_EditarPC extends javax.swing.JInternalFrame {

    Ventana_Admin vadmin;
    boolean seleccionado;
    int tip;
    
    public Ventana_EditarPC(Ventana_Admin vadmin) {
        initComponents();
        this.vadmin=vadmin;
    }

    public void enviar_Modificacion(){
                int id;
        String Nombre;
        int capacidad;
        int tipo = 0;
        double tarifa;
        int operadorAsi;
        if(!cajaNombre.getText().equals("") && !cajaCapacidad.getText().equals("") && !cajaTrifa.getText().equals("") && !cajauserasig.getText().equals("")){
            id=tip;
            Nombre=cajaNombre.getText();
            capacidad = Integer.parseInt(cajaCapacidad.getText());
            tarifa= Double.parseDouble(cajaTrifa.getText());
            operadorAsi= Integer.parseInt(cajauserasig.getText());
            //comboUTipo.getSelectedItem().toString()
                    try {
                        vadmin.marco.vLogin.conect.editarPC(id, Nombre, tarifa, capacidad, operadorAsi);
                    } catch (SQLException ex) {
                        Logger.getLogger(Ventana_EditarPC.class.getName()).log(Level.SEVERE, null, ex);
                    }
            tablaPc();
            limpiar();
            seleccionado=false;
            vadmin.tablaPC();
            JOptionPane.showMessageDialog(null, "Se actualizo el Punto de Control");
        }else{
            
            JOptionPane.showMessageDialog(null, "No se encuentran todos los campos llenos");
        }
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
        cajaNombre.setText("");
        cajaCapacidad.setText("");
        cajaTrifa.setText("");
        cajauserasig.setText("");
        texto_Id.setText("");
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollTabla = new javax.swing.JScrollPane();
        tablaPC = new javax.swing.JTable();
        panelNuevoUsuario = new javax.swing.JPanel();
        textoNombre = new javax.swing.JLabel();
        textoTipo = new javax.swing.JLabel();
        textoTarifa = new javax.swing.JLabel();
        textoUsuerAsign = new javax.swing.JLabel();
        cajaNombre = new javax.swing.JTextField();
        cajaTrifa = new javax.swing.JTextField();
        cajauserasig = new javax.swing.JTextField();
        labelDescripcion = new javax.swing.JLabel();
        cajaCapacidad = new javax.swing.JTextField();
        textoID = new javax.swing.JLabel();
        texto_Id = new javax.swing.JLabel();
        botonModificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaPC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPC.setToolTipText("");
        tablaPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPCMouseClicked(evt);
            }
        });
        scrollTabla.setViewportView(tablaPC);

        getContentPane().add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 910, 340));

        textoNombre.setText("Nombre:");

        textoTipo.setText("Capacidad de Paquetes:");

        textoTarifa.setText("Tarifa:");

        textoUsuerAsign.setText("Operador Asignado");

        labelDescripcion.setText("Todos los campos son Obligatorio");

        textoID.setText("ID Usuario");

        botonModificar.setText("Modificar");
        botonModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonModificarMouseClicked(evt);
            }
        });

        jLabel1.setText("Haz Clik en la el registro que quieres modificar");

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
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cajaNombre)
                    .addComponent(cajaTrifa, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(cajauserasig)
                    .addComponent(cajaCapacidad)
                    .addGroup(panelNuevoUsuarioLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(labelDescripcion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevoUsuarioLayout.createSequentialGroup()
                        .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(texto_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNuevoUsuarioLayout.createSequentialGroup()
                        .addComponent(botonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115))))
            .addGroup(panelNuevoUsuarioLayout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelNuevoUsuarioLayout.setVerticalGroup(
            panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNuevoUsuarioLayout.createSequentialGroup()
                        .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(cajaCapacidad)))
                    .addGroup(panelNuevoUsuarioLayout.createSequentialGroup()
                        .addComponent(textoID)
                        .addGap(6, 6, 6)
                        .addComponent(texto_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoTarifa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cajaTrifa, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoUsuerAsign, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelNuevoUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cajauserasig, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonModificar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelDescripcion)
                .addGap(18, 18, 18)
                .addComponent(jLabel1))
        );

        getContentPane().add(panelNuevoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 910, 210));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPCMouseClicked
        seleccionado = true;
        int fila=tablaPC.getSelectedRow();

        cajaNombre.setText( tablaPC.getValueAt(fila, 1).toString());
        cajaTrifa.setText( tablaPC.getValueAt(fila, 2).toString());
        cajauserasig.setText(tablaPC.getValueAt(fila, 4).toString());
        cajaCapacidad.setText(tablaPC.getValueAt(fila, 3).toString());
        texto_Id.setText(tablaPC.getValueAt(fila, 0).toString());
        tip=  Integer.parseInt( tablaPC.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_tablaPCMouseClicked

    private void botonModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonModificarMouseClicked

        if(seleccionado){
            enviar_Modificacion();
        }

    }//GEN-LAST:event_botonModificarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonModificar;
    private javax.swing.JTextField cajaCapacidad;
    private javax.swing.JTextField cajaNombre;
    private javax.swing.JTextField cajaTrifa;
    private javax.swing.JTextField cajauserasig;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JPanel panelNuevoUsuario;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tablaPC;
    private javax.swing.JLabel textoID;
    private javax.swing.JLabel textoNombre;
    private javax.swing.JLabel textoTarifa;
    private javax.swing.JLabel textoTipo;
    private javax.swing.JLabel textoUsuerAsign;
    private javax.swing.JLabel texto_Id;
    // End of variables declaration//GEN-END:variables
}

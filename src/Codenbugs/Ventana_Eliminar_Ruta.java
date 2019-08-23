package Codenbugs;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Ventana_Eliminar_Ruta extends javax.swing.JInternalFrame {

    Ventana_Admin vadmin;
    int ids;
    boolean seleccionado;
    
    public Ventana_Eliminar_Ruta(Ventana_Admin vadmin) {
        initComponents();
        this.vadmin=vadmin;
    }

    public void enviarRuta(int ids){
        vadmin.marco.vLogin.conect.rutaC.eliminarRuta(ids);
        seleccionado=false;
        limpiar();
        tablaRuta();
        vadmin.tablaRuta();
    }
    
    private void limpiar(){
        textoDestino.setText("");
        textoId.setText("");
        textoInicio.setText("");  
    }
    public void tablaRuta(){
        DefaultTableModel modelo1 = new DefaultTableModel();
        modelo1.addColumn("ID");
        modelo1.addColumn("Inicio");
        modelo1.addColumn("Destino");
        modelo1.addColumn("Cuota");
        tablaRutas.setModel(modelo1);
        String datos[]= new String[4];
        try {
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery(vadmin.rutaS);
            while (vadmin.marco.vLogin.conect.res.next()) {                
                //System.out.println(marco.vLogin.conect.res.getString(1));
                datos[0]=vadmin.marco.vLogin.conect.res.getString(1);
                datos[1]=vadmin.marco.vLogin.conect.res.getString(2);
                datos[2]=vadmin.marco.vLogin.conect.res.getString(3);
                datos[3]=vadmin.marco.vLogin.conect.res.getString(5);
                modelo1.addRow(datos);      
            }  
        } catch (Exception e) {    
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEliminaruta = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRutas = new javax.swing.JTable();
        botonEliminar = new javax.swing.JButton();
        labelId = new javax.swing.JLabel();
        textoId = new javax.swing.JLabel();
        labelDestino = new javax.swing.JLabel();
        labelInicio = new javax.swing.JLabel();
        textoDestino = new javax.swing.JLabel();
        textoInicio = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        botonEliminar.setText("Eliminar");
        botonEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEliminarMouseClicked(evt);
            }
        });

        labelId.setText("ID");

        labelDestino.setText("Destino");

        labelInicio.setText("Inicio");

        javax.swing.GroupLayout panelEliminarutaLayout = new javax.swing.GroupLayout(panelEliminaruta);
        panelEliminaruta.setLayout(panelEliminarutaLayout);
        panelEliminarutaLayout.setHorizontalGroup(
            panelEliminarutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarutaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelEliminarutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEliminarutaLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(labelId))
                    .addGroup(panelEliminarutaLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(botonEliminar))
                    .addGroup(panelEliminarutaLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(panelEliminarutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelEliminarutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textoDestino, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textoId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelDestino, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(labelInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        panelEliminarutaLayout.setVerticalGroup(
            panelEliminarutaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarutaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelEliminarutaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelDestino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(labelInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(botonEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panelEliminaruta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaRutasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRutasMouseClicked
        int fila=tablaRutas.getSelectedRow();
        ids=  Integer.parseInt((String) tablaRutas.getValueAt(fila, 0));
        textoId.setText(tablaRutas.getValueAt(fila, 0).toString());
        textoDestino.setText(tablaRutas.getValueAt(fila, 1).toString());
        textoInicio.setText(tablaRutas.getValueAt(fila,2).toString());
        seleccionado=true;   
    }//GEN-LAST:event_tablaRutasMouseClicked

    private void botonEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarMouseClicked
        if(seleccionado){
            enviarRuta(ids);
            JOptionPane.showMessageDialog(null, "Se elimino correctamente la Ruta");
        }
    }//GEN-LAST:event_botonEliminarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDestino;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelInicio;
    private javax.swing.JPanel panelEliminaruta;
    private javax.swing.JTable tablaRutas;
    private javax.swing.JLabel textoDestino;
    private javax.swing.JLabel textoId;
    private javax.swing.JLabel textoInicio;
    // End of variables declaration//GEN-END:variables
}

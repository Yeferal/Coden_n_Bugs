package Codenbugs;

import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ventana_Recepcionista extends javax.swing.JInternalFrame {

    Marco marco;
    String clientes = "SELECT * FROM cliente;";
    int nitActual;
    int idActualClinete;
    boolean clienteExiste;
    Ventana_Consulta vConsulta = new Ventana_Consulta(this);
    Ventana_Registro_Paquetes vNuevoPaquete = new Ventana_Registro_Paquetes(this);
    Factura html;
    double total;
    DefaultTableModel modelo1 = new DefaultTableModel();
    
    public Ventana_Recepcionista(Marco marco) {
        initComponents();
        this.marco=marco;
        
    }
    /**agrega el modelo y los registros de la tabla de paquetes arrivdos**/
    private void actualizarArribados(){
        DefaultTableModel modelo1 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }  
            };
        
            modelo1.addColumn("ID");
            modelo1.addColumn("Peso");
            modelo1.addColumn("Destino");
            modelo1.addColumn("Hora Llegada");
            modelo1.addColumn("NIT");
        
            tablaArrivados.setModel(modelo1);
            String datos[]= new String[5];
            
        try {
            marco.vLogin.conect.stmt = marco.vLogin.conect.conexion.createStatement();
            marco.vLogin.conect.res = marco.vLogin.conect.stmt.executeQuery("SELECT * FROM paquete WHERE entrega=1;");
            while(marco.vLogin.conect.res.next()){
                datos[0] = marco.vLogin.conect.res.getString(1);
                datos[1] = marco.vLogin.conect.res.getString(2);
                datos[2] = marco.vLogin.conect.res.getString(9);
                datos[3] = marco.vLogin.conect.res.getString(7);
                datos[4] = marco.vLogin.conect.res.getString(3);
                modelo1.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRecepcionista = new javax.swing.JPanel();
        subRecepcionista = new javax.swing.JTabbedPane();
        panelConsulta = new javax.swing.JPanel();
        labelNitR = new javax.swing.JLabel();
        labelIdPa = new javax.swing.JLabel();
        cajaNitRe = new javax.swing.JTextField();
        cajaIDP = new javax.swing.JTextField();
        botonConsulataBuscar = new javax.swing.JButton();
        botonIngreso = new javax.swing.JButton();
        panelArrivos = new javax.swing.JPanel();
        scrollArrivados = new javax.swing.JScrollPane();
        tablaArrivados = new javax.swing.JTable();
        botonEntregas = new javax.swing.JButton();
        botonActualiz = new javax.swing.JButton();
        labelIdeAri = new javax.swing.JLabel();
        labelnitArri = new javax.swing.JLabel();
        txtIDPa = new javax.swing.JLabel();
        txtNit = new javax.swing.JLabel();

        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelNitR.setText("NIT Remintente:");

        labelIdPa.setText("ID Paquete: ");

        cajaNitRe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cajaNitReKeyTyped(evt);
            }
        });

        botonConsulataBuscar.setText("Buscar");
        botonConsulataBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonConsulataBuscarMouseClicked(evt);
            }
        });

        botonIngreso.setText("Nuevo Ingreso De Paquetes");
        botonIngreso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonIngresoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelConsultaLayout = new javax.swing.GroupLayout(panelConsulta);
        panelConsulta.setLayout(panelConsultaLayout);
        panelConsultaLayout.setHorizontalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelNitR)
                    .addComponent(labelIdPa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cajaNitRe, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(cajaIDP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(botonConsulataBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(343, 343, 343))
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addGap(384, 384, 384)
                .addComponent(botonIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelConsultaLayout.setVerticalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelConsultaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonConsulataBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelConsultaLayout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNitR)
                            .addComponent(cajaNitRe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelIdPa)
                            .addComponent(cajaIDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 403, Short.MAX_VALUE)
                .addComponent(botonIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        subRecepcionista.addTab("Consultas", panelConsulta);

        tablaArrivados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaArrivados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaArrivadosMouseClicked(evt);
            }
        });
        scrollArrivados.setViewportView(tablaArrivados);

        botonEntregas.setText("Entregar");
        botonEntregas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEntregasMouseClicked(evt);
            }
        });

        botonActualiz.setText("Actualizar");
        botonActualiz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonActualizMouseClicked(evt);
            }
        });

        labelIdeAri.setText("ID Paquete:");

        labelnitArri.setText("NIT:");

        javax.swing.GroupLayout panelArrivosLayout = new javax.swing.GroupLayout(panelArrivos);
        panelArrivos.setLayout(panelArrivosLayout);
        panelArrivosLayout.setHorizontalGroup(
            panelArrivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArrivosLayout.createSequentialGroup()
                .addComponent(scrollArrivados, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelArrivosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonActualiz)
                .addGap(23, 23, 23))
            .addGroup(panelArrivosLayout.createSequentialGroup()
                .addGroup(panelArrivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelArrivosLayout.createSequentialGroup()
                        .addGap(461, 461, 461)
                        .addComponent(botonEntregas))
                    .addGroup(panelArrivosLayout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(labelIdeAri)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDPa, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(labelnitArri)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelArrivosLayout.setVerticalGroup(
            panelArrivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArrivosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollArrivados, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(panelArrivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIdeAri)
                    .addComponent(labelnitArri)
                    .addComponent(txtIDPa, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(botonEntregas)
                .addGap(52, 52, 52)
                .addComponent(botonActualiz)
                .addGap(19, 19, 19))
        );

        subRecepcionista.addTab("Paquetes Arrivados", panelArrivos);

        javax.swing.GroupLayout panelRecepcionistaLayout = new javax.swing.GroupLayout(panelRecepcionista);
        panelRecepcionista.setLayout(panelRecepcionistaLayout);
        panelRecepcionistaLayout.setHorizontalGroup(
            panelRecepcionistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subRecepcionista)
        );
        panelRecepcionistaLayout.setVerticalGroup(
            panelRecepcionistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecepcionistaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subRecepcionista))
        );

        getContentPane().add(panelRecepcionista, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActualizMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActualizMouseClicked
        actualizarArribados();
    }//GEN-LAST:event_botonActualizMouseClicked

    private void tablaArrivadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaArrivadosMouseClicked
        int fila=tablaArrivados.getSelectedRow();
        
        txtIDPa.setText(tablaArrivados.getValueAt(fila, 0).toString());
        txtNit.setText(tablaArrivados.getValueAt(fila, 4).toString());
        
        
    }//GEN-LAST:event_tablaArrivadosMouseClicked

    private void botonEntregasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEntregasMouseClicked
        if(!txtIDPa.getText().equals("")){
            marco.vLogin.conect.arrivadosC.setEntregasFinal(txtIDPa.getText());
            marco.vLogin.conect.arrivadosC.entregarPaque(txtNit.getText());
            marco.vLogin.conect.registroC.eliminarPaquete(txtIDPa.getText());
        }
        txtIDPa.setText("");
        txtNit.setText("");
        actualizarArribados();
    }//GEN-LAST:event_botonEntregasMouseClicked

    private void botonConsulataBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonConsulataBuscarMouseClicked
        
        if(!cajaNitRe.getText().equals("")){
            marco.PanelEscritorio.add(vConsulta);
            vConsulta.show();
            vConsulta.llenart(cajaNitRe.getText(), cajaIDP.getText());
        }
        
        
    }//GEN-LAST:event_botonConsulataBuscarMouseClicked

    private void botonIngresoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIngresoMouseClicked
        marco.PanelEscritorio.add(vNuevoPaquete);
        vNuevoPaquete.show();
        
    }//GEN-LAST:event_botonIngresoMouseClicked

    private void cajaNitReKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cajaNitReKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9') evt.consume();
        if (cajaNitRe.getText().length()== 8) {

            evt.consume();
        }
    }//GEN-LAST:event_cajaNitReKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualiz;
    private javax.swing.JButton botonConsulataBuscar;
    private javax.swing.JButton botonEntregas;
    private javax.swing.JButton botonIngreso;
    private javax.swing.JTextField cajaIDP;
    private javax.swing.JTextField cajaNitRe;
    private javax.swing.JLabel labelIdPa;
    private javax.swing.JLabel labelIdeAri;
    private javax.swing.JLabel labelNitR;
    private javax.swing.JLabel labelnitArri;
    private javax.swing.JPanel panelArrivos;
    private javax.swing.JPanel panelConsulta;
    private javax.swing.JPanel panelRecepcionista;
    private javax.swing.JScrollPane scrollArrivados;
    private javax.swing.JTabbedPane subRecepcionista;
    private javax.swing.JTable tablaArrivados;
    private javax.swing.JLabel txtIDPa;
    private javax.swing.JLabel txtNit;
    // End of variables declaration//GEN-END:variables
}

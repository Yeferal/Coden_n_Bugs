
package Codenbugs;

import java.sql.SQLException;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Ventana_RegistroPC extends javax.swing.JInternalFrame {

    private int id;
    private Marco marco;
    private String nombrePunto;
    private int aputador;
    private boolean termina;
    private double tarifa;
    private int ruta;
    String destino;
    /**agrega los datos correspondientes al punto de control
     * taless como la ruta a la que pertenece el operador asigna el apuntador
     * y el destino del paquet y llos agrega a los textos
     * @param marco
     * @param id 
     */
    public Ventana_RegistroPC(Marco marco,int id) {
        initComponents();
        this.id = id;
        this.marco = marco;
        
         try {            
            this.marco.vLogin.conect.stmt = this.marco.vLogin.conect.conexion.createStatement();
            this.marco.vLogin.conect.res = this.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM punto_de_control WHERE id_pc="+this.id+";");
            while(marco.vLogin.conect.res.next()){
            nombrePunto = this.marco.vLogin.conect.res.getString(2);
            ruta = Integer.parseInt(this.marco.vLogin.conect.res.getString(7));
            if(this.marco.vLogin.conect.res.getString(8)==null){
                termina=true;
            }else{
                termina = false;
                aputador = Integer.parseInt(this.marco.vLogin.conect.res.getString(8));
            }
            
            tarifa = Double.parseDouble(this.marco.vLogin.conect.res.getString(4));
            
            }
            
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
         textoNombre.setText(nombrePunto);
         textoId.setText(Integer.toString(this.id));
         actualizarTablaP();
    }
    
    /**envia los datos del paquete para que pueda pasar a l siguietne punto de control**/
    private void registrarPaquete(int idPaque){
        enviarFechas();
        try {
            if(termina){
                marco.vLogin.conect.insercion = marco.vLogin.conect.conexion.prepareStatement("UPDATE paquete SET id_pc="+0+", entrega=1 WHERE id_paquete="+idPaque+";");
                marco.vLogin.conect.insercion.executeUpdate();
                marco.vLogin.conect.arrivadosC.setIngreso(Integer.toString(idPaque), Integer.toString(ruta));
                marco.vLogin.conect.arrivadosC.setEntreegarPaquete(Integer.toString(ruta));
                marco.vLogin.conect.arrivadosC.entregarPaqueteNuevo(Integer.toString(idPaque), Integer.toString(ruta),destino);
            }else{
               marco.vLogin.conect.insercion = marco.vLogin.conect.conexion.prepareStatement("UPDATE paquete SET id_pc="+aputador+" WHERE id_paquete="+idPaque);
               marco.vLogin.conect.insercion.executeUpdate(); 
            }
            actualizarTablaP();
            limpiar();
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        
    }
    
    /**actualiza la tabla y agrega el modelo corresponditne**/
    public void actualizarTablaP(){
        try {
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
            tablaPaquetes.setModel(modelo1);
            String datos[]= new String[4];
            marco.vLogin.conect.stmt = marco.vLogin.conect.conexion.createStatement();
            marco.vLogin.conect.res = marco.vLogin.conect.stmt.executeQuery("SELECT * FROM paquete WHERE id_pc="+id+";");
            while(marco.vLogin.conect.res.next()){
                datos[0] = marco.vLogin.conect.res.getString(1);
                datos[1] = marco.vLogin.conect.res.getString(2);
                datos[2] = marco.vLogin.conect.res.getString(9);
                datos[3] = marco.vLogin.conect.res.getString(7);
                modelo1.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }      
    }
    /**agrega la fecha al texto y actualiza la misma**/
    private void fecharTxt(){
        String fechaActual = null;
        try {
            fechaActual = marco.vLogin.conect.registroC.obtenertime();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        System.out.println("Date: "+fechaActual);
        cajaAnio.setText(fechaActual.substring(0, 4));
        cajaMes.setText(fechaActual.substring(5, 7));
        cajaDia.setText(fechaActual.substring(8, 10));
        cajaHora.setText(fechaActual.substring(11, 13));
        labelMinutos.setText(fechaActual.substring(14, 19));
    
    }
    /**envia las fecha para se actualizada**/
    public void enviarFechas(){
        if(!cajaAnio.getText().equals("") && !cajaMes.getText().equals("") && !cajaDia.getText().equals("") && !cajaHora.getText().equals("") ){
            String fechaNueva = cajaAnio.getText()+"-"+cajaMes.getText()+"-"+cajaDia.getText()+" "+cajaHora.getText()+":"+labelMinutos.getText();
            System.out.println("Fechas: "+fechaNueva);
            marco.vLogin.conect.pcConeC.setCostos(txtHora.getText(), Integer.toString(ruta), fechaNueva, Integer.toString(id), txtId.getText());
        }
    }
    /**limpia los tetos**/
    private void limpiar(){
        cajaAnio.setText("");
        cajaMes.setText("");
        cajaDia.setText("");
        cajaHora.setText("");
        txtId.setText("");
        txtHora.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRegitro = new javax.swing.JPanel();
        scrollTabla = new javax.swing.JScrollPane();
        tablaPaquetes = new javax.swing.JTable();
        botonRegisrar = new javax.swing.JButton();
        labelIdpc = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        textoNombre = new javax.swing.JLabel();
        textoId = new javax.swing.JLabel();
        botonActualziar = new javax.swing.JButton();
        titulopAQUETE = new javax.swing.JLabel();
        labeId = new javax.swing.JLabel();
        labelHora = new javax.swing.JLabel();
        labeldia = new javax.swing.JLabel();
        labelMes = new javax.swing.JLabel();
        labelAnio = new javax.swing.JLabel();
        laberDatHora = new javax.swing.JLabel();
        labelMinuto = new javax.swing.JLabel();
        cajaDia = new javax.swing.JTextField();
        cajaMes = new javax.swing.JTextField();
        cajaAnio = new javax.swing.JTextField();
        cajaHora = new javax.swing.JTextField();
        labelMinutos = new javax.swing.JLabel();
        linea = new javax.swing.JLabel();
        linea2 = new javax.swing.JLabel();
        puntos = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        txtHora = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaPaquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPaquetes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPaquetesMouseClicked(evt);
            }
        });
        scrollTabla.setViewportView(tablaPaquetes);

        botonRegisrar.setText("Registrado");
        botonRegisrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonRegisrarMouseClicked(evt);
            }
        });

        labelIdpc.setText("ID PC:");

        labelNombre.setText("Nombre PC:");

        botonActualziar.setText("Actualizar");
        botonActualziar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonActualziarMouseClicked(evt);
            }
        });

        titulopAQUETE.setText("Paquete Seleccionado");

        labeId.setText("ID:");

        labelHora.setText("Hora Llegada");

        labeldia.setText("Dia");

        labelMes.setText("Mes");

        labelAnio.setText("Año");

        laberDatHora.setText("Hora");

        labelMinuto.setText("Minuto");

        linea.setText("-");

        linea2.setText("-");

        puntos.setText(":");

        javax.swing.GroupLayout panelRegitroLayout = new javax.swing.GroupLayout(panelRegitro);
        panelRegitro.setLayout(panelRegitroLayout);
        panelRegitroLayout.setHorizontalGroup(
            panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegitroLayout.createSequentialGroup()
                .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegitroLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNombre)
                            .addComponent(labelAnio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelRegitroLayout.createSequentialGroup()
                                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelIdpc)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textoId, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(74, 74, 74))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegitroLayout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelRegitroLayout.createSequentialGroup()
                                            .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(labelMes)
                                                .addComponent(titulopAQUETE))
                                            .addGap(47, 47, 47))
                                        .addGroup(panelRegitroLayout.createSequentialGroup()
                                            .addComponent(labeId)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(labeldia)
                                                .addComponent(labelHora))
                                            .addGap(21, 21, 21)))
                                    .addComponent(txtHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap()))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegitroLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRegitroLayout.createSequentialGroup()
                                        .addComponent(botonRegisrar)
                                        .addGap(183, 183, 183))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegitroLayout.createSequentialGroup()
                                        .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelMinuto))
                                        .addGap(145, 145, 145))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegitroLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(cajaAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linea, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cajaMes, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(linea2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cajaDia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(laberDatHora)
                            .addGroup(panelRegitroLayout.createSequentialGroup()
                                .addComponent(cajaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(puntos, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(182, 182, 182))))
            .addGroup(panelRegitroLayout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(botonActualziar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRegitroLayout.setVerticalGroup(
            panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegitroLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelIdpc, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(labelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(78, 78, 78)
                .addComponent(titulopAQUETE)
                .addGap(56, 56, 56)
                .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labeId)
                    .addComponent(labelHora)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMes)
                    .addComponent(labelAnio)
                    .addComponent(laberDatHora)
                    .addComponent(labelMinuto)
                    .addComponent(labeldia))
                .addGap(18, 18, 18)
                .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRegitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cajaDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cajaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cajaHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(linea)
                        .addComponent(linea2)
                        .addComponent(puntos)
                        .addComponent(cajaAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(128, 128, 128)
                .addComponent(botonRegisrar)
                .addGap(51, 51, 51))
            .addGroup(panelRegitroLayout.createSequentialGroup()
                .addComponent(scrollTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonActualziar)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        getContentPane().add(panelRegitro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActualziarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonActualziarMouseClicked
        actualizarTablaP();
    }//GEN-LAST:event_botonActualziarMouseClicked

    private void tablaPaquetesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPaquetesMouseClicked
        int fila=tablaPaquetes.getSelectedRow();
        txtId.setText(tablaPaquetes.getValueAt(fila, 0).toString()); 
        txtHora.setText(marco.vLogin.conect.pcConeC.fechar(tablaPaquetes.getValueAt(fila, 0).toString()));
        destino = tablaPaquetes.getValueAt(fila, 2).toString();
        fecharTxt();
    }//GEN-LAST:event_tablaPaquetesMouseClicked

    private void botonRegisrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegisrarMouseClicked
        if(!txtId.getText().equals("")){
            registrarPaquete(Integer.parseInt(txtId.getText()));
        }
    }//GEN-LAST:event_botonRegisrarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualziar;
    private javax.swing.JButton botonRegisrar;
    private javax.swing.JTextField cajaAnio;
    private javax.swing.JTextField cajaDia;
    private javax.swing.JTextField cajaHora;
    private javax.swing.JTextField cajaMes;
    private javax.swing.JLabel labeId;
    private javax.swing.JLabel labelAnio;
    private javax.swing.JLabel labelHora;
    private javax.swing.JLabel labelIdpc;
    private javax.swing.JLabel labelMes;
    private javax.swing.JLabel labelMinuto;
    private javax.swing.JLabel labelMinutos;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labeldia;
    private javax.swing.JLabel laberDatHora;
    private javax.swing.JLabel linea;
    private javax.swing.JLabel linea2;
    private javax.swing.JPanel panelRegitro;
    private javax.swing.JLabel puntos;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tablaPaquetes;
    private javax.swing.JLabel textoId;
    private javax.swing.JLabel textoNombre;
    private javax.swing.JLabel titulopAQUETE;
    private javax.swing.JLabel txtHora;
    private javax.swing.JLabel txtId;
    // End of variables declaration//GEN-END:variables
}

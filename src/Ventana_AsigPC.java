
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class Ventana_AsigPC extends javax.swing.JInternalFrame {

     Ventana_Admin vadmin;
     boolean agregar, eliminar, mover;
     int idSeleccionado;
     int idRuta;
     int idSelEl, idApuntador, idSelEl2;
     String idcambio1, idcambio2;
     
    public Ventana_AsigPC( Ventana_Admin vadmin) {
        initComponents();
        this.vadmin=vadmin;
    }
    
    public void tablaRuta(){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("ID");
        modelo1.addColumn("Inicio");
        modelo1.addColumn("Destino");
        modelo1.addColumn("Cuota");


        tablaRutasDis.setModel(modelo1);
        String datos[]= new String[4];
        try {
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery(vadmin.rutaS);
            
            while (vadmin.marco.vLogin.conect.res.next()) {                
                
                datos[0]=vadmin.marco.vLogin.conect.res.getString(1);
                datos[1]=vadmin.marco.vLogin.conect.res.getString(2);
                datos[2]=vadmin.marco.vLogin.conect.res.getString(3);
                datos[3]=vadmin.marco.vLogin.conect.res.getString(5);

                modelo1.addRow(datos);      
            }  
        } catch (Exception e) {    
        }
    }
    
    private void tablaPCAsignados(int id){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("ID");
        modelo1.addColumn("Nombre");
        modelo1.addColumn("Capacidad");
        modelo1.addColumn("id siguiente");
        
        tablaPCAsign.setModel(modelo1);
        String datos[]= new String[4];
        try {
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery(vadmin.punto);
            System.out.println("id:"+id);
            while (vadmin.marco.vLogin.conect.res.next()) { 
                int idPP;
                if(vadmin.marco.vLogin.conect.res.getString(7)==null){
                    idPP = 0;
                }else{
                    idPP = Integer.parseInt(vadmin.marco.vLogin.conect.res.getString(7));
                }
                
                System.out.println(vadmin.marco.vLogin.conect.res.getString(7));
                if(idPP==id){
                    datos[0]=vadmin.marco.vLogin.conect.res.getString(1);
                    datos[1]=vadmin.marco.vLogin.conect.res.getString(2);
                    datos[2]=vadmin.marco.vLogin.conect.res.getString(5);
                    datos[3]=vadmin.marco.vLogin.conect.res.getString(8);
                    modelo1.addRow(datos);
                }                     
            }  
        } catch (Exception e) {    
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void tablaPCDisponible(){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("ID");
        modelo1.addColumn("Nombre");
        modelo1.addColumn("Capacidad");
        
        
        tablaPCDispo.setModel(modelo1);
        String datos[]= new String[3];
        try {
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery(vadmin.punto);
            
            while (vadmin.marco.vLogin.conect.res.next()) {                
                    String nu=vadmin.marco.vLogin.conect.res.getString(7);
                if(nu==null){
                    datos[0]=vadmin.marco.vLogin.conect.res.getString(1);
                    datos[1]=vadmin.marco.vLogin.conect.res.getString(2);
                    datos[2]=vadmin.marco.vLogin.conect.res.getString(5);

                    modelo1.addRow(datos);
                }                     
            }  
        } catch (Exception e) {    
        }
    }
    
    public void enviarPcAgregar(int idR, int idPc){
        System.out.println("id Ruta: "+idR);
        System.out.println("id Punto: "+idPc);
        vadmin.marco.vLogin.conect.agregarPC(idR, idPc);
        actualizarTabla();
    }
    public void enviarEliminar(int idPCdelet, int idsig){
        vadmin.marco.vLogin.conect.eliminarPCdeRuta(idPCdelet, idsig);
        actualizarTabla();
    }
    
    public void enviarCambiar(int idsele1, int idsele2, String sig1 , String sig2) throws SQLException{
        
        vadmin.marco.vLogin.conect.cambiarPCs(idsele1, idsele2, sig1, sig2);
        actualizarTabla();
    }
    
    public void actualizarTabla(){
        tablaPCAsignados(idRuta);
        tablaPCDisponible();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRutasDis = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPCDispo = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPCAsign = new javax.swing.JTable();
        labelrutas = new javax.swing.JLabel();
        labelPCAsig = new javax.swing.JLabel();
        laabelPCdispo = new javax.swing.JLabel();
        botonAsiganar = new javax.swing.JButton();
        botonCambitar = new javax.swing.JButton();
        botonMover = new javax.swing.JButton();
        laberSelecion = new javax.swing.JLabel();
        textoRuta = new javax.swing.JLabel();
        botonEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        l2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cajaNE = new javax.swing.JLabel();
        labeln2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelCambiar = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        cajaNM = new javax.swing.JLabel();
        botonListo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaRutasDis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaRutasDis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRutasDisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaRutasDis);

        tablaPCDispo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPCDispo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPCDispoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPCDispo);

        tablaPCAsign.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaPCAsign.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPCAsignMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaPCAsign);

        labelrutas.setText("Selecciona la Ruta que deseas Asignarle los Puntos de control");

        labelPCAsig.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelPCAsig.setText("Puntos de control que esta Ruta tiene Asignados");

        laabelPCdispo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        laabelPCdispo.setText("Puntos de control Disponibles sin asignar");

        botonAsiganar.setText("Asignar");
        botonAsiganar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAsiganarMouseClicked(evt);
            }
        });

        botonCambitar.setText("Cambiar");
        botonCambitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonCambitarMouseClicked(evt);
            }
        });

        botonMover.setText("Mover");

        laberSelecion.setText("Ruta:");

        botonEliminar.setText("Eliminar");
        botonEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEliminarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(69, 69, 69)
                        .addComponent(botonAsiganar)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botonCambitar)
                                .addGap(51, 51, 51)
                                .addComponent(botonMover))
                            .addComponent(botonEliminar))
                        .addGap(102, 102, 102))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(labelrutas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(labelPCAsig)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(laabelPCdispo)
                .addGap(54, 54, 54))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(laberSelecion)
                .addGap(18, 18, 18)
                .addComponent(textoRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelrutas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonEliminar)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonAsiganar)
                            .addComponent(botonCambitar)
                            .addComponent(botonMover))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(laberSelecion)
                    .addComponent(textoRuta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPCAsig)
                    .addComponent(laabelPCdispo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 950, 700));

        jLabel1.setText("PUNTO DE CONTROL SELECCIONADO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 710, -1, -1));

        l2.setText("ID");
        getContentPane().add(l2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 750, -1, -1));

        jLabel3.setText("Nombre:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 750, -1, -1));
        getContentPane().add(cajaNE, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 750, 170, 20));
        getContentPane().add(labeln2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 750, 170, 20));

        jLabel6.setText("PUNTO DE CONTROL SELECCIONADO");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 710, -1, -1));

        labelCambiar.setText("Cambiar");
        getContentPane().add(labelCambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 780, -1, -1));

        l3.setText("ID");
        getContentPane().add(l3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 810, -1, -1));
        getContentPane().add(cajaNM, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 810, 150, 20));

        botonListo.setText("Listo");
        getContentPane().add(botonListo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 740, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaRutasDisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRutasDisMouseClicked
        // TODO add your handling code here:
        int fila=tablaRutasDis.getSelectedRow();
        idRuta=Integer.parseInt((String) tablaRutasDis.getValueAt(fila, 0));
        tablaPCAsignados(Integer.parseInt(tablaRutasDis.getValueAt(fila, 0).toString()));
        textoRuta.setText(tablaRutasDis.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_tablaRutasDisMouseClicked

    private void tablaPCDispoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPCDispoMouseClicked
        // TODO add your handling code here:
        int fila=tablaPCDispo.getSelectedRow();
        idSeleccionado=Integer.parseInt((String) tablaPCDispo.getValueAt(fila, 0));
        labeln2.setText(tablaPCDispo.getValueAt(fila, 1).toString());
        agregar=true;
    }//GEN-LAST:event_tablaPCDispoMouseClicked

    private void botonAsiganarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAsiganarMouseClicked
        
        if(agregar){
            enviarPcAgregar(idRuta, idSeleccionado);
            agregar= false;
        }
        
        
    }//GEN-LAST:event_botonAsiganarMouseClicked

    private void tablaPCAsignMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPCAsignMouseClicked
        int fila=tablaPCAsign.getSelectedRow();
        System.out.println("fila: "+fila);
        if(eliminar==false){
            
            eliminar=true;
            mover=false;
            cajaNE.setVisible(true);
            cajaNM.setVisible(false);
            l3.setVisible(false);
            labelCambiar.setText("Eliminar");
            cajaNE.setText(tablaPCAsign.getValueAt(fila, 0).toString());
            idSelEl = Integer.parseInt(tablaPCAsign.getValueAt(fila, 0).toString());
            idApuntador = Integer.parseInt(tablaPCAsign.getValueAt(fila, 3).toString());
            
            idcambio1 = tablaPCAsign.getValueAt(fila, 3).toString();
        }else{
            eliminar=false;
            mover=true;
            cajaNE.setVisible(true);
            cajaNM.setVisible(true);
            l3.setVisible(true);
            
            idcambio2 = tablaPCAsign.getValueAt(fila, 3).toString();
            labelCambiar.setText("Cambiar");
            idSelEl2 = Integer.parseInt(tablaPCAsign.getValueAt(fila, 0).toString());
            cajaNM.setText(tablaPCAsign.getValueAt(fila, 2).toString());
        }  
    }//GEN-LAST:event_tablaPCAsignMouseClicked

    private void botonEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarMouseClicked
        // TODO add your handling code here:
        if(eliminar){
            enviarEliminar(idSelEl, idApuntador);
            eliminar = false;
        }
        
        
    }//GEN-LAST:event_botonEliminarMouseClicked

    private void botonCambitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCambitarMouseClicked
        // TODO add your handling code here:
        if(mover){
            try {
                enviarCambiar(idSelEl, idSelEl2, idcambio1, idcambio2);
            } catch (SQLException ex) {
                System.out.println("Falllllllllllllllllllo");
            }
        }
    }//GEN-LAST:event_botonCambitarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAsiganar;
    private javax.swing.JButton botonCambitar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonListo;
    private javax.swing.JButton botonMover;
    private javax.swing.JLabel cajaNE;
    private javax.swing.JLabel cajaNM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel laabelPCdispo;
    private javax.swing.JLabel labelCambiar;
    private javax.swing.JLabel labelPCAsig;
    private javax.swing.JLabel labeln2;
    private javax.swing.JLabel labelrutas;
    private javax.swing.JLabel laberSelecion;
    private javax.swing.JTable tablaPCAsign;
    private javax.swing.JTable tablaPCDispo;
    private javax.swing.JTable tablaRutasDis;
    private javax.swing.JLabel textoRuta;
    // End of variables declaration//GEN-END:variables
}

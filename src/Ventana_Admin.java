
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

public class Ventana_Admin extends javax.swing.JInternalFrame {
    
    String usuarios="SELECT * FROM usuario";
    String punto = "SELECT * FROM punto_de_control";
    String rutaS = "SELECT * FROM ruta";
    Marco marco;
    Ventana_Nuevo_Usuario vnuevoUsuario = new Ventana_Nuevo_Usuario(this);
    Editar_Usuario editarUsuario = new Editar_Usuario(this);
    Ventana_Eliminar_Usuario vEliminar = new Ventana_Eliminar_Usuario(this);
    Ventana_NuevoPC vNuevoPC = new Ventana_NuevoPC(this);
    Ventana_EditarPC vEditarPC =  new Ventana_EditarPC(this);
    Ventana_EliminarPC vEliminarPC = new Ventana_EliminarPC(this);
    Ventana_Nueva_Ruta vNuevaRuta = new Ventana_Nueva_Ruta(this);
    Ventana_Eliminar_Ruta vEliminarRuta = new Ventana_Eliminar_Ruta(this);
    Ventana_AsigPC vAsigPc = new Ventana_AsigPC(this);
    
    public Ventana_Admin(Marco marco) {
        initComponents();
        this.marco=marco;
        //tablaUser();   
    }
    
    public void tablaUser(){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("ID");
        modelo1.addColumn("Nombre");
        modelo1.addColumn("Codigo");
        modelo1.addColumn("Tipo");
        modelo1.addColumn("Password");

        tablaUsusarios.setModel(modelo1);
        String datos[]= new String[5];
        try {
            marco.vLogin.conect.stmt = marco.vLogin.conect.conexion.createStatement();
            marco.vLogin.conect.res = marco.vLogin.conect.stmt.executeQuery(usuarios);
            
            while (marco.vLogin.conect.res.next()) {                
                //System.out.println(marco.vLogin.conect.res.getString(1));
                datos[0] = marco.vLogin.conect.res.getString(1);
                datos[1] = marco.vLogin.conect.res.getString(2);
                datos[2] = marco.vLogin.conect.res.getString(3);
                
                //datos[3] = marco.vLogin.conect.res.getString(4);
                switch(marco.vLogin.conect.res.getString(4)){
                    case "1":
                         datos[3] = "Administrador";
                         break;
                    case "2":
                        datos[3] = "Operador";
                        break;
                    case "3":
                        datos[3] = "Recepcionista";
                        break;
                        default:
                            datos[3] = "Desactivado";
                }
                datos[4] = marco.vLogin.conect.res.getString(5);
                modelo1.addRow(datos);      
            }  
        } catch (Exception e) {    
        }
    }
    
    public void tablaPC(){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("ID");
        modelo1.addColumn("Nombre");
        modelo1.addColumn("Estado");
        modelo1.addColumn("Tarifa");
        modelo1.addColumn("Capacidad");
        modelo1.addColumn("Operador Asignado");
        modelo1.addColumn("Ruta");
        modelo1.addColumn("PC Siguiente");

        tablaPC.setModel(modelo1);
        String datos[]= new String[8];
        try {
            marco.vLogin.conect.stmt = marco.vLogin.conect.conexion.createStatement();
            marco.vLogin.conect.res = marco.vLogin.conect.stmt.executeQuery(punto);
            
            while (marco.vLogin.conect.res.next()) {                
                //System.out.println(marco.vLogin.conect.res.getString(1));
                datos[0]=marco.vLogin.conect.res.getString(1);
                datos[1]=marco.vLogin.conect.res.getString(2);
                datos[2]=marco.vLogin.conect.res.getString(3);
                datos[3]=marco.vLogin.conect.res.getString(4);
                datos[4]=marco.vLogin.conect.res.getString(5);
                datos[5]=marco.vLogin.conect.res.getString(6);
                datos[6]=marco.vLogin.conect.res.getString(7);
                datos[7]=marco.vLogin.conect.res.getString(8);
                modelo1.addRow(datos);      
            }  
        } catch (Exception e) {    
        }
    }
    
    public void tablaRuta(){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("ID");
        modelo1.addColumn("Inicio");
        modelo1.addColumn("Destino");
        modelo1.addColumn("Estado");
        modelo1.addColumn("Cuota");
        modelo1.addColumn("Ingresos");
        modelo1.addColumn("Costos");
        modelo1.addColumn("Ganancias");
        modelo1.addColumn("Entregas");
        modelo1.addColumn("En Camino");

        tablaRutas.setModel(modelo1);
        String datos[]= new String[10];
        try {
            marco.vLogin.conect.stmt = marco.vLogin.conect.conexion.createStatement();
            marco.vLogin.conect.res = marco.vLogin.conect.stmt.executeQuery(rutaS);
            
            while (marco.vLogin.conect.res.next()) {                
                //System.out.println(marco.vLogin.conect.res.getString(1));
                datos[0]=marco.vLogin.conect.res.getString(1);
                datos[1]=marco.vLogin.conect.res.getString(2);
                datos[2]=marco.vLogin.conect.res.getString(3);
                
                //datos[3]=marco.vLogin.conect.res.getString(4);
                if(marco.vLogin.conect.res.getString(4).equals("1")){
                    datos[3]="Activo";
                }else{
                    datos[3]="Desactivado";
                }
                
                datos[4]=marco.vLogin.conect.res.getString(5);
                datos[5]=marco.vLogin.conect.res.getString(6);
                datos[6]=marco.vLogin.conect.res.getString(7);
                datos[7]=marco.vLogin.conect.res.getString(8);
                datos[8]=marco.vLogin.conect.res.getString(9);
                datos[9]=marco.vLogin.conect.res.getString(10);
                modelo1.addRow(datos);      
            }  
        } catch (Exception e) {    
        }
    }

    public void tablaPCenRuta(){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("Nombre");
        modelo1.addColumn("Capacidad");
        modelo1.addColumn("PC Siguiente");

        tablaPCRutas.setModel(modelo1);
        String datos[]= new String[8];
        try {
            marco.vLogin.conect.stmt = marco.vLogin.conect.conexion.createStatement();
            marco.vLogin.conect.res = marco.vLogin.conect.stmt.executeQuery(punto);
            
            while (marco.vLogin.conect.res.next()) {                
                //System.out.println(marco.vLogin.conect.res.getString(1));
                datos[0]=marco.vLogin.conect.res.getString(1);
                datos[1]=marco.vLogin.conect.res.getString(2);
                datos[2]=marco.vLogin.conect.res.getString(3);
                datos[3]=marco.vLogin.conect.res.getString(4);
                datos[4]=marco.vLogin.conect.res.getString(5);
                datos[5]=marco.vLogin.conect.res.getString(6);
                datos[6]=marco.vLogin.conect.res.getString(7);
                datos[7]=marco.vLogin.conect.res.getString(8);
                modelo1.addRow(datos);      
            }  
        } catch (Exception e) {    
        }
    }
    private void tablaPCDisponible(){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("ID");
        modelo1.addColumn("Nombre");
        modelo1.addColumn("Capacidad");
        
        tablaPCRutas.setModel(modelo1);
        String datos[]= new String[3];
        try {
            marco.vLogin.conect.stmt = marco.vLogin.conect.conexion.createStatement();
            marco.vLogin.conect.res = marco.vLogin.conect.stmt.executeQuery(punto);
            
            while (marco.vLogin.conect.res.next()) {                
                    String nu=marco.vLogin.conect.res.getString(7);
                if(nu==null){
                    datos[0]=marco.vLogin.conect.res.getString(1);
                    datos[1]=marco.vLogin.conect.res.getString(2);
                    datos[2]=marco.vLogin.conect.res.getString(5);
                    modelo1.addRow(datos);
                }else{
                    System.out.println("No agrego");
                }  
                
            }  
        } catch (Exception e) {  
            e.getMessage();
            e.printStackTrace();
        }
    }
    public void actualizarTablas(){
        tablaPC();
        tablaPCDisponible();
        //tablaPCenRuta();
        tablaRuta();
        tablaUser();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        Panel_Usuarios = new javax.swing.JPanel();
        botonNuevoUsusario = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsusarios = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        Panel_Rutas = new javax.swing.JPanel();
        scrollTRutas = new javax.swing.JScrollPane();
        tablaRutas = new javax.swing.JTable();
        scrollTPc = new javax.swing.JScrollPane();
        tablaPCRutas = new javax.swing.JTable();
        botonNuevaRuta = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonEliminarRuta = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        botonAsignatPC = new javax.swing.JButton();
        botonquitatPC = new javax.swing.JButton();
        Panel_Puntos = new javax.swing.JPanel();
        botonNuevoPC = new javax.swing.JButton();
        botonEditarPC = new javax.swing.JButton();
        botonEliminarPC = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPC = new javax.swing.JTable();

        setIconifiable(true);
        setRequestFocusEnabled(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        botonNuevoUsusario.setText("Nuevo Usuario");
        botonNuevoUsusario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonNuevoUsusarioMouseClicked(evt);
            }
        });

        jButton8.setText("Quitar Usuario");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        tablaUsusarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaUsusarios);

        jButton9.setText("Editar Datos de Usuario");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_UsuariosLayout = new javax.swing.GroupLayout(Panel_Usuarios);
        Panel_Usuarios.setLayout(Panel_UsuariosLayout);
        Panel_UsuariosLayout.setHorizontalGroup(
            Panel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(Panel_UsuariosLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(botonNuevoUsusario, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );
        Panel_UsuariosLayout.setVerticalGroup(
            Panel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_UsuariosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(Panel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNuevoUsusario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Usuarios", Panel_Usuarios);

        tablaRutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollTRutas.setViewportView(tablaRutas);

        tablaPCRutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollTPc.setViewportView(tablaPCRutas);

        botonNuevaRuta.setText("Nueva Ruta");
        botonNuevaRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonNuevaRutaMouseClicked(evt);
            }
        });

        botonEditar.setText("Editar Ruta");

        botonEliminarRuta.setText("Eliminar Ruta");
        botonEliminarRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEliminarRutaMouseClicked(evt);
            }
        });

        jButton4.setText("jButton4");

        botonAsignatPC.setText("Asignar punto de control a Ruta");
        botonAsignatPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAsignatPCMouseClicked(evt);
            }
        });

        botonquitatPC.setText("Quitar punto de control a Ruta");

        javax.swing.GroupLayout Panel_RutasLayout = new javax.swing.GroupLayout(Panel_Rutas);
        Panel_Rutas.setLayout(Panel_RutasLayout);
        Panel_RutasLayout.setHorizontalGroup(
            Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_RutasLayout.createSequentialGroup()
                .addGroup(Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_RutasLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonNuevaRuta)
                            .addComponent(botonEditar))
                        .addGap(149, 149, 149)
                        .addGroup(Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botonAsignatPC)
                            .addComponent(botonquitatPC)))
                    .addGroup(Panel_RutasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollTRutas, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_RutasLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonEliminarRuta)
                            .addComponent(jButton4)))
                    .addGroup(Panel_RutasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollTPc, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel_RutasLayout.setVerticalGroup(
            Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_RutasLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNuevaRuta)
                    .addComponent(botonEliminarRuta)
                    .addComponent(botonAsignatPC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEditar)
                    .addComponent(jButton4)
                    .addComponent(botonquitatPC))
                .addGap(47, 47, 47)
                .addGroup(Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollTRutas, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                    .addComponent(scrollTPc))
                .addGap(34, 34, 34))
        );

        jTabbedPane1.addTab("Rutas", Panel_Rutas);

        botonNuevoPC.setText("Nuevo Punto de Control");
        botonNuevoPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonNuevoPCMouseClicked(evt);
            }
        });

        botonEditarPC.setText("Editar Punto de Control");
        botonEditarPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEditarPCMouseClicked(evt);
            }
        });

        botonEliminarPC.setText("Eliminar Punto de Control");
        botonEliminarPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEliminarPCMouseClicked(evt);
            }
        });

        tablaPC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaPC);

        javax.swing.GroupLayout Panel_PuntosLayout = new javax.swing.GroupLayout(Panel_Puntos);
        Panel_Puntos.setLayout(Panel_PuntosLayout);
        Panel_PuntosLayout.setHorizontalGroup(
            Panel_PuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_PuntosLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(botonNuevoPC, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(botonEditarPC, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(botonEliminarPC, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
            .addGroup(Panel_PuntosLayout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        Panel_PuntosLayout.setVerticalGroup(
            Panel_PuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_PuntosLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(Panel_PuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNuevoPC, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEditarPC, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEliminarPC, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Puntos de Control", Panel_Puntos);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 660));
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevoUsusarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonNuevoUsusarioMouseClicked
        marco.PanelEscritorio.add(vnuevoUsuario);
        vnuevoUsuario.show();    
    }//GEN-LAST:event_botonNuevoUsusarioMouseClicked

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
        // TODO add your handling code here:
        marco.PanelEscritorio.add(vEliminar);
        vEliminar.show();
        vEliminar.tablaUser();
        tablaUser();
    }//GEN-LAST:event_jButton8MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
        marco.PanelEscritorio.add(editarUsuario);
        editarUsuario.show();
        editarUsuario.tablaUser();
    }//GEN-LAST:event_jButton9MouseClicked

    private void botonNuevoPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonNuevoPCMouseClicked
        // TODO add your handling code here:
        marco.PanelEscritorio.add(vNuevoPC);
        vNuevoPC.show();
        
    }//GEN-LAST:event_botonNuevoPCMouseClicked

    private void botonEditarPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEditarPCMouseClicked
        // TODO add your handling code here:
        marco.PanelEscritorio.add(vEditarPC);
        vEditarPC.show();
        vEditarPC.tablaPc();
    }//GEN-LAST:event_botonEditarPCMouseClicked

    private void botonEliminarPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarPCMouseClicked
        // TODO add your handling code here:
        marco.PanelEscritorio.add(vEliminarPC);
        vEliminarPC.show();
        vEliminarPC.tablaPc();
    }//GEN-LAST:event_botonEliminarPCMouseClicked

    private void botonNuevaRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonNuevaRutaMouseClicked
        marco.PanelEscritorio.add(vNuevaRuta);
        vNuevaRuta.show();
        
    }//GEN-LAST:event_botonNuevaRutaMouseClicked

    private void botonEliminarRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarRutaMouseClicked
        
        marco.PanelEscritorio.add(vEliminarRuta);
        vEliminarRuta.show();
        vEliminarRuta.tablaRuta();
    }//GEN-LAST:event_botonEliminarRutaMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        
        actualizarTablas();
        
        
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void botonAsignatPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAsignatPCMouseClicked
        try {
            // TODO add your handling code here:
            marco.vLogin.conect.insercion =  marco.vLogin.conect.conexion.prepareStatement("UPDATE punto_de_control SET ruta=null WHERE id_pc="+6);
            marco.vLogin.conect.insercion.executeUpdate();
            marco.vLogin.conect.insercion =  marco.vLogin.conect.conexion.prepareStatement("UPDATE punto_de_control SET ruta=null WHERE id_pc="+7);
            marco.vLogin.conect.insercion.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Falo");
            ex.getMessage();
                ex.printStackTrace();
        }
         marco.PanelEscritorio.add(vAsigPc);
         vAsigPc.show();
         vAsigPc.tablaRuta();
         vAsigPc.tablaPCDisponible();
    }//GEN-LAST:event_botonAsignatPCMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Puntos;
    private javax.swing.JPanel Panel_Rutas;
    private javax.swing.JPanel Panel_Usuarios;
    private javax.swing.JButton botonAsignatPC;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEditarPC;
    private javax.swing.JButton botonEliminarPC;
    private javax.swing.JButton botonEliminarRuta;
    private javax.swing.JButton botonNuevaRuta;
    private javax.swing.JButton botonNuevoPC;
    private javax.swing.JButton botonNuevoUsusario;
    private javax.swing.JButton botonquitatPC;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane scrollTPc;
    private javax.swing.JScrollPane scrollTRutas;
    private javax.swing.JTable tablaPC;
    private javax.swing.JTable tablaPCRutas;
    private javax.swing.JTable tablaRutas;
    private javax.swing.JTable tablaUsusarios;
    // End of variables declaration//GEN-END:variables
}

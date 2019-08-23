package Codenbugs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

public class Ventana_Admin extends javax.swing.JInternalFrame {
    
    String usuarios="SELECT * FROM usuario;";
    String punto = "SELECT * FROM punto_de_control;";
    String rutaS = "SELECT * FROM ruta;";
    String destinos = "SELECT * FROM destinos;";
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
    Ventana_Destino vDestino = new Ventana_Destino(this);
    Ventana_Nuevo_Destino vNuevoDestino =  new Ventana_Nuevo_Destino(this);
    Ventana_EstadoRuta vEstadoRuta = new Ventana_EstadoRuta(this);
    
    public Ventana_Admin(Marco marco) {
        initComponents();
        this.marco=marco;  
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
            e.getMessage();
            e.printStackTrace();
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
            e.getMessage();
            e.printStackTrace();
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
                datos[0]=marco.vLogin.conect.res.getString(1);
                datos[1]=marco.vLogin.conect.res.getString(2);
                datos[2]=marco.vLogin.conect.res.getString(3);
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
            e.getMessage();
            e.printStackTrace();
        }
    }

    public void actualizarTablas() throws SQLException{
        tablaPC();
        //tablaPCenRuta();
        tablaRuta();
        tablaUser();
        marco.vLogin.conect.stmt = marco.vLogin.conect.conexion.createStatement();
        marco.vLogin.conect.res = marco.vLogin.conect.stmt.executeQuery("SELECT * FROM registro;");
        marco.vLogin.conect.res.next();
        txtTarifa.setText("Tarifa: "+marco.vLogin.conect.res.getString(2));
        txtPriori.setText("Priorizacion: "+marco.vLogin.conect.res.getString(5));
        txtLibra.setText("Libra: "+marco.vLogin.conect.res.getString(3));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subAdmin = new javax.swing.JTabbedPane();
        Panel_Usuarios = new javax.swing.JPanel();
        botonNuevoUsusario = new javax.swing.JButton();
        botonQuitarUsuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsusarios = new javax.swing.JTable();
        botonEditaUSER = new javax.swing.JButton();
        Panel_Rutas = new javax.swing.JPanel();
        scrollTRutas = new javax.swing.JScrollPane();
        tablaRutas = new javax.swing.JTable();
        botonNuevaRuta = new javax.swing.JButton();
        botonEliminarRuta = new javax.swing.JButton();
        botonAsignatPC = new javax.swing.JButton();
        botonEstosRuta = new javax.swing.JButton();
        Panel_Puntos = new javax.swing.JPanel();
        botonNuevoPC = new javax.swing.JButton();
        botonEditarPC = new javax.swing.JButton();
        botonEliminarPC = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPC = new javax.swing.JTable();
        panelDatos = new javax.swing.JPanel();
        botonEstablecer = new javax.swing.JButton();
        txtTarifa = new javax.swing.JLabel();
        botonDestinos = new javax.swing.JButton();
        botonNuevoDestino = new javax.swing.JButton();
        txtPriori = new javax.swing.JLabel();
        botonPr = new javax.swing.JButton();
        txtLibra = new javax.swing.JLabel();
        botonLibra = new javax.swing.JButton();

        setIconifiable(true);
        setRequestFocusEnabled(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subAdminMouseClicked(evt);
            }
        });

        botonNuevoUsusario.setText("Nuevo Usuario");
        botonNuevoUsusario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonNuevoUsusarioMouseClicked(evt);
            }
        });

        botonQuitarUsuario.setText("Quitar Usuario");
        botonQuitarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonQuitarUsuarioMouseClicked(evt);
            }
        });

        tablaUsusarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaUsusarios);

        botonEditaUSER.setText("Editar Datos de Usuario");
        botonEditaUSER.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEditaUSERMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_UsuariosLayout = new javax.swing.GroupLayout(Panel_Usuarios);
        Panel_Usuarios.setLayout(Panel_UsuariosLayout);
        Panel_UsuariosLayout.setHorizontalGroup(
            Panel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_UsuariosLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(botonNuevoUsusario, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(botonQuitarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(botonEditaUSER, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        Panel_UsuariosLayout.setVerticalGroup(
            Panel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_UsuariosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(Panel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNuevoUsusario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonQuitarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEditaUSER, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        subAdmin.addTab("Usuarios", Panel_Usuarios);

        tablaRutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollTRutas.setViewportView(tablaRutas);

        botonNuevaRuta.setText("Nueva Ruta");
        botonNuevaRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonNuevaRutaMouseClicked(evt);
            }
        });

        botonEliminarRuta.setText("Eliminar Ruta");
        botonEliminarRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEliminarRutaMouseClicked(evt);
            }
        });

        botonAsignatPC.setText("Editar Rutas");
        botonAsignatPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAsignatPCMouseClicked(evt);
            }
        });

        botonEstosRuta.setText("Descativar / Activar");
        botonEstosRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEstosRutaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Panel_RutasLayout = new javax.swing.GroupLayout(Panel_Rutas);
        Panel_Rutas.setLayout(Panel_RutasLayout);
        Panel_RutasLayout.setHorizontalGroup(
            Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_RutasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollTRutas))
            .addGroup(Panel_RutasLayout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(botonNuevaRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addGroup(Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonEstosRuta)
                    .addGroup(Panel_RutasLayout.createSequentialGroup()
                        .addComponent(botonAsignatPC, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(botonEliminarRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        Panel_RutasLayout.setVerticalGroup(
            Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_RutasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(botonEstosRuta)
                .addGap(29, 29, 29)
                .addGroup(Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAsignatPC)
                    .addComponent(botonEliminarRuta)
                    .addComponent(botonNuevaRuta))
                .addGap(18, 18, 18)
                .addComponent(scrollTRutas, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                .addContainerGap())
        );

        subAdmin.addTab("Rutas", Panel_Rutas);

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
                .addContainerGap(111, Short.MAX_VALUE))
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

        subAdmin.addTab("Puntos de Control", Panel_Puntos);

        botonEstablecer.setText("Establece Tarifa Global");
        botonEstablecer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEstablecerMouseClicked(evt);
            }
        });

        botonDestinos.setText("Destinos");
        botonDestinos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonDestinosMouseClicked(evt);
            }
        });

        botonNuevoDestino.setText("Nuevo Destino");
        botonNuevoDestino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonNuevoDestinoMouseClicked(evt);
            }
        });

        botonPr.setText("Establece Precio Priorizacion");
        botonPr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonPrMouseClicked(evt);
            }
        });

        botonLibra.setText("Establecer Precio Libra");
        botonLibra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonLibraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLibra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPriori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTarifa, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                .addGap(118, 118, 118)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonEstablecer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonDestinos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonPr, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addComponent(botonNuevoDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonLibra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonEstablecer)
                    .addComponent(txtTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPriori, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonPr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLibra, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonLibra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonDestinos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonNuevoDestino)
                .addContainerGap(427, Short.MAX_VALUE))
        );

        subAdmin.addTab("Datos", panelDatos);

        getContentPane().add(subAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 900, 660));
        subAdmin.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevoUsusarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonNuevoUsusarioMouseClicked
        marco.PanelEscritorio.add(vnuevoUsuario);
        vnuevoUsuario.show();    
    }//GEN-LAST:event_botonNuevoUsusarioMouseClicked

    private void botonQuitarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonQuitarUsuarioMouseClicked
        marco.PanelEscritorio.add(vEliminar);
        vEliminar.show();
        vEliminar.tablaUser();
        tablaUser();
    }//GEN-LAST:event_botonQuitarUsuarioMouseClicked

    private void botonEditaUSERMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEditaUSERMouseClicked
        marco.PanelEscritorio.add(editarUsuario);
        editarUsuario.show();
        editarUsuario.tablaUser();
    }//GEN-LAST:event_botonEditaUSERMouseClicked

    private void botonNuevoPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonNuevoPCMouseClicked
        // TODO add your handling code here:
        marco.PanelEscritorio.add(vNuevoPC);
        vNuevoPC.show();
        
    }//GEN-LAST:event_botonNuevoPCMouseClicked

    private void botonEditarPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEditarPCMouseClicked
        marco.PanelEscritorio.add(vEditarPC);
        vEditarPC.show();
        vEditarPC.tablaPc();
    }//GEN-LAST:event_botonEditarPCMouseClicked

    private void botonEliminarPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarPCMouseClicked
        marco.PanelEscritorio.add(vEliminarPC);
        vEliminarPC.show();
        vEliminarPC.tablaPc();
    }//GEN-LAST:event_botonEliminarPCMouseClicked

    private void botonNuevaRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonNuevaRutaMouseClicked
        marco.PanelEscritorio.add(vNuevaRuta);
        vNuevaRuta.show();
        try {
            vNuevaRuta.llenarcombos();
        } catch (SQLException ex) {
            System.out.println("no agrego");
        } 
    }//GEN-LAST:event_botonNuevaRutaMouseClicked

    private void botonEliminarRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarRutaMouseClicked
        marco.PanelEscritorio.add(vEliminarRuta);
        vEliminarRuta.show();
        vEliminarRuta.tablaRuta();
    }//GEN-LAST:event_botonEliminarRutaMouseClicked

    private void subAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subAdminMouseClicked
        try { 
            actualizarTablas();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_subAdminMouseClicked

    private void botonAsignatPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAsignatPCMouseClicked
        try {
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

    private void botonDestinosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonDestinosMouseClicked
        marco.PanelEscritorio.add(vDestino);
        vDestino.show();
    }//GEN-LAST:event_botonDestinosMouseClicked

    private void botonNuevoDestinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonNuevoDestinoMouseClicked
        marco.PanelEscritorio.add(vNuevoDestino);
        vNuevoDestino.show();
    }//GEN-LAST:event_botonNuevoDestinoMouseClicked

    private void botonEstosRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEstosRutaMouseClicked
         marco.PanelEscritorio.add(vEstadoRuta);
        vEstadoRuta.show();
        vEstadoRuta.llenarTabla();
    }//GEN-LAST:event_botonEstosRutaMouseClicked

    private void botonEstablecerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEstablecerMouseClicked
        int tarifa = Integer.parseInt(JOptionPane.showInputDialog("Introduzca La Nueva Tarifa"));
        marco.vLogin.conect.actualizarTarifa(tarifa);
        txtTarifa.setText("Tarifa: "+tarifa);
    }//GEN-LAST:event_botonEstablecerMouseClicked

    private void botonPrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonPrMouseClicked
        int tarifa = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el Precio de Priorizacion"));
        marco.vLogin.conect.actualizarPriosizacion(tarifa);
        txtPriori.setText("Priorizacion: "+tarifa);
    }//GEN-LAST:event_botonPrMouseClicked

    private void botonLibraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonLibraMouseClicked
        int tarifa = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el Precio por Libra"));
        marco.vLogin.conect.actualizarPriosizacion(tarifa);
        txtLibra.setText("Libra: "+tarifa);
    }//GEN-LAST:event_botonLibraMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Puntos;
    private javax.swing.JPanel Panel_Rutas;
    private javax.swing.JPanel Panel_Usuarios;
    private javax.swing.JButton botonAsignatPC;
    private javax.swing.JButton botonDestinos;
    private javax.swing.JButton botonEditaUSER;
    private javax.swing.JButton botonEditarPC;
    private javax.swing.JButton botonEliminarPC;
    private javax.swing.JButton botonEliminarRuta;
    private javax.swing.JButton botonEstablecer;
    private javax.swing.JButton botonEstosRuta;
    private javax.swing.JButton botonLibra;
    private javax.swing.JButton botonNuevaRuta;
    private javax.swing.JButton botonNuevoDestino;
    private javax.swing.JButton botonNuevoPC;
    private javax.swing.JButton botonNuevoUsusario;
    private javax.swing.JButton botonPr;
    private javax.swing.JButton botonQuitarUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JScrollPane scrollTRutas;
    private javax.swing.JTabbedPane subAdmin;
    private javax.swing.JTable tablaPC;
    private javax.swing.JTable tablaRutas;
    private javax.swing.JTable tablaUsusarios;
    private javax.swing.JLabel txtLibra;
    private javax.swing.JLabel txtPriori;
    private javax.swing.JLabel txtTarifa;
    // End of variables declaration//GEN-END:variables
}

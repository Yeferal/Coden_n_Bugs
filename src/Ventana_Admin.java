
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class Ventana_Admin extends javax.swing.JInternalFrame {

    String usuarios="SELECT * FROM usuario";
    Marco marco;
    Ventana_Nuevo_Usuario vnuevoUsuario = new Ventana_Nuevo_Usuario(this);
    Editar_Usuario editarUsuario = new Editar_Usuario(this);
    Ventana_Eliminar_Usuario vEliminar = new Ventana_Eliminar_Usuario(this);
    
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
                datos[0]=marco.vLogin.conect.res.getString(1);
                datos[1]=marco.vLogin.conect.res.getString(2);
                datos[2]=marco.vLogin.conect.res.getString(3);
                datos[3]=marco.vLogin.conect.res.getString(4);
                datos[4]=marco.vLogin.conect.res.getString(5);
                modelo1.addRow(datos);      
            }  
        } catch (Exception e) {    
        }
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
        Panel_Puntos = new javax.swing.JPanel();
        botonNuevoPC = new javax.swing.JButton();
        botonEditarPC = new javax.swing.JButton();
        botonEliminarPC = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setIconifiable(true);
        setRequestFocusEnabled(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Usuarios", Panel_Usuarios);

        javax.swing.GroupLayout Panel_RutasLayout = new javax.swing.GroupLayout(Panel_Rutas);
        Panel_Rutas.setLayout(Panel_RutasLayout);
        Panel_RutasLayout.setHorizontalGroup(
            Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 888, Short.MAX_VALUE)
        );
        Panel_RutasLayout.setVerticalGroup(
            Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Rutas", Panel_Rutas);

        botonNuevoPC.setText("Nuevo Punto de Control");

        botonEditarPC.setText("Editar Punto de Control");

        botonEliminarPC.setText("Eliminar Punto de Control");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable1);

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
                .addContainerGap(101, Short.MAX_VALUE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Puntos de Control", Panel_Puntos);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Puntos;
    private javax.swing.JPanel Panel_Rutas;
    private javax.swing.JPanel Panel_Usuarios;
    private javax.swing.JButton botonEditarPC;
    private javax.swing.JButton botonEliminarPC;
    private javax.swing.JButton botonNuevoPC;
    private javax.swing.JButton botonNuevoUsusario;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tablaUsusarios;
    // End of variables declaration//GEN-END:variables
}

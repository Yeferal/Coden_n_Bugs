
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ventana_Eliminar_Usuario extends javax.swing.JInternalFrame {

    Ventana_Admin vadmin;
    int ids;
    boolean seleccionado;
            
    public Ventana_Eliminar_Usuario(Ventana_Admin vadmin) {
        initComponents();
        this.vadmin=vadmin;
    }

    public void tablaUser(){
        DefaultTableModel modelo1 = new DefaultTableModel();
        
        modelo1.addColumn("ID");
        modelo1.addColumn("Nombre");
        modelo1.addColumn("Codigo");
        modelo1.addColumn("Tipo");
        modelo1.addColumn("Password");
       
        tablaUsurios.setModel(modelo1);
        String datos[]= new String[5];
        try {
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery(vadmin.usuarios);
            
            while (vadmin.marco.vLogin.conect.res.next()) {                
                //System.out.println(marco.vLogin.conect.res.getString(1));
                datos[0]=vadmin.marco.vLogin.conect.res.getString(1);
                datos[1]=vadmin.marco.vLogin.conect.res.getString(2);
                datos[2]=vadmin.marco.vLogin.conect.res.getString(3);
                switch(vadmin.marco.vLogin.conect.res.getString(4)){
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
                datos[4]=vadmin.marco.vLogin.conect.res.getString(5);
                modelo1.addRow(datos);
                
            }
            
        } catch (Exception e) {
            
        }
        
        
    }
    private void limpiar(){
        labeId.setText("");
        labelname.setText("");
        labbelCodigo.setText("");
        labelPass.setText("");
    }
    
    public void enviarUsuario(int id){
        
        vadmin.marco.vLogin.conect.eliminarUsuario(id);
        seleccionado=false;
        limpiar();
        tablaUser();
        vadmin.tablaUser();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEliminarUser = new javax.swing.JPanel();
        scrollEliminar = new javax.swing.JScrollPane();
        tablaUsurios = new javax.swing.JTable();
        bonteliminat = new javax.swing.JButton();
        textoID = new javax.swing.JLabel();
        textoNombre = new javax.swing.JLabel();
        textoCodigo = new javax.swing.JLabel();
        textoPass = new javax.swing.JLabel();
        labeId = new javax.swing.JLabel();
        labelname = new javax.swing.JLabel();
        labbelCodigo = new javax.swing.JLabel();
        labelPass = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaUsurios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaUsurios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuriosMouseClicked(evt);
            }
        });
        scrollEliminar.setViewportView(tablaUsurios);

        bonteliminat.setText("Eliminar Usuario");
        bonteliminat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bonteliminatMouseClicked(evt);
            }
        });

        textoID.setText("ID:");

        textoNombre.setText("Nombre:");

        textoCodigo.setText("Codigo:");

        textoPass.setText("Password:");

        javax.swing.GroupLayout panelEliminarUserLayout = new javax.swing.GroupLayout(panelEliminarUser);
        panelEliminarUser.setLayout(panelEliminarUserLayout);
        panelEliminarUserLayout.setHorizontalGroup(
            panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bonteliminat)
                    .addGroup(panelEliminarUserLayout.createSequentialGroup()
                        .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textoPass)
                            .addComponent(textoNombre)
                            .addComponent(textoCodigo)
                            .addComponent(textoID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labeId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labbelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelPass, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panelEliminarUserLayout.setVerticalGroup(
            panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEliminarUserLayout.createSequentialGroup()
                .addComponent(scrollEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEliminarUserLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoID)
                    .addComponent(labeId, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNombre)
                    .addComponent(labelname, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoCodigo)
                    .addComponent(labbelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(panelEliminarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoPass)
                    .addComponent(labelPass, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bonteliminat)
                .addGap(18, 18, 18))
        );

        getContentPane().add(panelEliminarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaUsuriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuriosMouseClicked
        
        int fila=tablaUsurios.getSelectedRow();
        ids=  Integer.parseInt((String) tablaUsurios.getValueAt(fila, 0));

        labeId.setText(tablaUsurios.getValueAt(fila, 0).toString());
        labelname.setText(tablaUsurios.getValueAt(fila, 1).toString());
        labbelCodigo.setText(tablaUsurios.getValueAt(fila,2).toString());
        labelPass.setText(tablaUsurios.getValueAt(fila, 4).toString());
        seleccionado=true;
    }//GEN-LAST:event_tablaUsuriosMouseClicked

    private void bonteliminatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bonteliminatMouseClicked
        // TODO add your handling code here:
        if(seleccionado){
            enviarUsuario(ids);
            JOptionPane.showMessageDialog(null, "Se elimino correctamente el Usuario");
        }
    }//GEN-LAST:event_bonteliminatMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bonteliminat;
    private javax.swing.JLabel labbelCodigo;
    private javax.swing.JLabel labeId;
    private javax.swing.JLabel labelPass;
    private javax.swing.JLabel labelname;
    private javax.swing.JPanel panelEliminarUser;
    private javax.swing.JScrollPane scrollEliminar;
    private javax.swing.JTable tablaUsurios;
    private javax.swing.JLabel textoCodigo;
    private javax.swing.JLabel textoID;
    private javax.swing.JLabel textoNombre;
    private javax.swing.JLabel textoPass;
    // End of variables declaration//GEN-END:variables
}

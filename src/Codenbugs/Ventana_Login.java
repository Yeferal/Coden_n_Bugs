package Codenbugs;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Ventana_Login extends javax.swing.JInternalFrame {
    Conector conect = new Conector();
    Usuario usuarioLogin;
    //Connection conexion =  null;
    String user = "root";
    String password = "123-abc";
    String servidor = "jdbc:mysql://localhost:3306/codenbugs";
    Marco marco;
    String usuarios="SELECT * FROM usuario";
    
    public Ventana_Login(Marco marco) {
        initComponents();
        this.marco=marco;
    }
    private void buscar(){
        System.out.println("entra");
        
        try {
            marco.vLogin.conect.stmt = marco.vLogin.conect.conexion.createStatement();
            marco.vLogin.conect.res = marco.vLogin.conect.stmt.executeQuery(usuarios);
            
            while (marco.vLogin.conect.res.next()) {                
                if(CajaCodigo.getText().equals(marco.vLogin.conect.res.getString(3))){
                    if(cajaPasswird.getText().equals(marco.vLogin.conect.res.getString(5))){
                        sesionUsusario(marco.vLogin.conect.res.getString(1), marco.vLogin.conect.res.getString(2), marco.vLogin.conect.res.getString(3), marco.vLogin.conect.res.getString(4), marco.vLogin.conect.res.getString(5));
                        
                        System.out.println("funciono");
                        this.dispose();
                        break;
                    }else{
                        System.out.println("falla 1");
                    }
                }
                        System.out.println("falla 2");  
            }    
        } catch (Exception e) {
            
        }
    }
    
    public void sesionUsusario(String id, String Nombre, String codigo, String tipo, String passw){
        usuarioLogin=new Usuario();
        usuarioLogin.setDatos(Nombre, codigo, Integer.parseInt(tipo), passw);
        usuarioLogin.setId(Integer.parseInt(id));
        switch(usuarioLogin.gettipo()){
            case 1:
                marco.vadministrador.tablaUser();
                marco.vadministrador.tablaPC();
                marco.vadministrador.tablaRuta();
                marco.vadministrador.show();
                break;
            case 2:
                //Abre ventana operador
                marco.vOperador.show();
                marco.vOperador.actualizarLista();
                break;
            case 3:
                //Abre ventana recepcionista
                marco.vReceptor.show();
                break;
                default:     
        }  
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLogin = new javax.swing.JPanel();
        TextoCodigo = new javax.swing.JLabel();
        TextoContraseña = new javax.swing.JLabel();
        CajaCodigo = new javax.swing.JTextField();
        IniciarUser = new javax.swing.JButton();
        cajaPasswird = new javax.swing.JPasswordField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TextoCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextoCodigo.setText("Codigo :");

        TextoContraseña.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextoContraseña.setText("Contraseña");

        CajaCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CajaCodigoKeyTyped(evt);
            }
        });

        IniciarUser.setText("Login");
        IniciarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarUserActionPerformed(evt);
            }
        });

        cajaPasswird.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cajaPasswirdKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(TextoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IniciarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cajaPasswird)))
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(TextoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CajaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CajaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaPasswird, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(IniciarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        getContentPane().add(panelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, -1, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CajaCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaCodigoKeyTyped
        char cajaCodigo = evt.getKeyChar();
        if(cajaCodigo==KeyEvent.VK_ENTER){
            cajaPasswird.requestFocus();;
        }
    }//GEN-LAST:event_CajaCodigoKeyTyped

    private void IniciarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarUserActionPerformed
        buscar();
    }//GEN-LAST:event_IniciarUserActionPerformed

    private void cajaPasswirdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cajaPasswirdKeyTyped
        char c = evt.getKeyChar();
        if(c==KeyEvent.VK_ENTER){
            IniciarUser.doClick();
        }
    }//GEN-LAST:event_cajaPasswirdKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CajaCodigo;
    private javax.swing.JButton IniciarUser;
    private javax.swing.JLabel TextoCodigo;
    private javax.swing.JLabel TextoContraseña;
    private javax.swing.JPasswordField cajaPasswird;
    private javax.swing.JPanel panelLogin;
    // End of variables declaration//GEN-END:variables
}

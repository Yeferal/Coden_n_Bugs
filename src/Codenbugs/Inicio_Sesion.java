


import com.sun.glass.events.KeyEvent;

public class Inicio_Sesion extends javax.swing.JFrame {

    public Inicio_Sesion() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        TextoCodigo = new javax.swing.JLabel();
        TextoContraseña = new javax.swing.JLabel();
        CajaCodigo = new javax.swing.JTextField();
        CajaContraseña = new javax.swing.JTextField();
        IniciarUser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        TextoCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextoCodigo.setText("Codigo:");

        TextoContraseña.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TextoContraseña.setText("Contraseña");

        CajaCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CajaCodigoKeyTyped(evt);
            }
        });

        CajaContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CajaContraseñaKeyTyped(evt);
            }
        });

        IniciarUser.setText("Login");
        IniciarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TextoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CajaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TextoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CajaContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IniciarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CajaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TextoContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(49, 49, 49)
                .addComponent(IniciarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void IniciarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IniciarUserActionPerformed

    private void CajaCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaCodigoKeyTyped
        char cajaCodigo = evt.getKeyChar();
        
        if(cajaCodigo== KeyEvent.VK_ENTER){
            
            CajaContraseña.requestFocus();
            //IniciarUser.doClick();
        }
        
    }//GEN-LAST:event_CajaCodigoKeyTyped

    private void CajaContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaContraseñaKeyTyped

        char cajaCodigo = evt.getKeyChar();
        
        if(cajaCodigo==KeyEvent.VK_ENTER){
            
            IniciarUser.doClick();
        }
    }//GEN-LAST:event_CajaContraseñaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CajaCodigo;
    private javax.swing.JTextField CajaContraseña;
    private javax.swing.JButton IniciarUser;
    private javax.swing.JLabel TextoCodigo;
    private javax.swing.JLabel TextoContraseña;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

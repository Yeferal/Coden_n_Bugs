/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author LENOVO-PC
 */
public class Ventana_Administrador extends javax.swing.JFrame {

    /**
     * Creates new form Ventana_Administrador
     */
    public Ventana_Administrador() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        Panel_Usuarios = new javax.swing.JPanel();
        Panel_Rutas = new javax.swing.JPanel();
        Panel_Puntos = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout Panel_UsuariosLayout = new javax.swing.GroupLayout(Panel_Usuarios);
        Panel_Usuarios.setLayout(Panel_UsuariosLayout);
        Panel_UsuariosLayout.setHorizontalGroup(
            Panel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );
        Panel_UsuariosLayout.setVerticalGroup(
            Panel_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Usuarios", Panel_Usuarios);

        javax.swing.GroupLayout Panel_RutasLayout = new javax.swing.GroupLayout(Panel_Rutas);
        Panel_Rutas.setLayout(Panel_RutasLayout);
        Panel_RutasLayout.setHorizontalGroup(
            Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );
        Panel_RutasLayout.setVerticalGroup(
            Panel_RutasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Rutas", Panel_Rutas);

        javax.swing.GroupLayout Panel_PuntosLayout = new javax.swing.GroupLayout(Panel_Puntos);
        Panel_Puntos.setLayout(Panel_PuntosLayout);
        Panel_PuntosLayout.setHorizontalGroup(
            Panel_PuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );
        Panel_PuntosLayout.setVerticalGroup(
            Panel_PuntosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 504, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Puntos de Control", Panel_Puntos);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 770, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Puntos;
    private javax.swing.JPanel Panel_Rutas;
    private javax.swing.JPanel Panel_Usuarios;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}

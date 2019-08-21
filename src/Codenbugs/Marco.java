package Codenbugs;

public class Marco extends javax.swing.JFrame {
    
    Ventana_Admin vadministrador = new Ventana_Admin(this);
    Ventana_Login vLogin = new Ventana_Login(this);
    Ventana_Recepcionista vReceptor = new Ventana_Recepcionista(this);
    Ventana_Operator vOperador = new Ventana_Operator(this,vLogin.usuarioLogin);
    
    
    public Marco() {
        initComponents();
        
        this.PanelEscritorio.add(vadministrador);
        this.PanelEscritorio.add(vLogin);
        this.PanelEscritorio.add(vReceptor);
        this.PanelEscritorio.add(vOperador);
        vLogin.show();
        //vadministrador.show();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelEscritorio = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);

        javax.swing.GroupLayout PanelEscritorioLayout = new javax.swing.GroupLayout(PanelEscritorio);
        PanelEscritorio.setLayout(PanelEscritorioLayout);
        PanelEscritorioLayout.setHorizontalGroup(
            PanelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 748, Short.MAX_VALUE)
        );
        PanelEscritorioLayout.setVerticalGroup(
            PanelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelEscritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelEscritorio)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JDesktopPane PanelEscritorio;
    // End of variables declaration//GEN-END:variables
}

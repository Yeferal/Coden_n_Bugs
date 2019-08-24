
package bodega;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Ventana_Bodega extends javax.swing.JFrame {
    
    Conector conect = new Conector();
    //Bodega bodega = new Bodega();
    Inicio inicio;
    public Ventana_Bodega(Inicio inicio) {
        initComponents();
        rellenarTabla();
        this.inicio = inicio;
    }
    /**Se genera el modelo  de la tabla y luego se agregan las filas.
     * Se conecta con la tabla de paquetes en bodega esto tuples son agregados
     * a la tabla para poder visualizar los que aun estan en cola
     */
    public void rellenarTabla(){
        DefaultTableModel modelo1 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }  
        };
        modelo1.addColumn("ID Bodega");
        modelo1.addColumn("Destino Paquete");
        modelo1.addColumn("Priorizado");
        modelo1.addColumn("Id Paquete");

        tablaBodega.setModel(modelo1);
        String datos[]= new String[4];
        try {
            conect.stmt = conect.conexion.createStatement();
            conect.res = conect.stmt.executeQuery("SELECT * FROM bodega ORDER BY priorizado_pq DESC;");
            
            while (conect.res.next()) {
                datos[0] = conect.res.getString(1);
                datos[1] = conect.res.getString(2);
                if(conect.res.getString(3).equals("0")){
                    datos[2] = "NO";
                }else{
                    datos[2] = "SI";
                }
                datos[3] = conect.res.getString(4);
                modelo1.addRow(datos);  
            }
            
        } catch (Exception ex) {
            System.out.println("Vacio");
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollTabla = new javax.swing.JScrollPane();
        tablaBodega = new javax.swing.JTable();
        scrollArea = new javax.swing.JScrollPane();
        areaDescripcion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaBodega.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollTabla.setViewportView(tablaBodega);

        getContentPane().add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 840, 648));

        areaDescripcion.setColumns(20);
        areaDescripcion.setRows(5);
        scrollArea.setViewportView(areaDescripcion);

        getContentPane().add(scrollArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 40, 340, 310));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaDescripcion;
    private javax.swing.JScrollPane scrollArea;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tablaBodega;
    // End of variables declaration//GEN-END:variables
}

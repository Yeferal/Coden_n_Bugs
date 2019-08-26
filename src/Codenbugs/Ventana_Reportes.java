
package Codenbugs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Ventana_Reportes extends javax.swing.JInternalFrame {
    private Ventana_Admin vadmin;
    private DefaultTableModel modelo1 = null;
    private Statement stamet;
    private ResultSet resultado;
    private String fecha1;
    private String fecha2;
    private String tipo;
    private String repoTitulo;
    private String estV;
    
    private ArrayList<int[]> cantidad = new ArrayList<int[]>();
    
    public Ventana_Reportes(Ventana_Admin vadmin) {
        ArrayList<String> nombreArrayList = new ArrayList<String>();
        initComponents();
        this.vadmin=vadmin;
    }
    /**Oculta todos los paneles**/
    public void ocultar(){
        panelPa.setVisible(false);
        panelGanancias1.setVisible(false);
        panelClientes.setVisible(false);
        panelGanancias2.setVisible(false);
    }
    /**Añade el modelo para los paquetes en la tabla**/
    private void tablaRutasPaquete() throws SQLException{
        modelo1 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }  
        };
        modelo1.addColumn("ID paquete");
        modelo1.addColumn("ID Ruta");
        modelo1.addColumn("Destino");
        modelo1.addColumn("NIT");
        modelo1.addColumn("Fecha Entrega");
        tabla1.setModel(modelo1);
        
    }
    /**crea el modelo y lo hace no editable**/
    private void modelo(){
    modelo1 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }  
        };
    }
    /**establece las columnas de la tabla**/
    private void tablaGanancias() throws SQLException{
        modelo();
        modelo1.addColumn("Ruta");
        modelo1.addColumn("Destino");
        modelo1.addColumn("Costo");
        modelo1.addColumn("Ingreso");
        modelo1.addColumn("Ganancia");
        tabla1.setModel(modelo1);
    }
    /**establece las columnas de la tabla**/
    private void tablaClientes() throws SQLException{
        modelo();
        modelo1.addColumn("NIT");
        modelo1.addColumn("ID paquete");
        modelo1.addColumn("Destino");
        modelo1.addColumn("Costo");
        modelo1.addColumn("Ingreso");
        modelo1.addColumn("Ganancia");
        tabla1.setModel(modelo1);
    }
    /**establece las columnas de la tabla**/
    private void tablaMejores() throws SQLException{
        modelo();
        modelo1.addColumn("ID Ruta");
        modelo1.addColumn("No. Paquetes");
        tabla1.setModel(modelo1);
    }
    /**agrega las filas de paquetes que estan en ruta**/
    private void agregarFilaRutaActivos(){
        try {
            String datos[]= new String[5];
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM paquete WHERE (entrega IS NULL) AND (id_pc IS NOT NULL);");
            while(vadmin.marco.vLogin.conect.res.next()){
                datos[0]=vadmin.marco.vLogin.conect.res.getString(1);
                datos[1]=vadmin.marco.vLogin.conect.pcConeC.getRutaPaquete(vadmin.marco.vLogin.conect.res.getString(4));
                datos[2]=vadmin.marco.vLogin.conect.res.getString(9);
                datos[3]=vadmin.marco.vLogin.conect.res.getString(3);
                datos[4]=vadmin.marco.vLogin.conect.res.getString(11);
                modelo1.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    /**agrega las filas de paquetes que no estan en ruta**/
    private void agregarFilaRutaInativos(){
        try {
            String datos[]= new String[5];
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM entregas;");
            while(vadmin.marco.vLogin.conect.res.next()){
                datos[0]=vadmin.marco.vLogin.conect.res.getString(1);
                datos[1]=vadmin.marco.vLogin.conect.res.getString(3);
                datos[2]=vadmin.marco.vLogin.conect.res.getString(4);
                datos[3]=vadmin.marco.vLogin.conect.res.getString(2);
                datos[4]=vadmin.marco.vLogin.conect.res.getString(8);
                modelo1.addRow(datos);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    /**agrega las filas de los clientes**/
    private void agregarFilasClientes(String nit) throws SQLException{
        String datos[]= new String[6];
        if(nit.equals("")){
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM entregas;");
        }else{
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM entregas WHERE nit="+nit+";");
        }
            while(vadmin.marco.vLogin.conect.res.next()){
                datos[0]=vadmin.marco.vLogin.conect.res.getString(2);
                datos[1]=vadmin.marco.vLogin.conect.res.getString(1);
                datos[2]=vadmin.marco.vLogin.conect.res.getString(4);
                datos[3]=vadmin.marco.vLogin.conect.res.getString(6);
                datos[4]=vadmin.marco.vLogin.conect.res.getString(5);
                datos[5]=vadmin.marco.vLogin.conect.res.getString(7);
                modelo1.addRow(datos);
            }
    }
    /**agrega las filas de ganancias**/
    private void agregarFilasGanancias(String fecha1,String fecha2) throws SQLException{
        String datos[]= new String[5];
        vadmin.marco.vLogin.conect.pcConeC.actulizarGananias();
        if(fecha1.equals("") && fecha2.equals("")){
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM entregas;");
        }else{
            vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
            vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM entregas WHERE TIMESTAMPDIFF(DAY,'"+fecha2+"',fecha_entrega)>=0 AND TIMESTAMPDIFF(DAY,fecha_entrega,'"+fecha1+"')>=0;");
        }
            while(vadmin.marco.vLogin.conect.res.next()){
                datos[0]=vadmin.marco.vLogin.conect.res.getString(3);
                datos[1]=vadmin.marco.vLogin.conect.res.getString(4);
                datos[2]=vadmin.marco.vLogin.conect.res.getString(5);
                datos[3]=vadmin.marco.vLogin.conect.res.getString(6);
                datos[4]=vadmin.marco.vLogin.conect.res.getString(7);
                modelo1.addRow(datos);
            }
    }
    /**Primero lee todas las ruta existentes luego busca el id de esa ruta en la tabla de entregas
     * lo busca con el intervalo de tiempo y cuenta el numerod e regisros y lo agrega al al arraylist
     * para luego agregar a la tabla los que tiene el arraylist
     * @param fecha1
     * @param fecha2
     * @throws SQLException 
     */
    private void setLista(String fecha1,String fecha2) throws SQLException{
        String idRutaActual = null;
        cantidad.clear();
        vadmin.marco.vLogin.conect.stmt = vadmin.marco.vLogin.conect.conexion.createStatement();
        vadmin.marco.vLogin.conect.res = vadmin.marco.vLogin.conect.stmt.executeQuery("SELECT id_ruta FROM ruta;");
        while (vadmin.marco.vLogin.conect.res.next()) {
            int numero [] = new int[2];            
            idRutaActual=vadmin.marco.vLogin.conect.res.getString(1);
            numero[0]=Integer.parseInt(idRutaActual);
            
            stamet = vadmin.marco.vLogin.conect.conexion.createStatement();
            resultado = stamet.executeQuery("SELECT count(id_paquete) FROM entregas WHERE (TIMESTAMPDIFF(DAY,'"+fecha2+"',fecha_entrega)>=0) AND (TIMESTAMPDIFF(DAY,fecha_entrega,'"+fecha1+"')>=0) AND (id_ruta="+idRutaActual+");");
            resultado.next();
             if(resultado.getString(1)!=null){
                 numero[1]=Integer.parseInt(resultado.getString(1));
             }else{
                 numero[1]=0;
             }   
            cantidad.add(numero);
        }
    }
    /**primero ordena los datos del arraylist
     * luego agrega los primeros tres datos a la tabla
     */
    private void agregarFilaRutasMejores(){
         String datos[]= new String[2];
         int aux [];
         for (int i = 0; i < (cantidad.size()-1); i++) {
             for (int j = 0; j < (cantidad.size()-1); j++) {
                 if(cantidad.get(j)[1]<cantidad.get(j+1)[1]){
                     aux = cantidad.get(j);
                     cantidad.set(j, cantidad.get(j+1));
                     cantidad.set(j+1, aux);
                 }
             }
        } 
         for (int i = 0; i < 3; i++) {
            datos[0]=Integer.toString(cantidad.get(i)[0]);
            datos[1]=Integer.toString(cantidad.get(i)[1]); 
            modelo1.addRow(datos);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollTabla = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        panelPa = new javax.swing.JPanel();
        labelPaquete = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        panelBotones = new javax.swing.JPanel();
        botonPaquetes = new javax.swing.JButton();
        botonGanancias = new javax.swing.JButton();
        botonMejores = new javax.swing.JButton();
        botonClientes = new javax.swing.JButton();
        botonExportar = new javax.swing.JButton();
        panelGanancias1 = new javax.swing.JPanel();
        comboAnio = new javax.swing.JComboBox<>();
        comboMes = new javax.swing.JComboBox<>();
        comboDia = new javax.swing.JComboBox<>();
        labelAnio = new javax.swing.JLabel();
        labelMes = new javax.swing.JLabel();
        labelDia = new javax.swing.JLabel();
        labelMayor = new javax.swing.JLabel();
        botonFiltarGanancias = new javax.swing.JButton();
        lavelintervalo = new javax.swing.JLabel();
        panelGanancias2 = new javax.swing.JPanel();
        comboAnio1 = new javax.swing.JComboBox<>();
        comboMes1 = new javax.swing.JComboBox<>();
        comboDia1 = new javax.swing.JComboBox<>();
        labelAnio1 = new javax.swing.JLabel();
        labelMes1 = new javax.swing.JLabel();
        labelDia1 = new javax.swing.JLabel();
        labelMenor = new javax.swing.JLabel();
        botonFiltrarRutas = new javax.swing.JButton();
        panelClientes = new javax.swing.JPanel();
        labelNit = new javax.swing.JLabel();
        cajaNit = new javax.swing.JTextField();
        botonFiltrar = new javax.swing.JButton();
        tetoLabel = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollTabla.setViewportView(tabla1);

        getContentPane().add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 1002, 430));

        labelPaquete.setText("Paquetes:");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Activos", "Inactivos" }));
        comboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPaLayout = new javax.swing.GroupLayout(panelPa);
        panelPa.setLayout(panelPaLayout);
        panelPaLayout.setHorizontalGroup(
            panelPaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPaquete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPaLayout.setVerticalGroup(
            panelPaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelPaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPaquete)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        getContentPane().add(panelPa, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 54, -1, 65));

        botonPaquetes.setText("Paquetes en Ruta");
        botonPaquetes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonPaquetesMouseClicked(evt);
            }
        });

        botonGanancias.setText("Ganancias");
        botonGanancias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonGananciasMouseClicked(evt);
            }
        });

        botonMejores.setText("Mejores Rutas");
        botonMejores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonMejoresMouseClicked(evt);
            }
        });

        botonClientes.setText("Clientes");
        botonClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonClientesMouseClicked(evt);
            }
        });

        botonExportar.setText("Exportar Tabla");
        botonExportar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonExportarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(botonPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(botonGanancias, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addGap(81, 81, 81)
                .addComponent(botonMejores)
                .addGap(51, 51, 51)
                .addComponent(botonClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(botonExportar)
                .addGap(56, 56, 56))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonPaquetes)
                    .addComponent(botonGanancias)
                    .addComponent(botonClientes)
                    .addComponent(botonExportar)
                    .addComponent(botonMejores))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        getContentPane().add(panelBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelGanancias1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboAnio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2014", "2015", "2016", "2017", "2018", "2019", "2020", " " }));
        panelGanancias1.add(comboAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 50, -1, -1));

        comboMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", " " }));
        panelGanancias1.add(comboMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 50, -1, -1));

        comboDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", " " }));
        panelGanancias1.add(comboDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 50, -1, -1));

        labelAnio.setText("Año");
        panelGanancias1.add(labelAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 28, -1, -1));

        labelMes.setText("Mes");
        panelGanancias1.add(labelMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 28, -1, -1));

        labelDia.setText("Dia");
        panelGanancias1.add(labelDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 28, -1, -1));

        labelMayor.setText("MAYOR");
        panelGanancias1.add(labelMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 0, -1, -1));

        botonFiltarGanancias.setText("Filtrar");
        botonFiltarGanancias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonFiltarGananciasMouseClicked(evt);
            }
        });
        panelGanancias1.add(botonFiltarGanancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        lavelintervalo.setText("Intervalo");
        panelGanancias1.add(lavelintervalo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        getContentPane().add(panelGanancias1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 250, 120));

        panelGanancias2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboAnio1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2014", "2015", "2016", "2017", "2018", "2019", "2020", " " }));
        panelGanancias2.add(comboAnio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 44, -1, -1));

        comboMes1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", " " }));
        panelGanancias2.add(comboMes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 44, -1, -1));

        comboDia1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", " " }));
        panelGanancias2.add(comboDia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 44, -1, -1));

        labelAnio1.setText("Año");
        panelGanancias2.add(labelAnio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 22, -1, -1));

        labelMes1.setText("Mes");
        panelGanancias2.add(labelMes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 22, -1, -1));

        labelDia1.setText("Dia");
        panelGanancias2.add(labelDia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 22, -1, -1));

        labelMenor.setText("MENOR");
        panelGanancias2.add(labelMenor, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 0, -1, -1));

        botonFiltrarRutas.setText("Filtrar");
        botonFiltrarRutas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonFiltrarRutasMouseClicked(evt);
            }
        });
        panelGanancias2.add(botonFiltrarRutas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        getContentPane().add(panelGanancias2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 170, 120));

        labelNit.setText("NIT:");

        botonFiltrar.setText("Filtrar");
        botonFiltrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonFiltrarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cajaNit))
            .addGroup(panelClientesLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(botonFiltrar)
                .addGap(0, 57, Short.MAX_VALUE))
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajaNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNit))
                .addGap(18, 18, 18)
                .addComponent(botonFiltrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panelClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, -1));
        getContentPane().add(tetoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 170, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonPaquetesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonPaquetesMouseClicked
        ocultar();
        panelPa.setVisible(true);
        tipo = "Paquetes";
        try {
            tablaRutasPaquete();
            agregarFilaRutaActivos();
            agregarFilaRutaInativos();
            tetoLabel.setText(tipo);
        } catch (SQLException ex) {
            ex.getMessage();
        }   
    }//GEN-LAST:event_botonPaquetesMouseClicked

    private void comboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEstadoActionPerformed
        try {
            tablaRutasPaquete();
        } catch (SQLException ex) {
            
        }
        switch (comboEstado.getSelectedIndex()) {
            case 0:
                agregarFilaRutaActivos();
                agregarFilaRutaInativos();
                break;
            case 1:
                agregarFilaRutaActivos();
                break;
            default:
                agregarFilaRutaInativos();
                break;
        }
        
    }//GEN-LAST:event_comboEstadoActionPerformed

    private void botonClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonClientesMouseClicked
        ocultar();
        panelClientes.setVisible(true);
        tipo = "Clientes";
        try {
            tablaClientes();
            agregarFilasClientes("");
            tetoLabel.setText(tipo);
        } catch (SQLException ex) {
            
        }
        
    }//GEN-LAST:event_botonClientesMouseClicked

    private void botonFiltrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonFiltrarMouseClicked
        try {
            tablaGanancias();
        } catch (SQLException ex) {}
        try {
            agregarFilasClientes(cajaNit.getText());
        } catch (SQLException ex) {}
    }//GEN-LAST:event_botonFiltrarMouseClicked

    private void botonGananciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGananciasMouseClicked
        ocultar();
        panelGanancias1.setVisible(true);
        panelGanancias2.setVisible(true);
        botonFiltrarRutas.setVisible(false);
        botonFiltarGanancias.setVisible(true);
        tipo = "Ganacias";
        try {
            tablaGanancias();
            panelGanancias1.setVisible(true);
            panelGanancias2.setVisible(true);
            agregarFilasGanancias("","");
            tetoLabel.setText(tipo);
        } catch (SQLException ex) {
            ex.getMessage();
                }
    }//GEN-LAST:event_botonGananciasMouseClicked

    private void botonFiltarGananciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonFiltarGananciasMouseClicked
        fecha1 = comboAnio.getSelectedItem()+"-"+comboMes.getSelectedItem()+"-"+comboDia.getSelectedItem()+" 00:00:00";
        fecha2 = comboAnio1.getSelectedItem()+"-"+comboMes1.getSelectedItem()+"-"+comboDia1.getSelectedItem()+" 00:00:00";
        
        try {
            tablaGanancias();
            agregarFilasGanancias(fecha1, fecha2);
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_botonFiltarGananciasMouseClicked

    private void botonExportarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonExportarMouseClicked
        Reportes reporte = new Reportes(tabla1);
        if(tipo.equals("Paquetes")){
            estV = comboEstado.getSelectedItem().toString();
        }else{
            estV = cajaNit.getText();
        }
        reporte.titularTabla(tipo, fecha1, fecha2, estV);
    }//GEN-LAST:event_botonExportarMouseClicked

    private void botonMejoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonMejoresMouseClicked
        ocultar();
        panelGanancias1.setVisible(true);
        panelGanancias2.setVisible(true);
        botonFiltarGanancias.setVisible(false);
        botonFiltrarRutas.setVisible(true);
        tipo = "Rutas";
        try {
            tablaMejores();
            tetoLabel.setText(tipo);
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_botonMejoresMouseClicked

    private void botonFiltrarRutasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonFiltrarRutasMouseClicked
        fecha1 = comboAnio.getSelectedItem()+"-"+comboMes.getSelectedItem()+"-"+comboDia.getSelectedItem()+" 00:00:00";
        fecha2 = comboAnio1.getSelectedItem()+"-"+comboMes1.getSelectedItem()+"-"+comboDia1.getSelectedItem()+" 00:00:00";
        try {
            tablaMejores();
            setLista(fecha1, fecha2);
            agregarFilaRutasMejores();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }//GEN-LAST:event_botonFiltrarRutasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonClientes;
    private javax.swing.JButton botonExportar;
    private javax.swing.JButton botonFiltarGanancias;
    private javax.swing.JButton botonFiltrar;
    private javax.swing.JButton botonFiltrarRutas;
    private javax.swing.JButton botonGanancias;
    private javax.swing.JButton botonMejores;
    private javax.swing.JButton botonPaquetes;
    private javax.swing.JTextField cajaNit;
    private javax.swing.JComboBox<String> comboAnio;
    private javax.swing.JComboBox<String> comboAnio1;
    private javax.swing.JComboBox<String> comboDia;
    private javax.swing.JComboBox<String> comboDia1;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboMes;
    private javax.swing.JComboBox<String> comboMes1;
    private javax.swing.JLabel labelAnio;
    private javax.swing.JLabel labelAnio1;
    private javax.swing.JLabel labelDia;
    private javax.swing.JLabel labelDia1;
    private javax.swing.JLabel labelMayor;
    private javax.swing.JLabel labelMenor;
    private javax.swing.JLabel labelMes;
    private javax.swing.JLabel labelMes1;
    private javax.swing.JLabel labelNit;
    private javax.swing.JLabel labelPaquete;
    private javax.swing.JLabel lavelintervalo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelClientes;
    private javax.swing.JPanel panelGanancias1;
    private javax.swing.JPanel panelGanancias2;
    private javax.swing.JPanel panelPa;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tabla1;
    private javax.swing.JLabel tetoLabel;
    // End of variables declaration//GEN-END:variables
}

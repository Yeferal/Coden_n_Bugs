
package Codenbugs;

import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.ParseConversionEvent;

public class Ventana_Registro_Paquetes extends javax.swing.JInternalFrame {
    
    Ventana_Recepcionista vRecep;
    String clientes = "SELECT * FROM cliente;";
    int nitActual;
    int idActualClinete;
    boolean clienteExiste;
    Factura html;
    double total;
    double totalAcumulado;
    DefaultTableModel modelo1 = new DefaultTableModel();
    
    public Ventana_Registro_Paquetes(Ventana_Recepcionista vRecep) {
        initComponents();
        this.vRecep = vRecep;
        modelo1.addColumn("Destino");
        modelo1.addColumn("Peso");
        modelo1.addColumn("Priorizacion");
        modelo1.addColumn("Cuota");
        modelo1.addColumn("Acumulado");
        tablaPaquetes.setModel(modelo1);
    }

    private void listarDestino(){
        comboDesstinos.removeAllItems();
        try {
            vRecep.marco.vLogin.conect.stmt = vRecep.marco.vLogin.conect.conexion.createStatement();
            vRecep.marco.vLogin.conect.res = vRecep.marco.vLogin.conect.stmt.executeQuery("SELECT * FROM destinos;");
            while (vRecep.marco.vLogin.conect.res.next()) {
                comboDesstinos.addItem(vRecep.marco.vLogin.conect.res.getString(1));
            }   
        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
    }
    private boolean verificarTamanio(String nitS){
        
        if(nitS.length()==8 || nitS.length()<=3){
            return true;
        }
        return false;
    }
    
    public boolean buscarNit( int nitEx){
        try {
            vRecep.marco.vLogin.conect.stmt = vRecep.marco.vLogin.conect.conexion.createStatement();
            vRecep.marco.vLogin.conect.res = vRecep.marco.vLogin.conect.stmt.executeQuery(clientes);
            
            while (vRecep.marco.vLogin.conect.res.next()) {
                int nitver = Integer.parseInt(vRecep.marco.vLogin.conect.res.getString(2));
                if(nitver==nitEx){
                    labelExistencia.setText("El cliente ya existe, si necesita actualizar su direccion click en actualizar");
                    botonCrearMidificar.setVisible(true);
                    botonCrearMidificar.setText("Actualizar");
                    
                    textoNombre.setText(vRecep.marco.vLogin.conect.res.getString(3));
                    textoDireccion.setText(vRecep.marco.vLogin.conect.res.getString(4));
                    idActualClinete = Integer.parseInt(vRecep.marco.vLogin.conect.res.getString(1));
                    System.out.println(nitActual);
                    clienteExiste = true;
                    return true;
                }                
            }
        } catch (SQLException ex) {
            System.out.println("No existesss");
            ex.getMessage();
            ex.printStackTrace();
        }
        return false;
    }
    
    public void registrar(){
        double peso = Double.parseDouble(textoPeso.getText());
        int nitC;
        if(textotnit.getText().equalsIgnoreCase("c/f")){
            nitC = 0;
        }else{
            nitC = Integer.parseInt(textotnit.getText());
        }
        int priorizdo = comboPriori.getSelectedIndex();
        String destinoS = comboDesstinos.getSelectedItem().toString();
        double couta = Double.parseDouble(textoCuota.getText());
        double total= Double.parseDouble(textototal.getText());
        
        vRecep.marco.vLogin.conect.registroC.crearPaquete(peso, nitC, priorizdo, total, destinoS, couta);
        agregarFila();
    }
    
    private void agregarFila(){
        String datos[]= new String[5];
        
        datos[0] = comboDesstinos.getSelectedItem().toString();
        datos[1] = textoPeso.getText();
        if(comboPriori.getSelectedIndex()==0){
            datos[2] = "0";
        }else{
            datos[2] = Integer.toString((int) vRecep.marco.vLogin.conect.pcConeC.priorizacionP);
        }
        datos[3] = Integer.toString((int) vRecep.marco.vLogin.conect.pcConeC.cuotaDestino);
        datos[4] = textototal.getText();
        
        modelo1.addRow(datos);
        
    }
    private void generarFactura(String Nombre){
        Date fecha = new Date();
        String fechaS = fecha.getYear()+""+fecha.getMonth()+""+fecha.getDay()+""+fecha.getHours()+""+fecha.getMinutes();
        File factura = new File("Facturas/"+Nombre+" "+fechaS+".html");
        html =  new Factura();
        try {
            FileWriter jugadores= new FileWriter(factura);
            BufferedWriter bw = new BufferedWriter(jugadores);
            html.generarEncabezado(textoNombre.getText(), Integer.parseInt(textotnit.getText()), textoDireccion.getText());
            html.pestania();
            bw.write(html.salida);
            bw.newLine();
            
            for (int i = 0; i < tablaPaquetes.getRowCount(); i++) {
                html.generarFilaHTML(tablaPaquetes.getValueAt(i, 0).toString(),Double.parseDouble((String) tablaPaquetes.getValueAt(i, 1)), Double.parseDouble((String) tablaPaquetes.getValueAt(i, 2)), Double.parseDouble((String) tablaPaquetes.getValueAt(i, 3)),Double.parseDouble((String) tablaPaquetes.getValueAt(i, 4)));
                bw.write(html.filaText);
                bw.newLine();
            }
            html.generarcola(totalAcumulado);
            bw.write(html.cola);
            bw.newLine();
            bw.close();
            jugadores.close();
            
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }

    }
    private void verificarNt(){
        if(verificarTamanio(cajaNIT.getText())){
            
            if(cajaNIT.getText().equalsIgnoreCase("c/f")){
                nitActual = 0;
                textoNombre.setText("Consuminidor final");
                textoDireccion.setText(" ----- ");
                textotnit.setText("0");
            }else{
                textotnit.setText(cajaNIT.getText());
                nitActual = Integer.parseInt(cajaNIT.getText());
                if(buscarNit(Integer.parseInt(cajaNIT.getText()))){

                }else{
                    botonCrearMidificar.setText("Crear");
                    labelExistencia.setText("El cliente NO existe, ingrese sus dato y Cree al cliente");
                    clienteExiste = false;
                }
            }

        }else{
            JOptionPane.showMessageDialog(null, "El NIT debe de tener Ocho numeros");
        }
    }
    private void totales(){
        if(!textoPeso.getText().equals("")){
            total = (Integer.parseInt(textoPeso.getText()))*(vRecep.marco.vLogin.conect.pcConeC.precioLibra);
        
        if(comboPriori.getSelectedIndex()==0){
            total = total + (vRecep.marco.vLogin.conect.pcConeC.cuotaDestino);
        }else{
            total = total + (vRecep.marco.vLogin.conect.pcConeC.cuotaDestino)+ (vRecep.marco.vLogin.conect.pcConeC.priorizacionP);
        }
        textototal.setText(Double.toString(total));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRegistrar = new javax.swing.JPanel();
        labelNit = new javax.swing.JLabel();
        cajaNIT = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();
        labelNombre = new javax.swing.JLabel();
        labelDirreccion = new javax.swing.JLabel();
        textoNombre = new javax.swing.JTextField();
        textoDireccion = new javax.swing.JTextField();
        labelExistencia = new javax.swing.JLabel();
        botonCrearMidificar = new javax.swing.JButton();
        scrollTalbapQ = new javax.swing.JScrollPane();
        tablaPaquetes = new javax.swing.JTable();
        labelPeso = new javax.swing.JLabel();
        textoPeso = new javax.swing.JTextField();
        labellibrs = new javax.swing.JLabel();
        labelNItcli = new javax.swing.JLabel();
        textotnit = new javax.swing.JTextField();
        labelProri = new javax.swing.JLabel();
        comboPriori = new javax.swing.JComboBox<>();
        lableTarifa = new javax.swing.JLabel();
        textoTaria = new javax.swing.JTextField();
        labelDestino = new javax.swing.JLabel();
        comboDesstinos = new javax.swing.JComboBox<>();
        labelCuata = new javax.swing.JLabel();
        textoCuota = new javax.swing.JTextField();
        labelTotal = new javax.swing.JLabel();
        textototal = new javax.swing.JTextField();
        botonConfirmar = new javax.swing.JButton();
        botonFinalizar = new javax.swing.JButton();
        txtAcumulado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelNit.setText("NIT:");

        cajaNIT.setBackground(new java.awt.Color(153, 255, 204));
        cajaNIT.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cajaNIT.setForeground(new java.awt.Color(204, 0, 0));
        cajaNIT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cajaNITKeyTyped(evt);
            }
        });

        botonBuscar.setText("Buscar");
        botonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonBuscarMouseClicked(evt);
            }
        });

        labelNombre.setText("Nombre: ");

        labelDirreccion.setText("Direccion: ");

        labelExistencia.setForeground(new java.awt.Color(255, 51, 51));

        botonCrearMidificar.setText("Crear Usuario");
        botonCrearMidificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonCrearMidificarMouseClicked(evt);
            }
        });

        tablaPaquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        scrollTalbapQ.setViewportView(tablaPaquetes);

        labelPeso.setText("Peso: ");

        textoPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoPesoKeyTyped(evt);
            }
        });

        labellibrs.setText("lbs");

        labelNItcli.setText("NIT Cliente:");

        textotnit.setEditable(false);

        labelProri.setText("Priorizar: ");

        comboPriori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "SI" }));

        lableTarifa.setText("Tarifa: ");

        labelDestino.setText("Destino: ");

        comboDesstinos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDesstinosActionPerformed(evt);
            }
        });

        labelCuata.setText("Cuota:");

        textoCuota.setEditable(false);

        labelTotal.setText("Total =");

        textototal.setEditable(false);

        botonConfirmar.setText("Confirmar Paquete");
        botonConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonConfirmarMouseClicked(evt);
            }
        });

        botonFinalizar.setText("Finalizar Registro");
        botonFinalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonFinalizarMouseClicked(evt);
            }
        });

        jLabel1.setText("Factura");

        javax.swing.GroupLayout panelRegistrarLayout = new javax.swing.GroupLayout(panelRegistrar);
        panelRegistrar.setLayout(panelRegistrarLayout);
        panelRegistrarLayout.setHorizontalGroup(
            panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrarLayout.createSequentialGroup()
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelRegistrarLayout.createSequentialGroup()
                            .addGap(111, 111, 111)
                            .addComponent(txtAcumulado, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonFinalizar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegistrarLayout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(labelNombre)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(labelDirreccion)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(botonCrearMidificar))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegistrarLayout.createSequentialGroup()
                            .addGap(329, 329, 329)
                            .addComponent(labelNit)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cajaNIT, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegistrarLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(scrollTalbapQ, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelRegistrarLayout.createSequentialGroup()
                                    .addGap(69, 69, 69)
                                    .addComponent(botonConfirmar))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistrarLayout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panelRegistrarLayout.createSequentialGroup()
                                            .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(labelProri)
                                                .addComponent(labelPeso)
                                                .addComponent(labelNItcli)
                                                .addComponent(labelDestino)
                                                .addComponent(labelCuata))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistrarLayout.createSequentialGroup()
                                                    .addComponent(textoPeso, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(labellibrs))
                                                .addComponent(textotnit)
                                                .addComponent(comboPriori, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(comboDesstinos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(textoCuota)))
                                        .addGroup(panelRegistrarLayout.createSequentialGroup()
                                            .addGap(21, 21, 21)
                                            .addComponent(labelTotal)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(textototal)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textoTaria, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistrarLayout.createSequentialGroup()
                                            .addComponent(lableTarifa)
                                            .addGap(55, 55, 55)))
                                    .addGap(8, 8, 8))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegistrarLayout.createSequentialGroup()
                            .addGap(180, 180, 180)
                            .addComponent(jLabel1)))
                    .addGroup(panelRegistrarLayout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(labelExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        panelRegistrarLayout.setVerticalGroup(
            panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistrarLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRegistrarLayout.createSequentialGroup()
                        .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cajaNIT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonBuscar))
                        .addGap(18, 18, 18)
                        .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelDirreccion, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelNombre))
                            .addComponent(textoNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonCrearMidificar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistrarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textoTaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lableTarifa)
                        .addGap(178, 178, 178)
                        .addComponent(botonFinalizar)
                        .addGap(51, 51, 51))
                    .addGroup(panelRegistrarLayout.createSequentialGroup()
                        .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRegistrarLayout.createSequentialGroup()
                                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelPeso)
                                    .addComponent(textoPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labellibrs))
                                .addGap(18, 18, 18)
                                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelNItcli)
                                    .addComponent(textotnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelProri)
                                    .addComponent(comboPriori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(comboDesstinos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDestino))
                                .addGap(18, 18, 18)
                                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCuata))
                                .addGap(109, 109, 109)
                                .addGroup(panelRegistrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelTotal)
                                    .addComponent(textototal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addComponent(botonConfirmar))
                            .addComponent(scrollTalbapQ, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(txtAcumulado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(74, Short.MAX_VALUE))))
        );

        getContentPane().add(panelRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 14, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cajaNITKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cajaNITKeyTyped
        char c = evt.getKeyChar();

        if(c==KeyEvent.VK_ENTER){

            botonBuscar.doClick();
        }
        //if(c<'0' || c>'9') evt.consume();
        if (cajaNIT.getText().length()== 8) {

            evt.consume();
        }

    }//GEN-LAST:event_cajaNITKeyTyped

    private void botonBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonBuscarMouseClicked
        // TODO add your handling code here:
        listarDestino();
        vRecep.marco.vLogin.conect.pcConeC.actualizarPrecio();
        verificarNt();
    }//GEN-LAST:event_botonBuscarMouseClicked

    private void botonCrearMidificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonCrearMidificarMouseClicked
        if(!textoNombre.getText().equals("") && !textoDireccion.getText().equals("")){
            if(clienteExiste){
                vRecep.marco.vLogin.conect.actualzarCliente(nitActual, textoNombre.getText(), textoDireccion.getText());
            }else{
                vRecep.marco.vLogin.conect.crearNuevoCliente(nitActual, textoNombre.getText(), textoDireccion.getText());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe de llenar todo los campos");
        }

    }//GEN-LAST:event_botonCrearMidificarMouseClicked

    private void textoPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoPesoKeyTyped

        char c = evt.getKeyChar();
        //if(c!='.' || c!='1' || c!='1') evt.consume();
    }//GEN-LAST:event_textoPesoKeyTyped

    private void botonConfirmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonConfirmarMouseClicked
        totalAcumulado = totalAcumulado+total;
        txtAcumulado.setText("TOTAL FINAL: "+totalAcumulado);
        registrar();
        textoPeso.setText("");
        textoCuota.setText("");
        textototal.setText("");
    }//GEN-LAST:event_botonConfirmarMouseClicked

    private void botonFinalizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonFinalizarMouseClicked
        generarFactura(textoNombre.getText());
        textoPeso.setText("");
        textoCuota.setText("");
        textototal.setText("");
        cajaNIT.setText("");
        tablaPaquetes.clearSelection();
        for (int i = 0;i<tablaPaquetes.countComponents(); i++) {
            modelo1.removeRow(i);
        }
    }//GEN-LAST:event_botonFinalizarMouseClicked

    private void comboDesstinosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDesstinosActionPerformed
        if(!textoPeso.getText().equals("")){
            String cou = Double.toString(vRecep.marco.vLogin.conect.pcConeC.actualizarCuota((String) comboDesstinos.getSelectedItem()));
            textoCuota.setText(cou);
            totales();
        }
    }//GEN-LAST:event_comboDesstinosActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonConfirmar;
    private javax.swing.JButton botonCrearMidificar;
    private javax.swing.JButton botonFinalizar;
    private javax.swing.JTextField cajaNIT;
    private javax.swing.JComboBox<String> comboDesstinos;
    private javax.swing.JComboBox<String> comboPriori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelCuata;
    private javax.swing.JLabel labelDestino;
    private javax.swing.JLabel labelDirreccion;
    private javax.swing.JLabel labelExistencia;
    private javax.swing.JLabel labelNItcli;
    private javax.swing.JLabel labelNit;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelPeso;
    private javax.swing.JLabel labelProri;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labellibrs;
    private javax.swing.JLabel lableTarifa;
    private javax.swing.JPanel panelRegistrar;
    private javax.swing.JScrollPane scrollTalbapQ;
    private javax.swing.JTable tablaPaquetes;
    private javax.swing.JTextField textoCuota;
    private javax.swing.JTextField textoDireccion;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextField textoPeso;
    private javax.swing.JTextField textoTaria;
    private javax.swing.JTextField textotnit;
    private javax.swing.JTextField textototal;
    private javax.swing.JLabel txtAcumulado;
    // End of variables declaration//GEN-END:variables
}

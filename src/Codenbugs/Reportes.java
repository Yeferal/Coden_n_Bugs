
package Codenbugs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

public class Reportes {
    private JTable tabla1;
    private String encabezado;
    private String cierre;
    private String pestania;
    private String datoS;
    private String ceAb;

    public Reportes (JTable tabla1){
        this.tabla1 = tabla1;
    }
    /**genera el encabezado del html**/
    private void generarEncabezado(String titulo){
        encabezado = "<html lang=\"en\" dir=\"ltr\">\n";
        encabezado+= "<head>\n" +
                     "<meta charset=\"utf-8\">\n" +
                     "<title>Reporte</title>\n" +
                     "<link rel=\"stylesheet\" href=\"estilo.css\">\n" +
                     "</head>\n" +
                     "<body>\n" +
                     "<header align:\"center\">\n" +
                     "<h1>Reporte de: "+titulo+"</h1>\n" +
                     "</header>\n" +
                     "<section>\n" +
                     "<article>\n" +
                     "<table>\n"+
                     "<tr>\n";
    }
    /**escribe una columna**/
    private void generarColumnas(String columna){
        pestania += "<th>"+columna+"</th>\n";
    }
    /**cierra la culuma**/
    private void cerrarAbrir(){
        ceAb = "</tr>\n" ;
    }
    /**escribe un celda de una fila**/
    private void generarFila(String dato){
        datoS += "<td>"+dato+"</td>\n";
    }
    /**cierra el html**/
    private void generarCierre(){
        cierre ="\n" +
                "</table>\n" +
                "</article>\n" +
                "</section>\n" +
                "</body>\n" +
                "</html>";
    }
    /**escribe toda las columnas correspondites a la tabla**/
    private void escribirColumnas(){
        pestania="";
        for (int i = 0; i < tabla1.getColumnCount(); i++) {
            generarColumnas(tabla1.getColumnName(i));
        }
    }
    /**escribe los registros completos dependiendo de lo que la tabla contenga**/
    private void escribirRegistros(){
        datoS="";
        for (int i = 0; i < tabla1.getRowCount(); i++) {
            datoS+="<tr>\n";
            for (int j = 0; j < tabla1.getColumnCount(); j++) {
                if(tabla1.getValueAt(i, j)==null){
                    generarFila("           ");
                }else{
                    generarFila(tabla1.getValueAt(i, j).toString());
                }
            }
            datoS+="</tr>\n";
        }
    }
    /**genera el archivo binario con la extension html**/
    private void crearBinario(String titulo, String tipo,String fecha1, String fecha2,String estados){

        File file = new File("Reportes/Reporte "+tipo+" "+estados+""+fecha1+".html");
        try {
            FileWriter html= new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(html);
            generarEncabezado(titulo);
            bw.write(encabezado);
            bw.newLine();
            escribirColumnas();
            bw.write(pestania);
            bw.newLine();
            cerrarAbrir();
            bw.write(ceAb);
            bw.newLine();
            escribirRegistros();
            bw.write(datoS);
            bw.newLine();
            generarCierre();
            bw.write(cierre);
            bw.newLine();
            bw.close();
            html.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    /**determina los datos que estaran en el nombre y titilo del html**/
    public void titularTabla(String tipo,String fecha1, String fecha2,String estados){
        switch(tipo){
            case "Paquetes":
                crearBinario("Reporte: Paquetes "+getEstado(estados), tipo, fecha1, fecha2, getEstado(estados));
                break;
            case "Clientes":
                crearBinario("Reporte: Cliente "+getNit(tipo), tipo, fecha1, fecha2, getNit(estados));
                break;
            case "Rutas":
                crearBinario("Reporte: Rutas entre "+fecha1+" "+fecha2, tipo, fecha1, fecha2, "3 mejores");
                break;
            case "Ganacias":
                crearBinario("Reporte: Ganancias entre "+fecha1+" "+fecha2, tipo, fecha1, fecha2, "de Rutas");
                break;
                default:
        }
    }
    /**retorna el tipo de estado**/
    private String getEstado(String estados){
        if(estados.equals("Activos")){
            return "Activos";
        }else if(estados.equals("Inactivos")){
            return "Inactivos";
        }
        return "Todos";
    }
    /**retorna el nit**/
    private String getNit(String nit){
        if(nit.equals("")){
            return "Todos";
        }
        return nit;
    }
}
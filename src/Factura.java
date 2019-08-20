
public class Factura {
    
    String salida;
    String filaText;
    String cola;
    
    
    public Factura(){ 
        
    }
        public void generarEncabezado(String nombre, int nit, String direccion){
        salida = "<html>\n";
        salida+="<head><title>Factura</title></head>\n"+"<body>\n";
        salida+="<h1>Nombre: "+nombre+" NIT: "+nit+"</h1>\n";
        salida+="<h2>Direccion: "+direccion+"</h2>\n"+"<table>\n";
        salida+="<tr>\n";
    }

    public void pestania(){
        salida+="<td><strong>Destino</strong></td>\n";
        salida+="<td><strong>Peso</strong></td>\n";
        salida+="<td><strong>Priorizacion</strong></td>\n";
        salida+="<td><strong>Cuota de Destino</strong></td>\n";
        salida+="<td><strong>Precio</strong></td>\n";
        salida+="</tr>"; 
    }

    public void generarFilaHTML(String p1,double p2,double p3,double p4, double p5){
        filaText="";
        filaText="\n<tr>\n";
        filaText+="<td>"+p1+"</td>\n";
        filaText+="<td>"+p2+"</td>\n";
        filaText+="<td>"+p3+"</td>\n";
        filaText+="<td>"+p4+"</td>\n";
        filaText+="<td>"+p5+"</td>\n";
        filaText+="</tr>\n";
    }

    public void generarcola(double total){
        cola="\n</table>\n";
        cola="<div>                Total"+total+"</div>";
        cola+="</body>\n";
        cola+="</html>\n";
    }

}

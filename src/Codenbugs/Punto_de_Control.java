
public class Punto_de_Control {
    
    private int idPC;
    private String Nombre;
    private int estado;
    private double tarifa;
    private int capacidad;
    private int operadorAsig;
    
    
    public Punto_de_Control(){
        
    }
    
    public void setDatos(String Nombre, double tarifa, int capacidad, int operadorAsig){
        this.Nombre = Nombre;
        this.tarifa = tarifa;
        this.capacidad = capacidad;
        this.operadorAsig = operadorAsig;
    }
    
    public void setIdPC(int idPC){
        this.idPC = idPC;
    }
    public void setNombre(String Nombre){
        this.Nombre = Nombre;
    }
    public void setEstado(int estado){
        this.estado =  estado;
    }
    public void setTarifa(double tarifa){
        this.tarifa = tarifa;
    }
    public void setCapacidad(int capacidad){
        this.capacidad = capacidad;
    }
    public void setOperadroAsig(int operadorAsig){
        this.operadorAsig = operadorAsig;
    }
    
    public int getIdPC(){
        return idPC;
    }
    
    public String getNombre(){
        return Nombre;
    }
    public int getEstado(){
        return estado;
    }
    public double getTarifa(){
        return tarifa;
    }
    public int getCapacidad(){
        return capacidad;
    }
    public int getOperadorAsig(){
        return operadorAsig;
    }
}

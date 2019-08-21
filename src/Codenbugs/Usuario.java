

public class Usuario {
    
    private String Nombre, Codigo, Pass;
    private int id,tipo;
    
    public Usuario (){
        
    }
    
    public void setDatos(String  Nombre, String Codigo, int tipo ,String Pass){
        this.Nombre=Nombre;
        this.Codigo=Codigo;
        this.tipo=tipo;
        this.Pass=Pass;
        
    }
    
    public String getNombre(){
        return Nombre;
    }
    public String getCodigo(){
        return Codigo;
    }
    public int gettipo(){
        return tipo;
    }
    public String getPassword(){
        return Pass;
    }
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id=id;
    }
    public void setNombre(String  Nombre){
        this.Nombre=Nombre;
    }
    public void setCodigo( String Codigo){
        this.Codigo=Codigo;
    }
    public void settipo( int tipo){
        this.tipo=tipo;
    }
    public void setPassword(String Pass){
        this.Pass=Pass;
    }
    
}

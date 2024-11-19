public class Reserva {
    private int dni;
    private String nombre;
    private String apellido;
    private String codTicket;
    private String ubicacion;

    public Reserva(int d, String nom, String ape, String codT, String ubic){
        dni = d;
        nombre = nom;
        apellido = ape;
        codTicket = codT;
        ubicacion = ubic;
    }

    public int obtenerDni(){
        return dni;
    }

    public String obtenerNombre(){
        return nombre;
    }

    public String obtenerApellido(){
        return apellido;
    }

    public String obtenerCodTicket(){
        return codTicket;
    }

    public String obtenerUbicacion(){
        return ubicacion;
    }
}

public class Logica{
    private Vuelo vuelo;
    
    //Constructor
    public Logica(){
        vuelo = new Vuelo(15,5);
    }

    public Vuelo obtenerVuelo(){
        return vuelo;
    }
}

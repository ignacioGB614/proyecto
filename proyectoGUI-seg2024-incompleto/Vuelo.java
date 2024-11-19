public class Vuelo {
    private Reserva[][] reservas;
 
    
    public Vuelo(int maxFil, int maxCol){
        reservas = new Reserva[maxFil][maxCol];
   
    }

    public void reservar(int f, int c, Reserva r){
        reservas[f][c] = r;
    }

    public Reserva consultarReserva(int f, int c){
        return reservas[f][c];
    }

    public Reserva hayReservaConDni(int dni){
        Reserva r = null;
        boolean encontre = false;
        for(int f = 0; f<reservas.length && !encontre; f++){
            for(int c = 0; c<reservas[0].length && !encontre; c++){
                if(reservas[f][c]!=null && reservas[f][c].obtenerDni()==dni){
                    r = reservas[f][c];
                    encontre=true;
                }
            }
        }
        return r;
    }

    public Reserva cancelarReservaConDni(int dni){
        boolean pude = false;
        Reserva r = null;
        for(int f = 0; f<reservas.length && !pude; f++){
            for(int c = 0; c<reservas[0].length && !pude; c++){
                if(reservas[f][c]!=null && reservas[f][c].obtenerDni()==dni){
                    r = reservas[f][c];
                    reservas[f][c] = null;
                    pude = true;
                }
            }
        }
        return r;
    }
}

package Ejercicio2_2;

public class Consumidor implements Runnable { // Asegúrate de que implemente Runnable
    private Almacen almacen;


    public Consumidor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            // Consumir hasta que se terminen los números
            while(true) { // Ajusta este límite según tus necesidades
                almacen.consumir(); // Llama al metodo consumir

                Thread.sleep(2000); // Simula el tiempo de consumo
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

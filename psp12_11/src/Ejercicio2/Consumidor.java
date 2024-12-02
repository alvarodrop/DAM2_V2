package Ejercicio2;


public class Consumidor implements Runnable {
    private Almacen almacen;

    public Consumidor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        try {
            while (true) {
                almacen.consumir();
                Thread.sleep(1500); // Simula el tiempo de consumo
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
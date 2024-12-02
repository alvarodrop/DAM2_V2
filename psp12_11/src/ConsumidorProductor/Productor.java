package ConsumidorProductor;

import java.util.Random;

public class Productor implements Runnable {
    private Almacen almacen;

    private int tamano = 30;
    private int idProductor;
    private Random random = new Random();

    public Productor(Almacen almacen, int idProductor) {
        this.almacen = almacen;
        this.idProductor = idProductor;
    }

    @Override
    public void run() {
        for (int numero = 1; numero <= tamano; numero++) {  // Generar números del 1 al 30
            try {
                int tiempoDeEspera = random.nextInt(1400);  // Espera aleatoria
                Thread.sleep(tiempoDeEspera);
                almacen.producir(numero, idProductor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
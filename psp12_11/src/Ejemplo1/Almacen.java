package Ejemplo1;

public class Almacen {
    private String coche;
    private boolean disponible = false;

    public synchronized void producir(String coche) throws
            InterruptedException {
        while (disponible) {
            wait();
        }
        this.coche = coche;
        System.out.println("Productorprodujo:" + coche);
        disponible = true;
        notify();
    }

    public synchronized void consumir() throws InterruptedException {
        while (!disponible) {
            wait();
        }
        System.out.println("Consumidorconsumió:" + coche);
        disponible = false;
        notify();

    }
}

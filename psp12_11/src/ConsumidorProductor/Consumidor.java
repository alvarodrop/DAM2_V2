package ConsumidorProductor;
import java.util.Random;


public class Consumidor implements Runnable { // Asegúrate de que implemente Runnable
    private Almacen almacen;
    private int idConsumidor;
    private Random random = new Random();

    public Consumidor(Almacen almacen, int idConsumidor) {
        this.almacen = almacen;
        this.idConsumidor = idConsumidor;
    }

    @Override
    public void run() {
        try {
            // Consumir hasta que se terminen los números
            while(true) {
                int tiempoDeEspera = random.nextInt(5000);
                Thread.sleep(tiempoDeEspera);
                almacen.consumir(idConsumidor); // Llama al metodo consumir

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

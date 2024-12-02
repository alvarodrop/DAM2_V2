package ConsumidorProductor2;


public class Fabrica {

    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        Thread hiloProductor = new Thread(new Productor(almacen));
        Thread hiloConsumidor = new Thread(new Consumidor(almacen));

        hiloProductor.start();
        hiloConsumidor.start();

        try {
            hiloProductor.join();
            hiloConsumidor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
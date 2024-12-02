package ConsumidorProductor;


import java.util.ArrayList;

public class Fabrica {
    public static void main(String[] args) {
        // Creamos el almacen con capacidad 10
        Almacen almacen = new Almacen(10);
        ArrayList<Thread> listaHilosProductor = new ArrayList<>();
        ArrayList<Thread> listaHilosConsumidor = new ArrayList<>();
        Integer numeroProductores = 5;
        Integer numeroConsumidores = 3;

        //Creamos los hilos de los productores
        for(int i = 1;i <= numeroProductores;i++){
            Thread hiloProductor = new Thread(new Productor(almacen, i));
            listaHilosProductor.add(hiloProductor);
        }


        // Creamos los hilos de los 3 consumidores
        for(int i = 1; i <= numeroConsumidores;i++){
            Thread hiloConsumidor = new Thread(new Consumidor(almacen, i));
            listaHilosConsumidor.add(hiloConsumidor);
        }

        // Iniciamos los hilos de los productores
        for(Thread hilo : listaHilosProductor){
           hilo.start();
        }


        // Iniciamos los hilos de los consumidores
        for(Thread hilo : listaHilosConsumidor){
            hilo.start();
        }


        try {
            for(Thread hilo : listaHilosProductor){
                hilo.join();
            }

            for(Thread hilo : listaHilosConsumidor){
                hilo.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package Ejemplo1;

public class Consumidor implements Runnable{
    private Almacen almacen;
    public Consumidor(Almacen almacen){
        this.almacen=almacen;
    }
    @Override
    public void run(){
        while(true){
            try{
                almacen.consumir();
                Thread.sleep(1500);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

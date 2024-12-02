package Ejemplo1;

public class Productor  implements Runnable{
    private Almacen almacen;
    public Productor(Almacen almacen){
        this.almacen=almacen;
    }
    @Override
    public void run(){
        String[]coches={"Toyota","Honda","Ford","BMW"};
        for(String coche:coches){
            try{
                almacen.producir(coche);
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

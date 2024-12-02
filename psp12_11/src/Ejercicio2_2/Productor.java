package Ejercicio2_2;


public class Productor  implements Runnable{
    private Almacen almacen;
    public Productor(Almacen almacen){
        this.almacen=almacen;
    }
    @Override
    public void run(){
        Integer[] numeros={1,2,3,4,5,6,7,8,9};
        for(Integer numero : numeros){
            try{
                almacen.producir(numero);
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}


package sample;

/**
 * Created by edd on 24/11/2017.
 */
public class Nodo {
    private int valor;
    private Nodo proximo;


    public Nodo() {
        this.valor = 0;
        this.proximo=null;

    }
    //Constructor
    public Nodo
    (int valor){
        this.valor=valor;
        this.proximo=null;
    }
    public void setValor(int valor){
        this.valor = valor;
    }
    public void setProximo(Nodo siguiente)  {
        this.proximo = siguiente;
    }
    public int getValor()
    {
        return this.valor;
    }
    public Nodo getProximo()
    {
        return this.proximo;
    }
}


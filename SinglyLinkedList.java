/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singlylinkedlist;

/**
 * <h1>SinglyLinkedList</h1>
 * Es un programa que permite visualizar una lista enlazada en consola.
 * <p>
 * Se crea una lista simple enlazada (ListaEnlazada) 
 * @see ListaEnlazada 
 * Y se hacen uso del comportamiento de esa clase en conjunto con la creación de Nodo
 * @see Nodo
 * 
 * @author NarcisaC
 * @version 1.0
 * @since 2023 
 */
public class SinglyLinkedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Nodo n1 = new Nodo(1);
        ListaEnlazada le = new ListaEnlazada(n1);
        Nodo n2 = new Nodo(2);
        Nodo n3 = new Nodo(3);
        le.insertarNodoAlFinal(n3);
        le.insertarNodoAlFinal(n2);
        
        le.borrarNodoPorValor(3);
        System.out.println(le.toString());
        
        le.convertirAListaEnlazadaCircularSimple();
        System.out.println("ciclo: " + le.isCycle());
        System.out.println(le.toString());
        
        le.borrarNodoPorValor(2);
        System.out.println("ciclo: " + le.isCycle());
        System.out.println(le.toString());
    }
    
}

/**
 * <h1>Nodo</h1>
 * Nodo es una clase que permite crear nodos.
 * <p>
 * Los nodos creados en esta clase pueden ser agregados en la lista simple enlazada (ListaEnlazada).
 */
class Nodo {
 
    int valor;
    Nodo siguiente;
    Nodo() {}
    Nodo(int valor) { this.valor = valor; }
    Nodo(int valor, Nodo siguiente) { this.valor = valor; this.siguiente = siguiente;  }        
}

/**
 * <h1>ListaEnlaza</h1>
 * Es un programa que permite visualizar una lista enlazada en consola.
 * <p>
 * Existen métodos que permiten crear un a lista simple enlazada (ListaEnlazada), agregar un nodo, 
 * buscar en la lista enlazada por nodo o por valor, eliminar un nodo, ver existen ciclos, entre otras.
 */
class ListaEnlazada {
    
    protected Nodo cabeza;
    protected Nodo cola;
    public int longitud;
    
    public ListaEnlazada(Nodo primerNodo) {
        this.cabeza = primerNodo;
        this.cola = primerNodo;
        this.longitud++;
    }
    
    public void insertarNodoAlFinal(Nodo nuevoNodo) {
        cola.siguiente = nuevoNodo;
        cola = cola.siguiente;
        this.longitud++;
    }
    
    public Nodo buscarNodo(int valorABuscar) {
        Nodo nodoActual = this.cabeza;
        
        while (nodoActual != null) {
            if (nodoActual.valor == valorABuscar) {
                return nodoActual;
            }
            nodoActual = nodoActual.siguiente;
        }
        return null;
    }
    
    public void borrarNodoPorValor(int valor) {
        
        if (this.cabeza == null) { // Si la lista enlazada está vacía
            System.out.println("El nodo no pudo ser borrado porque la ListaEnlazada está vacía.");
        } else {
            Nodo nodoABorrar = buscarNodo(valor);

            if (nodoABorrar != null) {
                if ((this.cabeza.equals(cola))) { // Si la lista enlazada tiene solo 1 nodo
                    this.cabeza = null;
                    System.out.println("ListaEnlazada ahora está vacía.");    
                } else if (nodoABorrar == this.cabeza) {
                    Nodo nuevo = this.cabeza.siguiente;
                    nodoABorrar.siguiente = null;
                    this.cabeza = nuevo;
                    this.cabeza.siguiente = nuevo.siguiente;                
                } else {   // Cualquier nodo después de cabeza, incluido cola

                    Nodo nodoAnterior = null;
                    Nodo nodoActual = this.cabeza;
                    
                    while(nodoActual.siguiente != null) {
                        nodoAnterior = nodoActual;
                        nodoActual = nodoActual.siguiente;
                        if ((nodoActual == nodoABorrar)) {
                            break;
                        }
                    }                       
                        
                    if (nodoABorrar != this.cola) {
                        nodoAnterior.siguiente = nodoActual.siguiente;
                        nodoActual.siguiente = null;
                        nodoActual = null;
                    } else {                    // Si nodoABorrar es igual a cola
                        this.cola = nodoAnterior;
                        this.cola.siguiente = null;
                    }
                    this.longitud--;
                } 
            } else {
                System.out.println("Nodo no existente.");
            }
        }    
    }
    
    public void reversarListaEnlazada() { 
        
        Nodo colaInicial = this.cola;          //Guardamos el Nodo cola inicial
        Nodo cabezaInicial = this.cabeza;      //Guardamos el Nodo cabeza inicial
        Nodo actual = this.cabeza;
        Nodo aux = null;
        
        while (actual.siguiente != null) {
            if (actual.equals(colaInicial)) {    
                /*
                 * Si el nodo actual es igual al Nodo colaInicial,
                 * cambiamos por primera vez el puntero del nodo cola,
                 * y se lo asignamos al Nodo cabezaInicial.
                 */
                this.cola = cabezaInicial;
                break;
            } 
            actual = actual.siguiente;
            aux = this.cola.siguiente;
            this.cola.siguiente = this.cabeza;
            this.cabeza.siguiente = aux;
            this.cabeza = actual;
        }
    }
    
    public void reverseLinkedList() { // SOLUCIÓN DE A PROFE
        //anterior y actual (inicia en el nodo cabeza) serían como los punteros que se usaban en el patrón "Dos Apuntadores"
        Nodo anterior = null;
        Nodo temp = null;
        Nodo cabezaInicial = this.cabeza;
        Nodo colaInicial = this.cola;
        
        while (this.cabeza != null) {
            temp = this.cabeza.siguiente; // Guardamos el Nodo cabeza.siguiente 
            this.cabeza.siguiente = anterior;	
            
            if ((this.cabeza.equals(colaInicial))) {
                /*
                 * Cuando llegamos al útlimo Nodo de la lista, que ahora es cabeza,
                 * al Nodo cola le asignamos el Nodo cabezaInicial, es decir
                 * al Nodo cola le asignamos el Nodo con el cual se inició el recorrido del while.
                 */
                this.cola = cabezaInicial;
                break;
            }
            
            anterior = this.cabeza;
            this.cabeza = temp;
        } 
    }

    public void oddEvenLinkedList(Nodo cabeza) {
        if (cabeza == null || (cabeza == this.cola)) { // Si la lista es nula o tiene sólo 1 Nodo
            return;
        }
        Nodo anterior = cabeza;
        Nodo actual = cabeza.siguiente;
        Nodo colaOriginal = this.cola;
        Nodo aux;
        while (actual.siguiente != null) { 
            aux = actual.siguiente;
            anterior.siguiente = actual.siguiente;
            this.cola.siguiente = actual;
            actual.siguiente = null;
            this.cola = actual;
            
            if (this.cola.equals(colaOriginal)) { // si la lista enlazada es de longitud par
                /*
                 * Rompe el ciclo justo después de colocar 
                 * como cola el Nodo colaOriginal
                 */
                break;
            }
            
            anterior = aux;
            actual = aux.siguiente;
            
            if (anterior.equals(colaOriginal)) { // si la lista enlazada es de longitud impar
                /*
                * Rompe el ciclo cuando llegamos al Nodo colaOriginal
                */                
                break;
            }            
        }  
    }
        
    public void convertirAListaEnlazadaCircularSimple() {
        if (this.cabeza != null) {
            this.cola.siguiente = this.cabeza;
        }
    }
    
    public boolean isCycle() {
        Nodo current = this.cabeza;
        for (int i = 0; i < this.longitud; i++) {
            if ((current.siguiente == null)) return false;
            current = current.siguiente;
        }
        
        return true;
    }
    
    public Nodo getTheIndex(int index) { // Índices empiezan en 1, porque longitud empieza en 1
        int counter = 1;
        Nodo currentNode = this.cabeza;
                
        if (index == 1) {
            return this.cabeza;
        }
        while(counter != index) {
            currentNode = currentNode.siguiente;
            counter++;
        }

        return currentNode;
    }    
    
    @Override
    public String toString() {
        String respuesta = "";
        if (this.cabeza == null) { //Si la lista es vacía 
            respuesta = "ListaEnlazada{}";
        } else if (this.cabeza != null && this.cabeza == this.cola) { // Si la lista tiene solo 1 Nodo
            respuesta = "ListaEnlazada{cabeza:" + this.cabeza.valor + ", cola: " + this.cola.valor + "}";
        } else {
            Nodo actual = this.cabeza;
            int cont = 1;
            while(actual.siguiente != null) {
                respuesta += actual.valor + " -> ";
                actual = actual.siguiente;
                if (actual == this.cabeza) { // Para que salga del ciclo si es una lista enlazada circular
                    cont++;
                    if (cont == 2) 
                        break;
                }
            }
            respuesta += actual.valor;
        }
        
        return respuesta;
    }
    
}

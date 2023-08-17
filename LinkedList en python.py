'''
Nodo y Lista_enlazada

Este archivo contiene las clases Nodo y Lista_enlazada, las cuales permiten:
 - Crear una lista enlazada simple (Lista_enlazada) con un nodo inicial creado desde la clase Nodo,
 - Agregar nodos a la Lista_enlazada,
 - Buscar en la lista por valor,
 - Eliminar nodos de la Lista_enlazada,
 - Comprobar si la Lista_enlazada tiene ciclos convirtiendo la lista enlazada simple a una lista circular simple, 

Autor: Narcisa Colcha
Fecha: 2023-08-17
Versión: 1.0
'''

# Creamos la clase Nodo
class Nodo:
    def __init__(self, data, siguiente = None):
        self.data = data
        self.siguiente = siguiente

# Creamos la clase Lista_enlazada
class Lista_enlazada: 
    def __init__(self, nodo: Nodo):
        self.cabeza = nodo
        self.cola = nodo      
        self.longitud = 1

    # Método para agregar elementos al final de la Lista_enlazada
    def insertar_nodo_al_final(self, nuevo_nodo: Nodo):
        self.cola.siguiente = nuevo_nodo
        self.cola = self.cola.siguiente
        self.longitud +=1

    # Método para agregar elementos en al inicio de la Lista_enlazada
    def insertar_nodo_al_inicio(self, nuevo_nodo: Nodo):
        nuevo_nodo.siguiente = self.cabeza
        self.cabeza = nuevo_nodo
        self.longitud +=1
    
    # Método para buscar un nodo a partir de un valor 
    def buscar_nodo_por_valor(self, valor_a_buscar):
        nodo_actual = self.cabeza
        while(nodo_actual is not None):
            if (nodo_actual.data == valor_a_buscar):
                return nodo_actual
            nodo_actual = nodo_actual.siguiente
        return None

    # Método para verificar si la Lista_enlazada esta vacia
    def es_vacio(self):
        return self.cabeza is None

    ''' Método para borrar un nodo, INCOMPLETO '''
    def borrar_nodo(self, nodo: Nodo):
        nodo.siguiente = None

    # Método para borrar un nodo, mejorado, método anterior (def borrar_nodo(self, nodo: Nodo):) mejorado
    def borrar_nodo_mejorado(self, nodo_a_borrar: Nodo):
        if (self.es_vacio()): # Si la Lista_enlazada está vacía       
            print('El nodo no pudo ser borrado porque la ListaEnlazada est vacía.', '\n')
        else:        
            nodo_a_borrar = self.buscar_nodo_por_valor(nodo_a_borrar.data)

            if (nodo_a_borrar is not None):
                if (self.cabeza == self.cola):
                    self.cabeza = None
                    print('ListaEnlazada ahora está vacía.', '\n')
                elif (nodo_a_borrar == self.cabeza):
                    nuevo = None
                    nuevo = self.cabeza.siguiente
                    self.cabeza = nuevo
                    self.cabeza.siguiente = nuevo.siguiente
                    del(nodo_a_borrar)
                else:   # Cualquier nodo después de cabeza, incluido cola
                    nodo_anterior = None
                    nodo_actual = self.cabeza

                    while (nodo_actual.siguiente is not None):
                        nodo_anterior = nodo_actual
                        nodo_actual = nodo_actual.siguiente
                        if (nodo_actual == nodo_a_borrar):
                            break
                    
                    if (nodo_a_borrar != self.cola):
                        nodo_anterior.siguiente = nodo_actual.siguiente
                    else:   # Si nodo_a_borrar es igual a cola
                        self.cola = nodo_anterior
                        self.cola.siguiente = None
                    self.longitud -=1    
                    del(nodo_actual)    
            else:
                print('Nodo no existente.', '\n')
            
    # Método para borrar un nodo a partir de un valor             
    def borrar_nodo_por_valor(self, valor):
        if (self.es_vacio()): # Si la Lista_enlazada está vacía       
            print('El nodo no pudo ser borrado porque la ListaEnlazada est vacía.', '\n')
        else:
            nodo_a_borrar = self.buscar_nodo_por_valor(valor)
            
            if (nodo_a_borrar is not None):                

                if (self.cabeza == self.cola):
                    self.cabeza = None
                    print('ListaEnlazada ahora está vacía.', '\n')
                elif (nodo_a_borrar == self.cabeza):
                    nuevo = None
                    nuevo = self.cabeza.siguiente
                    nodo_a_borrar.siguiente = None
                    self.cabeza = nuevo
                    self.cabeza.siguiente = nuevo.siguiente
                else:   # Cualquier nodo después de cabeza, incluido cola
                    nodo_anterior = None
                    nodo_actual = self.cabeza

                    while (nodo_actual.siguiente is not None):
                        nodo_anterior = nodo_actual
                        nodo_actual = nodo_actual.siguiente
                        if (nodo_actual == nodo_a_borrar):
                            break
                    
                    if (nodo_a_borrar != self.cola):
                        nodo_anterior.siguiente = nodo_actual.siguiente
                    else:   # Si nodo_a_borrar es igual a cola
                        self.cola = nodo_anterior
                        self.cola.siguiente = None    
                    self.longitud -=1
                    del(nodo_actual)
                        
            else:
                print('Nodo no existente.', '\n')

    # Método para eleminar nodo por valor, más sencillo
    def eliminar_nodo_por_valor(self, valor):
        if (self.es_vacio()): # Si la Lista_enlazada está vacía       
            print('El nodo no pudo ser borrado porque la ListaEnlazada est vacía.', '\n')
        else:
            if (self.buscar_nodo_por_valor(valor) is None):
                print('Nodo no existente.', '\n')
                return 
            nodo_actual = self.cabeza
            nodo_anterior = None
            while nodo_actual and nodo_actual.data != valor:
                nodo_anterior = nodo_actual
                nodo_actual = nodo_actual.siguiente
            if nodo_anterior is None:
                self.cabeza = nodo_actual.siguiente
                self.longitud -=1  
            elif nodo_actual:
                nodo_anterior.siguiente = nodo_actual.siguiente
                self.longitud -=1  
                del(nodo_actual)
    
    # Método para obtener el último nodo
    def obtener_ultimo_nodo(self):
        temp = self.cabeza
        while (temp.siguiente is not None):
            temp = temp.siguiente
        return temp.data

    #Método para convertir de Lista_enlazada simple a Lista_enlazada circular simple
    def convertir_a_lista_enlazada_circular_simple(self):
        self.cola.siguiente = self.cabeza

    # Método para saber si la Lista_enlazada tiene algún ciclo
    def tiene_ciclo(self):
        actual = self.cabeza

        for i in range (self.longitud):
            if (actual.siguiente is None):
                return False
            actual = actual.siguiente
        return True    

    # Método para imprimir la Lista_enlazada
    def imprimir_lista(self):
        respuesta = ''
        
        if (self.cabeza is None):  # Si la Lista_enlazada es vacía 
            respuesta = 'Lista enlazada{}' 
        elif ((self.cabeza is not None) and (self.cabeza == self.cola)):  # Si la Lista_enlazada tiene solo 1 Nodo
            respuesta = 'ListaEnlazada{cabeza: ' + str(self.cabeza.data) + ', cola: ' + str(self.cola.data) + '}'
        else:    
            actual = self.cabeza
            cont = 1
            while (actual.siguiente is not None):
                respuesta += str(actual.data) + ' -> '
                actual = actual.siguiente
                #Para poder imprimir la lista enlazada circular simple y evitar el ciclo infinito
                if (actual == self.cabeza):
                    cont += 1
                    if (cont == 2):
                        break
            respuesta +=  str(actual.data);
        print(respuesta)


nodo1 = Nodo(1)
nodo2 = Nodo(2)
nodo3 = Nodo(3)
nodo4 = Nodo(4)
nodo5 = Nodo(5)
le = Lista_enlazada(nodo1) # Instancia de la clase
le.imprimir_lista() # Imprimimos la lista de nodos
print('Tiene ciclo: ', le.tiene_ciclo())

le.insertar_nodo_al_final(nodo2)
le.insertar_nodo_al_final(nodo3) 
le.insertar_nodo_al_final(nodo4) 
le.insertar_nodo_al_final(nodo5) 

le.borrar_nodo_por_valor(2)
le.eliminar_nodo_por_valor(3)
le.borrar_nodo_mejorado(nodo4)
le.imprimir_lista()

le.convertir_a_lista_enlazada_circular_simple()
le.imprimir_lista() # Imprimimos la lista de nodos
print('Tiene ciclo: ', le.tiene_ciclo())

le.borrar_nodo_por_valor(5)
le.imprimir_lista() # Imprimimos la lista de nodos
print('Tiene ciclo: ', le.tiene_ciclo())

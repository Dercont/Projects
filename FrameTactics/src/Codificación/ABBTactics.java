package Codificación;
import java.util.LinkedList;
import javax.swing.JOptionPane;
/**
 *
 * @author Armando
 */
public class ABBTactics {
    
    private DatoTactics tmp;
    private DatoTactics padre;
    private DatoTactics pdre;
    private DatoTactics raiz;
    private int num_nodos;
    private int alt;
    
    public ABBTactics() {
        raiz = null;
        num_nodos = 0;
        alt = 0;
    }

 public void insertar(int dato, int i){
       if(existe(dato, i))return;
       DatoTactics nuevo = new DatoTactics(dato);
          if (raiz == null)
              raiz = nuevo;
          else
          {
              DatoTactics anterior = null;
              DatoTactics tmp = raiz;
              while (tmp != null)
              {
                  anterior = tmp;
                  if (dato < tmp.getDato())
                      tmp = tmp.getIzq();
                  else
                      tmp = tmp.getDer();
              }
              if (dato < anterior.getDato())
                  anterior.setIzq(nuevo);
              else
                  anterior.setDer(nuevo);
          }
          num_nodos++;
    }
 
 public boolean eliminar (int dato, boolean x ) {
                //tmp = raiz;
                //padre = null;  
                
                
		if (tmp != null) {
			
			//Búsqueda del Nodo a Eliminar
			while (dato != tmp.getDato())
			{
				padre = tmp;
				if (dato > tmp.getDato())
					tmp = tmp.getDer();
				else
					tmp = tmp.getIzq();
			}				
		}
		
		//Nodo Hoja
		if ((tmp.getIzq() == null) && (tmp.getDer()== null)){
                    if (padre.getIzq() == tmp){
			    padre.setIzq(null);
                            x=false; // Si entra por un tipo de eliminación no debe entrar a la de los dos hijos
                    }else{
			x=false;   
                        padre.setDer(null);
                    }
                }
			// Padre con un Hijo
		if ((tmp.getIzq() == null) && (tmp.getDer() != null) || ((tmp.getIzq() != null) && (tmp.getDer() == null))){ 
			if ((padre.getIzq() == tmp) && (tmp.getIzq() !=  null)){
                            x=false;
                            padre.setIzq(tmp.getIzq());
                        }else
                        {
                            
                        if ((padre.getIzq() == tmp) && (tmp.getDer() != null)){
				 padre.setIzq(tmp.getDer());
                                 x=false;
                        }else
				{	
					if ((padre.getDer() == tmp) && (tmp.getIzq() !=  null)){
                                            x=false;
                                            padre.setDer(tmp.getIzq());
                                        }else
						{	
						if ((padre.getDer() == tmp) && (tmp.getDer() !=  null)){
							padre.setDer(tmp.getDer());
                                                    x=false;
						}else
                                                {
                                                   if (padre == tmp){
                                                       if ((tmp.getDer() == null) && (tmp.getIzq() != null)){
                                                       padre.setDato(tmp.getIzq().getDato());
                                                       padre.setDer(tmp.getIzq().getDer());
                                                       padre.setIzq(tmp.getIzq().getIzq());
                                                       x=false;
                                                   }else{
                                                      if ((tmp.getIzq() == null) && (tmp.getDer() != null)){  
                                                          padre.setDato(tmp.getDer().getDato());
                                                          padre.setIzq(tmp.getDer().getIzq());
                                                          padre.setDer(tmp.getDer().getDer());
                                                          
                                                          x=false;
                                                      }
                                                   }
                                                   
                                                      }
                                                }
                                                }
                        }
                        }
                }
                return x;
 }
		
            //Padre con Dos Hijos
               public void eliminar(int dato){
                
                tmp = raiz;
                padre = raiz;
                pdre= null;
                
		if (eliminar(dato, true)){ 
                    if ((tmp.getIzq() != null) && (tmp.getDer() != null)) {
				pdre = tmp;
				padre = tmp;
				tmp = tmp.getDer();
					while (tmp.getIzq() != null){
						padre = tmp;
						tmp = tmp.getIzq();
                                          
					}
						pdre.setDato(tmp.getDato());
                                                eliminar(tmp.getDato(), true);
                    }
                    }
                }
				
				
			

    public DatoTactics getRaiz() {
        return raiz;
    }

    public void setRaiz(DatoTactics raiz) {
        this.raiz = raiz;
    }

    public int getNumNodos() {
        return num_nodos;
    }

    //Recorrido preorden, recibe el nodo a empezar (raiz) y una linkedlist para ir guardando el recorrido
    public void preorden(DatoTactics aux,LinkedList recorrido){
          if (aux != null)
          {
              recorrido.add(aux.getDato());
              preorden (aux.getIzq(),recorrido);
              preorden (aux.getDer(),recorrido);
          }
    }
    //Recorrido inorden, recibe el nodo a empezar (raiz) y una linkedlist para ir guardando el recorrido
    public void inorden(DatoTactics aux,LinkedList recorrido){
          if (aux != null)
          {
              inorden (aux.getIzq(),recorrido);
              recorrido.add(aux.getDato());
              inorden (aux.getDer(),recorrido);
          }
    }
    //Recorrido postorden, recibe el nodo a empezar (raiz) y una linkedlist para ir guardando el recorrido
    public void postorden(DatoTactics aux,LinkedList recorrido){
          if (aux != null)
          {
              postorden (aux.getIzq(),recorrido);
              postorden (aux.getDer(),recorrido);
              recorrido.add(aux.getDato());
          }
    }
    //Recorrido por nivel, recibe el nodo a empezar (raiz) y una linkedlist para ir guardando el recorrido
    public void porNivel(DatoTactics aux,LinkedList recorrido){
        LinkedList<DatoTactics> cola = new LinkedList<DatoTactics>();
        cola.addLast(aux);
        while(cola.size()> 0){
            DatoTactics tmp = cola.pollFirst();
            recorrido.add(tmp.getDato());
            if(tmp.getIzq()!=null){
                cola.addLast(tmp.getIzq());
            }
            if(tmp.getDer()!=null){
                cola.addLast(tmp.getDer());
            }
        }
    }

    //Metodo para verificar si hay un nodo en el arbol
    public boolean existe(int dato, int i) {
        DatoTactics aux = raiz;
        while (aux!=null) {
            if (dato==aux.getDato()){
                if (i!= 1)
                JOptionPane.showMessageDialog(null, "Error: Valor ya Insertado");
                return true;
            }else
                if (dato>aux.getDato())
                    aux=aux.getDer();
                else
                    aux=aux.getIzq();
        }
        return false;
    }


    private void altura (DatoTactics aux,int nivel)  {
        if (aux != null) {
            altura(aux.getIzq(),nivel+1);
            alt = nivel;
            altura(aux.getDer(),nivel+1);
        }
    }
    //Devuleve la altura del arbol
    public int getAltura(){
        altura(raiz,1);
        return alt;
    }
}


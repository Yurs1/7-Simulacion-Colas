
package simcola;

public class Cola {
    float pSal=0;

        class Nodo {
            //int[]info = new int[4];          
            /*
            [0] 
            [1]
            [2]
            [3]
            */
            int info;  
            Nodo sig;
        }

        private Nodo raiz,fondo;

        Cola(){
            raiz=null;
            fondo=null;
        }

        public boolean vacia (){
            if (raiz == null)
                return true;
            else
                return false;
        }

        void vaciar() {
            raiz=null;
            fondo=null;
        }
        
        private void insertar (int info) {
            Nodo nuevo;
            nuevo = new Nodo ();
            nuevo.info = info;
            nuevo.sig = null;
            if (vacia ()) {
                raiz = nuevo;
                fondo = nuevo;
            } else {
                fondo.sig = nuevo;
                fondo = nuevo;
            }
        }
        
        public boolean insert(int info)  {
            if(tamaño()<4)  {   insertar(info);   return true;   }         
            else            {  return false;  }
        }
                
        int extraer ()        {
            if (!vacia ())
            {
                int informacion = raiz.info;
                if (raiz == fondo){
                    raiz = null;
                    fondo = null;
                } else {
                    raiz = raiz.sig;
                }
                return informacion;
            } else
                return Integer.MAX_VALUE;
        }

        public void imprimir() {
            Nodo reco=raiz;
            if(reco==null)
            {System.out.print("vacia");}
            //System.out.println("Listado de todos los elementos de la cola.");
            while (reco!=null) {
                System.out.print(reco.info+"-");
                reco=reco.sig;
            }
            //System.out.println();
        }
        
        public int tamaño() {
            Nodo reco=raiz;
            int tam=0;
            while (reco!=null) {
                tam++;
                reco=reco.sig;
            }
            return tam;
        }
}
  

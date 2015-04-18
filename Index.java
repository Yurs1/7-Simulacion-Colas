
package simcola;

public class Index {
        
       int lam, a, b;
       int tamaño_maximo, abandonan, tot_llegado, tot_atendidos;
       float tamaño_promedio, tiempo_prom_permanencia;
       float promTimeFila1, promTimeFila2, promTamFila1, promTamFila2; 
       
       
 /*
       el promTimeFila es el promedio del tiempo de espera que hace una fila.  (duracion de cada usuario dividido en total de visitas)
       el promTamFila es el tamaño promedio de la fila (muestreado por cada lambda iteracion)
 */
       
       
        Cola c1 = new Cola();
        Cola c2 = new Cola();
        float pEnt=0;
        float itera =0 ;
        int cont1=0;
        int cont2=0;
        int tamax=0;
        public void begin()
        {
            System.out.println("ingrese el valor de Lamba: ");
            lam = 5;
            a=6;
            b=4;
            abandonan=0;
            tot_llegado=1;
            pEnt=(float)(Math.random()*lam);
            System.out.println("	Tnow,	Nq1   ,	Nq2   ,	Pe     ,	Ps1     ,	Ps2     ,	Fila1    ,	Fila2    ,");
            System.out.print("begin	");
            imprimelam();            
            while(itera<lam*60)
            {
                
                
                
                //System.out.println(itera);
                while((pEnt<itera)||(c1.tamaño()>0&&c1.pSal<itera)||(c2.tamaño()>0&&c2.pSal<itera))
                
                {
                    if(pEnt<itera) { 
                        llegada(tot_llegado++); 
                        pEnt=pEnt +(float)(Math.random()*lam);
                        //itera=pEnt;
                        System.out.print("entr	");
                        imprimelam();
                    }

                    if(c1.tamaño()>0&&c1.pSal<itera)
                        {
                            cont1++;
                            c1.getFirst().timer();  // obtener el tiempo que demoro haciendo fila
                            promTimeFila1=promTimeFila1+c1.getFirst().time;
                            c1.extraer();
                            c1.pSal=itera +b+ (float)(Math.random()*(a-b));
                            //itera=c1.pSal;
                            System.out.print("sal1	");
                            imprimelam();
                            
                            promTamFila1=promTamFila1 +c1.tamaño(); 
                            if(tamax<c1.tamaño()) {tamax=c1.tamaño();}
                        }

                    if(c2.tamaño()>0&&c2.pSal<itera)
                        {
                            cont2++;
                            c2.getFirst().timer(); // obtener el tiempo que demoro haciendo fila
                            promTimeFila2=promTimeFila2+c2.getFirst().time;
                            c2.extraer();
                            
                            c2.pSal=itera + b+(float)(Math.random()*(a-b));
                            //itera=c2.pSal; 
                            System.out.print("sal2	");
                            imprimelam();
                            
                            promTamFila2=promTamFila2 +c2.tamaño();
                            
                            if(tamax<c2.tamaño()) {tamax=c2.tamaño();}
                        }
                }
                //System.out.print("whil	");
                //imprimelam();
                itera=itera+(float) Math.random();
                
            }
            c1.imprimir();
            System.out.println("");
            c2.imprimir();
            System.out.println("");
            
            System.out.println("total llegadas: "+tot_llegado);
            System.out.println("abandonan: " +abandonan);
            System.out.println("total atendidos: " + (tot_llegado - abandonan - c1.tamaño()- c2.tamaño()));
            System.out.print("Querdaon en Cola 1: " + c1.tamaño() + "   " );
            c1.imprimir();
            System.out.println("");
            System.out.print("Querdaon en Cola 2: " + c2.tamaño() + "   " );
            c2.imprimir();
            System.out.println("");
            
            proms();
            
            System.out.println("el tamaño promedio de la fila 1 fue: "  +promTamFila1);
            System.out.println("el tamaño promedio de la fila 2 fue: "  +promTamFila2);
            System.out.println("el promedio de espera en la fila 1 fue: "  +promTimeFila1);
            System.out.println("el promedio de espera en la filas 2 fue: "  +promTimeFila2);
            
            System.out.println("el tamaño maximo de las filas fue: " + tamax );
            
        }   
        
    
    public void llegada(int tot_llegado )
    {
        /*si son iguales, por defecto se va a cola 1*/
        if(c2.tamaño()< c1.tamaño())
            {
                if(!c2.insert(tot_llegado, pEnt) ) {abandonan++;}
            }
        else
            {
                if(!c1.insert(tot_llegado, pEnt)) {abandonan++;}
            }
    }
        
    public void imprimelam()
    {
        System.out.print(itera+"	"+c1.tamaño() +"	"+c2.tamaño() +"	"+ pEnt+"	"+c1.pSal +"	"+ c2.pSal+"	");
        c1.imprimir();
        System.out.print("	");
        c2.imprimir();
        System.out.println("");
        
       // System.out.println("f1: " +promTimeFila1+"	f2: "+promTimeFila2);
    }
    
    public void proms()
    {
        promTamFila1 = promTamFila1/(cont1);
        promTamFila2 = promTamFila2/(cont2);
        promTimeFila1 = promTimeFila1/(cont1);
        promTimeFila2 = promTimeFila2/(cont2);
    
    }
}

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/*
 * Implementacao de uma solucao usando kmeans com uma lista de flores Iris
 * 
 * @author Igor Sgorla Brehm
 */

public class atividadeKMEANS {
    public static void main(String args[]) throws Exception{
        int k = Integer.parseInt(args[0]);
        File file = new File("iris.data");
        Scanner in = new Scanner(file);
        ArrayList<Iris> list = new ArrayList<Iris>();
        ArrayList<Centroid> centroids = new ArrayList<Centroid>();
        ArrayList<Centroid> copy = new ArrayList<Centroid>();

        //populando a lista da populacao de Iris
        while(in.hasNextLine()){
            String aux = in.nextLine();
            if(aux.isEmpty()){
                break;
            }
            String[] parts = aux.split(",");
            double largP = Double.parseDouble(parts[0]);
            double comprP = Double.parseDouble(parts[1]);
            double largS = Double.parseDouble(parts[2]);
            double comprS = Double.parseDouble(parts[3]);
            String tipo = parts[4];
            Iris iris = new Iris(largP,comprP,largS,comprS,tipo);
            list.add(iris);
        }
        
        //pegando os primeiros centroides
        Random r = new Random();
        for(int i = 0; i < k; i++){
            int aux = r.nextInt(list.size());
            Centroid it1 = new Centroid(list.get(aux).getLargPetal(),list.get(aux).getCompPetal(),list.get(aux).getLargSepal(),list.get(aux).getCompSepal());
            Centroid it2 = new Centroid(list.get(aux).getLargPetal(),list.get(aux).getCompPetal(),list.get(aux).getLargSepal(),list.get(aux).getCompSepal());
            centroids.add(it1);
            copy.add(it2);
        }
        
        boolean acabou = false;
        while(true){
            for(int j = 0; j < list.size(); j++){ //iteracao pela lista de Iris
                Iris iris = list.get(j);
                int closest = -1;
                double bestDist = -1.0;
                
                for(int i = 0; i < k; i++){ //descobrindo centroide mais proximo
                    Centroid centroid = centroids.get(i);
                    double dist = Math.sqrt((Math.pow(centroid.getLargPetal() - iris.getLargPetal(), 2)) + (Math.pow(centroid.getCompPetal() - iris.getCompPetal(), 2)) + (Math.pow(centroid.getLargSepal() - iris.getLargSepal(), 2)) + (Math.pow(centroid.getCompSepal() - iris.getCompSepal(), 2)));
                    if(dist < bestDist || (bestDist < 0)){
                        bestDist = dist;
                        closest = i;
                    }
                }
                centroids.get(closest).list.add(iris);
                double sumLP = 0.0;
                double sumCP = 0.0;
                double sumLS = 0.0;
                double sumCS = 0.0;
                for(int i = 0; i < centroids.get(closest).list.size(); i++){ //soma todos os itens do cluster atual
                    sumLP = sumLP + centroids.get(closest).list.get(i).getLargPetal();
                    sumCP = sumCP + centroids.get(closest).list.get(i).getCompPetal();
                    sumLS = sumLS + centroids.get(closest).list.get(i).getLargSepal();
                    sumCS = sumCS + centroids.get(closest).list.get(i).getCompSepal();
                }
                centroids.get(closest).setLargPetal(sumLP/ centroids.get(closest).list.size()); //atualiza os valores
                centroids.get(closest).setCompPetal(sumCP/ centroids.get(closest).list.size());
                centroids.get(closest).setLargSepal(sumLS/ centroids.get(closest).list.size());
                centroids.get(closest).setCompSepal(sumCS/ centroids.get(closest).list.size());
            }
            
            for(int i = 0; i < centroids.size(); i++){ //verifica se os centroides nao mudaram
                if(copy.get(i).getLargPetal() == centroids.get(i).getLargPetal()){
                    if(copy.get(i).getCompPetal() == centroids.get(i).getCompPetal()){
                        if(copy.get(i).getLargSepal() == centroids.get(i).getLargSepal()){
                            if(copy.get(i).getCompSepal() == centroids.get(i).getCompSepal()){
                                acabou = true;
                            }
                        }
                    }
                }
                else{
                    acabou = false;
                }
            }
            if(acabou == true){
                break;
            }
            for(int i = 0; i < centroids.size(); i++){ //limpa a lista de itens dos centroides para nova iteracao
                copy.get(i).setLargPetal(centroids.get(i).getLargPetal());
                copy.get(i).setCompPetal(centroids.get(i).getCompPetal());
                copy.get(i).setLargSepal(centroids.get(i).getLargSepal());
                copy.get(i).setCompSepal(centroids.get(i).getCompSepal());
                centroids.get(i).list.clear();
            }
            System.out.println("LIST OF CENTROIDS:");
            for(int i = 0; i < centroids.size(); i++){
                System.out.println("-----------------------------");
                System.out.println(centroids.get(i).getLargPetal());
                System.out.println(centroids.get(i).getCompPetal());
                System.out.println(centroids.get(i).getLargSepal());
                System.out.println(centroids.get(i).getCompSepal());
                System.out.println("-----------------------------");
            }
            System.out.println("   ");
        }
    }
}

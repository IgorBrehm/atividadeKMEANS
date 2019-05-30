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
            Centroid it = new Centroid(list.get(aux).getLargPetal(),list.get(aux).getCompPetal(),list.get(aux).getLargSepal(),list.get(aux).getCompSepal());
            centroids.add(it);
            it.list.add(list.get(aux));
        }
        
        boolean acabou = false;
        while(true){
            for(int j = 0; j < list.size(); j++){ //iteracao pela lista de Iris
                Iris iris = list.get(j);
                Centroid closest = new Centroid(0.0,0.0,0.0,0.0);
                double bestDist = -1.0;
                
                for(int i = 0; i < k; i++){ //descobrindo centroide mais proximo
                    Centroid centroid = centroids.get(i);
                    double dist = Math.sqrt((Math.pow(centroid.getLargPetal() - iris.getLargPetal(), 2)) + (Math.pow(centroid.getCompPetal() - iris.getCompPetal(), 2)) + (Math.pow(centroid.getLargSepal() - iris.getLargSepal(), 2)) + (Math.pow(centroid.getCompSepal() - iris.getCompSepal(), 2)));
                    if(dist < bestDist || (bestDist < 0)){
                        bestDist = dist;
                        closest = centroid;
                    }
                }
                closest.list.add(iris);
                double oldLP = closest.getLargPetal();
                double oldCP = closest.getCompPetal();
                double oldLS = closest.getLargSepal();
                double oldCS = closest.getCompSepal();
                double sumLP = 0.0;
                double sumCP = 0.0;
                double sumLS = 0.0;
                double sumCS = 0.0;
                for(int i = 0; i < closest.list.size(); i++){ //soma todos os itens do cluster atual
                    sumLP = sumLP + closest.list.get(i).getLargPetal();
                    sumCP = sumCP + closest.list.get(i).getCompPetal();
                    sumLS = sumLS + closest.list.get(i).getLargSepal();
                    sumCS = sumCS + closest.list.get(i).getCompSepal();
                }
                closest.setLargPetal(sumLP/ closest.list.size()); //atualiza os valores
                closest.setCompPetal(sumCP/ closest.list.size());
                closest.setLargSepal(sumLS/ closest.list.size());
                closest.setCompSepal(sumCS/ closest.list.size());
                
                if((oldLP == closest.getLargPetal()) && (oldCP == closest.getCompPetal()) && (oldLS == closest.getLargSepal()) && (oldCS == closest.getCompSepal())){
                    closest.setStatus(true);
                }
            }
            for(int i = 0; i < centroids.size(); i++){ //verifica se os centroides nao mudaram
                if(centroids.get(i).isDone() == false){
                    acabou = false;
                }
                else{
                    acabou = true;
                }
            }
            if(acabou == true){
                break;
            }
            for(int i = 0; i < centroids.size(); i++){ //limpa a lista de itens dos centroides para nova iteracao
                centroids.get(i).list.clear();
            }
            for(int i = 0; i < centroids.size(); i++){
                System.out.println("-----------------------------");
                System.out.println(centroids.get(i).getLargPetal());
                System.out.println(centroids.get(i).getCompPetal());
                System.out.println(centroids.get(i).getLargSepal());
                System.out.println(centroids.get(i).getCompSepal());
                System.out.println("-----------------------------");
            }
        }
    }
}

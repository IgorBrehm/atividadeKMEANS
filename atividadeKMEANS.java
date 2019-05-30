import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class atividadeKMEANS {
    public static void main(String args[]) throws Exception{
        int k = Integer.parseInt(args[0]);
        File file = new File("iris.data");
        Scanner in = new Scanner(file);
        ArrayList<Iris> list = new ArrayList<Iris>();
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
            System.out.println("---------------------");
            System.out.println(largP);
            System.out.println(comprP);
            System.out.println(largS);
            System.out.println(comprS);
            System.out.println(tipo);
            System.out.println("---------------------");
            Iris iris = new Iris(largP,comprP,largS,comprS,tipo);
            list.add(iris);
        }
        
    }
}

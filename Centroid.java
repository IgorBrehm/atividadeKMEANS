import java.util.ArrayList;
public class Centroid{
    private double largura_Petalas;
    private double comprimento_Petalas;
    private double largura_Sepalas;
    private double comprimento_Sepalas;
    public ArrayList<Iris> list = new ArrayList<Iris>();
    
    public Centroid(double larg_Petal, double compr_Petal, double larg_Sep, double compr_Sep){
        this.largura_Petalas = larg_Petal;
        this.comprimento_Petalas = compr_Petal;
        this.largura_Sepalas = larg_Sep;
        this.comprimento_Sepalas = compr_Sep;
    }
    
    public double getLargPetal(){
        return this.largura_Petalas;
    }
    
    public double getCompPetal(){
        return this.comprimento_Petalas;
    }
    
    public double getLargSepal(){
        return this.largura_Sepalas;
    }
    
    public double getCompSepal(){
        return this.comprimento_Sepalas;
    }
    
    public void setLargPetal(double newValue){
        this.largura_Petalas = newValue;
    }
    
    public void setCompPetal(double newValue){
        this.comprimento_Petalas = newValue;
    }
    
    public void setLargSepal(double newValue){
        this.largura_Sepalas = newValue;
    }
    
    public void setCompSepal(double newValue){
        this.comprimento_Sepalas = newValue;
    }
}

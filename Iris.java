public class Iris {
    private double largura_Petalas;
    private double comprimento_Petalas;
    private double largura_Sepalas;
    private double comprimento_Sepalas;
    private String tipo;
    
    public Iris(double larg_Petal, double compr_Petal, double larg_Sep, double compr_Sep, String umTipo){
        this.largura_Petalas = larg_Petal;
        this.comprimento_Petalas = compr_Petal;
        this.largura_Sepalas = larg_Sep;
        this.comprimento_Sepalas = compr_Sep;
        this.tipo = umTipo;
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
}

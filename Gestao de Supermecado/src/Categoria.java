import java.io.Serializable;
import java.util.Vector;

/*classe responsavel pela gestao de categorias de produtos*/

/**
 *
 * @author Chidi Mieka
 */

public class Categoria implements Serializable{
    private String categoria;
    
    /**
     *
     */
    public Categoria(){
        this("");
    }
    
    /**
     *
     * @param categoria
     */
    public Categoria(String categoria){
        this.categoria = categoria;
    }
    
    
    /**
     * atribui uma categoria ao produto
     */
    public void atribuiCategoria(){
       Vector categorias = Ficheiros.Ficheirostxt("Categorias");
       int nrCategorias = categorias.size();
       String dado = Validacao.intervaloOpcoes(visualizaCategorias(), 1, nrCategorias, false);//retorna a getCategoria
        
       categoria = (String) categorias.elementAt(Byte.valueOf(dado) - 1);
    }
    
    
    /**
     * Retorna a quantidade de produtos para uma ou todas categorias, segundo o caso. 
     * @param opc
     * @param index 
     * @return  
     */
    public static String qtdCategoria(byte opc,byte index){
        Vector categorias = Ficheiros.Ficheirostxt("Categorias");
        Vector quantPorCategoria = Ficheiros.Ficheirostxt("QuantPorCategoria");
        String str = "";
        switch(opc){
            case 1:
                System.out.println("");
                for(byte i = 0; i < categorias.size(); i++){
                    str += "Categoria " + (i + 1) + ": " +((String)categorias.elementAt(i)) + ": " + ((String)quantPorCategoria.elementAt(i)) + "\n";
                }
                str += "Insira o numero da categoria para visualizar os produtos: ";
                break;
            case 2: 
               System.out.println("");
               str += "Categoria " + (index) + ": " + ((String)categorias.elementAt(index - 1)) + ": " + 
                       ((String)quantPorCategoria.elementAt(index - 1)) + "\n"; 
               break;
        }
        
        return str;
    }
    
    
    /**
     * armazena num array todos os produtos da mesma Categoria  e depois guarda num ficheiro
     * @param produtos
     * @param categorias
     * @param opc 
     */
    public static void savePorCategoria(Vector produtos,Vector categorias, byte opc){
        
        try{
            Produto produto;
            Vector arrayProdutos = new Vector();
             
            for(byte i = 0; i < categorias.size(); i++){
                
                for(byte j = 0; j < produtos.size(); j++){
                    produto = (Produto) produtos.elementAt(j);
                    
                    if(produto.getCategoria().equalsIgnoreCase((String)categorias.elementAt(i))){
                        arrayProdutos.addElement(produto);
                        arrayProdutos.trimToSize();
                    }
                }
                //TabelasVisualizacoes.visualizaProdutosComplete(arrayProdutos);
                Ficheiros.toProdutosFiles(arrayProdutos,(String)categorias.elementAt(i),opc);
                arrayProdutos.removeAllElements();
            }
            
        }catch(ArrayIndexOutOfBoundsException | NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
         /**
     * Guarda a referencia no respectivo ficheiro
     * @param produto 
     */
    public static void guardaRefNoFicheiro(Produto produto){
        Vector refsArray = Ficheiros.Ficheirostxt("Referencias");
        reduzAumentaQuant(retornaIndiceCategoria(produto),(byte) 1);//aumenta a quantidade da getCategoria
        refsArray.addElement(produto.dadosProduto().getReferencia());
        refsArray.trimToSize();
        Ficheiros.toFicheiroTxt(refsArray,"Referencias");//Actualiza o ficheiro de referencias
    }
    
    
    /**
     * Concatena todas as getCategoria contidas no array de categorias
     * @return uma string com todas as categorias
     */
    public static String visualizaCategorias(){
        Vector categorias = Ficheiros.Ficheirostxt("Categorias");
        String concat = "";
        try{
            System.out.println("Escolha a categoria: ");
            
            for(byte i = 0; i < categorias.size(); i++){
                concat += String.valueOf(i + 1) + ". " + ((String)categorias.elementAt(i)) + "\n";
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return concat;
    }
    
    //
    /**
     * retorna a respectiva getCategoria a partir do index
     * @param index
     * @return Uma getCategoria
     * @throws ArrayIndexOutOfBoundsException 
     */
    public static String retornaCateCorrespondente(byte index)throws ArrayIndexOutOfBoundsException{
        
        return (String) Ficheiros.Ficheirostxt("Categorias").elementAt(index - 1);
    }
    
    /**
     * retorna a getCategoria a partir do prefixo
     * @param pref
     * @return 
     */
    public static String retornaCateFromPrefix(String pref){
        Vector prefixosCate = Ficheiros.Ficheirostxt("PrefixosCate");
        Vector categorias = Ficheiros.Ficheirostxt("Categorias");
        String categoria = "";
        
        for(byte i = 0; i < prefixosCate.size(); i++){

            if(pref.equalsIgnoreCase((String)prefixosCate.elementAt(i))){
                categoria =  (String)categorias.elementAt(i);
                break;
            }
        }
        return categoria;
    }
    
    /**
     * reduz ou aumenta, conforme a opcao, a quantidade duma getCategoria dada pelo 'index'
     * @param index
     * @param opc 
     */
    public static void reduzAumentaQuant(byte index,byte opc){
        Vector quantPorCategoria = Ficheiros.Ficheirostxt("QuantPorCategoria");
        int quantCate = Integer.valueOf((String)quantPorCategoria.elementAt(index)),
                quantTroc;
        try{
            switch(opc){
                case 1://Aumenta a quantidade de produtos dessa getCategoria
                    quantTroc = quantCate;
                    quantTroc++;
                    quantPorCategoria.set(index, quantTroc);
                    break;
                case 2: //decresce a quantidade de produtos dessa getCategoria
                    quantTroc = quantCate;
                    quantTroc--;
                    quantPorCategoria.set(index, quantTroc);
                    break;
            }
            Ficheiros.toFicheiroTxt(quantPorCategoria,"QuantPorCategoria");
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace(); 
        }
    }
    
         /**
     * recebe a referencia e retorna o prefixo
     * @param ref
     * @return 
     */
    public static String retornaPrefixo(String ref){
        
        if(ref.length() == 5){
            return ref.substring(0,2);
        }else{
            return ref.substring(1,3);
        }
    }
    
    /**
     * retorna o indice da getCategoria do respectivo produto
     * @param produto
     * @return o indice da getCategoria
     */
    public static byte retornaIndiceCategoria(Produto produto){
        byte index = 0;
        Vector categorias = Ficheiros.Ficheirostxt("Categorias");
        
        for(byte i = 0; i < categorias.size(); i++){
            
            if(produto.getCategoria().equalsIgnoreCase((String)categorias.elementAt(i))){
                index = i;
                break;
            }
        }
        return index;
    }
    
    /**
     * retorna a quantidade da getCategoria
     * @param categoria
     * @return 
     */
    public static int retornaQuantCategoria(String categoria){
        int quant = 0;
        Vector categorias = Ficheiros.Ficheirostxt("Categorias");
        Vector quantPorCategoria = Ficheiros.Ficheirostxt("QuantPorCategoria");
        
        for(byte i = 0; i < categorias.size(); i++){
            
            if(categoria.equalsIgnoreCase((String)categorias.elementAt(i))){
                quant = Integer.valueOf((String) quantPorCategoria.elementAt(i));
                break;
            }
        }
        return quant;
    }
    
    /**
     *
     * @return
     */
    public String getCategoria(){ return categoria;}

    /**
     *
     * @param categoria
     */
    public void setCategoria(String categoria){ 
        this.categoria = categoria;
    }
    
}

import java.io.Serializable;
import java.util.Vector;


/*classe responsavel pela criacao e alteracao de dados de um dado produto*/
/*nivel de acesso: gerente*/

/**
 *
 * @author Chidi Mieka
 */

public class Produto implements Serializable{
    
    
    private DadosProduto dadosProduto;
    private Categoria categoria;
    private Vector comentarios;//essa variavel vai servir para deixar as reclamacoes,dados a alterar... sobre o produto
    private byte comentariosPendentes;//os comentarios que ainda nao foram vistos pelo gerente
    private boolean dentroPrazo;
    
    /**
     *
     */
    public Produto(){
        this(new Categoria(), new DadosProduto(), new Vector(), true, (byte) 0);
    }
    
    /**
     *
     * @param categoria
     * @param dadosProduto
     * @param comentarios
     * @param dentroPrazo
     * @param comentariosPendentes
     */
    public Produto(Categoria categoria, DadosProduto dadosProduto, Vector comentarios, boolean dentroPrazo, byte comentariosPendentes){
        this.categoria = categoria;
        this.dadosProduto = dadosProduto;
        this.comentarios = comentarios;
        this.dentroPrazo = dentroPrazo;
        this.comentariosPendentes = comentariosPendentes;
    }
    
    /**
     *
     * @param dentroDoPrazo
     */
    public void setDentroPrazo(boolean dentroDoPrazo){ this.dentroPrazo = dentroDoPrazo;}

    /**
     *
     * @return
     */
    public boolean getDentroPrazo(){ return dentroPrazo;}

    /**
     *
     * @param comentarios
     */
    public void setComentarios(Vector comentarios){
        this.comentarios = comentarios;
    }
    
    /**
     *
     * @return
     */
    public Vector getComentarios(){ return comentarios;}
        
    /**
     *
     * @return
     */
    public DadosProduto dadosProduto(){
        return dadosProduto;
    }
    
    /**
     *
     * @param comentariosPendentes
     */
    public void setComentariosPendentes(byte comentariosPendentes){ this.comentariosPendentes = comentariosPendentes;}
    
    /**
     *
     * @return
     */
    public byte getComentariosPendentes(){ return comentariosPendentes;}
    
    /**
     *
     * @param categoria
     */
    public void setCategoria(String categoria){ this.categoria.setCategoria(categoria);}

    /**
     *
     * @return
     */
    public String getCategoria(){return categoria.getCategoria();}
    
    /**
     *
     */
    public void visualizaDados(){
        System.out.println("");
        System.out.println("________________DADOS DO PRODUTO___________________");
        System.out.println("Referencia do produto: " + dadosProduto.getReferencia());
        System.out.println("Nome do produto: " + dadosProduto.getNome());
        System.out.println("Unidade: " + dadosProduto.getUnidade());
        System.out.println("Descricao do produto: " + dadosProduto.getDescricao());
        System.out.println("Marca do produto: " + dadosProduto.getMarca());
        System.out.println("Preco do produto: " + dadosProduto.getPreco());
        System.out.println("Validade do produto: " + dadosProduto.getValidade());
        System.out.println("Categoria do produto: " + categoria.getCategoria());
        System.out.println("Fabricante: " + dadosProduto.getFabricante());
        System.out.println("Stock do produto: " + dadosProduto.getStock());
        System.out.println("Quantidade vendida: " + dadosProduto.getQtdVendida());
        System.out.println("_____________________________________________________");
        System.out.println("");
    }
    
     /**
     * atribui uma unidade ao produto
     */
    public void unidade(){
        String dado = Validacao.intervaloOpcoes((byte) 36, (byte) 1, (byte) 2, false);
        
        if(Byte.valueOf(dado) == 1){
            this.dadosProduto.setUnidade("Unidade");
        }else{
            this.dadosProduto.setUnidade("Embalagem");
        }
    }

    /**
     * faz o calculo dos dias em falta para  expericao da validade
     * @return os dias em falta para a expiracao da validade 
     */
    public long diasEmFaltaValidade(){
        
        return FuncoesTempo.tempoDentre("dias", FuncoesTempo.dataActual(), FuncoesTempo.stringParaData(this.dadosProduto.getValidade()));
    }
    
    /**
     * retorna a 'Sim' se o produto estiver dentro do prazo, 'Nao' se for o contrario
     * @return O estado do produto
     */
    public String retornaEstadoProduto(){
        
        if(this.getDentroPrazo() == true){
            return "Sim";
        }else{
            return "Nao";
        }
    }
    
    /**
     * Guarda um produto no ficheiro 
     */
    public void guardaNoFicheiro(){
        try{
            Produto pro;
            Vector arrayProdutos = Ficheiros.FicheirosProduto(this.getCategoria());
            
            for(int i = 0; i < arrayProdutos.size(); i++){
                pro = (Produto) arrayProdutos.elementAt(i);
                
                if(this.dadosProduto.getReferencia().equalsIgnoreCase(pro.dadosProduto.getReferencia())){
                    arrayProdutos.set(i,this);//atribui o 'novo' produto modificado
                    break;
                }
            }
            //passa os arrayProdutos para o ficheiro da respectiva getCategoria
            Ficheiros.toProdutosFiles(arrayProdutos,this.getCategoria(),(byte) 2);
            guardaNoFicheiroPrincipal(this);
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Guarda o produto no ficheiro principal
     * @param produto 
     */
    private void guardaNoFicheiroPrincipal(Produto produto){
        Produto pro;
        Vector arrayProdutos = Ficheiros.FicheirosProduto("Produtos");
        
        for(int i = 0; i < arrayProdutos.size(); i++){
            pro = (Produto) arrayProdutos.elementAt(i);

            if(produto.dadosProduto.getReferencia().equalsIgnoreCase(pro.dadosProduto.getReferencia())){
                arrayProdutos.set(i,produto);//atribui o 'novo' produto modificado
                break;
            }
        }
        Ficheiros.toProdutosFiles(arrayProdutos,"Produtos",(byte) 2);
    }
  
}

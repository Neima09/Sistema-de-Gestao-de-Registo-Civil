import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Vector;

/*nivel de acesso: gerente*/
//classe responsavel pelo registo das compras

/**
 *
 * @author Chidi Mieka
 */
public class RegistoVendas implements Serializable{
    
    private Vector produtoArray;
    private static Vector arrayRegistoVendas;
    private Vector listaCompras;
    private String nomeFuncionario,nomeCliente;
    private float totalCompra,valorEntregue;
    private int codigoCompra;
    private LocalDateTime dataCompra;
    
    /**
     *
     */
    public RegistoVendas(){
        arrayRegistoVendas = Ficheiros.leituraFicheiro("vendas");
    }
    
    /**
     *
     * @param listaCompras
     * @param totalCompra
     * @param valorEntregue
     * @param usuario
     */
    public RegistoVendas(Vector listaCompras, float totalCompra,float valorEntregue,Usuario usuario){
        try{
            codigoCompra = (int) (Math.random() * 1000000);//atribui um codigo de compra
            this.listaCompras = listaCompras;
            this.totalCompra = totalCompra;
            this.valorEntregue = valorEntregue;
            dataCompra = LocalDateTime.now();//atribui a data actual
            mudaStockEQtdProduto(listaCompras);
            nomeFuncionario = usuario.dadosPessoais().getNome();
            nomeCliente = Validacao.Letras("Nome do cliente: ", false);
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * muda a quantidade e o stock do produto
     * @param listaCompras 
     */
    private void mudaStockEQtdProduto(Vector listaCompras){ 
        Produto produto;
        ItemVendido item;
        
        for(byte i = 0; i < listaCompras.size(); i++){
            item = (ItemVendido) listaCompras.elementAt(i);
            produto = item.getProduto();//recebe o produto 
            produto.dadosProduto().setQtdVendida(produto.dadosProduto().getQtdVendida() + item.getQuantidade());//guarda a quantidade vendida do produto
            produto.dadosProduto().setStock(produto.dadosProduto().getStock() - item.getQuantidade());//altera o stock do produto
            stockEQtdChange(produto);
        }
    }
    
    /**
     * muda o stock e a quantidade do produto nos arrays dos produtos da getCategoria e principal
     * @param produto 
     */
    private void stockEQtdChange(Produto produto){
        Produto pro;
        try{
            produtoArray = Ficheiros.FicheirosProduto(produto.getCategoria());//recebe o array de produtos da getCategoria do produto
            
            for(int i = 0; i < produtoArray.size(); i++){//substitui o produto no array da getCategoria 
                pro = (Produto) produtoArray.elementAt(i);
                
                if(produto.dadosProduto().getReferencia().equalsIgnoreCase(pro.dadosProduto().getReferencia())){
                    produtoArray.set(i,produto);
                    break;
                }
            }
            Ficheiros.toProdutosFiles(produtoArray,produto.getCategoria(),(byte) 2);//guarda o array
            
            produtoArray = Ficheiros.FicheirosProduto("Produtos");//recebe o array principal dos produtos
            
            for(int i = 0; i < produtoArray.size(); i++){//substitui o produto no array principal dos produtos
                pro = (Produto) produtoArray.elementAt(i);
                
                if(produto.dadosProduto().getReferencia().equalsIgnoreCase(pro.dadosProduto().getReferencia())){
                     produtoArray.set(i,produto);
                    break;
                }
            }
             Ficheiros.toProdutosFiles(produtoArray,"Produtos",(byte) 2);//guarda o array
             
        }catch(NullPointerException | IndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * regista as compras no ficheiro
     * @param listaCompras
     * @param totalCompra
     * @param valorEntregue
     * @param usuario 
     */
    public void registaCompras(Vector listaCompras,float totalCompra,float valorEntregue, Usuario usuario){
        arrayRegistoVendas.addElement(new RegistoVendas(listaCompras,totalCompra,valorEntregue, usuario));
        arrayRegistoVendas.trimToSize();
        Ficheiros.toFicheiro(arrayRegistoVendas,"vendas");
    }
    
    /**
     * visualiza as compras
     */
    public void visualizaRegistoVendas(){
        String dado = "";
        do{
            TabelasVisualizacoes.visualizaRegistoVendas(arrayRegistoVendas);
            dado = Validacao.intervaloOpcoes((byte) 14, (byte) 1, (byte) 2, false);

            if(Byte.parseByte(dado) == 1){
                TabelasVisualizacoes.printDetalhesVenda(arrayRegistoVendas);
            }
        }while(Byte.valueOf(dado) != 2);
    }

    /**
     *
     * @return
     */
    public int getCodigoCompra(){return codigoCompra;}

    /**
     *
     * @return
     */
    public float getValorEntregue(){return valorEntregue;}

    /**
     *
     * @return
     */
    public String getNomeFuncionario(){return nomeFuncionario;}

    /**
     *
     * @return
     */
    public String getNomeCliente(){return nomeCliente;}

    /**
     *
     * @return
     */
    public LocalDateTime getDataCompra(){return dataCompra;}

    /**
     *
     * @return
     */
    public Vector getListaCompras(){return listaCompras;}
}

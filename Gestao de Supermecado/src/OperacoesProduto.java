
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chidi Mieka
 */
public class OperacoesProduto{

    /**
     *
     */
    public static Vector produtosDentroPrazo;

    /**
     *
     */
    public static Vector produtosForaPrazo;

    /**
     *
     */
    protected static Vector categoriasBuf;

    /**
     *
     */
    protected Usuario usuario;
    
    /**
     *
     */
    public OperacoesProduto(){
    }
    
    /**
     *
     * @param usuario
     */
    public OperacoesProduto(Usuario usuario){
        this.usuario = usuario;
    }
    
    /**
     *
     */
    protected void insercaoProdutos(){
        InserirNovoProduto inserir = new InserirNovoProduto();
        inserir.inserirProdutos();
    }
    
    /**
     *
     */
    protected void gestaoStock(){
        GestaoStock gestaoStock = new GestaoStock();
        gestaoStock.menuGestaoStock(usuario);
    }
    
    /**
     *
     */
    protected void vendaProdutos(){
        VendaProdutos vendaProdutos = new VendaProdutos();
        vendaProdutos.menuVendas(usuario);
    }
    
    /**
     *
     */
    protected void comentariosSobreProdutos(){
        OperacoesComentarios operacao = new OperacoesComentarios();
        operacao.menuComentariosSobreProdutos(usuario);
    }
    /**
     * altera os dados do objecto instanciado
     * @param produto 
     */
    protected void alterarProduto(Produto produto){
        String dado1,dado;
        try{
            dado = Validacao.intervaloOpcoes("Quantos atributos pretende modificar? ",(byte) 1,(byte) 9, true);//recebe o nr de atributos por modificar
            
            if(!dado.equalsIgnoreCase("s")){
                dado1 = Validacao.opcoes(Byte.parseByte(dado), (byte) 2, true);//retorna as opcoes por se modificar
                
                if(!dado1.equalsIgnoreCase("s")){
                    
                    for(byte i = 0; i < dado1.length(); i++){
                        
                        switch(dado1.charAt(i)){
                            case '1':
                                dado = Validacao.Letras("Nome: ", false);
                                produto.dadosProduto().setNome(dado);
                                break;
                            case '2':
                                dado = Validacao.Letras("Descricao: ", false);
                                produto.dadosProduto().setDescricao(dado);break;
                            case '3':
                                dado = Validacao.Letras("Marca: ", false);
                                produto.dadosProduto().setMarca(dado);
                                break;
                            case '4':
                                dado = Validacao.Float("Preco: ", false);
                                produto.dadosProduto().setPreco(Float.parseFloat(dado));
                                break;
                            case '5':
                                dado =  Validacao.regex("Validade", "Validade(Ex: 20/03/2024): ");
                                produto.dadosProduto().setValidade(dado);
                                break;
                            case '6':
                                OperacoesCategoria operacao = new OperacoesCategoria();
                                produto.setCategoria(operacao.mudaCategoria(produto.getCategoria()));
                                break;
                            case '7':
                                dado = Validacao.Letras("Fabricante: ", false);
                                produto.dadosProduto().setFabricante(dado);
                                break;
                            case '8':
                                dado = Validacao.Digitos("Stock: ", false);
                                produto.dadosProduto().setStock(Integer.parseInt(dado));
                                break;
                            case '9':
                                produto.unidade();
                                break;
                            default: System.out.println("Falha... ");
                        }
                    }
                }
            }
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(NullPointerException ex){
            System.err.print(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Recebe a getCategoria de produto e se esta nao estiver no array de categorias 
     dos produtos introduzidos ate o momento adiciona ao vector
     * @param categoria 
     */
    protected void appendCategoria(String categoria){
        String cate;
        boolean nova = true;
        if(!categoriasBuf.isEmpty()){
        
            for(byte i = 0; i < categoriasBuf.size(); i++){
                cate = (String) categoriasBuf.elementAt(i);

                if(categoria.equalsIgnoreCase(cate)){
                    nova = false;
                }
            }
        }
        
        if(nova){
                categoriasBuf.addElement(categoria);
                categoriasBuf.trimToSize();
         }
        
    }
    
     /**
     * Actualiza os dados dos produtos, isto e, o prazo e os comentarios
     * @
     */
    protected void updateProdutos(){
        
        categoriasBuf = new Vector();
        produtosDentroPrazo = new Vector();
        produtosForaPrazo = new Vector();
        try{
            Comentario.retornaProdutosPendentes();//actualiza o numero de comentarios pendentes nos produtos
            //Entretanto, na tabela dos produtos, na legendagem 'Comentarios Pen.' e mostrada a quantidade de comentarios do produto
            
            Vector array = Ficheiros.FicheirosProduto("Produtos");
            Produto produto;

            for(int i = 0; i < array.size(); i++){
                produto = (Produto) array.elementAt(i);
                appendCategoria(produto.getCategoria());
                
                if(produto.diasEmFaltaValidade() < 0){// se os dias em falta forem menores que 0
                    produto.setDentroPrazo(false);
                    array.set(i, produto);//substitui o produto modificado no array
                    produtosForaPrazo.addElement(produto);//adiciona o produto no array dos arrayProdutos fora do prazo
                    produtosForaPrazo.trimToSize();
                }else{
                    produto.setDentroPrazo(true);
                    array.set(i, produto);
                    produtosDentroPrazo.addElement(produto);//adiciona o produto no array dos arrayProdutos dentro do prazo
                    produtosDentroPrazo.trimToSize();
                }
            }
            Ficheiros.toProdutosFiles(array,"Produtos",(byte) 2);
            Categoria.savePorCategoria(array,categoriasBuf,(byte) 2);//guarda as alteracoes nos ficheiros
            
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    /**
     * retorna o produto correspondente a referencia
     * @param referencia
     * @return Um produto
     */
    protected Produto produtoRetorna(String referencia){
        Vector produtos = Ficheiros.FicheirosProduto(Categoria.retornaCateFromPrefix(Categoria.retornaPrefixo(referencia)));
        
        return retornaProdutoCorrespondente(referencia,produtos);
    }
    
    /**
     * procura no array arrayProdutos pelo produto com a referencia correspondente
     * @param referencia
     * @param produtos
     * @return Um produto
     */
    private Produto retornaProdutoCorrespondente(String referencia,Vector produtos){
        Produto aux = null, aux1;
        try{
            
            for(int i = 0; i < produtos.size(); i++){
                aux1 = (Produto) produtos.elementAt(i);

                if(aux1.dadosProduto().getReferencia().toLowerCase().contains(referencia.toLowerCase())){
                    aux = aux1;
                    break;
                }
            }
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }

        return aux;
    }
    
    /**
     * busca o produto a partir da referencia
     * @return Um produto
     */
    protected Produto buscaProduto(){
        Vector produtos = Ficheiros.FicheirosProduto("Produtos");
        String ref;
        Produto produto = new Produto();
        byte opc = Byte.valueOf(Validacao.intervaloOpcoes((byte) 37, (byte) 1, (byte) 2, false));
        
        switch(opc){
            case 1:
                String nomePro = Validacao.Letras("Nome: ", false);
                for(short i = 0; i < produtos.size(); i++){
                    produto = (Produto) produtos.elementAt(i);
                    
                    if(produto.dadosProduto().getNome().equalsIgnoreCase(nomePro)){
                        break;
                    }
                }
                break;
            case 2:
                ref = Validacao.regex("Referencia", "Referencia: ");
                produto = produtoRetorna(ref);
                break;
        }
                
        return produto;
    }
    
    
    /**
     * possiblilita a procura de um produto usando a referencia,o nome, a getCategoria ou uma palavra chave 
     */
    protected void procuraProdutos(){
        Produto produto;
        Vector produtos;
        String dado;
            do{
                System.out.println("");
                System.out.println("____________________PROCURA DE PRODUTOS______________________");
                System.out.println("");
                
                OperacoesCategoria operacao = new OperacoesCategoria();
                dado = Validacao.intervaloOpcoes((byte) 15, (byte) 1, (byte) 2, true);
                
                if(dado.equalsIgnoreCase("s")){
                    
                    switch(Byte.parseByte(dado)){
                        case 1:
                            dado = Validacao.intervaloOpcoes(Categoria.visualizaCategorias(),(byte) 1,(byte) operacao.getNrCategorias(), false);
                            produtos = Ficheiros.FicheirosProduto(Categoria.retornaCateCorrespondente(Byte.parseByte(dado)));//recebe os produtos da determinada getCategoria
                            TabelasVisualizacoes.visualizaProdutos(produtos);//visualiza os produtos da getCategoria indicada
                            break;
                        case 2:
                            produto = buscaProduto();
                            TabelasVisualizacoes.visualizaProduto(produto);
                        break;
                    }
                }
            }while(dado.equalsIgnoreCase("s"));
    }
    
}


import java.util.Vector;


/**
 * Classe responsavel pela gestao do stock ed produtos
 * 
 */
public class GestaoStock extends OperacoesProduto{
    
    private Produto produto = new Produto();
    private Vector  produtos;
    
    /**
     *
     */
    public GestaoStock(){
    }
    
    /**
     *
     * @param usuario
     */
    public void menuGestaoStock(Usuario usuario){
        String dado = "";
        try{
            do{
                System.out.println("");
                System.out.println("____________________GESTAO DE STOCK______________________");
                System.out.println("");
                dado = Validacao.intervaloOpcoes((byte) 10, (byte) 1, (byte) 4, true);
                
                if(!dado.equalsIgnoreCase("s")){
                    updateProdutos();
                    
                    switch(Byte.parseByte(dado)){
                        case 1:
                            alterarDadosPro();
                            break;
                        case 2:
                            removerProduto();
                            break;
                        case 3:
                            verStock();
                            break;
                    }
                }
            }while(!dado.equalsIgnoreCase("s"));
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Visualiza o stock dos produtos segundo a opcao
     */
    private void verStock(){
        String dado;
        try{
            dado = Validacao.intervaloOpcoes((byte) 9, (byte) 1, (byte) 4, true);
            if(!dado.equalsIgnoreCase("s")){
                
                switch(Byte.parseByte(dado)){
                    case 1:
                        buscaProduto().visualizaDados();
                        break;
                    case 2:
                        OperacoesCategoria operacao = new OperacoesCategoria();
                        //recebe o numero da getCategoria por visualizar
                        dado = Validacao.intervaloOpcoes(Categoria.qtdCategoria((byte) 1, (byte) 0), (byte) 1, (byte) operacao.getNrCategorias(), true);

                        if(!dado.equalsIgnoreCase("s")){
                            System.out.print(Categoria.qtdCategoria((byte) 2, Byte.parseByte(dado)));
                            produtos = Ficheiros.FicheirosProduto(Categoria.retornaCateCorrespondente(Byte.parseByte(dado)));//preenche o array com os arrayProdutos da
                            //categoria escolhida
                            if(produtos.size() != 0){
                                visualProdutosTabelaEEspecifico(produtos);
                            }else{
                                System.out.print("A respectiva categoria nao tem produtos");
                            }
                        }
                        break;
                    case 3:
                        produtos = Ficheiros.FicheirosProduto("Produtos");//preenche o array com os arrayProdutos
                        visualProdutosTabelaEEspecifico(produtos);
                        break;
                    case 4:
                        visualizaProdutosFromValidade();
                        break;
                }
            }
        }catch(NullPointerException | IndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * visualiza os Produtos dentro e fora do prazo, segundo o caso
     */
    private void visualizaProdutosFromValidade(){
        Vector dentroPrazo = produtosDentroPrazo,
        foraPrazo = produtosForaPrazo;
        String dado = "";
        System.out.print("1. Produtos dentro do prazo: " + dentroPrazo.size()+ "\n"
        + "2. Produtos fora do prazo: " + foraPrazo.size());
        dado = Validacao.intervaloOpcoes("Insira a opcao para visualizar os produtos: ", (byte) 1, (byte) 2, true);
        
        if(!dado.equalsIgnoreCase("s")){
            
            switch(Byte.parseByte(dado)){
                case 1:
                    TabelasVisualizacoes.visualizaProdutosComplete(dentroPrazo);
                    break;
                case 2:
                    TabelasVisualizacoes.visualizaProdutosComplete(foraPrazo);
                    break;
            }
        }
    }
    

    
    /**
     * Visualiza o vector de produtos e, se desejar, um produto especifico
     * @param products 
     */
    private void visualProdutosTabelaEEspecifico(Vector products){
        String dado = "";
        
        do{
            TabelasVisualizacoes.visualizaProdutosComplete(products);//visualiza os arrayProdutos dados
            System.out.println(Mensagens.sugestiva((byte) 2));
            dado = Validacao.Letras("Se pretente visualizar um produto insira 'ref': ", true);
            
            if(!dado.equalsIgnoreCase("s") && dado.equalsIgnoreCase("ref")){
                dado = Validacao.regex("Referencia", "Referencia: ");
                produto = produtoRetorna(dado);
                produto.visualizaDados();//visualiza o produto em especifico
            }
        }while(!dado.equalsIgnoreCase("s"));
    }
    
    /**
     * altera os dados do produto
     */
    private void alterarDadosPro(){
        String dado, dadoAux;
        dado = Validacao.Digitos("Numero de produtos a alterar: ", false);
        
        for(byte i = 0; i < Integer.parseInt(dado); i++){
            System.out.println("Produto " + (i + 1) + " :");
            dadoAux = Validacao.regex("Referencia", "Referencia: ");
            produto = produtoRetorna(dadoAux);//vai retornar o` produto desejado
            produto.visualizaDados();
            alterarProduto(produto);
            produto.visualizaDados();
            System.out.print("Manter alteracoes?");
            dadoAux = Validacao.intervaloOpcoes((byte) 29, (byte) 1, (byte) 2, false);
            
            if(!dadoAux.equalsIgnoreCase("2")){
                produto.guardaNoFicheiro();//guarda as alteracoes no ficheiro
            }
        }
    }
    
    /**
     * Remove um produto
     */
    private void removerProduto(){
        int size;
        String dado;
        String opcao,categoria;
        String listProRemover [];
        
        try{
            do{
                dado = Validacao.Letras("Quantos produtos pretende remover? ", false);
                listProRemover = new String[Byte.parseByte(dado)];//lista dos produtos por remover
                
                for(byte i = 0; i < listProRemover.length; i++){
                    //recebe as referencias
                    listProRemover[i] = Validacao.regex("Referencia", String.valueOf(i + 1) + "o. Referencia: ");
                }
                showProdutos(listProRemover);//preenche o array arrayProdutos a visualiza os arrayProdutos 
                opcao = Validacao.intervaloOpcoes((byte) 27, (byte) 1, (byte) 3, false);
            }while(Byte.parseByte(opcao) == 2);
            
            if(Byte.parseByte(opcao) != 3){
                
                for(byte i = 0; i < listProRemover.length; i++){
                    categoria = Categoria.retornaCateFromPrefix(Categoria.retornaPrefixo(listProRemover[i]));//recebe a getCategoria do produto
                    size = Categoria.retornaQuantCategoria(categoria);
                    produtos = Ficheiros.FicheirosProduto(categoria);//retorna o array de arrayProdutos da getCategoria indicada pela referencia
                    removeProduto(produtos,listProRemover[i],categoria,size);
                }
            }
            
            Mensagens.printMensagemNotavel("os produtos foram removidos com sucesso", (byte) 25);
            
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Remove o produto da respectiva referencia do vector da respectiva getCategoria
     * @param produtos
     * @param ref
     * @param categoria
     * @param size 
     */
    private void removeProduto(Vector produtos,String ref,String categoria, int size){
        Produto pro;
        for(byte i = 0; i < size; i++){
            pro = (Produto) produtos.elementAt(i);
            
            if(pro.dadosProduto().getReferencia().toLowerCase().contains(ref.toLowerCase())){
                produtos.remove(pro);
                produtos.trimToSize();
                break;
            }
        }
        Ficheiros.toProdutosFiles(produtos,categoria,(byte) 2);//guarda o array recentemente modificado
        removeProArrayPrincipal(ref);
    }
    
    /**
     * Remove o produto da respectiva referencia do array principal de produtos
     * @param ref referencia do produto
     */
    private void removeProArrayPrincipal(String ref){
        OperacoesComentarios operacao = new OperacoesComentarios();
        OperacoesCategoria operacao1 = new OperacoesCategoria();
        Vector produtos1 = Ficheiros.FicheirosProduto("Produtos");//recebe os arrayProdutos do array principal;
        Produto pro;
        
        for(byte i = 0; i < produtos1.size(); i++){
            pro = (Produto) produtos1.elementAt(i);

            if(pro.dadosProduto().getReferencia().toLowerCase().contains(ref.toLowerCase())){
                produtos1.remove(pro);
                produtos1.trimToSize();
                operacao.removeComProduto(pro);
                break;
            }
        }
        
        Ficheiros.toProdutosFiles(produtos1,"Produtos",(byte) 2);//guarda o array recentemente modificado
        operacao1.reduzQuantCategoriaERemoveRef(ref);//reduz a quantidade da getCategoria do produto e remove a referencia
    }
    
    
    /**
     * Visualiza a lista dos produto por se remover
     * @param aux 
     */
    private void showProdutos(String aux[]){
        produtos = new Vector();
        
        for(byte i = 0; i < aux.length; i++){
            produtos.addElement(produtoRetorna(aux[i]));//recebe os arrayProdutos por se remover
            produtos.trimToSize();
        }
        TabelasVisualizacoes.visualizaProdutosComplete(produtos);
    }
        

}
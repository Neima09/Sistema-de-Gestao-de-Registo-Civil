
import java.util.Vector;

/*
 *classe responsavel pela insercao de novos produtos
 */

/**
 *
 * @author Chidi Mieka
 */
public class InserirNovoProduto extends OperacoesProduto{
    
    private Vector arrayProdutos;
    private boolean checkNovoproduto = false;
    private static final String OPCOESPRO = "123456789";
    
    /**
     *
     */
    public InserirNovoProduto(){
    }
    
    /**
     *  Nas opcoes de varios produtos o objectivo e permitir que possa escolher mais de um atributo em comum
        para mais de um produto. Isto e, se ha tres produtos com algumas- mas nao todas-caracteristicas ou dados iguais
        e possivel preencher os campos em comum, e depois para cada produto em especifico, preencher os campos restantes
        que sao as caracteristicas unicas do produto. Por exemplo, 4 produtos podem vir da mesma fabrica, terem a mesma marca
        e serem da mesma getCategoria, entretanto, os restantes campos de cada produto podem ser diferentes
     */
    public void inserirProdutos(){
        String dado;
        
        try{
            arrayProdutos = new Vector();
            categoriasBuf = new Vector();
             
            do{
                System.out.println("");
                System.out.println("____________________INSERCAO DE PRODUTOS______________________");
                System.out.println("");
                dado = Validacao.intervaloOpcoes((byte) 4, (byte) 1, (byte) 5, false);
               
                
                switch(Byte.parseByte(dado)){
                    case 1: 
                        /*cria um unico produto*/
                        Produto produto = new Produto();
                        preencheProduto(OPCOESPRO, produto);
                        posInsercao(produto);
                        break;
                    case 2: 
                        //significa que so pode escolher dois atributos 
                        variosProdutos((byte) 2);
                        break;
                    case 3: 
                        //significa que so pode escolher tres atributos 
                        variosProdutos((byte) 3);
                        break;
                    case 4: 
                        //significa que so pode escolher quatro atributos 
                        variosProdutos((byte) 4);
                        break;
                    case 5:
                        if(checkNovoproduto == true){//guarda os produtos no ficheiro se pelo menos um produto tenha sido registado
                            Ficheiros.toProdutosFiles(arrayProdutos,"Produtos",(byte) 1);
                            Categoria.savePorCategoria(arrayProdutos,categoriasBuf,(byte) 1);
                            arrayProdutos = null;
                            checkNovoproduto = false;
                        }
                        break;
                }
            }while(Byte.parseByte(dado) != 5);
            
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * metodo invocado quando ocorre o registo de varios produtos
     * @param opc 
     */
    private void variosProdutos(byte opc){
        String dado;
        int quant;
        String opcoes,opcoesFalta;
        try{
            dado = Validacao.opcoes(opc,(byte) 1, true);//le as opcoes em comum dos produtos
            
            if(!dado.equalsIgnoreCase("s")){
                opcoes = dado;//recebe as opcoes em comum
                dado = Validacao.Digitos("Quantos produtos pretende introduzir?\n Nao inclua o stock, apenas conte os produtos: ", true);
                
                if(!dado.equalsIgnoreCase("s")){
                    quant = Integer.parseInt(dado);
                    Mensagens.printMensagemNotavel("Preencha os campos em comum dos produtos",(byte)"Preencha os campos em comum dos produtos".length());
                    Produto produto = new Produto();
                    preencheProduto(opcoes, produto);
                    
                    if(!dado.equalsIgnoreCase("s")){
                        opcoesFalta = removeOpcoes(opcoes);//os atributos em falta nos produtos
                        Mensagens.printMensagemNotavel("Preencha os campos em falta de cada produto",(byte)"Preencha os campos em falta de cada produto".length());
                        
                        for(byte i = 0; i < quant; i++){
                            Produto proAux = new Produto();
                            passaOpcoesJaPreenchidas(opcoes, produto, proAux);
                            System.out.println("\n" + String.valueOf(i + 1) + "o. Produto" + "\n");
                            preencheProduto(opcoesFalta,proAux);
                            posInsercao(proAux);
                        } 
                    }
                }
            }
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * remove os campos que ja foram preenchidos
     * @param opcoes
     * @return Os campos ainda por preencher
     */
    private String removeOpcoes(String opcoes){
        String opcFalta = "";
        opcFalta = OPCOESPRO;
        
        for(byte i = 0; i < opcoes.length(); i++){
            opcFalta = opcFalta.replaceAll(String.valueOf(opcoes.charAt(i)),"");
        }
        
        return opcFalta;
    }
    
    /**
     * Preenche os campos do produto que ja foram previamente preenchidos na variavel 'produto'
     * @param opcoes
     * @param produto
     * @param produtoNovo 
     */
    private void passaOpcoesJaPreenchidas(String opcoes,Produto produto, Produto produtoNovo){
        try{
            label://essa label vai ajudar na opcao de saida, pois depois de o usuario introduzir 's' havera um break para a label inteira
            for(byte i = 0; i < opcoes.length(); i++){
                switch(opcoes.charAt(i)){
                    case '1':
                        produtoNovo.dadosProduto().setNome(produto.dadosProduto().getNome());
                        break;
                    case '2':
                        produtoNovo.dadosProduto().setDescricao(produto.dadosProduto().getDescricao());
                        break;
                    case '3':
                        produtoNovo.dadosProduto().setMarca(produto.dadosProduto().getMarca());
                        break;
                    case '4':
                        produtoNovo.setCategoria(produto.getCategoria());
                        break;
                    case '5':
                        produtoNovo.dadosProduto().setFabricante(produto.dadosProduto().getFabricante());
                        break;
                    case '6':
                        produtoNovo.dadosProduto().setUnidade(produto.dadosProduto().getUnidade());
                        break;
                    default: System.out.println("Falha no preenchimento dos dados. ");
                }
            }
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * preenche os campos do produto requesitados pelas opcoes 
     * @param opcoes
     * @param produtoNovo 
     */
    private void preencheProduto(String opcoes,Produto produtoNovo){
        String dado;
        try{
            label://essa label vai ajudar na opcao de saida, pois depois de o usuario introduzir 's' havera um break para a label inteira
            for(byte i = 0; i < opcoes.length(); i++){
                switch(opcoes.charAt(i)){
                    case '1':
                        dado = Validacao.Letras("Nome do produto: ", false);
                        produtoNovo.dadosProduto().setNome(dado);
                        break;
                    case '2':
                        dado = Validacao.Letras("Descricao do produto: ", false);
                        produtoNovo.dadosProduto().setDescricao(dado);
                        break;
                    case '3':
                        dado = Validacao.Letras("Marca do produto: ", false);
                        produtoNovo.dadosProduto().setMarca(dado);
                        break;
                    case '4':
                        Categoria categoria = new Categoria();
                        categoria.atribuiCategoria();
                        produtoNovo.setCategoria(categoria.getCategoria());
                        break;
                    case '5':
                        dado = Validacao.Letras("Fabricante: ", false);
                        produtoNovo.dadosProduto().setFabricante(dado);
                        break;
                    case '6':
                        produtoNovo.unidade();
                        break;
                    case '7':
                        dado = Validacao.Float("Preco do produto: ", false);
                        produtoNovo.dadosProduto().setPreco(Float.parseFloat(dado));
                        break;
                    case '8':
                        dado = Validacao.Digitos("Stock: ", false);
                        produtoNovo.dadosProduto().setStock(Integer.parseInt(dado));
                        break;
                    case '9':
                        dado = Validacao.regex("Validade", "Validade(Ex: 20/03/2024): ");
                        produtoNovo.dadosProduto().setValidade(dado);
                        break;
                    default: System.out.println("Falha no preenchimento dos dados. ");
                }
            }
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    /**
     * Verifica se o usuario pretende alterar,eliminar ou guardar o produto
     * @param produto 
     */
    private void posInsercao(Produto produto){
        String dadoAux;
        byte opcao = 0;
        try{
            OperacoesCategoria operacao = new OperacoesCategoria();
            String referencia = operacao.giveReferencia(Categoria.retornaIndiceCategoria(produto));
            produto.dadosProduto().setReferencia(referencia);
            
            do{
                produto.visualizaDados();
                dadoAux = Validacao.intervaloOpcoes((byte) 3, (byte) 1, (byte) 3, false);//alterar,eliminar ou guardar o produto
                
                opcao = Byte.parseByte(dadoAux);
                switch(opcao){
                    case 1: 
                        alterarProduto(produto);
                        break;
                    case 2: 
                        produto = null;// garbage collector fara o resto
                        System.out.println("");
                        System.out.println("Anulou o produto com sucesso!");
                        System.out.println("");
                        break;
                    case 3:
                        checkNovoproduto = true;
                        addProduto(produto);
                        break;
                    default: System.out.println("Falha no processo do registo do produto...");break;
                }
        }while(opcao != 3 && opcao != 2);
            
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Adciona o produto ao array de produtos
     * @param produto 
     */
    private void addProduto(Produto produto){
        Categoria.guardaRefNoFicheiro(produto);//guarda a referencia no array de referencias

        arrayProdutos.addElement(produto); 
        arrayProdutos.trimToSize();

        appendCategoria(produto.getCategoria());
    }
    

    
}


import java.text.DecimalFormat;
import java.util.Vector;

/**
 * Classe que faz a visualizacao dos dados em tabelas
 * @
 */
public class TabelasVisualizacoes {
    
    private static String formatacao;
    private static byte sizes[];
    /**
     * recebe o maior numero de linhas por cada objecto a ser imprimido
     */
    private static byte maiorLinhas = 1;
    
    /**
     * Visualiza os usuarios
     * @param users 
     */
    public static void visualizaUsuarios(Vector users){
        Usuario user;
        try{
            System.out.println("");
            System.out.println("__________________________UTILIZADORES DO SISTEMA ________________________");
            System.out.printf("|%3s|%6s|%18s|%5s|%15s|%20s\n", "Nr", "Codigo", "Nome do Usuario", "Senha", "Nivel de acesso", "Validade credenciais");
            System.out.println("|_________________________________________________________________________|");
            
            for(short i = 0; i < users.size(); i++){
                user = (Usuario) users.elementAt(i);
               // Usuario.updateCredenciasUsers(user);//actualiza as credenciais do usuario
                System.out.printf("|%3s|%6s|%18s|%5s|%15s|%20s|\n", (i + 1),user.getCodigo(), user.dadosPessoais().getNome(), user.getSenha(),
                user.getnivelAcesso().getAcesso(),user.validadeCredenciais());
            }
            System.out.println("|_________________________________________________________________________|");
            System.out.println("");
            
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * visualiza certos dados do usuario em forma de tabela
     * @param user 
     */
    public static void visualizaUsuario(Usuario user){
         try{
            System.out.println("");
            System.out.println("______________ DADOS DO UTILIZADOR ________________________");
            System.out.printf("|%6s|%18s|%15s|\n", "Codigo", "Nome do Usuario", "Nivel de acesso");
            System.out.println("|______________________________________________________________|");
            if(user!= null){
                System.out.printf("|%6s|%18s|%15s|\n", user.getCodigo(), user.dadosPessoais().getNome(), user.getnivelAcesso().getAcesso());
            }
            System.out.println("|______________________________________________________________|");
            System.out.println("");
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.getMessage();
        }
    }
    
    /**
     * Visualiza a lista de produtos para compra
     * a sua visualizacao nao inclui o stock, a quantidade vendida
     * e o fabricante
     * @param listaCompra
     * @param totalCompra 
     */
    public static void visualizaProdutos(Vector listaCompra,float totalCompra){
        
        try{
            DecimalFormat moeda = new DecimalFormat("###,###.00 Mt");
            System.out.println("");
            System.out.println("_______________________________________________________________________________________________________________________________________");
            System.out.println("TOTAL: " + moeda.format(totalCompra));//imprime o total
            System.out.println("");
            System.out.println("_______________________________________________________________________________________________________________________________________");
            System.out.printf("|%-3s|%-6s|%-25s|%-20s|%-10s|%-14s|%-35s|%-10s|\n","Nr","Ref","Nome","Marca","Validade","Preco unitario","Descricao","Quantidade");
            System.out.println("_______________________________________________________________________________________________________________________________________");

            for(short i = 0; i < listaCompra.size(); i++){
                    printDadosCaso((ItemVendido) listaCompra.elementAt(i),(byte) 1, i);
            }
            System.out.println("");
            
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Visualiza os produtos sem incluir o stock e a quantidade vendida
     * @param Produtos 
     */
    public static void visualizaProdutos(Vector Produtos){
        try{
            System.out.println("");
            System.out.println("______________________________________________________DADOS DO PRODUTO____________________________________________________________________________________");
            System.out.printf("|%-3s|%-6s|%-20s|%-20s|%-30s|%-14s|%-10s|%-10s|%-15s|%-20s|%-20s|\n","Nr","Ref","Nome","Marca","Descricao",
                    "Preco unitario","Unidade","Validade","Dentro do prazo","Categoria","Fabricante");
            System.out.println("_________________________________________________________________________________________________________________________________________________________");
           
            for(short i = 0; i < Produtos.size(); i++){
                printDadosCaso((Produto) Produtos.elementAt(i),(byte) 2,i);
            }
            System.out.println("");
       
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
        /**
     * Visualiza o produto sem incluir o stock e a quantidade vendida
     * @param produto 
     */
    public static void visualizaProduto(Produto produto){
        try{
            System.out.println("");
            System.out.println("______________________________________________________DADOS DO PRODUTO____________________________________________________________________________________");
            System.out.printf("|%-3s|%-6s|%-20s|%-20s|%-30s|%-14s|%-10s|%-10s|%-15s|%-20s|%-20s|\n","Nr","Ref","Nome","Marca","Descricao",
                    "Preco unitario","Unidade","Validade","Dentro do prazo","Categoria","Fabricante");
            System.out.println("_________________________________________________________________________________________________________________________________________________________");
           
                printDadosCaso((Produto) produto,(byte) 2,(byte) 1);
            System.out.println("");
       
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * visualiza todos os dados dos produtos
     * @param produtos 
     */
    public static void visualizaProdutosComplete(Vector produtos){
        
        try{
            System.out.println("");
            System.out.println("______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
            System.out.printf("|%-3s|%-6s|%-25s|%-20s|%-35s|%-14s|%-10s|%-10s|%-20s|%-20s|%-5s|%-11s|%-15s|%-16s|\n","Nr","Ref","Nome","Marca","Descricao",
                    "Preco unitario","Unidade","Validade","Categoria","Fabricante","Stock","Qtd vendida","Dentro do prazo","Comentarios pen.");
            System.out.println("______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
            
            for(short i = 0; i < produtos.size(); i++){
                printDadosCaso((Produto)produtos.elementAt(i),(byte) 3, i);
            }
            System.out.println("");
       
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
             System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * visualiza os detalhes de uma compra
     * Na visualizacao de produtos nao e incluido stock, a quantidade vendida e o fabricante
     * @param listaCompras
     * @param compra
     * @param totalCompra 
     */
    public static void visualizaProdutos(Vector listaCompras,RegistoVendas compra, float totalCompra){
        
        try{
            DecimalFormat moeda = new DecimalFormat("###,###.00 Mt");
            System.out.println("");
            System.out.println("_________________________________________DETALHES DA COMPRA_____________________________________________________________________________________________");
            System.out.println("Codigo da compra: " + compra.getCodigoCompra());
            System.out.println("Valor entregue: " + compra.getValorEntregue());
            System.out.println("Total da compra: " + moeda.format(totalCompra));//imprime o total
            System.out.println("_________________________________________________________________________________________________________________________________________________________");
            System.out.printf("|%-3s|%-6s|%-20s|%-20s|%-10s|%-14s|%-10s|%-30s|%-10s|\n","Nr","Ref","Nome","Marca","Validade","Preco unitario","Unidade","Descricao","Quantidade");
             System.out.println("_________________________________________________________________________________________________________________________________________________________");
            
            for(short i = 0; i < listaCompras.size(); i++){
                printDadosCaso((ItemVendido) listaCompras.elementAt(i),(byte) 1, i);
            }
            System.out.println("");
        
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Visualiza os registos das compras
     * @param arrayRegistoVendas 
     */
    public static void visualizaRegistoVendas(Vector arrayRegistoVendas){
        try{
            
            System.out.println("");
            System.out.println("________________________REGISTO DE COMPRAS_________________________________________");
            System.out.printf("|%-3s|%-11s|%-19s|%-20s|%-20s|\n","Nr","Cod. Compra","Data","Funcionario","Cliente");
            System.out.println("____________________________________________________________________________________");

            for(short i = 0; i < arrayRegistoVendas.size(); i++){//imprime somente o codigo da compra, a data, o nome do funcionario e do cliente
                printDadosCaso((RegistoVendas) arrayRegistoVendas.elementAt(i),(byte) 4,i);
            }
            System.out.println("");
                
        }catch(NullPointerException | ArrayIndexOutOfBoundsException | NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Imprime os detalhes de uma compra
     * @param arrayRegistoVendas 
     */
    public static void printDetalhesVenda(Vector arrayRegistoVendas){
        
        try{
            RegistoVendas venda = null;
            String dado = Validacao.intervaloOpcoes("Escolha a compra: ", (byte) 1, (byte) arrayRegistoVendas.size(), false);

            for(short i = 0; i < arrayRegistoVendas.size(); i++){

                if((Byte.parseByte(dado) - 1) == i){// se o indice coincide com o numero introduzido
                    venda = (RegistoVendas) arrayRegistoVendas.elementAt(i);
                    break;
                }
            }
            TabelasVisualizacoes.visualizaProdutos(venda.getListaCompras(),venda,VendaProdutos.calcTotal(venda.getListaCompras()));//visualiza os produtos da lista
            
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Visualiza os produtos com comentarios pendentes. Isto e, produtos
     * que tem comentarios ainda por serem visualizados pelo gerente.
     * @param produtosComentados 
     */
    public static void visualizaProdutosComentados(Vector produtosComentados){
        
        try{
            System.out.println("");
            System.out.println("________________________PRODUTOS COM COMENTARIOS_____________________");
            System.out.printf("|%-3s|%-6s|%-20s|%-21s|\n","Nr","Ref","Nome","Comentarios pendentes");
            System.out.println("_____________________________________________________________________");
            
            for(short i = 0; i < produtosComentados.size(); i++){               
               printDadosCaso((Produto) produtosComentados.elementAt(i),(byte) 6, i);
            }
            System.out.println("");
            
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Visualiza os comentarios
     * @param comentarios 
     */
    public static void printComentarios(Vector comentarios){

        try{
            System.out.println("____________________________________________________________________________COMENTARIOS_______________________________________________________________________");
            System.out.printf("|%-3s|%-6s|%-50s|%-20s|%-22s|%-20s|%-20s|%-20s|\n","Nr","Codigo","Comentario","Estado do comentario","Freq. de visualizacao", "Comentado por", 
                    "Data","Produto");
            System.out.println("_______________________________________________________________________________________________________________________________________________________________");
                
            for(byte i = 0; i < comentarios.size(); i++ ){
                printDadosCaso((Comentario) comentarios.elementAt(i),(byte) 5,i);
            }
            System.out.println("");
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Imprime um comentario
     * @param comentario 
     */
    public static void printComentario(Comentario comentario){
        
        try{
            System.out.println("______________________________________________________________________________________________________________________________________________________");
            System.out.printf("|%-3s|%-6s|%-50s|%-20s|%-20s|%-20s|%-20s|%-20s|\n","Nr","Codigo","Comentario","Estado do comentario","Freq. de visualizacao", "Comentado por", 
                    "Data","Produto");
            System.out.println("______________________________________________________________________________________________________________________________________________________");
            
            printDadosCaso(comentario,(byte) 5,(byte) 1);
            
            System.out.println("");
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Ex: Se o tamanho duma String for 62 enquanto a formatacao da tabela so permite 50 
      caracteres, a string sera dividida em conjuntos de 50 caracteres. Ex:
        |O meu pai e|
        |um excelent|
        |e jogador  |
     * @param iteracao ajuda na decisao de que substring retornar, pois esta segue o numero de linhas em que a string
     * esta dividida
     * @param formatSize o tamanho do formato
     * @param string a string
     * @return a substring dessa string
     */
    private static String printString(byte iteracao, byte formatSize, String string){
        byte numLinhasCampo;//recebe o numero de linhas para cada campo
        int min = 0,max = 0;
        
        try{
            iteracao += 1;
            numLinhasCampo =  (byte) Math.ceil((double) string.length() / formatSize);
            
            if(maiorLinhas < numLinhasCampo){//actualiza o numero de linhas ate chegar ao maior numero de linhas
                maiorLinhas = numLinhasCampo;
            }
        
            if(iteracao > numLinhasCampo){//retorna uma string vazia se o numero de linhas para essa string for menor que a iteracao
                return string.substring(0,0);
            }else{
                max = formatSize * (iteracao);//recebe o limite da substring
                min = max - formatSize;

                if(iteracao == numLinhasCampo){//torna-se util na ultima linha, assim nao excede o tamanho da string
                    return string.substring(min,string.length());
                }
            }
            
        }catch(StringIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
       return  string.substring(min,max);
    }
    
    /**
     * Imprime os dados do objecto segundo o caso.
     * @param objecto
     * @param caso
     * @param iteracao 
     */
    private static void printDadosCaso(Object objecto, byte caso, short iteracao){
        ItemVendido item; Produto produto; RegistoVendas regVenda;
        Comentario comentario;
        
        try{
            if(objecto != null){
                
                switch(caso){
                    case 1:
                        item = (ItemVendido) objecto;
                        formatacao = "|%-3s|%-6s|%-20s|%-20s|%-10s|%-14s|%-10s|%-30s|%-10s|\n";
                        sizesArray(formatacao);

                        for(byte i = 0; i < maiorLinhas; i++){
                             System.out.printf(formatacao,printString(i,sizes[0],String.valueOf((iteracao + 1))),
                                                          printString(i,sizes[1],item.getProduto().dadosProduto().getReferencia()),
                                                          printString(i,sizes[2],item.getProduto().dadosProduto().getNome()),
                                                          printString(i,sizes[3],item.getProduto().dadosProduto().getMarca()),
                                                          printString(i,sizes[4],item.getProduto().dadosProduto().getValidade()),
                                                          printString(i,sizes[5],String.valueOf(item.getProduto().dadosProduto().getPreco())),
                                                          printString(i,sizes[6],item.getProduto().dadosProduto().getUnidade()),
                                                          printString(i,sizes[7],item.getProduto().dadosProduto().getDescricao()),
                                                          printString(i,sizes[8],String.valueOf(item.getQuantidade())));
                        }
                        break;
                    case 2:
                        produto = (Produto) objecto;
                        formatacao = "|%-3s|%-6s|%-20s|%-20s|%-30s|%-14s|%-10s|%-10s|%-15s|%-20s|%-20s|"; 
                        sizesArray(formatacao);

                        for(byte i = 0; i < maiorLinhas; i++){
                            System.out.printf(formatacao,printString(i,sizes[0],String.valueOf((iteracao + 1))),
                                                         printString(i,sizes[1],produto.dadosProduto().getReferencia()),
                                                         printString(i,sizes[2],produto.dadosProduto().getNome()),
                                                         printString(i,sizes[3],produto.dadosProduto().getMarca()),
                                                         printString(i,sizes[4],produto.dadosProduto().getDescricao()),
                                                         printString(i,sizes[5],String.valueOf(produto.dadosProduto().getPreco())),
                                                         printString(i,sizes[6],produto.dadosProduto().getUnidade()),
                                                         printString(i,sizes[7],produto.dadosProduto().getValidade()),
                                                         printString(i,sizes[8],produto.retornaEstadoProduto()),
                                                         printString(i,sizes[9],produto.getCategoria()),
                                                         printString(i,sizes[10],produto.dadosProduto().getFabricante()));
                            }
                        break;
                    case 3:
                        produto = (Produto) objecto;
                        formatacao = "|%-3s|%-6s|%-25s|%-20s|%-35s|%-14s|%-10s|%-10s|%-20s|%-20s|%-5s|%-11s|%-15s|%-16s|\n";
                        sizesArray(formatacao);

                        for(byte i = 0; i < maiorLinhas; i++){
                            System.out.printf(formatacao,printString(i,sizes[0],String.valueOf((iteracao + 1))),
                                                         printString(i,sizes[1],produto.dadosProduto().getReferencia()),
                                                         printString(i,sizes[2],produto.dadosProduto().getNome()),
                                                         printString(i,sizes[3],produto.dadosProduto().getMarca()),
                                                         printString(i,sizes[4],produto.dadosProduto().getDescricao()),
                                                         printString(i,sizes[5],String.valueOf(produto.dadosProduto().getPreco())),
                                                         printString(i,sizes[6],produto.dadosProduto().getUnidade()),
                                                         printString(i,sizes[7],produto.dadosProduto().getValidade()),
                                                         printString(i,sizes[8],produto.getCategoria()),
                                                         printString(i,sizes[9],produto.dadosProduto().getFabricante()),
                                                         printString(i,sizes[10],String.valueOf(produto.dadosProduto().getStock())),
                                                         printString(i,sizes[11],String.valueOf(produto.dadosProduto().getQtdVendida())),
                                                         printString(i,sizes[12],produto.retornaEstadoProduto()),
                                                         printString(i,sizes[13],String.valueOf(produto.getComentariosPendentes())));
                        }
                        break;
                    case 4:
                        regVenda = (RegistoVendas) objecto;
                        formatacao = "|%-3s|%-11s|%-19s|%-20s|%-20s|\n";
                        sizesArray(formatacao);

                        for(byte i = 0; i < maiorLinhas; i++){
                            System.out.printf(formatacao,printString(i,sizes[0],String.valueOf((iteracao + 1))),
                                                         printString(i,sizes[1],String.valueOf(regVenda.getCodigoCompra())),
                                                         printString(i,sizes[2],FuncoesTempo.formatarData(regVenda.getDataCompra())),
                                                         printString(i,sizes[3],regVenda.getNomeFuncionario()),
                                                         printString(i,sizes[4],regVenda.getNomeCliente()));
                        }
                        break;
                    case 5:
                        comentario = (Comentario) objecto;
                        formatacao = "|%-3s|%-6s|%-50s|%-20s|%-22s|%-20s|%-20s|%-20s|\n";
                        sizesArray(formatacao);

                        for(byte i = 0; i < maiorLinhas; i++){
                            System.out.printf(formatacao, printString(i,sizes[0],String.valueOf(iteracao + 1)),
                                                          printString(i,sizes[1],comentario.getCodigoComentario()),
                                                          printString(i,sizes[2],comentario.getComentario()),
                                                          printString(i,sizes[3],comentario.getEstadoComentario()),
                                                          printString(i,sizes[4],comentario.getFrequenciaVisualizacao()),
                                                          printString(i,sizes[5],comentario.getUsuario().dadosPessoais().getNome()),
                                                          printString(i,sizes[6],FuncoesTempo.formatarData(comentario.getData())),
                                                          printString(i,sizes[7],comentario.getProduto().dadosProduto().getNome()));
                        }
                        break;
                    case 6:
                        produto = (Produto) objecto;
                        formatacao = "|%-3s|%-6s|%-20s|%-21s|\n";
                        sizesArray(formatacao);
                        
                        for(byte i = 0; i < maiorLinhas; i++){
                            System.out.printf(formatacao, printString(i,sizes[0],String.valueOf(iteracao + 1)),
                                                          printString(i,sizes[1],produto.dadosProduto().getReferencia()),
                                                          printString(i,sizes[2],produto.dadosProduto().getNome()),
                                                          printString(i,sizes[3],String.valueOf(produto.getComentariosPendentes())));
                        }
                        break;
                }
                maiorLinhas = 1;
                separador();
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Retorna o array com os tamanhos de cada campo da formatacao
     * @param formatacao 
     */
    private static void sizesArray(String formatacao){
        formatacao = formatacao.substring(1, formatacao.length() - 2);
        formatacao = formatacao.replace("%-","").replace("s","").replace("|", ";");
        String aux[] = formatacao.split(";");
        formatacao += "\n";
        sizes = new byte[aux.length];
        
        for(byte i = 0; i < aux.length; i++){
            sizes[i] = Byte.valueOf(aux[i]);
        }
    }
    
    /**
     * Imprime a lnha separadora
     */
    private static void separador(){
        int soma = 0;
        int soma1 = sizes.length;
        
        for(byte i = 0; i < sizes.length; i++){
            soma += sizes[i];
        }
        soma += soma1;
        
        for(short i = 0; i < soma - 1; i++){
            System.out.print("-");
        }
        System.out.print("-\n");
    }
}

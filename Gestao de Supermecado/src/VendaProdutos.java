import java.io.*;
import java.text.DecimalFormat;
import java.util.Vector;


/*classe responsavel pela venda de produtos*/
/*nivel de acesso: gerente e vendedor*/

/**
 * A variavel listaCompras armazena a lista dos produtos e a variavel
 * listaProdutos armazena as referencias dos produtos
 * @
 */
public class VendaProdutos extends OperacoesProduto{
    
    private float totalCompra;
    
    /**
     *
     */
    public VendaProdutos(){
    }
    
    /**
     *
     * @param usuario
     */
    public void menuVendas(Usuario usuario){
        String auxDado;
        
        do{
            System.out.println("");
            System.out.println("____________________VENDA DE PRODUTOS______________________");
            System.out.println("");
                
            auxDado = Validacao.intervaloOpcoes((byte) 11, (byte) 1, (byte) 4, true);
            if(!auxDado.equalsIgnoreCase("s")){
                switch(Byte.parseByte(auxDado)){
                    case 1: 
                        /*
                            Na compra de produtos, e necessario ter as referencias dos produtos escritos num papel ou algo de genero
                            de modo a inserir as referencias. Esse metodo esta adaptado a compra de produtos na vida real. onde nao se teria
                            uma lista com os produtos, mas sim, os produtos na mesa do caixa 
                            */
                        compraProdutos(usuario);
                        break;
                    case 2: 
                        OperacoesComentarios operacao = new OperacoesComentarios();
                        operacao.comentarSobreProduto(usuario);
                        break;
                    case 3:
                        procuraProdutos();
                        break;
                    case 4:
                        if(usuario.getnivelAcesso().getAcesso().equals("Vendedor")){
                            System.out.println("\n\nAcesso interdito. Nao tem acesso a essa classe!");
                        }else{
                            RegistoVendas regCompras = new RegistoVendas();
                            regCompras.visualizaRegistoVendas();
                        }
                        break;
                }
            }
        }while(!auxDado.equalsIgnoreCase("s"));
    }
    
    //metodo responsavel pela compra de produtos por parte do cliente
    private void compraProdutos(Usuario usuario){
        String dado;
        float valor;
       
        
        try{
            Vector listaCompras = preencheListaProdutos(new Vector());//array que armazena os itens da lista de compra
            
            do{
                System.out.println("");
                System.out.println("____________________PREENCHIMENTO DA LISTA DA VENDA______________________");
                System.out.println("");
                
                visualizaLista(listaCompras);//visualiza a lista de produtos
                dado = Validacao.intervaloOpcoes((byte) 12, (byte) 1, (byte) 5, false);
                                    
                switch(Byte.parseByte(dado)){
                    case 1: 
                        listaCompras = adicionarItens(listaCompras);
                        break;
                    case 2: 
                        listaCompras = anularLista(listaCompras);
                        break;
                    case 3: 
                        listaCompras = alteraQtd(listaCompras);
                        break;
                    case 4: 
                        listaCompras = removerProdutos(listaCompras);
                        break;
                    case 5:
                        System.out.print("Insira '0' caso queira sair");
                        valor = Float.parseFloat(Validacao.Float("Valor entregue: ", false));

                        if(valor != 0){
                            RegistoVendas compra = new RegistoVendas();
                            compra.registaCompras(listaCompras,totalCompra,valor,usuario);//guarda a compra no ficheiro
                        }
                        System.out.println("Terminando...");
                        break;
                }
            }while((Byte.parseByte(dado) != 5 && Byte.parseByte(dado) != 2));
            
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.getMessage();
        }catch(ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.getMessage();
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.getMessage();
        }
    }
    
    /**
     * @retorna a lista de produtos 
     */
    private Vector preencheListaProdutos(Vector ListaCompra){
       String dado = "";
       
       try{
            do{
                 System.out.print(Mensagens.sugestiva((byte) 1));
                 dado = Validacao.regex("QtdXreferencia", "Introduza os produtos(QtdxNrUnico),ex: 2xElA00:  ");
                 if(dado.isEmpty()){//para sair da insercao de referencias so tem de introduzir uma string vazia
                     break;
                 }else{
                    ListaCompra = checkPrazoEStock(ListaCompra,dado);
                 }
            }while(!dado.isEmpty());
       }catch(NumberFormatException ex){
           System.err.println(ex.getMessage());
           ex.printStackTrace();
       }catch(NullPointerException ex){
           System.err.println(ex.getMessage());
           ex.printStackTrace();
       }
       return ListaCompra;
    }
    
    /**
     * verifica o stock e o prazo deste produto. Caso tudo esteja certo, adiciona o produto ao array de compras
     * @param listaCompra
     * @param dado
     * @return vector com os itens para compra
     */
    private Vector checkPrazoEStock(Vector listaCompra,String dado){
        
        String resp = "", mensagem = "", codigosPermissao = "";
        boolean entraNaListaCompra = false;
        Produto produto = produtoRetorna(retornaReferencia(dado));
       
        printComentarios(produto);
        
        if(produto.getDentroPrazo() == false || produto.dadosProduto().getStock() == 0){//se o produto estiver fora do prazo ou sem stock
        
            if(produto.getDentroPrazo() == false ){
                mensagem += "O produto " + produto.dadosProduto().getReferencia() + " esta fora do prazo!!! ";
                codigosPermissao += "CpPe1,";
            }
            
            if(produto.dadosProduto().getStock() == 0){
                mensagem += "O stock do produto " + produto.dadosProduto().getReferencia()+ " e 0 !!! ";
                codigosPermissao += "CpPs1,";
            }

            mensagem += " Se este nao for o caso peca a um gerente para confirmar a venda.";
            Mensagens.printMensagemNotavel(mensagem, (byte) 40);
            
            resp = Validacao.intervaloOpcoes((byte) 28, (byte) 1, (byte) 2, false);

            if(Byte.parseByte(resp) == 1){

                OperacoesNivelAcesso operacaoAcesso = new OperacoesNivelAcesso();
                //O gerente tem de conceder a permissao para a venda deste produto caso ele nao passe pela validacao do prazo e do stock
                entraNaListaCompra = operacaoAcesso.concedePermissao(codigosPermissao.substring(0, codigosPermissao.length() - 1), "venda");
            }
        }else{
            entraNaListaCompra = true;
        }
        
        if(entraNaListaCompra == true){
            listaCompra.addElement(new ItemVendido(produto, Integer.parseInt(retornaQuantidade(dado))));
            listaCompra.trimToSize();
        }
        
        return listaCompra;
    }
    
    /**
     * Visualiza os comentarios que devem ser mostrados a todos, se este o tiver
     * @param produto 
     */
    private void printComentarios(Produto produto){
        Vector comentarios = produto.getComentarios(), comentariosAux = new Vector();
        Comentario comentario;
        
        try{
            if(!comentarios.isEmpty()){
                
                for(byte i = 0; i < comentarios.size(); i++){
                    comentario = (Comentario) comentarios.elementAt(i);

                    if(comentario.getFrequenciaVisualizacao().equalsIgnoreCase("Sempre")){
                        comentariosAux.addElement(comentario);
                        comentariosAux.trimToSize();
                    }
                }

                if(!comentariosAux.isEmpty()){

                    for(byte i = 0; i < comentariosAux.size(); i++){
                        comentario = (Comentario) comentariosAux.elementAt(i);
                        Mensagens.printMensagemNotavel(comentario.getComentario(), (byte) 50);
                    }
                }
            }
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * retorna a referencia do produto em especifico
     * @param quantReferencia
     * @return 
     */
    public static String retornaReferencia(String quantReferencia){
        return quantReferencia.substring(quantReferencia.toLowerCase().indexOf("x") + 1);
    }
    
    /**
     * retorna a referencia do produto em especifico
     * @param quantReferencia
     * @return 
     */
    public static String retornaQuantidade(String quantReferencia){
        return quantReferencia.substring(0,quantReferencia.toLowerCase().indexOf("x"));
    }
    
    /**
     * adiciona itens a lista actual
     * @param lista
     * @return Um vector com os novos itens adicionados
     */
    private Vector adicionarItens(Vector lista){
        visualizaLista(lista);
        Vector listaCompras = preencheListaProdutos(lista);
        
        return listaCompras;
    }
    
    /**
     * Anula a lista
     * @param lista
     * @return 
     */
    private Vector anularLista(Vector lista){
        String dado;
        dado = Validacao.intervaloOpcoes((byte) 13, (byte) 1, (byte) 2, false);
        if(Byte.parseByte(dado) == 1){
            return null;
        }else{
            return lista;
        }
    }
    
    //altera a quantidade de um determinado produto
    private Vector alteraQtd(Vector listaCompras){
        String dado;
        int novaQtd;
        visualizaLista(listaCompras);
        
        do{
            dado = Validacao.Digitos("Escolha o produto: ", false);
        }while(Byte.parseByte(dado) < 1 || Byte.parseByte(dado) > listaCompras.size());//garante que nao escoha um produto fora dos limites
        
        ItemVendido item = (ItemVendido) listaCompras.elementAt(Byte.parseByte(dado) - 1);//recebe o produto
        novaQtd = Integer.parseInt(Validacao.Digitos("Quantidade: ", false));
        item.setQuantidade(novaQtd);//troca a quantidade anterior pela nova
        listaCompras.set(Byte.parseByte(dado) - 1,item);
        
        return listaCompras;
    }
    
    //remove itens da lista actual
    private Vector removerProdutos(Vector lista){
        visualizaLista(lista);
        Vector numRemove = new Vector();
        String dado;
        try{
            do{
                do{
                    System.out.print(Mensagens.sugestiva((byte) 2));
                    dado = Validacao.leituraSemValidacao("Introduza o numero do produto: ");
                    if(dado.isEmpty()){
                        break;
                    }else{
                        numRemove.addElement(new Integer(dado));//recebe o indice do produto a remover
                        numRemove.trimToSize();
                    }
                }while(!dado.isEmpty());
                /*if(numRemove.length == lista.length){
                    System.out.print("A lista dos produtos a remover e a mesma com a lista de compras. Deseja anula");
                }*/
            }while(numRemove.size() > lista.size());//garante que nao escolha todos os produtos
            
            for(byte i = 0; i < numRemove.size(); i++){
                numRemove.set(i, lista.elementAt((Integer) numRemove.elementAt(i) - 1));//recebe os produtos por se remove
            }
            lista.removeAll(numRemove);//remove os produtos
            
        }catch(NumberFormatException | NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }
    
    //visualiza os produtos da lista
    private void visualizaLista(Vector listaCompra){
        try{
            
            totalCompra = calcTotal(listaCompra);
            TabelasVisualizacoes.visualizaProdutos(listaCompra,totalCompra);//visualiza os produtos da lista
            
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
        
    //calcula o total dos produtos listados

    /**
     *
     * @param aux
     * @return
     */
        public static float calcTotal(Vector aux){
        ItemVendido item;
        float total = 0;
        
        for(byte i = 0; i < aux.size(); i++){
            item = (ItemVendido) aux.elementAt(i);
            total += (item.getProduto().dadosProduto().getPreco() * item.getQuantidade());//multiplica o preco pela quantidade e depois soma ao total
        }
        
        return total;
    }
    
}

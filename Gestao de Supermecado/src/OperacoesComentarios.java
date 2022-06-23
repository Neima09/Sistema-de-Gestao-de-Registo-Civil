
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
public class OperacoesComentarios extends Comentario{
    
    /**
     *
     */
    public OperacoesComentarios(){
    }
    
    /**
     *
     * @param usuario
     */
    public void menuComentariosSobreProdutos(Usuario usuario){
        String opcao = "";
        try{
            do{
                opcao = Validacao.intervaloOpcoes((byte) 33, (byte) 1, (byte) 4, true);
                
                if(!opcao.equalsIgnoreCase("s")){
                    
                    if(Login.loginClassesChecker(usuario, (byte) 33, opcao)){
                        retornaProdutosPendentes();//actualiza o numero de comentarios pendentes nos produtos

                        switch(Byte.parseByte(opcao)){
                            case 1:
                                comentarSobreProduto(usuario);
                                break;
                            case 2:
                                removerComentarios();
                                break;
                            case 3:
                                verComentariosPendentes();
                                break;
                            case 4:
                                verMeusComentarios(usuario);
                                break;
                        }
                    }
                }
            }while(!opcao.equalsIgnoreCase("s"));
            
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Faz um comentario sobre um produto
     * @param usuario 
     */
    public void comentarSobreProduto(Usuario usuario){
        String resp = "";
        Vector comentarios;
        try{
            System.out.println("");
            System.out.println("____________________COMENTARIO SOBRE PRODUTO______________________");
            System.out.println("");
                
            Produto produto = new Produto();
            OperacoesProduto operacao = new OperacoesProduto();
            
            do{
                produto = operacao.buscaProduto();//recebe o produto
                produto.visualizaDados();
                resp = Validacao.intervaloOpcoes((byte) 6, (byte) 1, (byte) 2, true);
            }while(Byte.parseByte(resp) == 2 && !resp.equalsIgnoreCase("s"));
            
            if(!resp.equalsIgnoreCase("s")){
                comentarios = produto.getComentarios();//recebe os comentarios do produto
                comentarios.addElement(new Comentario(usuario,produto));//cria um novo comentario
                comentarios.trimToSize();
                produto.setComentarios(comentarios);//guarda o novo comentario no produto
                produto.guardaNoFicheiro();//guarda o produto nos ficheiro
            }
            
        }catch(NumberFormatException | NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
        
    //remove comentarios
    private void removerComentarios(){
        String dado = "";
        Comentario comentario = new Comentario();
        
        System.out.println("");
        System.out.println("____________________REMOCAO DE COMENTARIOS______________________");
        System.out.println("");
        
        try{
            dado = Validacao.intervaloOpcoes((byte) 35, (byte) 1, (byte) 3, true);
            
            if(dado.equalsIgnoreCase("s")){
                
                switch(Byte.valueOf(dado)){
                    case 1:
                        comentario = escolheComentario(retornaProdutosComentados(),"produtos");
                        break;
                    case 2:
                        comentario = buscaComentario();
                        break;
                    case 3:
                        OperacoesProduto operacao = new OperacoesProduto();
                        comentario = escolheComentario(comentarios(operacao.buscaProduto()),"comentarios");
                        break;
                }
                TabelasVisualizacoes.printComentario(comentario);
                System.out.println("Deseja continuar com a remocao do comentario " + comentario.getCodigoComentario());
                dado = Validacao.intervaloOpcoes((byte) 29, (byte) 1, (byte) 2, false);

                if(Byte.valueOf(dado) == 1){
                    removeComentario(comentario);
                }
            }
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Remove o comentario do array de comentarios e do respectivo produto
     * @param comentario 
     */
    private void removeComentario(Comentario comentario){
        Vector comentarios = Ficheiros.leituraFicheiro("comentarios");
        Produto produto;
        
        for(byte i = 0; i < comentarios.size(); i++){
            Comentario com = (Comentario) comentarios.elementAt(i);
            
            if(com.getCodigoComentario().equalsIgnoreCase(comentario.getCodigoComentario())){
                 comentarios.remove(com);//remove o comentario do vector comentarios
                 Ficheiros.toFicheiro(comentarios,"comentarios");
            }
        }
        
        comentarios = Ficheiros.Ficheirostxt("Codigos Comentarios");
        comentarios.remove(comentario.getCodigoComentario());//remove do vector dos codigos dos comentarios
        Ficheiros.toFicheiroTxt(comentarios, "Codigos Comentarios");
        
        produto = comentario.getProduto();
        comentarios = produto.getComentarios();
        comentarios.remove(comentario);//remove o comentario do vector de comentarios do respectivo produto
        produto.setComentarios(comentarios);
        
        produto.guardaNoFicheiro();
    }
    
    /**
     * Remove os comentarios dum produto dos ficheiros de comentarios
     * @param produto 
     */
    public  void removeComProduto(Produto produto){
        Vector comentarios = produto.getComentarios();
        
        if(!comentarios.isEmpty()){
            
            for(byte i = 0; i < comentarios.size(); i++){
                removeComentario((Comentario) comentarios.elementAt(i));
            }
        }
    }
    
    /**
     * Actuliza os comentarios no ficheiro destes com os novos comentarios
     * @param comments 
     */
    private void actualizaComentarios(Vector comments){
        try{
            Comentario com,comAux;
            Vector comentarios = Ficheiros.leituraFicheiro("comentarios");
            
            for(byte i = 0; i < comments.size(); i++){
                com = (Comentario) comments.elementAt(i);
                
                for(byte j = 0; j < comentarios.size(); j++){
                    comAux = (Comentario) comentarios.elementAt(j);
                    
                    if(com.getCodigoComentario().equalsIgnoreCase(comAux.getCodigoComentario())){
                        comentarios.set(j, com);
                        break;
                    }
                }
            }
            Ficheiros.toFicheiro(comentarios, "comentarios");
            
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    /**
     * Recebe um vector, seja de produtos ou comentarios, e retorna o respectivo comentario
     * @param vector
     * @param caso
     * @return um comentario
     */
    private Comentario escolheComentario(Vector vector, String caso){
        Produto produto;
        Comentario comentario = new Comentario();
        String index = "";
        try{
            switch(caso.toLowerCase()){
                case "produtos":
                    TabelasVisualizacoes.visualizaProdutosComentados(vector);
                    index = Validacao.Digitos("Escolha o produto: ", false);
                    produto = (Produto) vector.elementAt(Byte.valueOf(index) - 1);//recebe o produto
                    comentario = pickComentario(produto.getComentarios());
                    break;
                case "comentarios":
                    comentario = pickComentario(vector);
                    break;
            }
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return comentario;
    }
    
    /**
     * Escolhe um comentario
     * @param comentarios
     * @return um comentario
     */
    private Comentario pickComentario(Vector comentarios){
        String codigo;
        Comentario comment = new Comentario();
                    
        TabelasVisualizacoes.printComentarios(comentarios);
        codigo = Validacao.regex("Comentario","Introduza o codigo do comentario: ");
        
        for(short i = 0; i < comentarios.size(); i++){
            comment = (Comentario) comentarios.elementAt(i);
            
            if(codigo.equalsIgnoreCase(comment.getCodigoComentario())){
                return comment;
            }
        }
        
        return null;
    }
    /**
     * organiza os comentarios na ordem: pendentes, recentes, antigos
     * @param comentarios
     * @return os comentarios organizados
     */
    private Vector organizaComentarios(Vector comentarios){
        Vector comments = new Vector();
        Vector comAux = new Vector();
        Comentario comentario;
        try{
            for(short i = (short) (comentarios.size() - 1); i >= 0; i--){//organiza o vector na ordem do ultimo ao primeiro elemento
              comAux.addElement(comentarios.elementAt(i));
            }
            comAux.trimToSize();

            for(short i = 0; i < comAux.size(); i++ ){//preenche os comentarios pendentes 
                comentario = (Comentario) comAux.elementAt(i);

                if(comentario.getEstadoComentario().equalsIgnoreCase("Pendente")){
                    comments.addElement(comentario);
                    comments.trimToSize();
                }
            }
        
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        if(comments.isEmpty()){//Se nao tiver comentarios pendentes
            return comAux;
        }else{
            comAux.removeAll(comments);//remove todos comentarios pendentes do array comAux
            comments.addAll(comAux);//adiciona os comentarios ja organizados aos pendentes
            comments.trimToSize();
            
            return comments;
        }
    }
    
    /**
     * mostra os comentarios que ainda nao foram vistos pelo gerente
     * @param user 
     */
    private void verComentariosPendentes(){
        String dado = "";
        Produto produto;
        Vector comentarios;
        
        try{
            Vector produtosPendentes = retornaProdutosPendentes();

            if(produtosPendentes.isEmpty()){
                String str = "Nao ha Produtos com comentarios";
                Mensagens.printMensagemNotavel(str,(byte)str.length());
            }else{
                TabelasVisualizacoes.visualizaProdutosComentados(produtosPendentes);//visualiza os produtos com comentarios pendentes
                System.out.println(Mensagens.sugestiva((byte) 2));
                dado = Validacao.Digitos("Insira o nr do produto caso queira visualizar os seus comentarios: ", true);
                
                if(!dado.equalsIgnoreCase("s")){
                    produto = (Produto) produtosPendentes.elementAt(Byte.valueOf(dado) - 1);
                    comentarios = organizaComentarios(produto.getComentarios());//recebe os comentarios pendentes
                    
                    TabelasVisualizacoes.printComentarios(comentarios);//visualiza os comentarios
                   
                    comentarios = mudaEstadoComentario(comentarios);//muda os estados dos comentarios
                    
                    produto.setComentarios(comentarios);//guarda os comentarios no produto
                    produto.guardaNoFicheiro();
                    actualizaComentarios(comentarios);
                }
            }
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * recebe os comentarios do produto a serem marcados como vistos pelo gerente
     * @param comentarios
     * @return Um vector de  comentarios com o seu estado alterado para 'visto'
     */
    private Vector mudaEstadoComentario(Vector comentarios){
        String dado = "";
        Vector comPendentes = new Vector();
        Comentario comentario = new Comentario();
        
        do{
            do{
                System.out.print(Mensagens.sugestiva((byte) 1));
                System.out.println("Para marcar todos insira 'todos'.");
                dado = Validacao.leituraSemValidacao("Insira o nr do comentario para marca-lo como visto: ");

                if(dado.equalsIgnoreCase("todos")){
                    comPendentes.removeAllElements();

                    for(byte i = 0; i < comentarios.size(); i++){
                        comentario = (Comentario) comentarios.elementAt(i);

                        if(comentario.getEstadoComentario().equalsIgnoreCase("pendente")){
                            comPendentes.addElement(comentario);
                            comPendentes.trimToSize();
                        }
                    }
                }else{

                    if(!dado.isEmpty()){
                        comPendentes.addElement(comentarios.elementAt(Byte.valueOf(dado) - 1));
                        comPendentes.trimToSize();
                    }
                }
            }while(!dado.isEmpty() && !dado.equalsIgnoreCase("todos"));

            if(!comPendentes.isEmpty()){
                TabelasVisualizacoes.printComentarios(comPendentes);
                System.out.println("Marcar estes comentarios como vistos?");
                dado = Validacao.intervaloOpcoes((byte) 29, (byte) 1, (byte) 2, false);

                if(Byte.valueOf(dado) == 1){
                    return mudaEstadoAux(comPendentes,comentarios);
                }
            }
        }while(!dado.isEmpty() && (!dado.equalsIgnoreCase("3") || !dado.equalsIgnoreCase("2")));
        
        return comentarios;
    }
    
    //muda o estado do comentario
    private Vector mudaEstadoAux(Vector comPendentes, Vector comentarios){
        Comentario comAux,comentario;
        
        for(byte i = 0; i < comPendentes.size(); i++){
            comentario = (Comentario) comPendentes.elementAt(i);
            comentario.setEstadoComentario((byte) 2);//muda o estado do comentario
            
            for(byte j = 0; j < comentarios.size(); j++){
                comAux = (Comentario) comentarios.elementAt(j);
                
                if(comentario.getCodigoComentario().equalsIgnoreCase(comAux.getCodigoComentario())){//substitui o antigo comentario pelo modificado
                    comentarios.set(j, comentario);
                    break;
                }
            }
        }
        
        return comentarios;
    }
        
    /**
     * mostra os comentarios feitos por um usuario
     * @param user 
     */
    private void verMeusComentarios(Usuario user){
        Vector comentarios = Ficheiros.leituraFicheiro("comentarios"),
                 comAux = new Vector();
        Comentario comentario;
        
        try{
            
            for(byte i = 0; i < comentarios.size(); i++){
                comentario = (Comentario) comentarios.elementAt(i);
                
                if(comentario.getUsuario().dadosPessoais().getNome().equalsIgnoreCase(user.dadosPessoais().getNome())){
                    comAux.addElement(comentario);
                    comAux.trimToSize();
                }
                         
            }
            TabelasVisualizacoes.printComentarios(comAux);
            
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}

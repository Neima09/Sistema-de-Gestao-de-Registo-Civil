
import java.time.Year;
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
public class OperacoesUsuario{

    /**
     *
     */
    protected  Vector users;
    
    /**
     *
     */
    public OperacoesUsuario(){
    }
 
    /**
     *
     */
    protected void nivelAcesso(){
        OperacoesNivelAcesso operacao  = new OperacoesNivelAcesso();
        operacao.menuNivelAcesso();
    }
    
    /**
     *
     */
    protected void novoUsuario(){
         InserirNovoUsuario inserir = new InserirNovoUsuario();
         inserir.inserirNovoUser();
    }
    
       //altera os dados escolhidos

    /**
     *
     * @param user
     */
       protected void alteraDados(Usuario user){
        String dado,dado1;
        try{
            dado = Validacao.intervaloOpcoes("Quantos atributos pretende modificar? ", (byte) 1, (byte) 9, true);//recebe o nr de atributos por modificar
            if(!dado.equalsIgnoreCase("s")){
                dado1 = Validacao.opcoes(Byte.parseByte(dado), (byte) 3, true);//retorna as opcoes por se modificar
                
                if(!dado1.equalsIgnoreCase("s")){
                    
                    for(byte i = 0; i < dado1.length(); i++){
                        
                        switch(dado1.charAt(i)){
                            case '1':
                                user.dadosPessoais().setNome(Validacao.Letras("Nome: ", false));
                                break;
                            case '2':
                                user.dadosPessoais().setIdade(Byte.parseByte(Validacao.Letras("Idade: ", false)));break;
                            case '3':
                                user.dadosPessoais().estadoCivil();
                                break;
                            case '4':
                                user.dadosPessoais().setNascimento(Validacao.regex("Data nascimento", "Data de nascimento: "));
                                break;
                            case '5':
                                user.dadosPessoais().setBi(Validacao.regex("B.I", "B.I: "));
                                break;
                            case '6':
                                user.dadosPessoais().setContacto(Validacao.regex("Contacto", "Contacto: "));
                                break;
                            case '7':
                                user.dadosPessoais().setcontactoEmer(Validacao.regex("Contacto", "Contacto de emergencia: "));
                                break;
                            case '8':
                                user.dadosPessoais().setMorada(Validacao.leituraSemValidacao("Morada: "));
                                break;
                            case '9':
                                user.setNivelAcesso(new NivelAcesso());
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
        }catch(Exception ex){
            System.err.print(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
       //remove o usuario

    /**
     *
     */
       protected void removerUsuarios(){
       Vector users = Ficheiros.leituraFicheiro("usuarios");//recebe os usuarios
       Usuario user;
       String dado = "";
       try{
           user = verUsuarios();
           System.out.println("Pretende remover o usuario " + user.dadosPessoais().getNome() + ", " + "codigo " + user.getCodigo());
           dado = Validacao.intervaloOpcoes((byte) 29, (byte) 1, (byte) 2, false);
           
           if(Byte.parseByte(dado) == 1){
               users.remove(user);//remove o usuario 
               Ficheiros.toFicheiro(users,"usuarios");//guarda no ficheiro de usuarios
               System.out.println("O usuario " + user.getCodigo() + " foi removido com sucesso!");
           }
           
           
       }catch(NullPointerException ex){
           System.err.println(ex.getMessage());
           ex.printStackTrace();
       }catch(Exception ex){
           System.err.println(ex.getMessage());
           ex.printStackTrace();
       }
   }
    
   //procura  o utilizador e retorna

    /**
     *
     * @param codigo
     * @return
     */
        public static Usuario procuraUsuario(String codigo){
        Vector users = Ficheiros.leituraFicheiro("usuarios");//recebe os usuarios
        Usuario user;
        Usuario userAux = new Usuario();
        try{
            for(int i = 0; i < users.size(); i++){
                user = (Usuario) users.elementAt(i);

                if(codigo.equals(String.valueOf(user.getCodigo()))){
                    userAux = (Usuario) users.elementAt(i);
                    break;
                }
            }
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return userAux;
    }
    
     //visualiza os utilizadores

    /**
     *
     * @return
     */
       protected Usuario verUsuarios(){
        Usuario user = new Usuario();
        users = Ficheiros.leituraFicheiro("usuarios");//recebe os usuarios
        String dado = Validacao.intervaloOpcoes((byte) 24, (byte) 1, (byte) 2, false);
        try{

            switch(Byte.parseByte(dado)){
                case 1://introduzindo o codigo
                    dado = Validacao.regex("Codigo utilizador", "Codigo: ");
                    user = procuraUsuario(dado);//recebe o utilizador
                    user.visualizaDados();
                    break;
                case 2://procurando na lista
                   TabelasVisualizacoes.visualizaUsuarios(users);
                   dado = Validacao.intervaloOpcoes("Insira o nr do utlizador caso queira visualiza-lo: ", (byte) 1, (byte) users.size(), false);

                    user = procuraUsuario(retornaCodigo(Integer.parseInt(dado)));//recebe o utilizador
                    user.visualizaDados();
                   break;
           }
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(IndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return user;
   }
   
     //retorna o codigo baseando-se no indice do array
    private String retornaCodigo(int index){
        Usuario dado = (Usuario) users.elementAt(index - 1);
        return String.valueOf(dado.getCodigo());
    }
    
    //altera a senha do utitlizador

    /**
     *
     */
        protected void alteraSenhaUser(){
        Usuario user;
        try{
            user = verUsuarios();
            user.visualizaDados();
            String dado = Validacao.intervaloOpcoes((byte) 30, (byte) 1, (byte) 2, false);
        
            if(dado.equals("1")){
                user.atribuiSenha();
                user.visualizaDados();
                user.guardaNoFicheiro();
            }
            
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.getMessage();
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.getMessage();
        }
    }
    
     /**
     * Preenche uma string com os poderes escolhidos
     * @param nivelAcesso
     * @return os poderes escolhidos
     */
    public String poderesLista(String nivelAcesso){
        String opcoes = "",poderes = "";
        byte opcao = 0;
        try{
            
            switch(nivelAcesso){
                case "Vendedor"://caso vendedor
                    poderes = poderesFill("4678");
                    break;
                case "Gerente"://caso gerente
                    poderes = poderesFill("5");
                    break;
                case "Especial"://caso personalizado
                    do{
                        opcoes = Validacao.Digitos(poderesPick(), false);
                        poderes = poderesFill(opcoes);
                        System.out.println("\nPoderes = {" + poderes + "}\n");
                        opcao = Byte.parseByte(Validacao.intervaloOpcoes((byte) 6, (byte) 1, (byte) 2, false));
                
                    }while(opcao == 2);
                    break;
            }
            
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return poderes;
    }
    
    /**
     * Retorna a lista dos poderes complementares disponiveis 
     * @return 
     */
    private String poderesPick(){
        return "Escolha as poderes: ex:123\n" +
                "1. " + poderesFill("1") + "\n" +
                "2. " + poderesFill("2") + "\n" +
                "3. " + poderesFill("3") + "\n";
                
    }
    
    /**
     * Retorna os poderes escolhidos 
     * @param opcoes
     * @return os poderes
     */
    private String poderesFill(String opcoes){
        String poderes = "";
        
        for(byte i = 0; i < opcoes.length(); i++){
            switch(opcoes.charAt(i)){
                case '1': poderes += "Inserir novos produtos,";break;
                case '2': poderes += "Inserir novos usuarios,";break;
                case '3': poderes += "Inserir novas categorias,";break;
                case '4': poderes += "Venda de produtos,";break;
                case '5': poderes += "Todos poderes,";break;
                case '6': poderes += "Ver meus comentarios,";break;
                case '7': poderes += "Comentar sobre produto,";break;
                case '8': poderes += "Ver comentarios em produtos,";break;
            }
        }
        
        return poderes.substring(0,poderes.length() - 1);
    }


    //verifica a existencia do codigo

    /**
     *
     * @param codigo
     * @return
     */
        public static boolean verificaCodigo(String codigo){
        Vector codigosUser = Ficheiros.Ficheirostxt("Codigos dos utilizadores");//recebe os codigos dos usuarios
        
        try{
            
            if(Integer.parseInt(codigo) == 0){
                return true;
            }else{
                
                for(byte i = 0; i < codigosUser.size(); i++){
                    if(codigo.equals((String) codigosUser.elementAt(i))){
                        return true;//se o codigo existir
                    }
                }
            }
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        System.out.print("Codigo invalido!\nIntroduza novamente: ");
        return false;
    }
    
     /**
     * Actualiza as credenciais dos usuarios com acessos especiais
     * @param user 
     */
    public void updateCredenciasUsers(Usuario user){
            
            if(user.getnivelAcesso().getAcesso().contains("Especial") && !user.getPrazoCredenciais().equalsIgnoreCase("Indefinida")){
                // se o prazo das credenciais tiver terminado, entao vai atribuir o nivel de acesso anterior e limpar os respectivos campos
                if(user.tempoFaltaCredencias() < 0){
                    user.setNivelAcesso(user.getNivelAcessoAnterior());
                    user.setPrazoCredenciais("Indefinida");
                    user.setPoderes(poderesLista(user.getNivelAcessoAnterior().getAcesso()));
                }
                user.guardaNoFicheiro();
            }
    }    
    
    
}

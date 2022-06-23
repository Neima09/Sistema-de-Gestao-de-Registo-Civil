

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chidi Mieka
 */

//classe responsavel pelo login de usuarios
public class Login {
    
    private static Usuario usuario;
    
    /**
     *
     */
    public Login(){
    }
  
    //vai fazer o login do utilzador

    /**
     *
     */
        public void loginUser(){
        String dado;
        mensagemEntrada();
        //		AUTENTICACAO AO SISTEMA
        try{
                    
            if(insercaoDeCredenciais((byte) 1)){
                
                //inicializa as classes
                if(!usuario.getnivelAcesso().getAcesso().equalsIgnoreCase("Revogado")){
                    
                    do{
                        
                        dado = Validacao.intervaloOpcoes((byte) 26, (byte) 1, (byte) 2, true);
                        
                        if(!dado.equalsIgnoreCase("s")){
                            
                            switch(Byte.parseByte(dado)){
                                case 1:
                                    GestaoProdutos gestaoProdutos = new GestaoProdutos(usuario);
                                    gestaoProdutos.menuGestaoProdutos();
                                    break;
                                case 2:
                                    GestaoUsuarios gestaoUsuarios = new GestaoUsuarios();
                                    gestaoUsuarios.menuGestaoUsuarios(usuario);
                                    break;
                            }
                        }
                    }while(!dado.equalsIgnoreCase("s"));
                }else{
                    System.out.println("O seu acesso foi revogado!");
                }
            }
        }catch(NullPointerException ex){
            System.err.print(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.print(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    //insercao das credenciais

    /**
     *
     * @param opcao
     * @return
     */
        public boolean insercaoDeCredenciais(byte opcao){
        Usuario user = new Usuario();
        boolean checker = true;
        String codigo;
        int senha = 0;
        try{
            do{
                System.out.println("Insira '0' caso queira sair.");
                System.out.println("");
                codigo = Validacao.regex("Codigo utilizador","Codigo do usuario: ");//recebe o codigo

                if(Integer.parseInt(codigo) != 0){
                    user = OperacoesUsuario.procuraUsuario(codigo);

                    if(opcao == 2 && !user.getnivelAcesso().getAcesso().equalsIgnoreCase("Gerente")){
                       System.out.println("\nCredenciais de gerente invalidas!!!");
                       checker = false;
                    }else{
                        checker = true;
                    }
                }
            }while(!checker); 
            senha =  Integer.valueOf(codigo) != 0 ? verificaSenha(user) : 0;// le e verifica a senha

            if(opcao == 1){
                usuario = user;
            } 

            if(senha != 0){
                visualizaUser(user);

                return true;
            }
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return false;
    }
    
    //visualiza a mensagem de entrada
    private void mensagemEntrada(){
        System.out.println("\n****************         LOGIN         ********************");
        System.out.println("*                  ACESSO AO SISTEMA                      *");
        System.out.println("*    Por Favor, Digite o NOME DO USUARIO e a SENHA:       *");
        System.out.println("*_________________________________________________________*");
    }
    
    //visualiza o usuario
    private void visualizaUser(Usuario usuario){
        System.out.println("");
        System.out.println("________________________________________________________________");
        System.out.println("Codigo do usuario: " + usuario.getCodigo());
        System.out.println("Nome do usuario: " + usuario.dadosPessoais().getNome());
        System.out.println("________________________________________________________________");
        System.out.println("");
    }
    
    //verifica se a senha introduzida pelo usuario esta correcta
    private int verificaSenha(Usuario user){
        int senhaUser;
        String senha = "";
        try{
            senhaUser =  user.getSenha();//vai receber a senha desse usuario
            
            do{
                
                senha = leEValidaSenha();//le e valida a senha
                
                if(Integer.parseInt(senha) != 0){
                    
                    if(senhaUser != Integer.parseInt(senha)){
                        System.out.println("Senha errada. Tente novamente.");
                    }
                }
            }while((senhaUser != Integer.parseInt(senha)) && Integer.parseInt(senha) != 0);
        }catch(NumberFormatException ex){
            System.err.print(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.print(ex.getMessage());
            ex.printStackTrace();
        }
        return Integer.parseInt(senha);
    } 
    
    //le e valida a senha
    private String leEValidaSenha(){
        String senha;
        do{
            System.out.println("");
            System.out.println("Insira '0' caso queira sair.");
            System.out.println("");
            senha = Validacao.Digitos("Introduza a senha: ", false);
            
            if(senha.length()!= 4 && Integer.parseInt(senha) != 0){
                System.out.println("Formato errado. Tente Novamente.");
            }
            
        }while(senha.length()!=4 && Integer.parseInt(senha) != 0);
        
        return senha;
    }
   
    /**
     * Verifica se o usuario tem permissao de acesso as classes escolhidas por ele
 Nota que este metodo basea-se no menus mostrado ao usuario aquando a escolha da opcao
 sendo por isso necessario que as opcoes mostradas ao usuario sejam incluidas e semelhantes aos poderes
 que sao atribuidos ao usuario na classe NivelAcesso.preecnhePoderesAux(opcoes)
     * @param user
     * @param mensagem
     * @param opcao
     * @return boolean
     */
    public static boolean loginClassesChecker(Usuario user, byte mensagem, String opcao){
        
        try{
            
            if(user.getnivelAcesso().getAcesso().equalsIgnoreCase("Gerente")){
                return true;
            }else{
                OperacoesUsuario operacao = new OperacoesUsuario();
                operacao.updateCredenciasUsers(user);//actualiza as credenciais do usuario
                String poderes[] = user.getPoderes().split(",");
                String opcoes[] = Mensagens.removeLiteral(mensagem).split("\n");//opcoes do menus
            
                for(byte i = 0; i < poderes.length; i++){//procura os poderes dentre a opcao escolhida
                    if(opcoes[Byte.parseByte(opcao) - 1].contains(poderes[i])){
                        return true;
                    }
                }
            }

            System.out.println("");
            System.out.println("____________________________________________________________________________");
            System.out.println("Nao tem permissao para aceder a esta opcao.\nOs seus poderes sao:\n");
            user.printPoderes();
            System.out.println("____________________________________________________________________________");
            System.out.println("");
            
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
    
}

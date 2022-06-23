/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chidi Mieka
 */
public class OperacoesNivelAcesso extends OperacoesUsuario{
    
    /**
     *
     */
    public OperacoesNivelAcesso(){
    }
    
    /**
     *
     */
    public void menuNivelAcesso(){
         String dado;
        do{
            dado = Validacao.intervaloOpcoes((byte) 22, (byte) 1, (byte) 2, true);
            if(!dado.equalsIgnoreCase("s")){
                switch(Byte.parseByte(dado)){
                    case 1:
                        alteraAcesso();
                        break;
                    case 2:
                        invalidarAcesso();
                        break;
                }
            }
        }while(!dado.equalsIgnoreCase("s"));
    }
    
     /**
     *altera o acesso de um utilizador
     * @
     */
    private void alteraAcesso(){
        Usuario user;
        String dado;
        //verifica se quer atribuir um acesso normal ou especial
        do{
            System.out.println("");
            System.out.println("______________________________ALTERAR ACESSO DO UTILIZADOR_____________________________\n");
            dado = Validacao.intervaloOpcoes((byte) 25, (byte) 1, (byte) 2, true);
            
            if(!dado.equalsIgnoreCase("s")){
                user = verUsuarios();
                
                switch(Byte.parseByte(dado)){
                    case 1://atribui acesso normal
                        user.setNivelAcesso(new NivelAcesso());
                        user.guardaNoFicheiro();//actualiza os dados no ficheiro
                         break;
                    case 2://atribuir acesso especial
                        if(user.getnivelAcesso().getAcesso().equalsIgnoreCase("Gerente")){
                            System.out.println("So pode atribuir um nivel de acesso especial a um vendedor!!!");
                        }else{
                            if(atribuirAcessoEspecial(user)){
                                user.guardaNoFicheiro();//actualiza os dados no ficheiro
                            }
                        }
                        break;
                }
            }
            
        }while(!dado.equalsIgnoreCase("s"));
    }
    
     /**
     * invalida o acesso de um utilizador
     */
    private void invalidarAcesso(){
        Usuario user;
        byte resp = 0;
        
        try{
            System.out.println("");
            System.out.print("_____________________________INVALIDACAO DO NIVEL DE ACESSO_______________________________\n");
            user = verUsuarios();
            resp = Byte.parseByte(Validacao.intervaloOpcoes((byte) 8, (byte) 1, (byte) 2, false));
            
            if(resp != 2){
                user.setNivelAcesso(new NivelAcesso("Revogado"));
                user.setPoderes("");
                user.guardaNoFicheiro();//actualiza os dados no ficheiro
                System.out.println("");
                System.out.println("O acesso do usuario " + user.dadosPessoais().getNome() + ", de codigo: " + user.getCodigo() + " foi revogado com sucesso.");
                System.out.println("");
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
     * atribui um acesso especial a um utilizador, isto e, pode atribuir a um vendedor o acesso ao registo
     * de produtos
     * @
     */
    private boolean atribuirAcessoEspecial(Usuario user){
        String dado =  Validacao.intervaloOpcoes((byte) 23, (byte) 1, (byte) 2, true),
                poderes,tempo;
        
        System.out.println("");
        System.out.println("______________________________ATRIBUIR NIVEL DE ACESSO ESPECIAL_____________________________\n");
        System.out.println("Nivel de acesso actual: " + user.getnivelAcesso().getAcesso());

        if(!dado.equalsIgnoreCase("s")){

            switch(Byte.parseByte(dado)){
                case 1:
                    poderes = poderesLista("Especial");//preenche os poderes do usuario de acordo com o nivel de acesso
                    tempo = Validacao.regex("Tempo", "Insira o tempo no formato HH:mm:ss : ");

                    if(confirmaMudancaAcesso(poderes,(byte) 1,user,tempo)){
                        atribuiSubtipoAcessoEspecial(poderes, user);
                        return true;
                    }
                    break;
                case 2:
                    poderes = poderesLista("Especial");
                    tempo = "Indefinida";

                    if(confirmaMudancaAcesso(poderes,(byte) 2,user,tempo)){
                        atribuiSubtipoAcessoEspecial(poderes, user);
                        return true;
                    }
                    break;
            }
        }

        return false;
    }
    
    /**
     * Atribui os subtipos de acesso especial aum usuario
     * @param poderes
     * @param user 
     */
    private void atribuiSubtipoAcessoEspecial(String poderes, Usuario user){
        String arrayPoderes[] = poderes.split(",");
        
        if(user.getPoderes().length() == 0 ){
            user.setPoderes(poderes);
        }else{
            user.setPoderes(user.getPoderes() + "," + poderes);
        }
        
        if(!user.getnivelAcesso().getAcesso().contains("Especial")){//armazena o nivel de acesso anterior desde que este nao seja 'especial'
            user.setNivelAcessoAnterior(user.getnivelAcesso());
        }
        
        
        switch(arrayPoderes.length){
            case 1:
                //tem acesso a uma classe especial
                user.setNivelAcesso(new NivelAcesso("Especial 1"));
                break;
            case 2:
                //tem acesso a duas classes especiais
                user.setNivelAcesso(new NivelAcesso("Especial 2"));
                break;
            case 3:
                //tem acesso a tres classes especiais
                user.setNivelAcesso(new NivelAcesso("Especial 3"));
                break;
        }
    }
    
     /**
     * Confirma a atribuicao de poderes e a mudanca do nivel de acesso a um usuario
     * @param poderes
     * @param opc
     * @param codigoFuncionario
     * @param tempo
     * @return 'true' se for confirmado, 'false' caso contrario
     */
    private boolean confirmaMudancaAcesso(String poderes, byte opc, Usuario user, String tempo){
        String aux[] = poderes.split(",");
        String opcMensg = "";
        byte opcao;
        
        if(opc == 1){
            opcMensg += "temporariamente";
        }else{
            opcMensg += "definitivamente";
        }
        System.out.println("");
        System.out.println("Confirme os poderes concedidos " + opcMensg + " ao funcionario " + user.getCodigo() + ":");
        System.out.println("________________PODERES_____________________");
        
        for(byte i = 0; i < aux.length; i++){
            System.out.println((i + 1) + ". " + aux[i]);
        }
        System.out.println("____________________________________________");
        
        if(!tempo.equalsIgnoreCase("indefinida")){
            FuncoesTempo.segundosParaMensagemTempo(FuncoesTempo.tempoParaSegundos(tempo), "De validade:");
        }else{
            System.out.println("De validade: " + tempo);
        }
        opcao = Byte.parseByte(Validacao.intervaloOpcoes((byte) 29, (byte) 1, (byte) 2, false));
        
        if(opcao == 2){
            return false;
        }else{
            user.setPrazoCredenciais(tempo);
            return true;
        }
    }
    
    /**
     * Requesitado quando o gerente tem de conceder premissao para uma determinada actividade
     * @param codigoPermissao
     * @param operacao
     * @return 'true' se o gerente permitir, 'false' caso contrario
     */
    public boolean concedePermissao(String codigoPermissao, String operacao){
        String codigosPermissao[] = codigoPermissao.split(",");
        String mensagem = "", resp;
        
        for(byte i = 0; i < codigosPermissao.length; i++){
            
            switch(codigosPermissao[i]){
                case "CpPe1":
                    mensagem += " Produto expirado,";
                    break;
                case "CpPs1":
                    mensagem += " Produto sem stock,";
                    break;
            }
        }
        
        Login logger = new Login();
        
        //se o gerente inserir as sua credenciais
        if(logger.insercaoDeCredenciais((byte) 2)){
            
            System.out.print("Conceder permissao para " + operacao + " de: \n{" + mensagem.substring(0, mensagem.lastIndexOf(",")) + "}");
            resp = Validacao.intervaloOpcoes((byte) 29, (byte) 1, (byte) 2, false);
            
            if(Byte.parseByte(resp) == 1){
                return true;
            }
        }
        
        return false;
    }
    
    
}

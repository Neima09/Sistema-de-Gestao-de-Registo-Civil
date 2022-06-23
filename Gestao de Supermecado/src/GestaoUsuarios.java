/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chidi Mieka
 */
public class GestaoUsuarios extends OperacoesUsuario{
    
    /**
     *
     */
    public GestaoUsuarios(){
    }
    
    /**
     *
     * @param usuario
     */
    public void menuGestaoUsuarios(Usuario usuario){
    Usuario user;
       String dado = "";
       try{
            do{
                System.out.println("");
                System.out.println("____________________GESTAO USUARIOS______________________");
                System.out.println("");
                
                dado = Validacao.intervaloOpcoes((byte) 31, (byte) 1, (byte) 6, true);

                if(!dado.equalsIgnoreCase("s")){
                     
                    if(Login.loginClassesChecker(usuario, (byte) 31, dado)){

                        switch(Byte.parseByte(dado)){
                            case 1:
                                novoUsuario();
                                break;
                            case 2:
                                user = verUsuarios();
                                alteraDados(user);
                                user.visualizaDados();
                                user.guardaNoFicheiro();
                                break;
                            case 3:
                                alteraSenhaUser();
                                break;
                            case 4:
                                nivelAcesso();
                                break;
                            case 5:
                                verUsuarios();
                                break;
                            case 6:
                                removerUsuarios();
                                break;
                        }
                    }
                }
            }while(!dado.equalsIgnoreCase("s"));
       }catch(Exception ex){
           System.err.println(ex.getMessage());
           ex.printStackTrace();
       }
    }
    
}

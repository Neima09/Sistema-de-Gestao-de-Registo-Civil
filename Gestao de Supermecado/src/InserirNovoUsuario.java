import java.io.*;
import java.time.Year;
import java.util.Vector;
/**
 *Classe responsavel pela insercao de novos utilizadores
 */
/*nivel de acesso: gerente*/
public class InserirNovoUsuario extends OperacoesUsuario{
    
    private Vector codigosUser;
    private int numUserRegistados = 0;
    private static boolean checkNovoUser = false;//altera o estado se e somente se pelo menos um usuario tenha sido introduzido
    
    /**
     *
     */
    public InserirNovoUsuario(){
    }
        
    /**
     * insere os dados do novo utilizador
     */
    public void inserirNovoUser(){        
        String dado;
        codigosUser = new Vector();
        users = new Vector();
        
        try{
            System.out.println("");
            System.out.println("____________________INSERCAO DE USUARIOS______________________");
            System.out.println("");
            
            codigosUser = Ficheiros.Ficheirostxt("Codigos dos utilizadores");
            numUserRegistados = codigosUser.size();
            users = Ficheiros.leituraFicheiro("usuarios");
            dado = Validacao.Digitos("Quantos utilizadores pretende introduzir?\n", true);//qtd dos usuarios
            
            if(!dado.equalsIgnoreCase("s")){
                
                for(byte i = 0; i < Byte.parseByte(dado); i++){
                    System.out.println("");
                    System.out.println((i + 1) + "o usuario: ");
                    System.out.println("");
                    
                    Usuario user = new Usuario(new NivelAcesso(), new DadosPessoais());
                    user.setPoderes(poderesLista(user.getnivelAcesso().getAcesso()));//preenche os poderes do usuario
                    user.setPrazoCredenciais("Indefinida");
                    user.atribuiCodeUser(++numUserRegistados);  
                    user.atribuiSenha();
                    
                    posInsercao(user);
                }

                if(checkNovoUser == true){//so vai guardar no ficheiro se tiverem sido registados novos usuarios
                    Ficheiros.toFicheiroTxt(codigosUser,"Codigos dos utilizadores");
                    Ficheiros.toFicheiro(users,"usuarios");//guarda os utilizadores no ficheiro
                }
            }
            
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
        
    /**
     * verifica se  quer guardar, alterar ou anular o novo usuario
     * @param user 
     */
    private void posInsercao(Usuario user){
        String dadoAux;
        byte opcao = 0;
        try{
            do{
                user.visualizaDados();//visualiza os dados do utilizador
                dadoAux = Validacao.intervaloOpcoes((byte) 3, (byte) 1, (byte) 3, false);
                opcao = Byte.parseByte(dadoAux);
                
                switch(opcao){
                    case 1:
                        alteraDados(user);
                        break;
                    case 2:
                        user = null;
                        numUserRegistados--;
                        break;
                    case 3:
                        /*checkNovoUser = true;
                        codigosUser.addElement(String.valueOf(user.getCodigo()));
                        codigosUser.trimToSize();
                        users.addElement(user);
                        users.trimToSize();*/
                       // TabelasVisualizacoes.visualizaUsuarios(users);
                        break;
                }
            }while(opcao != 2 && opcao != 3);
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}

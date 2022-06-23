import java.io.*;
import java.util.Vector;

/*classe responsavel pela gestao dos niveis de acesso*/
/*nivel de acesso: gerente*/

/**
 *
 * @author Chidi Mieka
 */

public class NivelAcesso implements Serializable{
    private String nivelAcesso;
        
    /**
     *
     */
    public NivelAcesso(){
        atribuirNivelAcesso();
    }
    
    /**
     *
     * @param acesso
     */
    public NivelAcesso(String acesso){
        nivelAcesso = acesso;
    }
    
    /**
     * Retorna o nivel de acesso em string
     * @return o acesso
     */
    public String getAcesso(){
        return nivelAcesso;
    }
    
    /**
     *
     * @param nivelAcesso
     */
    public void setAcesso(String nivelAcesso){
        this.nivelAcesso = nivelAcesso;
    }
    
    /**
     * atribui um nivel de acesso a um utilizador
     * @param user 
     */
    private void atribuirNivelAcesso(){
        String dado;
        System.out.println("");
        System.out.println("______________________________ATRIBUIR NIVEL DE ACESSO_____________________________\n");
        dado = Validacao.intervaloOpcoes((byte) 19, (byte) 1, (byte) 2, true);
        
        if(!dado.equalsIgnoreCase("s")){
            
           // user.setNivelAcessoAnterior(user.getnivelAcesso());
            if(Byte.parseByte(dado) == 1){
                nivelAcesso = "Gerente";
               /*user.setNivelAcesso("Gerente");
                user.setPoderes(poderesLista("Gerente"));*/
            }else{
                nivelAcesso = "Vendedor";
                /*user.setNivelAcesso("Vendedor");
                user.setPoderes(poderesLista("Vendedor"));*/
            }
            //user.setPrazoCredenciais("Indefinida");
        }
    }
}

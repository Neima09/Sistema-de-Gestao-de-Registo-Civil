import java.time.Year;
import java.util.Vector;
import java.io.Serializable;

//classe responsavel pela insercao e nivel de acesso dos utilizadores
/*nivel de acesso: gerente*/

/**
 *
 * @author Chidi Mieka
 */

public class Usuario implements Serializable{
    
    private DadosPessoais dadosPessoais;
    private String prazoCredenciais,poderes;
    private short senha,codigoUser;
    private NivelAcesso nivelAcesso,nivelAcessoAnterior;

    /**
     *
     */
    public Usuario(){
    }
   
    /**
     *
     * @param nivel
     * @param dados
     */
    public Usuario(NivelAcesso nivel, DadosPessoais dados){
       nivelAcesso = nivel;
       dadosPessoais = dados;
   }
 
    //retorna o codigo do utilizador

    /**
     *
     * @param quant
     */
        public void atribuiCodeUser(int quant){
        int nrUser = 1000 + quant;
        Year ano = Year.now();
        //atribui o codigo sendo: os ultimos 2 digitos do ano + quantidade de utilizadores
        codigoUser =  Short.parseShort(String.valueOf(ano).substring(2) + String.valueOf(nrUser).substring(1));
    }
    
        //retorna a senha do utilizador

    /**
     *
     */
        public void atribuiSenha(){
         senha = (short)(Math.random() * 10000);
    }
    
    
    //guarda as alteracoes no ficheiro

    /**
     *
     */
        public void guardaNoFicheiro(){
        Vector users = Ficheiros.leituraFicheiro("usuarios");//recebe os usuarios
        Usuario userAux;
        try{
            for(int i = 0; i < users.size(); i++){
                userAux = (Usuario) users.elementAt(i);
                if(this.getCodigo() == userAux.getCodigo()){// se os codigos forem os mesmos entao trata-se do mesmo usuario
                    users.set(i,this);//atribui o 'novo' usuario modificado
                    break;
                }
            }
            Ficheiros.toFicheiro(users,"usuarios");//passa os usuarios para o ficheiro
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     *
     * @param prazoCredenciais
     */
    public void setPrazoCredenciais(String prazoCredenciais) {
        
        if(!prazoCredenciais.toLowerCase().equalsIgnoreCase("indefinida")){
            long tempoAdicionado = FuncoesTempo.tempoParaSegundos(prazoCredenciais);
            //atribui o prazo das credencias somado  a data actual, assim, tem-se a data e o tempo na qual expiram as credenciais
            this.prazoCredenciais = String.valueOf(FuncoesTempo.adicionaTempo(FuncoesTempo.dataActualPlusHoras(), tempoAdicionado));
        }else{
            this.prazoCredenciais = prazoCredenciais;
        }
    }
    
    //calcula o tempo em falta para a expiracao das credenciais 

    /**
     *
     * @return
     */
        public long tempoFaltaCredencias(){
        
        return FuncoesTempo.tempoDentre("segundos",FuncoesTempo.dataActualPlusHoras(),
                FuncoesTempo.stringParaTempoPlusHoras(this.prazoCredenciais));
    }

    /**
     *
     */
    public void visualizaDados(){
       long tempoFaltaCredenciais;
       String poderes[] = this.poderes.split(",");
        System.out.println("");
        System.out.println("________________DADOS DO FUNCIONARIO__________________________");
        System.out.println("");
        System.out.println("_________________CREDENCIAIS DO FUNCIONARIO___________________");
        System.out.println("Codigo do utilizador: " +  this.codigoUser);
        System.out.println("Nivel de acesso: " +  this.nivelAcesso.getAcesso());
        System.out.println();
        System.out.println("Poderes: ");
        
        this.printPoderes();
        
        System.out.println();
        System.out.println("Senha de acesso: " +  this.senha);
        
        if(!this.prazoCredenciais.equalsIgnoreCase("indefinida")){
            //tempo em falta para expiracao das credenciais
            tempoFaltaCredenciais = tempoFaltaCredencias();
            
            if(tempoFaltaCredenciais < 0){
                System.out.println("Validade das credenciais: Expirada");
            }else{
                FuncoesTempo.segundosParaMensagemTempo(tempoFaltaCredenciais, "Credenciais expiram em");
            }
        }else{
            System.out.println("Validade das credenciais: " + this.prazoCredenciais);
        }
        System.out.println("_________________DADOS PESSOAIS________________________________");
        System.out.println("Nome: " +  this.dadosPessoais().getNome());
        System.out.println("Idade: " +  this.dadosPessoais().getIdade());
        System.out.println("Estado civil: " +  this.dadosPessoais().getEstadoCivil());
        System.out.println("Data de nascimento: " +  this.dadosPessoais().getNascimento());
        System.out.println("B.I: " +  this.dadosPessoais().getBi());
        System.out.println("Contacto principal: " +  this.dadosPessoais().getContacto());
        System.out.println("Contacto de emergencia: " +  this.dadosPessoais().getContactoEmer());
        System.out.println("Morada: " +  this.dadosPessoais().getMorada());
        System.out.println("_______________________________________________________________");
        System.out.println("");
    }
    
    /**
     *
     * @return
     */
    public NivelAcesso getnivelAcesso() {
        return nivelAcesso;
    }
     
    /**
     *
     * @return
     */
    public String getPoderes(){
        return poderes;
    }
    
    /**
     *
     * @param nivelAcesso
     */
    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
    
    /**
     *
     * @return
     */
    public short getCodigo() {
        return codigoUser;
    }

    /**
     *
     * @return
     */
    public DadosPessoais dadosPessoais(){
         return dadosPessoais;
     }
     
    /**
     *
     * @param senha
     */
    public void setSenha(short senha) {
        this.senha = senha;
    }
    
    /**
     *
     * @param nivelAcessoAnterior
     */
    public void setNivelAcessoAnterior(NivelAcesso nivelAcessoAnterior){
        this.nivelAcessoAnterior = nivelAcessoAnterior;
    }
    
   /* public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }*/

    /**
     *
     * @param poderes
     */
    
    
    public void setPoderes(String poderes){
        this.poderes = poderes;
    }

    /**
     *
     * @return
     */
    public short getSenha() {
        return senha;
    }
    
    /**
     *
     * @return
     */
    public NivelAcesso getNivelAcessoAnterior(){
        return nivelAcessoAnterior;
    }
    
    /**
     *
     * @return
     */
    public String getPrazoCredenciais() {
        return prazoCredenciais;
    }
    
    
    /*public void setCodigo(int codigo) {
        this.codigo = codigo;
    }*/

    /**
     *
     * @param codigoUser
     */
    
    
    public void setCodigoUser(short codigoUser){
        this.codigoUser = codigoUser;
    }
    
    
    //imprime os poderes do usuario

    /**
     *
     */
        public void printPoderes(){
        
        if(this.getPoderes().length() != 0){
            String poderesArr[] = this.getPoderes().split(",");

            for(byte i = 0; i < poderesArr.length; i++){
                System.out.println(i + 1 + ". " + poderesArr[i]);
            }
        }else{
            System.out.println("Nao tem poderes");
        }
    }

    //retorna a validade das credencias

    /**
     *
     * @return
     */
        public String validadeCredenciais(){
        
        if(this.getPrazoCredenciais().toLowerCase().equalsIgnoreCase("indefinida")){
            return "Indefinida";
        }else{
            return FuncoesTempo.formatarData(FuncoesTempo.stringParaTempoPlusHoras(this.getPrazoCredenciais()));
        }
    }
}


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chidi Mieka
 */
public class DadosPessoais implements Serializable{
    private String nome,morada,dataNascimento,
            contacto,contactoEmergencia,estadoCivil,bi;
    private byte idade;
    
    /**
     *
     */
    public DadosPessoais(){
        nome = Validacao.Letras("Nome: ", false);
        idade = Byte.parseByte(Validacao.Digitos("idade: ", false));
        estadoCivil();
        dataNascimento = Validacao.regex("Data de nascimento", "Data de nascimento: ");
        bi = Validacao.regex("B.I", "B.I: ");
        contacto = Validacao.regex("Contacto", "Contacto: ");
        contactoEmergencia = Validacao.regex("Contacto", "Contacto de emergencia: ");
        morada = Validacao.leituraSemValidacao("Morada: ");
    }
    

    /**
     * atribui um estado civil
     */
        public void estadoCivil(){
        String dado;
        dado = Validacao.intervaloOpcoes((byte) 18, (byte) 1, (byte ) 2, false);
        if(Byte.parseByte(dado) == 1){
            estadoCivil = "Solteiro";
        }else{
            estadoCivil =  "Casado";
        }
    }
    
    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }
        
    /**
     *
     * @return
     */
    public String getMorada() {
        return morada;
    }
    
    /**
     *
     * @return
     */
    public String getNascimento() {
        return dataNascimento;
    }
    
    /**
     *
     * @return
     */
    public String getContacto() {
        return contacto;
    }
    
    /**
     *
     * @return
     */
    public String getBi() {
        return bi;
    }
    
    /**
     *
     * @return
     */
    public String getContactoEmer() {
        return contactoEmergencia;
    }
    
    /**
     *
     * @return
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     *
     * @return
     */
    public byte getIdade() {
        return idade;
    }
    
    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     *
     * @param idade
     */
    public void setIdade(byte idade){
        this.idade = idade;
    }
    
    /**
     *
     * @param estado
     */
    public void setEstadoCivil(String estado){
        estadoCivil = estado;
    }
    
    /**
     *
     * @param dataNascimento
     */
    public void setNascimento(String dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    
    /**
     *
     * @param bi
     */
    public void setBi(String bi){
        this.bi = bi;
    }
        
    /**
     *
     * @param contacto
     */
    public void setContacto(String contacto){
        this.contacto = contacto;
    }
    
    /**
     *
     * @param contactoEmer
     */
    public void setcontactoEmer(String contactoEmer){
        this.contactoEmergencia = contactoEmer;
    }
    
    /**
     *
     * @param morada
     */
    public void setMorada(String morada){
        this.morada = morada;
    }
    
}

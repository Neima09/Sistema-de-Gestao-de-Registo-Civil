
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Vector;


/**
 *
 * @author Chidi Mieka
 */
public class Comentario implements Serializable{
    
    private String comentario, estadoComentario,
            frequenciaVisualizacao,codigoComentario;
    private LocalDateTime data;
    private Usuario usuario;
    private Produto produto;
    
    /**
     *
     */
    public Comentario(){
    }
    
    /**
     *
     * @param user
     * @param produto
     */
    public Comentario(Usuario user, Produto produto){
        atribuiCodigo();
        comentario = Validacao.leituraSemValidacao("Introduza o comentario: ");
        estadoComentario = estadoComentario((byte) 1);
        frequenciaVisualizacao = frequenciaVisualizacao(user);
        data = LocalDateTime.now();
        usuario = user;
        this.produto = produto;
        
        Vector comentarios = Ficheiros.leituraFicheiro("comentarios");
        comentarios.addElement(this);
        comentarios.trimToSize();
        
        Ficheiros.toFicheiro(comentarios,"comentarios");//guarda o comentario no ficheiro
    }
    
    //atribui um codigo ao comentario
    private void atribuiCodigo(){
        Vector codigos = Ficheiros.Ficheirostxt("Codigos Comentarios");
        
        Random random = new Random();
        codigoComentario = "C" + String.valueOf((int)(random.nextDouble() * 1000));
        
        codigos.addElement(codigoComentario);
        codigos.trimToSize();
        
        Ficheiros.toFicheiroTxt(codigos, "Codigos Comentarios");
    }
    //retorna um estado ao comentario
    private  String estadoComentario(byte opcao){
        if(opcao == 1){
            return "Pendente";
        }else{
            return "Visto";
        }
    }
    
    //frequencia de visualizacao dum comentario
    private  String frequenciaVisualizacao(Usuario user){
        
        if(user.getnivelAcesso().getAcesso().equalsIgnoreCase("Gerente")){
            return "Sempre";
        }else{
            return "Restrito";
        }
    }
    
    //recebe o codigo de um comentario e retorna o comentario

    /**
     *
     * @return
     */
        protected Comentario buscaComentario(){
        Vector comentarios;
        Comentario comentario;
        String codigo;
        try{
            comentarios = Ficheiros.leituraFicheiro("comentarios");
            codigo = Validacao.regex("Comentario", "Codigo do comentario: ");
            
            for(short i = 0; i < comentarios.size(); i++){
                comentario = (Comentario) comentarios.elementAt(i);//recebe o comentario
                
                if(codigo.equalsIgnoreCase(comentario.getCodigoComentario())){
                    return comentario;
                }
            }
            
        }catch(NullPointerException | ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return null;
    }

    /**
     * retorna os produtos com comentarios ainda por serem vistos pelo gerente
     * @return Um vector de produtos com comentarios pendentes
     */
    public static Vector retornaProdutosPendentes(){
        Vector arrayProdutos = Ficheiros.FicheirosProduto("Produtos"),produtosPendentes = new Vector(),
                comentarios;
        Produto produto;
        Comentario comentario;
        byte numCom = 0;
        
        try{
              
                for(byte i = 0; i < arrayProdutos.size(); i++){
                    produto = (Produto) arrayProdutos.elementAt(i);
                    comentarios = produto.getComentarios();//recebe os comentarios do produto
                    
                    if(!comentarios.isEmpty()){
                        
                        for(byte j = 0; j < comentarios.size(); j++){
                            comentario = (Comentario) comentarios.elementAt(j);//recebe o comentario do produto

                            if(comentario.getEstadoComentario().equalsIgnoreCase("Pendente")){
                                numCom++;
                            }
                        }

                        if(numCom != 0){
                            produto.setComentariosPendentes(numCom);//atribui o numero de comentarios pendentes
                            produtosPendentes.addElement(produto);
                            produtosPendentes.trimToSize();
                            produto.guardaNoFicheiro();
                            numCom = 0;
                        }
                    }else{
                            produto.setComentariosPendentes((byte) 0);//atribui o numero de comentarios pendentes
                            produto.guardaNoFicheiro();
                    }
                    
                } 
        
        }catch(IndexOutOfBoundsException | NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return produtosPendentes;
    }
    
    /**
     * verifica se o codigo do comentario introduzido existe
     * @param codigo
     * @return true se o codigo existir, false caso contrario
     */
    public static boolean verificaCodigo(String codigo){
        Vector codigos = Ficheiros.Ficheirostxt( "Codigos Comentarios");
        
        for(byte i = 0; i < codigos.size(); i++){
            
            if(codigo.equalsIgnoreCase((String)codigos.elementAt(i))){
                return true;
            }
        }
        System.out.println("Codigo invalido!\nIntroduza novamente: ");
        
        return false;
    }
    
    /**
     * Retorna um vector de produtos comentados
     * @return Um vector de produtos comentados
     */
    protected Vector retornaProdutosComentados(){
        Vector arrayProdutos = Ficheiros.FicheirosProduto("Produtos"),produtos = new Vector();
        Produto produto;
        
        try{
              
                for(byte i = 0; i < arrayProdutos.size(); i++){
                    produto = (Produto) arrayProdutos.elementAt(i);
                    
                    if(!produto.getComentarios().isEmpty()){
                        produtos.addElement(produto);
                        produtos.trimToSize();
                    }
                    
                } 
        
        }catch(IndexOutOfBoundsException | NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return produtos;
    }
    
    /**
     * recebe um produto e retorna todos os seus comentarios
     * @param produto
     * @return Um vector com todos os comentarios do produto
     */
    protected Vector comentarios(Produto produto){
        
        return produto.getComentarios();
    }
    
    /**
     *
     * @return
     */
    public String getComentario(){ return comentario;}

    /**
     *
     * @return
     */
    public String getEstadoComentario(){ return estadoComentario;}

    /**
     *
     * @return
     */
    public String getFrequenciaVisualizacao(){ return frequenciaVisualizacao;}

    /**
     *
     * @return
     */
    public String getCodigoComentario(){ return codigoComentario;}

    /**
     *
     * @return
     */
    public LocalDateTime getData(){ return data;}

    /**
     *
     * @return
     */
    public Produto getProduto(){ return produto;}

    /**
     *
     * @return
     */
    public Usuario getUsuario(){ return usuario;}

    /**
     *
     * @param comentario
     */
    public void setComentario(String comentario){ this.comentario = comentario;}

    /**
     *
     * @param opcao
     */
    public void setEstadoComentario(byte opcao){this.estadoComentario = estadoComentario(opcao);}

    /**
     *
     * @param user
     */
    public void setFrequenciaVisualizacao(Usuario user){ this.frequenciaVisualizacao = frequenciaVisualizacao(user);}
    
}


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
public class OperacoesCategoria extends Categoria{
    private static Vector categorias,quantPorCategoria,prefixosCate,/*esses array estao como que interligados, pois a getCategoria na posicao
    i tem a sua quantidade e prefixo correspondente nos array quantPorCategoria e prefixosCate*/
            refsArray;
    private byte nrCategorias;
    
    /**
     *
     */
    public OperacoesCategoria(){
        preencheArrays();
    }
    
    
     private void preencheArrays(){
        try{
            categorias = Ficheiros.Ficheirostxt("Categorias");
            nrCategorias = (byte) categorias.size();
            quantPorCategoria = Ficheiros.Ficheirostxt("QuantPorCategoria");
            refsArray = Ficheiros.Ficheirostxt("Referencias");
            prefixosCate = Ficheiros.Ficheirostxt("PrefixosCate");
            
        }catch(NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
     }
     
     /**
     * Atribui uma referencia a um determinado produto. 
     * @param indice o indice da categoria do produto
     * @return 
     */
    public String giveReferencia(byte indice){
        String nrUnico,letra = "",referencia = "";
        boolean aux = false;
        try{
            do{
                nrUnico = nrUnicoProduto(indice,aux);
                letra = "A";
                referencia = letra + nrUnico;
                aux = true;
            }while(checkRef(referencia));
                        
        }catch(ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return referencia;
    }
    
    /**
     * verifica se a referencia existe. Se esta existir, outra referencia sera feita 
     * @param referencia
     * @return true se a referencia existir, false caso contrario
     */
    private boolean checkRef(String referencia){
        String ref;
        
        for(short i = 0; i < refsArray.size(); i++){
            ref = (String) refsArray.elementAt(i);
            
            if(ref.equalsIgnoreCase(referencia)){
                return true;
            }
        }
        
        return false;
    }
    
    
    /**
     * Forma um numero unico para cada produto. O formato do numero 
 pode ser 'ElA00' - prefixo da getCategoria+letra+quantidade de produtos da getCategoria- ou
 'El00A' - prefixo da getCategoria+quantidade de produtos da getCategoria+letra-. Note que assim
 cada getCategoria pode ter ate 5200 produtos.
     * @param index
     * @return Um numero unico
     */
    private String nrUnicoProduto(byte index,boolean caso){
        int quant = Integer.valueOf((String)quantPorCategoria.elementAt(index)) ;
        String 
            prefix = (String) prefixosCate.elementAt(index),//recebe o prefixo da getCategoria
            sufix = retornaSufixo(String.valueOf(quant), caso),//recebe o sufixo
            letra;
        
        if(quant < 2600){
            letra = retornaLetra(quant,(byte) 1);//retorna a letra a ser usada de acordo com a quantidade
            
            return prefix + letra + sufix;//retorna no formato ElA00
        }else{
            
            if(quant > 2600 && quant < 5200){
                letra = retornaLetra(quant,(byte) 2);//retorna a letra a ser usada de acordo com a quantidade
                
                return prefix + sufix + letra;//retorna no formato El00A
            }
        }
        return null;
    }
   
    /**
     * Retorna a letra correspondente ao intervalo
     * @param quant
     * @param opc
     * @return Uma letra
     */
    private String retornaLetra(int quant,byte opc){
        String sep = "ABCDEFGHIJKLMNOPQRSTUVWXYZ",retorno = "";
        char sep1 [] = sep.toCharArray();
        int min,max;
        
        switch(opc){
            case 1:
                
                for(byte i = 0; i < 26; i++){//verifica se um numero esta no intervalo da posicao dada
                    min =  Integer.parseInt(String.valueOf(i) + "00");//recebe o minimo
                    max = Integer.parseInt(String.valueOf(i) + "99");//recebe o maximo
                    
                    if(quant >= min && quant < max){
                        retorno =  String.valueOf(sep1[i]);//retorna a letra correspondente
                        break;
                    }
                }
            break;
            case 2:
                
                for(byte i = 26; i < 52; i++){//verifica se um numero esta no intervalo da posicao dada
                    min =  Integer.parseInt(String.valueOf(i) + "00");//recebe o minimo
                    max = Integer.parseInt(String.valueOf(i) + "99");//recebe o maximo
                    
                    if(quant >= min && quant < max){
                        retorno =  String.valueOf(sep1[i - 26]);//retorna a letra correspondente
                        break;
                    }
                }
            break;
        }
        return retorno;
    }
    
    /**
     * Retorna os ultimos dois digitos desse numero
     * @param index
     * @return Uma string dos ultimos dois digitos desse numero
     */
    private String retornaSufixo(String quant, boolean caso){
        
        //se a referencia ja existir entao ira incrementar a quantidade da getCategoria para poder criar um novo sufixo
        if(caso == true){
            int i = Integer.valueOf(quant);
            i++;
            quant = String.valueOf(i);
        }
        
        if(quant.length() >= 3){
            return quant.substring(quant.length() - 2);
        }else{
            String sufix = String.valueOf(100 + Integer.parseInt(quant));;
            
            return sufix.substring(1);
        }
    }
    
        /**
     * muda a getCategoria de uma dado produto
     * @param categoriaPro
     * @return A nova getCategoria do produto
     */
    public String mudaCategoria(String categoriaPro){
        String dado;
        dado = Validacao.intervaloOpcoes(visualizaCategorias(), 1, nrCategorias, false);
        trocaCate(categoriaPro,dado);
        
        return dado;
    }
    
    /**
     * troca a quantidade de duma dada getCategoria
     * @param catAnterior
     * @param catActual 
     */
    private void trocaCate(String catAnterior,String catActual){
        byte index1 = 0,index2 = 0;
        
        for(byte i = 0; i < categorias.size(); i++){
            
            if(catAnterior.equalsIgnoreCase((String)categorias.elementAt(i))){
                index1 = i;//recebe o index da getCategoria anterior do produto
            }
            
            if(catActual.equalsIgnoreCase((String)categorias.elementAt(i))){
                index2 = i;//recebeo index da nova getCategoria
            }
        }
        reduzAumentaQuant((byte) index1,(byte) 2);
        reduzAumentaQuant((byte) index2,(byte) 1);
    }
    
    /**
     * Recebe a referencia dum produto, reduz a quantidade da respectiva getCategoria e 
 remove a referencia do array de referencias
     * @param ref 
     */
    public void reduzQuantCategoriaERemoveRef(String ref){
        String refe;
        for(byte i = 0; i < categorias.size(); i++){//reduz a quantidade da getCategoria
            
            if(retornaCateFromPrefix(retornaPrefixo(ref)).equalsIgnoreCase((String)categorias.elementAt(i))){
                reduzAumentaQuant(i,(byte) 2);
                break;
            }
        }
        
        for(int i = 0; i < refsArray.size(); i++){//remove a referencia do produto
            refe = (String)refsArray.elementAt(i);
            
            if(refe.equalsIgnoreCase(fullReferencia(ref))){
                refsArray.remove(i);
                break;
            }
        }
        Ficheiros.toFicheiroTxt(refsArray,"Referencias");
    }
    
    private static String fullReferencia(String referencia){
        if(referencia.length() == 5){
            return "A" + referencia;
        }else{
            return referencia;
        }
    }
    
        /**
     * inclui uma nova getCategoria de produtos
     */
    public void novaCategoria(){
        String dado;
        String prefix,categoria;
        try{
            categoria = Validacao.Letras("Introduza a nova categoria: ", false);
            System.out.println("A nova categoria e: " + categoria);
            prefix = Validacao.Letras("Introduza o prefixo: ", false);
            System.out.print("O prefixo e: " + prefix);
            dado = Validacao.intervaloOpcoes((byte) 8, (byte) 1, (byte) 2, false);
            
            if(!dado.equalsIgnoreCase("2")){
                categorias.addElement(categoria);
                categorias.trimToSize();
                prefixosCate.addElement(prefix);
                prefixosCate.trimToSize();
                quantPorCategoria.addElement("0");//incializa um novo contador na posicao indicada
                quantPorCategoria.trimToSize();
                
                Ficheiros.toFicheiroTxt(prefixosCate,"PrefixosCate");
                Ficheiros.toFicheiroTxt(categorias,"Categorias");
                Ficheiros.toFicheiroTxt(quantPorCategoria,"QuantPorCategoria");
            }
        }catch(ArrayIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * verifica se a referencia existe
     * @param referencia
     * @return 
     */
    public static boolean verificaRef(String referencia){
        String ref;
        Vector refsArray = Ficheiros.Ficheirostxt("Referencias");
        
        for(byte i = 0; i < refsArray.size(); i++){
            ref = (String) refsArray.elementAt(i);
            
            if(ref.toLowerCase().contains(fullReferencia(referencia).toLowerCase())){
                return true;
            }
        }
        System.out.println("\nReferencia invalida!\nIntroduza novamente: ");
        
        return false;
    }
    
    /**
     *
     * @return
     */
    public byte getNrCategorias(){return nrCategorias;}
    
    
}

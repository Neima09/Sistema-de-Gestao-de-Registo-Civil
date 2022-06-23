/*Ao executar o metodo redimensionar para produtos, na classe ficheiro, 27 vezes, tira um ArrayOutOfBoundsException -128*/
import java.io.Serializable;
import java.io.*;
import java.util.Vector;

/*
/*metodo responsavel pela gestao de produtos*/
/*nivel de acesso: gerente e vendedor*/

/**
 *
 * @author Chidi Mieka
 */

public class GestaoProdutos extends OperacoesProduto{
    
    /**
     *
     * @param usuario
     */
    public GestaoProdutos(Usuario usuario){
            super(usuario);
        }
        
        //acesso as classes da gestao de produtos

    /**
     *
     */
            public void menuGestaoProdutos(){
            String dado = "";
            try{
                do{
                    System.out.println("");
                    System.out.println("____________________GESTAO DE PRODUTOS______________________");
                    System.out.println("");
                    dado = Validacao.intervaloOpcoes((byte) 17, (byte) 1, (byte) 5, true);
                    
                    if(!dado.equalsIgnoreCase("s")){
                        
                        if(Login.loginClassesChecker(super.usuario, (byte) 17, dado)){
                        
                            if(existemProdutos(Byte.parseByte(dado))){
                                updateProdutos();//actualiza a validade dos produtos
                            
                                switch(Byte.parseByte(dado)){
                                    case 1:
                                        insercaoProdutos();
                                        break;
                                    case 2:
                                        gestaoStock();
                                        break;
                                    case 3:
                                        vendaProdutos();
                                        break;
                                    case 4:
                                        OperacoesCategoria operacao = new OperacoesCategoria();
                                        operacao.novaCategoria();
                                        break;
                                    case 5:
                                        comentariosSobreProdutos();
                                        break;
                                }
                            }
                        }
                    }
                }while(!dado.equalsIgnoreCase("s"));
                
            }catch(Exception ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        
         //verifica se existem produtos, caso as opcoes forem 2 ou 3
        private boolean existemProdutos(byte opc){
            Vector array = Ficheiros.Ficheirostxt("QuantPorCategoria");
            int quant;
            
            if(opc == 2 || opc == 3){// se a opcao for 2 ou 3, vai verificar se ha produtos
                
                for(byte i = 0; i < array.size(); i++){
                    quant = Integer.valueOf(String.valueOf(array.elementAt(i)));
                    
                    if(quant != 0){//se existir pelo menos uma posicao != 0 entao ha um produto
                        return true;
                    }
                }
                String str = "Na ha produtos...";
                Mensagens.printMensagemNotavel(str,(byte)str.length());
                
                return false;
            }
            
            return true;
        }
}


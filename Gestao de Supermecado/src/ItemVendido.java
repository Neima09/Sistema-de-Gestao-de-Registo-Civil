
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
public class ItemVendido  implements Serializable{
    
    private int quantidade;
    private Produto produto;
    
    /**
     *
     * @param produto
     * @param quantidade
     */
    public ItemVendido(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;
    }
    
    /**
     *
     * @return
     */
    public Produto getProduto(){return produto;}

    /**
     *
     * @return
     */
    public int getQuantidade(){return quantidade;}

    /**
     *
     * @param qtd
     */
    public void setQuantidade(int qtd){ quantidade = qtd;}
    
}

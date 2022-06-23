
import java.io.*;
import java.util.Vector;

/*classe responsavel por criar dados com as atributos de um produto*/

/**
 *
 * @author Chidi Mieka
 */

public class DadosProduto implements Serializable{
    
        private String nome,descricao,marca,
            referencia,validade,fabricante,unidade;
        private float preco; 
        private int stockPro,qtdVendida;
     
    /**
     *
     */
    public DadosProduto(){
     }
    
    /**
     *
     * @param preco
     */
    public void setPreco(float preco){ this.preco = preco;}

    /**
     *
     * @param validade
     */
    public void setValidade(String validade){ this.validade = validade;}

    /**
     *
     * @param nome
     */
    public void setNome(String nome){ this.nome = nome;}

    /**
     *
     * @param marca
     */
    public void setMarca(String marca){ this.marca = marca;}

    /**
     *
     * @param descricao
     */
    public void setDescricao(String descricao){ this.descricao = descricao;}

    /**
     *
     * @param fabricante
     */
    public void setFabricante(String fabricante){ this.fabricante = fabricante;}

    /**
     *
     * @param stock
     */
    public void setStock(int stock){ stockPro = stock;}

    /**
     *
     * @param qtdVendida
     */
    public void setQtdVendida(int qtdVendida){ this.qtdVendida = qtdVendida;}

    /**
     *
     * @param unidade
     */
    public void setUnidade(String unidade){ this.unidade = unidade;}

    /**
     *
     * @param referencia
     */
    public void setReferencia(String referencia){this.referencia = referencia;}

    /**
     *
     * @return
     */
    public String getReferencia(){return referencia;}

    /**
     *
     * @return
     */
    public String getValidade(){ return validade;}

    /**
     *
     * @return
     */
    public float getPreco(){ return preco;}

    /**
     *
     * @return
     */
    public String getMarca(){ return marca;}

    /**
     *
     * @return
     */
    public String getFabricante(){ return fabricante;}

    /**
     *
     * @return
     */
    public String getNome(){ return nome;}

    /**
     *
     * @return
     */
    public String getDescricao(){ return descricao;}

    /**
     *
     * @return
     */
    public String getUnidade(){ return unidade;}

    /**
     *
     * @return
     */
    public int getStock(){return stockPro;}

    /**
     *
     * @return
     */
    public int getQtdVendida(){ return qtdVendida;}
}

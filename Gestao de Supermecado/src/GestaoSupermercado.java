/*__________________________UTILIZADORES DO SISTEMA ________________________
| Nr|Codigo|   Nome do Usuario|Senha|Nivel de acesso|Validade credenciais
|_________________________________________________________________________|
|  1| 20001|      Chidi Jovito| 8299|        Gerente|          Indefinida|
|  2| 20002|  Francisco Tamele| 2137|     Especial 2|          Indefinida|
|  3| 20003|      Hermen Tomas| 2475|        Gerente|          Indefinida|
|  4| 20004|   Alafo Fulgencio| 5704|       Revogado|          Indefinida|
|  5| 20005|    Joao Chirindza| 8050|       Vendedor|          Indefinida|
|_________________________________________________________________________|*/
public class GestaoSupermercado{

    /**
     *
     * @param args
     */
    public static void main(String[]args){
       Login login = new Login();
       login.loginUser();
   }
}

/**
 * Classe com todas mensagens predefinidas
 * 
 */
public class Mensagens{
    
    /**
     *
     */
    public Mensagens(){
    }
    
    /**
     * Retorna mensagens que tem opcoes
     * @param nrMensagem
     * @return mensagens dum menus
     */
    public static String menus(byte nrMensagem){
        String mensagem = "";
        
        switch(nrMensagem){
            case 1:
                mensagem += "O que pretende alterar:Ex: 168\n";
                mensagem += "1. Nome\n";
                mensagem += "2. Descricao\n";
                mensagem += "3. Marca\n";
                mensagem += "4. Preco\n";
                mensagem += "5. Validade\n";
                mensagem += "6. Categoria\n";
                mensagem += "7. Fabricante\n";
                mensagem += "8. Stock\n";
                mensagem += "9. Unidade\n";
                break;
            case 2:
                mensagem += "Escolha a categoria: \n";
                mensagem += "1. Alimentos\n";
                mensagem += "2. Bebidas\n";
                mensagem += "3. Brinquedos\n";
                mensagem += "4. Electrodomesticos\n";
                mensagem += "5. Electronicos\n";
                mensagem += "6. Entreternimento\n";
                mensagem += "7. Higiene\n";
                mensagem += "8. Limpeza\n";
                mensagem += "9. Utensilios\n";
                break;   
            case 3:
                mensagem += "1. Alterar dados\n";
                mensagem += "2. Anular\n";
                mensagem += "3. Guardar\n";
                break;
            case 4:
                mensagem += "Escolha o modo de registo:\n";
                mensagem += "1. Modo de registo 1\n";
                mensagem += "Para varios produtos:\n";
                mensagem += "2. Modo de registo 2\n";
                mensagem += "3. Modo de registo 3\n";
                mensagem += "4. Modo de registo 4\n";
                mensagem += "5. Sair do resgisto de produtos\n";
                break;
            case 5:
                mensagem += "Escolha os atributos: Ex:145\n";
                mensagem += "1. Nome\n";
                mensagem += "2. Descricao\n";
                mensagem += "3. Marca\n";
                mensagem += "4. Categoria\n";
                mensagem += "5. Fabricante\n";
                mensagem += "6. Unidade\n";
                break;
            case 6:
                mensagem += "Deseja continuar?\n";
                mensagem += "1. Continuar\n";
                mensagem += "2. Mudar\n";
                break;
            case 7:
                mensagem += "Tem certeza que que sair?\n";
                mensagem += "1. Sim\n";
                mensagem += "2. Nao\n";
                break;
            case 8:
                mensagem += "Deseja continuar?\n";
                mensagem += "1. Sim\n";
                mensagem += "2. Nao\n";
                break;
            case 9:
                mensagem += "Ver stock:\n";
                mensagem += "1. Um produto\n";
                mensagem += "2. Todas categorias\n";
                mensagem += "3. Todos produtos\n";
                mensagem += "4. A partir da validade\n";
                break;
            case 10:
                mensagem += "1. Alterar produto\n";
                mensagem += "2. Remover produto\n";
                mensagem += "3. Ver stock\n";
                break;
            case 11:
                mensagem += "Escolha a opcao:\n";
                mensagem += "1. Compra de produtos\n";
                mensagem += "2. Deixar comentario acerca do produto\n";
                mensagem += "3. Procura de produtos\n";
                mensagem += "4. Visualizar o registo de compras\n";
                break;
            case 12:
                mensagem += "1. Adicionar itens\n";
                mensagem += "2. Anular Lista\n";
                mensagem += "3. Alterar quantidade\n";
                mensagem += "4. Remover produtos\n";
                mensagem += "5. Terminar\n";
                break;
            case 13:
                mensagem += "Tem certeza que quer anular a lista?\n";
                mensagem += "1. Sim\n";
                mensagem += "2. Nao\n";
                break;
            case 14:
                mensagem += "Escolha a opcao:\n";
                mensagem += "1. Mais detalhes da compra\n";
                mensagem += "2. Voltar\n";
                break;
            case 15:
                mensagem += "Procurar usando:\n";
                mensagem += "1. Categoria\n";
                mensagem += "2. Nome ou referencia\n";
                break;
            case 16:
                mensagem += "Vai introduzir a categoria?\n";
                mensagem += "1. Sim\n";
                mensagem += "2. Nao\n";
                break;
            case 17:
                mensagem += "1. Inserir novos produtos\n";
                mensagem += "2. Gestao de stock\n";
                mensagem += "3. Venda de produtos\n";
                mensagem += "4. Inserir novas categorias\n";
                mensagem += "5. Gestao de comentarios\n";
                break;
            case 18:
                mensagem += "Estado civil:\n";
                mensagem += "1. Solteiro/a\n";
                mensagem += "2. Casado/a\n";
                break;
            case 19:
                 mensagem += "Escolha o nivel de acesso:\n";
                mensagem += "1. Gerente\n";
                mensagem += "2. Vendedor\n";
                break;
            case 20:
                mensagem += "Escolha os atributos: Ex:145\n";
                mensagem += "1. Nome\n";
                mensagem += "2. idade\n";
                mensagem += "3. Estado civil\n";
                mensagem += "4. Data de nascimento\n";
                mensagem += "5. B.I\n";
                mensagem += "6. Contacto\n";
                mensagem += "7. Contacto de emergencia\n";
                mensagem += "8. Morada\n";
                break;
            case 21:
                mensagem += "O que pretende alterar:Ex: 168\n";
                mensagem += "1. Nome\n";
                mensagem += "2. idade\n";
                mensagem += "3. Estado civil\n";
                mensagem += "4. Data de nascimento\n";
                mensagem += "5. B.I\n";
                mensagem += "6. Contacto\n";
                mensagem += "7. Contacto de emergencia\n";
                mensagem += "8. Morada\n";
                mensagem += "9. Nivel de acesso\n";
                break;
            case 22:
                mensagem += "Escolha a opcao:\n";
                mensagem += "1. Alterar o acesso de um utilizador\n";
                mensagem += "2. Invalidar o acesso dum utilizador\n";
                break;
            case 23:
                mensagem += "1. Acesso especial por tempo definido\n";
                mensagem += "2. Acesso especial por tempo indefinido\n";
                break;
            case 24:
                mensagem += "1. Introduzir codigo do funcionario\n";
                mensagem += "2. Ver todos funcionarios\n";
                break;
            case 25:
                mensagem += "1. Atribuir acesso normal a um utilizador\n";
                mensagem += "2. Atribuir acesso especial a utilizador\n";
                break;
            case 26:
                mensagem += "1. Gestao de produtos\n";
                mensagem += "2. Gestao de usuarios\n";
                break;
            case 27:
                mensagem += "1. Continuar\n";
                mensagem += "2. Mudar\n";
                mensagem += "3. Voltar\n";
                break;
            case 28:
                mensagem += "1. Permissao do gerente\n";
                mensagem += "2. Cancelar\n";
                break;
            case 29:
                mensagem += "1. Continuar\n";
                mensagem += "2. Cancelar\n";
                break;
            case 30:
                mensagem += "Pretende mudar a senha de acesso deste usuario?\n";
                mensagem += "1. Sim\n";
                mensagem += "2. Nao\n";
                break;
            case 31:
                mensagem += "1. Inserir novos usuarios\n";
                mensagem += "2. Alterar dados do utilizador\n";
                mensagem += "3. Mudar senha de acesso do utilizador\n";
                mensagem += "4. Gestao do nivel de acesso\n";
                mensagem += "5. Ver Utilizadores\n";
                mensagem += "6. Remover Utilizadores\n";
                break;
            case 32:
                 mensagem += "Escolha as poderes: ex:123\n";
                mensagem += "1. Inserir novos produtos\n";
                mensagem += "2. Inserir novos usuarios\n";
                mensagem += "3. Inserir novas categorias\n";
                break;
            case 33:
                mensagem += "1. Comentar sobre produto\n";
                mensagem += "2. Remover comentarios\n";
                mensagem += "3. Ver comentarios pendentes\n";
                mensagem += "4. Ver meus comentarios\n";
                break;
            case 34:
                mensagem += "Deseja continuar com a remocao desses comentarios?\n";
                mensagem += "1. Continuar\n";
                mensagem += "2. Mudar\n";
                mensagem += "3. Cancelar\n";
                break;
            case 35:
                mensagem += "Procurar o comentario a partir:\n";
                mensagem += "1. Lista de produtos com comentarios\n";
                mensagem += "2. Codigo do comentario\n";
                mensagem += "3. Referencia do produto\n";
                break;
            case 36:
                mensagem += "O produto esta em:\n";
                mensagem += "1. Unidades\n";
                mensagem += "2. Embalagem\n";
                break;
            case 37:
                mensagem += "Procurar usando:\n";
                mensagem += "1. Nome\n";
                mensagem += "2. Referencia\n";
                break;
        }
        return "\n" + mensagem;
    }
    
    /**
     * Retorna as mensagens de invalidacao ou erro.
     * @param nrMensagem
     * @return mensagem de invalidacao ou erro
     */
    public static String invalidacao(byte nrMensagem){
        String mensagem = "";
        
        switch(nrMensagem){
            case 1:
                mensagem += "O dado contem uma irregularidade.\nIntroduza novamente:  ";
                break;
            case 2:
                mensagem += "O valor introduzido esta fora dos limites.\nIntroduza novamente:  ";
                break;
            case 3:
                mensagem += "Formato ou valores errados. \nIntroduza Novamente: ";
                break;
            case 4:
                mensagem += "Numero de opcoes invalidas.\n\nNumero de atributos: ";
                break;
            case 5:
                mensagem += "Prefixo invalido.";
                break;
            case 6:
                mensagem += "Referencia invalida.";
                break; 
        }
        
        return mensagem;
    }
    
    /**
     * Retorna a mensagem para saida
     * @param nrMensagem
     * @return mensagem para saida
     */
    public static String sugestiva(byte nrMensagem){
        String mensagem = "";
        
        switch(nrMensagem){
            case 1:
                mensagem += "\nPrima 'enter' para sair.\n";
                break;
            case 2:
                mensagem += "\nInsira 's' se nao tiver uma opcao disponivel para sair, voltar ou cancelar a operacao.\n";
                break;
        }
        return mensagem;
    }
 
    /**
     * Remove o literal de nova linha '\n' no inicio e no fim da mensagem
     * @param nrMensagem
     * @return a mensagem 
     */
    public static String removeLiteral(byte nrMensagem){
        
        String mensagem = menus(nrMensagem);
        mensagem = mensagem.substring(mensagem.indexOf("\n") + 1);
        
        return  mensagem.substring(0, mensagem.lastIndexOf("\n"));
    }
    
    /**
     * Imprime a mensagem em maiusculas. Seguuindo o limite de caracteres, a mensagem pode estar envolvida 
     * por um rectangulo ou quadrado de asteristicos.
     * @param mensagem
     * @param limChar Limite de caracteres para a impressao 
     */
    public static void printMensagemNotavel(String mensagem, byte limChar){
        byte numLinhas = (byte) Math.ceil((double) mensagem.length() / limChar);
        System.out.println("");
        
        separador(limChar);
        
        for(byte i = 0; i < numLinhas; i++){
            System.out.println("*  " + printString(i, limChar,mensagem.toUpperCase(),numLinhas));
        }
        
        separador(limChar);
        
        System.out.println("");
    }
    
    /**
     * Imprime uma linha de asteristicos
     * @param quant o numero de asteristicos
     */
    private static void separador(int quant){
        
        for(short i = 0; i < quant + 5; i++){
            System.out.print("*");
        }
        System.out.println("*");
    }
    
     /**
     * Ex: Se o tamanho duma String for 62 enquanto a formatacao da tabela so permite 50 
      caracteres, a string sera dividida em conjuntos de 50 caracteres. Ex:
        |O meu pai e|
        |um excelent|
        |e jogador  |
     * @param iteracao ajuda na decisao de que substring retornar, pois esta segue o numero de linhas em que a string
     * esta dividida
     * @param formatSize tamanho do formato
     * @param string a string
     * @return a substring dessa string
     */
    private static String printString(byte iteracao, byte formatSize, String string, byte numLinhas){
           int min = 0,max = 0;
        
        try{
            iteracao += 1;
        
            if(iteracao > numLinhas){//retorna uma string vazia se o numero de linhas para essa string for menor que a iteracao
                return string.substring(0,0);
            }else{
                max = formatSize * (iteracao);//recebe o limite da substring
                min = max - formatSize;

                if(iteracao == numLinhas){//torna-se util na ultima linha, assim nao excede o tamanho da string
                    String str = string.substring(min,string.length());
                    return str + spaces(formatSize,str.length());
                }
            }
            
        }catch(StringIndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
       return  string.substring(min,max) + "  *";
    }
    
    /**
     * Retorna uma  string de espacos
     * @param limChar limite de caracteres
     * @param strSize tamanho da string
     * @return 
     */
    private static String spaces(int limChar, int strSize){
        int quant = limChar - strSize;
        String str = "";
        
        for(byte i = 0; i < quant; i++){
            str += " ";
        }
        str += "  *";
        
        return str;
    }
}

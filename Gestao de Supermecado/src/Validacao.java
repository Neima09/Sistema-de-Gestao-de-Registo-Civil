import java.io.*;

/**
 * Classe encarregue da validacao dos dados
 * @
 */
public class Validacao{
    /**
     * Faz a leitura sem validar o dado
     * @param mensagem
     * @return o dado
     **/
    public static String leituraSemValidacao(String mensagem){
        
        BufferedReader receptor = new BufferedReader(new InputStreamReader(System.in));
        String dado = "";
        try{
            
            System.out.print("\n" + mensagem);
            dado = receptor.readLine();
            
        }catch(IOException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return dado;
    }
    
    /**
     * verifica se a variavel contem caracteres diferentes de letras
     * @param mensagem
     * @param sair se sair for false entao nao ira aceitar voltar, caso contario aceitara
     * @return uma string
     **/
    public static String Letras(String mensagem, boolean sair){
        BufferedReader receptor = new BufferedReader(new InputStreamReader(System.in));
        boolean clean = true;//O ajuda na saida do loop do while, ao inves de usar a condicao no if para sair do loop
        byte resp;
        String dado = "";
        try{
            do{
                resp = 0;
                do{
                    System.out.print("\n" + mensagem);
                    dado = receptor.readLine();
                    if(dado.isEmpty() || dado.matches("//d+") || dado.matches(".*//D+.*")){
                        System.out.print(Mensagens.invalidacao((byte) 1));
                        clean = false;
                    }else{
                        clean = true;
                    }
                }while(!clean);
                if(dado.equalsIgnoreCase("s")){
                        resp = verificaSaida(sair)? (byte)0 : (byte)2;
                }
            }while(resp == 2);
        }catch(IOException | NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return dado;
    }
  
    /**
     * verifica se a variavel caracteres diferentes de digitos  
     * @param mensagem
     * @param sair se sair for false entao nao ira aceitar voltar, caso contario aceitara
     * @return uma string
     **/
    public static String Digitos(String mensagem, boolean sair){
        BufferedReader receptor = new BufferedReader(new InputStreamReader(System.in));
        boolean clean;
        byte resp;
        String dado = "";
        try{
            do{
                resp = 0;
                do{
                    clean = true;
                    System.out.print("\n" + mensagem);
                    dado = receptor.readLine();
                    
                    if((dado.matches("[a-zA-Z&&[^sS]]") || dado.matches(".*//d+.*")) || dado.isEmpty()){
                        System.out.print(Mensagens.invalidacao((byte) 1));
                        clean = false;
                    }else{
                        if(!dado.matches("[sS]")){
                            clean = Integer.parseInt(dado) >= 0;
                        }
                    }
                }while(!clean);
                
                if(dado.equalsIgnoreCase("s")){
                   resp = verificaSaida(sair)? (byte)0 : (byte)2;
                }
            }while(resp == 2);
        }catch(NumberFormatException ex){
           System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(IOException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return dado;
    }
    
    /**
     * Verifica se o dado contem caracteres diferentes dum float
     * @param mensagem
     * @param sair se sair for false entao nao ira aceitar voltar, caso contario aceitara
     * @return o dado
     **/
     public static String Float(String mensagem, boolean sair){
        BufferedReader receptor = new BufferedReader(new InputStreamReader(System.in));
        boolean clean;
        byte resp;
        String dado = "";
        try{
            do{
                resp = 0;
                do{
                    clean = true;
                    System.out.print("\n" + mensagem);
                    dado = receptor.readLine();
                    
                    if(dado.isEmpty() || dado.matches("[a-zA-Z&&[^sS]]") || dado.matches(".*//d+.*") || dado.matches(".*[a-zA-Z]+.*")){
                        System.out.print(Mensagens.invalidacao((byte) 1));
                        clean = false;
                    }else{
                        if(!dado.matches("[sS]")){
                            clean = Float.parseFloat(dado) >= 0;
                        }
                    }
                }while(!clean);
                
                if(dado.equalsIgnoreCase("s")){
                    resp = verificaSaida(sair)? (byte)0 : (byte)2;
                }
            }while(resp == 2);
            
        }catch(IOException | NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return dado;
    }
    
     /**
      * Verifica se a opcao de saida e valida ou nao
      * @param sair 
      * @return true se for valida, false caso contrario
      */
     private static boolean verificaSaida(boolean sair){
         byte opcao;
         try{
                opcao = Byte.parseByte(intervaloOpcoes((byte) 7, (byte) 1, (byte) 2, sair));
                if(opcao == 1 && sair == false){
                     String str = "Nao e possivel sair, voltar ou cancelar a operacao.";
                     Mensagens.printMensagemNotavel(str, (byte) str.length());
                      return false;
                }else{
                    if(opcao == 1 && sair == true){
                        return true;
                    }
                } 
         }catch(NumberFormatException ex){
             ex.printStackTrace();
         }
         return false;
     }
     /**
     * Verificacao do intervalo- dado por min e max- do dado introduzido.  
     * @param mensagem em byte
     * @param min
     * @param max
     * @param sair se sair for false entao nao ira aceitar voltar, caso contario aceitara
     * @return um dado
     */
    public static String intervaloOpcoes(byte mensagem,byte min,byte max, boolean sair){
        
        boolean clean = true;
        String dado = "";
        int aux;
        try{
                System.out.print("\n" + Mensagens.sugestiva((byte) 2) + "\n");
                
                do{
                    dado = Digitos(Mensagens.menus(mensagem), sair);
                    
                    if(!dado.equalsIgnoreCase("s")){
                       aux = Integer.parseInt(dado);
                       
                        if(aux < min || aux > max){
                            System.out.print(Mensagens.invalidacao((byte) 2));
                            clean = false;
                        }else{
                            return String.valueOf(aux);
                        }
                    }
                }while(!clean && !dado.equalsIgnoreCase("s"));
                
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return dado;
    }
    
    /**
     * Verificacao do intervalo- dado por min e max- do dado introduzido   
     * @param mensagem em string
     * @param min
     * @param max
     * @param sair se sair for false entao nao ira aceitar voltar, caso contario aceitara
     * @return um dado
     */
    public static String intervaloOpcoes(String mensagem,int min,int max, boolean sair){
        boolean clean = true;
        String dado = "";
        int aux;
        try{
                System.out.print("\n" + Mensagens.sugestiva((byte) 2) + "\n");
                
                do{
                    dado = Digitos(mensagem, sair);
                    
                    if(!dado.equalsIgnoreCase("s")){
                       aux = Integer.parseInt(dado);
                       
                        if(aux < min || aux > max){
                            System.out.print(Mensagens.invalidacao((byte) 2));
                            clean = false;
                        }else{
                            return String.valueOf(aux);
                        }
                    }
                }while(!clean && !dado.equalsIgnoreCase("s"));
                
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return dado;
    }
    
    /**
     * Validacao duma string usando uma expressao regular
     * @param opcao
     * @param mensagem
     * @return o dado
     */
    public static String regex(String opcao, String mensagem){
        BufferedReader receptor = new BufferedReader(new InputStreamReader(System.in));
        boolean clean = true;
        String dado = "";
        try{
            String regex = "";
            
            switch(opcao){
                case "Contacto" :
                    regex = "8[0-9]{8}";
                    dado = Validacao.leituraSemValidacao(mensagem);
                    do{
                        if(!dado.matches(regex)){
                            System.out.print(Mensagens.invalidacao((byte) 3));
                            dado = receptor.readLine();
                            clean = false;
                        }else{
                            clean = true;
                        }
                    }while(!clean);
                    break;
                case "Validade": 
                    regex = "^((0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(20[23][0-9]))$";
                    dado = Validacao.leituraSemValidacao(mensagem);
                    do{
                        if(!dado.matches(regex)){
                            System.out.print(Mensagens.invalidacao((byte) 3));
                            dado = receptor.readLine();
                            clean = false;
                        }else{
                            clean = true;
                        }
                    }while(!clean);
                    break;
                case "Data de nascimento":
                    regex = "^((0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19[789][0-9]|200[012]))$";
                    dado = Validacao.leituraSemValidacao(mensagem);
                    do{
                        if(!dado.matches(regex)){
                            System.out.print(Mensagens.invalidacao((byte) 3));
                            dado = receptor.readLine();
                            clean = false;
                        }else{
                            clean = true;
                        }
                    }while(!clean);
                    break;
                case "Referencia":
                    regex = "^[A-Za-z]{2,3}[A-Za-z][0-9]{2}$";
                    do{
                        do{
                            dado = Validacao.leituraSemValidacao(mensagem);
                            if(!dado.matches(regex)){
                                System.out.print(Mensagens.invalidacao((byte) 3));
                                clean = false;
                            }else{
                                clean = true;
                            }
                        }while(!clean);
                    }while(!OperacoesCategoria.verificaRef(dado));//verifica se a referencia existe
                    break;
                case "B.I":
                    regex  = "[0-9]{12}[a-zA-Z]{1}";
                    dado = Validacao.leituraSemValidacao(mensagem);
                    do{
                        if(!dado.matches(regex)){
                            System.out.print(Mensagens.invalidacao((byte) 3));
                            dado = receptor.readLine();
                            clean = false;
                        }else{
                            clean = true;
                        }
                    }while(!clean);
                    break;
                case "Codigo utilizador":
                    regex  = "\\d\\d\\d\\d\\d";
                    do{
                        do{
                            dado = Validacao.leituraSemValidacao(mensagem);
                            if(!dado.matches(regex) && Integer.parseInt(dado) != 0){
                                System.out.println("Insira '0' caso queira sair.");
                                System.out.println("");
                                System.out.print(Mensagens.invalidacao((byte) 3));
                                clean = false;
                            }else{
                                clean = true;
                            }
                        }while(!clean);
                    }while(!OperacoesUsuario.verificaCodigo(dado));//verifica se o codigo existe
                    break;
                case "Tempo":
                    regex  = "\\d\\d:\\d\\d:\\d\\d";
                    do{
                        dado = Validacao.leituraSemValidacao(mensagem);
                        if(!dado.matches(regex)){
                            System.out.println("");
                            System.out.print(Mensagens.invalidacao((byte) 3));
                            clean = false;
                        }else{
                            clean = true;
                        }
                    }while(!clean);
                    break;
                case "QtdXreferencia"://Valida a String para o formato(quantidadeXreferencia)
                    regex = "^[0-9]+[xX][A-Za-z]{2,3}[A-Za-z][0-9]{2}$";
                    do{
                        do{
                            dado = Validacao.leituraSemValidacao(mensagem);
                            if(!dado.matches(regex) && !dado.isEmpty()){
                                System.out.print(Mensagens.invalidacao((byte) 3));
                                clean = false;
                            }else{
                                clean = true;
                            }
                        }while(!clean);
                        if(dado.isEmpty()){ break;}
                    }while(!OperacoesCategoria.verificaRef(VendaProdutos.retornaReferencia(dado)) && !dado.isEmpty());//verifica se a referencia existe
                    break;
                case "Comentario":
                    regex = "[Cc]\\d{3}";
                    do{
                        do{
                            dado = Validacao.leituraSemValidacao(mensagem);
                            if(!dado.matches(regex)){
                                System.out.println("");
                                System.out.print(Mensagens.invalidacao((byte) 3));
                                clean = false;
                            }else{
                                clean = true;
                            }
                        }while(!clean);
                    }while(!Comentario.verificaCodigo(dado));//verifica se o codigo existe
                    break;
            }
        }catch(IOException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return dado;
    }
    
    
    /**
     * Retorna as opcoes escolhidas
     * @param nrOpc numero de opcoes a serem lidas
     * @param opc o caso
     * @param sair se sair for false entao nao ira aceitar voltar, caso contario aceitara
     * @return As opcoes escolhidas
     */
    public static String opcoes(byte nrOpc,byte opc, boolean sair){
        byte opcao = 0;
        String aux = "";
        try{
           switch(opc){
               case 1:
                    do{
                        aux = leOpcoes(nrOpc, (byte) 5, sair);//recebe as opcoes escolhidas
                        
                        if(!aux.equalsIgnoreCase("s")){
                            atributosPreenche(aux, (byte) 1, nrOpc);
                            opcao = Byte.parseByte(intervaloOpcoes((byte) 8, (byte) 1, (byte) 2, false));
                        }
                    }while(opcao == 2);
                    break;
                case 2://
                    do{
                        aux = leOpcoes(nrOpc, (byte) 1, sair);//recebe as opcoes escolhidas
                        
                        if(!aux.equalsIgnoreCase("s")){
                            atributosPreenche(aux, (byte) 2, nrOpc);
                            opcao = Byte.parseByte(intervaloOpcoes((byte) 8, (byte) 1, (byte) 2, false));
                        }
                    }while(opcao == 2);
                    break;
                case 3:
                    do{
                        aux = leOpcoes(nrOpc, (byte) 21, sair);//recebe as opcoes escolhidas
                        
                        if(!aux.equalsIgnoreCase("s")){
                            atributosPreenche(aux, (byte) 3, nrOpc);
                            opcao = Byte.parseByte(intervaloOpcoes((byte) 8, (byte) 1, (byte) 2, false));
                        }
                    }while(opcao == 2);
                    break;
            }
        }catch(IOException | NumberFormatException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return aux;
    }
    
    /**
     *preenche a string 'atributos' com os atributos escolhidos e depois visualiza-os 
     * @param aux as opcoes
     * @param opc o caso
     * @param nrOpc numero de opcoes a serem lidas
     */
    private static void atributosPreenche(String aux,byte opc,byte nrOpc){
        String atr = "";
        switch(opc){
            case 1:
                
                for(byte i = 0; i < nrOpc; i++){
                    
                    switch(aux.charAt(i)){
                        case '1': atr += "Nome,";break;
                        case '2': atr += "Descricao,";break;
                        case '3': atr += "Marca,";break;
                        case '4': atr += "Categoria,";break;
                        case '5': atr += "Farbicante,";break;
                        case '6': atr += "Unidade,";break;
                    }
                }
                break;
            case 2:
                
                for(byte i = 0; i < nrOpc; i++){
                    
                    switch(aux.charAt(i)){
                        case '1': atr += "Nome,";break;
                        case '2': atr += "Descricao,";break;
                        case '3': atr += "Marca,";break;
                        case '4': atr += "Preco,";break;
                        case '5': atr += "Validade,";break;
                        case '6': atr += "Categoria,";break;
                        case '7': atr += "Fabricante,";break;
                        case '8': atr += "Stock,";break;
                        case '9': atr += "Unidade,";break;
                    }
                }
                break;
            case 3:
                
                for(byte i = 0; i < nrOpc; i++){
                    
                    switch(aux.charAt(i)){
                        case '1': atr += "Nome,";break;
                        case '2': atr += "Idade,";break;
                        case '3': atr += "Estado civil,";break;
                        case '4': atr += "Data de nascimento,";break;
                        case '5': atr += " B.I,";break;
                        case '6': atr += "Contacto,";break;
                        case '7': atr += "Contacto de emergencia,";break;
                        case '8': atr += "Morada,";break;
                        case '9': atr += "Nivel de acesso,";break;
                    }
                }
                break;
            default: System.out.print("Falhou...");
        }
        System.out.print("atributos = {" + atr.substring(0,atr.length() - 1) + "}");
    }
    
    /**
     * le os atributos escolhidos
     * @param nrOpc o numero de opcoes a srem lidas
     * @param mensagem
     * @param sair se sair for false entao nao ira aceitar voltar, caso contario aceitara
     * @return os atributos
     * @throws IOException 
     */
    private static String leOpcoes(byte nrOpc,byte mensagem, boolean sair)throws IOException{
       boolean clean = true;
       String aux;
       System.out.println("");
       System.out.println("Nr de atributos: " + nrOpc);//mostra  o numero de atributos a escolher
       aux = Digitos(Mensagens.menus(mensagem), sair);
       
       if(!aux.equalsIgnoreCase("s")){
           
            do{

                if(aux.length() > nrOpc || aux.length() < nrOpc){//se o tamanho da string for maior ou menor que o numero de opcoes
                     System.out.print(Mensagens.invalidacao((byte) 4));
                     System.out.println(nrOpc);//mostra  o numero de atributos a escolher
                     aux = Digitos(Mensagens.menus(mensagem), sair);
                     clean = false;
                }else{
                    clean = true;
                }
             }while(!clean);
       }
       
       return aux;
    }
}

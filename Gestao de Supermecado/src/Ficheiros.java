import java.io.*;
import java.util.Vector;

/**
 * Classe responsavel pela leitura e escrita nos ficheiros
 * 
 */
public class Ficheiros{
    
    private static Vector vector;
        
    //*********************Metodos para ficheiros de objectos******************************************
    
    public Ficheiros(){}
    /**
     * Faz a leitura do respectivo ficheiro de objectos de produtos
     * @param nome o nome do ficheiro
     * @return 
     */
    public static Vector FicheirosProduto(String nome){
        String local = "Ficheiros/Produtos/" + nome + ".txt";
        
        try{
            vector = new Vector();
            FileInputStream fichInput = new FileInputStream(local);
            ObjectInputStream objIn = new ObjectInputStream(fichInput);
            vector = (Vector) objIn.readObject();
            objIn.close();
            
        }catch(FileNotFoundException ex1){
            System.out.println("O ficheiro " + local + " nao foi encontrado.");
        }catch(NumberFormatException ex2){
            System.out.print(ex2.getMessage());
        }catch(ClassNotFoundException ex4){
            System.out.print("Verifique a existencia da classe.");
        }catch(NullPointerException ex3){
            System.out.print(ex3.getMessage());
        }catch(EOFException ex1){
            System.err.println("Uma2 excepcao EOFException foi apanhada: " + ex1.getMessage());
            ex1.printStackTrace();
        }catch(InvalidClassException ex){
            System.err.println("Um erro de InvalidClassException ocorreu: " + ex.getMessage());
            ex.printStackTrace();
        }catch(IOException ex){
            System.err.println("Uma2 excepcao IOException foi apanhada: " + ex.getMessage());
            ex.printStackTrace();
        }
        return vector;
    }
    
    /**
     * Faz a escrita no respectivo ficheiro de objectos de produtos
     * @param produtos os produtos
     * @param nome o nome do ficheiro
     * @param opc a opcao
     */
    public static void toProdutosFiles(Vector produtos,String nome, byte opc){
        vector = new Vector();
        String local = "Ficheiros/Produtos/" + nome + ".txt";
        try{
            
            switch(opc){
                case 1://quando se trata de novos produtos
                    if(verificaExistencia(local).length() != 0){
                        vector = FicheirosProduto(nome);//recebe os produtos do ficheiro
                        vector.addAll(produtos);
                    }else{
                        vector = produtos;
                    }
                    break;
                case 2://quando nao se trata de novos produtos
                    vector = produtos;
                    break;
            }
            
            FileOutputStream fichOut = new FileOutputStream(local);
            ObjectOutputStream objOut = new ObjectOutputStream(fichOut);
            objOut.writeObject(vector);
            objOut.close(); 
            
        }catch(FileNotFoundException ex1){
            System.err.print("O ficheiro " + local + " nao foi encontrado.");
        }catch(NumberFormatException ex2){
            System.err.print(ex2.getMessage());
        }catch(InvalidClassException ex){
            System.err.println("Um erro de InvalidClassException ocorreu: " + ex.getMessage());
            ex.printStackTrace();
        }catch(IOException ex3){
            System.err.println("Uma1 excepcao IOException foi apanhada: " + ex3.getMessage());
            ex3.printStackTrace();
        }catch(IndexOutOfBoundsException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Leitura do respectivo ficheiro de objectos
     * @param caso o ficheiro a ser lido
     * @return um vector
     */
    public static Vector leituraFicheiro(String caso){
        vector = new Vector();
        String local = "";
        try{
            
            switch(caso.toLowerCase()){
                case "comentarios":
                    local = "Ficheiros/Comentarios/Comentarios.txt";
                    break;
                case "vendas":
                    local = "Ficheiros/Registo de compras/Registo de compras.txt";
                    break;
                case "usuarios":
                    local = "Ficheiros/Funcionarios/Funcionarios.txt";
                    break;
            }
            
            if(estaVazio(local)){
                return new Vector();
            }else{
                FileInputStream fichInput = new FileInputStream(local);
                ObjectInputStream objIn = new ObjectInputStream(fichInput);
                vector = (Vector) objIn.readObject();
                objIn.close();
            }
            
        }catch(FileNotFoundException ex){
            System.out.print("O ficheiro " + local + " nao foi encontrado.");
        }catch(ClassNotFoundException ex){
            System.out.print("Verifique a existencia da classe.");
        }catch(NullPointerException | IOException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return vector;
    }
    
    /**
     * Escrita no respectivo ficheiro de objectos
     * @param vector
     * @param caso o ficheiro a ser escrito
     */
    public static void toFicheiro(Vector vector, String caso){
        String local = "";
        try{
            
            switch(caso.toLowerCase()){
                case "comentarios":
                    local = "Ficheiros/Comentarios/Comentarios.txt";
                    break;
                case "vendas":
                    local = "Ficheiros/Registo de compras/Registo de compras.txt";
                    break;
                case "usuarios":
                    local = "Ficheiros/Funcionarios/Funcionarios.txt";
                    break;
            }
            
            FileOutputStream fichOut = new FileOutputStream(verificaExistencia(local));
            ObjectOutputStream objOut = new ObjectOutputStream(fichOut);
            objOut.writeObject(vector);
            objOut.close();   
            
        }catch(FileNotFoundException ex1){
            System.out.print("O ficheiro " + local + " nao foi encontrado.");
        }catch(IOException ex){
             System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    //*********************Metodos para ficheiros de texto******************************************
    
    /**
     * Leitura do respectivo fichero de txt
     * @param nome o nome do ficheiro
     * @return 
     */
    public static Vector Ficheirostxt(String nome){
        String local = "Ficheiros/Ficheiros txt/" + nome + ".txt",
               umaLinha = "";
        String aux[];
        
        try{
            
            if(estaVazio(local)){
                return new Vector();
            }else{
                vector = new Vector();
                FileReader fichReader = new FileReader(local);
                BufferedReader bufReader = new BufferedReader(fichReader);
                umaLinha = bufReader.readLine();
                bufReader.close();
                aux = umaLinha.split(";");

                for(short i = 0; i < aux.length; i++){
                    vector.addElement(new String(aux[i]));
                    vector.trimToSize();
                }
            }
            
            
        }catch(FileNotFoundException ex1){
            System.out.print("O ficheiro " + local + " nao foi encontrado.");
        }catch(NumberFormatException | IOException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return vector;
    }
   
        
    /**
     * Escrita no respectivo ficheiro de texto
     * @param arrayString
     * @param nome o nome do ficheiro a ser escrito
     */
    public static void toFicheiroTxt(Vector arrayString,String nome){
        String local = "Ficheiros/Ficheiros txt/" + nome + ".txt",
                umaLinha = "";
        try{
            
            if(!arrayString.isEmpty()){
                
                for(byte i = 0; i < arrayString.size() - 1; i++){
                    umaLinha += arrayString.elementAt(i) + ";";
                }
                umaLinha += arrayString.elementAt(arrayString.size() - 1);
            }
            
            FileWriter fichWriter = new FileWriter(verificaExistencia(local));
            BufferedWriter bufWriter = new BufferedWriter(fichWriter);
            bufWriter.write(umaLinha);
            bufWriter.close(); 
            
        }catch(FileNotFoundException ex){
            System.out.print("O ficheiro " + local + " nao foi encontrado.");
        }catch(NumberFormatException ex){
             System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(IOException ex){
             System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * verifica a existencia de um ficheiro
     * @param local
     * @return O ficheiro
     */
    private static File verificaExistencia(String local){
        File ficheiro = new File(local);
        
        try{

            if(!ficheiro.exists()){
                boolean createNewFile = ficheiro.createNewFile();
            }
        }catch(IOException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return ficheiro;
    }
    
    /**
     *verifica se um ficheiro esta vazio 
     * @param local
     * @return true se o ficheiro estiver vazio, false caso contrario
     */
    private static boolean estaVazio(String local){
        
        File ficheiro = new File(local);

        if(ficheiro.length() == 0){
            return true;
        }else{
            return false;
        }
    }
}

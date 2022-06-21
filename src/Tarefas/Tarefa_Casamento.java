/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarefas;

/**
 *
 * @author SIMIAO CANZE
 */
import Tarefas.Validacoes;
import Modelos.Casamento;
import java.io.*;
import java.util.*;
public class Tarefa_Casamento
{
    private Casamento cs;
    private Validacoes val;
    private Vector casamento;
    private Casamento [] casar, casarNOiva, casarPadr, casarMadr, casarNoivo;
    private int contador, contCad, somaCont, cont;
    
    public Tarefa_Casamento()
    {
        casar = new Casamento[1000];
        casarNoivo = new Casamento[1000];
        casarMadr = new Casamento[1000];
        casarPadr = new Casamento[1000];
        casarNOiva = new Casamento[1000];
        contador=0;
        contCad=0;
        somaCont=0;
        cont=0;
        
        backUpCasamento();
                
    }
    
    /*Este metodo, busca os dados armazenados no ficheiro de texto casamento
    e armazena-os nos arrays de objecto
    */
    
    public void backUpCasamento()
    {
        Tarefas.Validacoes vc = new Tarefas.Validacoes();
        String ficheiro = "Casamento.txt";
        StringTokenizer st;
        String linha = ""; 
        
        String nomNoivo, documentNoivo, nomNoiva, documentNoiva, nomPadrinho, documentPadrinho, nomMadrinha,documentMadinha, lerLocal,lerStatus, lerData, localP="";
        String apelidoNoivo, apelidoNoiva, apelidoPadr, apelidoMadr;
        double lerPagamento;
        int lerID, divorciarID;
        char genero =' ';

        try
        {
                FileReader fr = new FileReader (ficheiro);
                BufferedReader brf = new BufferedReader (fr);
                BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
                
                linha = brf.readLine();
                while (linha!=null)
                {
                    st = new StringTokenizer (linha, " | ");
                    
                    lerID = Integer.parseInt(st.nextToken());
                    
                    nomNoivo = st.nextToken();
                    
                    apelidoNoivo = st.nextToken();

                    documentNoivo = st.nextToken();

                    nomNoiva = st.nextToken();
                    
                    apelidoNoiva = st.nextToken();

                    documentNoiva = st.nextToken();

                    nomPadrinho = st.nextToken();
                    
                    apelidoPadr = st.nextToken();

                    documentPadrinho = st.nextToken();

                    nomMadrinha = st.nextToken();
                    
                    apelidoMadr = st.nextToken();

                    documentMadinha = st.nextToken();

                    lerLocal = st.nextToken();
                    
                    lerData = st.nextToken();
                    
                    lerPagamento = Double.parseDouble(st.nextToken());
                    
                    lerStatus = st.nextToken();
                    
                    casarNoivo [contCad] = new Modelos.Casamento(lerID, nomNoivo, apelidoNoivo, documentNoivo,genero, lerLocal, lerData, lerPagamento,localP, lerStatus);
                    casarNOiva [contCad] = new Casamento(nomNoiva, apelidoNoiva, documentNoiva);
                    casarMadr [contCad] = new Casamento(nomMadrinha, apelidoMadr, documentMadinha);
                    casarPadr [contCad] = new Casamento(nomPadrinho, apelidoPadr, documentPadrinho);
                    
                    casarNoivo[contCad].setLocalCasamento(lerLocal);
                    casarNoivo [contCad].setStatus(lerStatus);
                    
                    contCad++;

                    linha = brf.readLine();
                }
                fr.close();

        }
        catch (NumberFormatException nfe)
        {
                System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
        }
        catch(FileNotFoundException f)
        {
                System.err.println("ERRO: FICHEIRO NAO ENCONTRADO! "+f.getMessage());
        }
        catch(IOException ex)
        {
                System.err.println("Error: \n" + ex.getMessage());
        }
    }
    
    /*Este metodo le os dados armazenados no ficheiro de texto nascimento
    e e armazena-os em um ficheiro para posterior uso.
    */
    public void lerNascimento()
    {
        StringTokenizer st;
        Casamento cs = new Casamento();
        Tarefas.Validacoes val = new Tarefas.Validacoes();
        String nome, apelido, localNasc, bi, ficheiro = "Nascimento.txt", linha = "", local;
        String nomePai, nomeMae, apelidoPai, apelidoMae, biPai, biMae, data="", status = "Casados";
        char resposta = ' ', genero=' ';
        int codigo, op;
        double valorPagar;
        
        
        try
        {
            FileReader fr = new FileReader (ficheiro);
            BufferedReader brf = new BufferedReader (fr);
            BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
           
            linha = brf.readLine();
            while(linha != null)
            {
                st = new StringTokenizer(linha , " | ");
                
                codigo = Integer.parseInt(st.nextToken());
                
                nome = st.nextToken();
                
                apelido = st.nextToken();
                
                bi = st.nextToken();
                
                genero = st.nextToken().charAt(0);
                
                localNasc = st.nextToken();
                
                data = st.nextToken();
                
                valorPagar = Double.parseDouble(st.nextToken());
                
                nomePai = st.nextToken();
                
                apelidoPai =st.nextToken();
                
                biPai = st.nextToken();
                
                nomeMae = st.nextToken();
                
                apelidoMae = st.nextToken();
                
                biMae = st.nextToken();
                
                status = st.nextToken();
                
                if(status.equalsIgnoreCase("V"))
                casar [contador] = new Casamento(codigo, nome, apelido, bi);
                
                contador++;
                
                linha = brf.readLine();
            }
            
            fr.close();
        }
        catch (NumberFormatException n)
        {
                System.err.println ("Dados mal inseridos!\n"+ n.getMessage());
        }
        catch (FileNotFoundException fn)
        {
                System.err.println ("FICHEIRO NAO ENCONTRADO!\n"+ fn.getMessage());
        }
        catch (IOException ex)
        {
                System.err.println ("ERROR: "+ex.getMessage());                             
        }
    }
    
    /*
    Visualizacao de toda informacao armazenada no ficheiro de texto casamento
    */
    public void visualizarTodaInf()
    { 
        Casamento cs = new Casamento();
        
        String nomeNoivo="", nomeNoiva="", nomePadr="", nomeMadr="", docNoivo="", docNoiva="", docPadr="", docMadr="", local="", status="", data="", localP="";
        String apelidoNoivo="", apelidoNoiva="", apelidoPadrinho="", apelidoMadrinha="";
        int cod=0;
        double valor=0;
        char genero=' ';
        
        for(int i=0; i<contCad; i++)
        {
            cod = casarNoivo[i].getCodigo();
            nomeNoivo= casarNoivo[i].getNome();
            apelidoNoivo = casarNoivo[i].getApelido();
            local=casarNoivo[i].getLocalCasamento();
            data = casarNoivo[i].getData();
            
            nomeNoiva = casarNOiva[i].getNome();
            apelidoNoiva = casarNOiva[i].getApelido();
            
            nomePadr = casarPadr[i].getNome();
            apelidoPadrinho = casarPadr[i].getApelido();
            
            nomeMadr = casarMadr[i].getNome();
            apelidoMadrinha = casarMadr[i].getApelido();
            
            System.out.println("Codigo: "+cod+" ; Nome do casal: "+nomeNoivo+" "+apelidoNoivo+" e "+nomeNoiva+" "+apelidoNoiva);
            System.out.println("Padrinos: "+nomePadr+" "+apelidoPadrinho+" e "+nomeMadr+" "+apelidoMadrinha);
            System.out.println("Tendo se casado aos "+data+" ; Local "+local);
            System.out.println("");
            
        }
        
    }
    
    /*
    Visualiza apenas informacao dos casados que se encontram no ficheiro casamento
    */
    public void visualizarCasados()
    { 
        Casamento cs = new Casamento();
        
        String nomeNoivo="", nomeNoiva="", nomePadr="", nomeMadr="", docNoivo="", docNoiva="", docPadr="", docMadr="", local="", status="", data="", localP="";
        String apelidoNoivo="", apelidoNoiva="", apelidoPadrinho="", apelidoMadrinha="";
        int cod=0;
        double valor=0;
        char genero=' ';
        
        System.out.println("RELACTORIO DOS CASADOS: ");
        
        for(int i=0; i<contCad; i++)
        {
            cod = casarNoivo[i].getCodigo();
            nomeNoivo= casarNoivo[i].getNome();
            apelidoNoivo = casarNoivo[i].getApelido();
            local=casarNoivo[i].getLocalCasamento();
            data = casarNoivo[i].getData();
            
            nomeNoiva = casarNOiva[i].getNome();
            apelidoNoiva = casarNOiva[i].getApelido();
            
            nomePadr = casarPadr[i].getNome();
            apelidoPadrinho = casarPadr[i].getApelido();
            
            nomeMadr = casarMadr[i].getNome();
            apelidoMadrinha = casarMadr[i].getApelido();
            
            if(casarNoivo[i].getStatus().equalsIgnoreCase("Casados"))
            {
                System.out.println("Codigo: "+cod+" ; Nome do casal: "+nomeNoivo+" "+apelidoNoivo+" e "+nomeNoiva+" "+apelidoNoiva);
                System.out.println("Padrinos: "+nomePadr+" "+apelidoPadrinho+" e "+nomeMadr+" "+apelidoMadrinha);
                System.out.println("Tendo se casado aos "+data+" ; Local "+local);
                System.out.println("");
            }
            
        }
        
    }
    
    /*
    Visualiza apenas informacao dos divorciados que se encontram no ficheiro casamento
    */
    public void visualizarDivorciados()
    { 
        Casamento cs = new Casamento();
        
        String nomeNoivo="", nomeNoiva="", nomePadr="", nomeMadr="", docNoivo="", docNoiva="", docPadr="", docMadr="", local="", status="", data="", localP="";
        String apelidoNoivo="", apelidoNoiva="", apelidoPadrinho="", apelidoMadrinha="";
        int cod=0;
        double valor=0;
        char genero=' ';
        
        System.out.println("RELACTORIO DOS CASAIS DIVORCIADOS: ");
        
        for(int i=0; i<contCad; i++)
        {
            cod = casarNoivo[i].getCodigo();
            nomeNoivo= casarNoivo[i].getNome();
            apelidoNoivo = casarNoivo[i].getApelido();
            local=casarNoivo[i].getLocalCasamento();
            data = casarNoivo[i].getData();
            
            nomeNoiva = casarNOiva[i].getNome();
            apelidoNoiva = casarNOiva[i].getApelido();
            
            nomePadr = casarPadr[i].getNome();
            apelidoPadrinho = casarPadr[i].getApelido();
            
            nomeMadr = casarMadr[i].getNome();
            apelidoMadrinha = casarMadr[i].getApelido();
            
            if(casarNoivo[i].getStatus().equalsIgnoreCase("Divorciados"))
            {
                System.out.println("Codigo: "+cod+" ; Nome do casal: "+nomeNoivo+" "+apelidoNoivo+" e "+nomeNoiva+" "+apelidoNoiva);
                System.out.println("Padrinos: "+nomePadr+" "+apelidoPadrinho+" e "+nomeMadr+" "+apelidoMadrinha);
                System.out.println("Tendo se casado aos "+data+" ; Local "+local);
                System.out.println("");
            }
            
        }
        
    }
    
    /*
    O metodo abaico verifica se a pessoa e de nacionalidade mocambicana,
    caso seja, insere-se apenas os seu codigo e a sua respectiva informacao e buscada 
    no fichero de texto casamento, caso contrario os dados sao registados.
    */
    public void casar_Codigo()
    {
        Validacoes val = new Validacoes();
     
        String nomeNoivo="", nomeNoiva="", nomePadr="", nomeMadr="", docNoivo="", docNoiva="", docPadr="", docMadr="", local="", status="", data="", localP="";
        String apelidoNoivo="", apelidoNoiva="", apelidoPadrinho="", apelidoMadrinha="";
        int codNoivo, codNoiva, codPadrinho, codMadrinha, localCasam;
        int cod=999+contCad, codComparador=0, nr=0, incr=0;
        double valor=0;
        char resp=' ', genero=' ';
        Date dataRecibo;
        String ficheiro = "Casamento.txt";
        
        
        try
        {
            FileWriter fw = new FileWriter (ficheiro);
            BufferedWriter bw = new BufferedWriter (fw);
            
            dataRecibo = val.buscarData();
            
            do
            {
                nr++;
                System.out.println(nr+"º Registo: \n");
                
                cod++;
                System.out.println("Casal numero: "+cod);
                resp = val.validarResposta("o noivo e de nacionalidade mocambicana? S (Sim) || N (Nao): ");

                
                if(resp == 'S' || resp == 's')
                {
                    do
                    {
                        codNoivo = val.validarInt("Insira o codigo do noivo: ", 1000, 2000);

                        for(int i=0; i<contador; i++)
                        {
                            if(casar[i] != null)
                            if(casar[i].getCodigo()==codNoivo)
                            {
                                codNoivo=casar[i].getCodigo();
                                nomeNoivo=casar[i].getNome();
                                apelidoNoivo=casar[i].getApelido();
                                docNoivo=casar[i].getNrDocumento();
                            }
                        }
                        
                        incr++;
                        
                        if(casar[incr]==null && nomeNoivo.equalsIgnoreCase(""))
                        {
                            System.err.println("ERROR: codigo invalido!"
                                    + "\nO codigo inserido pertence a um inativo, por favor, certifique-se do codigo e tente novamente. ");
                        }
                    }
                    while(casar[incr]==null && nomeNoivo.equalsIgnoreCase(""));
                }
                else
                {
                    System.out.println("DADOS DO NOIVO: ");
                    nomeNoivo = val.validarString("Insira o nome: ", 2, 15);
                    apelidoNoivo = val.validarString("Insira o apelido: ", 2, 10);
                    docNoivo = val.validarDocumento();

                }


                resp = val.validarResposta("A noiva e de nacionalidade mocambicana? S (Sim) || N (Nao): ");

                if(resp == 'S' || resp == 's')
                {
                    incr=0;
                    
                    do
                    {
                        codNoiva = val.validarInt("Insira o codigo da noiva: ", 1000, 2000);

                        for(int i=0; i<contador; i++)
                        {
                            if(casar[i] != null)
                            if(casar[i].getCodigo()==codNoiva)
                            {
                                codNoiva=casar[i].getCodigo();
                                nomeNoiva=casar[i].getNome();
                                apelidoNoiva=casar[i].getApelido();
                                docNoiva=casar[i].getNrDocumento();
                            }
                        }
                        
                        incr++;
                        
                        if(casar[incr]==null && nomeNoivo.equalsIgnoreCase(""))
                        {
                            System.err.println("ERROR: codigo invalido!"
                                    + "\nO codigo inserido pertence a um inativo, por favor, certifique-se do codigo e tente novamente. ");
                        }
                    }
                    while(casar[incr]==null && nomeNoiva.equalsIgnoreCase(""));
                }
                else
                {
                    System.out.println("DADOS DA NOIVA: ");
                    nomeNoiva = val.validarString("Insira o nome: ", 2, 15);
                    apelidoNoiva = val.validarString("Insira o apelido: ", 2, 10);
                    docNoiva = val.validarDocumento();

                }

                resp = val.validarResposta("O Padrinho e de nacionalidade mocambicana? S (Sim) || N (Nao): ");

                if(resp == 'S' || resp == 's')
                {
                    incr=0;
                    
                    do
                    {
                        codPadrinho = val.validarInt("Insira o codigo do padrino: ", 1000, 2000);

                        for(int i=0; i<contador; i++)
                        {
                            if(casar[i] != null)
                            if(casar[i].getCodigo()==codPadrinho)
                            {
                                codPadrinho=casar[i].getCodigo();
                                nomePadr=casar[i].getNome();
                                apelidoPadrinho=casar[i].getApelido();
                                docPadr=casar[i].getNrDocumento();
                            }
                        }
                        
                        incr++;
                        
                        if(casar[incr]==null && nomePadr.equalsIgnoreCase(""))
                        {
                            System.err.println("ERROR: codigo invalido!"
                                    + "\nO codigo inserido pertence a um inativo, por favor, certifique-se do codigo e tente novamente. ");
                        }
                    }
                    while(casar[incr]==null && nomePadr.equalsIgnoreCase(""));
                }
                else
                {
                    System.out.println("DADOS DO Padrinho: ");
                    nomePadr = val.validarString("Insira o nome: ", 2, 15);
                    apelidoPadrinho = val.validarString("Insira o apelido: ", 2, 10);
                    docPadr = val.validarDocumento();

                }

                resp = val.validarResposta("A Madrinha e de nacionalidade mocambicana? S (Sim) || N (Nao): ");

                if(resp == 'S' || resp == 's')
                {
                    incr=0;
                    
                    do
                    {
                        codPadrinho = val.validarInt("Insira o codigo da madrinha: ", 1000, 2000);

                        for(int i=0; i<contador; i++)
                        {
                            if(casar[i] != null)
                            if(casar[i].getCodigo()==codPadrinho)
                            {
                                codComparador=casar[i].getCodigo();
                                nomeMadr=casar[i].getNome();
                                apelidoMadrinha=casar[i].getApelido();
                                docMadr=casar[i].getNrDocumento();
                            }
                        }
                        
                        incr++;
                        
                        if(casar[incr]==null && nomeMadr.equalsIgnoreCase(""))
                        {
                            System.err.println("ERROR: codigo invalido!"
                                    + "\nO codigo inserido pertence a um inativo, por favor, certifique-se do codigo e tente novamente. ");
                        }
                    }
                    while(casar[incr]==null && nomeMadr.equalsIgnoreCase(""));
                }
                else
                {
                    System.out.println("DADOS DA MADRINHA: ");
                    nomeMadr = val.validarString("Insira o nome: ", 2, 15);
                    apelidoMadrinha = val.validarString("Insira o apelido: ", 2, 10);
                    docMadr = val.validarDocumento();

                }
                
                localCasam = val.validarInt(" Possiveis locais para casar: \n 1.Palacio \n 2.Conservatoria \n 3.Ao domicilio ", 1, 3);
                if(localCasam == 1)
                {
                    local = "Palacio";
                    valor = 6000;
                }

                if(localCasam == 2)
                {
                    local = "Conservatoria";
                    valor = 4500;
                }

                if(localCasam == 3)
                {
                    local = "Domocilio";
                    valor = 10000;
                }

                data = val.validarData();

                status = "Casados";
                
                resp = val.validarResposta("Tem certeza de que deseja casar o casal: \n"
                                            +nomeNoivo+" "+apelidoNoivo+" e  "
                                            +nomeNoiva+" "+apelidoNoiva+"\n"
                                            +"Cujos padrinhos sao: \n"
                                            +nomePadr+" "+apelidoPadrinho+" e "
                                            +nomeMadr+" "+apelidoMadrinha+"?");
                
                if(resp == 'S' || resp == 's')
                {
                    
                    somaCont = contCad+cont;
                    casarNoivo [somaCont] = new Casamento(cod, nomeNoivo, apelidoNoivo, docNoivo,genero, local, data, valor,localP, status);
                    casarNOiva [somaCont] = new Casamento(nomeNoiva, apelidoNoiva, docNoiva);
                    casarMadr [somaCont] = new Casamento(nomeMadr, apelidoMadrinha, docMadr);
                    casarPadr [somaCont] = new Casamento(nomePadr, apelidoPadrinho, docPadr);
                    
                    casarNoivo[somaCont].setLocalCasamento(local);
                    casarNoivo [somaCont].setStatus(status);
                    
                    System.out.println("RECIBO NUMERO: "+somaCont);
                    System.out.println("");
        
                    System.out.println("_______________________________________________");
                    System.out.println("|       Recibo de certidao de casamento ");
                    System.out.println("|");
                    System.out.println("| Em nome de "+nomeNoivo+" "+apelidoNoivo);
                    System.out.println("| e de "+nomeNoiva+" "+apelidoNoiva);
                    System.out.println("| ");
                    System.out.println("| Valor a pagar: "+valor);
                    System.out.println("| ");
                    System.out.println("| A certidao devera ser levantada depois ");
                    System.out.println("| de 15 dias uteis.");
                    System.out.println("|");
                    System.out.println("| "+dataRecibo);
                    System.out.println("|_______________________________________________");
                    
                    cont++;
                    
                    System.out.println("DADOS ARMAZENADOS COM SUCESSO!!!");
                }
                else
                    System.err.println("Cadastro cancelado com sucesso!");
                
                resp = val.validarResposta("DESEJA EFECTUAR MAIS ALGUMA OPERACAO? S (Sim) || N (Nao): ");
                
            }
            while(resp=='s' || resp == 'S');
            
            
        }
        catch (NumberFormatException n)
        {
                System.err.println ("Dados mal inseridos!\n"+ n.getMessage());
        }
        catch (FileNotFoundException fn)
        {
                System.err.println ("FICHEIRO NAO ENCONTRADO!\n"+ fn.getMessage());
        }
        catch (IOException ex)
        {
                System.err.println ("ERROR: "+ex.getMessage());                             
        }
        
        
    }
    
    /*
    O metodo abaixo, incrementa registos na informacao lida pelo metodo backUpCasamento
    e depois devolve toda informacao para o ficheiro de texto
    */
    public void incrementar_Casamento()
    {
        Validacoes val = new Validacoes();
        Casamento cs = new Casamento();
        String nomeNoivo="", nomeNoiva="", nomePadr="", nomeMadr="", docNoivo="", docNoiva="", docPadr="", docMadr="", local="", status="", data="", localP="";
        String apelidoNoivo="", apelidoNoiva="", apelidoPadrinho="", apelidoMadrinha="";
        int codNoivo, codNoiva, codPadrinho, codMadrinha, localCasam;
        int cod=0, codComparador=0, contar=0;
        double valor=0;
        char resp=' ', genero=' ';
        String ficheiro = "Casamento.txt";
        
        
        try
        {
            FileWriter fw = new FileWriter (ficheiro);
            BufferedWriter bw = new BufferedWriter (fw);
            
           do
           {
                cod = casarNoivo[contar].getCodigo();
                nomeNoivo=casarNoivo[contar].getNome();
                apelidoNoivo=casarNoivo[contar].getApelido();
                docNoivo=casarNoivo[contar].getNrDocumento();
                local=casarNoivo[contar].getLocalCasamento();
                valor=casarNoivo[contar].getValorPagar();
                status=casarNoivo[contar].getStatus();
                data=casarNoivo[contar].getData();
                
                nomeMadr= casarMadr[contar].getNome();
                apelidoMadrinha=casarMadr[contar].getApelido();
                docMadr=casarMadr[contar].getNrDocumento();
                
                nomePadr=casarPadr[contar].getNome();
                apelidoPadrinho=casarPadr[contar].getApelido();
                docPadr=casarPadr[contar].getNrDocumento();
                
                nomeNoiva=casarNOiva[contar].getNome();
                apelidoNoiva=casarNOiva[contar].getApelido();
                docNoiva=casarNOiva[contar].getNrDocumento();
                
                bw.write(cod + " | "+nomeNoivo +" | "+apelidoNoivo+ " | "+docNoivo + " | "+nomeNoiva + " | "
                        +apelidoNoiva+" | "+docNoiva + " | "+nomePadr + " | "+apelidoPadrinho+" | "+docPadr 
                        + " | "+nomeMadr + " | "+apelidoMadrinha+" | "+docMadr + " | "+local+ " | "+data 
                        + " | "+valor + " | "+status + " | ");
                
                bw.newLine();
                contar++;
           }
           while(contar<=somaCont);
           
            bw.close();
        }
        catch (NumberFormatException n)
        {
                System.err.println ("Dados mal inseridos! (Insercao) \n"+ n.getMessage());
        }
        catch (FileNotFoundException fn)
        {
                System.err.println ("FICHEIRO NAO ENCONTRADO! (Insercao) \n"+ fn.getMessage());
        }
        catch (IOException ex)
        {
                System.err.println (ex.getMessage());                             
        }
    }
    
   
    
    /*
    Inpressao da certidao de casamento.
    */
    public void certidao_Casamento()
    {
        Validacoes val = new Validacoes();
        Casamento cs = new Casamento();
        String nomeNoivo="", nomeNoiva="", nomePadr="", nomeMadr="", docNoivo="", docNoiva="", docPadr="", docMadr="", local="", status="", data="", localP="";
        String apelidoNoivo="", apelidoNoiva="", apelidoPadrinho="", apelidoMadrinha="";
        int codNoivo, codNoiva, codPadrinho, codMadrinha, localCasam;
        int cod=0, codComparador=0, contar=0;
        double valor=0;
        char resp=' ', genero=' ';
        Date dataActual;
        String ficheiro = "Casamento.txt";
        
        try
        {
            dataActual=val.buscarData();
            codComparador = val.validarInt("Insira o codigo do casal que deseja imprimir a certidao de casamento: ", 1000, 1000+contCad);

            for(int i = 0; i<contCad; i++)
            {
                if(codComparador == casarNoivo[i].getCodigo())
                {
                    cod = casarNoivo[i].getCodigo();
                    nomeNoivo=casarNoivo[i].getNome();
                    apelidoNoivo=casarNoivo[i].getApelido();
                    docNoivo=casarNoivo[i].getNrDocumento();
                    local=casarNoivo[i].getLocalCasamento();
                    valor=casarNoivo[i].getValorPagar();
                    status=casarNoivo[i].getStatus();
                    data=casarNoivo[i].getData();

                    nomeMadr= casarMadr[i].getNome();
                    apelidoMadrinha=casarMadr[i].getApelido();
                    docMadr=casarMadr[i].getNrDocumento();

                    nomePadr=casarPadr[i].getNome();
                    apelidoPadrinho=casarPadr[i].getApelido();
                    docPadr=casarPadr[i].getNrDocumento();

                    nomeNoiva=casarNOiva[i].getNome();
                    apelidoNoiva=casarNOiva[i].getApelido();
                    docNoiva=casarNOiva[i].getNrDocumento();

                    System.out.println("________________________________________________________________________________ ");
                    System.out.println("|		  REPUBLICA DE MOCAMBIQUE				             ");
                    System.out.println("|                 GOVERNO DA CIDADE                                              ");
                    System.out.println("|		   ASSENTO DE CASAMENTO					             ");
                    System.out.println("|									             ");
                    System.out.println("| AOs "+ data +" no "+local+                                               "");
                    System.out.println("| Compareceram os nubentes: "+ nomeNoivo+" "+apelidoNoivo+                      "");
                    System.out.println("| e "+nomeNoiva+" "+apelidoNoiva+                                               "");
                    System.out.println("|                                                                                ");
                    System.out.println("| Os nubentes, perante mim acima mencionado e as testemunhas adiante nomeados,   ");
                    System.out.println("| celebram o seu casamento CIVIL, tendo declarado, previamente, que o celebravam ");
                    System.out.println("| por sua livre vontade e com convencao antenupcial com comunhao de bens.        ");
                    System.out.println("|                                                                                ");
                    System.out.println("| A nubente declarou adoptar os apelido "+apelidoNoivo+ " do marido              ");
                    System.out.println("| Assento Nr: "+cod+                                                            "");
                    System.out.println("| Foram testemunhas "+nomePadr+" "+apelidoPadrinho+                             "");
                    System.out.println("| e "+nomeMadr+" "+apelidoMadrinha+                                             "");
                    System.out.println("|									         ");
                    System.out.println("|                                O Director                                      ");
                    System.out.println("|			____________________________                              ");
                    System.out.println("|									          ");
                    System.out.println("| Data: "+dataActual+"                                                           ");
                    System.out.println("|									          ");
                    System.out.println("_________________________________________________________________________________");

                }
            }
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
        }
         
    }

    
    public void certidao_Divorcio()
    {
        Validacoes val = new Validacoes();
        Casamento cs = new Casamento();
        String nomeNoivo="", nomeNoiva="", nomePadr="", nomeMadr="", docNoivo="", docNoiva="", docPadr="", docMadr="", local="", status="", data="", localP="";
        String apelidoNoivo="", apelidoNoiva="", apelidoPadrinho="", apelidoMadrinha="";
        int codNoivo, codNoiva, codPadrinho, codMadrinha, localCasam;
        int cod=0, codComparador=0, contar=0;
        double valor=0;
        char resp=' ', genero=' ';
        Date dataActual;
        try
        {
            dataActual=val.buscarData();
            codComparador = val.validarInt("Insira o codigo do casal que deseja imprimir a certidao de divorcio: ", 1000, 1000+cont);

            for(int i = 0; i<contCad; i++)
            {
                if(casarNoivo[i].getStatus().equalsIgnoreCase("Divorciados"))
                if(codComparador == casarNoivo[i].getCodigo())
                {
                    cod = casarNoivo[i].getCodigo();
                    nomeNoivo=casarNoivo[i].getNome();
                    apelidoNoivo=casarNoivo[i].getApelido();
                    docNoivo=casarNoivo[i].getNrDocumento();
                    local=casarNoivo[i].getLocalCasamento();
                    valor=casarNoivo[i].getValorPagar();
                    status=casarNoivo[i].getStatus();
                    data=casarNoivo[i].getData();

                    nomeMadr= casarMadr[i].getNome();
                    apelidoMadrinha=casarMadr[i].getApelido();
                    docMadr=casarMadr[i].getNrDocumento();

                    nomePadr=casarPadr[i].getNome();
                    apelidoPadrinho=casarPadr[i].getApelido();
                    docPadr=casarPadr[i].getNrDocumento();

                    nomeNoiva=casarNOiva[i].getNome();
                    apelidoNoiva=casarNOiva[i].getApelido();
                    docNoiva=casarNOiva[i].getNrDocumento();

                    System.out.println("________________________________________________________________________________ ");
                    System.out.println("|		  REPUBLICA DE MOCAMBIQUE				             ");
                    System.out.println("|                 GOVERNO DA CIDADE                                              ");
                    System.out.println("|		   ASSENTO DE DIVORCIO					             ");
                    System.out.println("|									             ");
                    System.out.println("| AOs "+ data +" no "+local+                                               "");
                    System.out.println("| Compareceram os nubentes: "+ nomeNoivo+" "+apelidoNoivo+                      "");
                    System.out.println("| e "+nomeNoiva+" "+apelidoNoiva+                                               "");
                    System.out.println("|                                                                                ");
                    System.out.println("| Os nubentes, perante mim acima mencionados divorciam-se nos termos da lei da,   ");
                    System.out.println("| Republica de Mocambique, onde serao aplicadas todas leis e condicoes que foram ");
                    System.out.println("| declaratos no acto do seu casamento.                                              ");
                    System.out.println("|									         ");
                    System.out.println("| E pelas razoes acima mensionadas declaro-lhes oficialmente divorciados!        ");
                    System.out.println("|									         ");
                    System.out.println("| Assento Nr: "+cod+                                                            "");
                    System.out.println("| Foram testemunhas "+nomePadr+" "+apelidoPadrinho+                             "");
                    System.out.println("| e "+nomeMadr+" "+apelidoMadrinha+                                             "");
                    System.out.println("|									         ");
                    System.out.println("|                                O Director                                      ");
                    System.out.println("|			____________________________                              ");
                    System.out.println("|									          ");
                    System.out.println("| Data: "+dataActual+"                                                           ");
                    System.out.println("|									          ");
                    System.out.println("_________________________________________________________________________________");

                }
            }
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
        }
         
    }
    
    
    /*
    Gravar toda informacao contida no array em um ficheiro de objectos
    */
    public void gravarFichObjCasamento()
    {
        String fich="Object casamento.dat";
        
        try
        {
            FileOutputStream fs = new FileOutputStream(fich);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            
            os.writeObject(casarNoivo+" "+casarNOiva+" "+casarPadr+" "+casarMadr);
            os.close();
        }
        catch (NumberFormatException nfe)
        {
                System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
        }
        catch(FileNotFoundException f)
        {
                System.err.println("ERRO: FICHEIRO NAO ENCONTRADO! "+f.getMessage());
        }
        catch(IOException ex)
        {
                System.err.println("Error: \n" + ex.getMessage());
        }
    }

}

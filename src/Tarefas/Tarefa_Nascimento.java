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
import java.io.*;
import java.util.*;
import Tarefas.Validacoes;
import Modelos.Nascimento;
import Modelos.Obito;
import Modelos.Pessoa;
public class Tarefa_Nascimento 
{
    private Validacoes val;
    
    private Obito ob;
    private Obito [] morto;
    private Vector nascimento;
    private Nascimento [] nascerFilho;
    private Nascimento [] nascerPai;
    private Nascimento [] nascerMae;
    private int cont, contaLinha, contCad, somaCont;
    
    public Tarefa_Nascimento()
    {
        nascimento = new Vector();
        nascerFilho = new Nascimento[1000];
        nascerPai = new Nascimento[1000];
        nascerMae = new Nascimento[1000];
        morto = new Obito[1000];
        cont = 0;
        contaLinha =0;
        contCad=0;
        somaCont=0;
        
        backUpFicheiroNascimento();
    }
    
    /*
    leitura (back up) de toda informacao contida no ficheiro de texto
    nascimeto, os dados dos activos sao armazenados no array "nascerFilho"
    e os inativos no array "mortos" e as informacaoes dos pais e maes sao armazenadas 
    nos arrays "nascerPai" e "nascerMae" respectivamente.
    */
    public void backUpFicheiroNascimento()
    {
        StringTokenizer st;
        Tarefas.Validacoes val = new Tarefas.Validacoes();
        Nascimento nc = new Nascimento();
        String nome, apelido, localNasc, bi, ficheiro = "Nascimento.txt", linha = "";
        String nomePai, nomeMae, apelidoPai, apelidoMae, biPai, biMae, data;
        String nomeReq, apelidoReq, docReq, dataMorte;
        char resposta = ' ', genero=' ', status=' ';
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
                
                status = st.nextToken().charAt(0);
                
                criarObjNascimento(codigo, nome, apelido, bi, genero, localNasc, data, valorPagar, status);
                
                if(status == 'v' || status == 'V')
                {
                    nascerFilho [contCad] = new Nascimento(codigo, nome, apelido,bi,genero, data, localNasc, valorPagar, status);    
                }
                else
                {
                    dataMorte = st.nextToken();
                    nomeReq = st.nextToken();
                    apelidoReq = st.nextToken();
                    docReq = st.nextToken();
                    
                    
                    morto [contCad] = new Obito(codigo, nome, apelido,bi,genero, data, localNasc, valorPagar, status, nomeReq, apelidoReq, docReq, dataMorte);
                    
                }
                
                nascerPai [contCad] = new Nascimento(nomePai, apelidoPai, biPai);
                nascerMae [contCad] = new Nascimento(nomeMae, apelidoMae, bi);
                contCad++;
                
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
    O metodo abixo faz o novo registo de um nascido para que seja cadastrado no
    ficheiro de texto nascimento
    */
    public void escreverFicheiroNascimento()
    {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        Tarefas.Validacoes val = new Tarefas.Validacoes();
        Nascimento nc = new Nascimento();
        String nome, apelido, localNasc, bi, ficheiro = "Nascimento.txt";
        String nomePai, nomeMae, apelidoPai, apelidoMae, biPai, biMae, data;
        char resposta = ' ', genero=' ', status=' ';
        int codigo=999+contCad, contador=0, contar=0;
        double valorPagar=200;
        Date dataRecibo;
        
        
        try
        {
            dataRecibo = val.buscarData();
            do
            {
                contador++;
                System.out.println(contador+"º REGISTO: ");
                System.out.println("DADOS DA CRIANCA: ");
                
                codigo++;
                
                System.out.println(codigo);
                
                nome = val.validarString("Insira o nome: ", 2, 10);
                
                apelido = val.validarString("Insira o apelido: ", 2, 10);
                
                genero = val.validarGenero();
                
                do
                {
                    System.out.println("Insira o numero do documento: ");
                    bi = br.readLine();
                    
                    if(bi.length() < 5 || bi.length()>10)
                        System.out.println("Numero de caracteres Invalido (5-10)!");
                }
                while(bi.length() < 5 || bi.length()>10);
                
                System.out.println("DATA DE NASCIMENTO: ");
                
                data = val.validarData();
                
                localNasc = val.provincia();
                
                System.out.println("Devera pagar "+valorPagar+" pelo registo!");
                
                System.out.println("");
                
                System.out.println("NOTA: coloque XXXXX nos dados da Pai caso nao os tenha no momento!");
                
                System.out.println("DADOS DO PAI: ");
                
                nomePai = val.validarString("Insira o nome: ", 2, 10);
                
                apelidoPai = val.validarString("Insira o apelido: ", 2, 10);
                
                biPai = val.validarDocumento();
                
                System.out.println("");
                
                System.out.println("NOTA: coloque XXXXX nos dados da Mae caso nao os tenha no momento!");
                
                System.out.println("DADOS DA MAE: ");
                
                nomeMae = val.validarString("Insira o nome: ", 2, 10);
                
                apelidoMae = val.validarString("Insira o apelido: ", 2, 10);
                
                biMae = val.validarDocumento();
                
                status='V';
                
                somaCont = contar+contCad;
                nascerFilho [somaCont] = new Nascimento(codigo, nome, apelido,bi,genero, data, localNasc, valorPagar, status);
                nascerPai [somaCont] = new Nascimento(nomePai, apelidoPai, biPai);
                nascerMae [somaCont] = new Nascimento(nomeMae, apelidoMae, biMae);
                contar++;
                
                System.out.println("DADOS ARMAZENADOS COM SUCESSO!!!");
                
                System.out.println("RECIBO NUMERO: "+somaCont);
                System.out.println("");

                System.out.println("_______________________________________________");
                System.out.println("|       Recibo de certidao de nascimento      |");
                System.out.println("|                                             |");
                System.out.println("| Em nome de "+nome+" "+apelido+              "");
                System.out.println("|                                             |");
                System.out.println("| Valor a pagar: "+valorPagar+                "");
                System.out.println("|                                             |");
                System.out.println("| A certidao devera ser levantada depois      |");
                System.out.println("| de 15 dias uteis.                           |");
                System.out.println("|                                             |");
                System.out.println("| DATA: "+dataRecibo+                         "");
                System.out.println("|_____________________________________________|");
                
                resposta = val.validarResposta("DESEJA EFECTUAR MAIS ALGUM REGISTO? ");
                
            }
            while (resposta == 'S' || resposta == 's');
            
        }
        catch (NumberFormatException n)
        {
                System.err.println ("Dados mal inseridos!\n"+ n.getMessage());
        }
        catch (IOException ex)
        {
                System.err.println ("ERROR: "+ex.getMessage());                             
        }
    }
    
    /*
    criacao do objecto nascimento
    */
    public void criarObjNascimento(int cod, String name, String apelid, String nrDoc, char gener, String local, String date, double valor, char status) 
    {
        Nascimento nc = new Nascimento();
        nc.setCodigo(cod);
        nc.setNome(name);
        nc.setApelido(apelid);
        nc.setNrDocumento(nrDoc);
        nc.setGenero(gener);
        nc.setData(date);
        nc.setLocalProvincia(local);
        nc.setValorPagar(valor);
        nc.setStatus(status);
        
        nascimento.addElement(nc);
    }
    
    public void criarObjObito(int cod, String name, String apelid, String nrDoc, char gener, String local, String date, double valor,char status, String dataMort, String nomeReq, String apelidoReq, String docReq) 
    {
        Obito ob = new Obito();
        ob.setCodigo(cod);
        ob.setNome(name);
        ob.setApelido(apelid);
        ob.setNrDocumento(nrDoc);
        ob.setGenero(gener);
        ob.setData(date);
        ob.setLocalProvincia(local);
        ob.setValorPagar(valor);
        ob.setDataMorte(dataMort);
        ob.setNomeRequerente(nomeReq);
        ob.setApelidoRequerente(apelidoReq);
        ob.setDocRequerente(docReq);
        ob.setStatus(status);
        
        nascimento.addElement(ob);
    }
    
    /*
    Impressao de todos obitos registados no ficheiro de texto nascimento
    */
    public void Relactorio_Obitos()
    {
        Obito ob;
        Nascimento nc;
        String nome, apelido, localNasc, bi;
        String nomePai, nomeMae, apelidoPai, apelidoMae, biPai, biMae, data;
        String nomeReq, apelidoReq, docReq, dataMorte;
        char genero=' ';
        int codigo;
        double valorPagar;
        
        try
        {
            for(int i=0; i<contCad; i++)
            {
                if(morto[i] != null)
                {
                    codigo = morto[i].getCodigo();
                    nome = morto[i].getNome();
                    apelido = morto[i].getApelido();
                    bi = morto[i].getNrDocumento();
                    dataMorte = morto[i].getDataMorte();
                    nomePai = nascerPai[i].getNome();
                    nomeMae= nascerMae[i].getNome();
                    apelidoPai= nascerPai[i].getApelido();
                    apelidoMae = nascerMae[i].getApelido();
                    data = morto[i].getData();
                    localNasc = morto[i].getLocalProvincia();

                    System.out.println("Codigo: "+codigo+"; Nome: "+nome+" "+apelido+"; com documento numero: "+bi);
                    System.out.println("Filho de "+nomePai+" "+apelidoPai+" e de "+nomeMae+" "+apelidoMae);
                    
                    System.out.println("");
                }
            }
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
        }
            
    }
    
    /*
    Impressao de todos dados dos activos (vivos) registadas
    no ficheiro nascimento
    */
    public String relactorio_Activos()
    {
        Pessoa p;
        Nascimento nc;
        
        String vivo="";
        for(int i=0; i<nascimento.size(); i++)
        {
            p=(Pessoa)nascimento.elementAt(i);
            nc=(Nascimento) nascimento.elementAt(i);
            if(nc.getStatus()=='v' || nc.getStatus()=='V')
            if(p instanceof Nascimento)
            {
                nc=(Nascimento)p;
                vivo+=nc.toString();
            }
        }
        
        return vivo;
    }
    
    /*
    Impressao de todos dados dos activos (vivos) e inativos (mortos) registadas
    no ficheiro nascimento
    */
    public String toString() 
    {
        String ver = "";
        for (int i = 0; i < nascimento.size(); i++)
            ver += nascimento.elementAt(i).toString();
        return ver;
    }
    
    
    /*
    O metodo abaixo acrescenta os dados em falta, isto e,
    se uma determinada crianca foi registada e nao se teve acesso 
    aos dados do pai ou da mae no acto do resgisto, acrescenta-se 
    esses dados...
    */
    public void editarNascimento_Perfiliacao()
    {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        Tarefas.Validacoes val = new Tarefas.Validacoes();
        Nascimento nc = new Nascimento();
        String ficheiro = "Nascimento.txt";
        String nomePai="", nomeMae="", apelidoPai="", apelidoMae="", biPai="", biMae="";
        char resposta = ' ';
        int codigo;
        
        
        String nome, apelido, localNasc, bi, data="";
        String nomeReq="", apelidoReq="", docReq="", dataMorte="";
        char genero=' ', status=' ';
        double valorPagar=200;
        Date dataRecibo;
        
        try
        {
            dataRecibo=val.buscarData();
            codigo = val.validarInt("Insira o codigo da crianca para a qual pretende adicionar dados: " , 1000, contCad+1000);
            
            for(int i=0; i<contCad; i++)
            {
                if(nascerFilho[i] != null)
                {
                    if(nascerFilho[i].getCodigo() == codigo)
                    {
                      
                        
                        resposta=val.validarResposta("O nome da crianca e: "+nascerFilho[i].getNome()+" "+nascerFilho[i].getApelido()
                                +"\nDeseja realmente editar o registo? S(Sim) || N(Nao): ");
                        
                        if(resposta == 'S' || resposta == 's')
                        {
                            System.out.println("");
                            System.out.println("NOTA: Situacao actual do registo dos pais da crianca em causa: ");
                            System.out.println("");
                            System.out.println("DADOS DO PAI: ");
                            System.out.println("Nome: "+nascerPai[i].getNome()+" "+nascerPai[i].getApelido());
                            System.out.println("Numero do documento: "+nascerPai[i].getNrDocumento());
                            System.out.println("");
                            System.out.println("DADOS DO MAE: ");
                            System.out.println("Nome: "+nascerMae[i].getNome()+" "+nascerMae[i].getApelido());
                            System.out.println("Numero do documento: "+nascerMae[i].getNrDocumento());

                            do
                            {
                                System.out.println("Pretende edicionar os dados da mae ou o pai? M (Mae) || P (Pai): ");
                                resposta = br.readLine().charAt(0);

                                if(resposta != 'P' && resposta != 'p' && resposta != 'M' && resposta !='m')
                                    System.err.println("Error: limite a responder apenas com M (Mae) ou P (Pai)");
                            }
                            while(resposta != 'P' && resposta != 'p' && resposta != 'M' && resposta !='m');



                            if(resposta == 'P' || resposta == 'p')
                            {
                                System.out.println("DADOS DO PAI: ");

                                nomePai = val.validarString("Insira o nome: ", 2, 10);

                                apelidoPai = val.validarString("Insira o apelido: ", 2, 10);

                                biPai = val.validarDocumento();

                                nascerPai[i].setNome(nomePai);
                                nascerPai[i].setApelido(apelidoPai);
                                nascerPai[i].setNrDocumento(biPai);
                            }
                            else
                            {
                                System.out.println("DADOS DA MAE: ");

                                nomeMae = val.validarString("Insira o nome: ", 2, 10);

                                apelidoMae = val.validarString("Insira o apelido: ", 2, 10);

                                biMae = val.validarDocumento();

                                nascerMae[i].setNome(nomeMae);
                                nascerMae[i].setApelido(apelidoMae);
                                nascerMae[i].setNrDocumento(biMae);
                            }

                            System.out.println("DADOS ARMAZENADOS COM SUCESSO!!!");
                            System.out.println("");
                            System.out.println("RECIBO NUMERO: "+somaCont);
                            System.out.println("");

                            System.out.println("_______________________________________________");
                            System.out.println("|           Recibo de Perfiliacao             |");
                            System.out.println("|                                             |");
                            System.out.println("| Em nome de "+nascerFilho[i].getNome()+" "
                                                   +nascerFilho[i].getApelido()+              "");
                            System.out.println("|                                             |");
                            System.out.println("| Valor a pagar: "+nascerFilho[i].getValorPagar()+                "");
                            System.out.println("|                                             |");
                            System.out.println("| DATA: "+dataRecibo+                         "");
                            System.out.println("|_____________________________________________|");
                        }
                        else
                        {
                            System.out.println("Cancelado com sucesso!");
                        }
                    }
                    
                   
                }
            }
        }
        catch (NumberFormatException n)
        {
                System.err.println ("Dados mal inseridos!\n"+ n.getMessage());
        }
        catch (IOException ex)
        {
                System.err.println ("ERROR: "+ex.getMessage());                             
        }
        
        try
        {
            FileWriter fw = new FileWriter (ficheiro);
            BufferedWriter bw = new BufferedWriter (fw);
            for(int i=0; i<=contCad; i++)
            {
                if(morto[i] != null)
                {
                    codigo = morto[i].getCodigo();
                    nome = morto[i].getNome();
                    apelido = morto[i].getApelido();
                    genero = morto[i].getGenero();
                    localNasc = morto[i].getLocalProvincia();
                    bi = morto[i].getNrDocumento();
                    data = morto[i].getData();
                    valorPagar = morto[i].getValorPagar();
                    
                    nomeReq = morto[i].getNomeRequerente();
                    apelidoReq = morto[i].getApelidoRequerente();
                    docReq = morto[i].getDocRequerente();
                    dataMorte = morto[i].getDataMorte();

                    nomePai = nascerPai[i].getNome();
                    apelidoPai = nascerPai[i].getApelido();
                    biPai = nascerPai[i].getNrDocumento();

                    nomeMae = nascerMae[i].getNome();
                    apelidoMae = nascerMae[i].getApelido();
                    biMae = nascerMae[i].getNrDocumento();
                    
                    status = 'M';
                    

                    bw.write(codigo+" | "+nome+" | "+apelido+" | "+bi+" | "+genero+" | "+data+" | "
                            +localNasc+" | "+valorPagar+" | "+nomePai+" | "+apelidoPai+" | "+biPai
                            +" | "+nomeMae+" | "+apelidoMae+" | "+biMae+" | "+status+" | "+nomeReq+" | "+apelidoReq+" | "+docReq+" | "+dataMorte+" | ");
                    
                    bw.newLine();
                }
                else
                {
                    if(nascerFilho[i]!=null)
                    {
                    codigo = nascerFilho[i].getCodigo();
                    nome = nascerFilho[i].getNome();
                    apelido = nascerFilho[i].getApelido();
                    genero = nascerFilho[i].getGenero();
                    localNasc = nascerFilho[i].getLocalProvincia();
                    bi = nascerFilho[i].getNrDocumento();
                    data = nascerFilho[i].getData();
                    valorPagar = nascerFilho[i].getValorPagar();

                    nomePai = nascerPai[i].getNome();
                    apelidoPai = nascerPai[i].getApelido();
                    biPai = nascerPai[i].getNrDocumento();

                    nomeMae = nascerMae[i].getNome();
                    apelidoMae = nascerMae[i].getApelido();
                    biMae = nascerMae[i].getNrDocumento();

                    status = 'V';

                    bw.write(codigo+" | "+nome+" | "+apelido+" | "+bi+" | "+genero+" | "+data+" | "
                            +localNasc+" | "+valorPagar+" | "+nomePai+" | "+apelidoPai+" | "+biPai
                            +" | "+nomeMae+" | "+apelidoMae+" | "+biMae+" | "+status);
                    
                    bw.newLine();
                    }
                    
                }
                
            }
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
    
    
    public void relactorio_RegNaoTerminados()
    {
        for(int i=0; i<contCad; i++)
        {
            if(nascerFilho[i] != null)
            if(nascerPai[i].getNome().contains("xxx") || nascerPai[i].getNome().contains("XXX") || nascerMae[i].getNome().contains("xxx") || nascerMae[i].getNome().contains("XXX"))
            {
                System.out.println("Codigo: "+nascerFilho[i].getCodigo()+"; Nome: "+nascerFilho[i].getNome()+" "+nascerFilho[i].getApelido()+"; Nome do pai: "+nascerPai[i].getNome()
                                    +" "+nascerPai[i].getApelido()+"; Nome da Mae: "+nascerMae[i].getNome()+" "+nascerMae[i].getApelido());
              
            }
        }
        
    }
    
    /*
    No metodo abaixo insere-se um codigo de uma pessoa para que a mesma seja reesgistada 
    como morta.
    */
    public void cadastrar_Obito()
    {
        Tarefas.Validacoes val = new Tarefas.Validacoes();
        Nascimento nc = new Nascimento();
        Obito ob = new Obito();
        String nome, apelido, localNasc, bi, ficheiro = "Nascimento.txt", linha = "";
        String nomePai, nomeMae, apelidoPai, apelidoMae, biPai, biMae, data;
        String nomeReq, apelidoReq, docReq, dataMorte;
        char resposta = ' ', genero=' ', status=' ';
        int codigo, cod, incr=0;
        double valorPagar;
        Date dataRecibo;
        
        try
        {
            FileWriter fw = new FileWriter (ficheiro);
            BufferedWriter bw = new BufferedWriter (fw);
            
            dataRecibo = val.buscarData();
            do
            {
                cod = val.validarInt("Insira o codigo de nascimento do morto: ", 1000, 1000+contCad);

                for(int i=0; i<contCad; i++)
                {
                    if(nascerFilho[i]!=null)
                    {
                        codigo = nascerFilho[i].getCodigo();
                        nome = nascerFilho[i].getNome();
                        apelido = nascerFilho[i].getApelido();
                        genero = nascerFilho[i].getGenero();
                        localNasc = nascerFilho[i].getLocalProvincia();
                        bi = nascerFilho[i].getNrDocumento();
                        data = nascerFilho[i].getData();
                        valorPagar = nascerFilho[i].getValorPagar();

                        nomePai = nascerPai[i].getNome();
                        apelidoPai = nascerPai[i].getApelido();
                        biPai = nascerPai[i].getNrDocumento();

                        nomeMae = nascerMae[i].getNome();
                        apelidoMae = nascerMae[i].getApelido();
                        biMae = nascerMae[i].getNrDocumento();

                        status = 'V';
                        
                        if(cod == nascerFilho[i].getCodigo())
                        {
                            status = 'M';
                            System.out.println("O nome do morto e: "+nascerFilho[i].getNome()+" "+nascerFilho[i].getApelido());
                            resposta = val.validarResposta("Tem certeza de que deseja cadastra-lo como morto? S (SIM) || N (NAO): ");
                            
                            
                            if(resposta == 's' || resposta == 'S')
                            {
                                System.out.println("DADOS DO REQUERENTE");

                                nomeReq = val.validarString("Insira o nome: ", 2, 10);

                                apelidoReq = val.validarString("Insira o apelido: ", 2, 10);

                                docReq = val.validarDocumento();

                                System.out.println("Data da morte: ");

                                dataMorte = val.validarData();

                                 bw.write(codigo+" | "+nome+" | "+apelido+" | "+bi+" | "+genero+" | "+data+" | "
                                +localNasc+" | "+valorPagar+" | "+nomePai+" | "+apelidoPai+" | "+biPai
                                +" | "+nomeMae+" | "+apelidoMae+" | "+biMae+" | "+status+" | "+dataMorte+" | "+nomeReq+" | "+apelidoReq+" | "+docReq+" | ");
                                 
                                System.out.println("RECIBO NUMERO: "+somaCont);
                                System.out.println("");

                                System.out.println("_______________________________________________");
                                System.out.println("|       Recibo de certidao de Obito           |");
                                System.out.println("|                                             |");
                                System.out.println("| Em nome de "+nome+" "+apelido+"              ");
                                System.out.println("|                                             |");
                                System.out.println("| Valor a pagar: "+valorPagar+   "             ");
                                System.out.println("|                                             |");
                                System.out.println("| A certidao devera ser levantada depois      |");
                                System.out.println("| de 15 dias uteis.                           |");
                                System.out.println("|                                             |");
                                System.out.println("| "+dataRecibo+     "                          ");
                                System.out.println("|_____________________________________________|");
                            }
                        }
                        else
                        {
                            bw.write(codigo+" | "+nome+" | "+apelido+" | "+bi+" | "+genero+" | "+data+" | "
                                    +localNasc+" | "+valorPagar+" | "+nomePai+" | "+apelidoPai+" | "+biPai
                                    +" | "+nomeMae+" | "+apelidoMae+" | "+biMae+" | "+status);
                        }
                    }
                    else
                    {
                        if(morto[i] != null)
                        {
                            codigo = morto[i].getCodigo();
                            nome = morto[i].getNome();
                            apelido = morto[i].getApelido();
                            genero = morto[i].getGenero();
                            localNasc = morto[i].getLocalProvincia();
                            bi = morto[i].getNrDocumento();
                            data = morto[i].getData();
                            valorPagar = morto[i].getValorPagar();

                            nomeReq = morto[i].getNomeRequerente();
                            apelidoReq = morto[i].getApelidoRequerente();
                            docReq = morto[i].getDocRequerente();
                            dataMorte = morto[i].getDataMorte();

                            nomePai = nascerPai[i].getNome();
                            apelidoPai = nascerPai[i].getApelido();
                            biPai = nascerPai[i].getNrDocumento();

                            nomeMae = nascerMae[i].getNome();
                            apelidoMae = nascerMae[i].getApelido();
                            biMae = nascerMae[i].getNrDocumento();

                            status = 'M';


                            bw.write(codigo+" | "+nome+" | "+apelido+" | "+bi+" | "+genero+" | "+data+" | "
                                    +localNasc+" | "+valorPagar+" | "+nomePai+" | "+apelidoPai+" | "+biPai
                                    +" | "+nomeMae+" | "+apelidoMae+" | "+biMae+" | "+status+" | "+nomeReq+" | "+apelidoReq+" | "+docReq+" | "+dataMorte+" | ");


                        }
                    }
                    bw.newLine();
                    
                }
                bw.close();

                resposta = val.validarResposta("DESEJA EFECTUAR MAIS ALGUM REGISTO? ");
            }
            while (resposta == 'S' || resposta == 's');
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
    O metodo abaixo reescreve toda informacao lida e guardada no
    array a incrementada no ficheiro de texto nascimento 
    */
    public void incrementar_Nascimento()
    {
        String nome, apelido, localNasc, bi, ficheiro = "Nascimento.txt";
        String nomePai, nomeMae, apelidoPai, apelidoMae, biPai, biMae, data;
        String nomeReq="", apelidoReq="", docReq="", dataMorte="";
        char resposta = ' ', genero=' ', status=' ';
        int codigo=0;
        double valorPagar=200;
        
        try
        {
            Nascimento nc = new Nascimento();
            FileWriter fw = new FileWriter (ficheiro);
            BufferedWriter bw = new BufferedWriter (fw);
            for(int i=0; i<=somaCont; i++)
            {
                if(morto[i] != null)
                {
                    codigo = morto[i].getCodigo();
                    nome = morto[i].getNome();
                    apelido = morto[i].getApelido();
                    genero = morto[i].getGenero();
                    localNasc = morto[i].getLocalProvincia();
                    bi = morto[i].getNrDocumento();
                    data = morto[i].getData();
                    valorPagar = morto[i].getValorPagar();
                    
                    nomeReq = morto[i].getNomeRequerente();
                    apelidoReq = morto[i].getApelidoRequerente();
                    docReq = morto[i].getDocRequerente();
                    dataMorte = morto[i].getDataMorte();

                    nomePai = nascerPai[i].getNome();
                    apelidoPai = nascerPai[i].getApelido();
                    biPai = nascerPai[i].getNrDocumento();

                    nomeMae = nascerMae[i].getNome();
                    apelidoMae = nascerMae[i].getApelido();
                    biMae = nascerMae[i].getNrDocumento();
                    
                    status = 'M';
                    

                    bw.write(codigo+" | "+nome+" | "+apelido+" | "+bi+" | "+genero+" | "+data+" | "
                            +localNasc+" | "+valorPagar+" | "+nomePai+" | "+apelidoPai+" | "+biPai
                            +" | "+nomeMae+" | "+apelidoMae+" | "+biMae+" | "+status+" | "+dataMorte+" | "+nomeReq+" | "+apelidoReq+" | "+docReq+" | ");
                    
                    bw.newLine();
                }
                else
                {
                    if(nascerFilho[i]!=null)
                    {
                        codigo = nascerFilho[i].getCodigo();
                        nome = nascerFilho[i].getNome();
                        apelido = nascerFilho[i].getApelido();
                        genero = nascerFilho[i].getGenero();
                        localNasc = nascerFilho[i].getLocalProvincia();
                        bi = nascerFilho[i].getNrDocumento();
                        data = nascerFilho[i].getData();
                        valorPagar = nascerFilho[i].getValorPagar();

                        nomePai = nascerPai[i].getNome();
                        apelidoPai = nascerPai[i].getApelido();
                        biPai = nascerPai[i].getNrDocumento();

                        nomeMae = nascerMae[i].getNome();
                        apelidoMae = nascerMae[i].getApelido();
                        biMae = nascerMae[i].getNrDocumento();

                        status = 'V';

                        bw.write(codigo+" | "+nome+" | "+apelido+" | "+bi+" | "+genero+" | "+data+" | "
                                +localNasc+" | "+valorPagar+" | "+nomePai+" | "+apelidoPai+" | "+biPai
                                +" | "+nomeMae+" | "+apelidoMae+" | "+biMae+" | "+status);

                        bw.newLine();
                    }
                }
                
            }
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
    Impressao da certidao de nascimento
    */
    public void certidao_Nascimento()
    {
        Validacoes val = new Validacoes();
        String nome, apelido, localNasc, bi;
        String nomePai, nomeMae, apelidoPai, apelidoMae, biPai, biMae, data;
        String nomeReq="", apelidoReq="", docReq="", dataMorte="";
        char resposta = ' ', genero=' ', status=' ';
        int codigo=0, codComparador;
        Date dataActual;
        double valorPagar=200;
        Nascimento nc;
        
        try
        {
            dataActual=val.buscarData();
            codComparador = val.validarInt("Insira o codigo do nescido que deseja imprimir o codigo: ", 1000, contCad+1000);

            for(int i=0; i<contCad; i++)
            {
                if(nascerFilho[i]!=null)
                if(nascerFilho[i].getCodigo()==codComparador)
                {
                    nc = (Nascimento) nascimento.elementAt(i);
                    codigo = nc.getCodigo();
                    nome = nc.getNome();
                    apelido = nc.getApelido();
                    genero = nc.getGenero();
                    localNasc = nc.getLocalProvincia();
                    bi = nc.getNrDocumento();
                    data = nc.getData();
                    valorPagar = nc.getValorPagar();

                    nomePai = nascerPai[i].getNome();
                    apelidoPai = nascerPai[i].getApelido();
                    biPai = nascerPai[i].getNrDocumento();

                    nomeMae = nascerMae[i].getNome();
                    apelidoMae = nascerMae[i].getApelido();
                    biMae = nascerMae[i].getNrDocumento();


                    System.out.println("_______________________________________________________________________________________________");
                    System.out.println("|		              REPUBLICA DE MOCAMBIQUE				               ");
                    System.out.println("|		                  GOVERNO DA CIDADE				               ");
                    System.out.println("|		              CERTIDAO DE NASCIMENTO				               ");
                    System.out.println("|									                       ");
                    System.out.println("| Nome: "+nome+" "+apelido+                                                                   "");
                    System.out.println("| Filho de "+nomePai+" "+apelidoPai+" com documento numero: "+biPai+                          "");
                    System.out.println("| e de "+nomeMae+" "+apelidoMae+" com documento numero: "+biMae+                              "");
                    System.out.println("|                                                                                              ");
                    System.out.println("| Documento numero: "+bi+                                                                     "");
                    System.out.println("| Nascido aos "+data+                                                                         "");
                    System.out.println("| Natural de "+localNasc+                                                                     "");  
                    System.out.println("|                                                                                              ");
                    System.out.println("|                                                                                              ");
                    System.out.println("|                                               O Director                                     ");
                    System.out.println("|                                       ____________________________                           ");
                    System.out.println("|                                                                                              ");
                    System.out.println("|                           Data: "+dataActual+"                                               ");
                    System.out.println("|                                                                                              ");
                    System.out.println("_______________________________________________________________________________________________");

                }
            }
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
        }
            
       
    }
    
    /*
    Impressao do certidao de obito
    */
    public void certidao_Obidto()
    {
        Validacoes val = new Validacoes();
        String nome, apelido, localNasc, bi, ficheiro = "Nascimento.txt";
        String nomePai, nomeMae, apelidoPai, apelidoMae, biPai, biMae, data;
        String nomeReq="", apelidoReq="", docReq="", dataMorte="";
        char resposta = ' ', genero=' ', status=' ';
        int codigo=0, codComparador;
        Date dataActual;
        double valorPagar=200;
        Nascimento nc;
        
        try
        {
            dataActual=val.buscarData();
            codComparador = val.validarInt("Insira o codigo do nescido que deseja imprimir o codigo: ", 1000, contCad+1000);

            for(int i=0; i<contCad; i++)
            {
                if(morto[i]!=null)
                {
                    if(morto[i].getCodigo()==codComparador)
                    {
                        nc=(Nascimento) nascimento.elementAt(i);
                        codigo = nc.getCodigo();
                        nome = nc.getNome();
                        apelido = nc.getApelido();
                        genero = nc.getGenero();
                        localNasc = nc.getLocalProvincia();
                        bi = nc.getNrDocumento();
                        data = nc.getData();
                        valorPagar = nc.getValorPagar();

                        dataMorte = morto[i].getDataMorte();

                        nomePai = nascerPai[i].getNome();
                        apelidoPai = nascerPai[i].getApelido();
                        biPai = nascerPai[i].getNrDocumento();

                        nomeMae = nascerMae[i].getNome();
                        apelidoMae = nascerMae[i].getApelido();
                        biMae = nascerMae[i].getNrDocumento();

                        System.out.println("_______________________________________________________________________________________________");
                        System.out.println("|		              REPUBLICA DE MOCAMBIQUE				               ");
                        System.out.println("|		                  GOVERNO DA CIDADE				               ");
                        System.out.println("|		                 CERTIDAO DE OBITO				               ");
                        System.out.println("|									                       ");
                        System.out.println("| Nome: "+nome+" "+apelido+"                                                                   ");
                        System.out.println("| Filho de "+nomePai+" "+apelidoPai+" com documento numero+ "+biPai+                          "");
                        System.out.println("| e de "+nomeMae+" "+apelidoMae+" com documento numero+ "+biMae+                              "");
                        System.out.println("|                                                                                              ");
                        System.out.println("| Documento numero: "+bi+                                                                     "");
                        System.out.println("| Nascido aos "+data+                                                                         "");
                        System.out.println("| Natural de "+localNasc+                                                                     "");  
                        System.out.println("| Teno perdido a vida aos "+dataMorte+                                                        "");
                        System.out.println("|                                                                                               ");
                        System.out.println("|                                           O Director                                          ");
                        System.out.println("|                                   ____________________________                                ");
                        System.out.println("|                                                                                               ");
                        System.out.println("|               Data: "+dataActual+"                                                           ");
                        System.out.println("|                                                                                               ");
                        System.out.println("|_______________________________________________________________________________________________");

                    }

                }

            }
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
        }
            
    }
    
    /*
    Registo de todos os dados em um ficheiro de objectos
    */
    public void gravarFichObjNascimento()
    {
        String fich="Object nascimento.dat";
        
        try
        {
            FileOutputStream fs = new FileOutputStream(fich);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            
            os.writeObject(nascerFilho+" "+nascerPai+" "+nascerMae);
            os.writeObject(morto+" "+nascerPai+" "+nascerMae);
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

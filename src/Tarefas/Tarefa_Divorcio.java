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
import Modelos.Casamento;
import java.util.*;
import java.io.*;
import Modelos.Divorcio;
import Tarefas.Validacoes;
public class Tarefa_Divorcio 
{
    private Validacoes val;
    private Divorcio [] divorcioNoivo;
    private Divorcio [] divorcioNoiva;
    private Divorcio [] divorcioPadr;
    private Divorcio [] divorcioMadr;
    private int cont;
    
    public Tarefa_Divorcio()
    {
        divorcioNoivo = new Divorcio[100];
        divorcioNoiva = new Divorcio[100];
        divorcioPadr = new Divorcio[100];
        divorcioMadr = new Divorcio[100];
        cont=0;
        
        
    }
    
    /*
    Leitura (back up) de toda informacao do ficheiro de texto casamento
    para os arrays e insercao do codigo, caso este seja igual a de um casal
    este muda o estado para "divorciados"
    */
    public void lerDados_ParaDivorciar ()
    {
        Validacoes val = new Validacoes();
        String ficheiro = "Casamento.txt";
        StringTokenizer st;
        String linha = ""; 
        
        String nomNoivo, documentNoivo, nomNoiva, documentNoiva, nomPadrinho, documentPadrinho, nomMadrinha,documentMadinha, lerLocalProvincia="",lerStatus="", lerData;
        String apelidoNoivo, apelidoNoiva, apelidoPadr, apelidoMadr, local;
        double lerPagamento;
        char genero=' ', resp;
        int lerID, divorciarID;
        Date dataRecibo;

        try
        {
                FileReader fr = new FileReader (ficheiro);
                BufferedReader brf = new BufferedReader (fr);
                BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
                dataRecibo=val.buscarData();
                divorciarID = val.validarInt("POR FAVOR, INSIRA O ID DO CASAL QUE DESEJA DIVORCIAR: ", 1000, 9999);
                
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
                    
                    local = st.nextToken();
                    
                    lerData = st.nextToken();
                    
                    lerPagamento = Double.parseDouble(st.nextToken());
                    
                    lerStatus = st.nextToken();
                    
                    divorcioNoivo [cont] = new Divorcio(lerID, nomNoivo, apelidoNoivo, documentNoivo, genero,  lerLocalProvincia, lerData, lerPagamento, local, lerStatus);
                    divorcioNoiva [cont] = new Divorcio(nomNoiva, apelidoNoiva, documentNoiva);
                    divorcioPadr [cont] = new Divorcio(nomPadrinho, apelidoPadr, documentPadrinho);
                    divorcioMadr [cont] = new Divorcio (nomMadrinha, apelidoMadr, documentMadinha);
               
                    if(divorcioNoivo[cont].getCodigo() == divorciarID)
                    {
                        resp = val.validarResposta("Tem certeza de que pretende divorciar o casal "
                                +nomNoiva +" "+apelidoNoivo+" e "
                                +nomNoiva+" "+apelidoNoiva+"?");
                        
                        if(resp=='s' || resp=='S')
                        {
                            lerStatus = "Divorciados";
                            divorcioNoivo [cont].setStatus(lerStatus);
                            System.err.println("O casal "+divorcioNoivo[cont].getNome()+" e "+divorcioNoiva[cont].getNome()+" encontram-se oficialmente divorciados!");
                            
                            System.out.println("");
                            System.out.println("");
                            System.out.println("_______________________________________________");
                            System.out.println("|       Recibo de certidao de divorcio        |");
                            System.out.println("|                                             |");
                            System.out.println("| Em nome de "+nomNoivo+" "+apelidoNoivo+     "");
                            System.out.println("| e de "+nomNoiva+" "+apelidoNoiva+           "");
                            System.out.println("|                                             |");
                            System.out.println("| Valor a pagar: 3000.00                      |");
                            System.out.println("|                                             |");
                            System.out.println("| A certidao devera ser levantada depois      |");
                            System.out.println("| de 15 dias uteis.                           |");
                            System.out.println("|                                             |");
                            System.out.println("| DATA: "+dataRecibo+                         "");
                            System.out.println("|_____________________________________________|");
                        }
                    }
                    else
                    {
                        divorcioNoivo [cont].setStatus(lerStatus);
                    }
                    
                    cont++;

                    linha = brf.readLine();
                }
                fr.close();

        }
        catch (NumberFormatException nfe)
        {
                System.err.println("ERRO: DADOS MAL CONVERTIDOS (lerDados_ParaDivorciar)! \n"+nfe.getMessage());
        }
        catch(FileNotFoundException f)
        {
                System.err.println("ERRO: FICHEIRO NAO ENCONTRADO (lerDados_ParaDivorciar)! "+f.getMessage());
        }
        catch(IOException ex)
        {
                System.err.println("Error (lerDados_ParaDivorciar): \n" + ex.getMessage());
        }
    }
    
   
    /*
    Registo de todos os dados no ficheiro de texto depois do divorcio
    */
    public void cadastro_AposDivorcio ()
    {
        String nomeNoivo, nomeNoiva, nomePadr, nomeMadr, docNoivo, docNoiva, docPadr, docMadr, local="", status, data;
        String apelidoNoivo, apelidoNoiva, apelidoPadr, apelidoMadr, localProvincia;
        int ID;
    
        double valor=0;
    
        
        String ficheiro = "Casamento.txt";
        
        try
        {
            FileWriter fw = new FileWriter (ficheiro);
            BufferedWriter bw = new BufferedWriter (fw);
            
            for(int i=0; i<cont; i++)
            {
                ID=divorcioNoivo[i].getCodigo();
        
                nomeNoivo = divorcioNoivo[i].getNome();
                
                apelidoNoivo = divorcioNoivo[i].getApelido();

                docNoivo = divorcioNoivo[i].getNrDocumento();

                nomeNoiva = divorcioNoiva[i].getNome();
                
                apelidoNoiva = divorcioNoiva[i].getApelido();

                docNoiva = divorcioNoiva[i].getNrDocumento();

                nomePadr = divorcioPadr[i].getNome();
                
                apelidoPadr = divorcioPadr[i].getApelido();

                docPadr = divorcioPadr[i].getNrDocumento();

                nomeMadr = divorcioMadr[i].getNome();
                
                apelidoMadr = divorcioMadr[i].getApelido();

                docMadr = divorcioMadr[i].getNrDocumento();

                local = divorcioNoivo[i].getLocalCasamento();
                
                data = divorcioNoivo[i].getData();
                    
                valor = divorcioNoivo[i].getValorPagar();

                status = divorcioNoivo[i].getStatus();
                
                bw.write(ID + " | "+nomeNoivo + " | "+apelidoNoivo+" | "+docNoivo + " | "
                        +nomeNoiva + " | "+apelidoNoivo+" | "+docNoiva + " | "
                        +nomePadr + " | "+apelidoPadr+" | "+docPadr + " | "
                        +nomeMadr + " | "+apelidoMadr+" | "+docMadr + " | "
                        +local + " | "+data+ " | "+valor + " | "+status + " | ");
                
                bw.newLine();
            }
            
            bw.close();
        }
        catch (NumberFormatException n)
        {
                System.err.println ("Dados mal inseridos! (cadastro_AposDivorcio) \n"+ n.getMessage());
        }
        catch (FileNotFoundException fn)
        {
                System.err.println ("FICHEIRO NAO ENCONTRADO! (cadastro_AposDivorcio) \n"+ fn.getMessage());
        }
        catch (IOException ex)
        {
                System.err.println ("Error (cadastro_AposDivorcio): "+ex.getMessage());                             
        }
    }

    
    
    

    
}


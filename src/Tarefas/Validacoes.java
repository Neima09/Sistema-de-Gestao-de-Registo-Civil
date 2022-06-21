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
public class Validacoes 
{
    //Validacao de todos os dados de tipo String
    public static String validarString (String caracter, int menor, int maior)
    {
    	BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
    	
        String palavra="";
        
        do
        {
            System.out.println(caracter);
            try
            {
                palavra = br.readLine();
                if(palavra.length()<menor || palavra.length()>maior)
                    System.out.println("NUMERO DE CARACTERES INVALIDO! \n O numero deve estar entre "+menor+ " e "+maior+".");
            }
            catch(IOException ex)
            {
                System.err.println("Error: \n" + ex.getMessage());
            }
        }
        while (palavra.length()<menor || palavra.length()>maior);
        return palavra;
    }

    //Validacao de todos os dados de tipo Inteiro
    public static int validarInt (String caracter, int menor, int maior)
    {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        int num=0;
        
        do
        {
            System.out.println(caracter);
            try
            {
                num=Integer.parseInt(br.readLine());
                if(num < menor || num > maior)
                    System.out.println("NUMERO INVALIDO! \n O numero deve estar entre "+menor+ " e "+maior+".");
            }
            catch (NumberFormatException nfe)
            {
                System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
            }
            catch(IOException ex)
            {
                System.err.println("Error: \n" + ex.getMessage());
            }
        }
        while (num < menor || num > maior);
        return num;
    }

    //Validacao de todos os dados decimais
    public static double validarDouble (String caracter, double menor, double maior)
    {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        double num=0;

        do
        {
            System.out.println(caracter);
            try
            {
                num=Double.parseDouble(br.readLine());
                if(num >maior || num <menor)
                    System.out.println("NUMERO INVALIDO! \n O numero deve estar entre "+menor+ " e "+maior+".");
            }
            catch (NumberFormatException nfe)
            {
                System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
            }
            catch(IOException ex)
            {
                System.err.println("Error: \n" + ex.getMessage());
            }
        }
        while (num >maior || num <menor);        
        return num;
    }

    //Validacao de todos os dados de tipo Byte
    public static byte validarByte (String caracter, byte menor, byte maior)
    {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        byte num=0;
        
        do
        {
            System.out.println(caracter);
            try
            {
                num=Byte.parseByte(br.readLine());
                if(num < menor || num > maior)
                    System.out.println("NUMERO INVALIDO! \n O numero deve estar entre "+menor+ " e "+maior+".");
            }
            catch (NumberFormatException nfe)
            {
                System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
            }
            catch(IOException ex)
            {
                System.err.println("Error: \n" + ex.getMessage());
            }
        }
        while (num < menor || num > maior);
        return num;
    }
 
    //Validacao de todos os dados de tipo Float
    public static float validarFloat (String caracter, float menor, float maior)
    {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        float num=0;

        do
        {
            System.out.println(caracter);
            try
            {
                num=Float.parseFloat(br.readLine());
                if(num >maior || num <menor)
                    System.out.println("NUMERO INVALIDO! \n O numero deve estar entre "+menor+ " e "+maior+".");
            }
            catch (NumberFormatException nfe)
            {
                System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
            }
            catch(IOException ex)
            {
                System.err.println("Error: \n" + ex.getMessage());
            }
        }
        while (num >maior || num <menor);        
        return num;
    }

    //Validacao das quantidades (apenas numeros inteiros)
    public int validarQuantidade(String caracter)
   {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        int quant=0;

        do
        {
            System.out.println(caracter);
            try
            {
                quant=Integer.parseInt(br.readLine());

                if(quant<=0)
                        System.out.println("Numero Invalido! \n Deve ser maior que zero.");
            }
            catch (NumberFormatException nfe)
            {
                System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
            }
            catch(IOException ex)
            {
                System.err.println("Error: \n" + ex.getMessage());
            }
        }
        while(quant<=0);

        return quant;
    }

    //Validacao das quantidades (apenas numeros decimais)
    public double validarValor(String caracter)
    {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        double quant=0;

        do
        {
            System.out.println(caracter);
            try
            {
                quant=Integer.parseInt(br.readLine());

                if(quant<=0)
                    System.out.println("Numero Invalido! \n Deve ser maior que zero.");
            }
            catch (NumberFormatException nfe)
            {
                System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
            }
            catch(IOException ex)
            {
                System.err.println("Error: \n" + ex.getMessage());
            }
        }
        while(quant<=0);

        return quant;
    }
    
    //Validacao de respostas, para que sejam respondidas apenas com sim ou nao
    public char validarResposta(String msg)
    {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        char resp=' ';
        do{
            System.out.println(msg);
            try
            {
            resp = br.readLine().charAt(0);
            
            if(resp != 's' && resp != 'n' && resp != 'S' && resp != 'N')
                System.err.println("Por favor, limite-se a responder apenas com S (SIM) ou N (NAO)!");
            }
            catch(IOException ex)
            {
                System.err.println("Error: \n" + ex.getMessage());
            }
        }
        while(resp != 's' && resp != 'n' && resp != 'S' && resp != 'N');
        return resp;
    }
    
    //Validacao do genero, para que seja apenas Masculino ou Feminino
    public char validarGenero()
    {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        char genero=' ';
        do{
            System.out.println("M (Masculino)\nF (Feminino)\nEscolha o genero: ");
            try
            {
                genero = br.readLine().charAt(0);

                if(genero != 'f' && genero != 'm' && genero != 'F' && genero != 'M')
                    System.err.println("Por favor, limite-se apenas a inserir M ou F!");
            }
            catch(IOException ex)
            {
                System.err.println("Error: \n" + ex.getMessage());
            }
        }
        while(genero != 'f' && genero != 'm' && genero != 'F' && genero != 'M');
        return genero;
    }
    
    //Validacao de datas
    public String validarData()
    {
        int dia=0, mes=0, ano1=0;
        do{
            try
            {
                dia = validarByte("INTRODUZA O DIA (1-31): ",(byte) 1, (byte) 31);
                mes = validarByte("INTRODUZA O MES (1-12): ", (byte) 1, (byte) 12);
                ano1 = validarInt("INTRODUZA O ANO (NAO SUPEIOR AO ANO ACTUAL): ",1000,2019);

                if((dia<0 ||dia>31)||(mes<0||mes>12)||(ano1>2019))
                    System.out.println("DATA MAL INTRODUZIDA, TENTE NOVAMENTE: ");
            }
            catch (NumberFormatException nfe)
            {
                System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
            }
        }while((dia<0 ||dia>31)||(mes<0||mes>12)||(ano1>2019));
        
        return dia+"."+mes+"."+ano1;
    }

    //Validacao de todo tipo de documento
    public String validarDocumento()
    {
        byte opcao=0;
        String numeroD ="";
        do{
            try
            {
                opcao = validarByte("1-BI \n2-DIR \n3-PASSAPORTE \n4-CEDULA 0-VOLTAR \n0-VOLTAR \nEscolha o tipo de documento: ",(byte)1, (byte) 4);
                switch(opcao)
                {
                    case 1:
                        numeroD = validarString("INTRODUZA O NUMERO DO BI: ",5,10);
                    break;

                    case 2:
                        numeroD = validarString("INTRODUZA O CODIGO DO DIR: ",5,10);
                    break;

                    case 3:
                        numeroD =validarString("INTRODUZA O CODIGO DO PASSAPORTE: ",5,10);
                    break;

                    case 4:
                        numeroD = validarString("INTRODUZA O CODIGO DA CEDULA: ",4,8);
                    break;

                    default:
                        System.err.println("Opcao Invalida!");
                    break;
                }
            }
            catch (NumberFormatException nfe)
            {
                System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
            }
        }
        while(opcao==0);
        return numeroD;
    }


    public Date buscarData()
    {
        Date data = new Date();
        
        return data;
    }
    
    public String provincia()
    {
        String nome = "";
        
        try
            {
                nome = validarString("1-Maputo | 2-Gaza | 3-Inhambane | 4-Manica |\n"
                        + "5-Sofala | 6-Tete | 7-Zambezia | 8-Nampula |\n"
                        + "9-Niassa | 10-Cabo Delgado | \nEsolha a provincia: ",1,1);
                switch(nome)
                {
                    case "1":
                        nome = "Maputo";
                    break;

                    case "2":
                        nome = "Gaza";
                    break;

                    case "3":
                        nome = "Inhambane";
                    break;

                    case "4":
                        nome = "Manica";
                    break;
                        
                    case "5":
                        nome = "Sofala";
                    break;
                        
                    case "6":
                        nome = "Tete";
                    break;
                        
                    case "7":
                        nome = "Zambezia";
                    break;
                        
                    case "8":
                        nome = "Nampula";
                    break;
                        
                    case "9":
                        nome = "Niassa";
                    break;
                        
                    case "10":
                        nome = "Cabo_Delgado";
                    break;

                    default:
                        System.err.println("Opcao Invalida!");
                    break;
                }
            }
            catch (NumberFormatException nfe)
            {
                System.err.println("ERRO: DADOS MAL INSERIDOS! \n"+nfe.getMessage());
            }
        
        return nome;
    }

}

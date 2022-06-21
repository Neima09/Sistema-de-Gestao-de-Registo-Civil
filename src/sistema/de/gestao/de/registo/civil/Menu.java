/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.gestao.de.registo.civil;

/**
 *
 * @author SIMIAO CANZE
 */
import Modelos.Casamento;
import java.io.*;
import Tarefas.Tarefa_Casamento;
import Tarefas.Tarefa_Divorcio;
import Tarefas.Tarefa_Nascimento;
import Tarefas.Validacoes;
public class Menu 
{
    public  Menu() throws IOException
    {
        menu();
    }
    
    public void menu() throws IOException
    {
        int op, ap1, ap2, ap3, op4, op5,op6;
        
        Validacoes val = new Validacoes();
        Tarefa_Casamento cs = new Tarefa_Casamento();
        Tarefa_Divorcio div = new Tarefa_Divorcio();
        Tarefa_Nascimento nc = new Tarefa_Nascimento();
        
        
        do
        {
            System.out.println("*                        MENU DO SISTEMA                                  *");
            System.out.println("*                        1. CASAMENTO                                     *");
            System.out.println("*                         2. DIVORCIO                                     *");
            System.out.println("*                        3. NASCIMENTO                                    *");
            System.out.println("*                          4. OBITO                                       *");
            System.out.println("*                       5. PERFILIACAO                                    *");
            System.out.println("*                     6. IMPRIMIR CERTIDAO                                *");
            System.out.println("*                            0.Sair                                       *");
            op = val.validarInt("POR FAVOR, ESCOLHA A OPCAO: ", 0, 6);
            
            switch(op)
            {
                case 1:
                     do
                     {
                         System.out.println("                MENU CASAMENTO                              ");
                         System.out.println("            1. Registar casamento                           ");
                         System.out.println("          2. Visualizar os casados                          ");
                         System.out.println("     3. Visualizar os casados e os divorciados              ");
                         System.out.println("                 0. Voltar                                  ");
                         ap1=val.validarInt("Escolha a opcao: ", 0, 3);
                         
                         switch(ap1)
                         {
                            case 1: 
                                
                                cs.lerNascimento();
                                cs.casar_Codigo();
                                cs.incrementar_Casamento();
                                cs.gravarFichObjCasamento();
                            break;
                                
                            case 2:
                                cs.visualizarCasados();
                            break;
                                
                            case 3:
                                cs.visualizarTodaInf();
                            break;
                         }
                     }
                     while(ap1!=0);
                break;
                    
                case 2:
                    do
                     {
                         System.out.println("  *              MENU DIVORCIO                    *           ");
                         System.out.println("  *            1. Divorciar                       *          ");
                         System.out.println("  *       2. Visualizar os divorciados            *         ");
                         System.out.println("  *   3. Visualizar os casados e os divorciados   *          ");
                         System.out.println("  *               0. Voltar                       *          ");
                         ap2=val.validarInt("Escolha a opcao: ", 0, 3);
                         
                         switch(ap2)
                         {
                            case 1:
                                div.lerDados_ParaDivorciar();
                                div.cadastro_AposDivorcio();
                            break;
                                
                            case 2:
                                cs.visualizarDivorciados();
                            break;
                                
                            case 3:
                                cs.visualizarTodaInf();
                            break;
                         }
                     }
                     while(ap2!=0);
                break;
                    
                case 3:
                    
                    do
                     {
                         System.out.println("    *              MENU NASCIMENTO                  *                 ");
                         System.out.println("    *          1. Registar nascimento               *                 ");
                         System.out.println("    *    2. Visualizar os nascidos acttvos          *                ");
                         System.out.println("    *  3. Relactorio geral (activos e inactivos)    *                ");
                         System.out.println("    *               0. Voltar                       *                ");
                         ap3=val.validarInt("Escolha a opcao: ", 0, 3);
                         
                         switch(ap3)
                         {
                            case 1:
                                nc.escreverFicheiroNascimento();
                                nc.incrementar_Nascimento();
                                nc.gravarFichObjNascimento();
                            break;
                                
                            case 2:
                                System.out.println(nc.relactorio_Activos());
                            break;
                                
                            case 3:
                                System.out.println(nc.toString());
                            break;
                                
                         }
                     }
                     while(ap3!=0);
                break;
                    
                case 4:
                   
                    do
                     {
                         System.out.println("        *        MENU OBITO        *                      ");
                         System.out.println("        *    1. Registar obito     *                      ");
                         System.out.println("        *  2. Visualizar obitos    *                     ");
                         System.out.println("        *         0. Voltar        *                         ");
                         op4=val.validarInt("Escolha a opcao: ", 0, 2);
                         
                         switch(op4)
                         {
                            case 1:
                                nc.cadastrar_Obito();
                            break;
                                
                            case 2:
                                nc.Relactorio_Obitos();
                            break;
                                
                         }
                     }
                     while(op4!=0);
                break;
                    
                case 5:
                   
                    
                    do
                     {
                         System.out.println("     *                             MENU PERFILIACAO                       *                ");
                         System.out.println("     *   1. Acrescentar dados em falta (do pai ou da mae)                 *                ");
                         System.out.println("     *   2. Relactorio dos registos com dados em falta (do pai ou da mae) *                ");
                         System.out.println("     *                                  0. Voltar                         *                ");
                         op5=val.validarInt("Escolha a opcao: ", 0, 2);
                         
                         switch(op5)
                         {
                            case 1:
                                nc.editarNascimento_Perfiliacao();
                            break;
                                
                            case 2:
                                nc.relactorio_RegNaoTerminados();
                            break;
                            
                         }
                     }
                     while(op5!=0);
                break;
                    
                case 6:
                     do
                     {
                         System.out.println("     *               MENU CERTIDAO              *                ");
                         System.out.println("     *   1. Imprimir certidao de casamento      *                ");
                         System.out.println("     *   2. Imprimir certidao de nascimento     *                ");
                         System.out.println("     *      3. Imprimir certidao de obito       *                ");
                         System.out.println("     *    4. Imprimir certidao de divorcio      *                 ");
                         System.out.println("     *               0. Voltar                  *                ");
                         op6=val.validarInt("Escolha a opcao: ", 0, 4);
                         
                         switch(op6)
                         {
                            case 1:
                                cs.certidao_Casamento();
                            break;
                                
                            case 2:
                                nc.certidao_Nascimento();
                            break;
                                
                            case 3:
                                nc.certidao_Obidto();
                            break;
                                
                            case 4:
                                cs.certidao_Divorcio();
                            break;
                                
                         }
                     }
                     while(op6!=0);
                break;
                    
                case 0:
                    System.err.println("FIM DA EXECUCAO!!!");
                break;
                    
                default:
                    System.err.println("Opcao invalida!");
                break;
            }
        }
        while (op!=0);
    }
}

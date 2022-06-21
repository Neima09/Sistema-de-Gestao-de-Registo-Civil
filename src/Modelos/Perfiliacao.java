/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author SIMIAO CANZE
 */
public class Perfiliacao extends Nascimento
{
    //Esta classe herda os atributos da classe "Nascimento"
    public Perfiliacao(int cod, String name, String apelid, String nrDoc, char gener, String local, String date, double valor, char st)
    {
        super(cod, name, apelid, nrDoc, gener, local, date, valor, st);
    }
    
    public Perfiliacao()
    {
        this(0, "", "", "", ' ', "", "", 0, ' ');
    }
}

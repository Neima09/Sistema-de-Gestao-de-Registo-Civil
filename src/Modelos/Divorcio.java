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
public class Divorcio extends Casamento
{
    //Esta classe herda os atributos da classe "Casamento"
    public Divorcio(int cod, String name, String apelid, String nrDoc, char gener, String local, String date, double valor, String localC, String estado)
    {
        super(cod, name, apelid, nrDoc, gener, local, date, valor, localC, estado);
    }
    
    public Divorcio(String name, String apelid, String nrDoc)
    {
        super(name, apelid, nrDoc);
    }
}

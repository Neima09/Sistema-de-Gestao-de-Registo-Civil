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
public class Nascimento extends Pessoa
{
    protected char status;
    //Esta classe herda os atributos da classe "Pessoa"
    public Nascimento(int cod, String name, String apelid, String nrDoc, char gener, String local, String date, double valor, char st)
    {
        super(cod, name, apelid, nrDoc, gener, local, date, valor);
        this.status=st;
    }
    
    public Nascimento()
    {
        this(0, "", "", "", ' ', "", "", 0, ' ');
    }
    
    public Nascimento(String name, String apelid, String nrDoc)
    {
        super(name, apelid, nrDoc);
    }
    
    public Nascimento(int i)
    {
        this("", "", "");
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    
    

    @Override
    public String toString() {
        return super.toString() + " status: " + status + "\n";
    }
    
    
    
}

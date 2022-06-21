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
public class Casamento extends Pessoa
{
    protected String localCasamento, status;
    
    //Esta classe herda os atributos da classe "Pessoa"
    
    public Casamento(int cod, String name, String apelid, String nrDoc, char gener, String local, String date, double valor, String localC, String estado)
    {
        super(cod, name, apelid, nrDoc, gener, local, date, valor);
        this.localCasamento = localC;
        this.status = status;
    }
    
    public Casamento()
    {
        this(0, "", "", "", ' ', "", "", 0, "", "");
    }
    
    
    
    public Casamento(String name, String apelid, String nrDoc)
    {
        super(name, apelid, nrDoc);
    }
    
    public Casamento(String noiva)
    {
        this("", "", "");
    }
    
    public Casamento(int cod, String name, String apelid, String nrDoc)
    {
        super(cod, name, apelid, nrDoc);
    }
    
    public Casamento(char casarCod)
    {
        super(0, "", "", "");
    }
    

    public void setLocalCasamento(String localCasamento) {
        this.localCasamento = localCasamento;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocalCasamento() {
        return localCasamento;
    }

    public String getStatus() {
        return status;
    }
    
    

    @Override
    public String toString() {
        return " Local do Casamento=" + localCasamento + "\n";
    }
    
    
    
}

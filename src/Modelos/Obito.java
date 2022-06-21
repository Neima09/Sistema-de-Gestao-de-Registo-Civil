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
public class Obito extends Nascimento
{
    //Esta classe herda os atributos da classe "Nascimento"
    private String nomeRequerente, apelidoRequerente, docRequerente, dataMorte;
    
    public Obito(int cod, String name, String apelid, String nrDoc, char gener, String local, String date, double valor, char st)
    {
        super(cod, name, apelid, nrDoc, gener, local, date, valor, st);
    }
    
    public Obito()
    {
        this(0, "", "", "", ' ', "", "", 0, ' ');
    }
    
    public Obito(int cod, String name, String apelid, String nrDoc, char gener, String local, String date, double valor, char st, String dataMort, String nomeReq, String apelidoReq, String docReq)
    {
        super(cod, name, apelid, nrDoc, gener, local, date, valor, st);
        this.nomeRequerente = nomeReq;
        this.apelidoRequerente = apelidoReq;
        this.docRequerente = docReq;
        this.dataMorte = dataMort;
    }
    
    public Obito(String morte)
    {
        this(0, "", "", "", ' ', "", "", 0, ' ', "", "", "", "");
    }
    
    public Obito(String name, String apelid, String nrDoc)
    {
        super(name, apelid, nrDoc);
    }
    
    public Obito(int i)
    {
        this("", "", "");
    }

    public String getNomeRequerente() {
        return nomeRequerente;
    }

    public String getApelidoRequerente() {
        return apelidoRequerente;
    }

    public String getDocRequerente() {
        return docRequerente;
    }

    public String getDataMorte() {
        return dataMorte;
    }

    public void setNomeRequerente(String nomeRequerente) {
        this.nomeRequerente = nomeRequerente;
    }

    public void setApelidoRequerente(String apelidoRequerente) {
        this.apelidoRequerente = apelidoRequerente;
    }

    public void setDocRequerente(String docRequerente) {
        this.docRequerente = docRequerente;
    }

    public void setDataMorte(String dataMorte) {
        this.dataMorte = dataMorte;
    }

    @Override
    public String toString() {
        return "nome do Requerente=" + nomeRequerente + ", apelido do Requerente=" + apelidoRequerente + ", doc Requerente=" + docRequerente + ", data da Morte=" + dataMorte + "\n";
    }
    
    
    
}

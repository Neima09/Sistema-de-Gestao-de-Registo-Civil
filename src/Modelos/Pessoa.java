/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;

/**
 *
 * @author SIMIAO CANZE
 */
public class Pessoa implements Serializable
{
    protected String nome, apelido, localProvincia, nrDocumento, data;
    protected char genero;
    protected double valorPagar;
    protected int codigo;
    
    public Pessoa(int cod, String name, String apelid, String nrDoc, char gener, String local, String date, double valor)
    {
        this.codigo = cod;
        this.nome = name;
        this.apelido = apelid;
        this.nrDocumento = nrDoc;
        this.genero = gener;
        this.localProvincia = local;
        this.data = date;
        this.valorPagar = valor;
    }
    
    public Pessoa()
    {
        this(0, "", "", "", ' ', "", "", 0);
    }
    
    public Pessoa(String name, String apelid, String nrDoc)
    {
        this.nome = name;
        this.apelido = apelid;
        this.nrDocumento = nrDoc;
    }
    
    public Pessoa(int i)
    {
        this("", "", "");
    }
    
    //Casamento
    public Pessoa(int cod, String name, String apelid, String nrDoc)
    {
        this.codigo = cod;
        this.nome = name;
        this.apelido = apelid;
        this.nrDocumento=nrDoc;
        
    }
    
    public Pessoa(String n)
    {
        this(0,"", "", "");
    }
    
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setLocalProvincia(String localProvincia) {
        this.localProvincia = localProvincia;
    }

    public void setNrDocumento(String nrDocumento) {
        this.nrDocumento = nrDocumento;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getLocalProvincia() {
        return localProvincia;
    }

    public String getNrDocumento() {
        return nrDocumento;
    }

    public String getData() {
        return data;
    }

    public char getGenero() {
        return genero;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public int getCodigo() {
        return codigo;
    }
    
    @Override
    public String toString() 
    {
        return "Codigo: "+codigo+", Nome: " + nome + ", Apelido: " + apelido + ", Natural de " + localProvincia + ", Numero deDocumento: " + nrDocumento + ", Data:" + data + ", Genero: " + genero + ", Valor pago:" + valorPagar+" ";
    }
    
    
}

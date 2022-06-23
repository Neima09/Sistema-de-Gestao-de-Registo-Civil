import java.io.*;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * Classe com funcoes auxiliares de tempo
 * Estas podem ser usadas para manipular o tempo
 * 
 */

public class FuncoesTempo {
    
    
    public FuncoesTempo(){}
    /**
     * calcula a quantidade de tempo dentre dois objectos temporais
     * @param unidade
     * @param tempoInclusivo o primeiro ponto no tempo
     * @param tempoExclusivo o segundo ponto no tempo
     * @return A quantidade de tempo em segundos
     */
    public static long tempoDentre(String unidade, Temporal tempoInclusivo, Temporal tempoExclusivo){
        long tempoDentre = 0L;
        try{
            switch(unidade){
                case "segundos":
                    tempoDentre = ChronoUnit.SECONDS.between(tempoInclusivo, tempoExclusivo);
                    break;
                case "dias":
                    tempoDentre = ChronoUnit.DAYS.between(tempoInclusivo, tempoExclusivo);
                    break;
            }
        }catch(UnsupportedTemporalTypeException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }catch(DateTimeException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return tempoDentre;
    }
    
    /**
     * Converte uma variavel LocalTime em segundos
     * @param tempo
     * @return O tempo em segundos
     */
    public static long tempoParaSegundos(LocalTime tempo){
        
        int horas = tempo.getHour();
        int minutos = tempo.getMinute();
        int segundos = tempo.getSecond();
        
        return (horas * 3600) + (minutos * 60) + segundos;
    }
    
    /**
     * Converte o tempo, dado no formato 'HH:mm::ss' em segundos
     * @param tempo
     * @return o tempo em segundos
     */
    public static long tempoParaSegundos(String tempo){
        
        String time[] = tempo.split(":");
        int horas = Integer.valueOf(time[0]);
        int minutos = Integer.valueOf(time[1]);
        int segundos = Integer.valueOf(time[2]);
        
        return (horas * 3600) + (minutos * 60) + segundos;
    }
    
    /**
     * Converte de segundos para o tempo em unidades:  dias, horas, minutos e segundos
     * @param tempoSegundos
     * @param mensagem 
     */
    public static void segundosParaMensagemTempo(long tempoSegundos, String mensagem){
        // 1 dia = 86400 segundos
        //1 hora = 3600 segundos
        //1 minuto = 60 segundos
        
        long dias = tempoSegundos / 86400;
        long horas = (tempoSegundos%86400) / 3600;
        long minutos = ((tempoSegundos%86400)%3600) / 60;
        long segundos = ((tempoSegundos%86400)%3600) % 60;
        
        System.out.println(mensagem + " " + dias + " dias, " + horas + " horas, " + minutos + " minutos e " + segundos + " segundos");
    }
    
    /**
     * formata a data: dd//MM//yyyy HH:mm:ss
     * @param data
     * @return data formatada
     */
    public static String formatarData(LocalDateTime data){
        String dataFormatada = "";
        try{
            
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            dataFormatada =  formatador.format(data);
            
        }catch(DateTimeParseException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return dataFormatada;
    }
    
    /**
     * formata a data: dd//MM//yyyy
     * @param data
     * @return data formatada
     */
    public static String formatarData(LocalDate data){
        String dataFormatada = "";
        try{
            
            DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataFormatada = formatador.format(data);
            
        }catch(DateTimeParseException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return dataFormatada;
    }
    
    /**
     * converte a data em String para uma variavel do tipo LocalDate
     * @param data
     * @return a data em LocalDate
     */
    public static LocalDate stringParaData(String data){
        LocalDate dataFormatada = null;
        try{
            
            DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataFormatada = LocalDate.parse(data, formatador);
            
        }catch(DateTimeParseException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return dataFormatada;
    }
    
    /**
     * converte o tempo em String para uma varialvel do tipo LocalTime
     * @param tempo
     * @return o tempo em LocalTime
     */
    public static LocalTime formatarTempo(String tempo){
        LocalTime tempoFormatado = null;
        try{
            
            DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("HH:mm:ss");
            tempoFormatado = LocalTime.parse(tempo, formatador);
            
        }catch(DateTimeParseException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return tempoFormatado;
    }
    
    /**
     * converte o tempo em String para uma varialvel do tipo LocalDateTime
     * @param data
     * @return a data em LocalDateTime
     */
    public static LocalDateTime stringParaTempoPlusHoras(String data){
        LocalDateTime tempoFormatado = null;
        try{
            
            tempoFormatado = LocalDateTime.parse(data);
            
        }catch(DateTimeParseException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return tempoFormatado;
    }
    
    /**
     * formata o tempo: HH:mm:ss
     * @param tempo
     * @return O tempo formatado
     */
    public static String formatarTempo(LocalTime tempo){
        String tempoFormatado = "";
        try{
            
            DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("HH:mm:ss");
            tempoFormatado = formatador.format(tempo);
            
        }catch(DateTimeParseException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        return tempoFormatado;
    }
    
    /**
     * retorna a data actual junto das horas
     * @return 
     */
    public static LocalDateTime dataActualPlusHoras(){
        
        return LocalDateTime.now();
    }
    
    /**
     * retorna a data actual
     * @return 
     */
    public static LocalDate dataActual(){
        
        return LocalDate.now();
    }
    
    /**
     * retorna a hora actual
     * @return 
     */
    public static LocalTime horaActual(){
        
        return LocalTime.now();
    }
    
    /**
     * adiciona uma dada quantidade de tempo em segundos a data
     * @param data
     * @param segundos
     * @return o resultado da soma dos tempos
     */
   public static LocalDateTime adicionaTempo(LocalDateTime data, long segundos){
       
       return data.plus(segundos, ChronoUnit.SECONDS);
   } 
    
}

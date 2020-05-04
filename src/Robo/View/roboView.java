package Robo.View;

import java.io.File;

public class roboView {
    public static String link(File file){
        return "<a href='" + file.getAbsolutePath() + "'>" + file.getName() + "</a>";
    }
    
    public static String br(){
        return "<br>";
    }
    
    public static String mensagemNaoEncontrado(String search, File path){
        return "O/A arquivo/pasta '" + search + "' não foi encontrado em "+  link(path) + br();
    }
    public static String mensagemNaoEncontrado(File path){
        return "O/A arquivo/pasta " +  link(path) + " não foi encontrado(a)." + br();
    }
}

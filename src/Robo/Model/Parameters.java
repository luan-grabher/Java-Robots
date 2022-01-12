package Robo.Model;

import fileManager.FileManager;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Parameters {

    //Constantes
    private static String CHAR_Abertura = "\\[";
    private static String CHAR_Fechamento = "]";
    private static String CHAR_Divisor = ":";

    private File arquivoParametros = null;
    public final Map<String, String> values = new HashMap<>();

    public static void setChars(String CHAR_Abertura, String CHAR_Divisor, String CHAR_Fechamento) {
        Parameters.CHAR_Abertura = CHAR_Abertura;
        Parameters.CHAR_Divisor = CHAR_Divisor;
        Parameters.CHAR_Fechamento = CHAR_Fechamento;
    }

    public static void setCHAR_Abertura(String CHAR_Abertura) {
        Parameters.CHAR_Abertura = CHAR_Abertura;
    }

    public static void setCHAR_Fechamento(String CHAR_Fechamento) {
        Parameters.CHAR_Fechamento = CHAR_Fechamento;
    }

    public static void setCHAR_Divisor(String CHAR_Divisor) {
        Parameters.CHAR_Divisor = CHAR_Divisor;
    }

    public Parameters(File arquivoParametros) {
        this.arquivoParametros = arquivoParametros;
        String textoArquivoParametros = FileManager.getText(this.arquivoParametros.getAbsolutePath());

        //Print text on file
        System.out.println("Texto do arquivo de parametros: " + textoArquivoParametros);

        FileManager.save(this.arquivoParametros.getAbsolutePath(), "usado");

        definir(textoArquivoParametros);
    }

    public Parameters(String textoArquivoParametros) {
        this.arquivoParametros = null;

        definir(textoArquivoParametros);
    }

    public Parameters() {
    }

    private void definir(String textoArquivoParametros) {
        try {

            //Divide pelao caractere de abertura
            String[] divisoes = textoArquivoParametros.split(CHAR_Abertura);

            for (String divisao : divisoes) {
                //Se divisão conter seu fechamento
                if (divisao.contains(CHAR_Fechamento)) {
                    //Pega texto até o fechamento
                    String parametroString = divisao.substring(0, divisao.indexOf(CHAR_Fechamento));

                    //divide texto pelo caractere divisor
                    String[] parametroSplit = parametroString.split(CHAR_Divisor, 2);

                    //Verifica se há apenas o nome e valor
                    if (parametroSplit.length == 2) {
                        values.put(parametroSplit[0], parametroSplit[1]);
                    }
                }
            }

        } catch (Exception e) {
        }
    }
}

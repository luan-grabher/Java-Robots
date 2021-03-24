package Robo;

import Entity.Executavel;
import Executor.Execution;
import Robo.Model.Parameters;
import SimpleView.View;
import fileManager.FileManager;
import java.io.File;
import java.util.List;
import java.util.Map;

public class AppRobo {

    private String nome;
    private File arquivoParametros = new File("//localhost/Robos/Tarefas/parametros.cfg").getAbsoluteFile();
    private File localRetorno = new File("//localhost/Robos/Retornos de Tarefas/").getAbsoluteFile();

    public Parameters parametros = null;
    private Execution execution;

    public AppRobo(String nome) {
        this.nome = nome;

        execution = new Execution("Executando robô " + this.nome);
        execution.setShowMessages(false);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void definirParametros() {
        try {
            Parameters parametros = new Parameters(arquivoParametros);
        } catch (Exception e) {
            Parameters parametros = new Parameters("");
        }
    }

    public void definirParametros(String textoParametros) {
        parametros = new Parameters(textoParametros);
    }

    public String getRetornoPadrao() {
        return "Parece que eu perdi a mensagem que eu deveria mostrar para você, "
                + "mas não se preocupe, geralmente isso acontece quando faço o meu "
                + "trabalho coretamente e não tenho nada para falar. Você poderia "
                + "conferir se eu fiz o que deveria ter feito? Caso eu não tenha "
                + "feito por favor contate o programador!";
    }

    public void executar(String retornoFuncao) {
        try {
            String titulo = "Retorno da tarefa " + nome;

            //Verifica se possui Id
            Integer idTarefa = Integer.parseInt(parametros.values.getOrDefault("idTarefa", "0"));
            if (idTarefa > 0) {
                retornoFuncao = retornoFuncao.equals("") ? getRetornoPadrao() : retornoFuncao;
                retornoFuncao = retornoFuncao.replaceAll("\n", "<br>");
                if (!FileManager.save(localRetorno.getAbsolutePath() + "/" + idTarefa + ".html", titulo + "§" + retornoFuncao)) {
                    System.out.println("Ocorreu um erro ao salvar retorno da tarefa!");
                }
            } else {
                String[] options = new String[]{"PrintLn", "Arquivo no Desktop"};
                int option = View.chooseButton("Você está testando o robô, escolha como quer visualizar o retorno:", options);
                if (option == 0) {
                    System.out.println(titulo + "\n\n" + retornoFuncao.replaceAll("<br>", "\n"));
                } else {
                    File desktop = new File(System.getProperty("user.home") + "/Desktop");
                    FileManager.save(desktop.getAbsolutePath() + "/test robo.html", titulo + "<br><br>" + retornoFuncao);
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu a excessão: " + e);
            e.printStackTrace();
        } catch (Error e) {
            System.out.println("Ocorreu um erro: " + e);
            e.printStackTrace();
        } finally {
            //Finaliza JFRAME
            execution.endExecution();
        }

        //Garante fechamento Robô
        System.exit(0);
    }

    public String getParametro(String nomeParametro) throws Exception {
        if(parametros == null) throw new Exception("A variavel de parametros ficou em branco");
        if(parametros.values == null) throw new Exception("A variavel de parametros.value ficou em branco");
        return parametros.values.get(nomeParametro);
    }

    public void setArquivoParametros(File arquivoParametros) {
        this.arquivoParametros = arquivoParametros;
    }

    public void setLocalRetorno(File localRetorno) {
        this.localRetorno = localRetorno;
    }

    public static String rodarExecutaveis(String nomeApp, List<Executavel> executaveis) {
        Execution execucao = new Execution(nomeApp);
        execucao.setExecutables(executaveis);
        execucao.setShowMessages(false);
        execucao.runExecutables();
        execucao.endExecution(false);
        return execucao.getRetorno();
    }

    public static String rodarExecutaveis(String nomeApp, Map<String, Executavel> executaveis) {
        Execution execucao = new Execution(nomeApp);
        execucao.setExecutionMap(executaveis);
        execucao.setShowMessages(false);
        execucao.runExecutables();
        execucao.endExecution(false);
        return execucao.getRetorno();
    }
}

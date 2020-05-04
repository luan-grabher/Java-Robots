package Teste;

public class Testes {

    public static void main(String[] args) {
        //Instanciar Robo
        Robo.AppRobo robo = new Robo.AppRobo("Aplicação XYZ");

        robo.definirParametros();
        robo.executar(
                funcaoTeste(
                        robo.getParametro("mes").getInteger(),
                        robo.getParametro("ano").getInteger()
                )
        );
    }

    private static String funcaoTeste(Integer mes, Integer ano) {
        System.out.println("Função executada");

        return "Tudo Ok";
    }

}

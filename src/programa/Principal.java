package programa;

import java.util.ArrayList;

public class Principal {

    String EPSILON = "ε";

    public Principal() {

        ArrayList<String> terminais = new ArrayList<>();

        terminais.add("(");
        terminais.add(")");
        terminais.add("id");
        terminais.add("*");
        terminais.add("+");
        terminais.add(EPSILON);

        ArrayList<String> naoTerminais = new ArrayList<>();

        naoTerminais.add("E");
        naoTerminais.add("E'");
        naoTerminais.add("T");
        naoTerminais.add("T'");
        naoTerminais.add("F");

        ArrayList<Regra> regrasDeProducao = new ArrayList<>();

        regrasDeProducao.add(new Regra("E -> T | T'"));
        regrasDeProducao.add(new Regra("E' -> +TE' | ε"));
        regrasDeProducao.add(new Regra("T -> FT'"));
        regrasDeProducao.add(new Regra("T' -> *FT' | ε"));
        regrasDeProducao.add(new Regra("F -> (E) | id"));

        String simboloInicial = "E";

        Gramatica gramatica = new Gramatica(terminais, naoTerminais, regrasDeProducao, simboloInicial);

        Programa programa = new Programa(gramatica);

    }

    public static void main(String[] args) {
        Principal initProg = new Principal();
    }
}

package programa;

import java.util.ArrayList;

public class Gramatica {

    ArrayList<String> terminal;

    ArrayList<String> NaoTerminal;

    ArrayList<Regra> regrasDeProducao;

    String simboloInicial;

    public Gramatica(ArrayList<String> terminal, ArrayList<String> NaoTerminal, ArrayList<Regra> regrasDeProducao, String simboloInicial) {
        this.terminal = terminal;
        this.NaoTerminal = NaoTerminal;

        //remove regras repetidas... ex:
        // S -> F
        // S -> (S + F)
        ArrayList<String> simbolosNaoRepetidos = new ArrayList<>();
        for (int i = 0; i < regrasDeProducao.size(); i++) {

            String simbolo = regrasDeProducao.get(i).simbolo;

            if (simbolosNaoRepetidos.indexOf(simbolo) < 0) {
                simbolosNaoRepetidos.add(simbolo);
            }

        }

        if (simbolosNaoRepetidos.size() < regrasDeProducao.size()) {

            ArrayList<Regra> novoArrayRegras = new ArrayList<>();
            for (int j = 0; j < simbolosNaoRepetidos.size(); j++) {

                String simbolo = simbolosNaoRepetidos.get(j);

                Regra regra = new Regra();

                regra.setSimbolo(simbolo);

                novoArrayRegras.add(regra);

                for (int k = 0; k < regrasDeProducao.size(); k++) {

                    String simboloRegra = regrasDeProducao.get(k).getSimbolo();

                    ArrayList<String> corpoRegra = regrasDeProducao.get(k).getCorpo();

                    if (simboloRegra.equals(simbolo)) {

                        regra.pushCorpo(corpoRegra);

                    }

                }

            }

            this.regrasDeProducao = novoArrayRegras;

        } else {
            this.regrasDeProducao = regrasDeProducao;
        }

        this.simboloInicial = simboloInicial;
    }

}

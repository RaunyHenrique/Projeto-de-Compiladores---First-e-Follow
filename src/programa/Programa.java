package programa;

import java.util.ArrayList;

public class Programa {

    Gramatica gramatica;

    First first = new First();

    Follow follow = new Follow();


    public Programa(Gramatica gramatica) {

        this.gramatica = gramatica;

        initFirst(this.gramatica.regrasDeProducao);

        initFollow(this.gramatica.regrasDeProducao);

        printFirst();

        System.out.print("\n");

        printFollow();

    }

    public void initFirst(ArrayList<Regra> regrasDeProducao) {

        int indexSimboloEmAnalise;

        int indexCountCorpoDaRegraEmAnalise;

        ArrayList<String> terminais = new ArrayList<>();

        for (int i = 0; i < regrasDeProducao.size(); i++) {

            indexCountCorpoDaRegraEmAnalise = 0;

            for (int j = 0; j < regrasDeProducao.get(i).corpo.size(); j++) {

                indexSimboloEmAnalise = getIndexSimbolo(regrasDeProducao.get(i).simbolo);

                while (true) {

                    String corpoDaRegra = regrasDeProducao.get(indexSimboloEmAnalise).corpo.get(indexCountCorpoDaRegraEmAnalise);

                    String token = "" + corpoDaRegra.charAt(0);

                    if (1 < corpoDaRegra.length()) {
                        String proxToken = "" + corpoDaRegra.charAt(1);
                        if (proxToken.equals("'")) {
                            token += proxToken;
                        }
                    }

                    if (isNaoTerminal(token)) {

                        indexSimboloEmAnalise = getIndexSimbolo(token);
                        //System.out.print(indexSimboloEmAnalise);
                        indexCountCorpoDaRegraEmAnalise = 0;

                    } else {

                        for (int k = 0; k < regrasDeProducao.get(indexSimboloEmAnalise).corpo.size(); k++) {

                            String tokenEmAnalise = regrasDeProducao.get(indexSimboloEmAnalise).corpo.get(k);

                            String TokenItem = "" + tokenEmAnalise.charAt(0);

                            if (1 < tokenEmAnalise.length()) {
                                String proxTokenItem = "" + tokenEmAnalise.charAt(1);
                                if (proxTokenItem.equals("'")) {
                                    TokenItem += proxTokenItem;
                                }
                            }

                            if (tokenEmAnalise.equals("id") || tokenEmAnalise.equals("ε")) {
                                if (terminais.indexOf(tokenEmAnalise) < 0) {
                                    terminais.add(tokenEmAnalise);
                                }
                            } else {
                                if (!isNaoTerminal(TokenItem)) {
                                    if (terminais.indexOf(TokenItem) < 0) {
                                        terminais.add(TokenItem);
                                    }
                                }
                            }
                        }

                        indexCountCorpoDaRegraEmAnalise++;
                        break;

                    }

                }

            }

            String simboloRegra = regrasDeProducao.get(i).simbolo;

            Regra obgRegra = new Regra();

            obgRegra.setSimbolo(simboloRegra);
            obgRegra.setCorpo(terminais);

            //add corpo do simb. terminal que esta sendo analisado
            first.pushRegra(obgRegra);

            terminais = new ArrayList<>();

        }

    }

    public void initFollow(ArrayList<Regra> regrasDeProducao) {

        int indexSimboloEmAnalise;

        int indexCountCorpoDaRegraEmAnalise;

        ArrayList<String> terminais = new ArrayList<>();

        for (int i = 0; i < regrasDeProducao.size(); i++) {

            indexCountCorpoDaRegraEmAnalise = 0;

            String simboloASerProcurado = regrasDeProducao.get(i).simbolo;

            for (int j = 0; j < regrasDeProducao.get(i).corpo.size(); j++) {

                indexSimboloEmAnalise = getIndexSimbolo(regrasDeProducao.get(i).simbolo);

                String corpoDaRegraAtual = regrasDeProducao.get(i).corpo.get(j);

                if (corpoDaRegraAtual.contains(simboloASerProcurado)) {

                    if (corpoDaRegraAtual.length() > 1) {

                        for (int k = 0; k < corpoDaRegraAtual.length(); k++) {

                            String tokenAtual = "" + corpoDaRegraAtual.charAt(k);

                            if (tokenAtual.equals(simboloASerProcurado)) {

                                if (k + 1 < corpoDaRegraAtual.length()) {

                                    String proxToken = "" + corpoDaRegraAtual.charAt(k + 1);

                                    Regra regra =  first.regra.get(getIndexSimbolo(tokenAtual));

                                    ArrayList<String> aux = new ArrayList<>();

                                    aux.add(proxToken);

                                    regra.pushCorpo(aux);

                                } else {//se n, pega o simb. term.

                                    Regra regra =  first.regra.get(getIndexSimbolo(tokenAtual));

                                    ArrayList<String> aux = new ArrayList<>();

                                    aux.add(tokenAtual);

                                    regra.pushCorpo(aux);

                                }

                                while (true) {

                                    String corpoDaRegra = regrasDeProducao.get(indexSimboloEmAnalise).corpo.get(indexCountCorpoDaRegraEmAnalise);

                                    String token = "" + corpoDaRegra.charAt(0);

                                    if (1 < corpoDaRegra.length()) {
                                        String proxToken = "" + corpoDaRegra.charAt(1);
                                        if (proxToken.equals("'")) {
                                            token += proxToken;
                                        }
                                    }

                                    if (isNaoTerminal(token)) {

                                        indexSimboloEmAnalise = getIndexSimbolo(token);
                                        //System.out.print(indexSimboloEmAnalise);
                                        indexCountCorpoDaRegraEmAnalise = 0;

                                    } else {

                                        for (int p = 0; p < regrasDeProducao.get(indexSimboloEmAnalise).corpo.size(); p++) {

                                            String tokenEmAnalise = regrasDeProducao.get(indexSimboloEmAnalise).corpo.get(p);

                                            String TokenItem = "" + tokenEmAnalise.charAt(0);

                                            if (1 < tokenEmAnalise.length()) {
                                                String proxTokenItem = "" + tokenEmAnalise.charAt(1);
                                                if (proxTokenItem.equals("'")) {
                                                    TokenItem += proxTokenItem;
                                                }
                                            }

                                            if (tokenEmAnalise.equals("id") || tokenEmAnalise.equals("ε")) {
                                                if (terminais.indexOf(tokenEmAnalise) < 0) {
                                                    terminais.add(tokenEmAnalise);
                                                }
                                            } else {
                                                if (!isNaoTerminal(TokenItem)) {
                                                    if (terminais.indexOf(TokenItem) < 0) {
                                                        terminais.add(TokenItem);
                                                    }
                                                }
                                            }
                                        }

                                        indexCountCorpoDaRegraEmAnalise++;
                                        break;

                                    }

                                }

                            }

                        }

                    } else {//aplica A -> αB.. ex: S -> F

                        while (true) {

                            String corpoDaRegra = regrasDeProducao.get(indexSimboloEmAnalise).corpo.get(indexCountCorpoDaRegraEmAnalise);

                            String token = "" + corpoDaRegra.charAt(0);

                            if (1 < corpoDaRegra.length()) {
                                String proxToken = "" + corpoDaRegra.charAt(1);
                                if (proxToken.equals("'")) {
                                    token += proxToken;
                                }
                            }

                            if (isNaoTerminal(token)) {

                                indexSimboloEmAnalise = getIndexSimbolo(token);
                                //System.out.print(indexSimboloEmAnalise);
                                indexCountCorpoDaRegraEmAnalise = 0;

                            } else {

                                for (int k = 0; k < regrasDeProducao.get(indexSimboloEmAnalise).corpo.size(); k++) {

                                    String tokenEmAnalise = regrasDeProducao.get(indexSimboloEmAnalise).corpo.get(k);

                                    String TokenItem = "" + tokenEmAnalise.charAt(0);

                                    if (1 < tokenEmAnalise.length()) {
                                        String proxTokenItem = "" + tokenEmAnalise.charAt(1);
                                        if (proxTokenItem.equals("'")) {
                                            TokenItem += proxTokenItem;
                                        }
                                    }

                                    if (tokenEmAnalise.equals("id") || tokenEmAnalise.equals("ε")) {
                                        if (terminais.indexOf(tokenEmAnalise) < 0) {
                                            terminais.add(tokenEmAnalise);
                                        }
                                    } else {
                                        if (!isNaoTerminal(TokenItem)) {
                                            if (terminais.indexOf(TokenItem) < 0) {
                                                terminais.add(TokenItem);
                                            }
                                        }
                                    }
                                }

                                indexCountCorpoDaRegraEmAnalise++;
                                break;

                            }

                        }

                    }

                }

            }

            String simboloRegra = regrasDeProducao.get(i).simbolo;

            Regra obgRegra = new Regra();

            obgRegra.setSimbolo(simboloRegra);

            if (i == 0) {
                terminais.add("$");
            }

            obgRegra.setCorpo(terminais);

            //add corpo do simb. terminal que esta sendo analisado
            follow.pushRegra(obgRegra);

            terminais = new ArrayList<>();

        }

    }

    public void printFirst() {
        System.out.print(first.toString());
    }

    public void printFollow() {
        System.out.print(follow.toString());
    }

    public int getIndexSimbolo(String simbolo) {

        int index = -1;
        for (int i = 0; i < gramatica.regrasDeProducao.size(); i++) {

            if (simbolo.equals(gramatica.regrasDeProducao.get(i).simbolo)) {
                return i;
            }

        }

        return index;
    }

    public boolean isNaoTerminal(String token) {

        for (int k = 0; k < gramatica.NaoTerminal.size(); k++) {

            if (token.equals(gramatica.NaoTerminal.get(k))) {
                return true;
            }

        }

        return  false;
    }

    public boolean isTerminal(String token) {

        for (int k = 0; k < gramatica.terminal.size(); k++) {

            if (token.equals(gramatica.terminal.get(k))) {
                return true;
            }

        }

        return  false;
    }

}

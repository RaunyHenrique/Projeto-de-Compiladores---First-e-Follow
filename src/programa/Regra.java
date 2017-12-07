package programa;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regra {

    String simbolo;

    ArrayList<String> corpo = new ArrayList<>();

    String regProducao = "^(\\s*[A-Z]+(')?\\s*->\\s*)+((\\W+)|(\\w+))+";

    public Regra(String regra) {

        if (isValidRegraDeProducao(regra)) {

            //elimina espaços em branco
            String aux = regra.replaceAll("\\s*", "");

            this.simbolo = "" + regra.charAt(0);

            String linha = "" + regra.charAt(1);
            String aux2 = "";
            if (linha.equals("'")) {
                this.simbolo += linha;
                //retira "->" da string
                aux2 = aux.substring(4);
            } else {
                //retira "->" da string
                aux2 = aux.substring(3);
            }

            //analisa e trata os "|".. "OU"
            String str = "";
            for (int i = 0; i < aux2.length(); i++) {

                String token = "" + aux2.charAt(i);

                if (token.equals("|")) {
                    this.corpo.add(str);
                    str = "";
                } else {
                    str += token;
                }

            }

            this.corpo.add(str);

        } else {
            System.out.print("Uma ou mais regras de produção são inválidas :(");
        }

    }

    public Regra() { }

    public boolean isValidRegraDeProducao(String producao) {
        Pattern patternElem = Pattern.compile(regProducao);
        Matcher match = patternElem.matcher(producao);

        return match.find();
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public ArrayList<String> getCorpo() {
        return corpo;
    }

    public void setCorpo(ArrayList<String> corpo) {
        this.corpo = corpo;
    }

    public void pushCorpo(ArrayList<String> corpo) {
        this.corpo.addAll(corpo);
    }

}

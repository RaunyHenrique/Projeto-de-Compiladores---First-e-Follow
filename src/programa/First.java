package programa;

import java.util.ArrayList;

public class First {

    ArrayList<Regra> regra = new ArrayList<>();

    public ArrayList<Regra> getRegra() {
        return regra;
    }

    public void setRegra(ArrayList<Regra> regra) {
        this.regra = regra;
    }

    public void pushRegra(Regra regra) {
        this.regra.add(regra);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < regra.size(); i++) {
            str += "\nFirst(" + regra.get(i).getSimbolo() + ") = " + regra.get(i).getCorpo().toString();
        }
        return str;
    }

}

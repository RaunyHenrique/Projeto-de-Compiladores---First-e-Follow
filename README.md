# Projeto-de-Compiladores---First-e-Follow

* Abra o projeto na sua IDE favorita;
* Abra o arquivo "Principal.java";
* Você vera 3 ArrayLists - um para simbolos não terminais, simbolos terminais e de regras de produção. Além de uma string, para definir 
o simbolo inicial. Defina essas variaveis com a gramatica que preferir.
***
* Exemplo: 

        String EPSILON = "ε";

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
 
 * Saida do programa (PROGRAMA INCOMPLETO):
 
        First(E) = [(, id, *, ε]
        First(E') = [+, ε]
        First(T) = [(, id, ']
        First(T') = [*, ε]
        First(F) = [(, id]

        Follow(E) = [$]
        Follow(E') = []
        Follow(T) = [(, id]
        Follow(T') = []
        Follow(F) = []

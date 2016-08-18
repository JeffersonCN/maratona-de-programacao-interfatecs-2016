import java.util.*;

class vacina {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> sequencias;
        Set<String> subSet;
        int tam, qtdeSubs, repete, cont;
        String sub, seq;

        while (scan.hasNext()) {
            // ArrayList pra guardar as sequencias (dengue, zika, chikun)
            sequencias = new ArrayList<>();
            // Set pra guardar as subsequências que já foram testadas
            subSet = new HashSet<>();
            // Contador pra guardar quantas subsequencias estão presentes nas 3 sequências
            cont = 0;

            // Faz a leitura das sequencias adicionando no ArrayList
            for (int i = 0; i < 3; i++) {
                sequencias.add(scan.next());
            }
            // Faz a leitura do tamanho das subsequências a serem testadas
            tam = scan.nextInt();

            // Pra cada sequência...
            for (String sequencia : sequencias) {
                // Define a quantidade de subsequencias possíveis
                qtdeSubs = sequencia.length() - tam + 1;

                // Pra cada subsequência..
                for (int i = 0; i < qtdeSubs; i++) {
                    // Pega a subsequência de determinada sequencia
                    sub = sequencia.substring(i, i + tam);
                    // Reseta o número de repetições
                    repete = 0;

                    // Se a subsequência ainda não foi testada, então ela ainda não foi incluida no Set e pode ser testada
                    if (!subSet.contains(sub)) {
                        // Pra cada uma das sequencias iniciais (zika, dengue, etc)
                        for (int j = 0; j < 3; j++) {
                            // Pega a sequencia
                            seq = sequencias.get(j);
                            
                            // Verifica se ela é igual à sequencia inicial
                            if (seq.equals(sequencia)) {
                                // Se elas forem iguais, então com certeza ela contém a subsequência
                                // então incrementa o número de repetições
                                repete++;
                                // e pula pra próxima iteração
                                continue;
                            }
                            
                            // Esse if só é alcançável caso a sequencia inicial seja diferente da testada
                            // Se a sequência testada contém a subsequência
                            if (seq.contains(sub)) {
                                // Incrementa o número de repetições
                                repete++;
                            }
                        }

                        // Se a subsequência se repetiu nas 3 sequências
                        if (repete == 3) {
                            // Incrementa o contador
                            cont++;
                        }
                    }

                    // Adiciona a subsequência testada no Set, pra que ela não seja testada novamente
                    subSet.add(sub);
                }
            }
            // Imprime a saída
            System.out.println(cont);
        }
    }
}

import java.util.*;

class amigos {

    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);

        // Leitura do número de casos
        int nCasos = leia.nextInt();

        int nLinhas, nColunas, linhaAtual, colunaAtual, proxLinha, proxColuna, migalhas, contaDirecoes, ultimaPontuacao;
        int[] posDoce = new int[2];
        boolean acabar, voltar;
        String pos, linha, caractere, nome = null;
        String[] caracteres;
        String[][] floresta, mapa;
        Map<String, int[]> posInicial = new HashMap<>();
        Map<String, Integer> placar, nVisitas;
        Map<String, String[]> prioritarias = new HashMap<>();

        // Esse mapa guarda as direções prioritárias pra cada jogador
        prioritarias.put("B", new String[]{"L", "O", "N", "S"});
        prioritarias.put("J", new String[]{"N", "L", "S", "O"});
        prioritarias.put("M", new String[]{"S", "N", "O", "L"});

        // Esse FOR vai rodar cada caso
        for (int i = 0; i < nCasos; i++) {
            // Pega o tamanho da Floresta
            nLinhas = leia.nextInt();
            nColunas = leia.nextInt();

            // Cria uma matriz pra guardar as informações da floresta
            floresta = new String[nLinhas][nColunas];

            // Inicia a leitura linha por linha
            for (int j = 0; j < nLinhas; j++) {
                linha = leia.next();
                // Quebra a linha em um array de caracteres
                caracteres = linha.split("");

                // Cada caractere está situado em uma "coluna"
                for (int k = 0; k < nColunas; k++) {
                    // Pega o caractere na coluna k
                    caractere = caracteres[k];

                    // Se o caractere lido for algum dos jogadores
                    if (caractere.equals("J") || caractere.equals("M") || caractere.equals("B")) {
                        // Guarda a informação na posição no mapa das posições iniciais
                        posInicial.put(caractere, new int[]{j, k});
                        // Se o caractere lido for referente ao doce
                    } else if (caractere.equals("D")) {
                        // Guarda a posição do doce
                        posDoce[0] = j;
                        posDoce[1] = k;
                    }

                    // Insere o caractere lido na matriz da Floresta
                    floresta[j][k] = caractere;
                }
            }

            // LEITURA FINALIZADA
            // Reseta a quantidade de direções prioritárias testadas
            contaDirecoes = 0;
            // Cria um novo mapa, pra armazenar o placar
            placar = new HashMap<>();
            // Essa variável vai ser usada depois pra comparar qual jogador andou menos
            ultimaPontuacao = 0;

            // INICIA O JOGO 
            trocandoJogador:
            // PRA CADA JOGADOR
            for (String jogador : posInicial.keySet()) {
                // Esse mapa guarda a quantidade de vezes que o jogador passou por determinada posição (linha, coluna)
                nVisitas = new HashMap<>();
                // Inicializa a posição atual do jogador com sua posição inicial
                linhaAtual = posInicial.get(jogador)[0];
                colunaAtual = posInicial.get(jogador)[1];
                // Inicializa a contagem de visitas da posição inicial com zero
                nVisitas.put(String.format("%d %d", linhaAtual, colunaAtual), 0);

                // Esse novo mapa é clonado da floresta original para ser manipulado durante o jogo
                // pra evitar de alterar o mapa original da floresta
                mapa = floresta.clone();
                for (int j = 0; j < floresta.length; j++) {
                    mapa[j] = floresta[j].clone();
                }
                // Defini que a MIGALHA vai ser representada no mapa por um asterisco
                // como a migalha deve ser deixada por onde passar, já deixa uma na posição atual do jogador, que no momento é a posição inicial
                mapa[linhaAtual][colunaAtual] = "*";

                // FLAG que termina a jogada
                acabar = false;
                // FLAG que determina se jogador pode voltar pra um ponto onde já existe migalha
                voltar = false;

                // Então, enquanto não for fim da jogada
                while (!acabar) {
                    // Esse if foi criado para o caso de o jogador estar impedido de qualquer movimento
                    // ele conta quantas vezes o jogador fica na posição inicial e vai incrementando lá no mapa das Visitas
                    if (linhaAtual == posInicial.get(jogador)[0] && colunaAtual == posInicial.get(jogador)[1]) {
                        int visitas = nVisitas.get(String.format("%d %d", linhaAtual, colunaAtual));
                        visitas++;
                        nVisitas.put(String.format("%d %d", linhaAtual, colunaAtual), visitas);
                    }

                    // Esse FOR é usado para iterar sobre as direções prioritárias e definir qual delas seguir
                    procurandoDirecao:
                    for (int k = 0; k < prioritarias.get(jogador).length; k++) {
                        // Pega a direção lá do mapa de direções
                        String direction = prioritarias.get(jogador)[k];
                        // Coloca a posição atual em variáveis auxiliares
                        proxLinha = linhaAtual;
                        proxColuna = colunaAtual;

                        // Incrementa a quantidade de direções testadas
                        contaDirecoes++;

                        // Se o jogador já passou por aquele ponto mais de 2 vezes, é sinal que está encontrando o doce
                        if (nVisitas.get(String.format("%d %d", linhaAtual, colunaAtual)) > 2) {
                            // Ativa a flag pra terminar a jogada
                            acabar = true;
                            // Dá continuidade ao jogo com o próximo jogador
                            continue trocandoJogador;
                        }

                        // Se já verificou mais que 4 direções e ainda assim não encontrou pra que lado ir
                        if (contaDirecoes > 4) {
                            // Ativa a flag que permite voltar para um ponto que já tem migalha
                            voltar = true;
                        }

                        // Hora de aplicar a alteração da posição nas variáveis auxiliares
                        switch (direction) {
                            // Caso direção seja NORTE
                            case "N":
                                // Só é possível andar para o norte se não estiver no limite superior do mapa
                                if (linhaAtual > 0) {
                                    // Diminui uma linha, ou seja, sobe uma linha
                                    proxLinha--;
                                } else {
                                    // Se está no limite, continua testando outras direções
                                    continue procurandoDirecao;
                                }
                                break;
                            // MESMA COISA PARA OS PRÓXIMOS CASOS
                            // Sempre verificando se a posição permite determinado movimento
                            // Caso contrário, continua testando outras direções
                            case "S":

                                if (linhaAtual < nLinhas - 1) {
                                    proxLinha++;
                                } else {
                                    continue procurandoDirecao;
                                }
                                break;
                            case "L":
                                if (colunaAtual < nColunas - 1) {
                                    proxColuna++;
                                } else {
                                    continue procurandoDirecao;
                                }
                                break;
                            case "O":
                                if (colunaAtual > 0) {
                                    proxColuna--;
                                } else {
                                    continue procurandoDirecao;
                                }
                                break;
                        }

                        // Agora que tem a posição do possível próximo movimento
                        // pega o caractere do mapa naquela posição
                        caractere = mapa[proxLinha][proxColuna];

                        // Se for árvore
                        if (caractere.equals("#")) {
                            // Interrompe e vai testar outra direção
                            continue procurandoDirecao;
                            // Se for migalha mas não está autorizado a voltar para caminho anterior
                        } else if (caractere.equals("*") && !voltar) {
                            // Interrompe também e testa otura direção
                            continue procurandoDirecao;
                        } else {
                            // Caso contrário, pode se mover para aquela posição
                            // Atualiza os dados de posição atual
                            linhaAtual = proxLinha;
                            colunaAtual = proxColuna;
                            // Cria uma string com as posições separadas por um espaço
                            // Eu utilizei dessa forma porque em Java existe uma dificuldade em usar um vetor como key de um Mapa
                            pos = String.format("%d %d", linhaAtual, colunaAtual);

                            // Se essa posição já existe na contagem de visitas, só incrementa
                            if (nVisitas.get(pos) != null) {
                                int qtde = nVisitas.get(pos);
                                qtde++;
                                nVisitas.put(pos, qtde);
                            } else {
                                // Se não estiver, inclui a posição no mapa e atribui a primeira visita
                                nVisitas.put(pos, 1);
                            }

                            // Se não deixou migalha ainda naquela posição
                            if (!voltar) {
                                // Altera o caractere para a migalha
                                mapa[linhaAtual][colunaAtual] = "*";
                            }

                            // Reseta a flag para false
                            voltar = false;
                            // Reseta a contagem de direções testadas
                            contaDirecoes = 0;

                            // Se essa nova posição é também a posição do doce
                            if (linhaAtual == posDoce[0] && colunaAtual == posDoce[1]) {
                                // A quantidade de migalhas vai ser a quantidade de posições visitadas, sem repetições
                                migalhas = nVisitas.size();
                                // Se foi a primeira rodada, a ultima pontuação é zero
                                if (ultimaPontuacao == 0) {
                                    // Guarda o valor da pontuação do primeiro jogador
                                    ultimaPontuacao = migalhas;
                                    // Se algum outro jogador chegar no doce com menos migalhas, temos um novo candidato a vencedor
                                } else if (ultimaPontuacao > migalhas) {
                                    // Remove o jogador anterior do placar
                                    placar.clear();
                                }

                                // Se a quantidade de migalhas do atual jogador for menor ou igual a do jogador anterior
                                if (ultimaPontuacao >= migalhas) {
                                    // Adiciona o jogador e sua pontuação no placar
                                    placar.put(jogador, migalhas);
                                }

                                // Já que encontrou o doce, termina a jogada
                                acabar = true;
                                // Continua o jogo com o próximo jogador
                                continue trocandoJogador;
                            }
                            // Como já se moveu, interrompe a procura por nova direção
                            break procurandoDirecao;
                        }
                    }
                }
            }
            
            // Se o placar estiver zerado, significa que ninguém chegou ao doce
            if (placar.isEmpty()) {
                System.out.println("ninguem");
            // Se tiver dois jogadores ou mais no placar, houve empate
            } else if (placar.size() >= 2) {
                System.out.println("empate");
            } else {
            // Senão, houve empate
                // Esse for vai pegar o nome do ganhador
                for (String player : placar.keySet()) {
                    switch (player) {
                        case "J":
                            nome = "joao";
                            break;
                        case "M":
                            nome = "maria";
                            break;
                        case "B":
                            nome = "bruxa";
                            break;
                    }
                }
                // E agora imprime o resultado que é o nome do jogador e sua pontuação
                System.out.println(String.format("%s %d", nome, placar.get(nome.substring(0, 1).toUpperCase())));
            }
        }
    }
}

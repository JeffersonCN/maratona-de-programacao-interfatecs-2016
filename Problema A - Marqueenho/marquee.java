
import java.util.*;

class marquee {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int marqueeLen, textoLen, tempoDecorrido;
        String texto, espacoEmBranco, textoFinal, aux;
        ArrayList<String> marquee;

        while (scan.hasNext()) {
            marqueeLen = scan.nextInt();
            textoLen = scan.nextInt();
            scan.nextLine(); // Consome o \n do fim da linha, para ler corretamente a linha do texto
            texto = scan.nextLine();
            tempoDecorrido = scan.nextInt();

            // Se o marquee for maior que o texto
            if (marqueeLen > textoLen) {
                // Gera uma String somente com espaços em branco para completar o texto no marquee
                espacoEmBranco = new String(new char[marqueeLen - 1]).replace("\0", " ");

                // Adiciona o espaço em branco ao final do texto
                texto += espacoEmBranco;
            }

            // Transforma o texto em um ArrayList de caracteres
            marquee = new ArrayList<>(Arrays.asList(texto.split("")));

            // O estado inicial do marquee é atingido no instante equivalento ao tamanho do texto + 1
            if (tempoDecorrido > marqueeLen) {
                tempoDecorrido = tempoDecorrido % (texto.length() + 1);
            }

            // Corre o "tempo" e faz as trocas de posição
            for (int i = 0; i < tempoDecorrido; i++) {
                // Pega o primeiro
                aux = marquee.get(0);
                // Adiciona ao final
                marquee.add(aux);
                // Remove do começo
                marquee.remove(0);
            }

            // Compõe a String de saída
            textoFinal = "";
            for (int i = 0; i < marqueeLen; i++) {
                textoFinal += marquee.get(i);
            }

            // Imprime a saída
            System.out.println(textoFinal);
        }
    }
}

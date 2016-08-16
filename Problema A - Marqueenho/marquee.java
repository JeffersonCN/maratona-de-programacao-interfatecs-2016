
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class marquee {
    public static String geraEspacos(int tam) {
        return new String(new char[tam]).replace("\0", " ");
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int tamMarquee, tamTexto, tempoDecorrido, tamPrimeiraPalavra;
        String texto, textoFinal, aux;
        ArrayList<String> marquee;
        String separadores = "[ ,.:;!?]";
        Pattern pattern = Pattern.compile(separadores);
        Matcher matcher;

        while (scan.hasNext()) {
            tamMarquee = scan.nextInt();
            tamTexto = scan.nextInt();
            scan.nextLine(); // Consome o \n do fim da linha, para ler corretamente a linha do texto
            texto = scan.nextLine();
            tempoDecorrido = scan.nextInt();
            
            matcher = pattern.matcher(texto); // Procura os separadores no texto

            if (matcher.find()) {
                // Caso encontre, pega o índice do primeiro match pra usar como tamanho da primeira palavra
                tamPrimeiraPalavra = matcher.start();
            } else {
                // Caso não encontre, não há separadores, então só há uma palavra
                tamPrimeiraPalavra = tamTexto;
            }
            
            // Se o texto for menor que o marquee
            if (tamTexto < tamMarquee) {
                // Completa o texto com espaços em branco até chegar ao tamanho do marquee
                texto += geraEspacos(tamMarquee - tamTexto);
            }
            
            // Se retirando a primeira palavra do texto, ele fica menor que o marquee
            if (texto.length() - tamPrimeiraPalavra < tamMarquee) {
                // Adiciona o espaço em branco ao final do texto equivalente ao tamanho da primeira palavra
                texto += geraEspacos(tamPrimeiraPalavra - 1);
            }

            // Transforma o texto em um ArrayList de caracteres
            marquee = new ArrayList<>(Arrays.asList(texto.split("")));

            // O estado inicial do marquee se repete em determinado tempo
            if (tempoDecorrido > tamMarquee) {
                tempoDecorrido = (tempoDecorrido % (texto.length())) + 1;
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
            for (int i = 0; i < tamMarquee; i++) {
                textoFinal += marquee.get(i);
            }

            // Imprime a saída
            System.out.println(textoFinal);
        }
    }
}

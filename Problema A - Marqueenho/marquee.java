
import java.util.*;

class marquee {

    public static String geraEspacos(int tam) {
        return new String(new char[tam]).replace("\0", " ");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int tamMarquee, tamTexto, tempoDecorrido, ultimoSeparador = 0;
        String texto;
        char aux;
        String marquee;
        String separadores = "[ ,.:;!?]";

        while (scan.hasNext()) {
            tamMarquee = scan.nextInt();
            tamTexto = scan.nextInt();
            scan.nextLine(); // Consome o \n do fim da linha, para ler corretamente a linha do texto
            texto = scan.nextLine();
            tempoDecorrido = scan.nextInt();

            // Corre o "tempo" e faz as trocas de posição
            for (int i = 0; i < tempoDecorrido; i++) {
                aux = texto.charAt(0);
                texto = texto.substring(1) + aux;
            }
            
            // Pega a posição do último separador
            if (texto.substring(texto.length() - 1).matches(separadores)) {
                ultimoSeparador = texto.length() - 1;
            } else {
                for (int i = texto.length() - 1; i >= 0; i--) {
                    if (texto.substring(i - 1, i).matches(separadores)) {
                        ultimoSeparador = i - 1;
                        break;
                    }
                }
            }
               
            // Se o primeiro ou o último dígito são separadores
            if (texto.substring(0, 1).matches(separadores) || texto.substring(texto.length() - 1).matches(separadores)) {
                // Se o marquee for menor ou igual ao texto
                if (tamMarquee <= tamTexto) {
                    // Marquee corta o texto com seu tamanho
                    marquee = texto.substring(0, tamMarquee);
                } else {
                    // Se o texto é menor, só pegar o texto inteiro
                    marquee = texto;
                }
            // Se o marquee for maior ou igual ao texto
            // Ou se o último separador está posicionado dentro do espaço do marquee (mas a palavra ainda não sumiu pela esquerda)
            } else if (tamMarquee >= tamTexto || ultimoSeparador < tamMarquee - 1) {
                // Pega somente até o último separador
                marquee = texto.substring(0, ultimoSeparador + 1);
            // Se não se encaixa em nenhum dos casos anteriores
            } else {
                // Pega o tamanho do marquee mesmo
                marquee = texto.substring(0, tamMarquee);
            }
            
            // Imprime a saída
            System.out.println(marquee);
        }
    }
}

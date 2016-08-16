import java.util.Scanner;

class escolha {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int qtde, contador, carro;
        while(scan.hasNext()){
            qtde = scan.nextInt();
            
            if (qtde < 0) break;
            
            contador = 0;
            carro = 0;

            if (qtde >= 31) {
                carro = 31;
            } else {
                while (contador != 31) {
                    if (carro == qtde) {
                        carro = 1;
                    } else {
                        carro++;
                    }
                    contador++;
                }
            }
            
            System.out.println(carro);
        }
    }
}

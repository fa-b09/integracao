import java.io.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        double valorNumerico = 0;
        boolean entradaValida = false;

        System.out.println("☕ [Java] Iniciando a ponte com o Sistema Financeiro...");

        // 1. Loop de Validação de Entrada
        while (!entradaValida) {
            try {
                System.out.print("💰 Digite o valor para cálculo (ex: 1500,50): ");
                // Lê como string e troca vírgula por ponto para o Java entender como double
                String entrada = teclado.nextLine().replace(",", ".");
                valorNumerico = Double.parseDouble(entrada);

                if (valorNumerico <= 0) {
                    System.out.println("⚠️  Erro: O valor deve ser maior que zero.");
                } else {
                    entradaValida = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Erro: Digite apenas números válidos.");
            }
        }

        // 2. Chamada ao COBOL
        try {
            System.out.println("⏳ [Java] Enviando R$ " + valorNumerico + " para o motor COBOL...");
            
            // Passamos o valor formatado para o COBOL
            ProcessBuilder pb = new ProcessBuilder("./cobol/calculo_bin", String.valueOf(valorNumerico));
            Process processo = pb.start();

            BufferedReader leitor = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            String linha;
            
            System.out.println("\n--- Resposta do Legado ---");
            while ((linha = leitor.readLine()) != null) {
                System.out.println("📜 " + linha);
            }

            int codigoSaida = processo.waitFor();
            if (codigoSaida == 0) {
                System.out.println("✅ [Java] Processamento concluído com sucesso.");
            } else {
                System.out.println("⚠️  [Java] O COBOL retornou um aviso. Código: " + codigoSaida);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("❌ [Erro Crítico]: Falha na comunicação com o COBOL: " + e.getMessage());
        } finally {
            teclado.close();
        }
    }
}
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Output {
    public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner (System.in);

        System.out.println("Nome: ");
        String nome = entrada.nextLine();

        FileOutputStream arq = new FileOutputStream("c:\\xampp\\teste.dat");
        DataOutputStream data = new DataOutputStream(arq);

        data.writeUTF(nome);
    }
}

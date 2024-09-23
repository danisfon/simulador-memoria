import java.io.DataInputStream;
import java.io.FileInputStream;


public class SimuladorMain {
    public static void main(String[] args) throws Exception {
        Simulador simuladoor = new Simulador();
        
       /*  FileInputStream arquivo = new FileInputStream("c:\\xampp\\teste.dat");
        
        
        FileInputStream arquivo = new FileInputStream("C:\\msys64\\home\\User\\arq-sim-assembler\\ps.bin"); 
        
        
        DataInputStream data = new DataInputStream(arquivo);

        String nome = data.readUTF();
        System.out.println(nome);*/

        simuladoor.load_binary ("C:\\msys64\\home\\User\\arq-sim-assembler\\ali.bin");
    }

}
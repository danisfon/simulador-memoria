import java.io.DataInputStream;
import java.io.FileInputStream;


public class SimuladorMain {
    public static void main(String[] args) throws Exception {
        Simulador simuladoor = new Simulador();
        
    String nomeArquivo = "C:\\msys64\\home\\User\\arq-sim-assembler\\greg.bin";   
    simuladoor.load_binary(nomeArquivo);
    
    
    for (int i = 0; i < Simulador.MEMORY_SIZE; i++) {
        short instrucaoCodificada = simuladoor.memory[i];

        Instrucoes instrucao = simuladoor.decodificar(instrucaoCodificada);

        if(!simuladoor.executar(instrucao)){
            break;
        }

        simuladoor.executar(instrucao);


    }

    for (int i = 0; i < simuladoor.registradores.length ; i++) {
        System.out.println("Registrador r" + i + ":" + simuladoor.registradores[i]);   
    }

    System.out.println("\n");

    for (int i = 0; i < 100 ; i++) {
        System.out.println("Memória " + i + " : " + (simuladoor.memory[i] & 0xFFFF));
        //0xFFFF para tirar as memórias negativas
    }
   
}
    }

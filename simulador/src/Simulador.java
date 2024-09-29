
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Simulador {

	private static int MEMORY_SIZE = 1024;	

	private short[] memory = new short[MEMORY_SIZE];
	private short[] registradores = new short[8];

	public static String nomeRegistradores(int registradores){
		String[] registradoresNome = {"r0", "r1", "r2", "r3", "r4", "r5", "r6", "r7"};
		return registradoresNome[registradores];
	}

	public short extract_bits (short value, int bstart, int blength){
		short mask = (short)((1 << blength) - 1);
		return (short)((value >> bstart) & mask);
	}

	public void memory_write (short addr, short value){
		memory[addr] = value;
	}

	public Instrucoes decodificar(short instrucaoCodificada){
		Instrucoes decodificada = new Instrucoes();
		decodificada.format = extract_bits(instrucaoCodificada, 15, 1);

		//instruções para o formato R:
		if(decodificada.format == 0){
			decodificada.opcode = extract_bits(instrucaoCodificada, 9, 6);
			decodificada.destino = extract_bits(instrucaoCodificada, 6, 3);
			decodificada.operando1 = extract_bits(instrucaoCodificada, 3, 3);
			decodificada.operando2 = extract_bits(instrucaoCodificada, 0, 3);
		}
		//instruções para o formato I:
		else if(decodificada.format == 1) {
			decodificada.opcode = extract_bits(instrucaoCodificada, 13, 2);
			decodificada.imediato = extract_bits(instrucaoCodificada, 0, 10);
		}
		return decodificada; 	
	}

	public void executar(Instrucoes instrucoes){
		if (instrucoes.format == 0){
			if(instrucoes.opcode == 0){ //add
				registradores[instrucoes.destino] = (short) (registradores[instrucoes.operando1] + registradores[instrucoes.operando2]);
			}
			else if(instrucoes.opcode == 1){ //sub
				registradores[instrucoes.destino] = (short) (registradores[instrucoes.operando1] - registradores[instrucoes.operando2]);
			}
			else if(instrucoes.opcode == 2){ //mul 
				registradores[instrucoes.destino] = (short) (registradores[instrucoes.operando1] * registradores[instrucoes.operando2]);
			}
			else if(instrucoes.opcode == 3){ //div
				registradores[instrucoes.destino] = (short) (registradores[instrucoes.operando1] / registradores[instrucoes.operando2]);
			}
			else if(instrucoes.opcode == 4){ //cmp_equal
				if((registradores[instrucoes.operando1] == registradores[instrucoes.operando2])){
					registradores[instrucoes.destino] = 1;
				}else{
					registradores[instrucoes.destino] = 0;
				}
				//registradores[instrucoes.destino] = (short) (registradores[instrucoes.operando1] == registradores[instrucoes.operando2]);
			}
			else if(instrucoes.opcode == 5){ //cmp_neq
				if((registradores[instrucoes.operando1] != registradores[instrucoes.operando2])){
					registradores[instrucoes.destino] = 1;
				}else{
					registradores[instrucoes.destino] = 0;
				}
				//registradores[instrucoes.destino] = (short) (registradores[instrucoes.operando1] + registradores[instrucoes.operando2]);
			}
			else if(instrucoes.opcode == 15){ //load
				short endereco = registradores[instrucoes.operando1];
				registradores[instrucoes.destino] = memory[endereco]; //armazena no registrador um valor da memória

				registradores[instrucoes.destino] = memory[registradores[instrucoes.operando1]];
			}
			else if(instrucoes.opcode == 7){ //div
				registradores[instrucoes.destino] = (short) (registradores[instrucoes.operando1] / registradores[instrucoes.operando2]);
			
			else if(instrucoes.opcode == 8){ //div
				registradores[instrucoes.destino] = (short) (registradores[instrucoes.operando1] / registradores[instrucoes.operando2]);
			}
			else if(instrucoes.opcode == 9){ //div
				registradores[instrucoes.destino] = (short) (registradores[instrucoes.operando1] / registradores[instrucoes.operando2]);
			}
		}
	}
		
	void load_binary (String binary_name)
	{
		try {
			FileInputStream fileInputStream = new FileInputStream(binary_name);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);

			long tamanhoArquivo = fileInputStream.getChannel().size();

			int numShorts = (int) (tamanhoArquivo / 2);

			for (int i = 0; i < numShorts; i++) {
				int low = dataInputStream.readByte() & 0x000000FF;
				int high = dataInputStream.readByte() & 0x000000FF;
				int value = (low | (high << 8)) & 0x0000FFFF;

				this.memory_write((short)i, (short)value);
			}

			dataInputStream.close();
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Estacionamento {

    private String[] placas;
    private int numVagas;
    private SimpleDateFormat dateFormat;
    private File historicoFile;
    private File placasFile;

    
    public Estacionamento(int numVagas)throws Exception{
        
    	if (numVagas <= 0) {
            throw new Exception("Número de Vagas tem que ser maior que 0.");
        }
    	
    	this.numVagas = numVagas;
        this.placas = new String[numVagas];
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.historicoFile = new File("historico.csv");
        this.placasFile = new File("placas.csv");
        
        try {
            if (!this.historicoFile.exists()) {
                this.historicoFile.createNewFile();
            }
            if (!this.placasFile.exists()) {
                this.placasFile.createNewFile();
            }
            this.lerDados();
        } catch (IOException e) {
            throw new Exception("Erro ao criar arquivo: " + e.getMessage());
        }
    }

    public void entrar(String placa, int vaga) throws Exception {
        if (vaga < 1 || vaga > this.numVagas) {
        	throw new Exception("Vaga Inválida");
        }
        if (this.placas[vaga-1] != null){
            throw new Exception("Vaga já ocupada!");
          
        }
        this.placas[vaga-1] = placa;
        this.gravarDados();
        try {
            FileWriter historicoWriter = new FileWriter(this.historicoFile, true);
            historicoWriter.append(placa + ";" + vaga + ";" + this.dateFormat.format(new Date()) + ";entrada\n");
            historicoWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar histórico: " + e.getMessage());
        }
    }

    public void sair(int vaga) throws Exception {
        if (vaga < 1 || vaga > this.numVagas) {
        	 throw new Exception("Vaga inválida!");
        }
        if (this.placas[vaga-1] == null) {
        	 throw new Exception("Vaga já desocupada!");
        	 
        }
        String placa = this.placas[vaga-1];
        this.placas[vaga-1] = null;
        this.gravarDados();
        try {
            FileWriter historicoWriter = new FileWriter(this.historicoFile, true);
            historicoWriter.append(placa + ";" + vaga + ";" + this.dateFormat.format(new Date()) + ";saída\n");
            historicoWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar histórico: " + e.getMessage());
        }
    }

    public int consultarPlaca(String placa) {
        for (int i = 0; i < this.numVagas; i++) {
            if (this.placas[i] != null && this.placas[i].equals(placa)) {
                return i+1;
            }
        }
         return -1;
    }

    public void transferir(int vaga1, int vaga2)throws Exception {
        if (vaga1 < 1 || vaga1 > this.numVagas || vaga2 < 1 || vaga2 > this.numVagas) {
        	 throw new Exception("Vaga inválida!");
        }
        
        if (this.placas[vaga2-1] != null) {
            throw new Exception("A vaga destino já está ocupada!");
        }
        
        if (this.placas[vaga1-1] == null) {
            throw new Exception("Não há carro estacionado na vaga de origem!");
        }
        String placa = this.placas[vaga1-1];
        this.placas[vaga1-1] = null;
        this.placas[vaga2-1] = placa;
        gravarDados();
}
    
    public void lerDados() {
        try {
            FileReader placasReader = new FileReader(this.placasFile);
            BufferedReader bufferedReader = new BufferedReader(placasReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] dados = line.split(";");
                int vaga = Integer.parseInt(dados[0]);
                String placa = dados[1];
                if (placa != null && !placa.isEmpty()) {
                    this.placas[vaga-1] = placa;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
    
    public void gravarDados() {
        try {
            FileWriter placasWriter = new FileWriter(this.placasFile);
            BufferedWriter bufferedWriter = new BufferedWriter(placasWriter);
            for (int i = 0; i < this.placas.length; i++) {
                if (this.placas[i] != null && !this.placas[i].isEmpty()) {
                    bufferedWriter.write((i+1) + ";" + this.placas[i]);
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar arquivo: " + e.getMessage());
        }
    }
    public String[] listarGeral() {
        String[] vagas = new String[numVagas];
        for (int i = 0; i < numVagas; i++) {
            if (placas[i] == null) {
                vagas[i] = "livre";
            } else {
                vagas[i] = placas[i];
            }
        }
        return vagas;
    }
    public ArrayList<Integer> listarLivres() {
        ArrayList<Integer> vagasLivres = new ArrayList<Integer>();
        for (int i = 0; i < numVagas; i++) {
            if (placas[i] == null) {
                vagasLivres.add(i+1);
            }
        }
        return vagasLivres;
    }
}
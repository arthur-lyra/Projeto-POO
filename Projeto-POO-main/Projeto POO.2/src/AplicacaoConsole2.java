/**
 * TSI - POO - Prof fausto Ayres
 * Teste da classe Estacionamento
 */
public class AplicacaoConsole2 {

	public static void main(String[] args) {
		Estacionamento estacionamento = null;
		
		try {
			estacionamento = new Estacionamento(10);	//10 vagas
			new Estacionamento(0);	//0 vagas
			System.out.println("*************0--->Nao lan�ou exce��o para: construtor"); 
		}catch (Exception e) {System.out.println("exce��o0--->"+e.getMessage());}

		System.out.println("\n-------TESTE EXCE��ES LAN�ADAS--------");
		try {
			estacionamento.entrar("AAA1111",1);
			estacionamento.entrar("XXX1111",1);
			System.out.println("*************1--->Nao lan�ou exce��o para: entrar - 1"); 
		}catch (Exception e) {System.out.println("exce��o1--->"+e.getMessage());}

		try {
			estacionamento.sair(2);
			System.out.println("*************2--->Nao lan�ou exce��o para: sair - 2"); 
		}catch (Exception e) {System.out.println("exce��o2--->"+e.getMessage());}

		
		
		int vaga = estacionamento.consultarPlaca("XXX0000");
		System.out.println("placa XXX0000 nao encontrada, vaga= "+vaga); 

		
		try {
			estacionamento.transferir(1,1);
			System.out.println("*************3--->Nao lan�ou exce��o para: transferir - 1"); 
		}
		catch (Exception e) {System.out.println("exce��o3--->"+e.getMessage());}

		try {
			estacionamento.transferir(2,1);
			System.out.println("*************4--->Nao lan�ou exce��o para: transferir - 2"); 
		}
		catch (Exception e) {System.out.println("exce��o4--->"+e.getMessage());}

		try {
			estacionamento.transferir(3,2);
			System.out.println("*************5--->Nao lan�ou exce��o para: transferir - 3 "); 
		}
		catch (Exception e) {System.out.println("exce��o5--->"+e.getMessage());}

		System.out.println("\n------------------------");
		System.out.println("listagem geral");
		System.out.println("------------------------");
		for(String s : estacionamento.listarGeral()) {
			System.out.println(s);
		}

	}

}

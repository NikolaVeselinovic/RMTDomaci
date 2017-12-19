package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.LinkedList;




public class Server {
	static LinkedList<Fajl> listaFajlova=new LinkedList<Fajl>();
	static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	static String generisi() {
		int count=10;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}


	static String sacuvajTekst(String tekst) {
		String id;
		while(true) {
			id=generisi();
			int brojac=0;
			for(int i=0;i<listaFajlova.size();i++) {
				if(!(listaFajlova.get(i).getId().equals(id))){
					brojac++;
				}
			}
			if(brojac==listaFajlova.size()) {
				break;
			}
		}
		Fajl f = new Fajl(id);
		f.setId(id);
		try {
			PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter(f.getTekst())));
			out.println(tekst);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		listaFajlova.add(f);
		return id;
	}  

	static String vracanjeFajla(String id)  {
		for(int i=0;i<listaFajlova.size();i++) {
			if(listaFajlova.get(i).getId().equals(id)) {
				try {
					BufferedReader in = new BufferedReader(new FileReader(listaFajlova.get(i).getTekst()));
					String s=in.readLine();
					in.close();
					return s;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		}
		
		return "Netacan id";
	}
	public static void main(String[] args) throws Exception {
		int portNumber=12000;

		LinkedList<ServerClientThread> listaKlijenata = new LinkedList<ServerClientThread>();
		
		System.out.println(vracanjeFajla(sacuvajTekst("lelelelle")));

	}

}

package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerClientThread extends Thread{
	Socket soketZaKomunikaciju=null;
	BufferedReader citajOdKlijenta=null;
	PrintStream pisiKlijentu=null;
	public String username=null;

	public ServerClientThread(Socket soketZaKomunikaciju) {
		this.soketZaKomunikaciju=soketZaKomunikaciju;
	}
	public void run() {
		try {
			citajOdKlijenta=new BufferedReader(new InputStreamReader(soketZaKomunikaciju.getInputStream()));
			pisiKlijentu=new PrintStream(soketZaKomunikaciju.getOutputStream());
			pisiKlijentu.println("Unesite ime korisnika (/quit za izlaz):");
			username=citajOdKlijenta.readLine();
			if(username.contains("/quit")) {
				soketZaKomunikaciju.close();
			}
			else {


				while(true) {
					pisiKlijentu.println("Izaberite opciju (unesite broj):");
					pisiKlijentu.println("1 uploadovanje");
					pisiKlijentu.println("2 preuzimanje");
					pisiKlijentu.println("0 izlaz");

					int izbor=Integer.parseInt(citajOdKlijenta.readLine());

					if(izbor==1) {
						pisiKlijentu.println("Unesite tekst fajla:");
						String tekst=citajOdKlijenta.readLine();
						String s= Server.sacuvajTekst(tekst);
						pisiKlijentu.println("Id vaseg fajla je: "+s);
						break;
					}
					else {
						if(izbor==2) {
							pisiKlijentu.println("Unesite id fajla: ");
							String s=citajOdKlijenta.readLine();
							String sadrzaj=Server.vracanjeFajla(s);
							pisiKlijentu.println("Sadrzaj vaseg fajla je: " + sadrzaj);
							break;
						}
						else {
							if(izbor==0) {
								soketZaKomunikaciju.close();
								break;
							}
							else {
								pisiKlijentu.println("Pogresan unos");}
						}

					}

				}}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}

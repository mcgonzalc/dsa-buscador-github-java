package cat.mcgonzalc.buscadorgithubjava;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        Buscador buscador = new Buscador();
        Scanner sc = new Scanner(System.in);
        System.out.print("Introdueix el nom d'usuari: ");
        String nomUsuari = sc.nextLine().toLowerCase();
        //Busquem primer l'usuari per saber si existeix
        buscador.start(0, nomUsuari);
    }
}
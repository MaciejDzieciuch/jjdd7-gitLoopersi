package com.infoshareacademy.jjdd7.menu.menuprint;

import com.infoshareacademy.jjdd7.menu.Menu;

public class PrintHeader implements Menu {

    @Override
    public void doAction() {
        System.out.println("\n1. Lista pracowników");
        System.out.println("2. Urlop");
        System.out.println("3. Wyszukaj");
        System.out.println("4. Konfiguracja");
    }
}

package com.infoshareacademy.gitLoopersi.menu.menuaction;

import com.infoshareacademy.gitLoopersi.menu.ConsoleCleaner;
import com.infoshareacademy.gitLoopersi.menu.MenuTree;
import com.infoshareacademy.gitLoopersi.menu.menuprint.AlertMessagePrinter;

public class MenuAction {

  private MenuNavigator menuNavigator = new MenuNavigator();
  private MenuTree menuTree = new MenuTree();
  private AlertMessagePrinter alertMessagePrinter = new AlertMessagePrinter();

  public void doMenuAction() {
    while (true) {
      if (menuTree.buildMenuTree().containsKey(menuNavigator.getPage())) {

        if (menuNavigator.getExit()) {
          break;
        }

        menuTree.buildMenuTree().get(menuNavigator.getPage()).doAction();
        menuNavigator.changeMenuPage();

        ConsoleCleaner.cleanConsole();

      } else {

        if (menuNavigator.getExit()) {
          break;
        }
        while (!menuTree.buildMenuTree().containsKey(menuNavigator.getPage())) {
          menuNavigator.returnToPreviousPage();
        }

        menuTree.buildMenuTree().get(menuNavigator.getPage()).doAction();
        alertMessagePrinter.doAction();
        menuNavigator.changeMenuPage();

        ConsoleCleaner.cleanConsole();
      }
    }
  }
}
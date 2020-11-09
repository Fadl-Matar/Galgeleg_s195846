package com.example.galgeleg.GalgeLogik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Galgelogik {
  /** AHT afprøvning er muligeOrd synlig på pakkeniveau */
  private ArrayList<String> muligeOrd = new ArrayList<String>();
  private String ordet;
  private ArrayList<String> brugteBogstaver = new ArrayList<String>();
  private String synligtOrd;
  private int antalForkerteBogstaver;
  private boolean sidsteBogstavVarKorrekt;
  private boolean spilletErVundet;
  private boolean spilletErTabt;
  private static Galgelogik instance = new Galgelogik();

  private Galgelogik() {
    muligeOrd.add("bil");
    startNytSpil();
  }

  public static Galgelogik getInstance(){
    return instance;
  }


  public ArrayList<String> getBrugteBogstaver() {
    return brugteBogstaver;
  }

  public String getSynligtOrd() {
    return synligtOrd;
  }

  public String getOrdet() {
    return ordet;
  }

  public int getAntalForkerteBogstaver() {
    return antalForkerteBogstaver;
  }

  public boolean erSidsteBogstavKorrekt() {
    return sidsteBogstavVarKorrekt;
  }

  public boolean erSpilletVundet() {
    return spilletErVundet;
  }

  public boolean erSpilletTabt() {
    return spilletErTabt;
  }

  public boolean erSpilletSlut() {
    return spilletErTabt || spilletErVundet;
  }


  public void startNytSpil() {
    brugteBogstaver.clear();
    antalForkerteBogstaver = 0;
    spilletErVundet = false;
    spilletErTabt = false;
    if (muligeOrd.isEmpty()) throw new IllegalStateException("Listen over mulige ord er tom!");
    ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
    System.out.println("Nyt spil - det skjulte ord er: "+ordet);
    opdaterSynligtOrd();
  }


  private void opdaterSynligtOrd() {
    synligtOrd = "";
    spilletErVundet = true;
    for (int n = 0; n < ordet.length(); n++) {
      String bogstav = ordet.substring(n, n + 1);
      if (brugteBogstaver.contains(bogstav)) {
        synligtOrd = synligtOrd + bogstav;
      } else {
        synligtOrd = synligtOrd + "*";
        spilletErVundet = false;
      }
    }
  }

  public void gætBogstav(String bogstav) {
    if (bogstav.length() != 1) return;
    System.out.println("Der gættes på bogstavet: " + bogstav);
    if (brugteBogstaver.contains(bogstav)) return;
    if (spilletErVundet || spilletErTabt) return;

    brugteBogstaver.add(bogstav);

    if (ordet.contains(bogstav)) {
      sidsteBogstavVarKorrekt = true;
      System.out.println("Bogstavet var korrekt: " + bogstav);
    } else {
      // Vi gættede på et bogstav der ikke var i ordet.
      sidsteBogstavVarKorrekt = false;
      System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
      antalForkerteBogstaver = antalForkerteBogstaver + 1;
      if (antalForkerteBogstaver > 6) {
        spilletErTabt = true;
      }
    }
    opdaterSynligtOrd();
  }

  public void logStatus() {
    System.out.println("---------- ");
    System.out.println("- ordet (skult) = " + ordet);
    System.out.println("- synligtOrd = " + synligtOrd);
    System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
    System.out.println("- brugeBogstaver = " + brugteBogstaver);
    if (spilletErTabt) System.out.println("- SPILLET ER TABT");
    if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
    System.out.println("---------- ");
  }

  public Word getKeyWord(String keyWord){
    if (keyWord == null){
      return null;
    }
    if (keyWord.equalsIgnoreCase("dr")){
      return new hentFraDr();
    } else if (keyWord.equalsIgnoreCase("regneark")){
      return new hentFraArk();
    }
    return null;
  }


  /**
   * Hent ord fra DRs forside (https://dr.dk)
   */
  public void hentOrdFraDr() throws Exception {
    Word wordDr = getKeyWord("dr");
    wordDr.getWord(muligeOrd);
    startNytSpil();
  }


  /**
   * Hent ord og sværhedsgrad fra et online regneark. Du kan redigere i regnearket, på adressen
   * https://docs.google.com/spreadsheets/d/1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M/edit?usp=sharing
   * @param/
   * sværhedsgrader en streng med de tilladte sværhedsgrader - f.eks "3" for at medtage kun svære ord, eller "12" for alle nemme og halvsvære ord
   * @throws Exception
   */

  public void hentOrdFraRegneark(String sværhedsgrader) throws Exception {
    Word wordRegneark = getKeyWord("regneark");
    wordRegneark.getWord(muligeOrd);
    startNytSpil();
  }

  public static void main(String[] args) throws Exception {
    new Galgelogik().hentOrdFraDr();
  }
}

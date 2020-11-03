package com.example.galgeleg.GalgeLogik;

public class wordFac {
    public Word getOrd(String s){
        if (s == null){
            return null;
        }
        if (s.equalsIgnoreCase("dr")){
            return new hentFraDr();
        } else if (s.equalsIgnoreCase("regneark")){
            return new hentFraArk();
        }
        return null;
    }
}

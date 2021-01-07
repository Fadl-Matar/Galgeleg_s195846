package com.example.galgeleg.GalgeLogik;

import com.example.galgeleg.GalgeLogik.Galgelogik;

public class scoreCalculator {
    private int score;
    private Galgelogik galgelogik = Galgelogik.getInstance();

    public scoreCalculator(){
        this.score = 0;
    }

    public int calculate(){
        if (galgelogik.getOrdet().length() <= 3 && galgelogik.erSidsteBogstavKorrekt()){
            score += 3;
        }
        else if (galgelogik.getOrdet().length() > 3 && galgelogik.erSidsteBogstavKorrekt()) {
            score += 5;
        }
        return score;
    }


}

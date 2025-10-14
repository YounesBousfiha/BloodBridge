package com.jartiste.bloodbridge.domain.enums;

public enum Contraindication {
    HIV("Infection par le VIH / Sida"),
    HEPATITE_B("Hépatite B"),
    HEPATITE_C("Hépatite C"),
    SYPHILIS("Syphilis"),
    CANCER("Antécédent de cancer"),
    CARDIAC_DISEASE("Maladie cardiaque grave"),
    DIABETE_INSULINE("Diabète sous insuline"),
    EPILEPSIE("Épilepsie non contrôlée"),
    AUTO_IMMUNE_DISEASE("Maladie auto-immune sévère"),
    GREFFE("Antécédent de greffe d’organe"),
    DROGUE_INJECTABLE("Usage de drogues injectables"),
    TATOUAGE_RECENT("Tatouage ou piercing récent"),
    INFECTION_RECENTE("Infection ou fièvre récente"),
    CHIRURGIE_RECENTE("Chirurgie récente"),
    VOYAGE_PALUDISME("Voyage en zone à risque de paludisme"),
    GROSSESSE_RECENTE("Grossesse ou accouchement récent");


    private String description;

    Contraindication(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

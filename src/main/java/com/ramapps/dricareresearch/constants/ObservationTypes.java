package com.ramapps.dricareresearch.constants;

public enum ObservationTypes {
    HEART_RATE("heart-rate"),
    SKIN_TEMP("skin-temperature"),
    RESP_RATE("respiratory-rate"),
    OTHER("other");

    private final String type;

    ObservationTypes(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public static ObservationTypes toObservationType(String value) {
        if (value.equals(ObservationTypes.HEART_RATE.toString())) {
            return ObservationTypes.HEART_RATE;
        } else if (value.equals(ObservationTypes.SKIN_TEMP.toString())) {
            return ObservationTypes.HEART_RATE;
        } else if (value.equals(ObservationTypes.RESP_RATE.toString())) {
            return ObservationTypes.RESP_RATE;
        } else {
            return ObservationTypes.OTHER;
        }
    }

}

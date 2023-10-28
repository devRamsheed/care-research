package com.ramapps.dricareresearch.constants;

public enum Units {
    BEATS_MINUTE("beats/minute"),
    BREATHS_MINUTE("breaths/minute"),
    DEGREE("degrees Celsius"),
    OTHER("other");

    private final String units;

    Units(String unit) {
        this.units = unit;
    }

    @Override
    public String toString() {
        return this.units;
    }

    public static Units toUnit(String value) {
        if (value.equals(BEATS_MINUTE.toString())) {
            return BEATS_MINUTE;
        } else if (value.equals(BREATHS_MINUTE.toString())) {
            return BREATHS_MINUTE;
        } else if (value.equals(DEGREE.toString())) {
            return DEGREE;
        } else {
            return OTHER;
        }
    }
}

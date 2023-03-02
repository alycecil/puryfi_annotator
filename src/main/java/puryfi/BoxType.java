package puryfi;

public enum BoxType {
    BELLY_EXPOSED,
    BELLY_COVERED,
    BUTTOCKS_EXPOSED,
    BUTTOCKS_COVERED,
    FEMALE_BREAST_EXPOSED,
    FEMALE_BREAST_COVERED,
    FEMALE_GENITALIA_EXPOSED,
    FEMALE_GENITALIA_COVERED,
    MALE_GENITALIA_COVERED,
    MALE_GENITALIA_EXPOSED,
    MALE_BREAST_EXPOSED,
    MALE_BREAST_COVERED,
    FACE_FEMALE,
    FACE_MALE,
    FEET_COVERED,
    FEET_EXPOSED,
    ARMPITS_COVERED,
    ARMPITS_EXPOSED,
    ANUS_COVERED,
    ANUS_EXPOSED;

    public static BoxType getType(int label) {
        return BoxType.values().length > label ? BoxType.values()[label] : BoxType.values()[0];
    }
}
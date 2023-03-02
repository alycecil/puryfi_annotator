//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package puryfi;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

@SuppressWarnings("unused")
public class NSFW_BoundingBox {
    String name;
    Type type;
    Rectangle bounding_box = new Rectangle();
    Double confidence;
    BufferedImage sticker = null;
    boolean censor = true;
    DecimalFormat f = new DecimalFormat("#0.00");

    public NSFW_BoundingBox(String name, Double confidence, int[] box) {
        this.name = this.normalizeName(name);
        this.type = this.toType(name);
        this.confidence = confidence;
        this.bounding_box.x = box[0];
        this.bounding_box.y = box[1];
        this.bounding_box.width = box[2] - box[0];
        this.bounding_box.height = box[3] - box[1];
    }

    private String normalizeName(String name) {
        byte var3 = -1;
        switch (name.hashCode()) {
            case -2103929759:
                if (name.equals("FEMALE_BREAST_EXPOSED")) {
                    var3 = 8;
                }
                break;
            case -1574524032:
                if (name.equals("MALE_BREAST_EXPOSED")) {
                    var3 = 4;
                }
                break;
            case -1482929298:
                if (name.equals("FACE_FEMALE")) {
                    var3 = 14;
                }
                break;
            case -1426861851:
                if (name.equals("FEET_COVERED")) {
                    var3 = 16;
                }
                break;
            case -1041281136:
                if (name.equals("BUTTOCKS_EXPOSED")) {
                    var3 = 2;
                }
                break;
            case -962396435:
                if (name.equals("BELLY_COVERED")) {
                    var3 = 1;
                }
                break;
            case -853527316:
                if (name.equals("FEMALE_GENITALIA_EXPOSED")) {
                    var3 = 11;
                }
                break;
            case -447425171:
                if (name.equals("MALE_GENITALIA_EXPOSED")) {
                    var3 = 7;
                }
                break;
            case -374302644:
                if (name.equals("ANUS_EXPOSED")) {
                    var3 = 12;
                }
                break;
            case -290863189:
                if (name.equals("ARMPITS_COVERED")) {
                    var3 = 18;
                }
                break;
            case 163610071:
                if (name.equals("FEMALE_BREAST_COVERED")) {
                    var3 = 9;
                }
                break;
            case 600565615:
                if (name.equals("FEET_EXPOSED")) {
                    var3 = 17;
                }
                break;
            case 693015798:
                if (name.equals("MALE_BREAST_COVERED")) {
                    var3 = 5;
                }
                break;
            case 1044470447:
                if (name.equals("FACE_MALE")) {
                    var3 = 15;
                }
                break;
            case 1065031031:
                if (name.equals("BELLY_EXPOSED")) {
                    var3 = 0;
                }
                break;
            case 1226258694:
                if (name.equals("BUTTOCKS_COVERED")) {
                    var3 = 3;
                }
                break;
            case 1414012514:
                if (name.equals("FEMALE_GENITALIA_COVERED")) {
                    var3 = 10;
                }
                break;
            case 1736564277:
                if (name.equals("ARMPITS_EXPOSED")) {
                    var3 = 19;
                }
                break;
            case 1820114659:
                if (name.equals("MALE_GENITALIA_COVERED")) {
                    var3 = 6;
                }
                break;
            case 1893237186:
                if (name.equals("ANUS_COVERED")) {
                    var3 = 13;
                }
        }

        switch (var3) {
            case 0:
                return "Stomach / Belly - Exposed";
            case 1:
                return "Stomach / Belly - Covered";
            case 2:
                return "Buttocks - Exposed";
            case 3:
                return "Buttocks - Covered";
            case 4:
                return "Male Breast - Exposed";
            case 5:
                return "Male Breast - Covered";
            case 6:
                return "Male Genitalia - Covered";
            case 7:
                return "Male Genitalia - Exposed";
            case 8:
                return "Female Breast - Exposed";
            case 9:
                return "Female Breast - Covered";
            case 10:
                return "Female Genitalia - Covered";
            case 11:
                return "Female Genitalia - Exposed";
            case 12:
                return "Anus - Exposed";
            case 13:
                return "Anus - Covered";
            case 14:
                return "Face - Female";
            case 15:
                return "Face - Male";
            case 16:
                return "Feet - Covered";
            case 17:
                return "Feet - Exposed";
            case 18:
                return "Armpits - Covered";
            case 19:
                return "Armpits - Exposed";
            default:
                return name;
        }
    }

    private Type toType(String name) {
        Type type = null;
        byte var4 = -1;
        switch (name.hashCode()) {
            case -2103929759:
                if (name.equals("FEMALE_BREAST_EXPOSED")) {
                    var4 = 8;
                }
                break;
            case -2078063293:
                if (name.equals("Face - Male")) {
                    var4 = 35;
                }
                break;
            case -2068824744:
                if (name.equals("Male Genitalia - Exposed")) {
                    var4 = 27;
                }
                break;
            case -1899816527:
                if (name.equals("Feet - Covered")) {
                    var4 = 36;
                }
                break;
            case -1574524032:
                if (name.equals("MALE_BREAST_EXPOSED")) {
                    var4 = 4;
                }
                break;
            case -1482929298:
                if (name.equals("FACE_FEMALE")) {
                    var4 = 14;
                }
                break;
            case -1442154556:
                if (name.equals("Female Breast - Covered")) {
                    var4 = 29;
                }
                break;
            case -1426861851:
                if (name.equals("FEET_COVERED")) {
                    var4 = 16;
                }
                break;
            case -1386400489:
                if (name.equals("Female Genitalia - Exposed")) {
                    var4 = 31;
                }
                break;
            case -1336796209:
                if (name.equals("Stomach / Belly - Covered")) {
                    var4 = 21;
                }
                break;
            case -1164361949:
                if (name.equals("Male Breast - Covered")) {
                    var4 = 25;
                }
                break;
            case -1041281136:
                if (name.equals("BUTTOCKS_EXPOSED")) {
                    var4 = 2;
                }
                break;
            case -962396435:
                if (name.equals("BELLY_COVERED")) {
                    var4 = 1;
                }
                break;
            case -853527316:
                if (name.equals("FEMALE_GENITALIA_EXPOSED")) {
                    var4 = 11;
                }
                break;
            case -552549023:
                if (name.equals("Armpits - Exposed")) {
                    var4 = 39;
                }
                break;
            case -447425171:
                if (name.equals("MALE_GENITALIA_EXPOSED")) {
                    var4 = 7;
                }
                break;
            case -417915432:
                if (name.equals("Anus - Exposed")) {
                    var4 = 32;
                }
                break;
            case -374302644:
                if (name.equals("ANUS_EXPOSED")) {
                    var4 = 12;
                }
                break;
            case -290863189:
                if (name.equals("ARMPITS_COVERED")) {
                    var4 = 18;
                }
                break;
            case -263522926:
                if (name.equals("Buttocks - Covered")) {
                    var4 = 23;
                }
                break;
            case -55712510:
                if (name.equals("Face - Female")) {
                    var4 = 34;
                }
                break;
            case 127610939:
                if (name.equals("Feet - Exposed")) {
                    var4 = 37;
                }
                break;
            case 163610071:
                if (name.equals("FEMALE_BREAST_COVERED")) {
                    var4 = 9;
                }
                break;
            case 198715086:
                if (name.equals("Male Genitalia - Covered")) {
                    var4 = 26;
                }
                break;
            case 585272910:
                if (name.equals("Female Breast - Exposed")) {
                    var4 = 28;
                }
                break;
            case 600565615:
                if (name.equals("FEET_EXPOSED")) {
                    var4 = 17;
                }
                break;
            case 690631257:
                if (name.equals("Stomach / Belly - Exposed")) {
                    var4 = 20;
                }
                break;
            case 693015798:
                if (name.equals("MALE_BREAST_COVERED")) {
                    var4 = 5;
                }
                break;
            case 863065517:
                if (name.equals("Male Breast - Exposed")) {
                    var4 = 24;
                }
                break;
            case 881139341:
                if (name.equals("Female Genitalia - Covered")) {
                    var4 = 30;
                }
                break;
            case 1044470447:
                if (name.equals("FACE_MALE")) {
                    var4 = 15;
                }
                break;
            case 1065031031:
                if (name.equals("BELLY_EXPOSED")) {
                    var4 = 0;
                }
                break;
            case 1226258694:
                if (name.equals("BUTTOCKS_COVERED")) {
                    var4 = 3;
                }
                break;
            case 1414012514:
                if (name.equals("FEMALE_GENITALIA_COVERED")) {
                    var4 = 10;
                }
                break;
            case 1714990807:
                if (name.equals("Armpits - Covered")) {
                    var4 = 38;
                }
                break;
            case 1736564277:
                if (name.equals("ARMPITS_EXPOSED")) {
                    var4 = 19;
                }
                break;
            case 1763904540:
                if (name.equals("Buttocks - Exposed")) {
                    var4 = 22;
                }
                break;
            case 1820114659:
                if (name.equals("MALE_GENITALIA_COVERED")) {
                    var4 = 6;
                }
                break;
            case 1849624398:
                if (name.equals("Anus - Covered")) {
                    var4 = 33;
                }
                break;
            case 1893237186:
                if (name.equals("ANUS_COVERED")) {
                    var4 = 13;
                }
        }

        switch (var4) {
            case 0:
            case 20:
                type = Type.BELLY_EXPOSED;
                break;
            case 1:
            case 21:
                type = Type.BELLY_COVERED;
                break;
            case 2:
            case 22:
                type = Type.BUTTOCKS_EXPOSED;
                break;
            case 3:
            case 23:
                type = Type.BUTTOCKS_COVERED;
                break;
            case 4:
            case 24:
                type = Type.MALE_BREAST_EXPOSED;
                break;
            case 5:
            case 25:
                type = Type.MALE_BREAST_COVERED;
                break;
            case 6:
            case 26:
                type = Type.MALE_GENITALIA_COVERED;
                break;
            case 7:
            case 27:
                type = Type.MALE_GENITALIA_EXPOSED;
                break;
            case 8:
            case 28:
                type = Type.FEMALE_BREAST_EXPOSED;
                break;
            case 9:
            case 29:
                type = Type.FEMALE_BREAST_COVERED;
                break;
            case 10:
            case 30:
                type = Type.FEMALE_GENITALIA_COVERED;
                break;
            case 11:
            case 31:
                type = Type.FEMALE_GENITALIA_EXPOSED;
                break;
            case 12:
            case 32:
                type = Type.ANUS_EXPOSED;
                break;
            case 13:
            case 33:
                type = Type.ANUS_COVERED;
                break;
            case 14:
            case 34:
                type = Type.FACE_FEMALE;
                break;
            case 15:
            case 35:
                type = Type.FACE_MALE;
                break;
            case 16:
            case 36:
                type = Type.FEET_COVERED;
                break;
            case 17:
            case 37:
                type = Type.FEET_EXPOSED;
                break;
            case 18:
            case 38:
                type = Type.ARMPITS_COVERED;
                break;
            case 19:
            case 39:
                type = Type.ARMPITS_EXPOSED;
                break;
            default:
                System.err.println("NOT FOUND:" + name);
        }

        return type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        this.type = this.toType(name);
        this.confidence = 1.0D;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
        this.confidence = 1.0D;
    }

    public Rectangle getBounding_box() {
        return this.bounding_box;
    }

    public void setBounding_box(Rectangle bounding_box) {
        this.bounding_box = bounding_box;
    }

    public Double getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public boolean isCensored() {
        if ((this.type != Type.FACE_FEMALE || !NSWFAPI.ff_CheckBox.isSelected()) && (this.type != Type.FACE_MALE || !NSWFAPI.fm_CheckBox.isSelected())) {
            if (this.type != Type.FACE_FEMALE && this.type != Type.FACE_MALE) {
                return this.censor && this.checkOptions();
            } else {
                return false;
            }
        } else {
            return this.censor;
        }
    }

    public void setCensored(boolean print) {
        this.censor = print;
    }

    public boolean checkOptions() {
        if (this.type == null) {
            return true;
        } else if (this.type.equals(Type.BELLY_EXPOSED) && !NSWFAPI.belly_e_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.BELLY_COVERED) && !NSWFAPI.belly_c_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.BUTTOCKS_EXPOSED) && !NSWFAPI.buttocks_e_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.BUTTOCKS_COVERED) && !NSWFAPI.buttocks_c_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.FEMALE_BREAST_EXPOSED) && !NSWFAPI.fbreats_e_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.FEMALE_BREAST_COVERED) && !NSWFAPI.fbreats_c_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.FEMALE_GENITALIA_EXPOSED) && !NSWFAPI.fgen_e_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.FEMALE_GENITALIA_COVERED) && !NSWFAPI.fgen_c_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.MALE_GENITALIA_COVERED) && !NSWFAPI.mgen_c_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.MALE_GENITALIA_EXPOSED) && !NSWFAPI.mgen_e_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.MALE_BREAST_EXPOSED) && !NSWFAPI.mbreast_e_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.MALE_BREAST_COVERED) && !NSWFAPI.mbreast_c_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.FEET_COVERED) && !NSWFAPI.feet_c_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.FEET_EXPOSED) && !NSWFAPI.feet_e_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.ARMPITS_COVERED) && !NSWFAPI.armpits_c_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.ARMPITS_EXPOSED) && !NSWFAPI.armpits_e_button.isSelected()) {
            return false;
        } else if (this.type.equals(Type.ANUS_COVERED) && !NSWFAPI.anus_c_button.isSelected()) {
            return false;
        } else {
            return !this.type.equals(Type.ANUS_EXPOSED) || NSWFAPI.anus_e_button.isSelected();
        }
    }

    public String getHeadline() {
        if (NSWFAPI.labelModeComboBox.getSelectedIndex() == 1) {
            String[] split = this.getName().split("\\s+");
            StringBuilder r = new StringBuilder();

            for (String s : split) {
                if (!s.isEmpty()) {
                    r.append(s.charAt(0));
                    if (s.charAt(0) == 'A') {
                        r.append(s.charAt(1));
                    }
                }
            }

            Double confidence1 = this.getConfidence();
            return r + this.f.format(confidence1);
        } else {
            return NSWFAPI.labelModeComboBox.getSelectedIndex() == 2 ? "" : this.getName() + " " + this.getConfidence();
        }
    }

    public BufferedImage getSticker() {
        return this.sticker;
    }

    public void setSticker(BufferedImage sticker) {
        this.sticker = sticker;
    }
}

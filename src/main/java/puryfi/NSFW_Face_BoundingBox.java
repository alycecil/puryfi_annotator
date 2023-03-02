//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package puryfi;

public class NSFW_Face_BoundingBox extends NSFW_BoundingBox {
    String gender;
    String ethnicity;
    int age;

    public NSFW_Face_BoundingBox(String name, Double confidence, String gender, String ethnicity, int age, int[] box) {
        super(name, confidence, box);
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnicity() {
        return this.ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHeadline() {
        if (NSWFAPI.labelModeComboBox.getSelectedIndex() == 1) {
            String[] splited = this.getName().split("\\s+");
            String r = "";

            for(int i = 0; i < splited.length; ++i) {
                if (!splited[i].isEmpty()) {
                    r = r + splited[i].charAt(0);
                    if (splited[i].charAt(0) == 'A') {
                        r = r + splited[i].charAt(1);
                    }
                }
            }

            Double confidence1 = this.getConfidence();
            return r + this.f.format(confidence1);
        } else {
            return NSWFAPI.labelModeComboBox.getSelectedIndex() == 2 ? "" : this.getName() + "\nAge: " + this.age + " Race: " + this.ethnicity.toLowerCase();
        }
    }
}

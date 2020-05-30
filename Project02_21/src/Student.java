public class Student {
    public String name;
    public char gender;
    public Date birthdate;
    public Preference pref;
    public boolean matched;

    public Student(String name, char gender, Date birthdate, Preference pref){
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.pref = pref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Preference getPreference() {
        return pref;
    }

    public void setPreference(Preference pref) {
        this.pref = pref;
    }

    public boolean getMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public int compare(Student st){
        int compatabilityScore;
        if(gender != st.getGender()){
            return 0;
        }
        compatabilityScore = (40 - pref.compare(st.pref)) + (60-birthdate.compare(st.birthdate));

        if (compatabilityScore < 0){
            return 0;
        }
        else if (compatabilityScore > 100){
            return 100;
        }

        return compatabilityScore;
    }



}

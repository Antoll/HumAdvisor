package uqac.dim.humadvisor;


public class User {

    private String pseudo;
    private String mail;

    private float globalNote;

    private float confortable;
    private int confortable_nbr;

    private float sympa;
    private int sympa_nbr;

    private float intelligence;
    private int intelligence_nbr;

    private float beau;
    private int beau_nbr;

    private float sociable;
    private int sociable_nbr;

    public User(){}
    public User(String pseudo, String mail){
        this.pseudo = pseudo;
        this.mail = mail;

        //Critères
        this.globalNote = -1f;
        this.confortable = -1f;
        this.confortable_nbr = 0;
        this.sympa = -1f;
        this.sympa_nbr = 0;
        this.intelligence = -1f;
        this.intelligence_nbr = 0;
        this.beau = -1f;
        this.beau_nbr = 0;
        this.sociable = -1f;
        this.sociable_nbr = 0;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public float getGlobalNote() {
        return this.globalNote;
    }

    public void setGlobalNote(float globalNote) {
        this.globalNote = globalNote;
    }


    public float getConfortable(){
        return this.confortable;
    }

    public void setConfortable(float confortable){
        this.confortable = confortable;
    }

    public int getConfortable_nbr() {
        return this.confortable_nbr;
    }

    public void setConfortable_nbr(int confortable_nbr) {
        this.confortable_nbr = confortable_nbr;
    }

    public float getSympa() {
        return this.sympa;
    }

    public void setSympa(float sympa) {
        this.sympa = sympa;
    }

    public int getSympa_nbr() {
        return this.sympa_nbr;
    }

    public void setSympa_nbr(int sympa_nbr) {
        this.sympa_nbr = sympa_nbr;
    }

    public float getIntelligence() {
        return this.intelligence;
    }

    public void setIntelligence(float intelligence) {
        this.intelligence = intelligence;
    }

    public int getIntelligence_nbr() {
        return this.intelligence_nbr;
    }

    public void setIntelligence_nbr(int intelligence_nbr) {
        this.intelligence_nbr = intelligence_nbr;
    }

    public float getBeau() {
        return this.beau;
    }

    public void setBeau(float beau) {
        this.beau = beau;
    }

    public int getBeau_nbr() {
        return this.beau_nbr;
    }

    public void setBeau_nbr(int beau_nbr) {
        this.beau_nbr = beau_nbr;
    }

    public float getSociable() {
        return sociable;
    }

    public void setSociable(float sociable) {
        this.sociable = sociable;
    }

    public int getSociable_nbr() {
        return sociable_nbr;
    }

    public void setSociable_nbr(int sociable_nbr) {
        this.sociable_nbr = sociable_nbr;
    }

}

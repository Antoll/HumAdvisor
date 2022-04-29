package uqac.dim.humadvisor;


import java.util.List;

public class User {

    private String pseudo;
    private String mail;
    private String firebaseUID;

    private float globalNote;
    private float confortable;
    private float sympa;
    private float intelligence;
    private float beau;
    private float sociable;
    private int nbrOfVote;

    private List<String> usersNoted;


    public User(){}
    public User(String pseudo, String mail, String firebaseUID){
        this.pseudo = pseudo;
        this.mail = mail;
        this.firebaseUID = firebaseUID;
        this.nbrOfVote = 0;

        //Critères
        this.globalNote = 0f;
        this.confortable = 0f;
        this.sympa = 0f;
        this.intelligence = 0f;
        this.beau = 0f;
        this.sociable = 0f;
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

    public String getFirebaseUID() {
        return firebaseUID;
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

    public float getSympa() {
        return this.sympa;
    }

    public void setSympa(float sympa) {
        this.sympa = sympa;
    }

    public float getIntelligence() {
        return this.intelligence;
    }

    public void setIntelligence(float intelligence) {
        this.intelligence = intelligence;
    }

    public float getBeau() {
        return this.beau;
    }

    public void setBeau(float beau) {
        this.beau = beau;
    }

    public float getSociable() {
        return sociable;
    }

    public void setSociable(float sociable) {
        this.sociable = sociable;
    }

    public List<String> getUsersNoted() {
        return usersNoted;
    }

    public int getNbrOfVote() {
        return nbrOfVote;
    }

    public void setNbrOfVote(int nbrOfVote) {
        this.nbrOfVote = nbrOfVote;
    }
}

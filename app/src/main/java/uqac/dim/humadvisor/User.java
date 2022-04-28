package uqac.dim.humadvisor;


public class User {

    private String pseudo;
    private String mail;
    private int globalNote;
    private int getGlobalNote_nbr;
    private int confortable;
    private int confortable_nbr;

    public User(){}
    public User(String name, String pseudo){
        this.mail = mail;
        this.pseudo = pseudo;
        this.globalNote = -1;
        this.getGlobalNote_nbr = 0;
        this.confortable = -1;
        this.confortable_nbr = 0;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setName(String mail) {
        this.mail = mail;
    }

    public int getGlobalNote() {
        return globalNote;
    }

    public void setGlobalNote(int globalNote) {
        this.globalNote = globalNote;
    }

    public int getGetGlobalNote_nbr() {
        return getGlobalNote_nbr;
    }

    public void setGetGlobalNote_nbr(int getGlobalNote_nbr) {
        this.getGlobalNote_nbr = getGlobalNote_nbr;
    }

    public int getConfortable() {
        return confortable;
    }

    public void setConfortable(int confortable) {
        this.confortable = confortable;
    }

    public int getConfortable_nbr() {
        return confortable_nbr;
    }

    public void setConfortable_nbr(int confortable_nbr) {
        this.confortable_nbr = confortable_nbr;
    }
}

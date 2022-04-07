package uqac.dim.humadvisor;


public class UserInfo {

    private String name;
    private String pseudo;
    private int globalNote;
    private int getGlobalNote_nbr;
    private int confortable;
    private int confortable_nbr;

    public UserInfo(){}
    public UserInfo(String name, String pseudo){
        this.name = name;
        this.pseudo = pseudo;
        this.globalNote = -1;
        this.getGlobalNote_nbr = 0;
        this.confortable = -1;
        this.confortable_nbr = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

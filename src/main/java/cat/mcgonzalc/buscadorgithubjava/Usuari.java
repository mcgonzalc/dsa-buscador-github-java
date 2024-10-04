package cat.mcgonzalc.buscadorgithubjava;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//https://docs.github.com/en/rest/users/users?apiVersion=2022-11-28#get-a-user
public class Usuari {

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("repos_url")
    @Expose
    private String repos_url;

    Usuari(String login, String repos_url)
    {
        this.login = login;
        this.repos_url = repos_url;
    }

    public String getLogin() {
        return login;
    }

    public String getRepos_url(){
        return repos_url;
    }
}
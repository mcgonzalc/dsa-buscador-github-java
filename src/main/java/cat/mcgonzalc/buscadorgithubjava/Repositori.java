package cat.mcgonzalc.buscadorgithubjava;

import com.google.gson.annotations.SerializedName;

public class Repositori {

    @SerializedName("name")
    private String name;

    @SerializedName("stargazers_count")
    private int stargazers_count;

    Repositori(String name, int stargazers_count)
    {
        this.name = name;
        this.stargazers_count = stargazers_count;
    }

    public String getName() {
        return name;
    }

    public int getStargazers_count(){
        return stargazers_count;
    }
}
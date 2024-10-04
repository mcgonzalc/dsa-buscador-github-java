package cat.mcgonzalc.buscadorgithubjava;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubAPI {

    //Funció que asignem per realitzar una búsqueda d'usuari en l'API de GitHub
    @GET("{username}")
    Call<Usuari> buscarUsuari(@Path("username") String nomusuari);

    //Funció que asignem per buscar els repositoris d'un usuari de GitHub
    @GET("{username}/repos")
    Call<List<Usuari>> buscarRepositoris(@Path("username") String nomusuari);
}

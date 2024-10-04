package cat.mcgonzalc.buscadorgithubjava;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Buscador implements Callback<Usuari> {

    //Enllaç base pel programa
    static final String BASE_URL = "https://api.github.com/users/";
    private Gson gson = new GsonBuilder()
            .create();

    public void start(int opcioSeleccionada, String usuariBuscat) {

        //Creem una instància del desearilitzador de JSON per poder obtenir
        //l'informació que ens torna el sistema
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GitHubAPI githubAPI = retrofit.create(GitHubAPI.class);

        //Envíem la petició de búsqueda d'usuari a la API de GitHub
        Call<Usuari> peticio = githubAPI.buscarUsuari(usuariBuscat);

        //Posem la petició en espera
        peticio.enqueue(this);

    }

    @Override
    public void onResponse(Call<Usuari> peticio, Response<Usuari> response) {
        if(response.isSuccessful()) {
            Usuari a = response.body();
            assert a != null;
            System.out.println(a.getLogin());
            System.out.println(a.getRepos_url());
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Usuari> call, Throwable t) {
        t.printStackTrace();
    }
}

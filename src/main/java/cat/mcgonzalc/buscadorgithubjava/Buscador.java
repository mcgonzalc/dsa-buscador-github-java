package cat.mcgonzalc.buscadorgithubjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Buscador {

    int statusCode;
    //Enllaç base pel programa
    static final String BASE_URL = "https://api.github.com/users/";
    private Gson gson = new GsonBuilder()
            .create();

    public void buscadorUsuari(String usuariBuscat) {

        //Creem una instància del desearilitzador de JSON per poder obtenir
        //l'informació que ens torna el sistema
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        GitHubAPI githubAPI = retrofit.create(GitHubAPI.class);

        //Enviem la petició de búsqueda d'usuari a la API de GitHub
        Call<Usuari> peticio = githubAPI.buscarUsuari(usuariBuscat);


        //Posem la petició en espera
        peticio.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Usuari> peticio, Response<Usuari> response) {
                if (response.isSuccessful()) {
                    Usuari usuari = response.body();
                    assert usuari != null;
                    buscadorRepositoris(usuari.getLogin());
                    statusCode = response.code();
                } else {
                    System.out.println(response.errorBody());
                    statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<Usuari> peticio, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public void buscadorRepositoris(String usuariBuscat){

        //Creem una instància del desearilitzador de JSON per poder obtenir
        //l'informació que ens torna el sistema
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        GitHubAPI githubAPI = retrofit.create(GitHubAPI.class);

        //Enviem la petició de búsqueda d'usuari a la API de GitHub
        Call<List<Repositori>> peticio = githubAPI.buscarRepositoris(usuariBuscat);

        //Posem la petició en espera
        peticio.enqueue(new Callback<>() {

            @Override
            public void onResponse(Call<List<Repositori>> peticio, Response<List<Repositori>> resposta) {
                if (resposta.isSuccessful()) {

                    List<Repositori> repositoris = resposta.body();

                    for (int i = 0; i < repositoris.size(); i++)
                    {
                        System.out.println(repositoris.get(i).getName() + " - Estrelles: " + repositoris.get(i).getStargazers_count());
                    }
                    statusCode = resposta.code();
                } else {
                    System.out.println(resposta.errorBody());
                    statusCode = resposta.code();
                }
            }

            @Override
            public void onFailure(Call<List<Repositori>> peticio, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public int getStatusCode(){
        return this.statusCode;
    }
}

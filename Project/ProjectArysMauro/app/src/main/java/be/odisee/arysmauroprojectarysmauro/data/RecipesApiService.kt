package be.odisee.arysmauroprojectarysmauro.data
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Body
import retrofit2.http.POST

interface RecipesApiService {
    @GET("recepten")
    suspend fun getAllRecipes(
        @HeaderMap headers: Map<String, String>
    ): RecipeResponse


    @GET("gebruiker")
    suspend fun getGebruiker(
        @HeaderMap headers: Map<String, String>
    ): UserResponse


    @POST("gebruiker")
    suspend fun updateAlias(
        @HeaderMap headers: Map<String, String>,
        @Body aliasUpdate: AliasUpdate
    ): StatusMessageResponse

}
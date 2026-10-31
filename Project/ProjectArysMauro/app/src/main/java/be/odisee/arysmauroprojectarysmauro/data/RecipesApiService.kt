package be.odisee.arysmauroprojectarysmauro.data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

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

    @GET("gebruiker/{id}/recepten")
    suspend fun getReceptenVanGebruiker(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id: Int
    ): MijnReceptenResponse

    @POST("recepten")
    suspend fun addRecipe(
        @HeaderMap headers: Map<String, String>,
        @Body request: AddRecipeRequest
    ): AddRecipeResponse

    @POST("recepten/{id}/like")
    suspend fun likeRecipe(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id: Int
    ): LikeResponse

    @DELETE("recepten/{id}/like")
    suspend fun deleteLike(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id: Int
    ): LikeResponse

    @DELETE("recepten/{id}")
    suspend fun deleteRecipe(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id: Int
    ): Response<Unit>


}
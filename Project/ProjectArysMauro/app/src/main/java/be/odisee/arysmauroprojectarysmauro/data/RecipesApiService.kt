package be.odisee.arysmauroprojectarysmauro.data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
// Project Test - Code : SDFGBRTY
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
        @Body Gegevensupdate: AliasUpdate
    ): AliasUpdateResponse

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

    @GET("recepten/{id}/likes")
    suspend fun getLikes(
        @HeaderMap headers: Map<String, String>,
        @Path("id") id: Int
    ): LikeResponse2

    @GET("recepten/{id}/comments")
    suspend fun getComments(
        @HeaderMap headers: Map<String, String>,
        @Path("id") receptId: Int
    ): CommentResponse

    @POST("recepten/{id}/comment")
    suspend fun addComment(
        @HeaderMap headers: Map<String, String>,
        @Path("id") receptId: Int,
        @Body request: AddCommentRequest
    ): AddCommentResponse

    @DELETE("recepten/{id}/comment")
    suspend fun deleteComment(
        @HeaderMap headers: Map<String, String>,
        @Path("id") recipeId: Int
    ): LikeResponse

    @POST("recepten/{id}/comment/{id2}")
    suspend fun updateComment(
        @HeaderMap headers: Map<String, String>,
        @Path("id") recipeId: Int,
        @Path("id2") commentId: Int,
        @Body request: AddCommentRequest
    ): LikeResponse
}

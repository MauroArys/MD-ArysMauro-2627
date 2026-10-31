package be.odisee.arysmauroprojectarysmauro.data

object RecipesApi {
    val retrofitService: RecipesApiService by lazy {
        retrofit.create(RecipesApiService::class.java)
    }
}

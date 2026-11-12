package be.odisee.arysmauroprojectarysmauro.data

// Project Test - Code : SDFGBRTY
object RecipesApi {
    val retrofitService: RecipesApiService by lazy {
        retrofit.create(RecipesApiService::class.java)
    }
}

package com.android.tvshowsapp.di

import android.content.Context
import androidx.room.Room
import com.android.tvshowsapp.database.FavShowDatabase
import com.android.tvshowsapp.network.TVApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFavShowDatabase(
            @ApplicationContext context: Context
    ) = Room.databaseBuilder(
            context,
            FavShowDatabase::class.java,
            "favorite_db"
    ).build()

    @Singleton
    @Provides
    fun provideFavShowDao(db: FavShowDatabase) = db.favShowDao()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                    .baseUrl(TVApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
    @Singleton
    @Provides
    fun provideTVApi(retrofit: Retrofit): TVApi =
            retrofit.create(TVApi::class.java)
}
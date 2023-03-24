package com.guillem.sample_app_willy_wonka_chocolate_factory.fake

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity.OompaLoompaEntity
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.responsemodels.FavoriteData
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.responsemodels.OompaLoompaDetailedData


val fakeFavoriteEntity  = OompaLoompaEntity.FavoriteEntity(
    color = "fakeColor",
    food = "fakeFood",
    random_string = "fakeRandomString",
    song = "fakeSong"
)

val fakeOompaLoompaEntity = OompaLoompaEntity(
    age = 0,
    country = "fakeCountry",
    email = "fakeEmail",
    favorite = fakeFavoriteEntity,
    first_name = "fakeName",
    gender = "fakeGender",
    height = 0,
    id = 0,
    description = null,
    quota = null,
    image = "fakeImage",
    last_name = "fakeLastName",
    profession = "fakeProfession"

)

val fakeFavoriteRemote  = FavoriteData(
    color = "fakeColor",
    food = "fakeFood",
    random_string = "fakeRandomString",
    song = "fakeSong"
)

val fakeOompaLoompaRemote = OompaLoompaDetailedData(
    age = 0,
    country = "fakeCountry",
    email = "fakeEmail",
    favorite = fakeFavoriteRemote,
    first_name = "fakeName",
    gender = "fakeGender",
    height = 0,
    description = "fakeDescription",
    quota = "fakeQuota",
    image = "fakeImage",
    last_name = "fakeLastName",
    profession = "fakeProfession",
)


class FakePostsPagingSource : PagingSource<Int, OompaLoompaEntity>() {
    private var workers: List<OompaLoompaEntity> = emptyList()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OompaLoompaEntity> =
        LoadResult.Page(
            data = workers,
            prevKey = null,
            nextKey = null
        )

    override fun getRefreshKey(state: PagingState<Int, OompaLoompaEntity>): Int =
        state.anchorPosition ?: 1

}


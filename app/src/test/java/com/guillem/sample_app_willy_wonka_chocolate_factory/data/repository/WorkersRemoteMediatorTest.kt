package com.guillem.sample_app_willy_wonka_chocolate_factory.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.AppDatabase
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.remote.ApiEndpoints
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalPagingApi::class)
class WorkersRemoteMediatorTest{

    @RelaxedMockK
    private lateinit var apiEndpoints: ApiEndpoints

    @RelaxedMockK
    private lateinit var appDatabase: AppDatabase

    private lateinit var remoteMediator: WorkersRemoteMediator

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        remoteMediator = WorkersRemoteMediator(appDatabase, apiEndpoints)
    }

    @Test
    fun `RemoteMediator initialize returns correct action`() = runBlocking {
        val result = remoteMediator.initialize()
        assertEquals(RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH, result)
    }
}

package com.bignerdranch.android.blognerdranch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.models.PostMetadata
import com.bignerdranch.android.blognerdranch.ui.list.PostListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: Repository

    @Mock
    private lateinit var mockObserver: Observer<List<PostMetadata>>

    @Captor
    private lateinit var postListMetadataCaptor: ArgumentCaptor<List<PostMetadata>>

    @Spy
    private val postListMetaDataLiveData: MutableLiveData<List<PostMetadata>> = MutableLiveData()

    @InjectMocks
    private lateinit var postListViewModel: PostListViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun shouldEmitPostList_whenGetPostListIsInvoked() = runBlockingTest {
        whenever(mockRepository.getPostMetadata()).thenReturn(
            listOf(
                PostMetadata(postId = 1),
                PostMetadata(postId = 2),
                PostMetadata(postId = 3)
            )
        )
        postListViewModel.postAdapterLiveData.observeForever(mockObserver)
        launch {
            postListViewModel.getPostList()
        }.join()
        verify(mockObserver).onChanged(postListMetadataCaptor.capture())
        assertEquals(3, postListMetadataCaptor.value.size)
    }

    @After
    fun tearDown() {
        postListViewModel.postAdapterLiveData.removeObserver(mockObserver)
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}
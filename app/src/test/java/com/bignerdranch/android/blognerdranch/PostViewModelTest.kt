package com.bignerdranch.android.blognerdranch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.models.Post
import com.bignerdranch.android.blognerdranch.ui.post.PostViewModel
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
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepository: Repository

    @Mock
    private lateinit var mockObserver: Observer<Post>

    @Captor
    private lateinit var postCaptor: ArgumentCaptor<Post>

    @Spy
    private val postLiveData: MutableLiveData<Post> = MutableLiveData()

    @InjectMocks
    private lateinit var postViewModel: PostViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun shouldEmitPost_whenGetPostIsInvoked() = runBlockingTest {
        whenever(mockRepository.getPost(anyInt())).thenReturn(Post(id = 1))
        postViewModel.postLiveData.observeForever(mockObserver)
        launch {
            postViewModel.getPost(2)
        }.join()
        verify(mockObserver).onChanged(postCaptor.capture())
        assertEquals(1, postCaptor.value.id)
    }

    @After
    fun tearDown() {
        postViewModel.postLiveData.removeObserver(mockObserver)
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}
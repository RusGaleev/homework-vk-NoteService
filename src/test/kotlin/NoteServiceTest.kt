import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock


object NoteService {
    @Before
    fun beforeTest(){
        mockkObject(NoteService)
    }
    @Test
    fun getUserNotesTest(){
        NoteService
        var service = mockkObject(NoteService)

        service
        Assert.assertTrue(NoteService.add(Note(1, 2, true, "11")))
    }
}
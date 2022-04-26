import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun getLastId() {
    }

    @Test
    fun setLastId() {
    }

    @Test
    fun getUserNotes() {
        NoteService.clearNotes()
        val ownerId = 111
        val note = Note(1,ownerId,true, "")
        val notes = arrayOf(note)
        NoteService.add(note)
        assertArrayEquals(NoteService.getUserNotes(ownerId),notes)
    }

    @Test
    fun getById() {
        NoteService.clearNotes()
        val id = 1
        val note = Note(id,1,true, "")
        NoteService.add(note)
        assertEquals(NoteService.getById(id), note)
    }

    @Test
    fun add() {
        NoteService.clearNotes()
        val note = Note(1,1,true, "")
        NoteService.add(note)
        assertTrue(NoteService.getUserNotes(note.ownerId).size == 1)
    }

    @Test
    fun update() {
        NoteService.clearNotes()
        val id =1
        val note = Note(id, 1,true, "")
        NoteService.add(note)
        assertEquals(NoteService.getById(id), note)
    }

    @Test(expected = Exception::class)
    fun delete() {
        NoteService
        val id =1
        val note = Note(id, 1,true, "")
        NoteService.add(note)
        NoteService.delete(note)
        NoteService.getById(id)
    }

    @Test
    fun addComment() {
        NoteService
        val noteId = 1
        val note = Note(noteId, 1,true, "")
        val comment = Comment(1,1, true, "")
        NoteService.add(note)
        NoteService.addComment(noteId, comment)
        assertEquals(NoteService.getById(noteId).comments.last(), comment)
    }

    @Test
    fun updateComment() {
        NoteService.clearNotes()
        val note = Note(1, 1,true, "")
        val comment = Comment(1,1, true, "")
        val commentUpdate = Comment(1,1, true, "123")
        NoteService.add(note)
        NoteService.addComment(note.id, comment)
        NoteService.updateComment(note.id, commentUpdate)
        assertEquals(NoteService.getById(note.id).comments.last(), commentUpdate)
    }
}
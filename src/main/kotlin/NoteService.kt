fun main() {
    val service = NoteService
    service.add(Note(123, 123, true, "Charmander"))
    service.add(Note(321, 123, true, "Oshawott"))
    service.addComment(321, Comment(1, 123, true, "Water"))
}

object NoteService {
    private var notes = emptyArray<Note>()
    var lastId: Int = 0

    fun getUserNotes(ownerId: Int): Array<Note> {
        var userNotes = emptyArray<Note>()
        for ((index, item) in notes.withIndex()) {
            if (item.ownerId == ownerId) {
                if (notes[index].visible)
                    userNotes += notes[index]
            }
        }
        return userNotes
    }

    fun getById(id: Int): Note {
        for ((index, item) in notes.withIndex()) {
            if (item.id == id) {
                if (notes[index].visible) {
                    return notes[index]
                } else throw Exception("Note with ID $id was deleted")
            }
        }
        throw Exception("Note with ID $id does not exist")
    }

    fun add(note: Note): Int {
        notes += note
        return lastId++
    }

    fun update(note: Note): Boolean {
        for ((index, item) in notes.withIndex()) {
            if (item.id == note.id) {
                notes[index] = note
            }
        }
        return true
    }

    fun delete(note: Note): Boolean {
        if (note.visible == false) {
            return false
        }
        note.visible = false
        for ((index, item) in note.comments.withIndex()) {
            item.visible = false //удаление комментариев
        }

        return true
    }

    fun addComment(noteId: Int, comment: Comment): Boolean {
        for ((index, item) in notes.withIndex()) {
            if (item.id == noteId) {
                notes[index].comments.add(comment)
            }
        }
        return true
    }

    fun updateComment(note: Note, comment: Comment): Boolean {
        for ((index, item) in note.comments.withIndex()) {
            if (item.id == comment.id) {
                note.comments[index] = comment
            }
        }
        return true
    }
}

fun deleteComment(note: Note, comment: Comment): Boolean {
    for ((index, item) in note.comments.withIndex()) {
        if (item.id == comment.id) {
            note.comments[index] = item.copy(visible = false)
        }
    }
    return true
}

fun restoreComment(note: Note, comment: Comment): Boolean {
    for ((index, item) in note.comments.withIndex()) {
        if (item.id == comment.id) {
            note.comments[index] = item.copy(visible = true)
        }
    }
    return true
}

data class Note(
    val id: Int,
    val ownerId: Int,
    var visible: Boolean,
    val noteText: String,
    var comments: MutableList<Comment> = mutableListOf()
)

data class Comment(
    val id: Int,
    val ownerId: Int,
    var visible: Boolean,
    val noteText: String
)
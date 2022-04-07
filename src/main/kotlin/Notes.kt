class Notes(
    val id: Int,
    val title: String,
    val text: String,
    val comments: MutableList<Comments> = mutableListOf(),
    private var isDelete: Boolean = true

) {


    fun getStatusNote(): Boolean {
        return isDelete
    }

    fun deleteOrRestoreNote(boolean: Boolean) {
        isDelete = boolean
    }

    fun addComment(comment: Comments) {
        comments.add(comment)
    }
}

object NoteService {
    private val notes = mutableListOf<Notes>()
    private val idComment = mutableListOf<Comments>()

    fun clean() {
        notes.clear()
        idComment.clear()
    }

    fun addNote(note: Notes): Boolean {
        val id = notes.size + 1
        notes.add(Notes(id, note.title, note.text, note.comments, isDelete = note.getStatusNote()))
        return true
    }

    fun createComment(comment: Comments): Boolean {
        for (note in notes) {
            if (note.id == comment.idNotes) {
                idComment += comment
                note.addComment(Comments(comment.idNotes, idComment.size, comment.text, comment.getStatusComment()))
                return true
            }
        }
        throw NoteNotFoundException("not note with id: ${comment.idNotes}")
    }

    fun deleteNote(id: Int): Boolean {
        for (note in notes) {
            if (id == note.id && note.getStatusNote()) {
                note.deleteOrRestoreNote(false)
                return true
            }
        }
        throw NoteNotFoundException("not note with id: $id")
    }

    fun deleteComment(idComm: Int): Boolean {
        for (note in notes) {
            for (comment in note.comments) {
                if (comment.id == idComm && comment.getStatusComment()) {
                    comment.deleteOrRestoreComment(false)
                    return true
                }
            }
        }
        throw CommentNotFoundException("not comments with id: $idComm")
    }

    fun editNote(noteForEdit: Notes): Boolean {
        for (note in notes) {
            if (note.id == noteForEdit.id && note.getStatusNote()) {
                notes[notes.indexOf(note)] = noteForEdit
                return true
            }
        }
        throw NoteNotFoundException("not not with id: ${noteForEdit.id}")
    }

    fun editComment(commentForEdit: Comments): Boolean {
        for (note in notes) {
            if (note.id == commentForEdit.idNotes) {
                for (comment in note.comments) {
                    if (comment.id == commentForEdit.id && comment.getStatusComment()) {
                        note.comments[note.comments.indexOf(comment)] = commentForEdit
                        return true
                    }
                }
            }
        }
        throw CommentNotFoundException("not comment with id: ${commentForEdit.id}")
    }

    fun getNotes(): MutableList<Notes> {
        val notesForGet = mutableListOf<Notes>()
        for (note in notes) {
            if (note.getStatusNote()) {
                notesForGet += note
            }
        }
        return notesForGet

    }

    fun getNoteById(id: Int): Notes {
        for (note in notes) {
            if (note.id == id && note.getStatusNote()) return note
        }
        throw NoteNotFoundException("not note with id: $id")
    }

    fun getComments(idNote: Int): MutableList<Comments> {
        val commentsForGet = mutableListOf<Comments>()
        for (note in notes) {
            if (note.id == idNote && note.getStatusNote()) {
                for (comment in note.comments) {
                    if (comment.getStatusComment()) {
                        commentsForGet += comment
                    }
                }
                return commentsForGet
            }
        }
        throw NoteNotFoundException("note note with id: $idNote")

    }

    fun restoreComment(idComm: Int): Boolean {
        for (note in notes) {
            for (comment in note.comments) {
                if (idComm == comment.id && !comment.getStatusComment()) {
                    comment.deleteOrRestoreComment(true)
                    return true
                }
            }
        }
        return false
    }
}

class NoteNotFoundException(message: String) : RuntimeException(message)
class CommentNotFoundException(message: String) : RuntimeException(message)
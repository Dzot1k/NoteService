import org.junit.After
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTestDelete {

    @After
    fun clean() {
        NoteService.clean()
    }

    @Test
    fun deleteNoteIsTrue() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        val resultDelete = NoteService.deleteNote(1)
        assertTrue(resultDelete)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteIsFalseWithOtherIdNote() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.deleteNote(2)

    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteIsFalseWithDeleteStatusNote() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = false
            )
        )
        NoteService.deleteNote(1)

    }

    @Test
    fun deleteCommentIsTrue() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment"

            )
        )
        val resultComment = NoteService.deleteComment(1)
            assertTrue(resultComment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentIsFalseWithOtherIdComm() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment"

            )
        )
        NoteService.deleteComment(2)

    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentIsFalseWithDeleteStatusComm() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment",
                isDelete = false

            )
        )
        NoteService.deleteComment(1)

    }


}
import org.junit.After
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTestAdd {

    @After
    fun clean() {
        NoteService.clean()
    }

    @Test
    fun addNote() {
        val resultNote = NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        assertTrue(resultNote)
    }

    @Test
    fun createCommentIsTrue() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        val resultComment = NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment"

            )
        )
        assertTrue(resultComment)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentIsFalse() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.createComment(
            Comments(
                idNotes = 5,
                id = 1,
                text = "TextComment"

            )
        )
    }
}
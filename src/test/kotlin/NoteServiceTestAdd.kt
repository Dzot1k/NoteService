import org.junit.After
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTestAdd {

    @After
    fun clean() {
        NoteService.clean()
        CommentsService.clean()
    }

    @Test
    fun addNote() {
        val resultNote = NoteService.add(
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
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        val resultComment = CommentsService.add(
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
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        CommentsService.add(
            Comments(
                idNotes = 5,
                id = 1,
                text = "TextComment"

            )
        )
    }
}
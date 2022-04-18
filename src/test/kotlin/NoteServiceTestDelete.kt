import org.junit.After
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTestDelete {

    @After
    fun clean() {
        NoteService.clean()
        CommentsService.clean()
    }

    @Test
    fun deleteNoteIsTrue() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        val resultDelete = NoteService.delete(1)
        assertTrue(resultDelete)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteIsFalseWithOtherIdNote() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.delete(2)

    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNoteIsFalseWithDeleteStatusNote() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )
        NoteService.delete(1)

    }

    @Test
    fun deleteCommentIsTrue() {
        NoteService.add(
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
        val resultComment = CommentsService.delete(1)
        assertTrue(resultComment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentIsFalseWithOtherIdComm() {
        NoteService.add(
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
        CommentsService.delete(2)

    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentIsFalseWithDeleteStatusComm() {
        NoteService.add(
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
                isDelete = true

            )
        )
        CommentsService.delete(1)

    }


}
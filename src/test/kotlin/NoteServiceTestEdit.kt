import org.junit.After
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTestEdit {

    @After
    fun clean() {
        NoteService.clean()
        CommentsService.clean()
    }

    @Test
    fun editNoteIsTrue() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        val resultEdit = NoteService.edit(
            Notes(
                id = 1,
                title = "TitleNoteEdit",
                text = "TextNoteEdit",
            )
        )

        assertTrue(resultEdit)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editNoteIsFalseWithDeleteStatusNote() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )
        NoteService.edit(
            Notes(
                id = 1,
                title = "TitleNoteEdit",
                text = "TextNoteEdit",
            )
        )
    }

    @Test(expected = NoteNotFoundException::class)
    fun editNoteIsFalseWithOtherIdNote() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.edit(
            Notes(
                id = 3,
                title = "TitleNoteEdit",
                text = "TextNoteEdit",
            )
        )
    }

    @Test
    fun editCommentIsTrue() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment"
            )
        )

        val resultEdit = CommentsService.edit(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextCommentEdit"
            )
        )

        assertTrue(resultEdit)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentIsFalseWithOtherIdNotes() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment"
            )
        )

        CommentsService.edit(
            Comments(
                idNotes = 2,
                id = 1,
                text = "TextCommentEdit"
            )
        )
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentIsFalseWithOtherIdComment() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment"
            )
        )

        CommentsService.edit(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextCommentEdit"
            )
        )
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentIsFalseWithDeleteStatusOfComment() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment",
                isDelete = true
            )
        )

        CommentsService.edit(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextCommentEdit"
            )
        )
    }

}
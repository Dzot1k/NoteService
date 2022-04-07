import org.junit.After
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTestEdit {

    @After
    fun clean() {
        NoteService.clean()
    }

    @Test
    fun editNoteIsTrue() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        val resultEdit = NoteService.editNote(
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
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = false
            )
        )
        NoteService.editNote(
            Notes(
                id = 1,
                title = "TitleNoteEdit",
                text = "TextNoteEdit",
            )
        )
    }

    @Test(expected = NoteNotFoundException::class)
    fun editNoteIsFalseWithOtherIdNote() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.editNote(
            Notes(
                id = 3,
                title = "TitleNoteEdit",
                text = "TextNoteEdit",
            )
        )
    }

    @Test
    fun editCommentIsTrue() {
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

        val resultEdit = NoteService.editComment(
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

        NoteService.editComment(
            Comments(
                idNotes = 2,
                id = 1,
                text = "TextCommentEdit"
            )
        )
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentIsFalseWithOtherIdComment() {
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

        NoteService.editComment(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextCommentEdit"
            )
        )
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentIsFalseWithDeleteStatusOfComment() {
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

        NoteService.editComment(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextCommentEdit"
            )
        )
    }

}
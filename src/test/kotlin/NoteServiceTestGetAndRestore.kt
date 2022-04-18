import org.junit.After
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTestGetAndRestore {

    @After
    fun clean() {
        NoteService.clean()
        CommentsService.clean()
    }

    @Test
    fun getNotesReturnMutableList() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.add(
            Notes(
                id = 2,
                title = "TitleNote2",
                text = "TextNote2",
            )
        )

        val actualResult = NoteService.read()
        val expectedResult = mutableListOf(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            ),
            Notes(
                id = 2,
                title = "TitleNote2",
                text = "TextNote2",
            )
        )

        assertEquals(expectedResult, actualResult)

    }

    @Test
    fun getNotesWithDeleteStatusNote() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.add(
            Notes(
                id = 2,
                title = "TitleNote2",
                text = "TextNote2",
                isDelete = true
            )
        )

        val actualResult = NoteService.read()
        val expectedResult = mutableListOf(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        assertEquals(expectedResult, actualResult)

    }

    @Test
    fun getNoteByIdReturnNote() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        val actualResult = NoteService.getById(1)
        val expectedResult = Notes(
            id = 1,
            title = "TitleNote",
            text = "TextNote",
        )
        assertEquals(expectedResult, actualResult)

    }

    @Test(expected = NoteNotFoundException::class)
    fun getNoteByIdWithOtherIdNote() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.getById(2)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getNoteByIdWithDeleteStatusNote() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )

        NoteService.getById(1)
    }

    @Test
    fun getComments() {
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
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )

        val actualResult = CommentsService.getByIdNote(1)
        val expectedResult = mutableListOf(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment"
            ), Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )

        assertEquals(expectedResult, actualResult)

    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsIsFalseWithOtherIdNote() {
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
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )

        CommentsService.getByIdNote(6)


    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsIsFalseWithDeleteStatusNote() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )

        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment"
            )
        )
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )

        CommentsService.getByIdNote(1)

    }

    @Test
    fun getCommentsIsTrueWithDeleteStatusComment() {
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
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2",
                isDelete = false
            )
        )

        val actualResult = CommentsService.getByIdNote(1)
        val expectedResult = mutableListOf(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2",
            )
        )


        assertEquals(expectedResult, actualResult)

    }

    @Test
    fun getCommentIsTrueByIdComment() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.add(
            Notes(
                id = 2,
                title = "TitleNote2",
                text = "TextNote",
                isDelete = true
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
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2",
            )
        )
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 3,
                text = "TextComment3",
            )
        )

        val actualResult = CommentsService.getById(3)
        val expectedResult =
            Comments(
                idNotes = 1,
                id = 3,
                text = "TextComment3",
            )


        assertEquals(expectedResult, actualResult)

    }


    @Test(expected = CommentNotFoundException::class)
    fun getCommentIsFalseByIdComment() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.add(
            Notes(
                id = 2,
                title = "TitleNote2",
                text = "TextNote",
                isDelete = true
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
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2",
            )
        )
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 3,
                text = "TextComment2",
            )
        )

        CommentsService.getById(4)
    }

    @Test(expected = CommentNotFoundException::class)
    fun getCommentIsFalseByStatusComment() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.add(
            Notes(
                id = 2,
                title = "TitleNote2",
                text = "TextNote",
                isDelete = true
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
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2",
            )
        )

        CommentsService.getById(1)
    }

    @Test
    fun getAllComments() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.add(
            Notes(
                id = 2,
                title = "TitleNote2",
                text = "TextNote",
                isDelete = true
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
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2",
            )
        )

        val actualResult = CommentsService.read()
        val expectedResult = mutableListOf(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )


        assertEquals(expectedResult, actualResult)

    }

    @Test
    fun restoreCommentIsTrue() {
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
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )
        CommentsService.delete(2)
        val result = CommentsService.restore(2)

        assertTrue(result)
    }

    @Test
    fun restoreCommentIsFalseWithOtherIdComment() {
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
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )
        CommentsService.delete(1)
        val result = CommentsService.restore(3)

        assertFalse(result)
    }

    @Test
    fun restoreCommentIsFalseWithNoDeleteStatusComment() {
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
        CommentsService.add(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )

        val result = CommentsService.restore(2)

        assertFalse(result)
    }

    @Test
    fun restoreNoteIsTrue() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )

        val result = NoteService.restore(1)
        assertTrue(result)
    }

    @Test
    fun restoreNoteIsFalseWithOtherId() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = true
            )
        )

        val result = NoteService.restore(2)
        assertFalse(result)
    }

    @Test
    fun restoreNoteIsFalse() {
        NoteService.add(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = false
            )
        )

        val result = NoteService.restore(1)
        assertFalse(result)
    }

}
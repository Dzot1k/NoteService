import org.junit.After
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTestGetAndRestore {

    @After
    fun clean() {
        NoteService.clean()
    }

    @Test
    fun getNotesReturnMutableList() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.addNote(
            Notes(
                id = 2,
                title = "TitleNote2",
                text = "TextNote2",
            )
        )

        val actualResult = NoteService.getNotes()
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

        var result = false
        for (noteFirst in expectedResult) {
            for (noteSecond in actualResult) {
                result =
                    noteFirst.id == noteSecond.id && noteFirst.title == noteSecond.title && noteFirst.text == noteSecond.text
            }
        }


        assertTrue(result)
    }

    @Test
    fun getNotesWithDeleteStatusNote() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )
        NoteService.addNote(
            Notes(
                id = 2,
                title = "TitleNote2",
                text = "TextNote2",
                isDelete = false
            )
        )

        val actualResult = NoteService.getNotes()
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

        var result = false
        for (noteFirst in expectedResult) {
            for (noteSecond in actualResult) {
                result =
                    noteFirst.id == noteSecond.id && noteFirst.title == noteSecond.title && noteFirst.text == noteSecond.text
            }
        }


        assertFalse(result)
    }

    @Test
    fun getNoteByIdReturnNote() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        val actualResult = NoteService.getNoteById(1)
        val expectedResult = Notes(
            id = 1,
            title = "TitleNote",
            text = "TextNote",
        )

        val result: Boolean =
            actualResult.id == expectedResult.id && actualResult.title == expectedResult.title && actualResult.text == expectedResult.text

        assertTrue(result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getNoteByIdWithOtherIdNote() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
            )
        )

        NoteService.getNoteById(2)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getNoteByIdWithDeleteStatusNote() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = false
            )
        )

        NoteService.getNoteById(1)
    }

    @Test
    fun getComments() {
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
        NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )

        val actualResult = NoteService.getComments(1)
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

        var result = false
        for (noteFirst in expectedResult) {
            for (noteSecond in actualResult) {
                result =
                    noteFirst.id == noteSecond.id && noteFirst.text == noteSecond.text && noteFirst.idNotes == noteSecond.idNotes
            }
        }

        assertTrue(result)

    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsIsFalseWithOtherIdNote() {
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
        NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )

        NoteService.getComments(6)


    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentsIsFalseWithDeleteStatusNote() {
        NoteService.addNote(
            Notes(
                id = 1,
                title = "TitleNote",
                text = "TextNote",
                isDelete = false
            )
        )

        NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment"
            )
        )
        NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )

        NoteService.getComments(1)

    }

    @Test
    fun getCommentsIsTrueWithDeleteStatusComment() {
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
            )
        )
        NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2",
                isDelete = false
            )
        )

        val actualResult = NoteService.getComments(1)
        val expectedResult = mutableListOf(
            Comments(
                idNotes = 1,
                id = 1,
                text = "TextComment"
            )
        )

        var result = true
        for (noteFirst in expectedResult) {
            for (noteSecond in actualResult) {
                result =
                    noteFirst.id == noteSecond.id && noteFirst.text == noteSecond.text && noteFirst.idNotes == noteSecond.idNotes
            }
        }

        assertTrue(result)

    }

    @Test
    fun restoreCommentIsTrue() {
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
        NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )
        NoteService.deleteComment(2)
        val result = NoteService.restoreComment(2)

        assertTrue(result)
    }

    @Test
    fun restoreCommentIsFalseWithOtherIdComment() {
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
        NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )
        NoteService.deleteComment(1)
        val result = NoteService.restoreComment(3)

        assertFalse(result)
    }

    @Test
    fun restoreCommentIsFalseWithNoDeleteStatusComment() {
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
        NoteService.createComment(
            Comments(
                idNotes = 1,
                id = 2,
                text = "TextComment2"
            )
        )

        val result = NoteService.restoreComment(2)

        assertFalse(result)
    }
}
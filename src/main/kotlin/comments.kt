class Comments(
    val idNotes: Int,
    val id: Int,
    val text: String,
    private var isDelete: Boolean = true
) {

    fun getStatusComment(): Boolean {
        return isDelete
    }

    fun deleteOrRestoreComment(boolean: Boolean) {
        isDelete = boolean
    }

}
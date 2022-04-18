interface CrudService<E> {
    fun add(item: E): Boolean
    fun delete(id: Int): Boolean
    fun edit(item: E): Boolean
    fun read(): List<E>
    fun getById(id: Int): E
    fun restore(id: Int): Boolean

}
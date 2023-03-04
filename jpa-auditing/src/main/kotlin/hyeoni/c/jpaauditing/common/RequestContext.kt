package hyeoni.c.jpaauditing.common

class RequestContext {

    companion object {
        private val currentAuditorStore = ThreadLocal<String?>()

        var currentAuditor: String
            get() = currentAuditorStore.get() ?: "default"
            set(currentAuditor) {
                currentAuditorStore.remove()
                return currentAuditorStore.set(currentAuditor)
            }
    }
}

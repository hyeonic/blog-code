package hyeoni.c.springdataenvers.domain.post

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.history.RevisionRepository

interface PostRepository : JpaRepository<Post, Long>, RevisionRepository<Post, Long, Long>

fun PostRepository.getPostById(id: Long): Post {
    return this.findById(id)
        .orElseThrow { NotFoundException() }
}

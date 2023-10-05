package hyeoni.c.springdataenvers.domain.post

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>
//    , RevisionRepository<Post, Long, Long>

fun PostRepository.getPostById(id: Long): Post {
    return this.findById(id)
        .orElseThrow { NotFoundException() }
}

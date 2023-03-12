package hyeoni.c.springdataenvers.domain.post

import hyeoni.c.springdataenvers.domain.member.MemberRepository
import hyeoni.c.springdataenvers.domain.member.getMemberById
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class PostService(
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository
) {

    @Transactional
    fun save(content: String, memberId: Long) {
        val post = Post(content, memberRepository.getMemberById(memberId))
        postRepository.save(post)
    }

    @Transactional
    fun update(id: Long, content: String, memberId: Long) {
        val post = postRepository.getPostById(id)
        post.updateContent(content)
    }
}

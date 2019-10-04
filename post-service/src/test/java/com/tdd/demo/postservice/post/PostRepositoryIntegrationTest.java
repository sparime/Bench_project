package com.tdd.demo.postservice.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void findByPostId_thenReturnPost() {

        //  optional.findFirst.orELse to get the actual object
        Post found = postRepository.findById(1).orElse(null);
        assertThat(found.getPostContent()).isEqualToIgnoringCase("first_post");

    }

    @Test
    public void saveUser_thenReturnPostId() {

        Post newPost = new Post(11, "saved_post");
        entityManager.persistAndFlush(newPost);

        // can write a better test
        Post fromDb = postRepository.findPostByPostContent("saved_post").orElse(null);
        assertThat(fromDb.getPostContent()).isEqualToIgnoringCase("saved_post");

    }

    @Test
    public void updatePostContent_thenReturnPostId() {

        Post fromDb = postRepository.findById(10).orElse(null);
        String oldContent = fromDb.getPostContent();

        if (fromDb != null) {
            fromDb.setPostContent("updated_post_content");
        }

        entityManager.persistAndFlush(fromDb);
        Post updatedFromDb = postRepository.findById(10).orElse(null);

        assertThat(oldContent).isNotEqualToIgnoringCase(updatedFromDb.getPostContent());
    }

    @Test
    public void deleteUser_thenReturnPostId() {

        postRepository.deleteById(10);
        assertThat(postRepository.findById(10).orElse(null)).isNull();
    }
}

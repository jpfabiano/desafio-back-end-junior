package com.example.BlogJWT.repositories;

import com.example.BlogJWT.models.BlogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface BlogRepository extends JpaRepository<BlogModel, UUID> {
    @Query(value = "SELECT * FROM blog_teste ORDER BY data_postagem, hora_postagem", nativeQuery = true)
    List<BlogModel> findAllOrderByDataPostagem();

    @Query(value = "SELECT * FROM blog_teste ORDER BY data_postagem DESC, hora_postagem DESC" , nativeQuery = true)
    List<BlogModel> findAllOrderByDataPostagemDesc();

    List<BlogModel> findAllByUsuarioPublicacao( String usuarioPublicacao);
}

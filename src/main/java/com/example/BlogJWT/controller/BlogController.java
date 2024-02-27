package com.example.BlogJWT.controller;

import com.example.BlogJWT.Dtos.blog.BlogDTO;
import com.example.BlogJWT.Dtos.blog.BlogResponse;
import com.example.BlogJWT.Dtos.blog.BlogUpdateDto;
import com.example.BlogJWT.models.BlogModel;
import com.example.BlogJWT.service.UsuarioLogado;
import com.example.BlogJWT.repositories.BlogRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/posts")
public class BlogController {
    @Autowired
    private UsuarioLogado usuarioLogado;

    final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public String definirData() {
        LocalDate dataAtual = LocalDate.now();
        return dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String definirHora() {
        LocalTime horaAtual = LocalTime.now();
        return horaAtual.format(DateTimeFormatter.ofPattern("HH:mm"));
    }


    @PostMapping
    public ResponseEntity<Object> novaPostagem (@RequestBody @Valid BlogDTO blogDTO){
        BlogModel blogModel = new BlogModel();
        BeanUtils.copyProperties(blogDTO,blogModel);

        blogModel.setDataPostagem(definirData());
        blogModel.setHoraPostagem(definirHora());
        blogModel.setStatusAtualizada(false);
        blogModel.setUsuarioPublicacao(usuarioLogado.getUsuarioLogado());

        return ResponseEntity.status(HttpStatus.OK).body(blogRepository.save(blogModel));
    }
    @GetMapping("/desc")
    public ResponseEntity<BlogResponse> obterPostagensDesc(){
        List<BlogModel> posts = blogRepository.findAllOrderByDataPostagemDesc();
        BlogResponse response = new BlogResponse(posts);
        response.add(linkTo(BlogController.class).slash("cresc").withRel("Postagens-crescentes"));
        response.add(linkTo(BlogController.class).slash("user").withRel("Postagens-"+usuarioLogado.getUsuarioLogado()));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/cresc")
    public ResponseEntity<BlogResponse> obterPostagens(){
        List<BlogModel> posts = blogRepository.findAllOrderByDataPostagem();

        BlogResponse response = new BlogResponse(posts);
        response.add(linkTo(BlogController.class).slash("desc").withRel("Postagens-decrescentes"));
        response.add(linkTo(BlogController.class).slash("user").withRel("Postagens-"+usuarioLogado.getUsuarioLogado()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/user")
    public ResponseEntity<BlogResponse> obterPostagensUsuario(){
        List<BlogModel> posts = blogRepository.findAllByUsuarioPublicacao(usuarioLogado.getUsuarioLogado());

        BlogResponse response = new BlogResponse(posts);
        response.add(linkTo(BlogController.class).slash("desc").withRel("Postagens-decrescentes"));
        response.add(linkTo(BlogController.class).slash("cresc").withRel("POstagens-crescentes"));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{idPostagem}")
    public ResponseEntity<Object> obterPostagemID ( @PathVariable(name = "idPostagem") UUID idPostagem){
        Optional<BlogModel> blog = blogRepository.findById(idPostagem);
        if(blog.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicação não encontrada");
        return ResponseEntity.status(HttpStatus.OK).body(blog);
    }
    @PutMapping("/{idPostagem}")
    public ResponseEntity<Object> atualizarPostagem (@PathVariable(name = "idPostagem") UUID idPostagem, @RequestBody @Valid BlogUpdateDto blogUpdateDto){
        Optional<BlogModel> blog = blogRepository.findById(idPostagem);

        if(blog.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicação não encontrada");

        BlogModel blogModel = blog.get();

        if (!usuarioLogado.getUsuarioLogado().equals(blogModel.getUsuarioPublicacao()))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Você não tem permissão para editar essa publicação");

        blogModel.setTexto(blogUpdateDto.getTexto());
        blogModel.setDataPostagem(definirData());
        blogModel.setHoraPostagem(definirHora());
        blogModel.setStatusAtualizada(true);

        return ResponseEntity.status(HttpStatus.OK).body(blogRepository.save(blogModel));
    }
    @DeleteMapping("/{idPostagem}")
    public ResponseEntity<Object> deletarPostagem (@PathVariable(name = "idPostagem") UUID idPostagem){
        Optional<BlogModel> blog = blogRepository.findById(idPostagem);
        if(blog.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicação não encontrada");
        blogRepository.delete(blog.get());
        return ResponseEntity.status(HttpStatus.OK).body("Post deletado com sucesso!\n"+blog);
    }


}

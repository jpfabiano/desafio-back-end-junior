package com.example.BlogJWT.Dtos.blog;

import com.example.BlogJWT.models.BlogModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
@Data
@AllArgsConstructor
public class BlogResponse extends RepresentationModel <BlogResponse> {
    private List<BlogModel> posts;
}

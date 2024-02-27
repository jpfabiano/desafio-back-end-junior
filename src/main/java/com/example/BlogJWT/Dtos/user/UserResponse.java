package com.example.BlogJWT.Dtos.user;

import com.example.BlogJWT.Dtos.blog.BlogResponse;
import com.example.BlogJWT.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
public class UserResponse extends RepresentationModel<BlogResponse> {
    private UserModel userModel;
}
